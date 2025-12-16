package Entitite.Cliente.TiposDeCliente;

import Entitite.Cliente.Cliente;

public class ClientePessoaJuridica extends Cliente {

    private String cnpj;
    private String nome_Empresa;

    public ClientePessoaJuridica(String nome, String endereco, String cnpj, String nome_Empresa, String dataNascimento) {
        super(nome, endereco, dataNascimento);
        this.cnpj = cnpj;
        this.nome_Empresa = nome_Empresa;
    }

    public String getCnpj() {return cnpj;}
    public String getNomeEmpresa() {return nome_Empresa;}

    @Override
    public String toString() {
        return "ClientePessoaJuridica - "+super.toString() +", cnpj =" + cnpj + ", Nome da Empresa atrelada ao CNPJ =" + nome_Empresa;
    }
}
