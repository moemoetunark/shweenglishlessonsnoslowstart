package siam.moemoetun.com.shwedailyenglish.fragments;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import siam.moemoetun.com.shwedailyenglish.R;
import siam.moemoetun.com.shwedailyenglish.adapter.MyRecyclerViewAdapter;
import siam.moemoetun.com.shwedailyenglish.webview.SongLyrics;


public  class Fragment11 extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {
   MyRecyclerViewAdapter adapter;
    ArrayList<String> animalNames = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("/dictionary");


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dic, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set up the RecyclerView
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> wordsList = new ArrayList<>();

                // Iterate through the children of "dictionary"
                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                    // Get the value of the "word" field from each child
                    String word = childSnapshot.child("word").getValue(String.class);
                    // Check if the "word" field is not empty and add it to the list
                    if (word != null && !word.isEmpty()) {
                        wordsList.add(word);
                    }
                }

                // Initialize the adapter with the list of words
                adapter = new MyRecyclerViewAdapter(getContext(), wordsList);

                RecyclerView recyclerView = view.findViewById(R.id.recyclerView_1);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                // Set the adapter after it's initialized
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                // Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }
    @Override
    public void onItemClick(View view, int position) {

    }
    private void showItemClickToast(int position) {

    }

}
