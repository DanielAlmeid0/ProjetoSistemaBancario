package model;

import repository.Transacao;

import java.io.IOException;

public class ContaCorrente extends Conta {

    private Double limiteChequeEspecial;
    private final String tipo = "CC";

    public ContaCorrente(Integer numero, Integer agencia, Cliente titular, double limite) {
        super(numero, agencia, titular);
        this.limiteChequeEspecial = limite;
    }

    public ContaCorrente(Integer numero, Integer agencia, Cliente titular) {
        super(numero, agencia, titular);
        this.limiteChequeEspecial = 0.0;
    }

    public ContaCorrente(Cliente titular) {
        super(titular);
    }

    @Override
    public String toStringARQ(){
        return tipo +";"+ super.toStringARQ();
    }

    @Override
    public boolean sacar(double val_saque) throws IOException {
        if (val_saque > 0 && (saldo + limiteChequeEspecial) >= val_saque) { //Daniel
            saldo -= val_saque;
            Transacao transacao= new Transacao("Saque", val_saque);
            historico.add(transacao);
            transacao.registrar();
            return true;
        } else {
            System.out.println("Saldo insuficiente para saque!");//revisar
            return false;
        }
    }

    @Override
    public String toString() {
        return "Conta Corrente: " +
                "\nTitular - " + titular.getNome() +
                "\nSaldo - " + saldo +
                "\nAgência - " + agencia +
                "\nNúmero da conta - " + numero;
    }
}
