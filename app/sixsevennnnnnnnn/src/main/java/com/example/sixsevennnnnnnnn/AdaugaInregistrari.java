package com.example.sixsevennnnnnnnn;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaInregistrari extends AppCompatActivity {

    private EditText etDestinatie;
    private SeekBar sbPret;
    private RadioGroup rgAnotimp;
    private RatingBar rbRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adauga_inregistrari);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etDestinatie = findViewById(R.id.et_destinatie);
        sbPret = findViewById(R.id.sb_pret);
        rgAnotimp = findViewById(R.id.rg_anotimp);
        rbRating = findViewById(R.id.rb_rating);
        Button btnSalveaza = findViewById(R.id.btn_salveaza);

        btnSalveaza.setOnClickListener(v -> valideaza());
    }

    // Ex 5 - validarea informatiilor din formular
    private boolean valideaza() {
        String destinatie = etDestinatie.getText().toString().trim();
        if (destinatie.isEmpty()) {
            Toast.makeText(this, "Introduceti destinatia!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (destinatie.length() < 3) {
            Toast.makeText(this, "Destinatia trebuie sa aiba minim 3 caractere!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sbPret.getProgress() == 0) {
            Toast.makeText(this, "Pretul trebuie sa fie mai mare ca 0!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (rgAnotimp.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Selectati un anotimp!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (rbRating.getRating() == 0) {
            Toast.makeText(this, "Acordati un rating!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
