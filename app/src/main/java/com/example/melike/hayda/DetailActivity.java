package com.example.melike.hayda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.UUID;

public class DetailActivity extends AppCompatActivity {
    EditText AttendKeyText;
    Button buttonAttend;
    FirebaseAuth mAuth;
    DatabaseReference myRef;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        AttendKeyText=(EditText)findViewById(R.id.AttendKeyText);
        buttonAttend=(Button)findViewById(R.id.buttonAttend);

        firebaseDatabase= FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();

        Intent intent=getIntent();



    }

    public void Attend(View view){



        FirebaseUser user=mAuth.getCurrentUser();
        assert user != null;
        String userEmail= user.getEmail();
        String userAttendanceKey=AttendKeyText.getText().toString();

        UUID uuid=UUID.randomUUID();
        String uuidstring=uuid.toString();
        myRef.child("AttendaActivity").child(uuidstring).child("attenduseremail").setValue(userEmail);
        myRef.child("AttendaActivity").child(uuidstring).child("userattendancekey").setValue(userAttendanceKey);






        Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
        startActivity(intent);


    }

    public void compareKeys(){
        DatabaseReference myReference=firebaseDatabase.getReference("UploadAttendance");
        myReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dies:dataSnapshot.getChildren()){
                    System.out.println(dies.child("AttendaActivity").child("userattendancekey").getValue()
                            .equals(dies.child("UploadAttendance").child("userattendancepassword")));






                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }





    }

