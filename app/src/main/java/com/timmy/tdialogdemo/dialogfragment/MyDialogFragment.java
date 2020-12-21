package com.timmy.tdialogdemo.dialogfragment;


import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.timmy.tdialogdemo.R;

/**
 * 使用onCreateView返回Dialog的界面布局
 */
public class MyDialogFragment extends DialogFragment {

    public static final String TAG = MyDialogFragment.class.getName();

    /**
     * 实例化,并传递数据
     */
    public static MyDialogFragment getInstance(int num) {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("num", num);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        int num = getArguments().getInt("num");
//        Log.d(TAG, "onCreate num:" + num);
//        //设置style类型
//        // Pick a style based on the num.
//        int style = DialogFragment.STYLE_NORMAL, theme = 0;
//        Log.d(TAG, "(num - 1) % 6:" + ((num - 1) % 6));
//        switch ((num - 1) % 6) {
//            case 1:
//                style = DialogFragment.STYLE_NO_TITLE;
//                break;
//            case 2:
//                style = DialogFragment.STYLE_NO_FRAME;
//                break;
//            case 3:
//                style = DialogFragment.STYLE_NO_INPUT;
//                break;
//        }
//        switch ((num - 1) % 6) {
//            case 1:
//                theme = android.R.style.Theme_Holo;
//                break;
//            case 2:
//                theme = android.R.style.Theme_Holo_Light_Dialog;
//                break;
//            case 3:
//                theme = android.R.style.Theme_Holo_Light;
//                break;
//        }
//        setStyle(style, theme);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_my_dialog, container, false);
        TextView textView = view.findViewById(R.id.tv);
        textView.setText("DialogFragment");
        Button button = view.findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick");
//                ((NormalDFActivity) getActivity()).showDialogFragment(null);
            }
        });
        return view;
    }
}
