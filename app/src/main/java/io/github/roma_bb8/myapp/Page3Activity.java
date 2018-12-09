package io.github.roma_bb8.myapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Page3Activity extends AppCompatActivity {

    private InterstitialAd interstitialAd;

    private DialogHelper dialogHelper;

    //########################################

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        ((AdView) findViewById(R.id.activity_page3_adview)).loadAd(new AdRequest.Builder().build());

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));

        dialogHelper = new DialogHelper();
    }

    //########################################

    public void onClick1(View view) {

        dialogHelper.showInterstitialAd(interstitialAd);

        dialogHelper.showAlertDialog(
                this,
                getResources().getString(R.string.popup4_title),
                getResources().getString(R.string.popup4_text),
                getResources().getString(R.string.popup4_ok)
        );
    }

    public void onClick2(View view) {

        dialogHelper.showInterstitialAd(interstitialAd);

        dialogHelper.showAlertDialog(
                this,
                getResources().getString(R.string.popup5_title),
                getResources().getString(R.string.popup5_text),
                getResources().getString(R.string.popup5_ok)
        );
    }

    public void onClick3(View view) {

        dialogHelper.showInterstitialAd(interstitialAd);

        startActivity(new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(getResources().getString(R.string.link2))
        ));
    }
}
