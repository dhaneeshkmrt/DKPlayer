package com.dk.android.dkplayer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.widget.SeekBar;
import android.widget.Toast;

import com.dk.android.dkplayer.shared.SharedValues;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PlayerController {
    private Activity activity = null;
    private MediaPlayer mediaPlayer = null;
    private MediaRecorder mediaRecorder = null;
    private SeekBar seekBar= null;
    private String mediaRocorderState = "NotStarted";

    PlayerController(Activity activity, SeekBar seekBar){
       this.activity = activity;
       this.mediaPlayer= new MediaPlayer();
       this.mediaRecorder = new MediaRecorder();
       this.seekBar = seekBar;
    }

    boolean startVoiceRecorder(String fileNameWithPath) {
        try {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(fileNameWithPath);

            mediaRecorder.prepare();
            mediaRecorder.start();
            mediaRocorderState = "Recording";

        } catch (IllegalStateException ise) {
            ise.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        } finally {
            return true;
        }
    }

    boolean stopVoiceRecorder(){
        if(mediaRecorder != null && mediaRocorderState == "Recording"){
            mediaPlayer.stop();
            return  true;
        } else {
            return false;
        }
    }

    boolean playRecordedVoice(final String fileName){

        if(mediaRecorder != null){
            if( mediaPlayer.isPlaying()){
                mediaRecorder.stop();
            }
            mediaRecorder.release();
        }

        if(mediaPlayer == null){
            mediaPlayer = new MediaPlayer();
        }

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(fileName);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mp.start();
                    mediaPlayer.start();
                    seekBar.setMax(mediaPlayer.getDuration());
                    seekBar.setProgress(0);
                }
            });
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        } finally {
            return false;
        }
    }




}
