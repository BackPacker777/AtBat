package org.howardbates.atbat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class DefenseActivity extends Activity {
	private String[] passNames;
	private ArrayList<String> names;
	private ArrayAdapter<String> listNamesD;
	private ListView fieldPositions;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_defense);
		fieldPositions = (ListView) findViewById(R.id.fieldPositions);
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		String[] passNames = b.getStringArray("array");
		names = new ArrayList<String>(Arrays.asList(passNames));
		setFieldPositions();
		returnOnClick();
	}

	private void setFieldPositions() {
		listNamesD = new ArrayAdapter<String>(
			this, android.R.layout.simple_list_item_2, names);
		fieldPositions.setAdapter(listNamesD);
		/*fieldPositions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long arg3) {
				view.setSelected(true);
//				http://stackoverflow.com/questions/16189651/android-listview-selected-item-stay-highlighted
			}
		});*/
	}

     private void returnOnClick() {
          ImageButton returnButton = (ImageButton) findViewById(R.id.returnButton);
          returnButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    finish();
               }
          });
     }
}
