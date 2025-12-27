package model;

public class Endereco {

    private String rua, cep, complemento, bairro, cidade;
    private int numeroDaCasa;

    public Endereco(String rua, String cep, int numeroDaCasa, String complemento, String bairro, String cidade) {
        this.rua = rua;
        this.cep = cep;
        this.numeroDaCasa = numeroDaCasa;
        if (complemento.isBlank() || complemento == null) {this.complemento = "Nenhum";
        }else {this.complemento = complemento;}
        this.bairro = bairro;
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Rua: " + rua +
                "\nBairro: " + bairro +
                "\nNúmero Da Casa: " + numeroDaCasa +
                "\nCidade: "+ cidade;
    }

    public String toStringARQ(){
        return cep+";"+rua +";"+ bairro +";"+ numeroDaCasa +";"+ cidade+";"+getComplemento();
    }

    public String getComplemento() {return complemento;}
}
