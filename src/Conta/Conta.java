package Conta;

import Cliente.Cliente;

public abstract class Conta {

    protected Integer numero;
    protected Integer agencia;
    protected Double saldo;
    protected Cliente titular;

    public Conta(Integer numero, Integer agencia, Double saldo, Cliente titular) {
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = saldo;
        this.titular = titular;
    }

    public abstract void depositar(double val_deposito);
    public abstract void sacar(double val_saque);
    public abstract void transferir(Conta conta, double valor_transferencia);
    public abstract double verSaldo();

}
