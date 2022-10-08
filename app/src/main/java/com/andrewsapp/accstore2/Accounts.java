package com.andrewsapp.accstore2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Iterator;

public class Accounts extends AppCompatActivity {

    public ListView list;
    public AccountsAdapter adapter;
    public ArrayList<RowDB> rows= new ArrayList<RowDB>();
    public Database database;
    public TextView noitemsyet;
    public androidx.appcompat.widget.SearchView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Window w= getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.setNavigationBarColor(ContextCompat.getColor(getApplicationContext(), R.color.black)); }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
// clear FLAG_TRANSLUCENT_STATUS flag:
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
            w.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.main3)); }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        //----------
        final ImageView exit=(ImageView)this.findViewById(R.id.exitApp);
        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.fab);
        final ImageView settings=(ImageView)this.findViewById(R.id.settings);
         sv=findViewById(R.id.searchView);
        final TextView all=(TextView)this.findViewById(R.id.all);
         noitemsyet=(TextView)this.findViewById(R.id.noitemsyet);

         showRememberPasswordWarning();

        ImageView searchIcon = sv.findViewById(androidx.appcompat.R.id.search_button);
        searchIcon.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_baseline_search_24));
        sv.setQueryHint("Search...");

        database=new Database(this);
        rows=database.getAllRows();
        list= (ListView)this.findViewById(R.id.list);
        adapter=new AccountsAdapter(this,R.layout.list_row,rows);

        list.setAdapter(adapter);// set info and layout to rows
        list.setTextFilterEnabled(true);// enable the list to be filtered by searchview
        list.addFooterView(new View(getApplicationContext()), null, true);//add divider at the end of listview
        list.setCacheColorHint(Color.TRANSPARENT); // not sure if this is required for you.
        list.setFastScrollEnabled(true);
        list.setScrollingCacheEnabled(false);

       toggleEmptyNotes();

        //----methods
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Accounts.this, Createaccount.class);
                startActivityForResult(intent, 1);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Accounts.this, Login.class);
                startActivity(intent);//go back to login
                moveTaskToBack(true);//go to home screen
            }
        });

// on close and on click achieve accounts appear and dissapear of views
sv.setOnSearchClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        settings.setVisibility(View.GONE);
        all.setVisibility(View.GONE);
        exit.setVisibility(View.GONE);
        sv.setVisibility(View.VISIBLE);
    }
});

     sv.setOnCloseListener(new SearchView.OnCloseListener() {
         @Override
         public boolean onClose() {

             settings.setVisibility(View.VISIBLE);
             all.setVisibility(View.VISIBLE);
             exit.setVisibility(View.VISIBLE);
             return false;
         }
     });

     // searchview method implementations
     sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
         @Override
         public boolean onQueryTextSubmit(String query) {
             return false;
         }

         @Override
         public boolean onQueryTextChange(String newText) {

             if (TextUtils.isEmpty(newText)){
                 adapter.filter("");// filter method implemented in adapter
                 list.clearTextFilter();
             }
             else {
                 adapter.filter(newText);
             }
             return true;
         }
     });


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RotateAnimation rotate = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(350);
                rotate.setInterpolator(new LinearInterpolator());
                rotate.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {

                        Intent intent= new Intent(Accounts.this, Settings.class);
                        startActivityForResult(intent, 10);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                settings.startAnimation(rotate);
            }
        });
    }


    private void showRememberPasswordWarning() {

        final String PREFERENCE_FILE_KEY =getString(R.string.SharedPref).toString();
        Context context =getApplicationContext();
        SharedPreferences sharedPreferences=context.getSharedPreferences(PREFERENCE_FILE_KEY,Context.MODE_PRIVATE);
     //   SharedPreferences.Editor editor=sharedPreferences.edit();
      //  editor.putString("PASSWORD",Tools.codeString(password));
       // editor.apply();
        if(sharedPreferences.getString("WARNING","null").equals("YES")||sharedPreferences.getString("WARNING","null").equals("null")) {


            LayoutInflater factory = LayoutInflater.from(Accounts.this);
            final View textEntryView = factory.inflate(R.layout.warning_remember_password, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(Accounts.this);
            builder.setCancelable(true);
            builder.setView(textEntryView);
            builder.setPositiveButton("I UNDERSTAND", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.cancel();

                }
            });


            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            alertDialog.getWindow().setBackgroundDrawableResource(R.color.dialogBackground);
            alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#b30000"));

            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("WARNING","NO");
            editor.apply();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                RowDB r = database.getRow(Integer.parseInt(result));

                if (r != null) {
                    // adding new note to array list at n position
                    rows.add(rows.size(), r);
                    // refreshing the list
                    adapter.notifyDataSetChanged();
                    adapter.update();
                    toggleEmptyNotes();
            }}

        }

        if(requestCode==2 && resultCode==Activity.RESULT_OK){
            String res=data.getStringExtra("key");
            adapter.filter("");
            sv.setIconified(true);

            for (Iterator<RowDB> iter = rows.iterator(); iter.hasNext(); ) {
                RowDB element = iter.next();
                if(element.getId()==Integer.parseInt(res)){
                    iter.remove();
                }
                adapter.notifyDataSetChanged();
                adapter.update();
                toggleEmptyNotes();
            }
        }

        if(requestCode==10 && resultCode==Activity.RESULT_OK){
            String response= data.getStringExtra("response").toString();
           if(response.equals("database_deleted")){
               rows.clear();
               adapter.notifyDataSetChanged();
               adapter.update();
               toggleEmptyNotes();

           }
        }

    }

    void toggleEmptyNotes() {
        // you can check notesList.size() > 0
        if(!(rows.size()>0))
            noitemsyet.setVisibility(View.GONE);
        else
            noitemsyet.setVisibility(View.VISIBLE);

        if (database.rowCount() > 0) {
            noitemsyet.setVisibility(View.GONE);
        } else {
            noitemsyet.setVisibility(View.VISIBLE);
        }}
}