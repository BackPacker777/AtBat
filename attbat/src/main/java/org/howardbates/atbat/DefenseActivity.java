package org.howardbates.atbat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefenseActivity extends Activity {
	private ArrayList<String> names, positions;
	private SimpleAdapter listNames;
	private ListView fieldPositions;
	private List<Map<String, String>> data;
	private Map<String, String> datum;

	protected void onCreate(Bundle savedInstanceState, ArrayList<String> names) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_defense);
		fieldPositions = (ListView) findViewById(R.id.battingList);
		this.names = names;
		setPositions();
//		setFieldPositions();
		returnOnClick();
	}

	private void setPositions() {
          positions = new ArrayList<String>();
		positions.add("Catcher");
		positions.add("Pitcher");
		positions.add("Short Stop");
		positions.add("1st Base");
		positions.add("2nd Base");
		positions.add("3rd Base");
		positions.add("Left Field");
		positions.add("Center Left");
		positions.add("Center Right");
		positions.add("Right Field");
	}

	private void setFieldPositions() {
		int counter = 0;
		data = new ArrayList<Map<String, String>>();
		for (String name : names) {
			datum = new HashMap<String, String>(2);
			datum.put("Player: ", names.get(counter));
			datum.put("Position: ", positions.get(counter));
			data.add(datum);
			counter++;
		}
		listNames = new SimpleAdapter(this, data,
				android.R.layout.simple_list_item_2,
				new String[] {"Player", "Position"},
				new int[] {android.R.id.text1,
				android.R.id.text2
			});
		fieldPositions.setAdapter(listNames);
//		http://stackoverflow.com/questions/7916834/android-adding-listview-sub-item-text
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
