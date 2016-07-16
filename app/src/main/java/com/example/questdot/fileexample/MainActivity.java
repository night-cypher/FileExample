package com.example.questdot.fileexample;

import android.os.Environment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private TextView textView;
    private Button btnWrite,btnRead;
    String directory =  Environment.getExternalStorageDirectory().getAbsolutePath() + "/newDir";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWrite=(Button)findViewById(R.id.btnWrite);
        btnRead=(Button)findViewById(R.id.btnRead);
        editText=(EditText) findViewById(R.id.editText);
        textView=(TextView) findViewById(R.id.textView);

        btnRead.setOnClickListener(this);
        btnWrite.setOnClickListener(this);

        File dir = new File(directory);
        dir.mkdirs();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {

            case R.id.btnWrite:
                File fileWrite = new File(directory+"/test1.txt");
                String saveText = editText.getText().toString();

                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWrite));
                    bufferedWriter.write(saveText);
                    bufferedWriter.close();
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;

            case R.id.btnRead:

                File fileRead = new File(directory+"/test1.txt");
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileRead));
                    String read;
                    StringBuilder builder = new StringBuilder("");

                    while ((read = bufferedReader.readLine()) != null) {
                        builder.append(read);
                    }
                   textView.setText(builder.toString());
                    bufferedReader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                break;



        }
    }



}
