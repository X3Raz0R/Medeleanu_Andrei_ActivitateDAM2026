package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddMagazinActivity extends AppCompatActivity {

    // Layout-ul activitatii; suprascris in AddMagazinAIActivity pentru varianta cu design AI
    protected int getLayoutId() {
        return R.layout.activity_add_magazin;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(getLayoutId());

        int padding = findViewById(R.id.main).getPaddingLeft();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + padding, systemBars.top + padding,
                    systemBars.right + padding, systemBars.bottom + padding);
            return insets;
        });

        EditText etNume = findViewById(R.id.etNume);
        EditText etAdresa = findViewById(R.id.etAdresa);
        RadioGroup rgTip = findViewById(R.id.rgTip);
        Spinner spinnerCategorie = findViewById(R.id.spinnerCategorie);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        Switch switchLivrare = findViewById(R.id.switchLivrare);
        CheckBox cbWeekend = findViewById(R.id.cbWeekend);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        String[] categorii = {"Alimentar", "Haine", "Electronice", "Cosmetice", "Altele"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorii);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorie.setAdapter(adapter);

        btnSalveaza.setOnClickListener(v -> {
            String nume = etNume.getText().toString().trim();
            String adresa = etAdresa.getText().toString().trim();

            if (nume.isEmpty() || adresa.isEmpty()) {
                Toast.makeText(this, "Completați toate câmpurile obligatorii!", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedRbId = rgTip.getCheckedRadioButtonId();
            RadioButton rbSelected = findViewById(selectedRbId);
            String tip = rbSelected != null ? rbSelected.getText().toString() : "Supermarket";

            String categorie = spinnerCategorie.getSelectedItem().toString();
            float rating = ratingBar.getRating();
            boolean livrare = switchLivrare.isChecked();
            boolean deschisWeekend = cbWeekend.isChecked();

            Magazin magazin = new Magazin(nume, adresa, tip, categorie, rating, livrare, deschisWeekend);

            Intent intent = new Intent();
            intent.putExtra("MAGAZIN_EXTRA", magazin);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
