package com.byron.firepeople;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirePeopleList extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Person");
    ListView lv_pulledPeople;
    List<Person> people = new ArrayList<>();
    ArrayAdapter<Person> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_people_list);

        lv_pulledPeople = findViewById(R.id.lstV);

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                people.clear();

                for (DataSnapshot child : dataSnapshot.getChildren())
                {
                    Person person = child.getValue(Person.class);
                    people.add(person);
                }

                adapter = new ArrayAdapter<>(FirePeopleList.this,
                        android.R.layout.simple_list_item_1, people);
                lv_pulledPeople.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("onCancelled", "Failed to read value.", error.toException());
            }
        });
    }
}
