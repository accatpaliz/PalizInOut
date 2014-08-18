package com.example.virdi;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.TextView;

public class Search extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.search);
		TextView ID = (TextView) findViewById(R.id.searchtextView1);
		TextView gname = (TextView) findViewById(R.id.searchtextView2);
		TextView name = (TextView) findViewById(R.id.searchtextView3);
		ID.setText(Edit.number.getText().toString());
		name.setText(Edit.name.getText().toString());
		gname.setText(Edit.gname.getText().toString());
	}
}
