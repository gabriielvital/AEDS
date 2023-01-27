package classes;

import java.util.Random;

public class Pessoa {
    
    private String ceps[] = {
        "36180-000", "25500-000", "36140-000", "37510-234", "12240-000",
        "34567-111", "76456-322", "45654-987", "98765-216", "40000-000"
    }; 

    private int MAX_REGISTROS = 800000; 
    private int MAX_CHARS = 80; 

    private char[] cpf = new char[11]; 
    private char[] nome = new char[MAX_CHARS]; 
    private int idade; 
    private char sexo;
    private char[] cep = new char[8];
    private Random r = new Random();

    public Pessoa() {

    }

    public void imprimir() {
        imprimirCEP();
        System.out.print("  ");
        imprimirCPF(); 
        System.out.print("  ");
        imprimirIdade();
        System.out.print("\t" + " ");
        imprimirSexo();
        System.out.print("    ");
        imprimirNome();
        System.out.println("");
    }

    public void imprimirNome() {
        for (int i = 0; i < getNome().length; i++) {
            System.out.print(getNome()[i]);
        }
    }

    public void imprimirIdade() {
        System.out.print(getIdade());
    }

    public void imprimirSexo() {
        System.out.print(getSexo());
    }

    public void imprimirCPF() {
        for (int i = 0; i < 11; i++) {
            System.out.print(getCpf()[i]);
            if (i == 2 || i == 5) {
                System.out.print(".");
            }
            if (i == 8) {
                System.out.print("-");
            }
        }
    }

    public void imprimirCEP() {
        for (int i = 0; i < 9; i++) {
            if (i < 5) {
                System.out.print(getCep()[i]);
            } else if (i == 5) {
                System.out.print("-");
            } else {
                System.out.print(getCep()[i - 1]);
            }
        }
    }

    public void gera_cep(char cep[]) {
        int aux = getR().nextInt(10);
        for (int i = 0; i < 9; i++) {
            if (i < 5) {
                cep[i] = getCeps()[aux].charAt(i);
            } else if (i > 5) {
                cep[i - 1] = getCeps()[aux].charAt(i);
            }
        }

    }

    public void gera_cpf(char cpf[]) {
        for (int i = 0; i < 11; i++) {
            int digito = getR().nextInt(10);
            char[] aux = Integer.toString(digito).toCharArray();
            cpf[i] = aux[0];
        }
    }

    public void gera_nome(char nome[]) {
        int cont = 0;
        boolean espacoAleatorio = false;
        Random espaco = new Random();
        int contEspacos = 0;

        for (int i = 0; i < getMAX_CHARS(); i++) {

            if (i > 0 && i < getMAX_CHARS()) {
                if (espaco.nextInt(100) < 10) {
                    espacoAleatorio = true;
                }
            }

            if (cont == Math.abs((getMAX_CHARS() - 2) / 3)) {
                espacoAleatorio = true;
            }
            if (espacoAleatorio == true) { 
                espacoAleatorio = false; 
                if (contEspacos < 3) {
                    contEspacos++; 
                    nome[i] = ' ';
                    cont = 0;
                } else {
                    i = getMAX_CHARS() - 1;
                }

            } else if (cont < Math.abs((getMAX_CHARS() - 2) / 3)) {
                int letra = (65 + getR().nextInt(26));
                nome[i] = (char) letra; 
                cont++;
            } else {
                i = getMAX_CHARS();
            }
        }
    }

    public int gera_idade() {
        int idade = 0;
        while (idade < 10 || idade > 50) {
            idade = getR().nextInt(51); 
        }
        return idade;
    }
    
    public char gera_sexo(){
        char sexo = ' ';
        
        Random rand = new Random();
        
        sexo = rand.nextBoolean()?'F':'M';
        
        return sexo;
    }

        public Pessoa gera_Registro() {
        Pessoa copia = new Pessoa(); 
        gera_cpf(getCpf()); 
        gera_nome(getNome()); 
        setIdade(gera_idade()); 
        setSexo(gera_sexo());
        gera_cep(getCep());

        copia.setCep(getCep());
        copia.setCpf(getCpf());
        copia.setIdade(getIdade());
        copia.setSexo(getSexo());
        copia.setNome(getNome());
        return copia;
    }

    public String AtributoToCSV() {
        String cep = new String(getCep());
        String cpf = new String(getCpf());
        String idade = String.valueOf(getIdade());
        String sexo = String.valueOf(getSexo());
        String nome = new String(getNome());

        String texto = cep + ";" + cpf + ";" + idade + ";" + sexo + ";" + nome + "\r\n";

        return texto;
    }

    public void CSVToAtributos(String texto) {
        String[] arrayTexto = texto.split(";");
        this.setCep(arrayTexto[0].toCharArray());
        this.setCpf(arrayTexto[1].toCharArray());
        this.setIdade(Integer.parseInt(arrayTexto[2]));
        this.setSexo(arrayTexto[3].charAt(0));
        this.setNome(arrayTexto[4].toCharArray());
    }
    //GETTERS AND SETTERS
    public String[] getCeps() { return ceps; }
    public void setCeps(String[] ceps) { this.ceps = ceps; }

    public int getMAX_REGISTROS() { return MAX_REGISTROS; }
    public void setMAX_REGISTROS(int MAX_REGISTROS) { this.MAX_REGISTROS = MAX_REGISTROS; }
    
    public int getMAX_CHARS() { return MAX_CHARS; }
    public void setMAX_CHARS(int MAX_CHARS) { this.MAX_CHARS = MAX_CHARS; }

    public char[] getCpf() { return cpf; }
    public void setCpf(char[] cpf) { this.cpf = cpf; }

    public char[] getNome() { return nome; }
    public void setNome(char[] nome) { this.nome = nome; }
    
    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public char getSexo() { return sexo; }    
    public void setSexo(char sexo) { this.sexo = sexo; }
    
    public char[] getCep() { return cep; }   
    public void setCep(char[] cep) { this.cep = cep; }

    public Random getR() { return r; }
    public void setR(Random r) { this.r = r; }
}
