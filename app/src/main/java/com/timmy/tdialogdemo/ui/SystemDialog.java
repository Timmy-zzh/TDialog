package com.timmy.tdialogdemo.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.timmy.tdialogdemo.R;

import static androidx.core.os.LocaleListCompat.create;

/**
 * 系统Dialog使用
 * List
 * 多选
 * 单选
 * 自定义
 */
public class SystemDialog extends AppCompatActivity {

    private int selectPosition;
    String[] fruitArray = new String[]{"西瓜", "芒果", "哈密瓜", "荔枝", "火龙果", "波罗蜜"};
    boolean[] selectArray = new boolean[]{true, false, true, true, true, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_dialog);
//        android.app.AlertDialog
    }

    public void btnDialog1(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage("内容")
                .setIcon(R.mipmap.ic_launcher_round)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SystemDialog.this, "确定Click", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public void btnDialog2(View view) {
        new AlertDialog.Builder(this)
                .setTitle("标题")
                .setMessage("内容")
                .setIcon(R.mipmap.ic_launcher_round)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SystemDialog.this, "确定Click", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SystemDialog.this, "取消Click", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("下次提醒", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SystemDialog.this, "下次提醒Click", Toast.LENGTH_SHORT).show();
                    }
                })
                .create()
                .show();
    }

    public void btnDialog3(View view) {
        new AlertDialog.Builder(this)
                .setTitle("你喜欢吃下列哪种水果?")
                .setIcon(R.mipmap.ic_launcher)
                .setItems(fruitArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(SystemDialog.this, "你选择了" + fruitArray[i], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    public void btnDialog4(View view) {
        new AlertDialog.Builder(this)
                .setTitle("你喜欢吃下列哪种水果?")
                .setIcon(R.mipmap.ic_launcher)
                .setSingleChoiceItems(fruitArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        selectPosition = i;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(SystemDialog.this, "你选择了" + fruitArray[selectPosition], Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    public void btnDialog5(View view) {
        new AlertDialog.Builder(this)
                .setMultiChoiceItems(fruitArray, selectArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        selectArray[i] = b;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(SystemDialog.this, "你选择了", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    public void btnDialog7(View view) {
        LayoutInflater layoutInflate = LayoutInflater.from(this);
        View customView = layoutInflate.inflate(R.layout.dialog_custom, null);
        AlertDialog customDialog = new AlertDialog.Builder(this)
//                .setTitle("标题")
                .setIcon(R.mipmap.ic_launcher)
                .setView(customView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .create();
        customDialog.show();
    }

    public void btnDialog8(View view) {
        View customTitle = LayoutInflater.from(this).inflate(R.layout.view_simple_text, null);
        new AlertDialog.Builder(this)
//                .setTitle("标题")
                .setMessage("内容")
                .setCustomTitle(customTitle)
//                        .setCancelable(true)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                    }
                })
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        return false;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    public void progressDialog(View view) {
        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("加载中");
        progressDialog.setMessage("拼命加载中...");
        progressDialog.show();

//        ProgressBar progressBar = new ProgressBar(this);
    }
}
