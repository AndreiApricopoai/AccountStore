package com.andrewsapp.accstore2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.ClipboardManager;

public class EntryView extends AppCompatActivity {

    public ImageView arrowback,edit,del,bigImage,seePas;
    public TextView accCopy,userCopy,passCopy,webOpen,website,account,username,ai,password,aditional,title;
    public LinearLayout fieldHolder,linearLayout,a,b,c,d,e;
    Database database;
    RowDB row;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        intent = getIntent();
        row = (RowDB) intent.getSerializableExtra("row");
        database = new Database(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_view);


        try {
            row.setUsername(AESUtils.decrypt(row.getUsername()).trim());
        } catch (Exception exception) {
            row.setUsername("");
            exception.printStackTrace();
        }


        try {
            row.setPass(AESUtils.decrypt(row.getPass()).trim());
        } catch (Exception exception) {
            row.setPass("");
            exception.printStackTrace();
        }

        linearLayout = (LinearLayout) this.findViewById(R.id.mainPanel);
        bigImage = (ImageView) this.findViewById(R.id.BigIcon);
        arrowback = (ImageView) this.findViewById(R.id.entryArrowBack);
        edit = (ImageView) this.findViewById(R.id.entryEdit);
        del = (ImageView) this.findViewById(R.id.entryDelete);

        ai = (TextView) this.findViewById(R.id.aditionalColor);
        accCopy = (TextView) this.findViewById(R.id.AccCopy);
        userCopy = (TextView) this.findViewById(R.id.UserCopy);
        passCopy = (TextView) this.findViewById(R.id.PassCopy);
        webOpen = (TextView) this.findViewById(R.id.WebOpen);
        seePas = (ImageView) this.findViewById(R.id.PassSee);

        title = (TextView) this.findViewById(R.id.viewTitle);
        website = (TextView) this.findViewById(R.id.website);
        account = (TextView) this.findViewById(R.id.account);
        username = (TextView) this.findViewById(R.id.username);
        password = (TextView) this.findViewById(R.id.password);
        aditional = (TextView) this.findViewById(R.id.aditional);

        a = (LinearLayout) this.findViewById(R.id.accountLay);
        b = (LinearLayout) this.findViewById(R.id.usernameLay);
        c = (LinearLayout) this.findViewById(R.id.passLay);
        d = (LinearLayout) this.findViewById(R.id.webLay);
        e = (LinearLayout) this.findViewById(R.id.addLay);


        ///
        seePas.setBackgroundResource(R.drawable.ic_visibility_black_24dp);
        title.setText(row.getTitle());
        account.setText(row.getAccount());
        username.setText(row.getUsername());
        password.setText(transform(row.getPass()));
        website.setText(row.getWebsite());
        aditional.setText(row.getAditionalinfo());


        ///


        if (row.getIcon() == null) {
            int culoare3 = Integer.parseInt(row.getColor_light());
            int culoare4 = Integer.parseInt(row.getColor_dark());
            Drawable newDr = Tools.gmailIcon(row.getTitle(), culoare3);
            bigImage.setImageDrawable(newDr);
            linearLayout.setBackgroundColor(culoare3);
            Tools.setColorsBar(getWindow(), getApplicationContext(), culoare4);
            accCopy.setTextColor(culoare3);
            userCopy.setTextColor(culoare3);
            passCopy.setTextColor(culoare3);
            webOpen.setTextColor(culoare4);
            ai.setTextColor(culoare3);

            if (account.getText().toString().equals("")) {
                a.setVisibility(View.GONE);
            }
            if (username.getText().toString().equals("")) {
                b.setVisibility(View.GONE);
            }
            if (password.getText().toString().equals("")) {
                c.setVisibility(View.GONE);
            }
            if (website.getText().toString().equals("")) {
                d.setVisibility(View.GONE);
            }
            if (aditional.getText().toString().equals("")) {
                e.setVisibility(View.GONE);
            }

        } else {
            int culoare1 = Integer.parseInt(row.getColor_light());
            int culoare2 = Integer.parseInt(row.getColor_dark());
            Drawable dr = Tools.byte2Drawable(row.getIcon(), this);
            bigImage.setImageDrawable(dr);
            linearLayout.setBackgroundColor(culoare1);
            Tools.setColorsBar(getWindow(), getApplicationContext(), culoare2);
            accCopy.setTextColor(culoare1);
            userCopy.setTextColor(culoare1);
            passCopy.setTextColor(culoare1);
            webOpen.setTextColor(culoare2);
            ai.setTextColor(culoare1);

            if (account.getText().toString().equals("")) {
                a.setVisibility(View.GONE);
            }
            if (username.getText().toString().equals("")) {
                b.setVisibility(View.GONE);
            }
            if (password.getText().toString().equals("")) {
                c.setVisibility(View.GONE);
            }
            if (website.getText().toString().equals("")) {
                d.setVisibility(View.GONE);
            }
            if (aditional.getText().toString().equals("")) {
                e.setVisibility(View.GONE);
            }
        }


        fieldHolder = (LinearLayout) this.findViewById(R.id.fieldHolder);


        arrowback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(EntryView.this, Edit.class);
                intent.putExtra("editrow", row);
                startActivity(intent);


            }
        });
int a;
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String text="Delete item '"+row.getTitle()+"' ?";
                String html="<font color='#333333'>"+text+"</font>";
                AlertDialog.Builder builder=new AlertDialog.Builder(EntryView.this);
                builder.setCancelable(true);
                builder.setTitle( Html.fromHtml(html));


                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                        database.deleteRow(row);
                        //   Intent resultintent = new Intent();
                        intent.putExtra("key", String.valueOf(row.getId()));
                        setResult(Activity.RESULT_OK, intent);
                        finish();
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
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Integer.parseInt(row.getColor_light()));
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Integer.parseInt(row.getColor_dark()));


            }
        });


        accCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clip(account);
            }
        });


        userCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clip(username);
            }
        });


        passCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String clipText = row.getPass().toString().trim();
                    ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("label", clipText);
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });


        webOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    String s = website.getText().toString().trim();
                    s=s.toLowerCase();

                    if(s.contains("https://") && !s.contains("www.")){

                        s="https://www."+s.substring(8);
                    }
                    else if(!s.contains("https://") && s.contains("www.")){
                        s="https://"+s;
                    }
                    else
                    {s = "https://www." + s;}

                    Uri uri = Uri.parse(s); // missing 'http://' will cause crashed
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "URL not valid!", Toast.LENGTH_LONG).show();
                }

            }
        });


        seePas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Tools.TOGGLE_EYE == 1) {
                    password.setText(transform(row.getPass()));
                    Tools.TOGGLE_EYE = 0;
                    seePas.setBackgroundResource(R.drawable.ic_visibility_black_24dp);
                } else {
                    password.setText(row.getPass());
                    Tools.TOGGLE_EYE = 1;
                    seePas.setBackgroundResource(R.drawable.ic_visibility_off_black_24dp);


                }

            }
        });
    }


    private void doNothing() {
    }


    public void Clip(TextView t) {
        try {
            String clipText = t.getText().toString().trim();
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", clipText);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public String transform(String s) {
        String toggle = "";
        for (int i = 1; i <= s.length(); i++) {
            toggle += "*";
        }
        return toggle;
    }

}