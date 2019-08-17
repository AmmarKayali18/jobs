package com.example.dell.navbot;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.security.Provider;

public class myservice extends IntentService {

    public static boolean ServiceIsRun=false;
    public String title="title";
    public String massage="massage";
    public int image=R.drawable.logo;
    public myservice() {
        super("myservices");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        while (ServiceIsRun) {
            //type here code that help us to get notification from server
            //set title
            //set massage
            //set image

            try {
                Thread.sleep(3000);
               /* Intent i = new Intent(this, MyBroadCast.class);
                i.putExtra("t",title);
                i.putExtra("m",massage);
                i.putExtra("img",image);
                sendBroadcast(i);*/
            } catch (Exception e) {
            }

        }


    }
}
