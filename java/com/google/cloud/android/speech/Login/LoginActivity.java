package com.google.cloud.android.speech.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.cloud.android.speech.Common.BaseActivity;
import com.google.cloud.android.speech.DailyTest.StartDailyTestActivity;
import com.google.cloud.android.speech.MainMenu.StartActivity;
import com.google.cloud.android.speech.R;

/**
 * Created by 김다영 on 2017-08-24.
 */

public class LoginActivity extends AppCompatActivity{

    private EditText editid,editpassword;
    private TextView loginBtn;
    private String id,pw;
    private String user_id,user_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        editid=(EditText)findViewById(R.id.login_editid);
        editpassword=(EditText)findViewById(R.id.login_editpassword);
        user_id="admin";
        user_password="admin";

        saveUserId(user_id);
        saveUserPassword(user_password);

        loginBtn=(TextView)findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=editid.getText().toString();
                pw=editpassword.getText().toString();
                checkUser(id,pw);
            }
        });

    }

    private void saveUserId(String user_id){

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();

        editor.putString("user_id",user_id);

        editor.commit();

    }

    private void saveUserPassword(String user_password){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);

        SharedPreferences.Editor editor = pref.edit();

        editor.putString("user_password", user_password);

        editor.commit();
    }

    private void checkUser(String id, String pw){
        if(user_id.equals(id)&&user_password.equals(pw)){
            Intent intent=new Intent(LoginActivity.this, StartActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"아이디 또는 비밀번호가 틀립니다.",Toast.LENGTH_SHORT).show();
        }
    }
}
