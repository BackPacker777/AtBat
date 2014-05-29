package org.howardbates.atbat;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by bates.he.z on 5/22/2014.
 */

public class DataHandler {
	private final String FILENAME = "atbat.csv";
	private ArrayList<String > names;
	private Context context;
	private File roster;

	public DataHandler(Context context) {
		File root = android.os.Environment.getExternalStorageDirectory();
		roster = new File(root.getAbsolutePath() + "/atbat/atbat.csv");
		names = new ArrayList<String>();
		this.context = context;
		checkExternalMedia();
		if (!roster.exists()) {
			writeData();
		}
		readData();
	}

	private void checkExternalMedia(){
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// Can read and write the media
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			// Can only read the media
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			// Can't read or write
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
	}

	private void readData(){
		int counter = 0;
		try {
			FileInputStream fis = new FileInputStream(roster);
			BufferedReader dataReader = new BufferedReader(new InputStreamReader(fis));
			String inputString;
			StringBuffer stringBuffer = new StringBuffer();
			while ((inputString = dataReader.readLine()) != null) {
				names.add(inputString);
//				stringBuffer.append(inputString + "\n");
//				Toast.makeText(context, names.get(counter), Toast.LENGTH_SHORT).show();
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeData(){
		File root = android.os.Environment.getExternalStorageDirectory();
		File dir = new File(root.getAbsolutePath() + "/atbat");
		dir.mkdirs();
		try {
			FileOutputStream f = new FileOutputStream(roster);
			PrintWriter pw = new PrintWriter(f);
			pw.println("Alex Feeley");
			pw.println("Kevin Kozlowski");
			pw.println("Kenan Bates");
			pw.println("Josiah Arndt");
			pw.println("Tommy Skinner");
			pw.println("Gordon McKenzie");
			pw.println("Alan Elya");
			pw.println("Conner Meengs");
			pw.println("Jackson Jacobs");
			pw.println("Kenneth Roberts");
			pw.println("Hayden Simon");
			pw.flush();
			pw.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getNames() {
		return names;
	}
}
