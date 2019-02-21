package com.dk.android.dkplayer.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class PermissionController {

    public static void getAllPermission(Context context, Activity activity, String permission[]){
        if(context != null && activity != null && permission.length > 0) {
            for(int i = 0; i< permission.length; i++){
                Boolean permissionStatus = PermissionController.getPermission(context,activity, permission[i]);
                // try once again to get permission
                if(permissionStatus == false) {
                    PermissionController.getPermission(context,activity, permission[i]);
                }
            }
        }
    }

    public  static boolean getPermission(Context context, Activity activity, String permission ){

        if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{permission},0);
            return false;
        } else {
            return true;
        }
    }
}
