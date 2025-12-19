package Service;

import Entitite.Cliente.Cliente;
import Entitite.Cliente.Endereco;
import Entitite.Cliente.TiposDeCliente.ClientePessoaFisica;
import Entitite.Cliente.TiposDeCliente.ClientePessoaJuridica;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Banco {
    private String nome_do_Banco;
    private String codigo;
    private List<Cliente> clientesDoBanco;

    public Banco(String nomeBanco, String codigo){
        this.nome_do_Banco = nomeBanco;
        this.codigo = codigo;
        this.clientesDoBanco = new ArrayList<>();
    }

    public boolean adicionarCliente(Scanner sc) throws InputMismatchException{
        String cpf, cnpj, dataDeNascimento, nome, nomeEmpresa, cidade, bairro, rua, complemento;
        Endereco enderecoDeCriacao;

        try {
            System.out.println("O cliente é Pessoa Física(PF) ou Pessoa Jurídica(PJ)?");
            String tipoCliente = sc.next();
            sc.nextLine();

            System.out.println("Nome do cliente: ");
            nome = sc.nextLine();
            System.out.println("Digite a data de nascimento do cliente: ");
            dataDeNascimento = sc.nextLine();

            if (tipoCliente.equalsIgnoreCase("PF")){
                System.out.println("CPF do cliente: ");
                cpf = sc.next();

                enderecoDeCriacao = leitorDeEndereco(sc);
                return this.clientesDoBanco.add(new ClientePessoaFisica(nome, cpf, enderecoDeCriacao, dataDeNascimento));
            }else if (tipoCliente.equalsIgnoreCase("PJ")){
                System.out.println("Digite o nome do cliente: ");
                nome = sc.nextLine();
                System.out.println("Digite o CNPJ do cliente: ");
                cnpj = sc.next();
                System.out.println("Digite o nome da empresa do cliente: ");
                nomeEmpresa = sc.nextLine();

                enderecoDeCriacao = leitorDeEndereco(sc);
                return this.clientesDoBanco.add(new ClientePessoaJuridica(nome, enderecoDeCriacao,cnpj,nomeEmpresa, dataDeNascimento));
            }else {
                System.out.println("Tipo de cliente inválido!");
                return false;
            }

        }catch (InputMismatchException e){
            throw new InputMismatchException("ERRO, DIGITOU CARACTERES AO INVÉS DE NÚMEROS! "+ e.getMessage());
        }
    }

    public void abrirConta(){

    }

    private Endereco leitorDeEndereco(Scanner sc){
        System.out.println("--- Endereço ---");
        System.out.println("Digite o CEP da cidade do cliente: ");
        int cep = sc.nextInt();
        sc.nextLine();

        System.out.println("Digite o nome da cidade do cliente: ");
        String cidade = sc.nextLine();

        System.out.println("Digite a rua do cliente: ");
        String rua = sc.nextLine();

        System.out.println("Digite o bairro do cliente: ");
        String bairro = sc.nextLine();

        System.out.println("Digite o número da casa do cliente: ");
        int numeroDaCasa = sc.nextInt();
        sc.nextLine();

        System.out.println("Complemento: ");
        String complemento = sc.nextLine();

        return new Endereco(rua, cep,numeroDaCasa,complemento,bairro,cidade);
    }
    public String getNomeDoBanco() {return nome_do_Banco;}
    public String getCodigo() {return codigo;}
}
