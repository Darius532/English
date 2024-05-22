package com.example.english;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private RadioGroup radioGroupOptions;
    private Button buttonSubmit;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private int incorrectAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Инициализация списка вопросов
        initializeQuestions();

        // Находим все элементы управления по их идентификаторам
        textViewQuestion = findViewById(R.id.textViewQuestion);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Отображаем первый вопрос
        displayQuestion(currentQuestionIndex);

        // Установка слушателя на кнопку "Проверить ответ"
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Проверяем выбранный ответ
                int selectedRadioButtonId = radioGroupOptions.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

                if (selectedRadioButton != null) {
                    int selectedOptionIndex = radioGroupOptions.indexOfChild(selectedRadioButton);

                    // Проверяем ответ
                    if (selectedOptionIndex == questionList.get(currentQuestionIndex).getCorrectOptionIndex()) {
                        correctAnswers++;
                    } else {
                        incorrectAnswers++;
                    }

                    // Переходим к следующему вопросу или завершаем тест
                    currentQuestionIndex++;
                    if (currentQuestionIndex < questionList.size()) {
                        displayQuestion(currentQuestionIndex);
                        radioGroupOptions.clearCheck(); // Сбрасываем выбор
                    } else {
                        // Все вопросы пройдены - показываем результаты
                        showResults();
                    }
                } else {
                    Toast.makeText(QuizActivity.this, "Пожалуйста, выберите вариант ответа.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeQuestions() {
        // Инициализация списка вопросов
        questionList = new ArrayList<>();

        // Добавление вопросов в список
        questionList.add(new Question("I live ... London. - Я живу в Лондоне.", new String[]{"to", "in", "at"}, 1));
        questionList.add(new Question("I speak ... English. - Я говорю по-английски.", new String[]{"in", "on", "-"}, 2));
        questionList.add(new Question("He ... dance. - Он не танцует.", new String[]{"don't", "doesn't", "not"}, 1));
        questionList.add(new Question("... you sleep? - Ты спишь?", new String[]{"Did", "Are", "Do"}, 2));
        questionList.add(new Question("Give ... an apple, please. - Дай мне пожалуйста яблоко.", new String[]{"my", "me", "mine"}, 1));
        questionList.add(new Question("... do you live? - Где ты живёшь?", new String[]{"Where", "What", "When"}, 0));
        questionList.add(new Question("She needs ... water. - Ей надо немного воды.", new String[]{"some", "any", "many"}, 0));
        questionList.add(new Question("I have 5 ... . - У меня есть 5 ящиков.", new String[]{"box", "boxes", "boxs"}, 1));
        questionList.add(new Question("Maria ... him yesterday. - Мария видела его вчера", new String[]{"see", "seed", "saw"}, 2));
        questionList.add(new Question("Do you believe ... ? — Ты веришь им?", new String[]{"him", "them", "us"}, 1));

    }

    private void displayQuestion(int index) {
        // Отображение вопроса и вариантов ответов
        Question question = questionList.get(index);
        textViewQuestion.setText(question.getQuestionText());

        // Установка вариантов ответов в RadioButton
        for (int i = 0; i < question.getOptions().length; i++) {
            RadioButton radioButton = (RadioButton) radioGroupOptions.getChildAt(i);
            radioButton.setText(question.getOptions()[i]);
        }
    }

    private void showResults() {
        // Вычисление процента правильных ответов
        int totalQuestions = questionList.size();
        double percentage = (correctAnswers * 100.0) / totalQuestions;

        // Формирование сообщения с результатами
        String resultMessage = "Тест завершен!\n\n";
        resultMessage += "Правильные ответы: " + correctAnswers + "\n";
        resultMessage += "Неправильные ответы: " + incorrectAnswers + "\n";
        resultMessage += "Процент: " + String.format("%.2f", percentage) + "%";

        // Отображение диалогового окна с результатами
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Результаты теста");
        builder.setMessage(resultMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Закрываем активити теста
                finish();
            }
        });
        builder.setCancelable(false); // Запретить закрытие диалогового окна при нажатии вне его

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}