package com.example.progettoapp;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    String testo;
    TextView textView;
    TextView risultato;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCaricaOgg = findViewById(R.id.buttonCaricaOggetto);
        Button buttonSalvaOgg = findViewById(R.id.buttonSalvaOgg);
          risultato = findViewById(R.id.txtProva2);


        buttonSalvaOgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUtility.salvaOggettoInMemoria(MainActivity.this,"salvataggioProva",new Utente("Dankan","Mcloude","clan Mcloude",1678));

            }
        });

        buttonCaricaOgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dir =MainActivity.this.getFilesDir().getAbsolutePath();
                Object o= FileUtility.recuperaOggettoDallaMemoria(MainActivity.this,"salvataggioProva");
                Utente Hailander=(Utente) o;
                String datiUtente=""+Hailander.nome+" "+Hailander.cognome+" "+Hailander.clan+" "+Hailander.eta+"\n"+dir;
                risultato.setText(datiUtente);



            }
        });




        textView = findViewById(R.id.txtProva);
        textView.setText("testo default");
       String t=FileUtility.getPreference(MainActivity.this,"chiave","nessun testo salvato");
       textView.setText(t);
       // FileUtility.getPreference("ok",77777)

        Button button = findViewById(R.id.buttonSalvaPref);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView.setText("Testo salvatojjjj");
                testo= textView.getText().toString();

                FileUtility.savePreference(MainActivity.this,"chiave",testo);


            }
        });

    }
}
