package siam.moemoetun.com.shwedailyenglish.note;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import siam.moemoetun.com.shwedailyenglish.R;
public class NoteActivity extends AppCompatActivity {
    private NoteAdapter noteAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        dbHelper = new DatabaseHelper(this, noteAdapter); // Initialize dbHelper
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        FloatingActionButton fabAddNote = findViewById(R.id.fabAddNote);
        int color = Color.parseColor("#000000"); // Replace "#FF0000" with your desired color in hex code
        ColorStateList colorStateList = ColorStateList.valueOf(color);
        fabAddNote.setBackgroundTintList(colorStateList);
        noteAdapter = new NoteAdapter(new ArrayList<>(), dbHelper);

        recyclerView.setAdapter(noteAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fabAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteActivity.this, NoteTakingActivity.class);
                startActivity(intent);
            }
        });
        loadData(); // Load initial data
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // Refresh data when the activity is resumed
    }

    private void loadData() {
        List<Note> notes = dbHelper.getAllNotes();
        noteAdapter.setNotes(notes);
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
