
package com.example.password;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "PasswordDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Passwords (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "website TEXT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addPassword(String website, String username, String password) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("website", website);
        values.put("username", username);
        values.put("password", password);

        long result = db.insert("Passwords", null, values);

        return result != -1;
    }

    public String getAllPasswords() {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Passwords", null);

        StringBuilder data = new StringBuilder();

        try {
            while (cursor.moveToNext()) {
                data.append("Website: ").append(cursor.getString(1)).append("\n");
                data.append("Username: ").append(cursor.getString(2)).append("\n");
                data.append("Password: ").append(cursor.getString(3)).append("\n\n");
            }
        } finally {
            cursor.close();
        }

        if (data.length() == 0) {
            return "No passwords found";
        }

        return data.toString();
    }
}
