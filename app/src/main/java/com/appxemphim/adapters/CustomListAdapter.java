package com.appxemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.appxemphim.R;
import com.appxemphim.data.Phim;
import com.squareup.picasso.Picasso;

import java.util.List;
public class CustomListAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<Phim> phimList;

    public CustomListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.phimList = phimList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        String movieTitle = getItem(position);

        ImageView imageView = convertView.findViewById(R.id.image);
        TextView textView = convertView.findViewById(R.id.textView);

        // Kiểm tra xem danh sách phim có null không
        if (phimList != null && position < phimList.size()) {
            // Lấy đối tượng Phim tương ứng với vị trí hiện tại
            Phim phim = phimList.get(position);

            // Load image into ImageView using Picasso
            Picasso.with(context)
                    .load(phim.getAnhBia())
                    .into(imageView);
        }

        // Set text to TextView
        textView.setText(movieTitle);

        return convertView;
    }
}
