package com.example.page1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class display_result extends AppCompatActivity {
    private TextView tx9;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        Intent intent = getIntent();
        String var = intent.getStringExtra("VAR");
        int sel = parseInt(intent.getStringExtra("SEL"));
        tx9=(TextView)findViewById(R.id.textView9);
        dataBaseHelper = new DataBaseHelper(this);

        Cursor cursor = dataBaseHelper.getResult(var,sel);

        if (cursor.moveToFirst()){
            StringBuilder op = new StringBuilder();
            if (sel==0){
                op.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tStudent Id\t\t\t\t\t\t\tMarks\n");
                do {
                    op.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+cursor.getString(0)+"\t\t\t\t\t\t\t\t\t\t"+cursor.getInt(1)+"\n");
                }while (cursor.moveToNext());

                tx9.setText(op.toString());
            }
            else {
                op.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSubject \t\t\t\tMarks\n");
                do {
                    op.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+cursor.getString(0)+"\t\t\t\t\t\t\t\t\t\t"+cursor.getInt(1)+"\n");
                }while (cursor.moveToNext());

                tx9.setText(op.toString());
            }
        }
        else {
            tx9.setText("NO DATA AVAILABLE");
        }
    }
}