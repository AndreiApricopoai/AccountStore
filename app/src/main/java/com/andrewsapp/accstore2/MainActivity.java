package com.andrewsapp.accstore2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private TextView  txt;

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
        setContentView(R.layout.activity_main);

        img=(ImageView)findViewById(R.id.startAppLogo);

        txt=(TextView)findViewById(R.id.startAppText);

        img.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);

        final Animation a= AnimationUtils.loadAnimation(getApplicationContext(),android.R.anim.fade_in);
        a.setDuration(1300);


        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                img.setVisibility(View.VISIBLE);
                txt.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        new CountDownTimer(500, 1000) {

            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {

                img.startAnimation(a);
                txt.startAnimation(a);

            }
        }.start();


        new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {

            }
            public void onFinish() {

                final String PREFERENCE_FILE_KEY =getString(R.string.SharedPref).toString();
                Context context =getApplicationContext();
                SharedPreferences sharedPreferences=context.getSharedPreferences(PREFERENCE_FILE_KEY,Context.MODE_PRIVATE);
                String condition=sharedPreferences.getString("SAVEDPASS","NotFound").toString();


                if(condition.equals("NotFound")){
                    Intent intent = new Intent(MainActivity.this, OneTime.class);
                    startActivity(intent);
                    finish();
                }
                else if(condition.equals("AlreadySaved")) {

                    String login=sharedPreferences.getString("LOGINCONDITION","NotFound").toString();
                    if(login.equals("Login")||login.equals("NotFound")) {
                        Intent intent = new Intent(MainActivity.this, Login.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(MainActivity.this, Accounts.class);
                        startActivity(intent);
                        finish();
                    }

                }
            }
        }.start();
    }
}