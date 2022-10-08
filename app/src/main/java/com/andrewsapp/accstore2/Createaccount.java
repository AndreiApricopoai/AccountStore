 package com.andrewsapp.accstore2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Createaccount extends AppCompatActivity implements Serializable {

    public ImageView close,selectIcon;
    public TextView save;
    public TextInputLayout til;
    public TextInputEditText tie,acc,user,pass,web,aditional;
    public PopupWindow popupWindow;
    public LinearLayout darkenGreen,darkenWhite;
    public byte[]icon,nul;
    public String color_light="",color_dark="";
    public Database database;
    public Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Window w= getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
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
        setContentView(R.layout.activity_createaccount);
        AsyncTaskExample asyncTask= new AsyncTaskExample();
        asyncTask.execute();

    }


    public void GETICON(View v){

        int id = v.getId();
        if (id == R.id.id1) {
            selectIcon.setBackgroundResource(R.drawable.netflix);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.netflix,this);
            color_light=String.valueOf(Color.parseColor("#99000d"));
            color_dark=String.valueOf(Color.parseColor("#000000"));
            Tools.IS_ICON_SELECTED=1;
            Setup();

        } else if (id == R.id.id2) {
            selectIcon.setBackgroundResource(R.drawable.facebook);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.facebook,this);
            color_light=String.valueOf(Color.parseColor("#1c4d89"));
            color_dark=String.valueOf(Color.parseColor("#163c6a"));
            Tools.IS_ICON_SELECTED=1;
            Setup();

        } else if (id == R.id.id3) {
            selectIcon.setBackgroundResource(R.drawable.instagram);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.instagram,this);
            color_light=String.valueOf(Color.parseColor("#f00075"));
            color_dark=String.valueOf(Color.parseColor("#b30056"));
            Tools.IS_ICON_SELECTED=1;
            Setup();

        } else if (id == R.id.id4) {
            selectIcon.setBackgroundResource(R.drawable.twitter);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.twitter,this);
            color_light=String.valueOf(Color.parseColor("#2ab8ea"));
            color_dark=String.valueOf(Color.parseColor("#159fd1"));
            Tools.IS_ICON_SELECTED=1;
            Setup();

        } else if (id == R.id.id5) {
            selectIcon.setBackgroundResource(R.drawable.spotify);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.spotify,this);
            color_light=String.valueOf(Color.parseColor("#23cf5f"));
            color_dark=String.valueOf(Color.parseColor("#1a9946"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id6) {
            selectIcon.setBackgroundResource(R.drawable.google);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.google,this);
            color_light=String.valueOf(Color.parseColor("#DB4437"));
            color_dark=String.valueOf(Color.parseColor("#c23124"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id7) {
            selectIcon.setBackgroundResource(R.drawable.steam);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.steam,this);
            color_light=String.valueOf(Color.parseColor("#000000"));
            color_dark=String.valueOf(Color.parseColor("#000000"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id8) {
            selectIcon.setBackgroundResource(R.drawable.tiktok);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.tiktok,this);
            color_light=String.valueOf(Color.parseColor("#5fe3ec"));
            color_dark=String.valueOf(Color.parseColor("#1bd7e4"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id9) {
            selectIcon.setBackgroundResource(R.drawable.patreon);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.patreon,this);
            color_light=String.valueOf(Color.parseColor("#fe5900"));
            color_dark=String.valueOf(Color.parseColor("#cc4700"));
            Tools.IS_ICON_SELECTED=1;
            Setup();


        } else if (id == R.id.id10) {
            selectIcon.setBackgroundResource(R.drawable.linkedin);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.linkedin,this);
            color_light=String.valueOf(Color.parseColor("#1686b0"));
            color_dark=String.valueOf(Color.parseColor("#116888"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id11) {
            selectIcon.setBackgroundResource(R.drawable.amazon);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.amazon,this);
            color_light=String.valueOf(Color.parseColor("#ff9900"));
            color_dark=String.valueOf(Color.parseColor("#cc7a00"));
            Tools.IS_ICON_SELECTED=1;
            Setup();

        } else if (id == R.id.id12) {
            selectIcon.setBackgroundResource(R.drawable.paypal);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.paypal,this);
            color_light=String.valueOf(Color.parseColor("#003087"));
            color_dark=String.valueOf(Color.parseColor("#002466"));
            Tools.IS_ICON_SELECTED=1;
            Setup();

        } else if (id == R.id.id13) {
            selectIcon.setBackgroundResource(R.drawable.watsapp);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.watsapp,this);
            color_light=String.valueOf(Color.parseColor("#25d366"));
            color_dark=String.valueOf(Color.parseColor("#1b9849"));
            Tools.IS_ICON_SELECTED=1;
            Setup();

        } else if (id == R.id.id14) {
            selectIcon.setBackgroundResource(R.drawable.apple);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.apple,this);
            color_light=String.valueOf(Color.parseColor("#4d4d4d"));
            color_dark=String.valueOf(Color.parseColor("#333333"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id15) {
            selectIcon.setBackgroundResource(R.drawable.windows);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.windows,this);
            color_light=String.valueOf(Color.parseColor("#0dabe8"));
            color_dark=String.valueOf(Color.parseColor("#0a7ca9"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        }
        else if (id == R.id.id16) {
            selectIcon.setBackgroundResource(R.drawable.telekom);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.telekom,this);
            color_light=String.valueOf(Color.parseColor("#e30073"));
            color_dark=String.valueOf(Color.parseColor("#b30059"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id17) {
            selectIcon.setBackgroundResource(R.drawable.mastercard);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.mastercard,this);
            color_light=String.valueOf(Color.parseColor("#e67300"));
            color_dark=String.valueOf(Color.parseColor("#b35900"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id18) {
            selectIcon.setBackgroundResource(R.drawable.visa);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.visa,this);
            color_light=String.valueOf(Color.parseColor("#1a1f71"));
            color_dark=String.valueOf(Color.parseColor("#131653"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id19) {
            selectIcon.setBackgroundResource(R.drawable.adobe);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.adobe,this);
            color_light=String.valueOf(Color.parseColor("#d1322a"));
            color_dark=String.valueOf(Color.parseColor("#95231d"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id20) {
            selectIcon.setBackgroundResource(R.drawable.oracle);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.oracle,this);
            color_light=String.valueOf(Color.parseColor("#9c1b32"));
            color_dark=String.valueOf(Color.parseColor("#82172b"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id21) {
            selectIcon.setBackgroundResource(R.drawable.youtube);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.youtube,this);
            color_light=String.valueOf(Color.parseColor("#cd201f"));
            color_dark=String.valueOf(Color.parseColor("#9b1717"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id22) {
            selectIcon.setBackgroundResource(R.drawable.twitch);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.twitch,this);
            color_light=String.valueOf(Color.parseColor("#6441a5"));
            color_dark=String.valueOf(Color.parseColor("#4e3380"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id23) {
            selectIcon.setBackgroundResource(R.drawable.discord);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.discord,this);
            color_light=String.valueOf(Color.parseColor("#8c9eff"));
            color_dark=String.valueOf(Color.parseColor("#667dff"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id24) {
            selectIcon.setBackgroundResource(R.drawable.snapchat);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.snapchat,this);
            color_light=String.valueOf(Color.parseColor("#e6e600"));
            color_dark=String.valueOf(Color.parseColor("#ccc900"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id25) {
            selectIcon.setBackgroundResource(R.drawable.tinder);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.tinder,this);
            color_light=String.valueOf(Color.parseColor("#ff5a62"));
            color_dark=String.valueOf(Color.parseColor("#ff4d55"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id26) {
            selectIcon.setBackgroundResource(R.drawable.yahoo);
            icon=Tools.convertFromResourcetoArrayByte(R.drawable.yahoo,this);
            color_light=String.valueOf(Color.parseColor("#410093"));
            color_dark=String.valueOf(Color.parseColor("#2e0066"));
            Tools.IS_ICON_SELECTED=1;
            Setup();
        } else if (id == R.id.id27) {
            selectIcon.setBackgroundResource(R.drawable.ic_baseline_color_lens_24);
            Tools.IS_ICON_SELECTED=0;
            Setup();
        }

    }


    public void requestFocus(View view){
        if(view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
        }

    public boolean validate_name(){
        if(tie.getText().toString().trim().isEmpty()){
            til.setError("This field can't be empty!");
            requestFocus(tie);
            return false;
        }
        return true;
    }

    public void Setup(){
        popupWindow.dismiss();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            darkenWhite.setForeground(new ColorDrawable(getResources().getColor(R.color.transparencynull)));
            darkenGreen.setForeground(new ColorDrawable(getResources().getColor(R.color.transparencynull)));
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.main3));

        }
    }



    @SuppressLint("StaticFieldLeak")
    private  class AsyncTaskExample extends AsyncTask<String, String, Integer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(String... strings) {

            database=new Database(context);
            close=(ImageView)((Activity)context).findViewById(R.id.close);
            save=(TextView) ((Activity)context).findViewById(R.id.saveItem);
            selectIcon=(ImageView) ((Activity)context).findViewById(R.id.selectIcon);
            til=(TextInputLayout)((Activity)context).findViewById(R.id.textinputlayoutID);
            tie=(TextInputEditText)((Activity)context).findViewById(R.id.textinputeditID);
            acc=(TextInputEditText)((Activity)context).findViewById(R.id.accountsid);
            user=(TextInputEditText)((Activity)context).findViewById(R.id.usernameid);
            pass=(TextInputEditText)((Activity)context).findViewById(R.id.passwordid);
            web=(TextInputEditText)((Activity)context).findViewById(R.id.websiteid);
            aditional=(TextInputEditText)((Activity)context).findViewById(R.id.aditionalinfoid) ;
            darkenGreen=(LinearLayout)((Activity)context).findViewById(R.id.DarkenColorGreen);
            darkenWhite=(LinearLayout)((Activity)context).findViewById(R.id.DarkenColorWhite);

            selectIcon.setBackgroundResource(R.drawable.ic_baseline_color_lens_24);

            TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}

                @Override
                public void afterTextChanged(Editable s) {
                    String editableString = s.toString().trim();
                    if(s.length()>0){
                        til.setError(null);
                    }
                }
            };
            tie.addTextChangedListener(watcher);



            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    finish();
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(validate_name()){

                        RowDB row=new RowDB();
                        Calendar cal = Calendar.getInstance();
                        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy");
                        String title=tie.getText().toString().trim();

                        if(Tools.IS_ICON_SELECTED==0){
                            int cul_light=Tools.generateRandomColor();
                            int cul_dark=Tools.light2dark(cul_light,0.8f);
                            color_light=String.valueOf(cul_light);
                            color_dark=String.valueOf(cul_dark);
                            icon= nul;
                        }
                        String account=acc.getText().toString().trim();

                        String username= null;
                        try {
                            username = AESUtils.encrypt(user.getText().toString().trim()).trim();
                        } catch (Exception e) {
                            username = "";
                            e.printStackTrace();
                        }

                        String password= null;
                        try {
                            password = AESUtils.encrypt(pass.getText().toString().trim()).trim();
                        } catch (Exception e) {
                            password = "";
                            e.printStackTrace();
                        }


                        String website=web.getText().toString().trim();
                        String aditionalinfo=aditional.getText().toString().trim();
                        String date=sdf.format(cal.getTime());

                        row.setTitle(title);
                        row.setAccount(account);
                        row.setUsername(username);
                        row.setPass(password);
                        row.setWebsite(website);
                        row.setAditionalinfo(aditionalinfo);
                        row.setDate(date);
                        row.setIcon(icon);
                        row.setColor_light(color_light);
                        row.setColor_dark(color_dark);

                        long id =database.insertRow(row);
                        Tools.IS_ICON_SELECTED=0;//se buguieste fiidnca ramane tot timpul 0

                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("result",String.valueOf(id));
                        setResult(Activity.RESULT_OK,returnIntent);
                        finish();
                    }

                }


            });

            selectIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // inflate the layout of the popup window
                    LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                    View popupView = inflater.inflate(R.layout.popup_window, null);
                    int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                    int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    boolean focusable = true; // lets taps outside the popup also dismiss it
                    popupWindow = new PopupWindow(popupView, width, height, true);
                    popupWindow.setAnimationStyle(R.style.popup_window_animation);
                    popupWindow.setOutsideTouchable(true);
                    popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                darkenWhite.setForeground(new ColorDrawable(getResources().getColor(R.color.transparencynull)));
                                darkenGreen.setForeground(new ColorDrawable(getResources().getColor(R.color.transparencynull)));
                                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.main3));

                            }                    }
                    });

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        darkenGreen.setForeground(new ColorDrawable(getResources().getColor(R.color.transparency)));
                        darkenWhite.setForeground(new ColorDrawable(getResources().getColor(R.color.transparency)));
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.bar));

                    }

                    ImageView c =(ImageView)popupView.findViewById(R.id.closepopupwindow);
                    c.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            popupWindow.dismiss();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                darkenGreen.setForeground(new ColorDrawable(getResources().getColor(R.color.transparencynull)));
                                darkenWhite.setForeground(new ColorDrawable(getResources().getColor(R.color.transparencynull)));
                                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.main3));
                            }
                        }
                    });
                }
            });

            return 1;
        }
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
        }
    }



}