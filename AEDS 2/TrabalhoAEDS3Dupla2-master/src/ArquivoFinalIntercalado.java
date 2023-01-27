import java.util.List;
import java.util.ArrayList;

public class ArquivoFinalIntercalado {
    public static void criar(String nomeDoArquivo){
        List<CaminhoExistente> caminhos = todosOsCaminhos();
        intercalaCaminhos(caminhos);
    }

    private static void intercalaCaminhos(List<CaminhoExistente> caminhos) {
        while(!caminhos.isEmpty()) {
            Registro menorRegistro = retiraMenorRegistro(caminhos);
            escreverNoRegistroFinal(menorRegistro);
        }
    }

    private static void escreverNoRegistroFinal(Registro registro){
        ArquivoUtils.gravarRegistro("arquivos//dadosOrdenados.csv", registro, true);
    }

    private static Registro retiraMenorRegistro(List<CaminhoExistente> caminhos){
        CaminhoExistente menorCaminho = menorPrimeiroRegistro(caminhos);
        Registro menorRegistro = menorCaminho.primeiroRegistro();
        menorCaminho.getRegistros().remove(0);
        if (menorCaminho.isEmpty()){
            caminhos.remove(menorCaminho);
        }
        return menorRegistro;
    }

    private static CaminhoExistente menorPrimeiroRegistro(List<CaminhoExistente> caminhos) {
        CaminhoExistente menorCaminho = caminhos.get(0);
        for(CaminhoExistente caminho : caminhos){
            if(caminho.comparaPrimeiroRegistro(menorCaminho) == -1){
                menorCaminho = caminho;
            }
        }
        return menorCaminho;
    }

    private static List<CaminhoExistente> todosOsCaminhos(){
        List<CaminhoExistente> caminhos = new ArrayList<>();
        for(int i=0; i<8; i++){
            CaminhoExistente caminho = new CaminhoExistente(i);
            caminhos.add(caminho);
        }

        return caminhos;
    }
}
