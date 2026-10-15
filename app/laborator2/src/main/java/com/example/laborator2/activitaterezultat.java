package com.example.laborator2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activitaterezultat extends AppCompatActivity {

    private int val1 = 0;
    private int val2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bzbzbzbz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            String mesaj = bundle.getString("mesaj", "");
            val1 = bundle.getInt("numar1", 0);
            val2 = bundle.getInt("numar2", 0);

            Toast.makeText(this, "Mesaj primit: " + mesaj + "\nValori: " + val1 + " si " + val2, Toast.LENGTH_LONG).show();
        }
    }

    public void trimiteInapoi(View view) {
        int suma = this.val1 + this.val2;
        Intent resultIntent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("mesaj_raspuns", "Suma celor doua valori este:");
        bundle.putInt("suma", suma);
        resultIntent.putExtras(bundle);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
