package com.example.boulets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonCommencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCommencer = (Button) findViewById(R.id.buttonCommencer);
        buttonCommencer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void OnClick(View v) {
                openCreationEquipe();
            }
        });
    }

    public void openCreationEquipe() {
        Intent intent = new Intent(this, CreationEquipe.class);
        startActivity(intent);
    }
}
