package com.timmy.tdialog.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.timmy.tdialog.R;
import com.timmy.tdialog.dialog.TDialog;

/**
 * DialogFragment封装
 */
public class DialogEncapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_encap);
    }

    /**
     * 展示Dialog
     * @param view
     */
    public void showDialog(View view) {
        TDialog.create(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_simple)
//                .setGravity(Gravity.BOTTOM|Gravity.RIGHT)
                .setWidth(800)
//                .setHeight(400)
                .setDimAmount(0.6f)
                .setCancelOutside(false)
                .setOnBindViewListener(new TDialog.OnBindViewListener() {
                    @Override
                    public void bindView(View v) {

                    }
                })
                .show();
    }
}
