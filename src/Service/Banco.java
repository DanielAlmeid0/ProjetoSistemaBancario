package Service;

public class Banco {
    private String nome_do_Banco;
    private String codigo;

    public Banco(String nomeBanco, String codigo){
        this.nome_do_Banco = nomeBanco;
        this.codigo = codigo;
    }

    public Banco(String nome_do_Banco){
        this.nome_do_Banco = nome_do_Banco;
        this.codigo = "";
    }

    public void adicionarCliente(){

    }

    public void abrirConta(){

    }

    public String getNomeDoBanco() {
        return nome_do_Banco;
    }

    public String getCodigo() {
        return codigo;
    }
}
