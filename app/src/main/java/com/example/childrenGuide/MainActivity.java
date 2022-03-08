package com.example.childrenGuide;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //____________________________________get Card Number Id____________________________________
        CardView cardNumbers = findViewById(R.id.openNumbersList);
        cardNumbers.setOnClickListener(this);
        //____________________________________get Card Colors Id____________________________________
        CardView cardColor = findViewById(R.id.openColorsList);
        cardColor.setOnClickListener(this);
        //____________________________________get Card Phrases Id___________________________________
        CardView cardPhrases = findViewById(R.id.openPhrasesList);
        cardPhrases.setOnClickListener(this);
        //____________________________________get Card Family Id____________________________________
        CardView cardFamily = findViewById(R.id.openFamilyList);
        cardFamily.setOnClickListener(this);
        //__________________________________________________________________________________________
    }

    @Override
    protected void onResume() {
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();
        super.onResume();
    }

    @Override
    protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "On Destroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this, "ORestart", Toast.LENGTH_SHORT).show();
        super.onRestart();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.openNumbersList){
            Intent openNumberIntent = new Intent(this , NumbersActivity.class);
            startActivity(openNumberIntent);
        }else if(view.getId() == R.id.openFamilyList){
            Intent openFamilyIntent = new Intent(this , FamilyActivity.class);
            startActivity(openFamilyIntent);
        }else if(view.getId() == R.id.openColorsList){
            Intent openColorsIntent = new Intent(this , ColorsActivity.class);
            startActivity(openColorsIntent);
        }else if(view.getId() == R.id.openPhrasesList){
            Intent openPhrasesIntent = new Intent(this , PhrasesActivity.class);
            startActivity(openPhrasesIntent);
        }
    }


}