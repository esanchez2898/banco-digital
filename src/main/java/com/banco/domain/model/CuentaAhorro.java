package com.banco.domain.model;

// Cuenta de ahorro con tasa de interes

import com.banco.domain.exception.SaldoInsuficienteException;

public class CuentaAhorro extends Cuenta {

    private double tasaInteres;



    // Constructor
    public CuentaAhorro(double tasaInteres) {
        super();
        this.tasaInteres = tasaInteres;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }



    @Override
    public void depositar(double monto) {
        this.saldo += monto;
        this.historial.add(new Transaccion(monto, TipoTransaccion.DEPOSITO, "Deposito en cuenta de ahorro" ));
    }

    @Override
    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto > this.saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente en cuenta de ahorro");
        }
        this.saldo -= monto;
        this.historial.add(new Transaccion(monto, TipoTransaccion.RETIRO, "Retiro de cuenta de ahorro"));
    }

    public void aplicarInteres() {
        double interes = this.saldo * this.tasaInteres;
        this.saldo += interes;
        this.historial.add(new Transaccion(interes,TipoTransaccion.DEPOSITO, "Aplicacion de interes: +" + interes));
    }
}
