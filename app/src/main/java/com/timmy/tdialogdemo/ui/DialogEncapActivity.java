package com.timmy.tdialogdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialogdemo.R;
import com.timmy.tdialog.TDialog;

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
     *
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

    public void showClickDialog(View view) {
        TDialog.create(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_click)
                .setScreenWidthAspect(this, 0.8f)
                .setDimAmount(0.2f)
                .setCancelOutside(false)
                .setOnBindViewListener(new TDialog.OnBindViewListener() {
                    @Override
                    public void bindView(View v) {

                    }
                })
                .addOnClickListener(R.id.tv_title, R.id.tv_content, R.id.btn_left, R.id.btn_right)
                .setOnItemChildClickListener(new TDialog.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BindViewHolder viewHolder, View view) {
                        switch (view.getId()) {
                            case R.id.tv_title:
                                Toast.makeText(DialogEncapActivity.this, "title", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.tv_content:
                                Toast.makeText(DialogEncapActivity.this, "tv_content", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.btn_left:
                                Toast.makeText(DialogEncapActivity.this, "btn_left", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.btn_right:
                                Toast.makeText(DialogEncapActivity.this, "btn_right", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                })
                .show();
    }
}
