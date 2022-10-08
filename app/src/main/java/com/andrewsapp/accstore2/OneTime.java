package com.andrewsapp.accstore2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OneTime extends AppCompatActivity {

    private Button btn;
    private EditText editText;
    private TextView tv;
    private ImageView imageView;
    private boolean clicked=true;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Window w= getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.white));

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
// clear FLAG_TRANSLUCENT_STATUS flag:
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color

            w.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.main));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_time);



        btn=(Button)findViewById(R.id.SaveButton);
        editText=(EditText)findViewById(R.id.ediTextPass);
        tv=(TextView)findViewById(R.id.redText);
        imageView=(ImageView)findViewById(R.id.FirstEye);
        linearLayout=(LinearLayout)findViewById(R.id.onetimeBackground) ;

        linearLayout.setBackgroundResource(R.drawable.background3);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password=editText.getText().toString();
                password=password.trim().toString();

                if(password.length()>=5 && password.length()<=40) {

                    final String PREFERENCE_FILE_KEY =getString(R.string.SharedPref).toString();
                    Context context =getApplicationContext();
                    SharedPreferences sharedPreferences=context.getSharedPreferences(PREFERENCE_FILE_KEY,Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    try {
                        editor.putString("PASSWORD",AESUtils.encrypt(password).trim());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    editor.apply();

                    try {
                        if(sharedPreferences.getString("PASSWORD","null").equals(AESUtils.encrypt(password).trim())) {
                            editor.putString("SAVEDPASS","AlreadySaved");
                            editor.apply();
                            tv.setText(R.string.successfully);
                            tv.setTextColor(getResources().getColor(R.color.succes));


                            new CountDownTimer(700, 1000) {

                                public void onTick(long millisUntilFinished) {

                                }
                                public void onFinish() {

                                    Intent intent=new Intent(OneTime.this,Accounts.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }.start();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if(password.length()<5){
                    tv.setText(R.string.bad);
                    tv.setTextColor(getResources().getColor(R.color.red));
                }
                else {

                    tv.setText(R.string.tooLong);
                    tv.setTextColor(getResources().getColor(R.color.yellow2));
                }
            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clicked){

                    imageView.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    editText.setTransformationMethod(null);//text

                    clicked=false;
                }
                else{
                    imageView.setImageResource(R.drawable.ic_visibility_black_24dp);
                    editText.setTransformationMethod(new PasswordTransformationMethod());//dots

                    clicked=true;
                }
            }
        });
    }
}