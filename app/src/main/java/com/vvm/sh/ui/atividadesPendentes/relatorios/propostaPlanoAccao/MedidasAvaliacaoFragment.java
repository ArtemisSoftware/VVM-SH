package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vvm.sh.R;
import com.vvm.sh.databinding.FragmentMedidasAvaliacaoBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;

import java.util.List;


public class MedidasAvaliacaoFragment extends Fragment {

    private FragmentMedidasAvaliacaoBinding binding;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    //private OnPlanoAtividadeListener listenerAtividade;



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
        binding = FragmentMedidasAvaliacaoBinding.inflate(inflater);
        //binding.setListener(listenerAtividade);
        return binding.getRoot();
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
//        if (context instanceof OnPlanoAtividadeListener) {
//            listenerAtividade = (OnPlanoAtividadeListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnPlanoAtividadeListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }


}
