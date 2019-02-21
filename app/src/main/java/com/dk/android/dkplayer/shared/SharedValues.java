package com.dk.android.dkplayer.shared;

import android.os.Environment;

import com.dk.android.dkplayer.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SharedValues {
    public static String outputDirectory=Environment.getExternalStorageDirectory().getPath() + "/Music/recording/";

    public static String fileNamePrefix = "recording_";
    public static String outputFormat = ".3gp";

    public static String getCurrentTime(){
        long currentDateTime = System.currentTimeMillis();

        //creating Date from millisecond
        Date currentDate = new Date(currentDateTime);

        //printing value of Date
        System.out.println("current Date: " + currentDate);

        DateFormat df = new SimpleDateFormat("ddMMyyHHmmss");

        return df.format(currentDateTime);

    }

}
