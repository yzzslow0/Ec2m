package com.example.ec.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.easycode.util.L;
import com.example.ec.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

//import rx.Observable;
//import rx.Observer;
//import rx.Subscriber;

public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onNext(4);
//                e.onComplete();
//            }
//
//        });
//
//
//        Observer<Integer> observer = new Observer<Integer>() {
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                L.d("onSubscribe", "subscribe");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                L.d("onNext", value.toString());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                L.d("onError", "onError");
//            }
//
//            @Override
//            public void onComplete() {
//                L.d("onComplete", "onComplete");
//            }
//        };
//        observable.subscribe(observer);


//        Observable.create(new ObservableOnSubscribe<String>() {
//
//            @Override
//            public void subscribe(ObservableEmitter<String> e) throws Exception {
//                Log.v("rx_call", Thread.currentThread().getName());
//                L.v("subscribe");
//                e.onNext("dd");
//                e.onComplete();
//            }
//        }).subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Function<String, String>() {
//                    @Override
//                    public String apply(String s) throws Exception {
//                        Log.v("rx_map", Thread.currentThread().getName());
//                        L.v(s + "----dd");
//                        return s + "88";
//                    }
//                }).subscribe(new Consumer<String>() {
//
//            @Override
//            public void accept(String s) throws Exception {
//                Log.v("rx_subscribe", Thread.currentThread().getName());
//                L.v(s + "----dd2");
//            }
//        });

//        Flowable.create(new FlowableOnSubscribe<String>() {
//
//            @Override
//            public void subscribe(FlowableEmitter<String> e) throws Exception {
//                e.onNext("exception:" + "123");
//                e.onComplete();
//            }
//        }, BackpressureStrategy.BUFFER)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                        Log.i("onSubscribe", Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        Log.i("onNext", Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        Log.i("onError", Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.i("onComplete", Thread.currentThread().getName());
//                    }
//                });
//
//
//    }

        //创建一个上游 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建一个下游 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("onSubscribe", "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d("onSubscribe", "" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("onSubscribe", "error");
            }

            @Override
            public void onComplete() {
                Log.d("onSubscribe", "complete");
            }
        };
        //建立连接
        observable.subscribe(observer);


    }
}
//                .map(new Func1<String, String >() {
//                    @Override
//                    public String call(String s) {
//                        Logger.v( "rx_map" , Thread.currentThread().getName()  );
//                        return s + "88";
//                    }
//                })
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        Logger.v( "rx_subscribe" , Thread.currentThread().getName()  );
//                    }
//                }
//                ) ;
