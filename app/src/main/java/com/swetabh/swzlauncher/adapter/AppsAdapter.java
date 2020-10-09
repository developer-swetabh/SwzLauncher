package com.swetabh.swzlauncher.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swetabh.swzlauncher.R;
import com.swetabh.launchersdk.AppInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {
    private List<AppInfo> appsList;
    private List<AppInfo> originalAppsList;

    public AppsAdapter(Context c, List<AppInfo> list) {

        appsList = list;
        originalAppsList = new ArrayList<>();
        originalAppsList.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //This is what adds the code we've written in here to our target view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.app_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//Here we use the information in the list we created to define the views

        String appLabel = appsList.get(position).getLabel().toString();
        Drawable appIcon = appsList.get(position).getIcon();

        TextView textView = holder.textView;
        textView.setText(appLabel);
        ImageView imageView = holder.img;
        imageView.setImageDrawable(appIcon);
    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Finds the views from our row.xml
            textView = (TextView) itemView.findViewById(R.id.app_label);
            img = (ImageView) itemView.findViewById(R.id.app_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            Context context = v.getContext();

            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(appsList.get(pos).getPackageName());
            context.startActivity(launchIntent);
            Toast.makeText(v.getContext(), appsList.get(pos).getLabel(), Toast.LENGTH_LONG).show();
        }
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        appsList.clear();
        if (charText.length() == 0) {
            appsList.addAll(originalAppsList);
        } else {
            for (AppInfo ai : originalAppsList) {
                if (ai.getLabel().toLowerCase(Locale.getDefault()).contains(charText)) {
                    appsList.add(ai);
                }
            }
        }
        notifyDataSetChanged();
    }
}
