package com.pcoder.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declaration d'objet en rapport avec l'interface MainActivity
    Button b1;
    EditText e1;
    TextView t1;
    ListView list1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        * Recuperer les id de l'interface activity_main
        * */
        b1 = findViewById(R.id.b1);
        e1 = findViewById(R.id.e1);
        t1 = findViewById(R.id.t1);
        list1 = findViewById(R.id.list1);

        /*
        * Declartionde de vecteur de type string
        * */
        String [] vecteur = {"Sony Ericsson", "Nikon", "Canon", "Apple", "Alcatel", "Techno Camon", "Itel", "Samsung", "Huwaie", "Open", "Apple", "Alcatel", "Techno Camon", "Itel", "Samsung", "Huwaie", "Open"};

        /*
        * Declaration d'un objet (adaptateur) de type array
        * */
        ArrayAdapter adapter;

        /*
        * Initialisation (intentiation) de l'adaptater
        * Creation de ma propre liste view a defaut de android studio
        *   adapter = new ArrayAdapter(MainActivity.this, R.layout.element, R.id.x2, vecteur);
        * Injecter les donnes dan la liste view 1 (list1)
        * */
        adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, vecteur);
        list1.setAdapter(adapter);

        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /*
            * Ajoute un envernement lors du clic sur un item de la liste
            * */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*
                * Toast:
                *       Toast.makeText(MainActivity.this, list1.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
                * Passser d'une activite a une autre prend deux paramettres:
                *   1- l'activite actuell (source)
                *   2- l'activite suivante (destinataire)
                * */
                Intent i = new Intent(MainActivity.this, MainActivity2.class);

                /*
                * Envoyer des informations sur l'interface suivante
                * exemple:
                *   i.putExtra("Telephone", "0623423536");
                * */
                i.putExtra("nom", list1.getItemAtPosition(position).toString());
                startActivity(i);
            }
        });

        /*
        * Modifier les donnees de l'interface
        * */
        t1.setText("Pcoder");
        b1.setText("Appeler");

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setText("Bonsoir " + e1.getText());
                Toast.makeText(MainActivity.this, "Merci Mr " + e1.getText() + " . Votre Messae a bien ete envoye", Toast.LENGTH_LONG).show();

                /*
                * Passser d'une activite a une autre
                * Intent i = new Intent(MainActivity.this, MainActivity2.class);
                * startActivity(i);
                * Envoie de SMS
                * SmsManager s = SmsManager.getDefault();
                * s.sendTextMessage(e1.getText().toString(), null, "Bonjour", null, null);
                * Faire un appel
                * Intent in = new Intent(Intent.ACTION_DIAL);
                * */
                Intent in = new Intent(Intent.ACTION_CALL);
                in.setData(Uri.parse("tel:" + e1.getText().toString() ));
                //in.setData(Uri.parse("tel:0789773695"));
                startActivity(in);
            }
        });

    }
}