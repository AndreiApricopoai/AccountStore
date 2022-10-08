package com.andrewsapp.accstore2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    public LinearLayout policy;
    public ImageView go_back;
    public SwitchCompat swich;
    public Button change_pass;
    public Button delete_db;
    public Button reset_app;
    public Database database;

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
            w.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.main3));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        policy=(LinearLayout)this.findViewById(R.id.privacypolicy);
        go_back=(ImageView)this.findViewById(R.id.settings_back_arrow);
        swich=(SwitchCompat) this.findViewById(R.id.switch_btn);
        change_pass=(Button)this.findViewById(R.id.settings_change);
        delete_db=(Button)this.findViewById(R.id.settings_delete);
        reset_app=(Button)this.findViewById(R.id.settings_reset);
        database=new Database(this);

        Tools.DELETED=0;

        final String PREFERENCE_FILE_KEY =getString(R.string.SharedPref).toString();
        Context context =getApplicationContext();
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREFERENCE_FILE_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String condition=sharedPreferences.getString("LOGINCONDITION","NotFound").toString();
        if(condition.equals("NotFound")){
            editor.putString("LOGINCONDITION","Login");
            editor.apply();
            swich.setChecked(false);
        }
        else swich.setChecked(!condition.equals("Login"));




        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater factory = LayoutInflater.from(Settings.this);
                final View textEntryView = factory.inflate(R.layout.settings_custom_alert_dialog, null);
                final EditText input1 = (EditText) textEntryView.findViewById(R.id.change_pass_first_edittext);
                final EditText input2 = (EditText) textEntryView.findViewById(R.id.change_pass_second_edittext);


                String txt=getString(R.string.warning3);
                AlertDialog.Builder builder=new AlertDialog.Builder(Settings.this);
                builder.setCancelable(true);
                builder.setTitle( Html.fromHtml("<font color='#333333'>"+txt+"</font>"));
                builder.setView(textEntryView);

                builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final String PREFERENCE_FILE_KEY =getString(R.string.SharedPref).toString();
                        Context context =getApplicationContext();
                        SharedPreferences sharedPreferences=context.getSharedPreferences(PREFERENCE_FILE_KEY,Context.MODE_PRIVATE);

                        String current_pass=sharedPreferences.getString("PASSWORD","NotFound").toString().trim();
                        String old_pass= null;
                        try {
                            old_pass = AESUtils.encrypt(input1.getText().toString().trim()).trim();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String new_pass= null;
                        try {
                            new_pass = AESUtils.encrypt(input2.getText().toString().trim()).trim();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if(old_pass.equals(current_pass)){

                            if(new_pass.length()<5){

                                Toast.makeText(getApplicationContext(),"New password is too short!",Toast.LENGTH_LONG).show();

                            }else if(new_pass.length()>40){
                                Toast.makeText(getApplicationContext(),"New password is too long!",Toast.LENGTH_LONG).show();

                            }else{
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("PASSWORD",new_pass);
                                editor.apply();
                                Toast.makeText(getApplicationContext(),"Password changed successfully!",Toast.LENGTH_LONG).show();
                            }

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Old password incorrect!",Toast.LENGTH_LONG).show();
                        }

                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog=builder.create();
                alertDialog.show();
                alertDialog.getWindow().setBackgroundDrawableResource(R.color.dialogBackground);
                alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#00b36b"));
                alertDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#000000"));

            }
        });



        swich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                final String PREFERENCE_FILE_KEY =getString(R.string.SharedPref).toString();
                Context context =getApplicationContext();
                SharedPreferences sharedPreferences=context.getSharedPreferences(PREFERENCE_FILE_KEY,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();

                if(isChecked){
                    String condition=sharedPreferences.getString("LOGINCONDITION","NotFound").toString();
                    if(condition.equals("NotFound")||condition.equals("Login")){
                        editor.putString("LOGINCONDITION","NoLogin");
                        editor.apply();
                    }


                }else{
                    String condition=sharedPreferences.getString("LOGINCONDITION","NotFound").toString();
                    if(condition.equals("NotFound")||condition.equals("NoLogin")){
                        editor.putString("LOGINCONDITION","Login");
                        editor.apply();
                    }
                }
            }
        });



        delete_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt=getString(R.string.warning2);
                AlertDialog.Builder builder=new AlertDialog.Builder(Settings.this);
                builder.setCancelable(true);
                builder.setTitle( Html.fromHtml("<font color='#333333'>"+txt+"</font>"));


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //reset database rows
                        database.delete_all_records();
                        Toast.makeText(getApplicationContext(),"Database Records Deleted!",Toast.LENGTH_LONG).show();
                        Tools.DELETED=1;
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





        reset_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt=getString(R.string.warning);
                AlertDialog.Builder builder=new AlertDialog.Builder(Settings.this);
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
                            Intent intent=new Intent(Settings.this,MainActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        }
                        else{
                            Intent intent=new Intent(Settings.this,MainActivity.class);
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




        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Tools.DELETED==1) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("response", "database_deleted");
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                else
                    finish();

            }
        });

        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String s ="https://sites.google.com/view/andrews-app-accstore/pagina-de-pornire";// site-ul meu cu linkul la privacy policy
                    s=s.toLowerCase();

                    Uri uri = Uri.parse(s); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "URL not valid!", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
    @Override
    public void onBackPressed() {
        if(Tools.DELETED==1) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("response", "database_deleted");
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
        else
            super.onBackPressed();
    }
}