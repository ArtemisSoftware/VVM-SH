package com.vvm.sh.ui.crossSelling.adaptadores;

import com.vvm.sh.ui.opcoes.modelos.Tipo;

public interface OnCrossSellingListener {

    void onItemChecked(Tipo tipo, boolean selecao);

    void OnGravarSinaletica(int idProduto, Tipo dimensao, Tipo tipo);

}
