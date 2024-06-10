package com.appxemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appxemphim.R;
import com.appxemphim.Utils.CapitalizeWords;
import com.appxemphim.data.Phim;
import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<Phim> {

    public MovieAdapter(Context context, List<Phim> movies) {
        super(context, 0, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Phim movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movie, parent, false);
        }

        ImageView posterImageView = convertView.findViewById(R.id.imageViewPoster);
        TextView titleTextView = convertView.findViewById(R.id.textViewTitle);
        TextView descriptionTextView = convertView.findViewById(R.id.textViewDescription);

        // Load image using Picasso
        Picasso.with(getContext()).load(movie.getAnhBia()).into(posterImageView);

        titleTextView.setText(CapitalizeWords.capitalizeWords(movie.getTieuDe()));
        descriptionTextView.setText(movie.getMoTa());

        return convertView;
    }
}

