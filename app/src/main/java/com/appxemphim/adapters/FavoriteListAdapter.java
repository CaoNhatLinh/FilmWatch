package com.appxemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.appxemphim.R;
import com.appxemphim.data.Phim;
import com.bumptech.glide.Glide;
import java.util.List;

public class FavoriteListAdapter extends ArrayAdapter<Phim> {

    private Context context;
    private List<Phim> phimList;

    public FavoriteListAdapter(Context context, List<Phim> phimList) {
        super(context, R.layout.item_favorite, phimList);
        this.context = context;
        this.phimList = phimList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            listItemView = inflater.inflate(R.layout.item_favorite, parent, false);
        }

        Phim phim = phimList.get(position);

        ImageView imageView = listItemView.findViewById(R.id.imageView);
        Glide.with(context).load(phim.getBanner()).into(imageView);

        TextView titleTextView = listItemView.findViewById(R.id.tieude);
        titleTextView.setText(phim.getTieuDe());

        return listItemView;
    }
}
