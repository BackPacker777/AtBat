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

/**
 * Created by bates.he.z on 5/22/2014.
 */

public class DataHandler {
	private final String FILENAME = "atbat.csv";
	private Context context;

	public DataHandler(Context context) {
		this.context = context;
		checkExternalMedia();
		writeData();
		readData();
	}

	private void checkExternalMedia(){
		boolean mExternalStorageAvailable = false;
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			// Can read and write the media
			mExternalStorageAvailable = mExternalStorageWriteable = true;
//			Toast.makeText(context, "Storage readable.", Toast.LENGTH_SHORT).show();
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
		File root = android.os.Environment.getExternalStorageDirectory();
		File file = new File(root.getAbsolutePath() + "/atbat/atbat.csv");
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedReader dataReader = new BufferedReader(new InputStreamReader(fis));
			String inputString;
			StringBuffer stringBuffer = new StringBuffer();
			while ((inputString = dataReader.readLine()) != null) {
				stringBuffer.append(inputString + "\n");
//				Toast.makeText(context, inputString, Toast.LENGTH_SHORT).show();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeData(){
		File root = android.os.Environment.getExternalStorageDirectory();
		File dir = new File(root.getAbsolutePath() + "/atbat");
		dir.mkdirs();
		File file = new File(dir, FILENAME);
		try {
			FileOutputStream f = new FileOutputStream(file);
			PrintWriter pw = new PrintWriter(f);
			pw.println("Kenan Bates");
			pw.println("Tommy Skinner");
			pw.flush();
			pw.close();
			f.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
