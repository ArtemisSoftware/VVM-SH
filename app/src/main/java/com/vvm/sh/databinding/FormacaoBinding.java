package com.vvm.sh.databinding;

import androidx.databinding.BindingAdapter;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.ui.atividadesPendentes.relatorios.AcaoFormacaoResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;

import java.util.List;

public class FormacaoBinding {


    @BindingAdapter({"tipos_", "acaoFormacao"})
    public static void setTipos_(MaterialSpinner view, List<Tipo> registos, AcaoFormacaoResultado resultado) {

        if (registos == null)
            return;

        view.setItems(registos);

        if(resultado != null) {

            for (int index = 0; index < registos.size(); ++index) {

                if(registos.get(index).id == resultado.idDesignacao){
                    view.setSelectedIndex(index);
                    break;
                }
            }
        }
    }

}
