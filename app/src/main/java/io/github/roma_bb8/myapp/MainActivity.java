package io.github.roma_bb8.myapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements
        BaseSliderView.OnSliderClickListener,
        ViewPagerEx.OnPageChangeListener {

    private InterstitialAd interstitialAd;

    private SliderLayout sliderLayout;

    private DialogHelper dialogHelper;

    //########################################

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((AdView) findViewById(R.id.activity_main_adview)).loadAd(new AdRequest.Builder().build());
        sliderLayout = findViewById(R.id.activity_main_slider);

        HashMap<String, Integer> hashMap = new HashMap<>();

        hashMap.put(getResources().getString(R.string.main_slider2), R.drawable.background_2);
        hashMap.put(getResources().getString(R.string.main_slider1), R.drawable.background_1);
        hashMap.put(getResources().getString(R.string.main_slider3), R.drawable.background_3);
        hashMap.put(getResources().getString(R.string.main_slider4), R.drawable.background_4);

        for (String name : hashMap.keySet()) {

            TextSliderView textSliderView = new TextSliderView(this);

            textSliderView
                    .description(name)
                    .image(hashMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this)
                    .bundle(new Bundle())
                    .getBundle()
                    .putString("extra", name);

            sliderLayout.addSlider(textSliderView);
        }

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Stack);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.addOnPageChangeListener(this);
        sliderLayout.setDuration(8000);

        MobileAds.initialize(this, getResources().getString(R.string.app_ads_code));

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));

        dialogHelper = new DialogHelper();
    }

    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }

    //########################################

    @Override
    public void onSliderClick(BaseSliderView slider) {

        String message = (String) slider.getBundle().get("extra");

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //########################################

    public void onClick1(View view) {

        dialogHelper.showInterstitialAd(interstitialAd);

        startActivity(new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(getResources().getString(R.string.link1))
        ));
    }

    public void onClick2(View view) {

        dialogHelper.showInterstitialAd(interstitialAd);

        startActivity(new Intent(
                MainActivity.this,
                Page2Activity.class
        ));
    }

    public void onClick3(View view) {

        dialogHelper.showInterstitialAd(interstitialAd);

        startActivity(new Intent(
                MainActivity.this,
                Page3Activity.class
        ));
    }
}
