package java02;

import java.util.*;

public class Cuenta {
    
    private static int numeroEstatico = 1;
        
    private boolean primeraCuenta = true;
    private int numero;
    private GregorianCalendar fecha; 
    private float saldo;
    private String propietario;
    
    private Cuenta siguiente = null;
    private  Cuenta anterior = null; 
    private static Cuenta pricinpio = null;
    private static Cuenta actual = null;
    
    
    public Cuenta()
    {
    
    }

    public Cuenta(float saldo, String propietario, int dia, int mes, int anio){
        
        setFecha(anio,mes,dia);
        setNumero(numeroEstatico);
        numeroEstatico++;
        setSaldo(saldo);
        setPropietario(propietario);
        
        
        if(pricinpio == null){
            pricinpio = this;
            siguiente = null;
            anterior = null;
    
        }
        else{
            pricinpio.setAnterior(this);
            pricinpio.setPrimeraCuenta(false);
            siguiente = pricinpio;
            pricinpio = this;
            
            
        }
        
    }
    
    public void setPrimeraCuenta(boolean primeraCuenta) {
        this.primeraCuenta = primeraCuenta;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    
    public void setFecha(int anio, int mes, int dia) {
        this.fecha = new GregorianCalendar(anio, mes, dia);
    }
    
    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }
        
    public static void setInicio(Cuenta cuenta) {
        pricinpio = cuenta;
    }
    
    public static void setActual(Cuenta cuenta) {
        actual = cuenta;
    }
    
    public void setSiguiente(Cuenta aSiguiente) {
        siguiente = aSiguiente;
    }
    
    public void setAnterior(Cuenta anterior) {
        this.anterior = anterior;
    }
        
    public int getNumero() {
        return numero;
    }
    
    public boolean isPrimeraCuenta() {
        return primeraCuenta;
    }
    
    public GregorianCalendar getFecha() {
        return fecha;
    }

    public float getSaldo() {
        return saldo;
    }

    public String getPropietario() {
        return propietario;
    }

    public static Cuenta getInicio() {
        return pricinpio;
    }

    public static Cuenta getActual() {
        return actual;
    }

    public Cuenta getSiguiente() {
        return siguiente;
    }
    
     public Cuenta getAnterior() {
        return anterior;
    }
    
    @Override
    public String toString(){
        String imprime = ("Numero de cuenta: "+ this.getNumero());
        imprime += ("\nPropietario: "+ this.getPropietario());
        imprime +=("\nSaldo: "+ this.getSaldo());
        imprime +=("\nFecha de creacion: "+ this.getFecha().get(Calendar.DATE)+"/"+this.getFecha().get(Calendar.MONTH)+"/"+this.getFecha().get(Calendar.YEAR));

        return imprime;
    }
    
    public static void crearCuenta(){
        Cuenta cuenta1 = new Cuenta(10000, "Manuel", 10, 3, 2003);
        Cuenta cuenta2 = new Cuenta(2600, "Jose Manuel", 17, 6, 2021);
        Cuenta cuenta3 = new Cuenta(8000, "Carmen", 20, 8, 2010);
        Cuenta cuenta4 = new Cuenta(300, "Mercedes", 18, 3, 2019);
        Cuenta cuenta5 = new Cuenta(5000, "Antonio Jose", 22, 5, 2022);
        
        Cuenta.setActual(Cuenta.getInicio());
    }
    
    public void avanzar(){
        Cuenta.setActual(Cuenta.getActual().getSiguiente());
        
    }
    public void anterior(){
        Cuenta.setActual(Cuenta.getActual().getAnterior());
        
    }
    
}
