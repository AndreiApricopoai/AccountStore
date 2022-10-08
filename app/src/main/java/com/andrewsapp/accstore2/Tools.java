package com.andrewsapp.accstore2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import java.io.ByteArrayOutputStream;

public class Tools {

    public static int IS_ICON_SELECTED=0;
    public static int TOGGLE_EYE=0;
    public static int DELETED=0;

    public static byte[] convertFromResourcetoArrayByte(int resource, Context context){
        Drawable drawable =context.getResources().getDrawable(resource);
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


    public static Drawable byte2Drawable(byte[] bytes,Context context){
        return new BitmapDrawable(context.getResources(), BitmapFactory.decodeByteArray(bytes,0,bytes.length));
    }


    public static int light2dark(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }


    public static Drawable gmailIcon(String s,int color ){
        s=s.substring(0,1);
        return TextDrawable.builder().buildRound(s,color);
    }


    public static int generateRandomColor(){
        ColorGenerator colorGenerator=ColorGenerator.MATERIAL;
        return colorGenerator.getRandomColor();
    }

    public static  void setColorsBar(Window w,Context c,int color ){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.setNavigationBarColor(ContextCompat.getColor(c, R.color.black));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
// clear FLAG_TRANSLUCENT_STATUS flag:
            w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
            w.setStatusBarColor(ContextCompat.getColor(c, R.color.oke));
            w.setStatusBarColor(color);
        }


    }

}
