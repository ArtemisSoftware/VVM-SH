package com.vvm.sh.ui.atividadesPendentes;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.ui.BaseDialogFragment;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.DatasUtil;

import butterknife.BindView;

public class DialogoDetalheAtividadePendente extends BaseDialogFragment {

    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;


    private static final String ARGUMENTO_RESULTADO = "resultado";


    public DialogoDetalheAtividadePendente() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoDetalheAtividadePendente newInstance(AtividadePendenteResultado resultado) {
        DialogoDetalheAtividadePendente fragmento = new DialogoDetalheAtividadePendente();

        Bundle args = new Bundle();
        args.putParcelable(ARGUMENTO_RESULTADO, resultado);
        fragmento.setArguments(args);
        return fragmento;
    }





    @Override
    protected void criarDialogo(AlertDialog.Builder builder) {


        LinearLayout LLTop = new LinearLayout(getActivity());
        LLTop.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams paramT;

        paramT = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramT.setMargins(20, 10, 20, 10);


        if(getArguments().getParcelable(ARGUMENTO_RESULTADO) == null){

            TextView tv = new TextView(getActivity());
            tv.setText(getString(R.string.sem_detalhe));
            LLTop.addView(tv, paramT);

        }
        else {

            AtividadePendenteResultado resultado = getArguments().getParcelable(ARGUMENTO_RESULTADO);


            if(resultado.idEstado == Identificadores.Estados.ESTADO_EXECUTADO){

                TextView tv = new TextView(getActivity());
                tv.setText(resultado.tempoExecucao);
                LLTop.addView(tv, paramT);

                TextView tv_ = new TextView(getActivity());
                tv_.setText("Tempo de Execução");
                LLTop.addView(tv_, paramT);

                TextView tv_1 = new TextView(getActivity());
                tv_1.setText(DatasUtil.converterData(resultado.dataExecucao, DatasUtil.FORMATO_DD_MM_YYYY));
                LLTop.addView(tv_1, paramT);

                TextView tv_1_ = new TextView(getActivity());
                tv_1_.setText("Data de Execução");
                LLTop.addView(tv_1_, paramT);

            }
            else{
                TextView tv2 = new TextView(getActivity());
                tv2.setText(resultado.idAnomalia + "");
                LLTop.addView(tv2, paramT);

                TextView tv2_ = new TextView(getActivity());
                tv2_.setText("Anomalia");
                LLTop.addView(tv2_, paramT);

                TextView tv1 = new TextView(getActivity());
                tv1.setText(resultado.observacao);
                LLTop.addView(tv1, paramT);

                TextView tv1_ = new TextView(getActivity());
                tv1_.setText("Observação");
                LLTop.addView(tv1_, paramT);
            }


        }


        linearLayout.addView(LLTop, paramT);

    }

    @Override
    protected String obterTitulo() {
        return getString(R.string.atividade_pendente);
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_generico;
    }
}
