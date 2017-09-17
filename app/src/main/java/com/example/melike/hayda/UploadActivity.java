package com.example.melike.hayda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class UploadActivity extends AppCompatActivity {

    EditText NameText, PassText;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        NameText=(EditText)findViewById(R.id.NameText);
        PassText=(EditText)findViewById(R.id.PassText);
        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();
        mAuth=FirebaseAuth.getInstance();



    }

    public void upload(View view){
        FirebaseUser user=mAuth.getCurrentUser();
        assert user != null;
        String userEmail= user.getEmail();
        String userAttendanceName=NameText.getText().toString();
        String userAttendancePassword=PassText.getText().toString();
        UUID uuid=UUID.randomUUID();
        String uuidstring=uuid.toString();
        myRef.child("UploadAttendance").child(uuidstring).child("useremail").setValue(userEmail);
        myRef.child("UploadAttendance").child(uuidstring).child("userattendancename").setValue(userAttendanceName);
        myRef.child("UploadAttendance").child(uuidstring).child("userattendancepassword").setValue(userAttendancePassword);

        Toast.makeText(getApplicationContext(),"Attendance shared",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getApplicationContext(),FeedActivity.class);
        startActivity(intent);




    }






}
