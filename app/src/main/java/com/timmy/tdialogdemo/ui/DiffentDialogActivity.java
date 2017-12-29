package com.timmy.tdialogdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import com.timmy.tdialog.list.TListDialog;
import com.timmy.tdialogdemo.R;
import com.timmy.tdialogdemo.adapter.SimpleAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * 常用的各种Dialog
 */
public class DiffentDialogActivity extends AppCompatActivity {

    private String[] data = {"java", "python", "c++", "ios", "android","运营","人力","行政","采购","市场"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffent_dialog);
    }

    public void listDialog(View view) {
//        SimpleAdapter adapter = new SimpleAdapter(Arrays.asList(data));
//        TListDialog.create(getSupportFragmentManager())
//                .setGravity(Gravity.BOTTOM)
//                .setAdapter(adapter)
//                .setScreenWidthAspect(this, 1.0f)
//                .setScreenHeightAspect(this,0.4f)
//                .show();
    }
}
