package e.lotz3.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class mainscreen extends AppCompatActivity implements View.OnClickListener {
    private Button parse;
    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference myRef;
    ListView l1;
    List<String> itemlist;
    UserInformation info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.button_scan).setOnClickListener(this);
        findViewById(R.id.button_add).setOnClickListener(this);
        l1 = (ListView) findViewById(R.id.listView);

        final ArrayList<Item> item = new ArrayList<>();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(userID).child("Items");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                item.clear();
                for (DataSnapshot items : dataSnapshot.getChildren()) {
                    info = items.getValue(UserInformation.class);


                    Item item1 = new Item(info.Quantity, info.Category, info.Model, items.getKey());
                    item.add(item1);
                }
                ItemListAdapter adapter = new ItemListAdapter(mainscreen.this, R.layout.adapter_new_layout, item);

                l1.setAdapter(adapter);
                l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(mainscreen.this, Scan.class);
                        Item model = item.get(position);

                        intent.putExtra("message_key", model.getKey().toString());
                        startActivity(intent);
                    }
                });
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_scan:

                startActivity(new Intent(this, qrScanner.class));

                break;
            case R.id.button_add:
                openactivity_adder();
                break;


        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this, MainActivity.class));



            break;
        }



        return true;
    }

    public void openactivity_adder(){
        Intent intent = new Intent(this, Adder.class);
        startActivity(intent);

    }
}
