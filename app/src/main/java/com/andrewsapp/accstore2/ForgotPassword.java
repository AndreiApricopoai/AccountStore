package com.andrewsapp.accstore2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class ForgotPassword extends AppCompatActivity {

    public Button reset;
    public ImageView returnArrow;
    public Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        Window w= getWindow();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        reset=findViewById(R.id.forgotPassReset);
        returnArrow=findViewById(R.id.forgotPassGoBack);
        database=new Database(this);


        returnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt=getString(R.string.warning);
                AlertDialog.Builder builder=new AlertDialog.Builder(ForgotPassword.this);
                builder.setCancelable(true);
                builder.setTitle( Html.fromHtml("<font color='#333333'>"+txt+"</font>"));


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        //reset shared preferences
                        final String PREFERENCE_FILE_KEY =getString(R.string.SharedPref).toString();
                        Context context =getApplicationContext();
                        SharedPreferences sharedPreferences=context.getSharedPreferences(PREFERENCE_FILE_KEY,Context.MODE_PRIVATE);
                        sharedPreferences.edit().clear().apply();

                        //reset database rows
                        database.delete_all_records();

                        //go to other activities
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            Intent intent=new Intent(ForgotPassword.this,MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        }
                        else{
                            Intent intent=new Intent(ForgotPassword.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawableResource(R.color.dialogBackground);
                alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ff0000"));
                alertDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#000000"));
            }
        });




    }
}