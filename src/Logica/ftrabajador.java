/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.vcliente;
import Datos.vtrabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author roque
 */
public class ftrabajador {
    

    private conexion mysql = new conexion();
    private Connection cn = mysql.conectar();
    private String sSQL = "";
    private String sSQL2 = "";
    public Integer totalregistro;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos
                = {"ID", "Nombre", "Apellido",  "Doc", "Numero Documento", "Direccion", "Telefono", "Email", "Sueldo", "Acceso", "Login", "Contrase�a", "Estado"};

        String[] registro = new String[13];
        totalregistro = 0;

        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona, p.nombre, p.apellido,  p.tipo_documento, p.num_documento,"
                + " p.direccion,p.telefono,p.email,t.sueldo,t.acceso,t.login, t.password, t.estado from persona p inner join trabajador t "
                + "on p.idpersona= t.idpersona where nombre like '%" 
                + buscar + "%' order by idpersona desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido");
                
                registro[3] = rs.getString("tipo_documento");
                registro[4] = rs.getString("num_documento");
                registro[5] = rs.getString("direccion");
                registro[6] = rs.getString("telefono");
                registro[7] = rs.getString("email");
                registro[8] = rs.getString("sueldo");
                registro[9] = rs.getString("acceso");
                registro[10] = rs.getString("login");
                registro[11] = rs.getString("password");
                registro[12] = rs.getString("estado");
                
                totalregistro = totalregistro + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;

        }

    }

    public boolean insertar(vtrabajador dts) {
        sSQL = "insert into persona (nombre,apellido,tipo_documento,num_documento,direccion,telefono,email)"
                + "VALUES(?,?,?,?,?,?,?)";
        sSQL2 = "insert into trabajador(idpersona,sueldo,acceso,login,password,estado)"
                + "VALUES((select idpersona from persona order by idpersona desc limit 1 ),?,?,?,?,?)";

        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApaterno());
            pst.setString(3, dts.getTipo_documento());
            pst.setString(4, dts.getNum_documento());
            pst.setString(5, dts.getDireccion());
            pst.setString(6, dts.getTelefono());
            pst.setString(7, dts.getEmail());

            pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());

            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {

                    return false;

                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

    }

    public boolean editar(vtrabajador dts) {
        sSQL = "UPDATE persona  set nombre=?,apellido=?,tipo_documento=?,num_documento=?,direccion=?,telefono=?,email=?"
                + "where idpersona=?";
 
        sSQL2 = "UPDATE trabajador  set sueldo=?,acceso=?,login=?,password=?,estado=?"
                + "where idpersona=?";
         try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getApaterno());
            
            pst.setString(3, dts.getTipo_documento());
            pst.setString(4, dts.getNum_documento());
            pst.setString(5, dts.getDireccion());
            pst.setString(6, dts.getTelefono());
            pst.setString(7, dts.getEmail());
            pst.setInt(8, dts.getIdpersona());

             pst2.setDouble(1, dts.getSueldo());
            pst2.setString(2, dts.getAcceso());
            pst2.setString(3, dts.getLogin());
            pst2.setString(4, dts.getPassword());
            pst2.setString(5, dts.getEstado());
            pst2.setInt(6, dts.getIdpersona());
            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {

                    return false;

                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(vtrabajador dts) {
        sSQL = "DELETE FROM trabajador WHERE idpersona=?";
         sSQL2 = "DELETE FROM persona WHERE idpersona=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sSQL);
            PreparedStatement pst2 = cn.prepareStatement(sSQL2);
     
            pst.setInt(1, dts.getIdpersona());

         
            pst2.setInt(1, dts.getIdpersona());
            int n = pst.executeUpdate();

            if (n != 0) {
                int n2 = pst2.executeUpdate();

                if (n2 != 0) {
                    return true;

                } else {

                    return false;

                }

            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }

}
     public DefaultTableModel login(String login, String password) {
        DefaultTableModel modelo;

        String[] titulos
                = {"ID", "Nombre", "Apellido", "Acceso", "Login", "Contraseña", "Estado"};

        String[] registro = new String[7];
        totalregistro = 0;

        modelo = new DefaultTableModel(null, titulos);

        sSQL = "select p.idpersona, p.nombre, p.apellido,"
                + "t.acceso,t.login, t.password, t.estado from persona p inner join trabajador t "
                + "on p.idpersona=t.idpersona where t.login= '" 
                + login + "'  and t.password= '"  + password+"'and t.estado='A'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sSQL);

            while (rs.next()) {
                registro[0] = rs.getString("idpersona");
                registro[1] = rs.getString("nombre");
                registro[2] = rs.getString("apellido");
                registro[3] = rs.getString("acceso");
                registro[4] = rs.getString("login");
                registro[5] = rs.getString("password");
                registro[6] = rs.getString("estado");
                
                totalregistro = totalregistro + 1;
                modelo.addRow(registro);

            }
            return modelo;

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;

        }

    }
    
    }

