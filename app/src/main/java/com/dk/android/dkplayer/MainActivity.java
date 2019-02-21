package com.dk.android.dkplayer;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dk.android.dkplayer.controllers.PermissionController;
import com.dk.android.dkplayer.shared.SharedValues;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private String permissions[] = { Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    public PlayerController playerController;
    public static String mediaNameToPlay = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get all permissions
        PermissionController.getAllPermission(this.getApplicationContext(), this, permissions);
        SeekBar seekBar = findViewById(R.id.playerSeekBar);
        playerController = new PlayerController(MainActivity.this, seekBar);

        // setup player default files list
        setupFilesListView(SharedValues.outputDirectory);

        setupEventListener();
    }

    void setupEventListener() {
        Button play = findViewById(R.id.playBtn);
        Button stop = findViewById(R.id.stopBtn);
        Button start = findViewById(R.id.startBtn);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outputFileNameWithPath = SharedValues.outputDirectory + SharedValues.fileNamePrefix+ SharedValues.getCurrentTime()+ SharedValues.outputFormat;
                if(playerController.startVoiceRecorder(outputFileNameWithPath)) {
                    // do sturff in recording
                    MainActivity.mediaNameToPlay = outputFileNameWithPath;
                    System.out.println("dk: voice recorder stated");
                }else {
                    System.out.println("dk: unable to start voice recorder ");
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playerController.stopVoiceRecorder()) {
                    // do sturff in recording
                    System.out.println("dk:stopped voice recorder ");

                } else {
                    System.out.println("dk: unable to stop voice recorder ");

                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( MainActivity.mediaNameToPlay != null && playerController.playRecordedVoice(MainActivity.mediaNameToPlay)) {
                    // do sturff in recording
                }
            }
        });
    }


    void setupFilesListView(String path){
        // create directory if not exists
        File recordingsDirectory = new File(path);
        if(!recordingsDirectory.exists()){
            recordingsDirectory.mkdirs();
        }

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
