package com.timmy.tdialogdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.base.TBaseAdapter;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;
import com.timmy.tdialogdemo.R;

import java.util.Arrays;

/**
 * 常用的各种Dialog
 */
public class DiffentDialogActivity extends AppCompatActivity {

    private String[] data = {"java", "android", "NDK", "c++", "python", "ios", "Go", "Unity3D", "Kotlin", "Swift", "js"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffent_dialog);
    }

    public void upgradeDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_version_upgrde)
                .setScreenWidthAspect(this, 0.7f)
                .addOnClickListener(R.id.tv_cancel, R.id.tv_confirm)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.tv_cancel:
                                tDialog.dismiss();
                                break;
                            case R.id.tv_confirm:
                                Toast.makeText(DiffentDialogActivity.this, "开始下载新版本apk文件", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                        }
                    }
                })
                .create()
                .show();

    }

    public void tipsDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_vb_convert)
                .setScreenWidthAspect(this, 0.85f)
                .addOnClickListener(R.id.tv_jiuyuan_desc, R.id.tv_cancel, R.id.tv_confirm)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.tv_jiuyuan_desc:
                                Toast.makeText(DiffentDialogActivity.this, "进入说明界面", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.tv_cancel:
                                tDialog.dismiss();
                                break;
                            case R.id.tv_confirm:
                                Toast.makeText(DiffentDialogActivity.this, "操作11", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    public void loadingDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_loading)
                .setHeight(300)
                .setWidth(300)
                .create()
                .show();
    }

    public void homeBannerDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_home_ad)
                .setScreenHeightAspect(this, 0.7f)
                .setScreenWidthAspect(this, 0.8f)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        //可对图片进行修改
                    }
                })
                .addOnClickListener(R.id.iv_close)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        tDialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    public void updateHead(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_change_avatar)
                .setScreenWidthAspect(this, 1.0f)
                .setGravity(Gravity.BOTTOM)
                .addOnClickListener(R.id.tv_open_camera, R.id.tv_open_album, R.id.tv_cancel)
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.tv_open_camera:
                                Toast.makeText(DiffentDialogActivity.this, "打开相机", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                            case R.id.tv_open_album:
                                Toast.makeText(DiffentDialogActivity.this, "打开相册", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                            case R.id.tv_cancel:

                                tDialog.dismiss();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    public void listDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setHeight(600)
                .setScreenWidthAspect(this, 0.8f)
                .setGravity(Gravity.CENTER)
                .setAdapter(new TBaseAdapter<String>(R.layout.item_simple_text, Arrays.asList(data)) {

                    @Override
                    protected void onBind(BindViewHolder holder, int position, String s) {
                        holder.setText(R.id.tv, s);
                    }
                })
                .setOnAdapterItemClickListener(new TBaseAdapter.OnAdapterItemClickListener<String>() {
                    @Override
                    public void onItemClick(BindViewHolder holder, int position, String s, TDialog tDialog) {
                        Toast.makeText(DiffentDialogActivity.this, "click:" + s, Toast.LENGTH_SHORT).show();
                        tDialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    public void bottomListDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setScreenHeightAspect(this, 0.5f)
                .setScreenWidthAspect(this, 1.0f)
                .setGravity(Gravity.BOTTOM)
                .setAdapter(new TBaseAdapter<String>(R.layout.item_simple_text, Arrays.asList(data)) {
                    @Override
                    protected void onBind(BindViewHolder holder, int position, String s) {
                        holder.setText(R.id.tv, s);
                    }
                })
                .setOnAdapterItemClickListener(new TBaseAdapter.OnAdapterItemClickListener<String>() {
                    @Override
                    public void onItemClick(BindViewHolder holder, int position, String s, TDialog tDialog) {
                        Toast.makeText(DiffentDialogActivity.this, "click:" + s, Toast.LENGTH_SHORT).show();
                        tDialog.dismiss();
                    }
                })
                .create()
                .show();
    }

}
