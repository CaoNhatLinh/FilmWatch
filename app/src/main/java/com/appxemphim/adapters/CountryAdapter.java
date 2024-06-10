package com.appxemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.appxemphim.R;
import java.util.List;

public class CountryAdapter extends ArrayAdapter<String> {

    public CountryAdapter(Context context, List<String> countries) {
        super(context, 0, countries);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String country = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_country, parent, false);
        }

        TextView countryTextView = convertView.findViewById(android.R.id.text1);
        countryTextView.setText(country);

        return convertView;
    }
}
