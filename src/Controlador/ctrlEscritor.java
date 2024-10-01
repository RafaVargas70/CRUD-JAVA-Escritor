package Controlador;

import Modelo.tbEscritor;
import Vista.frmEscritor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class ctrlEscritor implements MouseListener{
    
    private frmEscritor vista;
    private tbEscritor modelo;
   
    public ctrlEscritor(frmEscritor Vista, tbEscritor Modelo){
        
        this.vista = Vista;
        this.modelo = Modelo;
    
        vista.btnAgregar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        
        modelo.MostrarEscritor(vista.jtbEscritor);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource()== vista.btnAgregar){
            
            if(vista.txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Llenar todos los campos.");
            return;
            }
            
            modelo.setNombre_Escritor(vista.txtNombre.getText());
            
            try {
                int edadNumerica = Integer.parseInt(vista.txtEdad.getText());
                if(edadNumerica > 100 || edadNumerica == 0) {
                    JOptionPane.showMessageDialog(vista, "Ingrese una edad valida");
                    return;
                }
            
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese numeros");
                return;
            }
            
            try {
                   System.out.println("este es el valor" + Double.parseDouble(vista.txtPeso.getText()));
                double peso = Integer.parseInt(vista.txtPeso.getText());
             
                if(peso < 70 || peso > 300) {
                    JOptionPane.showMessageDialog(vista, "Ingrese un peso valido");
                    return;
                }
            
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El peso debe ser un número válido.");
                return;
            }
            
            if(!vista.txtCorreo.getText().contains("@") || !vista.txtCorreo.getText().contains(".com")) {
                JOptionPane.showMessageDialog(vista, "Ingrese un correo valido");
                return;
            }
            
            modelo.setCorreo_Escritor(vista.txtCorreo.getText());
            
            
            modelo.GuardarEscritor();
            
            JOptionPane.showMessageDialog(vista, "Guardado: se guardo un Escritor");
            
            modelo.MostrarEscritor(vista.jtbEscritor);
  
        }
        
        if(e.getSource()== vista.btnActualizar){
            
            if(vista.txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Llenar todos los campos.");
            return;
            }
            
            modelo.setNombre_Escritor(vista.txtNombre.getText());
            
            try {
                int edadNumerica = Integer.parseInt(vista.txtEdad.getText());
                if(edadNumerica > 100 || edadNumerica == 0) {
                    JOptionPane.showMessageDialog(vista, "Ingrese una edad valida");
                    return;
                }
            
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Ingrese numeros");
                return;
            }
            
            try {
                
                double peso = Double.parseDouble(vista.txtPeso.getText());
                if(peso < 70 || peso > 300) {
                    JOptionPane.showMessageDialog(vista, "Ingrese un peso valido");
                    return;
                }
            
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El peso debe ser un número válido.");
                return;
            }
            
            if(!vista.txtCorreo.getText().contains("@") || !vista.txtCorreo.getText().contains(".com")) {
                JOptionPane.showMessageDialog(vista, "Ingrese un correo valido");
                return;
            }
            
            modelo.setCorreo_Escritor(vista.txtCorreo.getText());
            
            
            modelo.ActualizarEscritor(vista.jtbEscritor);
            
            JOptionPane.showMessageDialog(vista, "Actualizado: se Actualizo un Escritor");
            
            modelo.MostrarEscritor(vista.jtbEscritor);
  
        }
        
        if(e.getSource()== vista.btnEliminar){
            
            int confirmacion = JOptionPane.showConfirmDialog(vista, "¿estas seguro de querer eliminar al progamador?", "Eliminar", JOptionPane.YES_NO_OPTION);
    
            if(confirmacion == JOptionPane.YES_OPTION) {
        
                modelo.EliminarEscritor(vista.jtbEscritor);
        
                JOptionPane.showMessageDialog(vista, "Eliminado: se ha borrado el Escritor.");
        
                modelo.MostrarEscritor(vista.jtbEscritor);
            } else {
                JOptionPane.showMessageDialog(vista, "Eliminación cancelada.");
            }
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}
