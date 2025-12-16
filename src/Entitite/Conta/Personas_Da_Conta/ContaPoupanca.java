package Entitite.Conta.Personas_Da_Conta;

import Entitite.Cliente.Cliente;
import Entitite.Conta.Conta;
import Entitite.InvalidValueException;


public class ContaPoupanca extends Conta implements Tributavel {

    private Double taxaRendimento;

    public ContaPoupanca(Integer numero, Integer agencia, Cliente titular, double taxa) {
        super(numero, agencia, titular);
        this.taxaRendimento = taxa;
    }

    public void calcularRendimento(){
        if (this.taxaRendimento != null && this.saldo > 0) {
            double valorDoRendimento = this.saldo * this.taxaRendimento;
            this.saldo += valorDoRendimento;
        }
    }

    @Override
    public double calcularImposto() { // DA INTERFACE
        return 0;
    }

    @Override
    public void depositar(double val_deposito) throws InvalidValueException {
        if (val_deposito > 0) {
            saldo += val_deposito;
        } else {
            throw new InvalidValueException("Valor de depósito inválido!");//revisar
        }
    }

    @Override
    public void sacar(double val_saque) throws InvalidValueException {
        if (val_saque > 0 && val_saque <= saldo) {
            saldo -= val_saque;
        }else {
            throw new InvalidValueException("Saldo insuficiente para saque!");//revisar
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
