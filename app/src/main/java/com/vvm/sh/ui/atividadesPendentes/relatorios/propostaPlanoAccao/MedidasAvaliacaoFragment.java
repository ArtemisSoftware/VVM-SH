package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.vvm.sh.R;
import com.vvm.sh.databinding.FragmentPropostaMedidasAvaliacaoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.adaptadores.OnPropostaPlanoAcaoListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;

import java.util.List;


public class MedidasAvaliacaoFragment extends Fragment implements CheckBox.OnCheckedChangeListener {

    private FragmentPropostaMedidasAvaliacaoBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    private OnPropostaPlanoAcaoListener listenerPropostas;
    private OnPropostaPlanoAcaoListener.OnPropostaListener listenerProposta;


    public MedidasAvaliacaoFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPropostaMedidasAvaliacaoBinding.inflate(inflater);
        binding.setListener(listenerPropostas);

        binding.chkBoxItem.setOnCheckedChangeListener(this);
        return binding.getRoot();
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        listenerProposta.OnSelecionarTudo(isChecked);
    }

    //-------------------------
    //Medidas locais
    //-------------------------

    public void atualizar(List<Proposta> items) {
        binding.setPropostas(items);
    }









    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPropostaPlanoAcaoListener) {
            listenerPropostas = (OnPropostaPlanoAcaoListener) context;
            listenerProposta = (OnPropostaPlanoAcaoListener.OnPropostaListener) context;
        } else {
            throw new RuntimeException(context.toString()  + " must implement OnPropostaPlanoAcaoListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listenerPropostas = null;
    }



}
