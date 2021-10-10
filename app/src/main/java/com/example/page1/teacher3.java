package com.example.page1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class teacher3 extends AppCompatActivity {
    DataBaseHelper db;
    private Button done_btn;
    private Button submit_btn;
    private EditText qno,qsn,A,B,C,D,ans,timer;
    private TextView subname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher3);
        Intent intent = getIntent();
        String qpcode = intent.getStringExtra("SubCode");
        Toast.makeText(getApplicationContext(),qpcode,Toast.LENGTH_SHORT).show();
        subname = (TextView)findViewById(R.id.textView6);
        subname.setText(qpcode);
        db = new DataBaseHelper(this);
        onSubmit();
        onDone();
    }

    public void onDone(){
        done_btn = (Button)findViewById(R.id.button14);
        done_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent("com.example.page1.teacher1");
                        startActivity(intent);
                    }
                }
        );
    }

    public void onSubmit() {
        submit_btn = (Button) findViewById(R.id.button6);
        qno = (EditText) findViewById(R.id.set_qno);
        qsn = (EditText) findViewById(R.id.question);
        A = (EditText) findViewById(R.id.optionA);
        B = (EditText) findViewById(R.id.optionB);
        C = (EditText) findViewById(R.id.optionC);
        D = (EditText) findViewById(R.id.optionD);
        ans = (EditText) findViewById(R.id.Answer);
        timer = (EditText) findViewById(R.id.timer_seconds);
        subname=(TextView)findViewById(R.id.textView6);
        submit_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String qpc = subname.getText().toString();
                        String qnum = qno.getText().toString();
                        String qest = qsn.getText().toString();
                        String optA = A.getText().toString();
                        String optB = B.getText().toString();
                        String optC = C.getText().toString();
                        String optD = D.getText().toString();
                        String qans = ans.getText().toString();
                        String qtime = timer.getText().toString();
                        if (qnum.equals("") || qest.equals("") || optA.equals("") || optB.equals("") || optC.equals("") || optD.equals("") ||
                                qans.equals("") || qtime.equals("")) {
                            Toast.makeText(getApplicationContext(), "Please fill all details", Toast.LENGTH_LONG).show();
                        }
                        else if (parseInt(qtime)<10){
                            Toast.makeText(getApplicationContext(),"Minimum 10 seconds timer ",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Boolean ch = db.add_qsn(qpc, qnum, qest, optA, optB, optC, optD, qans, qtime);
                            if (!ch){
                                Toast.makeText(getApplicationContext(),"Something Wrong , Try Again",Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Inserted Successfully ",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                }
        );
    }
}