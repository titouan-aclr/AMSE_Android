package org.imt.nordeurope.titouanauclair.tp_leboncoin;

public class AdModel {
    int idAnnonce;
    String nomAnnonce;
    Double prixAnnonce;
    String urlImageAnnonce;
    int anneeAnnonce;

    public AdModel(int idAnnonce, String nomAnnonce, Double prixAnnonce, String urlImageAnnonce, int anneeAnnonce){
        this.idAnnonce = idAnnonce;
        this.nomAnnonce = nomAnnonce;
        this.prixAnnonce = prixAnnonce;
        this.urlImageAnnonce = urlImageAnnonce;
        this.anneeAnnonce = anneeAnnonce;
    }

    public String getNomAnnonce() {
        return nomAnnonce;
    }

    public void setNomAnnonce(String nomAnnonce) {
        this.nomAnnonce = nomAnnonce;
    }

    public Double getPrixAnnonce() {
        return prixAnnonce;
    }

    public void setPrixAnnonce(Double prixAnnonce) {
        this.prixAnnonce = prixAnnonce;
    }

    public String getUrlImageAnnonce() {
        return urlImageAnnonce;
    }

    public void setUrlImageAnnonce(String urlImageAnnonce) {
        this.urlImageAnnonce = urlImageAnnonce;
    }

    public int getAnneeAnnonce() {
        return anneeAnnonce;
    }

    public void setAnneeAnnonce(int anneeAnnonce) {
        this.anneeAnnonce = anneeAnnonce;
    }

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }
};

