package org.imt.nordeurope.titouanauclair.tp_leboncoin;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.Random;

public class AdModel implements Serializable {
    public int idAnnonce;
    private String nomAnnonce;
    private Double prixAnnonce;
    private int ImageAnnonce;
    private int anneeAnnonce;
    private String description;
    private String phoneNumber;
    private String email;

    public AdModel(String nomAnnonce, Double prixAnnonce, int ImageAnnonce, int anneeAnnonce, String phoneNumber, String email, String description){
        this.idAnnonce = generateId();
        this.nomAnnonce = nomAnnonce;
        this.prixAnnonce = prixAnnonce;
        this.ImageAnnonce = ImageAnnonce;
        this.anneeAnnonce = anneeAnnonce;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public AdModel(String nomAnnonce, Double prixAnnonce, int ImageAnnonce, int anneeAnnonce, String description) {
        this(nomAnnonce, prixAnnonce, ImageAnnonce, anneeAnnonce, null, null, description);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private int generateId() {
        Random r = new Random();
        return r.nextInt(999999999);
    }
};

