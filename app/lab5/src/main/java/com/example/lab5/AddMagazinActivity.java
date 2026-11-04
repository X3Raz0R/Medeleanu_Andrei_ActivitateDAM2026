package com.example.lab5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class AddMagazinActivity extends AppCompatActivity {

    protected int getLayoutId() {
        return R.layout.activity_add_magazin;
    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(getLayoutId());

        int padding = findViewById(R.id.main).getPaddingLeft();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left + padding, systemBars.top + padding,
                        systemBars.right + padding, systemBars.bottom + padding);
                return insets;
            }
        });

        EditText etNume = findViewById(R.id.etNume);
        EditText etAdresa = findViewById(R.id.etAdresa);
        RadioGroup rgTip = findViewById(R.id.rgTip);
        Spinner spinnerCategorie = findViewById(R.id.spinnerCategorie);
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        Switch switchLivrare = findViewById(R.id.switchLivrare);
        CheckBox cbWeekend = findViewById(R.id.cbWeekend);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        String[] categorii = getResources().getStringArray(R.array.categorii);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categorii);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorie.setAdapter(adapter);

        Intent it = getIntent();
        if (it.hasExtra("MAGAZIN_EXTRA")) {
            Magazin magazin = it.getParcelableExtra("MAGAZIN_EXTRA");
            if (magazin != null) {
                etNume.setText(magazin.getNume());
                etAdresa.setText(magazin.getAdresa());

                if (magazin.getTip().equals(getString(R.string.tip_boutique))) {
                    rgTip.check(R.id.rbBoutique);
                } else {
                    rgTip.check(R.id.rbSupermarket);
                }

                int pozitieCategorie = Arrays.asList(categorii).indexOf(magazin.getCategorie());
                spinnerCategorie.setSelection(pozitieCategorie);

                ratingBar.setRating(magazin.getRating());
                switchLivrare.setChecked(magazin.isLivrare());
                cbWeekend.setChecked(magazin.isDeschisWeekend());
            }
        }

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nume = etNume.getText().toString().trim();
                String adresa = etAdresa.getText().toString().trim();

                if (nume.isEmpty() || adresa.isEmpty()) {
                    Toast.makeText(AddMagazinActivity.this, R.string.campuri_obligatorii, Toast.LENGTH_SHORT).show();
                    return;
                }

                int selectedRbId = rgTip.getCheckedRadioButtonId();
                RadioButton rbSelected = findViewById(selectedRbId);
                String tip = rbSelected != null ? rbSelected.getText().toString() : getString(R.string.tip_supermarket);

                String categorie = spinnerCategorie.getSelectedItem().toString();
                float rating = ratingBar.getRating();
                boolean livrare = switchLivrare.isChecked();
                boolean deschisWeekend = cbWeekend.isChecked();

                Magazin magazin = new Magazin(nume, adresa, tip, categorie, rating, livrare, deschisWeekend);

                Intent intent = new Intent();
                intent.putExtra("MAGAZIN_EXTRA", magazin);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
