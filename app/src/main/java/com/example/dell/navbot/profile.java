package com.example.dell.navbot;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class profile extends AppCompatActivity {
    ImageView profileimage2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        profileimage2=(ImageView)findViewById(R.id.profileimage2);

    }


}
