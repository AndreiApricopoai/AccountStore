package com.andrewsapp.accstore2;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.widget.TextView;


/**
 * Created by whit3hawks on 11/16/16.
 */
@TargetApi(Build.VERSION_CODES.M)

public class FingerHandler extends FingerprintManager.AuthenticationCallback {


    private Context context;


    // Constructor
    public FingerHandler(Context mContext) {
        super();
        context = mContext;
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }


    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update(context.getResources().getString(R.string.fingerPrintAuthError) + "\n" + errString, 4);
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update(context.getResources().getString(R.string.fingerPrintAuthHelp) + "\n" + helpString, 3);
    }


    @Override
    public void onAuthenticationFailed() {
        this.update(context.getResources().getString(R.string.fingerPrintAuthFailed), 2);
    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update(context.getResources().getString(R.string.fingerPrintAuthSucceded), 1);
        Intent intent = new Intent((Activity) context, Accounts.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        ((Activity) context).finishAffinity();
    }


    public void update(String e, int number) {
        TextView textView = (TextView) ((Activity) context).findViewById(R.id.errorText);
        textView.setText(e);

        switch (number) {
            case 1:
                textView.setTextColor(ContextCompat.getColor(context, R.color.main3));
                break;
            case 2:
                textView.setTextColor(ContextCompat.getColor(context, R.color.colorFailed));
                break;
            case 3:
                textView.setTextColor(ContextCompat.getColor(context, R.color.colorHelp));
                break;
            case 4:
                textView.setTextColor(ContextCompat.getColor(context, R.color.colorError));
                break;
        }


    }
}
