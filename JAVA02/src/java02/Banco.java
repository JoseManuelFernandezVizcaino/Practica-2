package java02;

import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

import java.text.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;


public class Banco extends JFrame
{
  
    private JLabel numeroLabel;
    private JLabel propietarioLabel;
    private JLabel fechaLabel;
    private JLabel saldoLabel;

    private static String numeroCuenta = "Número de Cuenta: ";
    private static String propietarioCuenta = "Propietario de Cuenta: ";
    private static String fechaString = "Fecha de Creación: ";
    private static String saldoCuenta = "Saldo Cuenta: ";

    private TextField numeroField;
    private TextField propietarioField;
    private TextField fechaField;
    private TextField saldoField;
    private TextField emptyField;
    
    private boolean focusIsSet = false;

    public Banco() {
        super("Banco");

        numeroLabel = new JLabel(numeroCuenta);
        propietarioLabel = new JLabel(propietarioCuenta);
        fechaLabel = new JLabel(fechaString);
        saldoLabel = new JLabel(saldoCuenta);

        numeroField = new TextField(10);
        numeroField.setText(""+Cuenta.getInicio().getNumero());
        numeroField.setEditable(false);
        
        propietarioField = new TextField(10);
        propietarioField.setText(""+Cuenta.getInicio().getPropietario());
        propietarioField.setEditable(false);
        
        fechaField = new TextField(10);
        fechaField.setText(""+ Cuenta.getInicio().getFecha().get(Calendar.DATE)+"/"+Cuenta.getInicio().getFecha().get(Calendar.MONTH)+"/"+Cuenta.getInicio().getFecha().get(Calendar.YEAR));
        fechaField.setEditable(false);
        
        saldoField = new TextField(10);
        saldoField.setText(""+Cuenta.getInicio().getSaldo());
        saldoField.setEditable(false);
        
        
       emptyField = new TextField(10);
       emptyField.setVisible(false);
       emptyField.setEditable(false);
       emptyField.setBounds(30,30,30,30);
       
       JButton botonAnterior = new JButton("Anterior");
       JButton botonSiguiente = new JButton("Siguiente");
       JButton botonCrear = new JButton("Nueva Cuenta");
       botonCrear.setBounds(1, 1, 1, 1);
        
       JButton botonAceptar = new JButton("Aceptar");
       JButton botonCancelar = new JButton("Cancelar");
        
       botonAceptar.setVisible(false);
       botonCancelar.setVisible(false);
       
       JPanel labelPanel = new JPanel();
       labelPanel.setLayout(new GridLayout(0, 1));
       labelPanel.add(numeroLabel);
       labelPanel.add(propietarioLabel);
       labelPanel.add(fechaLabel);
       labelPanel.add(saldoLabel);
       labelPanel.add(botonAnterior);
       labelPanel.add(botonCancelar);
        
       JPanel middlePanel = new JPanel();
       middlePanel.setLayout(new GridLayout(0, 1));
       
       middlePanel.add(botonCrear);
        
       JPanel fieldPanel = new JPanel();
       fieldPanel.setLayout(new GridLayout(0, 1));
       fieldPanel.add(numeroField);
       fieldPanel.add(propietarioField);
       fieldPanel.add(fechaField);
       fieldPanel.add(saldoField);
       fieldPanel.add(botonSiguiente);
       fieldPanel.add(botonAceptar);

       JPanel contentPane = new JPanel();
       contentPane.setBorder(
       BorderFactory.createEmptyBorder(20, 20, 20, 20));
       contentPane.setLayout(new BorderLayout());
       contentPane.add(labelPanel, BorderLayout.WEST);
       contentPane.add(middlePanel, BorderLayout.SOUTH);
       contentPane.add(fieldPanel, BorderLayout.EAST);
        
       setContentPane(contentPane); 

       botonAnterior.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                 Cuenta cuenta = Cuenta.getActual();
                if(cuenta.getAnterior() != null){
                    Cuenta.setActual(cuenta.getAnterior());
                    cuenta = Cuenta.getActual();    
                    actualizarP(cuenta); 
                    botonSiguiente.setEnabled(true);
                }
                else{
                    botonAnterior.setEnabled(false);
                }
            }
        });
       
          
        botonSiguiente.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                Cuenta cuenta = Cuenta.getActual();
                if(cuenta.getSiguiente() != null){                 
                    Cuenta.setActual(cuenta.getSiguiente());
                    cuenta = Cuenta.getActual();
                    actualizarP(cuenta);
                botonAnterior.setEnabled(true);
                }
                else{
                    botonSiguiente.setEnabled(false);
                }

            }
        });
        
        botonCrear.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {
                
               GregorianCalendar fecha = new GregorianCalendar(new Locale("es", "ES"));
               numeroField.setText("AUTONUMERICO");
               numeroField.setBackground(Color.gray);
               propietarioField.setText("");
               fechaField.setText(""+fecha.get(Calendar.DATE)+"/"+fecha.get(Calendar.MONTH)+"/"+fecha.get(Calendar.YEAR));
               fechaField.setBackground(Color.GRAY);
               saldoField.setText("");
                
               propietarioField.setEditable(true);
               saldoField.setEditable(true);
              
               botonCrear.setVisible(false);
               botonSiguiente.setVisible(false);
               botonAnterior.setVisible(false);
               botonAceptar.setVisible(true);
               botonCancelar.setVisible(true);
                
              
               botonAceptar.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e) {
                        
                       String propietario = propietarioField.getText();
                        float saldo = Float.parseFloat(saldoField.getText());
                        Cuenta nuevaCuenta = new Cuenta(saldo, propietario, fecha.get(Calendar.DATE), fecha.get(Calendar.MONTH), fecha.get(Calendar.YEAR));       
                        

                        propietarioField.setEditable(false);
                        saldoField.setEditable(false);
                        
                        numeroField.setBackground(null);
                        fechaField.setBackground(null);
                        
                        botonSiguiente.setVisible(true);
                        botonAnterior.setVisible(true);
                        botonAceptar.setVisible(false);
                        botonCancelar.setVisible(false);
                         botonCrear.setVisible(true);
                        Cuenta cuenta = Cuenta.getActual();
                        actualizarP(cuenta);
                }             
                    
                
            });
               botonCancelar.addActionListener(new ActionListener() {  
                    public void actionPerformed(ActionEvent e) {
                       botonSiguiente.setVisible(true);
                botonAnterior.setVisible(true);
                botonAceptar.setVisible(false);
                botonCancelar.setVisible(false);  
                 botonCrear.setVisible(true);
                Cuenta cuenta = Cuenta.getActual();
                actualizarP(cuenta);
                }             
                
            });

            }
        });
       
    } 

    public static void main(String[] args) {
          
       Cuenta.crearCuenta();
       final Banco app = new Banco();

        app.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            
            public void windowActivated(WindowEvent e) {
                app.setFocus();
            }
        });
        app.pack();
        app.setVisible(true);
    }
  
    public void actualizarP(Cuenta cuenta){
        
        numeroField.setText(""+cuenta.getNumero());
        propietarioField.setText(""+cuenta.getPropietario());
        fechaField.setText(""+ cuenta.getFecha().get(Calendar.DATE)+"/"+cuenta.getFecha().get(Calendar.MONTH)+"/"+cuenta.getFecha().get(Calendar.YEAR));
        saldoField.setText(""+cuenta.getSaldo());
        
        
    }
    
    private void setFocus() {
        if (!focusIsSet) {
            numeroField.requestFocus();
            focusIsSet = true;
        }
    }

}
