package com.example.ec.anim;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.widget.Button;
import android.widget.SeekBar;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.ec.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimActivity extends AppCompatActivity {

    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    LottieDrawable drawable;
    @BindView(R.id.progressBar)
    AppCompatSeekBar progressBar;
    @BindView(R.id.button12)
    Button button12;
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
        animationView.setAnimation("fourball.json");
        animationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progressBar.setProgress((int) (animation.getAnimatedFraction() * 100));
            }
        });

        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (!animationView.isAnimating()) {
                    animationView.setProgress(progress / 100f);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                animationView.pauseAnimation();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
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

    @OnClick(R.id.button12)
    public void onClick() {
        if (!animationView.isAnimating()){
            animationView.loop(true);
            animationView.playAnimation();
        }

    }
}
