package com.example.aacapplication.ui.presenter;

import com.example.aacapplication.ui.view.BaseView;

public class BasePresenter<T extends BaseView> {

    private T mView;

    public BasePresenter(T view) {
        mView = view;
    }

    public T getView() {
        return mView;
    }
}