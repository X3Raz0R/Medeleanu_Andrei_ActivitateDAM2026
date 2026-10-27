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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String LISTA_MAGAZINE = "LISTA_MAGAZINE";

    private final ArrayList<Magazin> listaMagazine = new ArrayList<>();

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
                            listaMagazine.add(magazin);

                            String info = getString(R.string.magazin_info,
                                    magazin.getNume(),
                                    magazin.getAdresa(),
                                    magazin.getTip(),
                                    magazin.getCategorie(),
                                    magazin.getRating(),
                                    getString(magazin.isLivrare() ? R.string.da : R.string.nu),
                                    getString(magazin.isDeschisWeekend() ? R.string.da : R.string.nu));
                            tvResult.setText(info);
                            Toast.makeText(this, getString(R.string.magazine_in_lista, listaMagazine.size()),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        // Lista poate fi modificata (stergeri) in ListaMagazineActivity, asa ca o preluam inapoi
        ActivityResultLauncher<Intent> listaLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        @SuppressWarnings("unchecked")
                        ArrayList<Magazin> listaActualizata =
                                (ArrayList<Magazin>) result.getData().getSerializableExtra(LISTA_MAGAZINE);
                        if (listaActualizata != null) {
                            listaMagazine.clear();
                            listaMagazine.addAll(listaActualizata);
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

        Button btnVeziLista = findViewById(R.id.btnVeziLista);
        btnVeziLista.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListaMagazineActivity.class);
            intent.putExtra(LISTA_MAGAZINE, listaMagazine);
            listaLauncher.launch(intent);
        });
    }
}
