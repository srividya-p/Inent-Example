package com.example.inent_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ExplicitActivity extends AppCompatActivity implements Button.OnClickListener{

    String[] pokemons = {"Pikachu", "Squirtle", "Bulbasaur", "Charmander", "Togepi", "Chikorita"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explicit);

        GridView gridView = findViewById(R.id.gridView);
        TextView textView = findViewById(R.id.textView);

        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.grid_item, R.id.textView, pokemons);
        gridView.setAdapter(adapter);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this::onClick);
    }

    public void onClick(View button) {

        Intent explicit_intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(explicit_intent);

    }
}