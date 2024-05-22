package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class YesNoActivity extends AppCompatActivity {

    private List<QuestionYesNo> questions;
    private int correctAnswers;
    private YesNoPagerAdapter adapter;

    private static final int N = 70; // 70%

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes_no);

        questions = getQuestions();
        correctAnswers = 0;

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        adapter = new YesNoPagerAdapter(this, questions);
        viewPager.setAdapter(adapter);

        adapter.setOnAnswerSelectedListener((question, isCorrect) -> {
            if (isCorrect) {
                correctAnswers++;
            }

            if (viewPager.getCurrentItem() < questions.size() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                showResults();
            }
        });
    }

    private List<QuestionYesNo> getQuestions() {
        List<QuestionYesNo> questions = new ArrayList<>();

        questions.add(new QuestionYesNo("Is long - длинный?", true));
        questions.add(new QuestionYesNo("Is power - простой?", false));
        questions.add(new QuestionYesNo("What? - что?", true));
        questions.add(new QuestionYesNo("How? - как?", true));
        questions.add(new QuestionYesNo("Where - где, куда?", true));
        questions.add(new QuestionYesNo("Which - который?", true));
        questions.add(new QuestionYesNo("When - когда?", true));
        questions.add(new QuestionYesNo("Why - почему?", true));
        questions.add(new QuestionYesNo("Use - использовать?", true));
        questions.add(new QuestionYesNo("Show - показывать?", true));
        questions.add(new QuestionYesNo("Lose - терять?", true));
        questions.add(new QuestionYesNo("Include - включать?", true));
        questions.add(new QuestionYesNo("Grow - встречать?", false));
        questions.add(new QuestionYesNo("Meet - расти?", false));
        questions.add(new QuestionYesNo("Set - устанавливать?", true));

        // Добавьте больше вопросов здесь
        return questions;
    }

    public YesNoPagerAdapter.OnAnswerSelectedListener getOnAnswerSelectedListener() {
        return adapter.getOnAnswerSelectedListener();
    }

    private void showResults() {
        int score = correctAnswers;
        int totalQuestions = questions.size();
        Intent intent = new Intent(YesNoActivity.this, ResultsActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", totalQuestions);
        startActivity(intent);
        finish(); // Закрываем текущую активити
    }
}