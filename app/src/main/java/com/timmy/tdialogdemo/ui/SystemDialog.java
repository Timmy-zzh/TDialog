package com.timmy.tdialogdemo.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.timmy.tdialogdemo.R;

/**
 * 系统Dialog使用
 */
public class SystemDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_dialog);
    }

    public void btnDialog1(View view) {
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
                .create()
                .show();
    }
}
