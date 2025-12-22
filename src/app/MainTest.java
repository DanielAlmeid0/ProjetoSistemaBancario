package app;

import service.Banco;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Banco banco = new Banco("QuixasFLix", "ffre13232");
        try {
            System.out.println("Quantos clientes deseja cadastrar?");
            int quant = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < quant; i++) {
                banco.adicionarCliente(sc);
            }


            banco.abrirConta(sc);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
