package com.example.kotlinbasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView rvstudent;
    DatabaseHelper databaseHelper;
    List<StudentModel> studentList;
    EditText name,deleteid,updateid,updatename;
    Button insert,updatebutton,delete;
    String update_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        rvstudent =(RecyclerView)findViewById(R.id.rvstudent);
        rvstudent.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        studentList =new ArrayList<>();
        databaseHelper =new DatabaseHelper(this);
        studentList=databaseHelper.getAllStudentData();
        StudentCustomAdapter adapter = new StudentCustomAdapter(studentList);
        rvstudent.setAdapter(adapter);


        name =(EditText) findViewById(R.id.edittextname);
        deleteid =(EditText) findViewById(R.id.edittextdelete);
        updateid =(EditText) findViewById(R.id.edittextupdatebyid);
        updatename =(EditText) findViewById(R.id.edittextupdate);

        insert =(Button) findViewById(R.id.insert_button);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    if(name.getText().toString().trim().length() > 0)
                    {
                        boolean isinserted = databaseHelper.insertdata(name.getText().toString());

                        if(isinserted == true)
                        {
                            Toast.makeText(getApplicationContext(), "Inserted", Toast.LENGTH_SHORT).show();
                            name.setText("");
                            studentList =new ArrayList<>();
                            databaseHelper =new DatabaseHelper(getApplicationContext());
                            studentList=databaseHelper.getAllStudentData();
                            StudentCustomAdapter adapter = new StudentCustomAdapter(studentList);
                            rvstudent.setAdapter(adapter);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Not Inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter a name", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "Exception : "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete = (Button)findViewById(R.id.delete_button);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    if(deleteid.getText().toString().trim().length() > 0)
                    {
                        databaseHelper.deleterecord(deleteid.getText().toString());
                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                        deleteid.setText("");

                        studentList =new ArrayList<>();
                        databaseHelper =new DatabaseHelper(getApplicationContext());
                        studentList=databaseHelper.getAllStudentData();
                        StudentCustomAdapter adapter = new StudentCustomAdapter(studentList);
                        rvstudent.setAdapter(adapter);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please Enter ID", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "Exception : "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
        updatebutton = (Button)findViewById(R.id.update_button);
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    if(updateid.getText().toString().trim().length() > 0)
                    {
                        if(updatename.getText().toString().trim().length() > 0)
                        {
                            databaseHelper.updaterecord(updateid.getText().toString(),updatename.getText().toString());
                            updateid.setText("");
                            updatename.setText("");
                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();

                            studentList =new ArrayList<>();
                            databaseHelper =new DatabaseHelper(getApplicationContext());
                            studentList=databaseHelper.getAllStudentData();
                            StudentCustomAdapter adapter = new StudentCustomAdapter(studentList);
                            rvstudent.setAdapter(adapter);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Enter a Name", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Enter a ID", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(), "Exception : "+ex, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}