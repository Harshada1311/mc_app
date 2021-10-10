package com.example.page1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

public class student2 extends AppCompatActivity {
    private Button done_btn;
    private Button submit_btn;
    private EditText qno,qsn,A,B,C,D,ans,timer;
    private int score = 0;
    private boolean answered;
    private int questionCount;
    private int totalQsn;
    private static long Time_in_milliseconds;
    private CountDownTimer countDownTimer;
    private long time_left_in_milliseconds;
    private Questions currentQuestion;
    private List<Questions> questionsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student2);
        Intent intent = getIntent();
        String qpcode = intent.getStringExtra("SC");
        String st_id = intent.getStringExtra("ID");
        qno = (EditText) findViewById(R.id.qno_s);
        qsn = (EditText) findViewById(R.id.qsn_s);
        A = (EditText) findViewById(R.id.optA_s);
        B = (EditText) findViewById(R.id.optB_s);
        C = (EditText) findViewById(R.id.optC_s);
        D = (EditText) findViewById(R.id.optD_s);
        ans = (EditText) findViewById(R.id.stu_ans);
        timer = (EditText) findViewById(R.id.qtime_s);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        questionsList = dataBaseHelper.getQsn(qpcode);
        showNextQuestion();
        onDone(st_id,qpcode);
        submit_btn = (Button) findViewById(R.id.button10);
        submit_btn.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            checkAnswer();
                            showNextQuestion();
                        }
                    }
            );
    }

    private void showNextQuestion(){
        //ans.setText("");
        totalQsn = questionsList.size();
        if (questionCount < totalQsn){
            currentQuestion = questionsList.get(questionCount);
            qno.setText(currentQuestion.getQno());
            qsn.setText(currentQuestion.getQsn());
            A.setText(currentQuestion.getOptA());
            B.setText(currentQuestion.getOptB());
            C.setText(currentQuestion.getOptC());
            D.setText(currentQuestion.getOptD());
            timer.setText(currentQuestion.getTimer());
            Time_in_milliseconds = (Long.parseLong(timer.getText().toString()))*1000;
            time_left_in_milliseconds = Time_in_milliseconds;
            startCountDown();
            answered=false;
            questionCount++;
        }
        else {
            Toast.makeText(getApplicationContext(),"No more questions , Click Done",Toast.LENGTH_SHORT).show();
        }
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(time_left_in_milliseconds,1000) {
            @Override
            public void onTick(long l) {
                time_left_in_milliseconds = l;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                time_left_in_milliseconds = 0;
                updateTimerText();
                ans.setText("");
                Toast.makeText(getApplicationContext(),"No more time left , click submit",Toast.LENGTH_LONG).show();
                checkAnswer();
            }
        }.start();
    }

    private void updateTimerText(){
        int seconds = (int) (time_left_in_milliseconds/1000);

        String time_format = String.format(Locale.getDefault(),"%d",seconds);
        timer.setText(time_format);
    }

    private void checkAnswer(){
        answered = true;

        countDownTimer.cancel();
        if (ans.getText().toString().equals(currentQuestion.getAns())){
            score++;
        }
    }

    protected void onDestroy(){
        super.onDestroy();
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    /*public void Question(String code,String qn){
        do {
            qno = (EditText) findViewById(R.id.qno_s);
            qsn = (EditText) findViewById(R.id.qsn_s);
            A = (EditText) findViewById(R.id.optA_s);
            B = (EditText) findViewById(R.id.optB_s);
            C = (EditText) findViewById(R.id.optC_s);
            D = (EditText) findViewById(R.id.optD_s);
            ans = (EditText) findViewById(R.id.stu_ans);
            timer = (EditText) findViewById(R.id.qtime_s);
            if (qn.equals("1")) res.moveToFirst();
            else res.moveToNext();
            qno.setText(res.getString(0));
            qsn.setText(res.getString(1));
            A.setText(res.getString(2));
            B.setText(res.getString(3));
            C.setText(res.getString(4));
            D.setText(res.getString(5));
            timer.setText(res.getString(7));
            answer = res.getString(6);
        }
        while (res.moveToNext());
        Toast.makeText(getApplicationContext(),"No more questions , click done",Toast.LENGTH_LONG).show();
    }*/

    public void onDone(final String st_id,final String qpc){
        done_btn=(Button)findViewById(R.id.button15);
        final DataBaseHelper dataBaseHelper = new DataBaseHelper(this);
        done_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Boolean r = dataBaseHelper.addResult(st_id,qpc,score);
                        if (r){
                            Intent intent = new Intent(student2.this,student4.class);
                            intent.putExtra("ID",st_id);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
}