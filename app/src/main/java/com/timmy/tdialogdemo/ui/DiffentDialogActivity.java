package com.timmy.tdialogdemo.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.base.TBaseAdapter;
import com.timmy.tdialog.list.TListDialog;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;
import com.timmy.tdialogdemo.R;

import java.util.Arrays;

/**
 * 常用的各种Dialog
 */
public class DiffentDialogActivity extends AppCompatActivity {

    private static final String TAG = "TDialog";
    private static final int WHAT_LOADING = 0;
    private static final int WHAT_PROGRESS = 1;
    private String[] data = {"java", "android", "NDK", "c++", "python", "ios", "Go", "Unity3D", "Kotlin", "Swift", "js"};
    private String[] sharePlatform = {"微信", "朋友圈", "短信", "微博", "QQ空间", "Google", "FaceBook", "微信", "朋友圈", "短信", "微博", "QQ空间"};
    private TDialog tDialog;
    int currProgress = 5;
    private ProgressBar progressBar;
    private TextView tvProgress;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_LOADING:
                    if (tDialog != null) {
                        tDialog.dismiss();
                    }
                    return;

                case WHAT_PROGRESS:
                    currProgress += 5;
                    progressBar.setProgress(currProgress);
                    tvProgress.setText("progress:" + currProgress + "/100");
                    if (tDialog != null && currProgress >= 100) {
                        currProgress = 0;
                        tDialog.dismiss();
                        tDialog = null;
                    } else {
                        handler.sendEmptyMessageDelayed(WHAT_PROGRESS, 1000);
                    }
                    return;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffent_dialog);
    }

    public void testDialog(View view2) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null);
        tDialog = new TDialog.Builder(getSupportFragmentManager())
                .setDialogView(view)
                .setCancelableOutside(false)
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        Toast.makeText(DiffentDialogActivity.this, "keyCode:" + keyCode, Toast.LENGTH_SHORT).show();
                        if (keyCode == KeyEvent.KEYCODE_BACK) {

                            return true;
                        }
                        return false;
                    }
                })
                .create();
        tDialog.show();
    }

    public void useTDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_click)    //设置弹窗展示的xml布局
