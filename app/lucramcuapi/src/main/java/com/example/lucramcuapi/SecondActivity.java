package com.example.lucramcuapi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SecondActivity extends AppCompatActivity {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());
    public String apikey = "apikey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etOras = findViewById(R.id.etOras);
        Button btnCauta = findViewById(R.id.btnCauta);
        TextView tvCheie = findViewById(R.id.tvCheie);
        TextView tvTempmin = findViewById(R.id.tvTempmin);
        TextView tvTempmax = findViewById(R.id.tvTempmax);

        btnCauta.setOnClickListener(v -> {
            String oras = etOras.getText().toString().trim();
            if (oras.isEmpty()) {
                Toast.makeText(this, "Introduceti un oras", Toast.LENGTH_SHORT).show();
                return;
            }
            tvCheie.setText("Se cauta...");
            tvTempmin.setText("temperatura minima:");
            tvTempmax.setText("temperatura maxima:");

            // apelul pe firul secundar
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String textCheie;
                    String textMin = "temperatura minima:";
                    String textMax = "temperatura maxima:";
                    try {
                        String link = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey="
                                + apikey + "&q=" + oras.replace(" ", "%20");
                        URL url = new URL(link);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("GET");
                        conn.setRequestProperty("Authorization", "Bearer " + apikey);

                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder builder = new StringBuilder();
                        String linie;
                        while ((linie = reader.readLine()) != null) {
                            builder.append(linie);
                        }
                        reader.close();
                        conn.disconnect();

                        // parsare JSON
                        JSONArray vector = new JSONArray(builder.toString());
                        JSONObject obiect = vector.getJSONObject(0);
                        String cheieOras = obiect.getString("Key");
                        Log.d("CHEIE_ORAS", cheieOras);

                        // apel2
                        String link2 = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/"
                                + cheieOras + "?apikey=" + apikey + "&metric=true";
                        URL url2 = new URL(link2);
                        HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                        conn2.setRequestMethod("GET");
                        conn2.setRequestProperty("Authorization", "Bearer " + apikey);

                        BufferedReader reader2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
                        StringBuilder builder2 = new StringBuilder();
                        String linie2;
                        while ((linie2 = reader2.readLine()) != null) {
                            builder2.append(linie2);
                        }
                        reader2.close();
                        conn2.disconnect();

                        // parsare JSON vreme
                        JSONObject vreme = new JSONObject(builder2.toString());
                        JSONObject temperatura = vreme.getJSONArray("DailyForecasts")
                                .getJSONObject(0)
                                .getJSONObject("Temperature");
                        double min = temperatura.getJSONObject("Minimum").getDouble("Value");
                        double max = temperatura.getJSONObject("Maximum").getDouble("Value");

                        textCheie = "Cheie oras: " + cheieOras;
                        textMin = "temperatura minima: " + min + " C";
                        textMax = "temperatura maxima: " + max + "      C";
                    } catch (Exception e) {
                        Log.e("CHEIE_ORAS", "Eroare", e);
                        textCheie = "Eroare: " + e;
                    }

                    // intoarcere pe firul principal
                    final String fCheie = textCheie;
                    final String fMin = textMin;
                    final String fMax = textMax;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tvCheie.setText(fCheie);
                            tvTempmin.setText(fMin);
                            tvTempmax.setText(fMax);
                        }
                    });
                }
            });
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
