package com.example.applicationserie;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class JSONhelper {

    public ArrayList<Serie> getListeSerie() {
        return listeSerie;
    }

    ArrayList<Serie> listeSerie = new ArrayList<Serie>();
    public JSONhelper(Context context){



        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset(context));
            JSONArray userArray = obj.getJSONArray("data");
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject userDetail = userArray.getJSONObject(i);
                Serie Serie = new Serie(userDetail.getLong("id"),
                        userDetail.getString("titre"),
                        userDetail.getString("resume"),
                        userDetail.getString("duree"),
                        userDetail.getString("premiereDiffusion"),
                        userDetail.getString("image"));
                SQLhelper dbhelper = new SQLhelper(context);
                dbhelper.addSerie(Serie);
                listeSerie.add(Serie);
                //Personne a = new Personne(userDetail.getString("titre"),170.0,60.0,userDetail.getString("image"));
                //lesPersonnes.add(a);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            context.getAssets();
            InputStream is = context.getAssets().open("serie.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public Serie getSerie(int position){
        return listeSerie.get(position);
    }
}
