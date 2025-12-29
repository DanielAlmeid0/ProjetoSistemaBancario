package app;

import model.Endereco;
import service.Banco;
import util.Validacoes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {

        /// FALTA FAZER A MAIN, TESTAR TODAS AS FUNCIONALIDADES, IMPLEMENTAR A INTERFACE GRÁFICA, EU CREIO

        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco("PACHECO's Bank");

        try {
            System.out.println("Quantos clientes deseja cadastrar?");
            int quant = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < quant; i++) {
               adicaoDeCliente(sc,banco);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean adicaoDeCliente(Scanner sc, Banco banco) {
        String cnpj, dataDeNascimento, nome, nomeEmpresa, cpf;
        Endereco enderecoDeCriacao;

        try {
            System.out.println("O cliente é Pessoa Física(PF) ou Pessoa Jurídica(PJ)?");
            String tipoCliente = Validacoes.validacaoDasStringsPorPadrao(sc, 2, "pf", "pj");

            System.out.println("Nome do cliente: ");
            nome = sc.nextLine();

            System.out.println("Digite a data de nascimento do cliente: ");
            dataDeNascimento = Validacoes.validacaoDasDatas(sc);

            if (tipoCliente.equalsIgnoreCase("PF")){

                System.out.println("CPF do cliente: ");
                cpf = Validacoes.validacaoDasStrings(sc,11);

                enderecoDeCriacao = leitorDeEndereco(sc);
                banco.adicionarCliente(nome, cpf, enderecoDeCriacao, dataDeNascimento);

                return true;

            }else if (tipoCliente.equalsIgnoreCase("PJ")){

                System.out.println("Digite o CNPJ do cliente: ");
                cnpj = sc.nextLine();

                System.out.println("Digite o nome da empresa do cliente: ");
                nomeEmpresa = sc.nextLine();

                enderecoDeCriacao = leitorDeEndereco(sc);
                banco.adicionarCliente(nome, cnpj, enderecoDeCriacao, dataDeNascimento,  nomeEmpresa);

                return true;

            }else {return false;}

        }catch (InputMismatchException e){
            System.out.println("ERRO, VOCÊ DIGITOU ALGO ERRADO NO CAMPO ERRADO (ex: digitou números em um campo de caracteres)! "+ e.getMessage());
            sc.nextLine();
            return false;
        }
    }

    public static Endereco leitorDeEndereco(Scanner sc) {
        System.out.println("--- Endereço ---");

        System.out.println("Digite o CEP da cidade do cliente: ");
        String cep = Validacoes.validacaoDasStrings(sc, 8);

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
}
