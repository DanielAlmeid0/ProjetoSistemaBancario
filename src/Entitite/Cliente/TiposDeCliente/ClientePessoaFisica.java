package Entitite.Cliente.TiposDeCliente;

import Entitite.Cliente.Cliente;

public class ClientePessoaFisica extends Cliente {

    private long cpf;

    public ClientePessoaFisica(String nome, long cpf, String endereco, String data_de_nascimento) {
        super(nome, endereco, data_de_nascimento);
        this.cpf = cpf;
    }

    public long getCpf() {return cpf;}

    @Override
    public String toString() {
        return "ClientePessoaFisica - " + super.toString() + ", cpf - " + cpf;
    }
}
