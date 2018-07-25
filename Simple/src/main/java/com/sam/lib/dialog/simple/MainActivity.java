package com.sam.lib.dialog.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.sam.lib.dialog.XDialogFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        XDialogFragment dialog = new TestDialogFragment().setBottom().setCanceledOnTouch(false).setCancelAble(true);
        dialog.showDialog(getSupportFragmentManager());
//        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//        dialog.show();
    }
}
