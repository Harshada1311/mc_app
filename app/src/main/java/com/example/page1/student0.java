package com.example.page1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class student0 extends AppCompatActivity {
    DataBaseHelper db;
    private EditText tx1,tx2;
    private Button Reg_button,log_in_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student0);
        onClickReg();
        onClickLogin();
    }

    public void onClickReg(){
        db = new DataBaseHelper(this);
        Reg_button=(Button)findViewById(R.id.button16);
        tx1 = (EditText)findViewById(R.id.studentUserId);
        tx2 = (EditText)findViewById(R.id.studentPassword);
        Reg_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String stu_id = tx1.getText().toString();
                        String stu_pass = tx2.getText().toString();
                        if(stu_id.equals("")||stu_pass.equals("")){
                            Toast.makeText(getApplicationContext(),"Fields cannot be empty",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Boolean chkuid = db.chk_uid(stu_id);
                            if(chkuid==true){
                                Boolean reg = db.registration(stu_id,stu_pass);
                                if(reg==true){
                                    Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"ID Already Exists",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );
    }

    public void onClickLogin(){
        db = new DataBaseHelper(this);
        log_in_button=(Button)findViewById(R.id.button13);
        tx1 = (EditText)findViewById(R.id.studentUserId);
        tx2 = (EditText)findViewById(R.id.studentPassword);
        log_in_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String stu_id = tx1.getText().toString();
                        String stu_pass = tx2.getText().toString();
                        Boolean chk_log = db.login(stu_id,stu_pass);
                        if(chk_log==true){
                            Intent intent = new Intent(student0.this,student4.class);
                            intent.putExtra("ID",stu_id);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Wrong ID or Password",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }
}