package siam.moemoetun.com.shwedailyenglish.quiz.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import siam.moemoetun.com.shwedailyenglish.R;

public class SelectedCategoryAdapter extends RecyclerView.Adapter<SelectedCategoryAdapter.ViewHolder> {

    private final ArrayList<String> itemList;
    private final ItemClickListener itemClickListener;

    public SelectedCategoryAdapter(ArrayList<String> itemList, ItemClickListener itemClickListener) {
        this.itemList = itemList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = itemList.get(position);
        holder.itemTextView.setText(item);
        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;
        Button playButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.cardTitle);
            playButton = itemView.findViewById(R.id.playButton);
        }
    }

    public interface ItemClickListener {
        void onItemClick(int position);
    }
}
