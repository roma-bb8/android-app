package io.github.roma_bb8.myapp;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

class DialogHelper {

    void showInterstitialAd(InterstitialAd interstitialAd) {

        interstitialAd.loadAd(new AdRequest.Builder().build());

        new Handler().postDelayed(new Runnable() {
            public void run() {
                // yourMethod();
            }
        }, 2000);

        if (interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
    }

    //########################################

    void showAlertDialog(Context context, String title, String Message, String Confirm) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);

        builder.setPositiveButton(Confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }
}
