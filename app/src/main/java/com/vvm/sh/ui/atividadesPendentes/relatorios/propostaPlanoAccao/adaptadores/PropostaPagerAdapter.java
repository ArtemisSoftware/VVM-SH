package com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.adaptadores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.CondicoesStFragment;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.MedidasAvaliacaoFragment;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.modelos.Proposta;

import java.util.ArrayList;
import java.util.List;

public class PropostaPagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> mFragmentList = new ArrayList<>();


    public PropostaPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }


    public void adicionarFragmento(Fragment fragment){
        mFragmentList.add(fragment);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


    public void atualizarCondicoesSt(List<Proposta> propostas) {
        ((CondicoesStFragment) mFragmentList.get(0)).atualizar(propostas);
    }


    public void atualizarMedidasAvaliacao(List<Proposta> propostas) {
        ((MedidasAvaliacaoFragment) mFragmentList.get(1)).atualizar(propostas);
    }
}
