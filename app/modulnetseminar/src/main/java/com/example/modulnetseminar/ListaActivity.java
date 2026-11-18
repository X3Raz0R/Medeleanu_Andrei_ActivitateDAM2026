package com.example.modulnetseminar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ListaActivity extends AppCompatActivity {

    private List<Masina> masini;
    private final List<Bitmap> imagini = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://thumb.wikimedia.org/wikipedia/commons/thumb/7/7b/Dacia_Logan_III.jpg/500px-Dacia_Logan_III.jpg");
        linkuriImagini.add("https://thumb.wikimedia.org/wikipedia/commons/thumb/f/f8/Dacia_Logan_2023_%28front%29.jpg/500px-Dacia_Logan_2023_%28front%29.jpg");
        linkuriImagini.add("https://thumb.wikimedia.org/wikipedia/commons/thumb/e/ee/Dacia_Logan_MCV.jpg/500px-Dacia_Logan_MCV.jpg");
        linkuriImagini.add("https://thumb.wikimedia.org/wikipedia/commons/thumb/8/8f/Ford_Mustang_GT_%2822738183992%29.jpg/500px-Ford_Mustang_GT_%2822738183992%29.jpg");
        linkuriImagini.add("https://thumb.wikimedia.org/wikipedia/commons/thumb/a/a4/2019_Toyota_Corolla_Icon_Tech_VVT-i_Hybrid_1.8.jpg/500px-2019_Toyota_Corolla_Icon_Tech_VVT-i_Hybrid_1.8.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        // firul secundar: descarcam imaginile
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link : linkuriImagini) {
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestProperty("User-Agent", "SeminarApp/1.0");
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                        is.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        // pastram ordinea: imaginea care nu s-a descarcat ramane null
                        imagini.add(null);
                    } finally {
                        if (con != null) {
                            con.disconnect();
                        }
                    }
                }

                // ne intoarcem pe firul principal
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        masini = new ArrayList<>();
                        masini.add(new Masina("Dacia", "Logan III", 2021, imagini.get(0),
                                "https://en.wikipedia.org/wiki/Dacia_Logan"));
                        masini.add(new Masina("Dacia", "Logan", 2023, imagini.get(1),
                                "https://en.wikipedia.org/wiki/Dacia_Logan"));
                        masini.add(new Masina("Dacia", "Logan MCV", 2007, imagini.get(2),
                                "https://en.wikipedia.org/wiki/Dacia_Logan"));
                        masini.add(new Masina("Ford", "Mustang GT", 2015, imagini.get(3),
                                "https://en.wikipedia.org/wiki/Ford_Mustang"));
                        masini.add(new Masina("Toyota", "Corolla Hybrid", 2019, imagini.get(4),
                                "https://en.wikipedia.org/wiki/Toyota_Corolla"));

                        ListView lvMasini = findViewById(R.id.lvMasini);
                        MasinaAdapter adapter = new MasinaAdapter(getApplicationContext(), R.layout.item_masina, masini);
                        lvMasini.setAdapter(adapter);
                    }
                });
            }
        });

        ListView lvMasini = findViewById(R.id.lvMasini);
        lvMasini.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(getApplicationContext(), webviewactivity.class);
                it.putExtra("link", masini.get(i).getLink());
                startActivity(it);
            }
        });
    }
}
