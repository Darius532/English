package com.example.english;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_views, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_settings) {
        //    return true;
        // }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        // Упражнения да/нет
        if (id == R.id.nav_exerciseYN) {
            Intent intent = new Intent(this, YesNoActivity.class);
            startActivity(intent);
            return true;

            // Упражнения на правописание, вставляем пропущенную букву
        } else if (id == R.id.nav_exercizeWrite) {
            Intent intent = new Intent(this, SpellingTaskActivity.class);
            startActivity(intent);
            return true;

            // Упражнения на сопоставление
        } else if (id == R.id.nav_matching) {
            Intent intent = new Intent(this, MatchingActivity.class);
            startActivity(intent);
            return true;

            // Видео обучение
        } else if (id == R.id.nav_video) {
            Intent intent = new Intent(this, ScrollingActivity.class);
            startActivity(intent);
            return true;

            // Тестирование
        } else if (id == R.id.nav_quiz) {
            Intent intent = new Intent(this, QuizActivity.class);
            startActivity(intent);
            return true;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
