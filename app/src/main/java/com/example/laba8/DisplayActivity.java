package com.example.laba8;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ListView listView;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        dbHelper = new DatabaseHelper(this);
        listView = findViewById(R.id.listView);
        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        displayData();
    }

    private void displayData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        String[] from = {DatabaseHelper.COLUMN_NAME, DatabaseHelper.COLUMN_YEAR};
        int[] to = {R.id.nameTextView, R.id.yearTextView};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.item_view, cursor, from, to, 0);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayData(); // Refresh data when returning from AddEditActivity
    }
}