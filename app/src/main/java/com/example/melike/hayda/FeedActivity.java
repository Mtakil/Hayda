package com.example.melike.hayda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {

    ArrayList<String>useremailfromFB;
    ArrayList<String>userattendancenamefromFB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    PostClass adapter;

    ListView listView;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_attendance,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.add_attendance){
            Intent intent=new Intent(getApplicationContext(),UploadActivity.class);
            startActivity(intent);
        }




        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        useremailfromFB=new ArrayList<String>();
        userattendancenamefromFB=new ArrayList<String>();
        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();



        adapter=new PostClass(useremailfromFB,userattendancenamefromFB, this);
        listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getApplicationContext(),DetailActivity.class);

                startActivity(intent);
            }
        });


        getDataFromFirebase();

        //clickable burda yapılıyor.





    }
    protected void getDataFromFirebase(){
        DatabaseReference newReference=firebaseDatabase.getReference("UploadAttendance");

        newReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    HashMap<String,String> hashMap=(HashMap<String,String>)ds.getValue();
                    useremailfromFB.add(hashMap.get("useremail"));
                    userattendancenamefromFB.add(hashMap.get("userattendancename"));
                    adapter.notifyDataSetChanged();

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }






}
