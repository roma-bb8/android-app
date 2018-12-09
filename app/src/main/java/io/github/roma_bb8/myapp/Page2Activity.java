package io.github.roma_bb8.myapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Page2Activity extends AppCompatActivity {

    private InterstitialAd interstitialAd;

    private DialogHelper dialogHelper;

    //########################################

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        ((AdView) findViewById(R.id.activity_page2_adview)).loadAd(new AdRequest.Builder().build());

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));

        dialogHelper = new DialogHelper();
    }

    //########################################

    public void onClick1(View view) {

        dialogHelper.showInterstitialAd(interstitialAd);

        dialogHelper.showAlertDialog(
                this,
                getResources().getString(R.string.popup1_title),
                getResources().getString(R.string.popup1_text),
                getResources().getString(R.string.popup1_ok)
        );
    }

    public void onClick2(View view) {

        dialogHelper.showInterstitialAd(interstitialAd);

        dialogHelper.showAlertDialog(
                this,
                getResources().getString(R.string.popup2_title),
                getResources().getString(R.string.popup2_text),
                getResources().getString(R.string.popup2_ok)
        );
    }

    public void onClick3(View view) {

        dialogHelper.showInterstitialAd(interstitialAd);

        dialogHelper.showAlertDialog(
                this,
                getResources().getString(R.string.popup3_title),
                getResources().getString(R.string.popup3_text),
                getResources().getString(R.string.popup3_ok)
        );
    }
}
