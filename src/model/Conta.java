package model;

import repository.Transacao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Conta{

    protected Integer numero;
    protected Integer agencia;
    protected Double saldo;
    protected Cliente titular;
    protected List<Transacao> historico;

    public Conta(int numero, int agencia, Cliente titular) {
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = 0.0;
        this.titular = titular;
        this.titular.vincularConta(this);
        this.historico = new ArrayList<>();
    }

    public Conta(Cliente titular){
        this.titular = titular;
    }

    public boolean depositar(double val_deposito) throws IOException {
        if (val_deposito > 0) {
            saldo += val_deposito;
            Transacao transacao = new Transacao("Deposito", val_deposito);
            this.historico.add(transacao);
            transacao.registrar();
            return true;
        } else {
            System.out.println("Valor de depósito inválido!");
            return false;
        }
    }

    public boolean sacar(double val_saque) throws IOException {
        if (val_saque > 0 && val_saque <= saldo && saldo >= 0) {
            saldo -= val_saque;
            Transacao transacao = new Transacao("Saque", val_saque);
            historico.add(transacao);
            transacao.registrar();
            return true;
        } else {
            System.out.println("Saldo insuficiente para saque!");//revisar
            return false;
        }
    }

    public boolean transferir(Conta conta, double valor_transferencia) throws IOException {
        if (valor_transferencia > 0 && this.getSaldo() >= valor_transferencia) {
            this.setSaldo(this.getSaldo() - valor_transferencia);
            conta.depositar(valor_transferencia);
            Transacao transacao = new Transacao("Transferencia", valor_transferencia);
            this.historico.add(transacao);
            transacao.registrar();
            return true;
        }else {
            System.out.println("FALHA NA TRANSFERÊNCIA, SALDO INSUFICENTE!");
            return false;
        }
    }

    /// retorna uma string com o extrato completo
    public String gerarExtrato() {
        StringBuilder extrato = new StringBuilder();

        extrato.append("--- EXTRATO BANCARIO ---\n");
        extrato.append("Titular: ").append( titular.getNome()).append("\n");

        for(Transacao transacao: historico) {
            extrato.append(transacao.toString()).append("\n");
        }

        extrato.append("------------------------\n");

        return extrato.toString();
    }

    public String toStringARQ(){
        return getNumero() +";"+ getAgencia() +";"+ getTitular().getNome() +";"+ getSaldo();
    }

    public double getSaldo(){return saldo;}
    public Integer getNumero() {return numero;}
    public Integer getAgencia() {return agencia;}
    public Cliente getTitular() {return titular;}
    public void setSaldo(Double saldo) {this.saldo = saldo;}

}
