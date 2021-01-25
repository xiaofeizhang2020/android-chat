/*
 * Copyright (c) 2020 WildFireChat. All rights reserved.
 */

package cn.wildfire.chat.app.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityOptionsCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.appbar.AppBarLayout;

import java.security.MessageDigest;
import java.util.Base64;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cn.wildfire.chat.app.AppService;
import cn.wildfire.chat.app.login.model.LoginResult;
import cn.wildfire.chat.app.main.MainActivity;
import cn.wildfire.chat.app.view.ProgressUtils;
import cn.wildfire.chat.kit.ChatManagerHolder;
import cn.wildfire.chat.kit.WfcBaseActivity;
import cn.wildfire.chat.kit.WfcBaseNoToolbarActivity;
import cn.wildfirechat.chat.R;

import static cn.wildfire.chat.app.BaseApp.getContext;

/**
 * use {@link SMSLoginActivity} instead
 */
//@Deprecated
public class LoginActivity extends WfcBaseNoToolbarActivity {
    @BindView(R.id.loginButton)
    Button loginButton;
    @BindView(R.id.accountEditText)
    EditText accountEditText;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.userRegister)
    TextView userRegister;


    @Override
    protected int contentLayout() {
        return R.layout.login_activity_account;
    }

//    @Override
//    protected boolean showHomeMenuItem() {
//        return false;
//    }

    @OnTextChanged(value = R.id.accountEditText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputAccount(Editable editable) {
        if (!TextUtils.isEmpty(passwordEditText.getText()) && !TextUtils.isEmpty(editable)) {
            loginButton.setEnabled(true);
        } else {
            loginButton.setEnabled(false);
        }
    }

    @OnTextChanged(value = R.id.passwordEditText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputPassword(Editable editable) {
        if (!TextUtils.isEmpty(accountEditText.getText()) && !TextUtils.isEmpty(editable)) {
            loginButton.setEnabled(true);
        } else {
            loginButton.setEnabled(false);
        }
    }


    @OnClick(R.id.loginButton)
    void login() {

        String account = accountEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

//        MaterialDialog dialog = new MaterialDialog.Builder(this)
////                .content("登录中...")
////                .progress(true, 10)
////                .cancelable(false)
////                .build();
////        dialog.show();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            String pwd = Base64.getEncoder().encodeToString(md5.digest(password.getBytes("utf-8")));
            ProgressUtils.getInstance().show(this);
            AppService.Instance().smsLogin(account, pwd, new AppService.LoginCallback() {
                @Override
                public void onUiSuccess(LoginResult loginResult) {
                    if (isFinishing()) {
                        return;
                    }

                    //需要注意token跟clientId是强依赖的，一定要调用getClientId获取到clientId，然后用这个clientId获取token，这样connect才能成功，如果随便使用一个clientId获取到的token将无法链接成功。
                    ChatManagerHolder.gChatManager.connect(loginResult.getUserId(), loginResult.getToken());
                    SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
                    sp.edit()
                            .putString("id", loginResult.getUserId())
                            .putString("token", loginResult.getToken())
                            .apply();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    // dialog.dismiss();
                    finish();
                    ProgressUtils.getInstance().dismiss();

                }

                @Override
                public void onUiFailure(int code, String msg) {
                    ProgressUtils.getInstance().dismiss();

                    if (isFinishing()) {
                        return;
                    }
                    // dialog.dismiss();

                    Toast.makeText(LoginActivity.this, "网络出问题了:" + code + " " + msg, Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @OnClick(R.id.userRegister)
    public void onViewClicked() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getContext(),
                android.R.anim.fade_in, android.R.anim.fade_out).toBundle();
        startActivity(intent, bundle);
    }
}
