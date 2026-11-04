
package com.example.password;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText website, username, password;
    Button add, view;
    TextView result;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        website = findViewById(R.id.website);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        add = findViewById(R.id.add);
        view = findViewById(R.id.view);
        result = findViewById(R.id.result);

        db = new DatabaseHelper(this);

        add.setOnClickListener(v -> {

            String w = website.getText().toString().trim();
            String u = username.getText().toString().trim();
            String p = password.getText().toString().trim();

            if (w.isEmpty() || u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Fill all fields",
                        Toast.LENGTH_SHORT).show();
            } else {
                boolean success = db.addPassword(w, u, p);

                if (success) {
                    Toast.makeText(this, "Password Saved",
                            Toast.LENGTH_SHORT).show();

                    website.setText("");
                    username.setText("");
                    password.setText("");
                }
            }
        });

        view.setOnClickListener(v -> {
            result.setText(db.getAllPasswords());
        });
    }
}
