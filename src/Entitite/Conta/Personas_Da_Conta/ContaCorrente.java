package Entitite.Conta.Personas_Da_Conta;

import Entitite.Cliente.Cliente;
import Entitite.Conta.Conta;
import Entitite.ValueInvalidException;

public class ContaCorrente extends Conta {

    private Double limiteChequeEspecial;

    public ContaCorrente(Integer numero, Integer agencia, Cliente titular, double limite) {
        super(numero, agencia, titular);
        this.limiteChequeEspecial = limite;
    }

    public Double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    @Override
    public void depositar(double val_deposito) throws ValueInvalidException{
        if (val_deposito > 0) {
            saldo += val_deposito;
        } else {
            throw new ValueInvalidException("Valor de depósito inválido!");//revisar
        }
    }

    @Override
    public void sacar(double val_saque) throws ValueInvalidException{
        if (val_saque > 0 && val_saque <= (saldo + limiteChequeEspecial)) { //Daniel
            saldo -= val_saque;
        } else {
            throw new ValueInvalidException("Saldo insuficiente para saque!");//Revisar
        }
    }

    @Override
    public double verSaldo() {
        return super.saldo;
    }

    @Override
    public void transferir(Conta conta, double valor_transferencia) {

    }

}
