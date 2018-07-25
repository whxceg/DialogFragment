package com.sam.lib.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public abstract class XDialogFragment extends DialogFragment {

    private int mAnimationResId;
    private int mGravity = Gravity.CENTER;
    private float mDimAmount = 0.6f;
    private int mWidth = WindowManager.LayoutParams.WRAP_CONTENT;
    private int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;
    private boolean mCanceledOnTouch = true;
    private boolean mCancelAble = true;

    public abstract int getLayoutResId();

    public void showDialog(FragmentManager fragmentManager) {
        if (!isAdded() && fragmentManager.findFragmentByTag(getClass().getSimpleName()) == null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(this, getClass().getSimpleName());
            ft.commitAllowingStateLoss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(isCanceledOnTouch());
            dialog.setCancelable(isCancelAble());
            Window window = dialog.getWindow();
            if (window != null) {
                window.requestFeature(Window.FEATURE_NO_TITLE);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                window.getDecorView().setPadding(0, 0, 0, 0);
                window.setLayout(getWidth(), getHeight());
                window.setDimAmount(getDimAmount());
                window.setGravity(getGravity());
                window.setWindowAnimations(getWindowAnimations());
            }
        }
        return inflater.inflate(getLayoutResId(), container, false);
    }

    public int getWindowAnimations() {
        return mAnimationResId;
    }

    public XDialogFragment setWindowAnimations(int resId) {
        this.mAnimationResId = resId;
        return this;
    }

    public int getGravity() {
        return mGravity;
    }

    public XDialogFragment setGravity(int mGravity) {
        this.mGravity = mGravity;
        return this;
    }

    public float getDimAmount() {
        return mDimAmount;
    }

    public XDialogFragment setDimAmount(float mDimAmount) {
        this.mDimAmount = mDimAmount;
        return this;
    }

    public int getWidth() {
        return mWidth;
    }

    public XDialogFragment setWidth(int mWidth) {
        this.mWidth = mWidth;
        return this;
    }

    public XDialogFragment setBottom() {
        setGravity(Gravity.BOTTOM);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setWindowAnimations(R.style.x_dialog_fragment_anim_bottom_in_out);
        return this;
    }

    public int getHeight() {
        return mHeight;
    }

    public XDialogFragment setHeight(int mHeight) {
        this.mHeight = mHeight;
        return this;
    }

    public boolean isCanceledOnTouch() {
        return mCanceledOnTouch;
    }

    public XDialogFragment setCanceledOnTouch(boolean mCanceledOnTouch) {
        this.mCanceledOnTouch = mCanceledOnTouch;
        return this;
    }

    public XDialogFragment setCancelAble(boolean cancelable) {
        mCancelAble = cancelable;
        setCancelable(cancelable);
        return this;
    }

    public boolean isCancelAble() {
        return mCancelAble;
    }
}
