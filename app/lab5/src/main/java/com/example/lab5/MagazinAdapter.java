package com.example.lab5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MagazinAdapter extends BaseAdapter {

    private final List<Magazin> magazine;
    private final Context ctx;
    private final int resursaLayout;

    public MagazinAdapter(List<Magazin> magazine, Context ctx, int resursaLayout) {
        this.magazine = magazine;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return magazine.size();
    }

    @Override
    public Object getItem(int position) {
        return magazine.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            v = inflater.inflate(resursaLayout, parent, false);
        }

        TextView nume = v.findViewById(R.id.nume);
        TextView adresa = v.findViewById(R.id.adresa);
        TextView tip = v.findViewById(R.id.tip);
        TextView categorie = v.findViewById(R.id.categorie);
        TextView rating = v.findViewById(R.id.rating);
        TextView livrare = v.findViewById(R.id.livrare);
        TextView deschisWeekend = v.findViewById(R.id.deschisWeekend);

        Magazin magazin = (Magazin) getItem(position);

        String da = ctx.getString(R.string.da);
        String nu = ctx.getString(R.string.nu);


        nume.setText(magazin.getNume());
        adresa.setText(magazin.getAdresa());
        tip.setText(magazin.getTip());
        categorie.setText(magazin.getCategorie());
        rating.setText(ctx.getString(R.string.rand_rating, magazin.getRating()));
        livrare.setText(ctx.getString(R.string.rand_livrare, magazin.isLivrare() ? da : nu));
        deschisWeekend.setText(ctx.getString(R.string.rand_weekend, magazin.isDeschisWeekend() ? da : nu));

        return v;
    }
}
