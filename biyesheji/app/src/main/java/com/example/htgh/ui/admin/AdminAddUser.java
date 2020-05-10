package com.example.htgh.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.htgh.R;
import com.example.htgh.common.ApiService;
import com.example.htgh.datasource.user.UserDao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminAddUser extends AppCompatActivity {
    EditText username, email, pwd, pwdConfirm;
    Button save;
    JSONArray users=null;
    private boolean status = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_user);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        pwdConfirm = findViewById(R.id.pwd_confirm);
        save = findViewById(R.id.save);
        final Intent intent = getIntent();
        String response = intent.getStringExtra("response");
        try {
            JSONObject obj=new JSONObject(response);
            users=obj.getJSONArray("data");

        } catch (JSONException e) {

        }
        save.setEnabled(false);
        setWatcher();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDao userDao=new UserDao();

                userDao.addUser(intent,username.getText().toString(),
                        pwd.getText().toString(),
                        email.getText().toString());
                while(true) {
                    int status = intent.getIntExtra("requestStatus", -1);
                    System.out.println("状态码：" + status);
                    if (status != ApiService.LODING) {
                        String response = intent.getStringExtra("response");
                        //处理用户名重复的问题看是后台自动生成还是咋的
                        System.out.println(response);
                        break;
                    }
                }
                startActivity(new Intent(AdminAddUser.this, AdminUserManage.class));
                AdminAddUser.this.finish();
            }
        });
    }

    private void setWatcher() {

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String nameS = username.getText().toString();
                String emailS = email.getText().toString();
                String pwdS = pwd.getText().toString();
                String pwdConfirmS = pwdConfirm.getText().toString();
                boolean flag = true;
                if (nameS.length() < 2 || nameS.length() > 12) {
                    flag = false;
                    username.setError("用户名在2-12个字符间");
                }else if(userNameExist(nameS)){
                    flag=false;
                    username.setError("用户名已存在");
                }
                if (emailS==""||!jugeEmail(emailS)) {
                    flag = false;
                    email.setError("请输入正确的邮箱格式");
                }
                if (pwdS.length() < 6 || pwdS.length() > 18) {
                    flag = false;
                    pwd.setError("密码在6-18字符");
                }
                if (!pwdConfirmS.equals(pwdS)) {
                    flag = false;
                    pwdConfirm.setError("两次输入不一致");
                }
                save.setEnabled(flag);
            }
        };

        username.addTextChangedListener(watcher);
        email.addTextChangedListener(watcher);
        pwd.addTextChangedListener(watcher);
        pwdConfirm.addTextChangedListener(watcher);
    }

    private void setFalse() {
        status = false;
    }

    private void setTrue() {
        status = true;
    }

    /**
     * 邮箱校验
     * @param s
     * @return
     */
    public boolean jugeEmail(String s){
        //邮箱正则表达式
        String ruleRmail="^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //编译规则
        Pattern pattern = Pattern.compile(ruleRmail);
        //插卡是否匹配
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    public boolean userNameExist(String s){
        for(int i=0;i<users.length();i++){
            try {
                JSONObject item= users.getJSONObject(i);
                if(item.get("userName").equals(s))
                    return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
