package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

        int padding = findViewById(R.id.main).getPaddingLeft();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left + padding, systemBars.top + padding,
                    systemBars.right + padding, systemBars.bottom + padding);
            return insets;
        });

        Button btnAddMagazin = findViewById(R.id.btnAddMagazin);
        TextView tvResult = findViewById(R.id.tvResult);

        ActivityResultLauncher<Intent> addMagazinLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        Magazin magazin = (Magazin) data.getSerializableExtra("MAGAZIN_EXTRA");
                        if (magazin != null) {
                            String info = getString(R.string.magazin_info,
                                    magazin.getNume(),
                                    magazin.getAdresa(),
                                    magazin.getTip(),
                                    magazin.getCategorie(),
                                    magazin.getRating(),
                                    getString(magazin.isLivrare() ? R.string.da : R.string.nu),
                                    getString(magazin.isDeschisWeekend() ? R.string.da : R.string.nu));

                            tvResult.setText(info);
                            Toast.makeText(this, R.string.magazin_adaugat, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

        btnAddMagazin.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddMagazinActivity.class);
            addMagazinLauncher.launch(intent);
        });

        Button btnAddMagazinAI = findViewById(R.id.btnAddMagazinAI);
        btnAddMagazinAI.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddMagazinAIActivity.class);
            addMagazinLauncher.launch(intent);
        });
    }
}
