package com.andrewsapp.accstore2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Locale;

public class AccountsAdapter extends ArrayAdapter<RowDB> {

    public Context mContext;
    public ArrayList<RowDB> mObjects;
    public ArrayList<RowDB> arrayList;

    public AccountsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RowDB> objects) {
        super(context, resource, objects);
        mContext=context;
        mObjects=objects;
        arrayList=new ArrayList<RowDB>();
        arrayList.addAll(mObjects);
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public RowDB getItem(int i) {
        return mObjects.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public  void update(){
        arrayList.clear();
        arrayList.addAll(mObjects);
    }



    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View widget=convertView;

        if(widget==null){

            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            widget = inflater.inflate(R.layout.list_row, parent, false);

        }


        TextView mTitle, mDate;
        ImageView mIcon;

        mTitle = widget.findViewById(R.id.rowTitle);
        mDate = widget.findViewById(R.id.rowDate);
        mIcon = widget.findViewById(R.id.rowIcon);

        if(mObjects.get(position).getTitle().length()>50){
            String text_to_show=mObjects.get(position).getTitle();
            text_to_show=text_to_show.substring(0,50);
            text_to_show=text_to_show.trim();
            text_to_show=text_to_show+" ...";
            mTitle.setText(text_to_show);


        }else{
            mTitle.setText(mObjects.get(position).getTitle());
        }

        mDate.setText(mObjects.get(position).getDate());
        if(mObjects.get(position).getIcon()==null) {
            Drawable drawable = Tools.gmailIcon(mObjects.get(position).getTitle(), Integer.parseInt(mObjects.get(position).getColor_light()));
            mIcon.setImageDrawable(drawable);
        }else{
            Drawable drawable = Tools.byte2Drawable(mObjects.get(position).getIcon(), mContext);
            mIcon.setImageDrawable(drawable);
        }

        widget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent=new Intent(mContext,EntryView.class);
              // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.putExtra("row",getItem(position));
                //mContext.getApplicationContext().startActivity(intent);
                ((Activity) mContext).startActivityForResult(intent, 2);
            }
        });

        return widget;


    }
    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        mObjects.clear();
        if (charText.length()==0){
            mObjects.addAll(arrayList);
        }
        else {
            for (RowDB model : arrayList){
                if (model.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    mObjects.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }


}
