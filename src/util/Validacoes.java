package util;

import exception.InvalidValueException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Scanner;

public class Validacoes {

    //provavelmente esse metodo vai ficar inútil e terá que ser removido por conta da interface gráfica que utiliza uma lógica diferente
    public static String validacaoDasStringsPorPadrao(String string, int tamanhoIdeal, String padrao, String padrao2){
        boolean valido = false;
        String stringAlvo = null;

        while(!valido){
            try{

                if (stringAlvo.length() != tamanhoIdeal || (!stringAlvo.equalsIgnoreCase(padrao) && !stringAlvo.equalsIgnoreCase(padrao2))){
                    throw new InvalidValueException();
                }else{
                    valido = true;
                }
            }catch (InvalidValueException e){
                System.out.println("ERRO, VALOR DIGITADO É INVÁLIDO, DIGITE NOVAMENTE COM BASE NAS OPÇÕES!");
            }
        }
        return stringAlvo;
    }

    public static void validacaoDasDatas(String dataTexto) throws InvalidValueException{

        //esse formatador abaixo exige que a data seja válida
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

        if (dataTexto.trim().isEmpty() || dataTexto.trim() == null){
            throw new InvalidValueException("Erro na data: A data não pode estar vazia.");
        }

           try {
            LocalDate dataLD = LocalDate.parse(dataTexto, formatter);

            Period idadeEmData = Period.between(dataLD, LocalDate.now());

            int idade = idadeEmData.getYears();

            if (idade < 18){
                throw new InvalidValueException("ERRO, a data \""+dataTexto+"\" é inválida, pois o usuário tem apenas "+idade+" anos, idade mínima é 18");
            }

           }catch (DateTimeParseException e){
               throw new InvalidValueException("ERRO, a data \"" + dataTexto + "\" é inválida. Digite novamente no formato dd/MM/uuuu (ex: 12/02/2000):");
           }catch (InvalidValueException e){
               throw new InvalidValueException(e.getMessage());
           }
    }


    public static void validacaoDasStrings(String stringREF, int tamanhoIdeal) throws InvalidValueException{
            try {
                if (stringREF.length() != tamanhoIdeal || !stringREF.matches("[0-9]+")){
                    throw new InvalidValueException();
                }
            }catch (InvalidValueException e) {
                throw new InvalidValueException("Erro: quantidade de caracteres inválida, digite " + tamanhoIdeal + " números! ");
            }
    }

    public static void validacaoDasStrings(String stringREF) throws InvalidValueException{
        try {
            if (stringREF.trim().isEmpty()|| stringREF == null){
                throw new InvalidValueException();
            }
        }catch (InvalidValueException e) {
            throw new InvalidValueException("Erro: algum campo ficou vazio!");
        }
    }

}
