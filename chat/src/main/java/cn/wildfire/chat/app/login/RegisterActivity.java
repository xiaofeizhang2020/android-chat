package cn.wildfire.chat.app.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.google.android.material.appbar.AppBarLayout;

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
import cn.wildfire.chat.kit.net.SimpleCallback;
import cn.wildfirechat.chat.R;

public class RegisterActivity extends WfcBaseActivity {



    @BindView(R.id.registerName)
    EditText registerName;
    @BindView(R.id.registerPwd)
    EditText registerPwd;
    @BindView(R.id.registerCode)
    EditText registerCode;
    @BindView(R.id.registerButton)
    Button registerButton;

    @Override
    protected int contentLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void afterViews() {
        super.afterViews();
        setTitle("用户注册");
       // setTitleBackgroundResource(cn.wildfire.chat.kit.R.color.gray, false);
     //   getToolbar().setTitleTextColor(getResources().getColor(R.color.black));
    }

    @OnTextChanged(value = R.id.registerName, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputAccount(Editable editable) {
        if (!TextUtils.isEmpty(registerName.getText()) && !TextUtils.isEmpty(registerPwd.getText())&&!TextUtils.isEmpty(registerCode.getText())) {
            registerButton.setEnabled(true);
        } else {
            registerButton.setEnabled(false);
        }
    }

    @OnTextChanged(value = R.id.registerPwd, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputPassword(Editable editable) {
        if (!TextUtils.isEmpty(registerName.getText()) && !TextUtils.isEmpty(registerPwd.getText())&&!TextUtils.isEmpty(registerCode.getText())) {
            registerButton.setEnabled(true);
        } else {
            registerButton.setEnabled(false);
        }
    }
    @OnTextChanged(value = R.id.registerCode, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void inputCode(Editable editable) {
        if (!TextUtils.isEmpty(registerName.getText()) && !TextUtils.isEmpty(registerPwd.getText())&&!TextUtils.isEmpty(registerCode.getText())) {
            registerButton.setEnabled(true);
        } else {
            registerButton.setEnabled(false);
        }
    }
    @OnClick(R.id.registerButton)
    public void onViewClicked() {
        String account = registerName.getText().toString().trim();
        String password = registerPwd.getText().toString().trim();
        String code = registerCode.getText().toString().trim();

//        MaterialDialog dialog = new MaterialDialog.Builder(this)
////                .content("注册中...")
////                .progress(true, 10)
////                .cancelable(false)
////                .build();
////        dialog.show();
        ProgressUtils.getInstance().show(this);
        AppService.Instance().useRegister(account, password,code,new SimpleCallback<String>(){

            @Override
            public void onUiSuccess(String s) {
                if (isFinishing()) {
                    return;
                }
               // dialog.dismiss();
                ProgressUtils.getInstance().dismiss();
                if(!TextUtils.isEmpty(s)){
                  JSONObject body= JSONObject.parseObject(s);
                  int code=body.getIntValue("code");

                    if(code==0){
                      Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_LONG).show();
                      finish();
                  }else{
                        String msg=body.getString("message");
                        Toast.makeText(RegisterActivity.this,msg,Toast.LENGTH_LONG).show();
                    }
                }

                //需要注意token跟clientId是强依赖的，一定要调用getClientId获取到clientId，然后用这个clientId获取token，这样connect才能成功，如果随便使用一个clientId获取到的token将无法链接成功。
              /*  ChatManagerHolder.gChatManager.connect(loginResult.getUserId(), loginResult.getToken());
                SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
                sp.edit()
                        .putString("id", loginResult.getUserId())
                        .putString("token", loginResult.getToken())
                        .apply();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);*/

            }

            @Override
            public void onUiFailure(int code, String msg) {
                ProgressUtils.getInstance().dismiss();

                if (isFinishing()) {
                    return;
                }
                //dialog.dismiss();

                Toast.makeText(RegisterActivity.this, "网络出问题了:" + code + " " + msg, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
