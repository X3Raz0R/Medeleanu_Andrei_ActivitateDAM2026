package com.example.laborator2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class actevetatenoo extends AppCompatActivity {

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_actevetatenoo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {
                            String mesaj = bundle.getString("mesaj_raspuns", "");
                            int suma = bundle.getInt("suma", 0);
                            Toast.makeText(this, mesaj + " " + suma, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void deschideActivitate(View view) {
        Intent intent = new Intent(this, activitaterezultat.class);
        Bundle bundle = new Bundle();
        bundle.putString("mesaj", "Mesaj din actevetatenoo!");
        bundle.putInt("numar1", 15);
        bundle.putInt("numar2", 25);
        intent.putExtras(bundle);
        launcher.launch(intent);
    }
}
