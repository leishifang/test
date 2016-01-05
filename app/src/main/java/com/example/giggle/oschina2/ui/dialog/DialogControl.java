package com.example.giggle.oschina2.ui.dialog;

import android.app.ProgressDialog;

/**
 * Created by Administrator on 2015-10-27.
 */
public interface DialogControl {
    public abstract void hideWaitDialog();

    public abstract ProgressDialog showWaitDialog();

    public abstract ProgressDialog showWaitDialog(int resid);

    public abstract ProgressDialog showWaitDialog(String text);
}
