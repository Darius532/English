package com.example.english;



import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Инициализация элементов интерфейса
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        // Создание экземпляра DatabaseHelper
        final DatabaseHelper dbHelper = new DatabaseHelper(this);

        // Обработчик нажатия на кнопку "Вход"
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Проверка на пустые поля
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                } else {
                    // Регистрация нового пользователя
                    boolean isLogin = dbHelper.loginUser(username, password);

                    if (isLogin) {
                        // Вход успешен
                        Toast.makeText(LoginActivity.this, "Вход выполнен успешно", Toast.LENGTH_SHORT).show();
                        // Переход к  главному экрану приложения
                        startActivity(new Intent(LoginActivity.this, NavigationActivity.class));

                    } else {
                        // Ошибка при входе
                        Toast.makeText(LoginActivity.this, "Ошибка. Вход не выполнен", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                        finish();
                    }
                }
            }
        });
    }
}
