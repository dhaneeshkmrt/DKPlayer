package com.dk.android.dkplayer;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    public static String outputDirectory=Environment.getExternalStorageDirectory().getPath()+ "/Music/recording/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFilesListView(outputDirectory);
    }

    void setupFilesListView(String path){
        ListView mediaListView = findViewById(R.id.media_List);
        TextView noMediaFound = findViewById(R.id.no_media_found);
        TextView directory_path = findViewById(R.id.directory_path);
        directory_path.setText(path);

        File directory = new File(path);
        if(directory.exists()){
            String filesList[] = directory.list();
            if(filesList != null && filesList.length > 0 ){
                ArrayAdapter ar = new MediaListAdapter(this, filesList);
                mediaListView.setAdapter(ar);
                noMediaFound.setVisibility(View.INVISIBLE);
            } else {
                System.out.println("Error DK: No files in the directory. ...");
                noMediaFound.setVisibility(View.VISIBLE);
            }
        } else {
            noMediaFound.setVisibility(View.VISIBLE);
            System.out.println("Error DK: directory not exists. ...");
        }
    }
}
