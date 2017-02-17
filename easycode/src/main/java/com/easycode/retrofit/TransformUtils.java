package com.easycode.retrofit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yzz on 2016/11/25.
 */

public class TransformUtils {

    public static <T> Observable.Transformer<T, T> defaultSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread());
            }
        };
    }

    public static <T> Observable.Transformer<T, T> all_io() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            }
        };
    }
}
