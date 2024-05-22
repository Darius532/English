package com.example.english;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;



public class MatchingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PairAdapter pairAdapter;
    private List<Pair> pairs;

    private static final int N = 70; // 70%

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pairs = new ArrayList<>();
        // Животные
        pairs.add(new Pair("rabbit", "кролик", new String[]{"кролик", "хорек", "удав"}));
        pairs.add(new Pair("ferret", "хорек", new String[]{"кролик", "хорек", "удав"}));
        pairs.add(new Pair("boa", "удав", new String[]{"кролик", "хорек", "удав"}));

        // Дни недели
        pairs.add(new Pair("Monday", "понедельник", new String[]{"понедельник", "вторник", "среда"}));
        pairs.add(new Pair("Tuesday", "вторник", new String[]{"понедельник", "вторник", "среда"}));
        pairs.add(new Pair("Wednesday", "среда", new String[]{"понедельник", "вторник", "среда"}));
        pairs.add(new Pair("Thursday", "четверг", new String[]{"четверг", "пятница", "суббота"}));
        pairs.add(new Pair("Friday", "пятница", new String[]{"четверг", "пятница", "суббота"}));
        pairs.add(new Pair("Saturday", "суббота", new String[]{"четверг", "пятница", "суббота"}));
        pairs.add(new Pair("Sunday", "воскресенье", new String[]{"воскресенье", "пятница", "суббота"}));

        // Школьные принадлежности
        pairs.add(new Pair("pencil", "карандаш", new String[]{"карандаш", "ручка", "ластик"}));
        pairs.add(new Pair("pen", "ручка", new String[]{"карандаш", "ручка", "ластик"}));
        pairs.add(new Pair("eraser", "ластик", new String[]{"карандаш", "ручка", "ластик"}));
        pairs.add(new Pair("notebook", "тетрадь", new String[]{"тетрадь", "учебник", "портфель"}));
        pairs.add(new Pair("textbook", "учебник", new String[]{"тетрадь", "учебник", "портфель"}));
        pairs.add(new Pair("backpack", "портфель", new String[]{"тетрадь", "учебник", "портфель"}));

        pairAdapter = new PairAdapter(pairs);
        recyclerView.setAdapter(pairAdapter);

        findViewById(R.id.submitButton).setOnClickListener(v -> checkAnswers());
    }

    private void checkAnswers() {
        int correctAnswers = 0;
        for (Pair pair : pairs) {
            if (pair.isCorrect()) {
                correctAnswers++;
            }
        }

        int score = (correctAnswers * 100) / pairs.size();
        if (score >= N) {
            Toast.makeText(this, "Вы прошли курс! Правильные ответы: " + score + "%", Toast.LENGTH_LONG).show();
            finish(); // Закрытие активити после успешного прохождения
        } else {
            Toast.makeText(this, "Вы не прошли курс. Попробуйте снова. Правильные ответы: " + score + "%", Toast.LENGTH_LONG).show();
            pairAdapter.notifyDataSetChanged(); // Reset for retry
        }
    }
}