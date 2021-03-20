package com.zhengxinpeng.nct;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.zhengxinpeng.nct.model.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class LoginActivity extends AppCompatActivity {

    private EditText et_user;
    private EditText et_pass;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_user = findViewById(R.id.et_user);
        et_pass = findViewById(R.id.et_pass);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    /**
     * 登录方法
     */
    private void login() {
        String userName = et_user.getText().toString();
        String password = et_pass.getText().toString();
        // 创建查询对象
        BmobQuery<User> query = new BmobQuery<>();
        query.addWhereEqualTo("userName", userName);
        query.findObjects(new FindListener<User>() {
            @Override
            public void done(List<User> list, BmobException e) {
                if (list.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "无该用户信息", Toast.LENGTH_SHORT).show();
                } else {
                    // 取到我们查询到的用户信息
                    User user = list.get(0);
                    // 将查询到的用户的密码与我们输入的密码作比较
                    if (user.getPassword().equals(password)) {
                        // 判断一致
                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        // 判断不一致
                        Toast.makeText(LoginActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}