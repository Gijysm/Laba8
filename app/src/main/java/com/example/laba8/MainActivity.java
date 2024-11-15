package com.example.laba8;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameInput, yearInput;
    private Button saveButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        nameInput = findViewById(R.id.nameInput);
        yearInput = findViewById(R.id.yearInput);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v->saveData());
    }

    private void saveData() {
        String name = nameInput.getText().toString();
        String year = yearInput.getText().toString();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(!name.isEmpty() && !year.isEmpty()) {
            values.put(DatabaseHelper.COLUMN_NAME, name);
            values.put(DatabaseHelper.COLUMN_YEAR, year);

            db.insert(DatabaseHelper.TABLE_NAME, null, values);
            db.close();

            // Return to DisplayActivity after saving

            finish();
            Intent intent = new Intent(this, DisplayActivity.class);
            startActivity(intent);
        }
    }
}

