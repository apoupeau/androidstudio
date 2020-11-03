package com.example.applicationserie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AffListeSerie extends AppCompatActivity {

    ArrayList<Serie> listeSerie = new ArrayList<Serie>();
    ListView lstView;
    SerieAdapter mAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_aff_liste_serie);
            //JEAN-MARC SON
            //JSONhelper listeSerieJSON = new JSONhelper(MainActivity.this);
            //listeSerie = listeSerieJSON.getListeSerie();

            SQLhelper listeSerieSQL = new SQLhelper(AffListeSerie.this);
            listeSerie = listeSerieSQL.getLesSeries();

            lstView = findViewById(R.id.lsdView);
            mAdapter = new SerieAdapter(AffListeSerie.this, listeSerie);
            lstView.setAdapter(mAdapter);


            lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object listItem = lstView.getItemAtPosition(position);
                    Serie serie = (Serie) listItem;
                    //long mid = serie.getId();
                    Intent detailSerieActivity = new Intent(AffListeSerie.this, Detail_Serie.class);
                    detailSerieActivity.putExtra("pos", serie.getId());
                    startActivity(detailSerieActivity);}
                });
        }
}




