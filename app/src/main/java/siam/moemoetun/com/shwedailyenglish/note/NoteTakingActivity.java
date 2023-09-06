package siam.moemoetun.com.shwedailyenglish.note;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import siam.moemoetun.com.shwedailyenglish.R;

public class NoteTakingActivity extends AppCompatActivity {
    private EditText titleEditText;
    private EditText contentEditText;
    private DatabaseHelper dbHelper;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_taking);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        dbHelper = new DatabaseHelper(this, noteAdapter);
        titleEditText = findViewById(R.id.noteTitle);
        contentEditText = findViewById(R.id.noteDetail);
        Button saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString().trim();
                String content = contentEditText.getText().toString().trim();

                if (!title.isEmpty() && !content.isEmpty()) {
                    long newRowId = dbHelper.insertNote(title, content);
                    if (newRowId != -1) {
                        Note note = new Note((int) newRowId, title, content, new Date());
                        List<Note> notes = dbHelper.getAllNotes();
                        notes.add(note);
                        //noteAdapter.setNotes(notes);
                        // Pass the list of notes
                       // noteAdapter.notifyDataSetChanged(); // Notify adapter of data change
                        finish(); // Finish the activity after saving the note
                        Toast.makeText(NoteTakingActivity.this, "Note was saved successfully.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NoteTakingActivity.this, "Note wasn't saved successfully.", Toast.LENGTH_SHORT).show();
                        // Failed to save note
                        // Handle error or display a message to the user
                    }
                } else {
                    Toast.makeText(NoteTakingActivity.this, "Note cannot be empty!", Toast.LENGTH_SHORT).show();
                    // Empty title or content, handle the error or display a message to the user
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
