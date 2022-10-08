package com.andrewsapp.accstore2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private Button btn;
    private EditText editText;
    private ImageView imageView;
    private boolean clicked= false;
    private TextView textView;
    private TextView textView2;
    private ImageView fingerPrint;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Window w= getWindow();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.main));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
// clear FLAG_TRANSLUCENT_STATUS flag:
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
            w.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        }



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fingerPrint=(ImageView)findViewById(R.id.fingerPrint);
        editText=(EditText)findViewById(R.id.editLogin);
        btn=(Button)findViewById(R.id.logBtn);
        imageView=(ImageView)findViewById(R.id.EyeLog);
        textView=(TextView)findViewById(R.id.Wrong);
        textView2=(TextView)findViewById(R.id.forgotPass);
        linearLayout=(LinearLayout)findViewById(R.id.loginBackground);

        linearLayout.setBackgroundResource(R.drawable.background2);

        fingerPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    Intent intent= new Intent(Login.this,FingerprintActivity.class);
                    startActivity(intent);                }
                else{
                    Toast.makeText(getApplicationContext(),"This device does not have fingerprint sensor!",Toast.LENGTH_LONG).show();
                }
            }
        });


        /*-------------------------------------------------------------------------------------------------*/

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Login.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

        /*-------------------------------------------------------------------------------------------------*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass=editText.getText().toString();
                pass=pass.trim();
                try {
                    pass=AESUtils.encrypt(pass).trim();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final String PREFERENCE_FILE_KEY =getString(R.string.SharedPref).toString();
                Context context =getApplicationContext();
                SharedPreferences sharedPreferences=context.getSharedPreferences(PREFERENCE_FILE_KEY,Context.MODE_PRIVATE);


                if(pass.equals(sharedPreferences.getString("PASSWORD","null"))) {

                    textView.setVisibility(View.INVISIBLE);
                    Intent intent = new Intent(Login.this, Accounts.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });

        /*-------------------------------------------------------------------------------------------------*/

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(clicked){

                    imageView.setImageResource(R.drawable.ic_visibility_black_24dp);
                    editText.setTransformationMethod(new PasswordTransformationMethod());//dots

                    clicked=false;
                }
                else{
                    imageView.setImageResource(R.drawable.ic_visibility_off_black_24dp);
                    editText.setTransformationMethod(null);//text

                    clicked=true;
                }
            }
        });
    }
}