package com.vvm.sh.ui.contaUtilizador;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.vvm.sh.BuildConfig;
import com.vvm.sh.R;
import com.vvm.sh.util.metodos.DatasUtil;

import java.io.File;
import java.util.List;

public class DefinicoesPreferenceFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_definicoes, rootKey);

        ((Preference) findPreference(getString(R.string.key_versao_app))).setSummary(BuildConfig.VERSION_NAME);

        try {

            PackageManager manager = getActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] permissoes = info.requestedPermissions;

            if(permissoes != null){
                if(permissoes.length > 0){

                    String registos = "";

                    for (String item : permissoes) {
                        registos += item + "\n";
                    }

                    ((Preference) findPreference(getString(R.string.key_permissoes))).setSummary(registos);
                }
                else{
                    ((Preference) findPreference(getString(R.string.key_permissoes))).setSummary(getString(R.string.sem_permissoes));
                }
            }
            else{
                ((Preference) findPreference(getString(R.string.key_permissoes))).setSummary(getString(R.string.sem_permissoes));
            }

            ApplicationInfo appInfo = manager.getApplicationInfo(info.packageName, 0);
            String data = DatasUtil.converterData(new File(appInfo.sourceDir).lastModified(), DatasUtil.FORMATO_DD_MM_YYYY__HH_MM_SS);

            ((Preference) findPreference(getString(R.string.key_ultima_atualizacao))).setSummary(data);

        }
        catch (PackageManager.NameNotFoundException e) {
        }
    }


}
