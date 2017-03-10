package com.example.ec.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.easycode.util.ToastUtil;
import com.example.ec.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {

    @BindView(R.id.btn_basic_dialog)
    Button btnBasicDialog;
    @BindView(R.id.btn_neutral_action)
    Button btnNeutralAction;
    @BindView(R.id.btn_checkbox)
    Button btnCheckbox;
    @BindView(R.id.btn_list_dialog)
    Button btnListDialog;
    @BindView(R.id.btn_single_list)
    Button btnSingleList;
    @BindView(R.id.btn_multi_list)
    Button btnMultiList;
    @BindView(R.id.btn_custom_list)
    Button btnCustomList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_basic_dialog, R.id.btn_neutral_action, R.id.btn_checkbox, R.id.btn_list_dialog, R.id.btn_single_list, R.id.btn_multi_list, R.id.btn_custom_list})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_basic_dialog:
                new MaterialDialog.Builder(this)
                        .title("标题")
                        .content("内容")
                        .positiveText("ok")
                        .negativeText("cancel")
                        .show();
                break;
            case R.id.btn_neutral_action:
                new MaterialDialog.Builder(this)
                        .title("标题")
                        .content("内容")
                        .positiveText("ok")
                        .negativeText("cancel")
                        .neutralText("123321")
                        .show();

                break;
            case R.id.btn_checkbox:

                new MaterialDialog.Builder(this)
                        .iconRes(R.mipmap.ic_launcher)
                        .limitIconToDefaultSize()
                        .title("标题")
                        .positiveText("ok")
                        .negativeText("cancel")
                        .onAny(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                ToastUtil.showShort("hehe");
                            }
                        })
                        .checkBoxPromptRes(R.string.app_name, false, null)
                        .show();
                break;
            case R.id.btn_list_dialog:

                new MaterialDialog.Builder(this)
                        .title("标题")
                        .items(R.array.languages)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                ToastUtil.showShort("selection " + text);
                            }
                        })
                        .positiveText("ok")
                        .negativeText("cancel")
                        .show();

                break;
            case R.id.btn_single_list:

                new MaterialDialog.Builder(this)
                        .title("标题")
                        .items(R.array.languages)
                        .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                            @Override
                            public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                                /**
                                 * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                                 * returning false here won't allow the newly selected radio button to actually be selected.
                                 **/
                                return true;
                            }
                        })
                        .positiveText("ok")
                        .negativeText("cancel")
                        .show();
                break;
            case R.id.btn_multi_list:
                ToastUtil.showShort("hehe");
                break;
            case R.id.btn_custom_list:
                ToastUtil.showShort("hehe");
                break;
        }
    }
}
