package Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transacao {

    private LocalDate data;
    private String tipo_de_transacao;
    private Double valTransacao;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Transacao(String dt, String tipoTransacao, double val){
        this.data = LocalDate.parse(dt, formatter);
        this.tipo_de_transacao = tipoTransacao;
        this.valTransacao = val;
    }

    public void registrar(){

    }

    public LocalDate getData() {return data;}
    public String getTipo_de_transacao() {return tipo_de_transacao;}
    public Double getValTransacao() {return valTransacao;}
}
