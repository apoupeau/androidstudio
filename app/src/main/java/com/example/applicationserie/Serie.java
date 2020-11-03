package com.example.applicationserie;

public class Serie {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getPremiereDiiffusion() {
        return premiereDiiffusion;
    }

    public void setPremiereDiiffusion(String premiereDiiffusion) {
        this.premiereDiiffusion = premiereDiiffusion;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Serie(long id, String titre, String resume, String duree, String premiereDiiffusion, String image) {
        this.id = id;
        this.titre = titre;
        this.resume = resume;
        this.duree = duree;
        this.premiereDiiffusion = premiereDiiffusion;
        this.image = image;
    }

    private long id;
    private String titre;
    private String resume;
    private String duree;
    private String premiereDiiffusion;
    private String image;

}
