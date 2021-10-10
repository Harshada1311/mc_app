package com.example.page1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class student4 extends Activity {
    private Button log_out;
    private Button start_test;
    private Button view_result;
    private EditText sub;
    private TextView uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student4);
        Intent intent2 = getIntent();
        String stu_id = intent2.getStringExtra("ID");
        uid=(TextView)findViewById(R.id.textView7);
        uid.setText(stu_id);
        onLogOut();
        onStartTest();
        onViewResult();
    }

    public void onLogOut(){
        log_out=(Button)findViewById(R.id.button9);
        log_out.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.example.page1.page2");
                        startActivity(intent);
                    }
                }
        );
    }

    public void onStartTest(){
        final DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        sub=(EditText)findViewById(R.id.editSubCode);
        start_test=(Button)findViewById(R.id.button7);
        start_test.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String scode=sub.getText().toString();
                        String stu_id=uid.getText().toString();
                        if (scode.equals("")){
                            Toast.makeText(getApplicationContext(),"Please enter subject code",Toast.LENGTH_SHORT).show();
                        }
                        else if (!dataBaseHelper.checkQsn(scode)){
                            Toast.makeText(getApplicationContext(),"No questions available , check code ",Toast.LENGTH_LONG).show();
                        }
                        else if (dataBaseHelper.chk_attempt(stu_id,scode)){
                            Toast.makeText(getApplicationContext(),"Already attempted ",Toast.LENGTH_LONG).show();
                        }
                        else{
                            Intent intent = new Intent(student4.this,student2.class);
                            intent.putExtra("SC",scode);
                            intent.putExtra("ID",stu_id);
                            startActivity(intent);
                        }
                    }
                }
        );
    }

    public void onViewResult(){
        view_result=(Button)findViewById(R.id.button8);
        view_result.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(student4.this,display_result.class);
                        intent.putExtra("VAR",uid.getText().toString());
                        intent.putExtra("SEL","1");
                        startActivity(intent);
                    }
                }
        );
    }
}
