package com.example.exercitiul_sase_sapte;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    int val1;
    int val2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            String msg = bundle.getString("mesaj");
            val1 = bundle.getInt("val1");
            val2 = bundle.getInt("val2");
            int suma = val1+val2;
            Toast.makeText(this,"suma 67 este:" + suma,Toast.LENGTH_LONG).show();
        }
        Button btninapoi = findViewById(R.id.button2);
        btninapoi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intentRaspuns = new Intent();
                intentRaspuns.putExtra("msg","sarupt dinactiviatte3");
                intentRaspuns.putExtra("suma",val1+val2);
                setResult(RESULT_OK,intentRaspuns);
                finish();
            }
        });
    }
}