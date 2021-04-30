package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTask;
    private TaskAdapter adapter;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTaskRecycler();
        initFloatingActionButton();
    }

    private void initFloatingActionButton() {
        floatingActionButton = findViewById(R.id.btn_open_add_task);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    private void initTaskRecycler() {
        rvTask = findViewById(R.id.rv_task);
        rvTask.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskAdapter(this);
        rvTask.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK){
            if (data != null) {
                TaskModel model = new TaskModel(data.getStringExtra("title"), data.getStringExtra("description"), data.getStringExtra("date"));
                adapter.addTask(model);
            }
        }
    }
}