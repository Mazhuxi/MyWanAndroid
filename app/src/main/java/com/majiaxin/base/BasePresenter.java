package com.majiaxin.base;

import java.lang.ref.WeakReference;

public class BasePresenter<V> {

    private WeakReference<V> mVWeakReference;
    protected V mView;

    public void attachView(V v) {
        mVWeakReference = new WeakReference<V>(v);
        mView = mVWeakReference.get();
    }

    public void deachView() {
        if (mVWeakReference != null){
            mVWeakReference.clear();
        }
    }
}
