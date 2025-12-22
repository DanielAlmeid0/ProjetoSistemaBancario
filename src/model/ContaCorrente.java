package model;

import exception.InvalidValueException;

public class ContaCorrente extends Conta {

    private Double limiteChequeEspecial;

    public ContaCorrente(Integer numero, Integer agencia, Cliente titular, double limite) {
        super(numero, agencia, titular);
        this.limiteChequeEspecial = limite;
    }

    public ContaCorrente(Integer numero, Integer agencia, Cliente titular) {
        super(numero, agencia, titular);
        this.limiteChequeEspecial = 0.0;
    }

    public Double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public void sacar(double val_saque) throws InvalidValueException{ //deve implementar transação
        if (val_saque > 0 && (saldo + limiteChequeEspecial) >= val_saque) { //Daniel
            saldo -= val_saque;
        } else {
            throw new InvalidValueException("Saldo insuficiente para saque!");//revisar
        }
    }

    @Override
    public String toString() {
        return "Conta Corrente: " +
                "Titular = " + titular.getNome() +
                " | Saldo = " + saldo +
                " | Agência = " + agencia +
                " | Número da conta = " + numero;
    }
}
