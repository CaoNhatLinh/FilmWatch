package com.appxemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.appxemphim.R;
import com.appxemphim.data.Phim;

import com.squareup.picasso.Picasso;

import java.util.List;


public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.PosterItemViewHolder> {
    private List<Phim> Phims;
    private Context context;

    public BannerAdapter(Context c, List<Phim> Phims) {
        this.Phims = Phims;
        this.context = c;
    }

    @Override
    public int getItemCount() {
        return Phims.size();
    }
    public void updatePhimList(List<Phim> newPhimList) {
        Phims.clear();
        Phims.addAll(newPhimList);
        notifyDataSetChanged();
    }
    @Override
    public PosterItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_banner_home, parent, false);

        return new PosterItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PosterItemViewHolder holder, int position) {
        Phim u = Phims.get(position);
        Picasso.with(context)
                .load(u.getBanner())
                .into(holder.ivBanner);
        holder.tvTitle.setText(u.getTieuDe());
    }
    public Phim getPhimAtPosition(int position) {
        return Phims.get(position);
    }
    public static class PosterItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView ivBanner;

        public PosterItemViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.textViewTitlePoster);
            ivBanner = (ImageView) itemView.findViewById(R.id.imagePoster);
        }
    }
}
