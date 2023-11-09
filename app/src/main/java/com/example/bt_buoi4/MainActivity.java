package com.example.bt_buoi4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhotoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        List<Photo> photoList = new ArrayList<>(); // Dữ liệu từ API sẽ được đặt vào đây
        adapter = new PhotoAdapter(photoList, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Gọi AsyncTask để lấy dữ liệu từ API và cập nhật RecyclerView sau khi nhận được dữ liệu
        new FetchData(adapter).execute();
    }

}