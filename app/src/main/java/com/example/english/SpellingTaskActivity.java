package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;
public class SpellingTaskActivity extends AppCompatActivity {

    private List<String> wordList;
    private List<String> correctAnswers;
    private int currentIndex;
    private int correctAnswersCount;

    private TextView textViewWord;
    private EditText editTextAnswer;
    private TextView textViewResult;
    private Button buttonRestart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spelling_task);

        textViewWord = findViewById(R.id.textViewWord);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        textViewResult = findViewById(R.id.textViewResult);
        buttonRestart = findViewById(R.id.buttonRestart);

        // Инициализация списка слов и правильных ответов
        wordList = new ArrayList<>();
        wordList.add("Bre__d (хлеб)");
        wordList.add("B_tter (масло)");
        wordList.add("e_g (яйцо)");
        wordList.add("c_eese (сыр)");

        correctAnswers = new ArrayList<>();
        correctAnswers.add("a");
        correctAnswers.add("u");
        correctAnswers.add("g");
        correctAnswers.add("h");

        // Начальная установка задания
        setNextWord();

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });
    }

    private void setNextWord() {
        if (currentIndex < wordList.size()) {
            String word = wordList.get(currentIndex);
            textViewWord.setText(word);
            editTextAnswer.setText(""); // Очищаем поле ввода
            textViewResult.setText(""); // Очищаем поле с результатом
            currentIndex++;
        } else {
            // Если закончились слова, показываем результат
            showFinalResult();
        }
    }

    public void checkAnswer() {
        String userAnswer = editTextAnswer.getText().toString().trim();

        if (TextUtils.isEmpty(userAnswer)) {
            Toast.makeText(this, "Введите ответ", Toast.LENGTH_SHORT).show();
            return;
        }

        String currentWord = wordList.get(currentIndex - 1);
        String correctAnswer = correctAnswers.get(currentIndex - 1);

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            textViewResult.setText("Правильно!");
            correctAnswersCount++;
        } else {
            textViewResult.setText("Неправильно. Правильный ответ: " + correctAnswer);
        }

        // Переходим к следующему слову
        // Запускаем задержку 2 секунды перед переходом к следующему слову
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Переходим к следующему слову
                setNextWord();
            }
        }, 2000); // 2000 миллисекунд = 2 секунды
    }

    private void showFinalResult() {
        int totalWords = wordList.size();
        double percentageCorrect = (double) correctAnswersCount / totalWords * 100;

        String resultMessage = "Вы ответили правильно на " + correctAnswersCount + " из " + totalWords + " слов.\n";

        if (percentageCorrect < 70) {
            resultMessage += "Попробуйте пройти этот курс еще раз.";
            buttonRestart.setVisibility(View.VISIBLE); // Показываем кнопку "Повторить курс"
        } else {
            resultMessage += "Поздравляем! Вы успешно прошли этот курс.";
            //Intent intent = new Intent(SpellingTaskActivity.this, YesNoActivity.class);
            //startActivity(intent);
            finish();
        }

        textViewResult.setText(resultMessage);
    }
    public void restartCourse(View view) {
        // Сбросим состояние заданий и начнем курс заново
        currentIndex = 0;
        correctAnswersCount = 0;
        setNextWord();
        buttonRestart.setVisibility(View.GONE); // Скрываем кнопку "Повторить курс"
    }
}