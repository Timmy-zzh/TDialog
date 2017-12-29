package com.timmy.tdialogdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;
import com.timmy.tdialogdemo.ui.DialogEncapActivity;
import com.timmy.tdialogdemo.ui.DiffentDialogActivity;
import com.timmy.tdialogdemo.ui.NormalDFActivity;
import com.timmy.tdialogdemo.ui.SystemDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 系统Dialog使用
     *
     * @param view
     */
    public void systemDialog(View view) {
        startActivity(new Intent(this, SystemDialog.class));
    }

    /**
     * DialogFragment的使用
     *
     * @param view
     */
    public void NormalDF(View view) {
        startActivity(new Intent(this, NormalDFActivity.class));
    }

    /**
     * DialogFragment封装
     *
     * @param view
     */
    public void DialogEncap(View view) {
//        startActivity(new Intent(this, DialogEncapActivity.class));
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_click)
                .setScreenWidthAspect(MainActivity.this,0.8f)
                .setTag("DialogTest")
                .setDimAmount(0.6f)
                .setGravity(Gravity.CENTER)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder bindViewHolder) {
                        bindViewHolder.setText(R.id.tv_content, "abcdef");
                    }
                })
                .addOnClickListener(R.id.btn_right, R.id.tv_title)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder,View view1, TDialog tDialog) {
                        switch (view1.getId()) {
                            case R.id.btn_right:
                                Toast.makeText(MainActivity.this, "btn_right", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                            case R.id.tv_title:
                                Toast.makeText(MainActivity.this, "tv_title", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    /**
     * 常用的各种Dialog
     *
     * @param view
     */
    public void diffentDialog(View view) {
//        Handler handler ;
//
//        //HandlerThread的使用
//        HandlerThread handlerThread = new HandlerThread("handlerThread");
//        handlerThread.start();
//        handler = new Handler(handlerThread.getLooper());
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//        startActivity(new Intent(this, DiffentDialogActivity.class));
    }
}
