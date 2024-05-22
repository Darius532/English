package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Создаем список видео
        List<Video> videoList = new ArrayList<>();
        //videoList.add(new Video("С чего начать учить английский сегодня?", "yUwwn_FOfhc"));
        videoList.add(new Video("Мастер-класс по произношению. Звуки [ɪ] и [iː]", "OUXnXIU_E5I?si=LU_ZaHIRRtN2Duej"));
        videoList.add(new Video("Мастер-класс по произношению. Звук [ɜː]", "o-IYl6q9HSM"));
        videoList.add(new Video("Мастер-класс по произношению. Звуки [ʊ] и [uː]", "pMkNfpScGRQ"));
        videoList.add(new Video("Мастер-класс по произношению. Звуки [ɒ] и [ɔː]", "t5Fyxnnu300"));
        videoList.add(new Video("Английский алфавит в жизни и на экзаменах", "y_9YzrgKnt0&list=PLIsliCynILxjPq9uekcn0yrWFXohkE5AW&index=2"));
        videoList.add(new Video("Что такое TO BE в английском?", "juwLaMhsDaM&list=PLIsliCynILxjPq9uekcn0yrWFXohkE5AW&index=3"));
        videoList.add(new Video("Личные местоимения (I, we, you и др.)", "LJRsFmvPfII&list=PLIsliCynILxjPq9uekcn0yrWFXohkE5AW&index=4"));
        videoList.add(new Video("Личные местоимения (me, us, him, her и т.д.)", "r36zsMTbtpc&list=PLIsliCynILxjPq9uekcn0yrWFXohkE5AW&index=5"));
        videoList.add(new Video("Количественные числительные: простые и составные", "9o4lrUgK5sk&list=PLIsliCynILxjPq9uekcn0yrWFXohkE5AW&index=6"));
        videoList.add(new Video("От легкого до более сложного", "jj10mT24X3Y"));

        // Добавьте другие видео по аналогии

        // Находим RecyclerView и настраиваем его
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Создаем адаптер для списка видео
        VideoAdapter adapter = new VideoAdapter(this, videoList);

        // Устанавливаем обработчик кликов на элементах списка
        adapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String videoId) {
                // Обрабатываем выбор видео, например, показываем Toast
                Toast.makeText(ScrollingActivity.this, "Выбрано видео с ID: " + videoId, Toast.LENGTH_SHORT).show();

                // Создаем Intent для запуска VideoActivity
                Intent intent = new Intent(ScrollingActivity.this, VideoActivity.class);
                intent.putExtra("videoId", videoId); // Передаем ID выбранного видео в VideoActivity
                startActivity(intent); // Запускаем VideoActivity
            }
        });

        // Устанавливаем адаптер для RecyclerView
        recyclerView.setAdapter(adapter);
    }
}