package com.example.applicationserie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Detail_Serie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__serie);
        Intent intent =getIntent();
        long position = intent.getLongExtra("pos",0);

        //JSONhelper jsonSerie = new JSONhelper(Detail_Serie.this);
        //Serie laSerie = jsonSerie.getSerie(position);

        SQLhelper dbHelper = new SQLhelper(Detail_Serie.this);
        Serie laSerie = dbHelper.getSerie(position);

        TextView titre = findViewById(R.id.titre);
        titre.setText(laSerie.getTitre());
        TextView resume = findViewById(R.id.resume);
        resume.setText(laSerie.getResume());
        resume.setMovementMethod(new ScrollingMovementMethod());

        TextView diffduree = findViewById(R.id.diffduree);
        diffduree.setText("1ere diffusion : "+laSerie.getPremiereDiiffusion()+" duree : "+laSerie.getDuree());

        ImageView image = findViewById(R.id.imageView);
        Picasso.get().load(laSerie.getImage()).into(image);
    }
}