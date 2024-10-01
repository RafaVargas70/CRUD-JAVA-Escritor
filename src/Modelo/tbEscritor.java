/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.frmEscritor;
import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafav
 */
public class tbEscritor {
    
    private String UUID_Escritor;
    private String Nombre_Escritor;
    private int Edad_Escritor;
    private int Peso_Escritor;
    private String Correo_Escritor;

    public String getUUID_Escritor() {
        return UUID_Escritor;
    }

    public void setUUID_Escritor(String UUID_Escritor) {
        this.UUID_Escritor = UUID_Escritor;
    }

    public String getNombre_Escritor() {
        return Nombre_Escritor;
    }

    public void setNombre_Escritor(String Nombre_Escritor) {
        this.Nombre_Escritor = Nombre_Escritor;
    }

    public int getEdad_Escritor() {
        return Edad_Escritor;
    }

    public void setEdad_Escritor(int Edad_Escritor) {
        this.Edad_Escritor = Edad_Escritor;
    }

    public int getPeso_Escritor() {
        return Peso_Escritor;
    }

    public void setPeso_Escritor(int Peso_Escritor) {
        this.Peso_Escritor = Peso_Escritor;
    }



    public String getCorreo_Escritor() {
        return Correo_Escritor;
    }

        public void setCorreo_Escritor(String Correo_Escritor) {
        this.Correo_Escritor = Correo_Escritor;
    }
    
    public void GuardarEscritor() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement newEscritor = conexion.prepareStatement("INSERT INTO tbEscritor(UUID_Escritor, Nombre_Escritor, Edad_Escritor, Peso_Escritor, Correo_Escritor) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            newEscritor.setString(1, UUID.randomUUID().toString());
            newEscritor.setString(2, getNombre_Escritor());
            newEscritor.setInt(3, getEdad_Escritor());
            newEscritor.setInt(4, getPeso_Escritor());
            newEscritor.setString(5, getCorreo_Escritor());
            
            newEscritor.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en el metodo guardar Escritor " + ex);
        }
    }
    
    public void MostrarEscritor(JTable tabla) {

        Connection conexion = ClaseConexion.getConexion();

        DefaultTableModel ModEscritor = new DefaultTableModel();
        
        ModEscritor.setColumnIdentifiers(new Object[]{"UUID_Escritor", "Nombre_Escritor", "Edad_Escritor", "Peso_Escritor", "Correo_Escritor"});
        try {

            Statement statement = conexion.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM tbEscritor");

            while (rs.next()) {

                ModEscritor.addRow(new Object[]{rs.getString("UUID_Escritor"), 
                    rs.getString("Nombre_Escritor"), 
                    rs.getInt("Edad_Escritor"),
                    rs.getDouble("Peso_Escritor"),
                    rs.getString("Correo_Escritor")});
            }

            tabla.setModel(ModEscritor);
            
        } catch (Exception e) {
            System.out.println("Error en metodo mostrar Escritor: " + e);
        }
    }
    
     public void cargarDatosTabla(frmEscritor Vista) {

    int filaSeleccionada = Vista.jtbEscritor.getSelectedRow();

    if (filaSeleccionada != -1) {
        String UUID_Escritor = Vista.jtbEscritor.getValueAt(filaSeleccionada, 0).toString();
        String Nombre_Escritor = Vista.jtbEscritor.getValueAt(filaSeleccionada, 1).toString();
        String Edad_Escritor = Vista.jtbEscritor.getValueAt(filaSeleccionada, 2).toString();
        String Peso_Escritor = Vista.jtbEscritor.getValueAt(filaSeleccionada, 3).toString();
        String Correo_Escritor = Vista.jtbEscritor.getValueAt(filaSeleccionada, 4).toString();

        Vista.txtNombre.setText(Nombre_Escritor);
        Vista.txtEdad.setText(Edad_Escritor);
        Vista.txtPeso.setText(Peso_Escritor);  
        Vista.txtCorreo.setText(Correo_Escritor);
    }
}
     
     public void ActualizarEscritor(JTable tabla){

        Connection conexion = ClaseConexion.getConexion();

        int filaseleccionada = tabla.getSelectedRow();
        if(filaseleccionada != -1){

            String UUIDEscritor = tabla.getValueAt(filaseleccionada, 0).toString();
            
            try{

                PreparedStatement ActualizarEscritor = conexion.prepareStatement("UPDATE tbEscritor set Nombre_Escritor =?, Edad_Escritor =?, Peso_Escritor =?, Correo_Escritor =? WHERE UUID_Escritor =?");
                ActualizarEscritor.setString(1, getNombre_Escritor());
                ActualizarEscritor.setInt (2, getEdad_Escritor());
                ActualizarEscritor.setDouble(3, getPeso_Escritor());
                ActualizarEscritor.setString(4, getCorreo_Escritor());
                ActualizarEscritor.setString(5, UUIDEscritor);
                ActualizarEscritor.executeUpdate();
                
            }
            catch(Exception e){
                System.out.println("Error en el metodo actualizar Escritor: " + e);
            }
        }
        else {
            System.out.println("no se pudo actualizar");
        }

    }
     
     public void EliminarEscritor(JTable tabla){
        
        Connection conexion = ClaseConexion.getConexion();
        
        int filaSeleccionada = tabla.getSelectedRow();
        
        String UUIDEscritor = tabla.getValueAt(filaSeleccionada, 0).toString();
        
        try{
            PreparedStatement EliminarEscritor = conexion.prepareStatement("DELETE FROM tbEscritor WHERE UUID_Escritor =?");
            EliminarEscritor.setString(1, UUIDEscritor);
            EliminarEscritor.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Error en el metodo eliminar Escritor: " + e);
        }
    }

  
    
}
