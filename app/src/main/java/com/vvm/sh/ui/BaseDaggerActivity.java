package com.vvm.sh.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityBaseDaggerBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.util.metodos.MensagensUtil;
import com.vvm.sh.util.interfaces.OnDialogoListener;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseDaggerActivity extends DaggerAppCompatActivity {

    private ActivityBaseDaggerBinding activityBaseBinding;
    protected ViewDataBinding activityBinding;


    @Inject
    public ViewModelProviderFactory providerFactory_;

    public ProgressBar mProgressBar;

    public MensagensUtil dialogo;

    protected OnDialogoListener listenerActivity;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogo = new MensagensUtil(this);

        activityBaseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_dagger);
        activityBinding = DataBindingUtil.inflate(getLayoutInflater(), obterLayout(), activityBaseBinding.activityContent, false);

        activityBaseBinding.activityContent.addView(activityBinding.getRoot());
        intActivity(savedInstanceState);

        activityBaseBinding.setLifecycleOwner(this);
        activityBaseBinding.setBaseviewmodel(obterBaseViewModel());

        ButterKnife.bind(this);


        if(toolbar != null) {

            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);

                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        }

        listenerActivity = new OnDialogoListener() {
            @Override
            public void onExecutar() {
                finish();
            }
        };


        //--dialog = new SweetAlertDialog(this);

    }


    /**
     * Metodo que inicia a activity
     * @param savedInstanceState
     */
    protected abstract void intActivity(Bundle savedInstanceState);


    /**
     * Metodo que permite obter o layout da activity
     * @return um layout
     */
    protected abstract int obterLayout();


    /**
     * Metodo que permite obter o view model associado à activity
     * @return um view model
     */
    protected abstract BaseViewModel obterBaseViewModel();


    /**
     * Metodo que permite subscrever observadores
     */
    protected abstract void subscreverObservadores();


}