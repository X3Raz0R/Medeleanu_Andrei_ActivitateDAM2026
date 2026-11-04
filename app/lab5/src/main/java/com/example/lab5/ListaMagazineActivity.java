package com.example.lab5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class    ListaMagazineActivity extends AppCompatActivity {

    private ArrayList<Magazin> listaMagazine;
    private MagazinAdapter adapter;
    private int idModificat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_magazine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            }
        });

        ArrayList<Magazin> primita =
                getIntent().getParcelableArrayListExtra(MainActivity.LISTA_MAGAZINE);
        listaMagazine = primita != null ? primita : new ArrayList<>();

        ListView lvMagazine = findViewById(R.id.lvMagazine);
        adapter = new MagazinAdapter(listaMagazine, this, R.layout.row_magazin);
        lvMagazine.setAdapter(adapter);

        ActivityResultLauncher<Intent> modificaLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Magazin modificat = result.getData().getParcelableExtra("MAGAZIN_EXTRA");
                            if (modificat != null) {
                                listaMagazine.set(idModificat, modificat);
                                adapter.notifyDataSetChanged();
                                trimiteListaInapoi();
                            }
                        }
                    }
                }
        );

        lvMagazine.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idModificat = position;
                Intent intent = new Intent(ListaMagazineActivity.this, AddMagazinActivity.class);
                intent.putExtra("MAGAZIN_EXTRA", listaMagazine.get(position));
                modificaLauncher.launch(intent);
            }
        });

        lvMagazine.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Magazin sters = listaMagazine.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(ListaMagazineActivity.this, getString(R.string.magazin_sters, sters.getNume()),
                        Toast.LENGTH_SHORT).show();
                trimiteListaInapoi();
                return true;
            }
        });
    }

    private void trimiteListaInapoi() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(MainActivity.LISTA_MAGAZINE, listaMagazine);
        setResult(RESULT_OK, intent);
    }
}
