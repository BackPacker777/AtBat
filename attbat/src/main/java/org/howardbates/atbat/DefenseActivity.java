package org.howardbates.atbat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class DefenseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_defense);
         returnOnClick();
    }

     private void returnOnClick() {
          ImageButton returnButton = (ImageButton) findViewById(R.id.returnButton);
          returnButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                    finish();
               }
          });
     }
}
