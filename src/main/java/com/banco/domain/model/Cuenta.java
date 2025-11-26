package com.banco.domain.model;

/* Clase abstracta que representa una cuenta basica.
        NO SE PUEDE INSTANCIAR, SOLO SE PUEDE USAR PARA HEREDAR!
 */

import com.banco.domain.exception.SaldoInsuficienteException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Cuenta {



    // Atributos
    protected String numero;
    protected double saldo;
    protected LocalDate fechaCreacion;
    protected List<Transaccion> historial = new ArrayList<>();



    // Constructor
    public Cuenta() {
        this.numero = UUID.randomUUID().toString(); // UUID es una clase de Java (java.util.UUID) que genera identificadores únicos universales (Universally Unique Identifier).
        this.fechaCreacion = LocalDate.now();
        this.saldo = 0.0;
    }



    // Getters
    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public List<Transaccion> getHistorial() {
        return historial;
    }



    // Operaciones Abstractas (Contrato para clases hijas)
    public abstract void depositar(double monto);
    public abstract void retirar(double monto) throws SaldoInsuficienteException;



    // Transferencia por defecto -> Usando el previo metodo RETIRAR en origen, y DEPOSITAR en destino
    public void Transferir(Cuenta destino, double monto) {
        this.retirar(monto);
        destino.depositar(monto);
        this.historial.add(new Transaccion(monto, TipoTransaccion.TRANSFERENCIA, "Transferencia a " + destino.getNumero()));
        destino.getHistorial().add(new Transaccion(monto, TipoTransaccion.TRANSFERENCIA, "Transferencia desde " + this.getNumero()));
    }



}
