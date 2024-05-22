package com.example.english;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);

        TextView textViewResults = findViewById(R.id.textViewResults);
        Button buttonRetry = findViewById(R.id.buttonRetry);
        Button buttonExit = findViewById(R.id.buttonExit);

        int score = getIntent().getIntExtra("score", 0);
        int totalQuestions = getIntent().getIntExtra("totalQuestions", 0);

        textViewResults.setText(String.format("Правильных ответов: %d из %d", score, totalQuestions));

        if (score < (totalQuestions * 70 / 100)) {
            buttonRetry.setVisibility(View.VISIBLE);
            buttonRetry.setOnClickListener(v -> {
                Intent intent = new Intent(ResultsActivity.this, YesNoActivity.class);
                startActivity(intent);
                finish(); // Закрываем текущую активити
            });
        } else {
            buttonRetry.setVisibility(View.GONE); // Скрываем кнопку, если нет необходимости в повторе
        }

        buttonExit.setOnClickListener(v -> finish());
    }
}