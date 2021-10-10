package com.example.page1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class teacher2 extends AppCompatActivity {
    private EditText code;
    private Button set_qsn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher2);
        onSetQsn();
    }

    public void onSetQsn(){
        code=(EditText)findViewById(R.id.sub_code_text);
        set_qsn = (Button)findViewById(R.id.button5);
        set_qsn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String sub_code = code.getText().toString();
                        if (sub_code.equals(" ")){
                            Toast.makeText(getApplicationContext(),"Please enter subject code",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent intent = new Intent(teacher2.this,teacher3.class);
                            intent.putExtra("SubCode",sub_code);
                            startActivity(intent);
                        }
                    }
                }
        );
    }
}