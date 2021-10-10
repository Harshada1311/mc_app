package com.example.page1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity1 extends AppCompatActivity {
    private Button btn_student;
    private Button btn_teacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        onClickStudent();
        onClickTeacher();
    }

    public void onClickStudent(){
        btn_student = (Button)findViewById(R.id.button11);
        btn_student.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.example.page1.student0");
                        startActivity(intent);
                    }
                }
        );
    }

    public void onClickTeacher(){
        btn_teacher = (Button)findViewById(R.id.button12);
        btn_teacher.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.example.page1.page2");
                        startActivity(intent);
                    }
                }
        );
    }
}