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

        TextView tvNume = v.findViewById(R.id.tvNumeRand);
        TextView tvRating = v.findViewById(R.id.tvRatingRand);
        TextView tvTipCategorie = v.findViewById(R.id.tvTipCategorieRand);
        TextView tvAdresa = v.findViewById(R.id.tvAdresaRand);
        TextView tvDetalii = v.findViewById(R.id.tvDetaliiRand);

        Magazin magazin = (Magazin) getItem(position);

        String da = ctx.getString(R.string.da);
        String nu = ctx.getString(R.string.nu);

        tvNume.setText(magazin.getNume());
        tvRating.setText(ctx.getString(R.string.rand_rating, magazin.getRating()));
        tvTipCategorie.setText(ctx.getString(R.string.rand_tip_categorie,
                magazin.getTip(), magazin.getCategorie()));
        tvAdresa.setText(magazin.getAdresa());
        tvDetalii.setText(ctx.getString(R.string.rand_detalii,
                magazin.isLivrare() ? da : nu,
                magazin.isDeschisWeekend() ? da : nu));

        return v;
    }
}
