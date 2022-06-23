package com.pcoder.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    //Declaration d'objet en rapport avec l'interface MainActivity2
    EditText e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Recuperer les id de l'interface MainActivity2
        e2 = findViewById(R.id.e2);

        //Recuperer l'Indent que nous renvoie le MainActivity
        Intent x = getIntent();

        //Recuperer la valeur du nom pusher depuis le MainActivity et l'assigner a e2
        e2.setText(x.getStringExtra("nom"));
    }
}