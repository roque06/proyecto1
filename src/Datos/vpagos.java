



package Datos;

import java.sql.Date;




public class vpagos {
    private int idpago;
    private int idreserva;
    private String tipo_comprovante;
    private String numero_comprovante;
    private Double igv;
    private Double total_pago;
    private Date fecha_emisor;
    private Date fecha_pago;

    public vpagos() {
    }

    public vpagos(int idpago, int idreserva, String tipo_comprovante, String numero_comprovante, Double igv, Double total_pago, Date fecha_emisor, Date fecha_pago) {
        this.idpago = idpago;
        this.idreserva = idreserva;
        this.tipo_comprovante = tipo_comprovante;
        this.numero_comprovante = numero_comprovante;
        this.igv = igv;
        this.total_pago = total_pago;
        this.fecha_emisor = fecha_emisor;
        this.fecha_pago = fecha_pago;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String getTipo_comprovante() {
        return tipo_comprovante;
    }

    public void setTipo_comprovante(String tipo_comprovante) {
        this.tipo_comprovante = tipo_comprovante;
    }

    public String getNumero_comprovante() {
        return numero_comprovante;
    }

    public void setNumero_comprovante(String numero_comprovante) {
        this.numero_comprovante = numero_comprovante;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getTotal_pago() {
        return total_pago;
    }

    public void setTotal_pago(Double total_pago) {
        this.total_pago = total_pago;
    }

    public Date getFecha_emisor() {
        return fecha_emisor;
    }

    public void setFecha_emisor(Date fecha_emisor) {
        this.fecha_emisor = fecha_emisor;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }
    
    
    
    
}
