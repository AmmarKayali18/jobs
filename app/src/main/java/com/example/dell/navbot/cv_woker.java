package com.example.dell.navbot;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

public class cv_woker extends AppCompatActivity {
   EditText firstname;
    EditText lastname;
    EditText phone_number;
    EditText address;
    EditText nickname;
    Spinner mydate;
    Spinner staudy;
    Spinner qualification;
    Button create_cv;
    ImageView profileimage;
    final int PICK_IMAGE_REQUST=234;
    private Uri file_path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cv_worker);
        firstname=(EditText)findViewById(R.id.firstname);
        lastname=(EditText)findViewById(R.id.lastname);
        phone_number=(EditText)findViewById(R.id.phone_number);
        address=(EditText)findViewById(R.id.address);
        nickname=(EditText)findViewById(R.id.nickname);
        mydate=(Spinner)findViewById(R.id.date);
        staudy=(Spinner)findViewById(R.id.staudy);
        qualification=(Spinner)findViewById(R.id.Qualification);
        create_cv=(Button)findViewById(R.id.create_cv);
        profileimage=(ImageView)findViewById(R.id.profileimage);
        String id_email = getIntent().getStringExtra("id_email");

    }

    public void create_cv(View view)
    {
        try
        {
            // put your data here
            String fullname=firstname.getText().toString()+lastname.getText().toString()+" ("+nickname.getText().toString()+") \n";
            String add=address.getText().toString()+" / "+phone_number.getText().toString();
            int image =profileimage.getId();
            Toast.makeText(getApplicationContext(),fullname+add,Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {

        }
    }

    public void addimgae(View view) {
        showfileshosen();
    }

     private void showfileshosen()
     {
         Intent intent=new Intent();
         intent.setType("image/*");
         intent.setAction(Intent.ACTION_GET_CONTENT);
         startActivityForResult(Intent.createChooser(intent,"select image to your profile"),PICK_IMAGE_REQUST);
     }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode==PICK_IMAGE_REQUST) && (resultCode==RESULT_OK) && (data!=null) && (data.getData()!=null))
        {
            file_path=data.getData();
            try
            {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),file_path);
                profileimage.setImageBitmap(bitmap);
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
