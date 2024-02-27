package siam.moemoetun.com.shwedailyenglish.premium;

import static siam.moemoetun.com.shwedailyenglish.utility.FileUtils.removeFileExtension;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.constant.AppConstant;
import siam.moemoetun.com.shwedailyenglish.utility.AdMobHelper;
import siam.moemoetun.com.shwedailyenglish.utility.IconUtility;

public class PremiumAdapter extends RecyclerView.Adapter<PremiumAdapter.FileNameViewHolder>
        implements AppConstant.CoinBalanceObserver {

    private List<String> fileNames;
    private SharedPreferences sharedPreferences;
    private int selectedPosition = -1; // Initially no position is selected

    public PremiumAdapter(List<String> fileNames, Context context) {
        this.fileNames = fileNames;
        this.sharedPreferences = context.getSharedPreferences("state_", Context.MODE_PRIVATE);
        // Register the adapter as a CoinBalanceObserver
        AppConstant.registerCoinBalanceObserver(this);
    }

    @NonNull
    @Override
    public FileNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.premium_item, parent, false);
        return new FileNameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileNameViewHolder holder, int position) {
        String fileName = fileNames.get(position);
        int number = position + 1;
        // Generate the number icon for the current position
        Context context = holder.itemView.getContext();
        int iconSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, context.getResources().getDisplayMetrics());
        int backgroundColor = R.color.colorPrimary;
        int textColor = android.R.color.white;
        Drawable numberIcon = IconUtility.generateNumberIcon(context, number, iconSize, backgroundColor, textColor);
        // Set the number icon to the ImageView
        holder.numberImageView.setImageDrawable(numberIcon);
        holder.bind(fileName, number);

        // Set the state of the rightIconImageView
        boolean isSelected = position == selectedPosition;
        boolean hasEnoughCoins = AppConstant.getCoinBalance() >= 10;
        boolean isSaved = sharedPreferences.getBoolean("state_" + position, false);
        // Check if the item has been bought before or is currently selected, or if the user has enough coins
        boolean canEnable = isSaved || isSelected || hasEnoughCoins;

        if (isSaved) {
            // If the state is saved, enable the rightIconImageView and set the appropriate image resource
            holder.rightIconImageView.setEnabled(true);
            holder.rightIconImageView.setImageResource(R.drawable.check_circle_24);
        } else {
            // If the state is not saved, set the state based on the coin balance and selected position
            holder.rightIconImageView.setEnabled(canEnable);
            holder.rightIconImageView.setImageResource(isSelected ? R.drawable.check_circle_24 : R.drawable.baseline_lock_30);
        }

        // Set a click listener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();
                if (onItemClickListener != null && canEnable) {
                    boolean isSaved = sharedPreferences.getBoolean("state_" + clickedPosition, false);
                    boolean isSelected = clickedPosition == selectedPosition;

                    if (!isSaved && !isSelected) {
                        int currentBalance = AppConstant.getCoinBalance();
                        if (currentBalance >= 10) {
                            currentBalance -= 10;
                            AppConstant.setCoinBalance(currentBalance);
                            sharedPreferences.edit().putBoolean("state_" + clickedPosition, true).apply();
                            Toast.makeText(view.getContext(), "Item unlocked! State saved.", Toast.LENGTH_SHORT).show();
                            // Update selected position after successfully unlocking
                            selectedPosition = clickedPosition;
                            notifyDataSetChanged();
                        }
                    }
                    onItemClickListener.onItemClick(fileName);
                }else {
                    Toast.makeText(view.getContext().getApplicationContext(), "Coin မလုံလောက်ပါ", Toast.LENGTH_SHORT).show();
                }
            }

        });


    }

    @Override
    public int getItemCount() {
        return fileNames.size();
    }

    // CoinBalanceObserver method
    @Override
    public void onCoinBalanceChanged(int newBalance) {
        // Update the UI or data in the adapter based on the new coin balance
        // For example, you might update the visibility or state of certain views
        notifyDataSetChanged(); // Refresh the adapter to reflect the changes
    }

    static class FileNameViewHolder extends RecyclerView.ViewHolder {

        private TextView fileNameTextView;
        private ImageView numberImageView;
        private ImageView rightIconImageView;

        FileNameViewHolder(View itemView) {
            super(itemView);
            fileNameTextView = itemView.findViewById(R.id.cardTitle2);
            numberImageView = itemView.findViewById(R.id.leftIconImageView);
            rightIconImageView = itemView.findViewById(R.id.rightIconImageView);
        }

        void bind(String fileName, int number) {
            String fileNameWithoutExtension = removeFileExtension(fileName);
            String fileNameNothing = fileNameWithoutExtension.substring(fileName.indexOf("_") + 1);
            fileNameTextView.setText(fileNameNothing);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String fileName);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
}
