package com.example.tsv.myapplication;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private static int MAX_SEND_LENGTH = 150;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    ArrayList<String> arrayList = new ArrayList<>();

    EditText editText;
    Button buttonSend;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSend = findViewById(R.id.buttonMessage);
        editText = findViewById(R.id.message_editText);
        recyclerView = findViewById(R.id.message_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final DataAdapter dataAdapter = new DataAdapter(arrayList, this);

        recyclerView.setAdapter(dataAdapter);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = editText.getText().toString();
                if (msg.equals("")) {
                    Toast.makeText(getApplicationContext(), "введите тексе", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (msg.length() > MAX_SEND_LENGTH) {
                    Toast.makeText(getApplicationContext(), "слишком длинный текст", Toast.LENGTH_SHORT).show();
                    return;
                }

                myRef.push().setValue(msg);
                editText.setText("");

            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String msg = dataSnapshot.getValue(String.class);
                arrayList.add(msg);
                dataAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(arrayList.size());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
