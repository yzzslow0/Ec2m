package com.example.ec.anim;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.ec.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimActivity extends AppCompatActivity {

    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    LottieDrawable drawable;
    @BindView(R.id.progressBar)
    AppCompatSeekBar progressBar;
    private boolean isplay = false;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        ButterKnife.bind(this);

        lottieTest();
    }

    private void lottieTest() {
        animationView.setAnimation("data.json");
        animationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((int) (animation.getAnimatedFraction() * 100));
            }
        });

        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!animationView.isAnimating()) {
                    animationView.setProgress(progress / 100f);
                }
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
//        animationView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                animationView.playAnimation();
//                timer.start();
//
////                if(isplay){
////                    animationView.setAnimation("data2.json");
////                    animationView.playAnimation();
////                    isplay = false;
////                }else{
////                    animationView.setAnimation("data.json");
////
////                    animationView.playAnimation();
////                    isplay=true;
////                }
//
//            }
//        });

    }
}
