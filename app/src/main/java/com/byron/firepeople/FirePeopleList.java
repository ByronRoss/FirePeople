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

public class FirePeopleList extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Person");
    String success;

    Person pulledPeople = new Person();
    ListView lv_pulledPeople;
    ArrayList<Person> people;
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

                //Iterable<DataSnapshot> i = dataSnapshot.getChildren();

                for (DataSnapshot d : dataSnapshot.getChildren())
                {
                    pulledPeople= d.getValue(Person.class);
                    Person p = d.getValue(Person.class);
                    people.add(pulledPeople);
                }
                adapter = new ArrayAdapter<>(FirePeopleList.this,
                        android.R.layout.simple_list_item_1);
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
