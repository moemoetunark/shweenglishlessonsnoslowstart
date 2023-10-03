package siam.moemoetun.com.shwedailyenglish.download.adapter;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.download.ItemModel;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<ItemModel> itemList;



    public ItemAdapter(List<ItemModel> itemList) {
        this.itemList = itemList;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.download_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = itemList.get(position);

        holder.titleTextView.setText(item.getTitle());
        holder.descriptionTextView.setText(item.getDescription());

        // Load image using Picasso or any other image-loading library
        Picasso.get().load(item.getImage()).
                resize(100, 100).
                centerCrop().
                into(holder.circularImageView);

        // Set click listener for Learn More button
        holder.learnMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click action here (e.g., open the URL in a web browser)
                CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
                intent.launchUrl(v.getContext(), Uri.parse(item.getUrl()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return Math.min(itemList.size(),5);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView circularImageView;
        TextView titleTextView;
        TextView descriptionTextView;
        TextView learnMoreButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circularImageView = itemView.findViewById(R.id.circularImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            learnMoreButton = itemView.findViewById(R.id.learnMoreButton);

        }
    }

}
