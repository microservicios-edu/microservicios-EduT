package com.pagosnotificaciones.pagos_notificaciones_api.model;

public class Notificacion {
    private String id;
    private String mensaje;
    private String destinatario;

    public Notificacion() {}

    public Notificacion(String id, String mensaje, String destinatario) {
        this.id = id;
        this.mensaje = mensaje;
        this.destinatario = destinatario;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }
}
