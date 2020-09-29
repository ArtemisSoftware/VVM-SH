package com.vvm.sh.ui.planoAccao.adaptadores;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vvm.sh.ui.planoAccao.AnuidadeFragment;
import com.vvm.sh.ui.planoAccao.modelo.AtividadeRegisto;

import java.util.ArrayList;
import java.util.List;

public class AnuidadePagerAdapter extends FragmentPagerAdapter {


    private final List<Fragment> mFragmentList = new ArrayList<>();


    public AnuidadePagerAdapter(@NonNull FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
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


    public void atualizar(List<AtividadeRegisto> data, int anuidade) {

        ((AnuidadeFragment) mFragmentList.get(anuidade)).atualizar(data);
    }

}
