package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vvm.sh.R;
import com.vvm.sh.databinding.FragmentPropostaCondicoesStBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;

import java.util.List;


public class CondicoesStFragment extends Fragment {


    private FragmentPropostaCondicoesStBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;



    public CondicoesStFragment() {
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

        binding = FragmentPropostaCondicoesStBinding.inflate(inflater);
        return binding.getRoot();
    }



    //--------------------
    //Metodos locais
    //--------------------


    public void atualizar(List<Proposta> items) {
        binding.setPropostas(items);
    }







    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
