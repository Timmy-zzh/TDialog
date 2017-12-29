package com.timmy.tdialogdemo.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;
import com.timmy.tdialogdemo.R;

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

    }

    public void showClickDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_click)
                .setDimAmount(0.6f)
                .setGravity(Gravity.RIGHT)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder bindViewHolder) {
                        bindViewHolder.setText(R.id.tv_content, "爱的减肥啦到家啦房间看多了几分");
                    }
                })
                .addOnClickListener(R.id.btn_right, R.id.tv_title)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder,View view1, TDialog tDialog) {
                        switch (view1.getId()) {
                            case R.id.btn_right:
                                Toast.makeText(DialogEncapActivity.this, "btn_right", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                            case R.id.tv_title:
                                Toast.makeText(DialogEncapActivity.this, "tv_title", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    public void showClickDialog2(View view) {
        TDialog.Builder builder = new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_click)
                .setGravity(Gravity.BOTTOM);
        TDialog djDialog = builder.create();
        djDialog.show();
    }


}
