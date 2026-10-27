package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListaMagazineActivity extends AppCompatActivity {

    private ArrayList<Magazin> listaMagazine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_magazine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        @SuppressWarnings("unchecked")
        ArrayList<Magazin> primita =
                (ArrayList<Magazin>) getIntent().getSerializableExtra(MainActivity.LISTA_MAGAZINE);
        listaMagazine = primita != null ? primita : new ArrayList<>();

        // ArrayAdapter afiseaza fiecare obiect folosind metoda toString() din Magazin
        ListView lvMagazine = findViewById(R.id.lvMagazine);
        ArrayAdapter<Magazin> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, listaMagazine);
        lvMagazine.setAdapter(adapter);

        // Click: afisam obiectul selectat intr-un Toast
        lvMagazine.setOnItemClickListener((parent, view, position, id) -> {
            Magazin magazin = listaMagazine.get(position);
            Toast.makeText(this, magazin.toString(), Toast.LENGTH_LONG).show();
        });

        // Long click: stergem obiectul din lista si din ListView
        lvMagazine.setOnItemLongClickListener((parent, view, position, id) -> {
            Magazin sters = listaMagazine.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, getString(R.string.magazin_sters, sters.getNume()),
                    Toast.LENGTH_SHORT).show();
            trimiteListaInapoi();
            return true;
        });
    }

    // Lista primita prin Intent este o copie, deci o trimitem inapoi in MainActivity
    // ca stergerea sa se faca si in lista de obiecte de acolo
    private void trimiteListaInapoi() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.LISTA_MAGAZINE, listaMagazine);
        setResult(RESULT_OK, intent);
    }
}
