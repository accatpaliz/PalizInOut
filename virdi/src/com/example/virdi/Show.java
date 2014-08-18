package com.example.virdi;

import java.util.ArrayList;
import java.util.List;

import com.example.virdi.Personnel;
import com.example.virdi.DatabaseHandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;


public class Show extends Activity {
    private  ListView listView;
    private Button backbutton;
    public static ArrayList<String> ArrayofName = new ArrayList<String>();
    final Context context = this;
    @Override
    public void onBackPressed() {
    }
    @Override
    protected void onResume() {

       super.onResume();
       this.onCreate(null);
    }
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.show);
        ArrayofName.clear();
        MainActivity.db.getAllPersonnel();
        
        listView = (ListView) findViewById(R.id.listView1);
        backbutton = (Button) findViewById(R.id.back); 
        backbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();				
			}
		});
        
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ArrayofName);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			private List<Personnel> val;

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int pos, long id) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Delete entry");
				alertDialogBuilder.setMessage("Are you sure you want to delete this entry?")
								  .setPositiveButton(android.R.string.yes,
										  new DialogInterface.OnClickListener() {
									  				public void onClick(DialogInterface dialog, int which) { 
									  						String temp1 =ArrayofName.get(pos);
									  						int a = temp1.indexOf("\n");
									  						String temp2 = temp1.substring(0, a);
									  						Personnel personnel = new Personnel(Integer.valueOf(temp2),"a","a");
									  						MainActivity.db.deletePersonnel(personnel);
									  						finish();
									  						startActivity(getIntent());
									  						
									  				}
								  })
								  .setNegativeButton(android.R.string.no,
										  new DialogInterface.OnClickListener() {
									  				public void onClick(DialogInterface dialog, int which) {
									  					dialog.cancel();
									  				}
			     });
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				return true;
			}
		});
    }
}
