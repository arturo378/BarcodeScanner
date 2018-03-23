package e.lotz3.barcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Scan extends AppCompatActivity implements View.OnClickListener  {

    int minteger = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        findViewById(R.id.button_decrease).setOnClickListener(this);
        findViewById(R.id.button_increase).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_decrease:



                break;
            case R.id.button_increase:

                break;


        }
    }
}
