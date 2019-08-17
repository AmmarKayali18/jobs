package com.example.dell.navbot;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.RequestQueue;

public class create_project extends AppCompatActivity {
    ImageView imageproject;
    private Uri file_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project);
        imageproject =(ImageView)findViewById(R.id.imageproject);
    }

    public void chose_imagee(View view) {
        showfileshosen();
    }
    private void showfileshosen()
    {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select image to your profile"),234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode==234) && (resultCode==RESULT_OK) && (data!=null) && (data.getData()!=null))
        {
            file_path=data.getData();
            try
            {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),file_path);
                imageproject.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {

            }
        }
    }
    public  String getImagPath(Uri uri)
    {
        String[] projection ={MediaStore.Images.Media.DATA};
        Cursor cursor=getContentResolver().query(uri,projection,null,null,null);
        if(cursor==null)
        {
            return  null;
        }
        int columindex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s=cursor.getString(columindex);
        cursor.close();
        return  s;
    }
}
