package e.lotz3.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mainscreen extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        findViewById(R.id.button_scan).setOnClickListener(this);
        findViewById(R.id.button_add).setOnClickListener(this);





    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_scan:

                startActivity(new Intent(this, Scan.class));

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
