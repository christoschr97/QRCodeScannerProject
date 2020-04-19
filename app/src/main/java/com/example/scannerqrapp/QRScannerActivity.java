package com.example.scannerqrapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.scannerqrapp.model.QRURLModel;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Objects;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {


    private ZXingScannerView scannerView;
    private TextView txtResult;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);
        Objects.requireNonNull(getSupportActionBar()).setTitle("SCAN FOR MENU");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Initialize the scanner
        scannerView = (ZXingScannerView) findViewById(R.id.zxscan);
        txtResult = (TextView) findViewById(R.id.txt_result);

        //Request permission
        Dexter.withActivity(this).
                withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        scannerView.setResultHandler(QRScannerActivity.this);
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(QRScannerActivity.this, "You must accept this permission", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
    }
    //if its destroyed we close the camera so it doesnt stay open
    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    //this method must be override sto we can set Result handler to the main activity context
    @Override
    public void handleResult(Result rawResult){
        processRawResult(rawResult.getText());
    }

    //
    private void processRawResult(String text){
        QRURLModel qrurlModel = new QRURLModel(text);
        if(text.startsWith("http://") || text.startsWith("https://") || text.startsWith("www.")){
            txtResult.setText(qrurlModel.getUrl());
            scannerView.startCamera();
            Intent menuActivity = new Intent(getApplicationContext(), MenuActivity.class);
            menuActivity.putExtra("com.example.scannerqrapp.URL", qrurlModel.getUrl());
            startActivity(menuActivity);
        } else {
            qrurlModel.setUrl("QR CODE NOT FOUND!");
        }


    }

}
