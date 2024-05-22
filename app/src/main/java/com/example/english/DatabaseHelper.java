package com.example.english;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "english_learning.db";
    private static final int DATABASE_VERSION = 1;

    // Таблица пользователей
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Создание таблицы пользователей
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }
    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Проверка, что пользователь с таким именем уже не существует
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ID}, COLUMN_USERNAME + "=?",
                new String[]{username}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return false; // Пользователь уже существует
        }

        // Если пользователь с таким именем не существует, добавляем его в базу данных
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);

        // Вставляем новую запись в таблицу пользователей
        long newRowId = db.insert(TABLE_USERS, null, values);

        // Закрываем соединение с базой данных
        db.close();

        // Проверяем, что вставка прошла успешно
        return newRowId != -1;
    }
    public boolean loginUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Проверка, что пользователь с таким именем существует
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_ID, COLUMN_PASSWORD}, COLUMN_USERNAME + "=?",
                new String[]{username}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            // Пользователь существует, проверяем пароль
            cursor.moveToFirst();
            @SuppressLint("Range") String storedPassword = cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD));
            cursor.close();
            // Закрываем соединение с базой данных
            db.close();

            // Сравниваем введенный пароль с паролем пользователя в базе данных
            return password.equals(storedPassword);
        } else {
            // Пользователь с указанным именем не найден
            if (cursor != null) {
                cursor.close();
            }
            // Закрываем соединение с базой данных
            db.close();
            return false;
        }
    }
}
