package com.example.myapp.hbndatabase;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class InnovationAdapter extends ArrayAdapter<Innovation> {
    public InnovationAdapter(Context context, ArrayList<Innovation> innovations) {
        super(context, 0, innovations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        final Innovation innovation = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }

        // Lookup view for data population

        TextView tvTitle = (TextView) convertView.findViewById(R.id.rowTitle);
        TextView tvType = (TextView) convertView.findViewById(R.id.rowCategory);
        TextView tvInnovator = (TextView) convertView.findViewById(R.id.rowInnovator);
        TextView tvSource = (TextView) convertView.findViewById(R.id.rowSource);
        TextView tvid = (TextView) convertView.findViewById(R.id.rowid);
        // Populate the data into the template view using the data object
         tvType.setText(innovation.getType());
         tvTitle.setText(innovation.getTitle());
         tvInnovator.setText(innovation.getName());

       //  tvid.setText(innovation.getDetails());
         tvSource.setText("HoneyBee");



        // Add onClickListener for the Listview element
//        LinearLayout tvParent = convertView.findViewById(R.id.rowParent);
//        tvParent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri webpage = Uri.parse(innovation.getLink());
//                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                }
//            }
//        });

        // Return the completed view to render on screen
        return convertView;
    }
}
