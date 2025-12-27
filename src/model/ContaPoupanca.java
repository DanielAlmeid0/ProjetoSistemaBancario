package model;

public class ContaPoupanca extends Conta implements Tributavel {

    private Double taxaRendimento; //talvez se colocasse como um valor fixo, seria mais interessante, não sei
    private final String tipo = "CP";

    public ContaPoupanca(Integer numero, Integer agencia, Cliente titular, double taxa) {
        super(numero, agencia, titular);
        this.taxaRendimento = taxa;
    }

    public ContaPoupanca(Integer numero, Integer agencia, Cliente titular) {
        super(numero, agencia, titular);
        this.taxaRendimento = 1.5;
    }

    public ContaPoupanca(Cliente titular){
        super(titular);
    }

    public void calcularRendimento(){
        if (this.taxaRendimento != null && this.saldo > 0) {
            double valorDoRendimento = this.saldo * this.taxaRendimento;
            this.saldo += valorDoRendimento;
        }
    }

    @Override
    public String toStringARQ(){
        return tipo +";"+ super.toStringARQ();
    }

    @Override /// FALTA IMPLEMENTAR INTERFACE
    public double calcularImposto() { return taxaRendimento * saldo;}

    @Override
    public String toString() {
        return "Conta Poupanca: " +
                "\nTitular - " + titular.getNome() +
                "\nSaldo - " + saldo +
                "\nAgência - " + agencia +
                "\nNúmero da conta - " + numero;
    }
}
