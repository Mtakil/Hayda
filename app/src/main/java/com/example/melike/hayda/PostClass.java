package com.example.melike.hayda;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by melike on 6.09.2017.
 */

public class PostClass extends ArrayAdapter<String> {
    private final ArrayList<String> username;
    private final ArrayList<String> userattendancename;

    private final Activity context;

    public PostClass(ArrayList<String> username, ArrayList<String> userattendancename, Activity context) {
        super(context,R.layout.custom_view,username);
        this.username = username;
        this.userattendancename = userattendancename;

        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.custom_view,null,true);

        TextView useremailText=(TextView)customView.findViewById(R.id.username);
        TextView userattendancenameText=(TextView)customView.findViewById(R.id.userattendancename);

        useremailText.setText(username.get(position));
        userattendancenameText.setText(userattendancename.get(position));




        return customView;
    }
}

