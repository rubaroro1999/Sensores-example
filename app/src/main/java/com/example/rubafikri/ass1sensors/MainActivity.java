package com.example.rubafikri.ass1sensors;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    Button btn1;
    Button btn2;
    EditText ed;
    TextView tv;
    ArrayList<String> q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tv = findViewById(R.id.tv2);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        ed  = findViewById(R.id.ed);
        q = new ArrayList<>();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed.getText().toString().trim().length() == 0){
                    Toast.makeText(MainActivity.this, "قم بكتابة سؤال في الصندوق الاعلى", Toast.LENGTH_SHORT).show();
                }else {
                    String qq = ed.getText().toString();
                    q.add(qq);
                    tv.setText(q.size()+"");
                    ed.setText("");

                }


            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q.size() < 2){
                    Toast.makeText(MainActivity.this, "يجب ادخال سؤالين او اكثر", Toast.LENGTH_SHORT).show();
                }else{
                    Intent i = new Intent(getApplicationContext(),Main2Activity.class);
                    i.putExtra("questions",q);
                    startActivity(i);
                }
            }
        });



    }



}
