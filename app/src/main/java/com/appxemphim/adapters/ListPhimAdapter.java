package com.appxemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.appxemphim.R;
import com.appxemphim.Utils.CapitalizeWords;
import com.appxemphim.data.Phim;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListPhimAdapter extends RecyclerView.Adapter<ListPhimAdapter.PhimItemViewHolder> {
    private List<Phim> Phims;
    private Context context;

    public ListPhimAdapter( Context c,List<Phim> Phims) {
        this.Phims = Phims;
        this.context = c;
    }

    @Override
    public int getItemCount() {
        return Phims.size();
    }
    public void updatePhimList(List<Phim> newPhimList) {
        Phims.clear();
//        for (Phim phim :
//                newPhimList) {
//            Phims.add(phim);
//
//        }
        Phims.addAll(newPhimList);
        notifyDataSetChanged();
    }
    @Override
    public PhimItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        return new PhimItemViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(PhimItemViewHolder holder, int position) {
        Phim u = Phims.get(position);
        Picasso.with(context)
                .load(u.getAnhBia())
                .into(holder.ivPhim);

        holder.TitlePhim.setText(CapitalizeWords.capitalizeWords(u.getTieuDe()));
    }
    public Phim getPhimAtPosition(int position) {
        return Phims.get(position);
    }
    public static class PhimItemViewHolder extends RecyclerView.ViewHolder {
        public TextView TitlePhim;
        public ImageView ivPhim;

        public PhimItemViewHolder(View itemView) {
            super(itemView);
            TitlePhim = (TextView) itemView.findViewById(R.id.textViewTitle);
            ivPhim = (ImageView) itemView.findViewById(R.id.imageViewPoster);
        }
    }
}
