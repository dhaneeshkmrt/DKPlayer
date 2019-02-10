package com.dk.android.dkplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MediaListAdapter extends ArrayAdapter {
    String mediaList[];
    public MediaListAdapter(Context context, String mediaList[]) {
        super(context, 0, mediaList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.media_list, parent, false);
        }

        String mediaName = (String) getItem(position);
        TextView mediaNameLabel = convertView.findViewById(R.id.media_name);
        mediaNameLabel.setText(mediaName);

        return convertView;
    }
}
