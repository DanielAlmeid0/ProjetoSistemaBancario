package Cliente;

public abstract class Cliente {

    private String nome;
    private long cpf;
    private String endereco;

    public Cliente(String nome, long cpf, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }
    public long getCpf() {
        return cpf;
    }
    public String getEndereco() {
        return endereco;
    }

    public void atualizarDados(String novo_nome, String novo_endereco) {
        this.nome = novo_nome;
        this.endereco = novo_endereco;
    }

    //Talvez com a implementação do toString() esse metodo não seja mais útil, possa ser que seja excluído
    public String consultarContas() {
        return "Nome do titular: " + nome + ", Cpf do titular: " + cpf + ", Endereco: " + endereco + ".";
    }

    @Override
    public String toString() {
        return "Nome do cliente =" + nome + ", cpf =" + cpf + ", endereco =" + endereco;
    }
}
