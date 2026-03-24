package br.edu.ifba.saj.fwads.model.gestaoEmbarque;

import br.edu.ifba.saj.fwads.model.AbstractModel;
import java.util.List;
import java.util.ArrayList;

public class RotaDesvio extends AbstractModel<Long> {
    private List<Ponto> pontosRemovidos = new ArrayList<>();
    private List<Ponto> novosPontos = new ArrayList<>();

   
    public List<Ponto> getPontosRemovidos(){
        return pontosRemovidos; 
    }

    
    public List<Ponto> getNovosPontos(){
        return novosPontos; 

    }
}