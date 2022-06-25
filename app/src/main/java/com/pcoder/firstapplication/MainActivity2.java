package com.pcoder.firstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity2 extends AppCompatActivity {

    /*
    * Declaration d'objet en rapport avec l'interface activity_main2
    * */
    EditText e2, e3, e4, e5;
    Button btn_inserer;
    ListView list2;

    /*
    * Declaration d'objet (adaptateur) pour la lecture des donnes
    * */
    SimpleCursorAdapter adapter;

    /*
    * Creation d'un object pour l'utilisation de SQLite (Creation de base de la donnee)
    * */
    SQLiteOpenHelper helper;

    /*
    * Utilisation de la base de donnee (creation d'objet)
    * */
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*
        * Recuperer les id de l'interface MainActivity2
        * */
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        e4 = findViewById(R.id.e4);
        e5 = findViewById(R.id.e5);
        btn_inserer = findViewById(R.id.btn_inserer);
        list2 = findViewById(R.id.list2);


        /*
        * Recuperer l'Indent que nous renvoie le MainActivity
        * Recuperer la valeur du nom pusher depuis le MainActivity et l'assigner a e2
        * */
        //Intent x = getIntent();
        //e2.setText(x.getStringExtra("nom"));



        helper = new SQLiteOpenHelper(MainActivity2.this, "Database2.db", null, 1) {
            /* Intentiation
                - Parameettre:
                    - Indentifier la classe ou creer la base de donne
                    - Nom de la base de donnee
                    - null
                    - Donnee la version de la base de donnee
             */
            @Override
            public void onCreate(SQLiteDatabase db) {
                /*
                * Creer des tables
                * NB: id dans les application mobile doit avoir un tire de 8 (exemple _id)
                * */
                db.execSQL("CREATE TABLE Voitures (_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, Nom TEXT, Prix REAL, Couleur TEXT, Annee INTEGER, Km INTEGER, Marque TEXT)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                /*
                * Mise a jour de la base de donnee
                * Lors de la migration des donnee
                * */
                db.execSQL("DROP TABLE Voitures");
                onCreate(db);
            }
        };

        /*
        * Lien la base de donnee cree avec l'objet database
        * Lectures des donnes de la base de donne
        * Declaration objet cursor
        * Creation de deux vecteurs  de type string et l'autre de type int
        * Instentiation de l'adapteur avec six parametre:
        *   1- Lactivite utiliser
        *   2- le mode d'affichage
        *   3- injection du cursor
        *   4- Ajoute du vecteur string pour les attributs de la table
        *   5- Afficher la valeur de l'attrobut de la table
        *   6- 0
        * Injecter les donnes dan la liste view 2 (list2)
        * */
        database = helper.getReadableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM Voitures", null);
        c.moveToNext();
        String [] from = {"Nom", "Prix"};
        int [] to = {android.R.id.text1, android.R.id.text2};
        adapter = new SimpleCursorAdapter(MainActivity2.this, android.R.layout.simple_list_item_2, c, from, to, 0);
        list2.setAdapter(adapter);


        e5.setOnKeyListener(new View.OnKeyListener() {
            /*
             * Faire une recherche avec un mot cle exacte
             *
             * */
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                database = helper.getReadableDatabase();
                Cursor c = database.rawQuery("SELECT * FROM Voitures WHERE Nom LIKE '%"+ e5.getText() +"%' ", null);
                c.moveToNext();
                String [] from = {"Nom", "Prix"};
                int [] to = {android.R.id.text1, android.R.id.text2};
                adapter = new SimpleCursorAdapter(MainActivity2.this, android.R.layout.simple_list_item_2, c, from, to, 0);
                list2.setAdapter(adapter);
                return false;
            }
        });

        btn_inserer.setOnClickListener(new View.OnClickListener() {
            /*
            * Evenement lorsqu'on clique sur le bouton insere
            * */
            @Override
            public void onClick(View v) {
                /*
                *Lier la base de donnee cree avec l'objet database (ecriture)
                * Inserer des donnees dans la base de donnee
                * */
                database = helper.getWritableDatabase();
                database.execSQL("INSERT INTO Voitures (Nom, Prix, Km) VALUES ('" + e2.getText() + "', " + e3.getText() + ", "+ e4.getText() +")");
            }
        });
    }
}