//                .setDialogView(view)  //设置弹窗布局,直接传入View
                .setWidth(600)  //设置弹窗宽度(px)
                .setHeight(800)  //设置弹窗高度(px)
                .setScreenWidthAspect(this, 0.8f)   //设置弹窗宽度(参数aspect为屏幕宽度比例 0 - 1f)
                .setScreenHeightAspect(this, 0.3f)  //设置弹窗高度(参数aspect为屏幕宽度比例 0 - 1f)
                .setGravity(Gravity.CENTER)     //设置弹窗展示位置
                .setTag("DialogTest")   //设置Tag
                .setDimAmount(0.6f)     //设置弹窗背景透明度(0-1f)
                .setCancelableOutside(true)     //弹窗在界面外是否可以点击取消
                .setDialogAnimationRes(R.style.animate_dialog) //设置弹窗动画
                .setOnDismissListener(new DialogInterface.OnDismissListener() { //弹窗隐藏时回调方法
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(DiffentDialogActivity.this, "弹窗消失回调", Toast.LENGTH_SHORT).show();
                    }
                })
                .setOnBindViewListener(new OnBindViewListener() {   //通过BindViewHolder拿到控件对象,进行修改
                    @Override
                    public void bindView(BindViewHolder bindViewHolder) {
                        bindViewHolder.setText(R.id.tv_content, "abcdef");
                        bindViewHolder.setText(R.id.tv_title, "我是Title");
                    }
                })
                .addOnClickListener(R.id.btn_left, R.id.btn_right, R.id.tv_title)   //添加进行点击控件的id
                .setOnViewClickListener(new OnViewClickListener() {     //View控件点击事件回调
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        switch (view.getId()) {
                            case R.id.btn_left:
                                Toast.makeText(DiffentDialogActivity.this, "left clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.btn_right:
                                Toast.makeText(DiffentDialogActivity.this, "right clicked", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                            case R.id.tv_title:
                                Toast.makeText(DiffentDialogActivity.this, "title clicked", Toast.LENGTH_SHORT).show();
                                viewHolder.setText(R.id.tv_title, "Title点击了");
                                break;
                        }
                    }
                })
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        Toast.makeText(DiffentDialogActivity.this, "按键 keyCode:" + keyCode, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .create()   //创建TDialog
                .show();    //展示
    }

    public void upgradeDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_version_upgrde)
                .setScreenWidthAspect(this, 0.7f)
                .addOnClickListener(R.id.tv_cancel, R.id.tv_confirm)
                .setDialogAnimationRes(R.style.animate_dialog_scale)
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

    public void upgradeDialogStrong(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_version_upgrde_strong)
                .setScreenWidthAspect(this, 0.7f)
                .addOnClickListener(R.id.tv_confirm)
                .setCancelableOutside(false)
                .setDialogAnimationRes(R.style.animate_dialog_scale)
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            Toast.makeText(DiffentDialogActivity.this, "返回健无效，请强制升级后使用", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                })
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        Toast.makeText(DiffentDialogActivity.this, "开始下载新版本apk文件", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    public void tipsDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_vb_convert)
                .setScreenWidthAspect(this, 0.85f)
                .setCancelableOutside(false)
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
                                Toast.makeText(DiffentDialogActivity.this, "执行优惠券兑换逻辑", Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                                break;
                        }
                    }
                })
                .create()
                .show();
    }

    public void loadingDialog(View view) {
        tDialog = new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_loading)
                .setHeight(300)
                .setWidth(300)
                .setCancelableOutside(false)
                .create()
                .show();
        handler.sendEmptyMessageDelayed(WHAT_LOADING, 5 * 1000);
    }

    public void progressDialog(final View view) {
        tDialog = new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_loading_progress)
                .setScreenWidthAspect(this, 0.8f)
                .setCancelableOutside(true)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        progressBar = viewHolder.getView(R.id.progress_bar);
                        tvProgress = viewHolder.getView(R.id.tv_progress);
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        handler.removeMessages(WHAT_PROGRESS);
                        currProgress = 5;
                    }
                })
                .create()
                .show();
        handler.sendEmptyMessageDelayed(WHAT_PROGRESS, 1000);
    }

    public void dialogView(View view) {
        TextView textView = new TextView(this);
        textView.setText("DialogView");
        textView.setTextSize(25);
        textView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        textView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));

        tDialog = new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_loading)
                .setDialogView(textView)
                .setHeight(400)
                .setWidth(600)
                .setCancelableOutside(true)
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
                .setDialogAnimationRes(R.style.animate_dialog)
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
        new TListDialog.Builder(getSupportFragmentManager())
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
        new TListDialog.Builder(getSupportFragmentManager())
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
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        Toast.makeText(DiffentDialogActivity.this, "setOnDismissListener 回调", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    //评价 弹出输入框
    public void evaluateDialog(View view) {
        new TDialog.Builder(getSupportFragmentManager())
                .setLayoutRes(R.layout.dialog_evaluate)
                .setScreenWidthAspect(this, 1.0f)
                .setGravity(Gravity.BOTTOM)
                .addOnClickListener(R.id.btn_evluate)
                .setOnBindViewListener(new OnBindViewListener() {
                    @Override
                    public void bindView(BindViewHolder viewHolder) {
                        final EditText editText = viewHolder.getView(R.id.editText);
                        editText.post(new Runnable() {
                            @Override
                            public void run() {
                                InputMethodManager imm = (InputMethodManager) DiffentDialogActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.showSoftInput(editText, 0);
                            }
                        });
                    }
                })
                .setOnViewClickListener(new OnViewClickListener() {
                    @Override
                    public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                        EditText editText = viewHolder.getView(R.id.editText);
                        String content = editText.getText().toString().trim();
                        Toast.makeText(DiffentDialogActivity.this, "评价内容:" + content, Toast.LENGTH_SHORT).show();
                        tDialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    //底部分享
    public void shareDialog(View view) {
        new TListDialog.Builder(getSupportFragmentManager())
                .setListLayoutRes(R.layout.dialog_share_recycler, LinearLayoutManager.HORIZONTAL)
                .setScreenWidthAspect(this, 1.0f)
                .setGravity(Gravity.BOTTOM)
                .setAdapter(new TBaseAdapter<String>(R.layout.item_share, Arrays.asList(sharePlatform)) {
                    @Override
                    protected void onBind(BindViewHolder holder, int position, String s) {
                        holder.setText(R.id.tv, s);
                    }
                })
                .setOnAdapterItemClickListener(new TBaseAdapter.OnAdapterItemClickListener<String>() {
                    @Override
                    public void onItemClick(BindViewHolder holder, int position, String item, TDialog tDialog) {
                        Toast.makeText(DiffentDialogActivity.this, item, Toast.LENGTH_SHORT).show();
                        tDialog.dismiss();
                    }
                })
                .create()
                .show();
    }

}
