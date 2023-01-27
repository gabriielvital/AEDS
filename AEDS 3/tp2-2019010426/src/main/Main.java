package main;

/*************************************************************/
/* Matrıcula/Aluno: 2019010426/Vitor Antenor Pedro      */
/* Curso: Ciencia da Computacao                              */
/* 2º Trabalho Pratico -- Ordencao Externa                   */
/* DCC288 -- 2022 -- IFSEMG, 3o.                             */
/* Prof. Flavio Augusto de Freitas                           */
/* Compilador: Apache NetBeans versao 13.0                   */
/* Sistema Operacional: Windows 11                           */
/*************************************************************/

import classes.*;
import java.util.Locale;

public class Main {
   
    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        
        long time = System.currentTimeMillis(); 
        
        Intercalacao ordena = new Intercalacao();
        Pessoa p = new Pessoa();
        int MAX_REGISTERS;
        MAX_REGISTERS = 80; //Numero de registros que criados ao inicializar o programa.
        ordena.menu(MAX_REGISTERS); 

        time = System.currentTimeMillis() - time; 
        System.out.println("Tempo total de execucao do programa: " + time + "ms = " + (time/1000) + "s.");
    }
    
}
