package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import android.content.Context;
import android.util.Log;

public class AdModel {
    int idAnnonce;
    String nomAnnonce;
    Double prixAnnonce;
    int ImageAnnonce;
    int anneeAnnonce;

    public AdModel(int idAnnonce, String nomAnnonce, Double prixAnnonce, int ImageAnnonce, int anneeAnnonce){
        this.idAnnonce = idAnnonce;
        this.nomAnnonce = nomAnnonce;
        this.prixAnnonce = prixAnnonce;
        this.ImageAnnonce = ImageAnnonce;
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

    public int getImageAnnonce() {
        return ImageAnnonce;
    }

    public void setImageAnnonce(int ImageAnnonce) {
        this.ImageAnnonce = ImageAnnonce;
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

