package com.javaxian.cleanarchitecture.steppercomponent.domain.base;

import android.app.Activity;
import android.content.Context;

public class BasePresenter<T> {

    private T view;
    private Context context;
    private Activity activity;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void initialize() {

    }


    public interface View {

    }
}
