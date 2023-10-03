package siam.moemoetun.com.shwedailyenglish.download.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.download.ItemModel;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder> {
    private List<ItemModel> SeconditemList;
    private OnItemClickListener listener;


    public SecondAdapter(List<ItemModel> SeconditemList, OnItemClickListener listener) {
        this.SeconditemList = SeconditemList;
        this.listener = listener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.download_item2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = SeconditemList.get(position);

        holder.titleTextView.setText(item.getTitle());
        holder.descriptionTextView.setText(item.getDescription());

        // Load image using Picasso or any other image-loading library
        Picasso.get().load(item.getImage()).
                resize(80, 80).
                centerCrop().
                into(holder.circularImageView);

        // Set click listener for Learn More button
        holder.learnMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click action here (e.g., open the URL in a web browser)
                if (listener != null) {
                    listener.onItemClick(item.getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return SeconditemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView circularImageView;
        TextView titleTextView;
        TextView descriptionTextView;
        Button learnMoreButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circularImageView = itemView.findViewById(R.id.circularImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            learnMoreButton = itemView.findViewById(R.id.learnMoreButton);

        }
    }

    public interface OnItemClickListener {
        void onItemClick(String url);
    }
}
