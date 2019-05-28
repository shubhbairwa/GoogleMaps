package com.bairwa.googlemaps;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

public class MainActivity extends AppCompatActivity {
private final static String  TAG="MainActivity";
private final static int  Error_dialog_request=9000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   if (isServicesOk()){
       init();
   }

    }

    private void init(){
        Button mapButton=findViewById(R.id.map);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Mapactivity.class);
                startActivity(intent);
            }
        });
    }


    public boolean isServicesOk(){
        Log.d(TAG, "isServicesOk: check google service version");
        // it check the service is available or not
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);
    if (available== ConnectionResult.SUCCESS){
        //everything is fine user can make map request
        Log.d(TAG, "isServicesOk: Google play service is working");
    return true;
    }
    //an error that we can fix
    else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
        Log.d(TAG, "isServicesOk: error occured but we can fix it");
        Dialog dialog=GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this,available,Error_dialog_request);
    }
else{
        Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
    }
return false;

    }
}
