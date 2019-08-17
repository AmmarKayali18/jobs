package com.example.dell.navbot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=intent.getExtras();
        String tilte= bundle.getString("t");
        String massage= bundle.getString("m");
        int image= bundle.getInt("img");
        Toast.makeText(context,tilte+"  "+massage+"   ",Toast.LENGTH_SHORT).show();
        MyNotification nf=new MyNotification();
        nf.notify(context,tilte,massage,image,123);
      /*

       */
    }
}
