package repository;

import model.*;
import java.io.*;
import java.util.List;

public class Persistencia <T>{

    private final String caminho = "BancoDeDadosArq.txt";
    private final File bancoDeDadosArq = new File(caminho);

    /// salva cadastros de clientes ou contas e atulizações dos mesmos
    public boolean salvar(Object objeto, String acao){ //falta implementação nos métodos

        boolean precisaEscreverCabecalho = !bancoDeDadosArq.exists() || bancoDeDadosArq.length() == 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bancoDeDadosArq, true))){
            if (precisaEscreverCabecalho){
                writer.write("--- Registro de Cadastros ---");
                writer.newLine();
            }

            if (acao.equalsIgnoreCase("cadastrar")){writer.write("Novo cadastrado!");}
            else if (acao.equalsIgnoreCase("atualização") || acao.equalsIgnoreCase("atualizar")){writer.write("Nova atualização!");}
            writer.newLine();

            //lógica de escrita do conteúdo no arq
            if (objeto instanceof Conta){
                Conta objConta = (Conta)objeto;
                writer.write(objConta.toStringARQ());
            } else if (objeto instanceof Cliente) {
                Cliente objCliente = (Cliente) objeto;
                writer.write(objCliente.toStringARQ());
            }else {return false;}

            writer.newLine();
            return true;

        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    /// carrega o que tem em um arquivo .txt e salva em uma lista, usado de início para persistência
    public void carregar(List<T> list) { //falta implementar nos construtores
        try (BufferedReader reader = new BufferedReader(new FileReader(bancoDeDadosArq))) {
            String linha;

            while ((linha = reader.readLine()) != null) {
                if (linha.startsWith("---") || linha.trim().isBlank()) {continue;}

                try {
                    Object objeto = parseObject(linha);
                    if (objeto == null) {continue;}

                    T objetoGenerico = (T) objeto;

                    list.add(objetoGenerico);
                } catch (ClassCastException e) {

                }catch (Exception e){
                    System.out.println("Erro ao ler linha "+ e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //SOLUÇÃO DE UM POSSÍVEL FUTURO ERRO: FAZER LER PRIMEIRO SÓ OS CLIENTES
    // DPS LER AS CONTAS, JÁ QUE POSSUI UMA RELAÇÃO DE DEPENDÊNCIA
    public Object parseObject(String linha) {
        if (linha == null || linha.trim().isBlank()) {
            throw new IllegalArgumentException("Linha inválida!");
        }

        String[] partes = linha.trim().split(";");
        if (partes.length < 2) {
            throw new IllegalArgumentException("Frase não válida!");
        }

        String tipo = partes[0];

        if (tipo.equalsIgnoreCase("CC") || tipo.equalsIgnoreCase("CP")) {
            int numero = Integer.parseInt(partes[1]);
            int agencia = Integer.parseInt(partes[2]);
            String nome = partes[3];
            double limite = Double.parseDouble(partes[4]);
            Cliente titularTemp = new ClientePessoaFisica(nome, "", null, null); //revisar essa parte

            if (tipo.equalsIgnoreCase("CC")) {
                return new ContaCorrente(numero, agencia, titularTemp, limite);
            } else {
                return new ContaPoupanca(numero, agencia, titularTemp, limite);
            }

        } else if (tipo.equalsIgnoreCase("PF")) {
            String nome = partes[1];
            String cep = partes[2];
            String rua = partes[3];
            String bairro = partes[4];
            int numeroDaCasa = Integer.parseInt(partes[5]);
            String cidade = partes[6];
            String complemento = partes[7];
            String dataDeNascimento = partes[8];
            String cpf = partes[9];

            Endereco endereco = new Endereco(rua, cep, numeroDaCasa, complemento, bairro, cidade);

            return new ClientePessoaFisica(nome, cpf, endereco, dataDeNascimento);
        } else if (tipo.equalsIgnoreCase("PJ")) {
            String nome = partes[1];
            String cep = partes[2];
            String rua = partes[3];
            String bairro = partes[4];
            int numeroDaCasa = Integer.parseInt(partes[5]);
            String cidade = partes[6];
            String complemento = partes[7];
            String dataDeNascimento = partes[8];
            String cnpj = partes[9];
            String nomeEmpresa = partes[10];

            Endereco endereco = new Endereco(rua, cep, numeroDaCasa, complemento, bairro, cidade);

            return new ClientePessoaJuridica(nome, endereco, cnpj, nomeEmpresa, dataDeNascimento);
        } else {return null;}
    }
}
