package e.lotz3.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Scan extends AppCompatActivity   {

    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;
    Button retrieve, fetch;
    String key;

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                key = data.getStringExtra("scannedValue");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        retrieve = (Button) findViewById(R.id.scan_button_add);
        fetch = (Button) findViewById(R.id.scan_button_delete);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        final EditText supplier = (EditText)findViewById(R.id.scan_text_supplier);
        final EditText brand = (EditText)findViewById(R.id.scan_text_brand);
        final EditText category = (EditText)findViewById(R.id.scan_text_category);
        final EditText description = (EditText)findViewById(R.id.scan_text_description);
        final EditText model = (EditText)findViewById(R.id.scan_text_model);

    fetch.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Scan.this, qrScanner.class);
            startActivityForResult(intent, 1);
        }
    });
    retrieve.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FirebaseUser user = mAuth.getCurrentUser();
            String userID = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();




            DatabaseReference myRef = database.getReference(userID).child("Items").child(key).child("Brand");
            DatabaseReference myRef2 = database.getReference(userID).child("Items").child(key).child("Category");
            DatabaseReference myRef3 = database.getReference(userID).child("Items").child(key).child("Description");
            DatabaseReference myRef4 = database.getReference(userID).child("Items").child(key).child("Model");
            DatabaseReference myRef5 = database.getReference(userID).child("Items").child(key).child("Supplier");



            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    brand.setText(dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    category.setText(dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    description.setText(dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    model.setText(dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            myRef5.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    supplier.setText(dataSnapshot.getValue().toString());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    });


    }



}
