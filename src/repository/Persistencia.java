package repository;

import service.Banco;

import java.io.*;

public class Persistencia <T>{

    private final String caminho = "BancoDeDadosArq.txt";
    private final File bancoDeDadosArq = new File(caminho);

    public boolean salvar(T objeto, String acao){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(bancoDeDadosArq, true))){
            if (bancoDeDadosArq.length() == 0 || !bancoDeDadosArq.exists()){
                writer.write("--- Registro de Cadastros ---");
                writer.newLine();
            }

            if (acao.equalsIgnoreCase("cadastrar")){writer.write("Novo cadastrado!");}
            else if (acao.equalsIgnoreCase("edicao") || acao.equalsIgnoreCase("atualizar")){writer.write("Nova atualização!");}
            writer.newLine();
            writer.write(objeto.toString());
            writer.newLine();

            return true;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void carregar(Banco b) {
        try (BufferedReader reader = new BufferedReader(new FileReader(bancoDeDadosArq))) {
//            String linha = null;
//
//            while ((linha = reader.readLine()) != null) {
//                if (linha.startsWith("---") || linha.trim().isBlank()) {continue;}
//
//                if (linha.startsWith("Cliente") || linha.startsWith("Conta")) {
//
//                }
//
//                switch (){
//                    case "cliente":
//
//                        break;
//                    case "conta":
//
//                        break;
//                    default:
//
//                        break;
//                }
//            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
