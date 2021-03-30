package com.vvm.sh.servicos.tipos;



import android.database.sqlite.SQLiteConstraintException;

import com.vvm.sh.api.modelos.pedido.ITipo;
import com.vvm.sh.api.modelos.pedido.ITipoChecklist;
import com.vvm.sh.api.modelos.pedido.ITipoListagem;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.Atualizacao;
import com.vvm.sh.baseDados.entidades.CheckList;
import com.vvm.sh.baseDados.entidades.ItemChecklist;
import com.vvm.sh.baseDados.entidades.SeccaoChecklist;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.repositorios.CarregamentoTiposRepositorio;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.mapeamento.DownloadMapping;

import java.util.ArrayList;
import java.util.List;

public abstract class TipoChecklistAsyncTask extends CarregamentoTipoAsyncTask {



    public TipoChecklistAsyncTask(VvmshBaseDados vvmshBaseDados, CarregamentoTiposRepositorio repositorio) {
        super(vvmshBaseDados, repositorio);
    }

    @Override
    protected List<Object> filtarRegistos(List<Object> respostas) {

        List<Object> registos = new ArrayList<>();

        for (Object item : respostas) {

            if (item instanceof ITipoChecklist) {
                registos.add(item);
            }
        }

        return registos;
    }

    @Override
    protected void executar(List<Object> dados){

        try {

            int index = 0;

            for (Object dado : dados) {

                ITipoChecklist resposta = (ITipoChecklist) dado;

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

                repositorio.carregarChecklist(checkList, areas, seccoes, itens);

                atualizarUI(++index, dados.size(), null);

                //--listener.atualizarTransferencia(new AtualizacaoUI_(AtualizacaoUI_.Estado.PROCESSAMENTO_CHECKLIST, 1, 1, "Checklist"));
            }
        }
        catch(SQLiteConstraintException throwable){
            erro = throwable.getMessage();
        }
    }


    @Override
    protected Atualizacao obterAtualizacao(Object resposta) {
        return null;
    }

    @Override
    protected void inserirRegisto(Object resposta, Atualizacao atualizacao) {

    }




}
