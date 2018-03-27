package e.lotz3.barcodescanner;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Adder extends AppCompatActivity {
    private static final String TAG = "AddToDatabase";
    private Button parse;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth mAuth;
    AuthStateListener mAuthListener;
    DatabaseReference myRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adder);
        parse = (Button) findViewById(R.id.button_add);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




        parse.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
               DatabaseReference myRef = database.getReference(userID).child("Items").push();



                String key = myRef.getKey();//Obtains Item key ready to be generated into QR code



               EditText sup = (EditText)findViewById(R.id.text_supplier);
               EditText cat = (EditText)findViewById(R.id.text_category);
               EditText bra = (EditText)findViewById(R.id.text_brand);
               EditText mod = (EditText)findViewById(R.id.text_model);
               EditText des = (EditText)findViewById(R.id.text_description);
                String supplier = sup.getText().toString();
                String category = cat.getText().toString();
                String brand = bra.getText().toString();
                String model = mod.getText().toString();
                String description = des.getText().toString();

               myRef.child("Supplier").setValue(supplier);
               myRef.child("Category").setValue(category);
                myRef.child("Brand").setValue(brand);
                myRef.child("Model").setValue(model);
                myRef.child("Description").setValue(description);


                setContentView(R.layout.activity_mainscreen);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //add a toast to show when successfully signed in
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}


