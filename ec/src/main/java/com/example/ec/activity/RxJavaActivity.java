package com.example.ec.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.easycode.util.L;
import com.example.ec.R;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;

public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();
            }

        });


        Observer<Integer> observer = new Observer<Integer>() {

            @Override
            public void onSubscribe(Disposable d) {
                L.d("onSubscribe", "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                L.d("onNext", value.toString());
            }

            @Override
            public void onError(Throwable e) {
                L.d("onError", "onError");
            }

            @Override
            public void onComplete() {
                L.d("onComplete", "onComplete");
            }
        };
        observable.subscribe(observer);

    }
}
