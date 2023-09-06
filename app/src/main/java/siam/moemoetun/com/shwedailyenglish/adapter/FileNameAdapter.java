package siam.moemoetun.com.shwedailyenglish.adapter;
import static siam.moemoetun.com.shwedailyenglish.utility.FileUtils.removeFileExtension;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.utility.IconUtility;

public class FileNameAdapter extends RecyclerView.Adapter<FileNameAdapter.FileNameViewHolder> {

    private final List<String> fileNames;
    private OnItemClickListener onItemClickListener;

    public FileNameAdapter(List<String> fileNames) {
        this.fileNames = fileNames;
    }



    @NonNull
    @Override
    public FileNameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyceler_item_2, parent, false);
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(fileName);
                }
            }
        });
    }


//    public int getCurrentItemIndex() {
//        return currentItemIndex;
//    }

    @Override
    public int getItemCount() {
        return fileNames.size();
    }


    static class FileNameViewHolder extends RecyclerView.ViewHolder {

        private final TextView fileNameTextView;
        private final ImageView numberImageView;


        FileNameViewHolder(View itemView) {
            super(itemView);
            fileNameTextView = itemView.findViewById(R.id.cardTitle2);
            numberImageView = itemView.findViewById(R.id.iconImageView);
            CardView cardView = itemView.findViewById(R.id.cardView);
            //cardView.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.recycler_item_background));


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

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

}
