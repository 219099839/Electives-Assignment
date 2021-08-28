package za.ac.cput.toasttvapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchAnime extends AppCompatActivity {

    DatabaseHelper myDb;
    SQLiteDatabase sqLiteDatabase;
    private TextView txtAnimeName, txtDescription, txtEpisodes;
    EditText edtAnimeTitle;
    String search_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_anime);

        Button btnReturn = findViewById(R.id.btnReturn2);
        Button btnSearch = findViewById(R.id.btnSearch);
        edtAnimeTitle = (EditText) findViewById(R.id.txtAnimeId);
        txtAnimeName = findViewById(R.id.txtAnimeTitle);
        txtEpisodes = findViewById(R.id.txtEpisodes);
        txtDescription = findViewById(R.id.txtDescription);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchAnime.this, MainActivity.class);
                startActivity(intent);

                myDb = new DatabaseHelper(SearchAnime.this);

            }
        });

    }
    public void Search_Id(View view) {
        search_id = edtAnimeTitle.getText().toString();
        myDb = new DatabaseHelper(getApplicationContext());
        sqLiteDatabase = myDb.getReadableDatabase();
        Cursor cursor = myDb.searchData(search_id, sqLiteDatabase);
        if (cursor.moveToFirst()) {

            String AnimeName = cursor.getString(1);
            String AnimeEpisodes = cursor.getString(2);
            String AnimeDescription = cursor.getString(3);

            Toast.makeText(SearchAnime.this, "Anime Found!", Toast.LENGTH_SHORT).show();
            txtAnimeName.setText("Anime Name: "+AnimeName);
            txtEpisodes.setText("Number Of Episodes: "+AnimeEpisodes);
            txtDescription.setText("Description Of Anime: "+AnimeDescription);

        } else {
            Toast.makeText(SearchAnime.this, "Could not find Equipment", Toast.LENGTH_SHORT).show();
            txtAnimeName.setText("Equipment Name: ");
            txtEpisodes.setText("Equipment Quantity: ");
            txtDescription.setText("Equipment rand: ");

        }
    }}