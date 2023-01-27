package classes;

import java.util.ArrayList;

public class Merge {
    
    Intercalacao r = new Intercalacao();
    private ArrayList<Pessoa> cadastros = new ArrayList<Pessoa>();
    private ArrayList<Pessoa> aux = new ArrayList<Pessoa>();
    ;
    public int lenght, swaps = 0, comparacoes = 0, swapAux = 0;

    public ArrayList<Pessoa> sort(ArrayList<Pessoa> reg) {

        this.cadastros.addAll(reg);

        lenght = reg.size();
        
        mergesort(0, lenght - 1);
        System.out.println("Numero de trocas entre arquivo auxiliar e principal: " + (swaps + swapAux) + " trocas.");
        return cadastros;
    }

    private void mergesort(int low, int high) {

        if (low < high) {
            int middle = low + (high - low) / 2;
            
            mergesort(low, middle);
            mergesort(middle + 1, high);         
            merge(low, middle, high);
        }
    }

    private void merge(int low, int middle, int high) {
        
        for (int i = low; i <= high; i++) {
            aux.add(i, cadastros.get(i));
            swapAux++;
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            comparacoes++;
            if (r.compara_registro(aux.get(i), aux.get(j)) == -1 || r.compara_registro(aux.get(i), aux.get(j)) == 0) {
                cadastros.set(k, aux.get(i));
                swaps++;
                i++;
            } else {
                swaps++;
                cadastros.set(k, aux.get(j));
                j++;
            }
            k++;
        }

        while (i <= middle) {
            cadastros.set(k, aux.get(i)); 
            swaps++;
            k++;
            i++;
        }
    } 
}
