package za.ac.cput.toasttvapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    private EditText edtAnimeTitle, edtNumberOfEpisodes, edtShortDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnReturn = findViewById(R.id.btnReturn);
        Button btnView = findViewById(R.id.btnView);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnSearch = findViewById(R.id.btnSearch);
        myDb = new DatabaseHelper(this);
        edtAnimeTitle = (EditText) findViewById(R.id.inputAnimeTitle);
        edtShortDescription = (EditText) findViewById(R.id.inputAnimeDescription);
        edtNumberOfEpisodes = (EditText) findViewById(R.id.inputNumberOfEpisodes);


        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });

        Button btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtAnimeTitle.getText().toString().equals("") ||
                        edtNumberOfEpisodes.getText().toString().equals("") ||
                        edtShortDescription.getText().toString().equals("")
                ) {
                    Toast.makeText(MainActivity.this, "Some fields are empty!", Toast.LENGTH_LONG).show();

                } else
                    checkRegister();

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewData();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtAnimeTitle.getText().toString().equals("") ||
                        edtNumberOfEpisodes.getText().toString().equals("") ||
                        edtShortDescription.getText().toString().equals("")
                ) {
                    Toast.makeText(MainActivity.this, "Please input the known values first!", Toast.LENGTH_LONG).show();

                } else
                    updateData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtAnimeTitle.getText().toString().equals("") ||
                        edtNumberOfEpisodes.getText().toString().equals("") ||
                        edtShortDescription.getText().toString().equals("")
                ) {
                    Toast.makeText(MainActivity.this, "Please input the known values first!", Toast.LENGTH_LONG).show();

                } else
                    deleteData();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,SearchAnime.class);
                startActivity(intent);
            }
        });
    }

    public void checkRegister() {
        if (edtAnimeTitle.getText().toString().equals("") ||
                edtNumberOfEpisodes.getText().toString().equals("") ||
                edtShortDescription.getText().toString().equals("") ) {
            Toast.makeText(this, "Some fields are empty!", Toast.LENGTH_LONG).show();
        } else {
            int quantity = Integer.parseInt(edtNumberOfEpisodes.getText().toString());
            boolean isInserted = myDb.insertData(
                    edtAnimeTitle.getText().toString(),
                    quantity,
                    edtShortDescription.getText().toString());
            if (isInserted == true) {
                Toast.makeText(this, "Anime Inserted!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            } else {

                Toast.makeText(this, "Insert Unsuccessful", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void viewData() {

        Cursor res;
        res = myDb.getAllData();

        if (res.getCount() == 0) {
            displayData("Error", "There are no records of any anime!");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {

            buffer.append("Anime Id : " + res.getString(0) + "\n");
            buffer.append("Anime Title : " + res.getString(1) + "\n");
            buffer.append("Anime Episodes : " + res.getString(2) + "\n");
            buffer.append("Anime Description : " + res.getString(3) + "\n");


        }

        displayData("Anime Details", buffer.toString());
    }

    public void displayData(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData() {
        int quantity = Integer.parseInt(edtNumberOfEpisodes.getText().toString());
        boolean isUpdated = myDb.updateAccounts(
                edtAnimeTitle.getText().toString(),
                quantity,
                 edtShortDescription.getText().toString());
        if (isUpdated == true) {
            Toast.makeText(MainActivity.this, "Account Updated!", Toast.LENGTH_LONG).show();

        } else
            Toast.makeText(MainActivity.this, "Account could not be updated!", Toast.LENGTH_LONG).show();


    }
    public void deleteData(){
        Integer deletedRows= myDb.deleteData(edtAnimeTitle.getText().toString());
        if(deletedRows>0){
            Toast.makeText(MainActivity.this, "Anime details deleted!", Toast.LENGTH_LONG).show();
        }else{
            displayData("Error", "This Anime does not exist or has already been deleted!");
        }
    }




}