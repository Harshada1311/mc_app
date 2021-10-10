package com.example.page1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class teacher1 extends AppCompatActivity {
    private Button log_out_button;
    private Button set_qsn_button;
    private Button check_result;
    private EditText sbcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher1);
        onClickLogOut();
        onSetQuestion();
        onCheckResult();
    }

    public void onClickLogOut(){
        log_out_button = (Button)findViewById(R.id.button4);
        log_out_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.example.page1.student0");
                        startActivity(intent);
                    }
                }
        );
    }

    public void onSetQuestion(){
        set_qsn_button = (Button)findViewById(R.id.button2);
        set_qsn_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent2 = new Intent("com.example.page1.teacher2");
                        startActivity(intent2);
                    }
                }
        );
    }

    public void onCheckResult(){
        sbcode=(EditText)findViewById(R.id.editsubcode);
        check_result=(Button)findViewById(R.id.button3);
        check_result.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (sbcode.getText().toString().equals("")){
                            Toast.makeText(getApplicationContext(),"Please enter code",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(teacher1.this, display_result.class);
                            intent.putExtra("VAR", sbcode.getText().toString());
                            intent.putExtra("SEL", "0");
                            startActivity(intent);
                        }
                    }
                }
        );
    }
}