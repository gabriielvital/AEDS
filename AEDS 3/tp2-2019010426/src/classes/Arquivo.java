package classes;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Arquivo {
    
    public static int access =0;
    public static void escreverArquivo(String texto, String caminho) {
        access++;
        File arquivo = new File(caminho);
        FileWriter fw;
        try {
            fw = new FileWriter(arquivo); 
            fw.write(texto);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            System.out.println("Erro" + e);
        }
    }

    public static String lerArquivo(String caminho) {
        
        try {
            access++;
            String lido = "";
            FileReader fr = new FileReader(caminho);
            try (Scanner arquivoLido = new Scanner(fr)) {
                arquivoLido.useDelimiter("\n");
                arquivoLido.next();
                while (arquivoLido.hasNext()) {
                    lido += arquivoLido.next() + "\n";
                }
            }
            return lido;
        } catch (Exception e) {
            return "";
        }
    }

    public static String[] getLinhas(String lido) {
        String texto = lido; 
        String[] arrayTexto = texto.split("\r\n");
        return arrayTexto;
    }  
}