package com.google.cloud.android.speech.UserData;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.cloud.android.speech.Common.BaseActivity;
import com.google.cloud.android.speech.MainMenu.StartActivity;
import com.google.cloud.android.speech.R;

/**
 * Created by 김다영 on 2017-07-12.
 */

public class UserDataActivity extends BaseActivity {

    final Context context=this;
    MySQLiteHandler handler;
    EditText editName;
    EditText editAge;
    EditText editAddress;
    TextView editId;
    Cursor c;

    Button submit,delete;
    ImageButton menu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdata);

        editName=(EditText)findViewById(R.id.editName);
        editAge=(EditText)findViewById(R.id.editAge);
        editAddress=(EditText)findViewById(R.id.editAddress);
        editId=(TextView)findViewById(R.id.editId);
        user_id=getUserId();
        handler=MySQLiteHandler.open(getApplicationContext());
        handler.insert(user_id,"","","");
        c=handler.selectById(user_id);
        startManagingCursor(c);

        if(c!=null&&c.moveToFirst()) {
            editName.setText(c.getString(c.getColumnIndex("name")));
            editId.setTextSize(20);
            editId.setText(user_id);
            if (c.getString(c.getColumnIndex("age")) != null) {
                editAge.setText(c.getString(c.getColumnIndex("age")));
            }

            if (c.getString(c.getColumnIndex("address")) != null) {
                editAddress.setText(c.getString(c.getColumnIndex("address")));
            }
        }

        submit=(Button)findViewById(R.id.submitbtn);
        delete=(Button)findViewById(R.id.deletebtn);
        menu=(ImageButton)findViewById(R.id.menubtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("저장하시겠습니까?");
                alertDialogBuilder.setMessage("").setCancelable(true).setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String name=editName.getText().toString();
                        String age=editAge.getText().toString();
                        String address=editAddress.getText().toString();

                        handler.update(user_id,name,age,address);
                        c.requery();
                        c.close();
                        handler.close();
                        Intent intent=new Intent(UserDataActivity.this,StartActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                                          alertDialogBuilder.setTitle("삭제하시겠습니까?");
                                          alertDialogBuilder.setMessage("모든 사용자정보를 삭제합니다.").setCancelable(true).setPositiveButton("예", new DialogInterface.OnClickListener() {
                                              public void onClick(DialogInterface dialog, int id) {
                                                  handler.delete(user_id);
                                                  handler.close();
                                                  c.close();
                                                  Intent intent = new Intent(UserDataActivity.this, StartActivity.class);
                                                  startActivity(intent);
                                              }
                                          }).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                              public void onClick(DialogInterface dialog, int id) {
                                                  dialog.cancel();
                                              }
                                          });

                                          AlertDialog alertDialog = alertDialogBuilder.create();
                                          alertDialog.show();
                                      }
                                  });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(UserDataActivity.this,StartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.rightout_activity,R.anim.rightout_activity);
            }
        });
    }
    private String getUserId(){
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);

        return pref.getString("user_id", "");
    }
}

