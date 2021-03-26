package com.vvm.sh.ui.transferencias.modelos;

import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.TipoNovo;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AtualizacaoTipos {

    public List<TiposUtil.MetodoApi> atualizacoes;
    public List<TipoNovo> tiposNovos;
    public List<CheckList> checklists;

    /**
     * Metodo que permite obter os identificadores das checklists que ainda não foram carregadas
     * @return uma lista de identificadores
     */
    public List<Integer> obterIdChecklists(){

        List<Integer> ids = new ArrayList<>();

        if(checklists.size() == TiposUtil.MetodosTiposChecklist.ID_CHECKLISTS__.length){

            return ids;
        }
        else if(checklists.size() == 0){

            for (Integer item : TiposUtil.MetodosTiposChecklist.ID_CHECKLISTS__) {
                ids.add(item);
            }

            return ids;
        }
        else{

            LinkedList registos = new LinkedList(Arrays.asList(TiposUtil.MetodosTiposChecklist.ID_CHECKLISTS__));

            for (CheckList item : checklists) {
                if(registos.contains(item.id) == false){
                    ids.add(item.id);
                }
            }

            return ids;
        }
    }

}
