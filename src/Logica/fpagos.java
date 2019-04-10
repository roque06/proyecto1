/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vhabitacion;
import Datos.vpagos;
import Datos.vproductos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TIANA CAROLINA
 */
public class fpagos {

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    public Integer totalregistro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos
                = {"ID", "IdReserva", "Tipo Comprobante", "Numero Comprobante", "Igv", "Total", "Fecha Emitido", "Fecha Pago"
                };

        String[] registro = new String[8];
        totalregistro = 0;

        modelo = new DefaultTableModel(null, titulos);

       sSQL="select * from pago where idreserva="+ buscar + " order by idpago desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro [0]=rs.getString("idpago");
               registro [1]=rs.getString("idreserva");
               registro [2]=rs.getString("tipo_comprobante");
               registro [3]=rs.getString("numero_comprobante");
               registro [4]=rs.getString("igv");
               registro [5]=rs.getString("total_pago");
               registro [6]=rs.getString("fecha_emisor");
               registro [7]=rs.getString("fecha_pago");
               
                totalregistro = totalregistro + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;

        }

    }

    public boolean insertar(vpagos dts) {
        sSQL = "insert into pago (idreserva,tipo_comprobante,numero_comprobante,igv,total_pago,fecha_emisor,fecha_pago)"
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdreserva());
            pst.setString(2, dts.getTipo_comprovante());
            pst.setString(3, dts.getNumero_comprovante());
            pst.setDouble(4, dts.getIgv());
            pst.setDouble(5, dts.getTotal_pago());
            pst.setDate(6, dts.getFecha_emisor());
            pst.setDate(7, dts.getFecha_pago());
            
            

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    public boolean editar(vpagos dts) {
        sSQL = "UPDATE pago set idreserva=?,tipo_comprobante=?, numero_comprobante=?,igv=?,total_pago=?,fecha_emisor=?,fecha_pago=?"
                + "where idpago=?";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdreserva());
            pst.setString(2, dts.getTipo_comprovante());
            pst.setString(3, dts.getNumero_comprovante());
            pst.setDouble(4, dts.getIgv());
            pst.setDouble(5, dts.getTotal_pago());
            pst.setDate(6, dts.getFecha_emisor());
            pst.setDate(7, dts.getFecha_pago());
            
             pst.setInt(8, dts.getIdpago());
            int n = pst.executeUpdate();
            

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return false;

    }

    public boolean eliminar(vpagos dts) {
        sSQL = "DELETE FROM pago WHERE idpago=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            pst.setInt(1, dts.getIdpago());

            int n = pst.executeUpdate();

            if (n != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        return false;

    }
}
