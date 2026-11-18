package com.example.iamcooked;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class monitoradaugare extends AppCompatActivity {

    private EditText etnume;
    private EditText etmarime;

    private Button butonsalveaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_monitoradaugare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etnume = findViewById(R.id.et_nume);
        etmarime = findViewById(R.id.marime);
        butonsalveaza = findViewById(R.id.btnsalv);
        butonsalveaza.setOnClickListener(v ->
        {
            String nume = etnume.toString().trim();
            int marime = Integer.parseInt(etmarime.toString().trim());
            monitor monitor = new monitor(marime, nume);

            Intent intent = new Intent();

            Bundle bundle = new Bundle();
            bundle.putParcelable("monitor nou", monitor);
            intent.putExtras(bundle);
            setResult(RESULT_OK, intent);
            finish();
        });

    }

}