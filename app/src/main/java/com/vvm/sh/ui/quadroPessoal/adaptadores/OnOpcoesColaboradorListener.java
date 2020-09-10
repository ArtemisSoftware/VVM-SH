package com.vvm.sh.ui.quadroPessoal.adaptadores;

public interface OnOpcoesColaboradorListener {

    void OnEditarColaborador(int id, int origem);

    void OnDemitirColaborador(int id);

    void OnReademitirColaborador(int id);

    void OnRemoverColaborador(int id);

    void OnDetalheColaborador(int id);

}
