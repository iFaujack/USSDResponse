package com.medkommandiri.ussdreponses;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.karan.churi.PermissionManager.PermissionManager;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    PermissionManager permissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(MainActivity.this,USSDService.class));

        permissionManager = new PermissionManager() {};
        permissionManager.checkAndRequestPermissions(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionManager.checkResult(requestCode,permissions,grantResults);

    }

    private void dialNumber(String code){
        String ussdcode = "*"+code+ Uri.encode("#");
        startActivity(new Intent("android.intent.action.CALL",Uri.parse("tel:"+ussdcode)));
    }

    public void CALL(View view) {
        dialNumber("806*77");
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView = findViewById(R.id.respon);
                textView.setText(String.valueOf(USSDService.responUSSD));

            }
        },2000);

    }




}
