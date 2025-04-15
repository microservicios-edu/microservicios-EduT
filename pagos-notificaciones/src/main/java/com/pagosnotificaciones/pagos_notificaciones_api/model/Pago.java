package com.pagosnotificaciones.pagos_notificaciones_api.model;

public class Pago {

    private String id;
    private String usuario;
    private double monto;

    public Pago() {}

    public Pago(String id, String usuario, double monto) {
        this.id = id;
        this.usuario = usuario;
        this.monto = monto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
