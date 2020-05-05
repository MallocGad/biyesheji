package com.example.htgh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htgh.MainActivity;
import com.example.htgh.R;
import com.example.htgh.common.ApiService;
import com.example.htgh.common.MyApplication;
import com.example.htgh.datasource.LoginDao;
import com.example.htgh.ui.admin.AdminMainActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        //获取view
        username=findViewById(R.id.login_user);
        pwd=findViewById(R.id.login_pwd);
        final RadioGroup type=findViewById(R.id.login_type);
        final Button button=findViewById(R.id.login);
        final TextView userWran=findViewById(R.id.user_warn);
        final TextView passwordWarn=findViewById(R.id.password_warn);
        //登录action
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!checkInput())
                    return;
                //获取view值
                String nameText = username.getText().toString();
                String pwdText = pwd.getText().toString();
                //获取单选框的值
                RadioButton user = (RadioButton) type.getChildAt(0);
                RadioButton admin=(RadioButton) type.getChildAt(1);
                String role=null;
                Intent intent=null;
                //根据不同角色进入不同主页
                if(admin.isChecked()){
                    role="admin";
                    intent = new Intent(LoginActivity.this, AdminMainActivity.class);
                }else{
                    role="user";
                    intent=new Intent(LoginActivity.this,MainActivity.class);
                }
                //下一页为主页
                LoginDao loginDao=new LoginDao(intent);
                //登录请求
                loginDao.login(nameText,pwdText,role);
                //判断请求状况,登录应该是一个同步操作
                int status;
                while(true){
                    status= loginDao.getStatus();
                    if(ApiService.LODING!=status)
                        break;
                }
                //有个cookie丢失的情况
                if(ApiService.NETWORKERRO==status){
                    Toast.makeText(LoginActivity.this,"网络错误！",Toast.LENGTH_SHORT).show();
                }else if(ApiService.SUCCESS==status){
                    String response = intent.getStringExtra("response");
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.getInt("code")==0){
                            //登录成功保存用户信息到application中
                            JSONObject data = jsonObject.getJSONObject("data");
                            MyApplication.setCurrentUserId(data.getInt("userId"));
                            MyApplication.setAttribute("userName",data.getString("userName"));
                            MyApplication.setAttribute("userInfo",data);
                        }else{
                            Toast.makeText(LoginActivity.this,jsonObject.getString("msg") ,Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this,"未知异常！请重试！",Toast.LENGTH_SHORT).show();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        //检测输入框
        TextWatcher watcher=new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                button.setEnabled(checkInput());
            }
        };
        username.addTextChangedListener(watcher);
        pwd.addTextChangedListener(watcher);

    }

    public boolean checkInput(){
        boolean flag=true;
        String nameTxt=username.getText().toString();
        String pwdTxt=pwd.getText().toString();
        if ( nameTxt== null || "".equals(nameTxt)) {
            username.setError("用户名不能为空");
            flag =false;
        }else
        {
        }
        if (pwdTxt == null || "".equals(pwdTxt)) {
            pwd.setError("密码不能为空");
            flag=false;
        }else
        {
        }
        return flag;
    }

}
