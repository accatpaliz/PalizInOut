package com.example.virdi;

import com.example.virdi.Personnel;
import com.example.virdi.MainActivity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Adduser extends Activity {
    @Override
    public void onBackPressed() {
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.adduser);
		Button back = (Button) findViewById(R.id.button1);
	
        Button add = (Button) findViewById(R.id.add);
        Button clear = (Button) findViewById(R.id.clearlist);
        final EditText gname = (EditText) findViewById(R.id.givenname);
        final EditText name = (EditText) findViewById(R.id.surname);
        gname.requestFocus();
        final EditText number = (EditText) findViewById(R.id.num);
       
        back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String temp=number.getText().toString();
				if(number.getText().toString().matches("")||name.getText().toString().matches("")||gname.getText().toString().matches(""))
					Toast.makeText(getApplicationContext(), "complete all fields and try again!",Toast.LENGTH_LONG).show();
				else if(MainActivity.db.isPersonnel(temp)){
					Toast.makeText(getApplicationContext(), "Number is repeated! Try a different number",Toast.LENGTH_LONG).show();
				}
				else{
					//Personnel temp;
					//int num=0;
					//num = Integer.parseInt(number.getText().toString());
					//temp = getPersonnel(num);
					MainActivity.db.add(new Personnel(Integer.parseInt(number.getText().toString()),name.getText().toString(),gname.getText().toString()));
					Toast.makeText(getApplicationContext(), "added",Toast.LENGTH_LONG).show();
				}
			}
		});
        clear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				gname.setText("");
				name.setText("");
				number.setText("");
			}
		});

	}
}
