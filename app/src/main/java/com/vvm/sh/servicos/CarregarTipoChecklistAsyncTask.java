package com.vvm.sh.servicos;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.SeccaoChecklist;
import com.vvm.sh.repositorios.TiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.excepcoes.TipoInexistenteException;
import com.vvm.sh.util.mapeamento.DownloadMapping;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.TiposUtil;

import java.util.ArrayList;
import java.util.List;

public class CarregarTipoChecklistAsyncTask extends AsyncTask<List<ITipoChecklist>, Void, Void> {

    private String errorMessage;
    private VvmshBaseDados vvmshBaseDados;
    private TiposRepositorio repositorio;

    /**
     * Permite enviar mensagens para fora do servico
     */
    private AtualizacaoUI atualizacaoUI;

    public CarregarTipoChecklistAsyncTask(VvmshBaseDados vvmshBaseDados, TiposRepositorio repositorio){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        //atualizacaoUI = new AtualizacaoUI(handler);
    }


    @Override
    protected Void doInBackground(List<ITipoChecklist>... tipoRespostas) {


        if(tipoRespostas[0] == null)
            return null;

        List<ITipoChecklist> respostas = tipoRespostas[0];


        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    for(ITipoChecklist resposta : respostas){

                        CheckList checkList = DownloadMapping.INSTANCE.map(resposta);

                        List<AreaChecklist> areas = new ArrayList();
                        List<SeccaoChecklist> seccoes = new ArrayList();
                        List<ItemChecklist> itens = new ArrayList();

                        for (ITipoChecklist.IArea itemArea : resposta.areas) {
                            AreaChecklist area = DownloadMapping.INSTANCE.map(itemArea, checkList);

                            areas.add(area);

                            for (ITipoChecklist.ISeccao itemSeccao : itemArea.seccoes) {

                                if(itemSeccao.items != null) {

                                    SeccaoChecklist seccao = DownloadMapping.INSTANCE.map(itemSeccao, checkList, itemArea);
                                    seccoes.add(seccao);

                                    for (ITipoChecklist.IItem itemItem : itemSeccao.items) {

                                        ItemChecklist item = DownloadMapping.INSTANCE.map(itemItem, checkList, itemArea, itemSeccao, itemItem);
                                        itens.add(item);
                                    }
                                }
                            }
                        }


                        repositorio.inserirChecklist(checkList, areas, seccoes, itens);

                    }

                }
                catch(SQLiteConstraintException throwable){
                    errorMessage = throwable.getMessage();
                }
            }
        });

        return null;
    }
}
