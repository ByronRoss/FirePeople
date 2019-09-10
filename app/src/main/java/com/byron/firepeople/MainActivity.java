package com.byron.firepeople;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName, reason;
    Button push;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Person");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        push = findViewById(R.id.btnFire);
        firstName = findViewById(R.id.et_firedName);
        lastName = findViewById(R.id.et_firedLastName);
        reason = findViewById(R.id.et_firedReason);

        push.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p = new Person();
                p.setFirstName(firstName.getText().toString());
                p.setLastName(lastName.getText().toString());
                p.setReason(reason.getText().toString());
                myRef.setValue(p);
                Intent i = new Intent(MainActivity.this, FirePeopleList.class);
                startActivity(i);
            }
        });

    }
}
