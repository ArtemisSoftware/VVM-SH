package com.vvm.sh.ui.crossSelling;

import com.vvm.sh.ui.opcoes.modelos.Tipo;

public interface OnCrossSellingListener {

    void onItemChecked(Tipo tipo, boolean selecao);

}
