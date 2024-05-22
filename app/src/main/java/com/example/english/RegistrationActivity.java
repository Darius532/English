package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText editTextUsername;
        EditText editTextPassword;
        Button buttonRegister;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

            // Инициализация элементов интерфейса
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

            // Создание экземпляра DatabaseHelper
       final DatabaseHelper dbHelper = new DatabaseHelper(this);
            // Обработчик нажатия на кнопку "P"
       buttonRegister.setOnClickListener(new View.OnClickListener() {
       @Override
             public void onClick(View v) {
                    String username = editTextUsername.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();

                    // Проверка на пустые поля
                    if (username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(RegistrationActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                    } else {
                        // Регистрация нового пользователя
                        boolean isRegistered = dbHelper.registerUser(username, password);

                        if (isRegistered) {
                            // Вход успешен
                            Toast.makeText(RegistrationActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                            // Переход к  главному экрану приложения
                            startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            // Ошибка при регистрации
                            Toast.makeText(RegistrationActivity.this, "Ошибка при регистрации", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }); // Добавлено закрытие скобки для метода onClick()
        }
    }
