package com.example.virtualwallets.transactionComponent.model;

import java.util.Date;

public class DaoTransaction {
    private Date fecha;
    private String glosa;
    private double monto;

    public DaoTransaction(Date fecha, String glosa, double monto) {
        this.fecha = fecha;
        this.glosa = glosa;
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
