package entity.cliente.tiposdecliente;

import entity.cliente.Cliente;
import entity.cliente.Endereco;

public class ClientePessoaFisica extends Cliente {

    private long cpf;

    public ClientePessoaFisica(String nome, long cpf, Endereco endereco, String data_de_nascimento) {
        super(nome, endereco, data_de_nascimento);
        this.cpf = cpf;
    }

    public long getCpf() {return cpf;}

    @Override
    public String toString() {
        return "ClientePessoaFisica: \n" + super.toString() + " | CPF - " + cpf;
    }
}
