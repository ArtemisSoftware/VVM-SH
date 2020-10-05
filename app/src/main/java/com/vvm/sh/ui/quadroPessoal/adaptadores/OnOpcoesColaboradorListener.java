package com.vvm.sh.ui.quadroPessoal.adaptadores;

import com.vvm.sh.ui.quadroPessoal.modelos.ColaboradorRegisto;

public interface OnOpcoesColaboradorListener {

    void OnEditarColaborador(int id, int origem);

    void OnDemitirColaborador(ColaboradorRegisto registo);

    void OnReademitirColaborador(ColaboradorRegisto registo);

    void OnRemoverColaborador(int id);

    void OnDetalheColaborador(int id);

}
