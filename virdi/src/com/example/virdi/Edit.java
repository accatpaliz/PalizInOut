package com.example.virdi;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends Activity {
	public static EditText gname,name,number;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.edit);
		Button edit = (Button) findViewById(R.id.picture);
		Button search = (Button) findViewById(R.id.button2);
		number = (EditText) findViewById(R.id.editText1);
		gname = (EditText) findViewById(R.id.num);
		name = (EditText) findViewById(R.id.editText2);
		
        edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String s1 = number.getText().toString();
				
				//Personnel p = new Personnel(Integer.valueOf(number.toString()), "a", "a");
				if(MainActivity.db.isPersonnel(s1)){
					if (gname.getText().toString().matches("")){
						gname.setText(MainActivity.db.getPersonnel(s1).getGName().toString());
					}
					if (name.getText().toString().matches("")){
						name.setText(MainActivity.db.getPersonnel(s1).getName().toString());
					}
					MainActivity.db.deletePersonnel(MainActivity.db.getPersonnel(s1));
					MainActivity.db.add(new Personnel(Integer.valueOf(s1), gname.getText().toString(), name.getText().toString()));
				}
				else{
					Toast.makeText(getApplicationContext(), "not found!",Toast.LENGTH_LONG).show();
					gname.setText("");
					name.setText("");
				}
					
			}
		});
        search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String s1 = number.getText().toString();
				if(MainActivity.db.isPersonnel(s1)){
					if (gname.getText().toString().matches("")){
						gname.setText(MainActivity.db.getPersonnel(s1).getGName().toString());
					}
					if (name.getText().toString().matches("")){
						name.setText(MainActivity.db.getPersonnel(s1).getName().toString());
					}
					startActivity(new Intent(Edit.this, Search.class));
				}
				else{
					Toast.makeText(getApplicationContext(), "not found!",Toast.LENGTH_LONG).show();
					gname.setText("");
					name.setText("");
				}
			}
		});

	}

}
