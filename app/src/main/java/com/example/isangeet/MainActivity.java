package com.example.isangeet;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(MainActivity.this, "RunTime Permission Given", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                      permissionToken.continuePermissionRequest();
                    }
                })
                .check();
        public ArrayList<File> fetchSongs(File file){
            ArrayList arraylist=new ArrayList();
            File [] songs=file.listFiles();
            for(File myFile:songs){
                if(!myFile.isHidden()&&myFile.isDirectory()){
                    arraylist.addALL(fetchSongs(myFile));
                }
                else{
                    if(myFile.getname().endsWith(".mp3")&&!myFile.getname().startsWith("."));
                    arraylist.add(myFile);
                }
            }
        }
        return arraylist;
    }
}