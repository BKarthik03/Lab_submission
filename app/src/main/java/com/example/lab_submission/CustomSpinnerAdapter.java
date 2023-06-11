package com.example.lab_submission;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    public CustomSpinnerAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
    }

    @Override
    public boolean isEnabled(int position) {
        // Make the first item unselectable
        return position != 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        if (position == 0) {
            // Customize the appearance of the unselectable item
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            // Other customization if needed
        }

        return view;
    }
}
