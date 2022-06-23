package com.pcoder.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

//    Declaration d'objet en rapport avec l'interface
    Button b1;
    EditText e1;
    TextView t1;
    ListView list1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Recuperer les id de l'interface
        b1 = findViewById(R.id.b1);
        e1 = findViewById(R.id.e1);
        t1 = findViewById(R.id.t1);
        list1 = findViewById(R.id.list1);

//        Declartionde de vecteur de plusieurs chaines de caracteres
        String [] vecteur = {"Sony Ericsson", "Nikon", "Canon", "Apple", "Alcatel", "Techno Camon", "Itel", "Samsung", "Huwaie", "Open", "Apple", "Alcatel", "Techno Camon", "Itel", "Samsung", "Huwaie", "Open"};

//        Mise en place d'un adaptateur native a java
        ArrayAdapter adapter;

//        Initialisation (intentiation) de l'adaptater
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, vecteur);
        list1.setAdapter(adapter);

//        Modifier les donnees de l'interface
        t1.setText("Pcoder");
        b1.setText("Send");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setText("Bonsoir " + e1.getText());

                Toast.makeText(MainActivity.this, "Merci Mr " + e1.getText() + " . Votre Messae a bien ete envoye", Toast.LENGTH_LONG).show();
            }
        });

    }
}