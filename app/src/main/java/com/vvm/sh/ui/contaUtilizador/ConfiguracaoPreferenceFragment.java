package com.vvm.sh.ui.contaUtilizador;

import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.vvm.sh.R;

public class ConfiguracaoPreferenceFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.pref_configuracao, rootKey);


        // THIS IS REQUIRED IF YOU DON'T HAVE 'entries' and 'entryValues' in your XML
        fixarListaPermissoes((ListPreference) findPreference(getString(R.string.key_permissoes)));

        ((ListPreference) findPreference(getString(R.string.key_permissoes))).setOnPreferenceClickListener(permissoes_OnClick);


        try {
            ((Preference) findPreference(getString(R.string.key_versao_app))).setSummary(getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName);

            /*
            body = "\n\n-----------------------------\nPlease don't remove this information\n Device OS: Android \n Device OS version: " +
                    Build.VERSION.RELEASE + "\n App Version: " + body + "\n Device Brand: " + Build.BRAND +
                    "\n Device Model: " + Build.MODEL + "\n Device Manufacturer: " + Build.MANUFACTURER;
            */
        }
        catch (PackageManager.NameNotFoundException e) {
        }

    }



    /**
     * Email client intent to send support mail
     * Appends the necessary device information to email body
     * useful when providing support
     */
    /*
    public void sendFeedback(Context context) {
        String body = null;
        try {
            body = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;

            body = "\n\n-----------------------------\nPlease don't remove this information\n Device OS: Android \n Device OS version: " +
                    Build.VERSION.RELEASE + "\n App Version: " + body + "\n Device Brand: " + Build.BRAND +
                    "\n Device Model: " + Build.MODEL + "\n Device Manufacturer: " + Build.MANUFACTURER;
        }
        catch (PackageManager.NameNotFoundException e) {
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"contact@androidhive.info"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Query from android app");
        intent.putExtra(Intent.EXTRA_TEXT, body);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.choose_email_client)));
    }
*/

    private void fixarListaPermissoes(ListPreference lista) {

        CharSequence[] entries = { "English", "French" };
        CharSequence[] entryValues = {"1" , "2"};
        lista.setEntries(entries);
        lista.setDefaultValue("1");
        lista.setEntryValues(entryValues);
    }


    //---------------------
    //Eventos
    //---------------------


    Preference.OnPreferenceClickListener permissoes_OnClick = new Preference.OnPreferenceClickListener(){
        @Override
        public boolean onPreferenceClick(Preference preference) {

            fixarListaPermissoes((ListPreference) findPreference(getString(R.string.key_permissoes)));
            return false;
        }
    };
}
