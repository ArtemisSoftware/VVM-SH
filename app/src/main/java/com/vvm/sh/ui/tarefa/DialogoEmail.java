package com.vvm.sh.ui.tarefa;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoEmailBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.servicos.ResultadoAsyncTask;
import com.vvm.sh.ui.agenda.modelos.Resultado;
import com.vvm.sh.ui.cliente.Cliente;
import com.vvm.sh.ui.BaseDaggerDialogFragment;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.ui.tarefa.adaptadores.OnTarefaListener;
import com.vvm.sh.ui.tarefa.modelos.EmailResultado;
import com.vvm.sh.util.MensagensUtil;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.Preferencias;

import javax.inject.Inject;

public class DialogoEmail extends BaseDaggerDialogFragment {



    private DialogoEmailBinding binding;


    @Inject
    ViewModelProviderFactory providerFactory;

    private TarefaViewModel viewModel;


    private OnTarefaListener listener;


    private static final String ARGUMENTO_EMAIL = "email";



    public DialogoEmail() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoEmail newInstance() {
        DialogoEmail frag = new DialogoEmail();

//        Bundle args = new Bundle();
//        args.putString(ARGUMENTO_EMAIL, email);
//        frag.setArguments(args);
        return frag;
    }



    @Override
    protected void initDialogo(AlertDialog.Builder builder) {

        listener = (OnTarefaListener) getContext();

        viewModel = ViewModelProviders.of(this, providerFactory).get(TarefaViewModel.class);

        binding = (DialogoEmailBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        viewModel.obterEmail(Preferencias.obterIdTarefa(getContext()));


        builder.setPositiveButton(Sintaxe.Opcoes.GRAVAR,  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String endereco = binding.txtInpEmail.getText().toString();
                Tipo autorizacao = (Tipo)binding.spnrEmail.getItems().get(binding.spnrEmail.getSelectedIndex());

                EmailResultado email = new EmailResultado(Preferencias.obterIdTarefa(getContext()), endereco, autorizacao);

                listener.OnGravarEmailListener(email);
            }
        });

        builder.setNegativeButton(Sintaxe.Opcoes.CANCELAR, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                terminarDialogo();
            }
        });

    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_email;
    }

    @Override
    protected int obterTitulo() {
        return R.string.email;
    }


    @Override
    protected void subscreverObservadores() {


        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        MensagensUtil.sucesso();
                        terminarDialogo();
                        break;

                    default:
                        break;
                }

            }
        });

    }


//    @BindView(R.id.txt_inp_email)
//    TextInputEditText txt_inp_email;
//
//    private DialogEmailListener listener;
//
//
//    @Override
//    protected int obterLayout() {
//        return R.layout.dialogo_email;
//    }
//
//    @Override
//    protected void criarDialogo(AlertDialog.Builder builder) {
//
//        builder.setTitle(getString(R.string.email))
//                .setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                })
//                .setPositiveButton(getString(R.string.gravar), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        //String username = editTextUsername.getText().toString();
//                        //String password = editTextPassword.getText().toString();
//                        listener.gravarEmail(txt_inp_email.getText().toString(), 1);
//                    }
//                });
//
//        //editTextUsername = view.findViewById(R.id.edit_username);
//        //editTextPassword = view.findViewById(R.id.edit_password);
//    }
//
//    @Override
//    protected String obterTitulo() {
//        return getString(R.string.email);
//    }

//
//*/
//
//
//    /**
//     * Metodo que valida o email
//     * @return true caso os dados sejam válidos e false caso contrário
//     */
//    /*
//    private boolean validarEmail(){
//
//        boolean valido = true;
//        LinearLayout lnr_lyt_email_erros = (LinearLayout)viewEmail.findViewById(R.id.lnr_lyt_email_erros);
//        TextView txt_view_email_erro = (TextView)viewEmail.findViewById(R.id.txt_view_email_erro);
//
//        txt_view_email_erro.setVisibility(View.GONE);
//        lnr_lyt_email_erros.setVisibility(View.GONE);
//
//        if(spnr_email_estado.getSelectedItem().toString().equals(SintaxeIF.EMAIL_RESPOSTA_AUTORIZADO) == true
//                ||
//                spnr_email_estado.getSelectedItem().toString().equals(SintaxeIF.EMAIL_RESPOSTA_NAO_AUTORIZADO) == true){
//
//            if(edit_txt_informacao_email.getText().toString().equals(AppIF.SEM_TEXTO)
//                    ||
//                    edit_txt_informacao_email.getText().toString().contains(SintaxeIF.ARROBA) == false){
//
//                txt_view_email_erro.setVisibility(View.VISIBLE);
//                valido = false & valido;
//            }
//        }
//
//        if(valido == false){
//            lnr_lyt_email_erros.setVisibility(View.VISIBLE);
//        }
//        return valido;
//    }
//*/

}