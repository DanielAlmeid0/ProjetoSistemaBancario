package model;

public class ContaPoupanca extends Conta implements Tributavel {

    private Double taxaRendimento;

    public ContaPoupanca(Integer numero, Integer agencia, Cliente titular, double taxa) {
        super(numero, agencia, titular);
        this.taxaRendimento = taxa;
    }

    public ContaPoupanca(Integer numero, Integer agencia, Cliente titular) {
        super(numero, agencia, titular);
        this.taxaRendimento = 1.5;
    }

    public void calcularRendimento(){
        if (this.taxaRendimento != null && this.saldo > 0) {
            double valorDoRendimento = this.saldo * this.taxaRendimento;
            this.saldo += valorDoRendimento;
        }
    }

    @Override /// FALTA IMPLEMENTAR INTERFACE
    public double calcularImposto() { return taxaRendimento * saldo;}

    @Override
    public String toString() {
        return "Conta Poupanca: " +
                "Titular = " + titular.getNome() +
                " | Saldo = " + saldo +
                " | Agência = " + agencia +
                " | Número da conta = " + numero;
    }
}
