package com.example.modulnetseminar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MasinaAdapter extends BaseAdapter {

    private final Context context;
    private final int layoutId;
    private final List<Masina> masini;

    public MasinaAdapter(Context context, int layoutId, List<Masina> masini) {
        this.context = context;
        this.layoutId = layoutId;
        this.masini = masini;
    }

    @Override
    public int getCount() {
        return masini.size();
    }

    @Override
    public Object getItem(int position) {
        return masini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        }

        ImageView ivImagine = convertView.findViewById(R.id.ivImagine);
        TextView tvDescriere = convertView.findViewById(R.id.tvDescriere);

        Masina masina = masini.get(position);
        tvDescriere.setText(masina.getDescriere());
        if (masina.getImagine() != null) {
            ivImagine.setImageBitmap(masina.getImagine());
        } else {
            ivImagine.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        return convertView;
    }
}
