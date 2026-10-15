package com.example.laborator2;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Seminar", "onStart - Error log");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w("Seminar", "onRestart - Warning log");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Seminar", "onResume - Info log");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Seminar", "onPause - Debug log");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("Seminar", "onStop - Verbose log");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Seminar", "onDestroy - Error log");
    }
}
