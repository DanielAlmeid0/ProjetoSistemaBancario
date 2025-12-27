package model;

import repository.Persistencia;
import service.Banco;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public abstract class Cliente {

    protected String nome;
    protected Endereco endereco;
    protected LocalDate dataDeNascimento;
    protected Set<Conta> contas = new HashSet<>();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Cliente(String nome, Endereco endereco, String dataNascimento) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataDeNascimento = LocalDate.parse(dataNascimento, formatter);
    }

    public void vincularConta(Conta conta) {
        if (conta != null && conta.getTitular() != null){this.contas.add(conta);}
        else{throw new RuntimeException("Vinculação de conta inválida!");}
    }

    public void atualizarDados(String novo_nome, Endereco novo_endereco) { //REFATORAR ESSE METODO!!!!!
        this.nome = novo_nome;
        this.endereco = novo_endereco;

//        Banco b = new Banco();
//        b.getBancoDeDados().salvar();
    }

    public void consultarContas() {// esse metodo deve ser revisado quando a Interface Gráfica for implementada
        System.out.println("--- Contas do cliente "+nome+" ---\n");
        for (Conta conta : contas) {
            System.out.println(conta.toString());
        }
        System.out.println("\n-------------------");
    }

    public void setNome(String nome){this.nome = nome;}
    public String getNome() {return nome;}
    public String getEnderecoARQ() {return endereco.toStringARQ();}

    @Override
    public String toString() {
        String enderecoImpressao;

        if (!endereco.getComplemento().equalsIgnoreCase("Nenhum") ){
            enderecoImpressao = endereco.toString() + "\nComplemento: "+endereco.getComplemento();}
        else {enderecoImpressao = endereco.toString();}

        return "Nome do cliente - " + nome +
                "\nDataDeNascimento - " + dataDeNascimento.format(formatter) +
                "\nEndereço - " + enderecoImpressao;
    }

    public String toStringARQ() {
        return nome + ";" + getEnderecoARQ() + ";" + dataDeNascimento.format(formatter);
    }
}
