package classes;

import java.io.File;
import java.util.ArrayList; 
import java.util.Scanner; 

public class Intercalacao {

    int acessCount = 0;
    long timeCount = 0;
    int swapCount = 0, compCount = 0;
    ArrayList<Pessoa> registros = new ArrayList<>();
    Pessoa p = new Pessoa();

    public void menu(int n) {
        Arquivo FM = new Arquivo();

        File fp1 = new File("registros.txt");

        File fp2 = new File("registros2.txt");
       
        
        fp1.delete();
        fp2.delete();
        new Intercalacao(fp1, n); 
        
        System.out.println("\t\t\tORDENACAO EXTERNA\n");
        
        System.out.println(n + " registros criados em " + fp1.getPath() + ".");                

                    
        long tempoInicio = System.currentTimeMillis();
        ordenar(fp1, n);
        timeCount = (System.currentTimeMillis() - tempoInicio); 
                        
        System.out.println("Arquivo ordenado criado em registros2.txt");                                               
        System.out.println("\n> 10 PRIMEIROS AQUIVOS DESORDENADOS");                       
        imprime_registros(fp1, 10);                                                 
        System.out.println("\n> 10 PRIMEIROS ARQUIVOS ORDENADOS");                    
        imprime_registros(fp2, 10);                         
        System.out.println("\n----------=========< RESULTADOS FINAIS >=========----------");                          
        System.out.println("\tTempo total da ordenação: " + timeCount + " ms.");                          
        System.out.println("\tQuantidade de acessos ao arquivo: " + Arquivo.access + ".");                           
        System.out.println("\tQuantidade total de trocas: " + swapCount + ".");                           
        System.out.println("\tQuantidade total de comparações: " + compCount + ".");                           
        System.out.println("--------------==============<  >==============--------------");
    }

    public Intercalacao() {
        
    }
   
    public Intercalacao(File fp, int n) { 
        gera_registros(fp, n); 
    }

    public void gera_registros(File fp, int n) {
        for (int i = 0; i < n; i++) {
            p = new Pessoa(); 
            registros.add(i, p); 
            p = p.gera_Registro(); 
            registros.set(i, p);
        }

        String texto; 
        for (int i = 0; i < n; i++) {
            texto = registroCSV();
            Arquivo.escreverArquivo(texto, fp.getPath());
        }

    }

    public void imprime_registros(File fp, int n) { 
        String texto = Arquivo.lerArquivo(fp.getPath()); 
        carregarDadosRegistro(texto);
        if (n == -1 || n > registros.size()) {
            n = registros.size();
        }
        
        System.out.println("CEP        CPF             IDADE SEXO NOME                                                                   ");
        System.out.println("---------  --------------  ----- ---- -----------------------------------------------------------------------");
        for (int i = 0; i < n; i++) { 
            registros.get(i).imprimir();
        }

    }

    public void ordenar(File fp, int n) { 
        String texto = "";
        texto = Arquivo.lerArquivo(fp.getPath());
        carregarDadosRegistro(texto);
        if (n == -1 || n > registros.size()) {
            n = registros.size();
        }
        metodoOrdenar();
        for (int i = 0; i < n; i++) {
            texto = registroCSV();
            Arquivo.escreverArquivo(texto, "registros2.txt");
        }

    }

    public void metodoOrdenar() {

        ArrayList<Pessoa> aux = new ArrayList<Pessoa>();
        Merge ms = new Merge(); 
        aux.addAll(registros);
        registros.clear(); 

        registros.addAll(ms.sort(aux));

        swapCount = ms.swaps;
        compCount = ms.comparacoes;

    }

    public int compara_int(int a, int b) {
        int resposta = 0;
        if (a < b) {
            resposta = -1;
        } else if (b < a) {
            resposta = 1;
        }
        return resposta;
    }

    public int compara_string(String a, String b) {
        int resposta = 0;
        resposta = a.compareTo(b);
        return resposta;
    }

    public int compara_registro(Pessoa a, Pessoa b) {
        String texto, sexoA, sexoB, nomeA, nomeB;
        int cepA = 0, cepB = 0, resposta = 0;
        texto = new String(a.getCep());
        cepA = Integer.parseInt(texto);
        texto = new String(b.getCep());
        cepB = Integer.parseInt(texto);
        sexoA = String.valueOf(a.getSexo());
        sexoB = String.valueOf(b.getSexo());
        nomeA = new String(a.getNome());
        nomeB = new String(b.getNome());

        resposta = compara_int(cepA, cepB);

        if (resposta == 0) { 
            resposta = compara_string(sexoA, sexoB);
            if (resposta < 0) { 
                resposta = -1;
            } else if (resposta > 0) {
                resposta = 1;
            } else {
                resposta = compara_int(a.getIdade(), b.getIdade());
                if (resposta == 0) {
                    resposta = compara_string(nomeA, nomeB);
                    if (resposta < 0) {
                        resposta = -1;
                    } else if (resposta > 0) {
                        resposta = 1;
                    }
                }
            }
        }
        return resposta;
    }

    public String registroCSV() {
        String texto = "CEP;CPF;IDADE;SEXO;NOME\r\n";
        for (Pessoa r : registros) {
            texto += r.AtributoToCSV();
        }
        return texto;
    }

    public void lerArq(String registrotxt) {
        Arquivo.lerArquivo(registrotxt);

    }

    public void carregarDadosRegistro(String registrotxt) {
        registros.clear();
        String[] texto = Arquivo.getLinhas(registrotxt);
        for (int i = 0; i < texto.length; i++) {
            Pessoa r = new Pessoa();
            r.CSVToAtributos(texto[i]);
            registros.add(r);
        }
    }
}
