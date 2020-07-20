package com.vvm.sh.ui.crossSelling;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityCrossSellingBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.crossSelling.adaptadores.OnCrossSellingListener;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;

import javax.inject.Inject;


public class CrossSellingActivity extends BaseDaggerActivity implements OnCrossSellingListener, OnSpinnerItemSelectedListener {


    private ActivityCrossSellingBinding activityCrossSellingBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private CrossSellingViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(CrossSellingViewModel.class);

        activityCrossSellingBinding = (ActivityCrossSellingBinding) activityBinding;
        activityCrossSellingBinding.setLifecycleOwner(this);
        activityCrossSellingBinding.setListener(this);
        activityCrossSellingBinding.setViewmodel(viewModel);

        activityCrossSellingBinding.spnrAreaRecomendacao.setOnSpinnerItemSelectedListener(this);

        subscreverObservadores();

        viewModel.obterProdutos();
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_cross_selling;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        //TODO: subscrever observadores do viewmodel
    }

    @Override
    public void onItemChecked(Tipo tipo, boolean selecao) {

        boolean sinaletica = ((Tipo) activityCrossSellingBinding.spnrAreaRecomendacao.getSelectedItem()).detalhe;


        if(selecao == true & sinaletica == false){
            //--gravar(null);
        }
        else if(selecao == true & sinaletica == true){

            DialogoSinaletica dialogo = new DialogoSinaletica();
            dialogo.show(getSupportFragmentManager(), "example dialog");
        }

        else{
            //--acessoBdCrossSelling.remover(registos.get(posicao).obterId());
            //--atualizar();
        }


    }

    @Override
    public void gravarSinaletica(Tipo tipo, String idDimensao, String idTipo) {

    }

    @Override
    public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
        Tipo item = (Tipo)parent.getItemAtPosition(position);

        viewModel.obterCrossSelling(item);
    }



}





//public class CrossSellingActivity extends BaseActivity implements OnCheckBoxItemListener {
//
//
//    @BindView(R.id.rcl_registos)
//    RecyclerView rcl_registos;
//
//    @BindView(R.id.rlt_lyt_sinaletica)
//    RelativeLayout rlt_lyt_sinaletica;
//
//    @BindView(R.id.spnr_area_recomendacao)
//    PowerSpinnerView spnr_area_recomendacao;
//
//    private CrossSellingRecyclerAdapter crossSellingRecyclerAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cross_selling);
//
//        iniciarAtividade();
//        obterRegistos("1", true);
//
//    }
//
//
//
//    //------------------------
//    //Metodos locais
//    //------------------------
//
//
//    /**
//     * Metodo que permite iniciar a atividade
//     */
//    private void iniciarAtividade(){
//
//        crossSellingRecyclerAdapter = new CrossSellingRecyclerAdapter(this);
//        rcl_registos.setAdapter(crossSellingRecyclerAdapter);
//        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
//        rcl_registos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
//    }
//
//
//    private void obterRegistos(String idArea, boolean sinaletica){
//
//        //--TESTE (apagar quando houver dados)
//
//        List<IconSpinnerItem> iconSpinnerItems = new ArrayList<>();
//        iconSpinnerItems.add(new IconSpinnerItem(null, "Report 1"));
//        iconSpinnerItems.add(new IconSpinnerItem(null, "Report do Report do Report para o Report 2"));
//        iconSpinnerItems.add(new IconSpinnerItem(null, "Report 3"));
//        iconSpinnerItems.add(new IconSpinnerItem(null, "Report 4"));
//        iconSpinnerItems.add(new IconSpinnerItem(null, "Report 5"));
//
//        IconSpinnerAdapter iconSpinnerAdapter = new IconSpinnerAdapter(spnr_area_recomendacao);
//        spnr_area_recomendacao.setSpinnerAdapter(iconSpinnerAdapter);
//        spnr_area_recomendacao.setItems(iconSpinnerItems);
//
//
//        List<Item> t1 = new ArrayList<>();
//        t1.add(new CrossSelling(1, "Produto numero 1", "200 x 400", "tipo duplo", 1));
//        t1.add(new CrossSelling(2, "Produto numero 2", null, null, 1));
//        t1.add(new CrossSelling(2, "Produto numero 3", null, null, 0));
//
//        crossSellingRecyclerAdapter.fixarRegistos(t1);
//
//        //TODO: chamar metodo do viewmodel
//    }
//
//    /**
//     * Metodo que permite subscrever observadores
//     */
//    private void subscreverObservadores(){
//
//
//        //TODO: subscrever observadores do viewmodel
//
//    }
//
//
//    //---------------------
//    //Eventos
//    //---------------------
//
//    @Override
//    public void onItemChecked(int posicao, boolean selecao) {
//
//        //TODO: saber se é sinaletica é uma informação contida no objeto da spinner
//
//
//        if(selecao == true /*& sinaletica == false*/){
//            //--gravar(null);
//        }
//        else if(selecao == true /*& sinaletica == true*/){
//            //--dialogoSinaletica();
//        }
//
//        else{
//            //--acessoBdCrossSelling.remover(registos.get(posicao).obterId());
//            //--atualizar();
//        }
//
//    }
//
//
//    /*
//    private CheckBox.OnClickListener checkbox_OnClickListener = new CheckBox.OnClickListener(){
//
//        @Override
//        public void onClick(View v) {
//
//            posicao = Integer.parseInt(v.getTag().toString());  // Here we get the position that we have set for the checkbox using setTag.
//            ((CrossSelling) registos.get(posicao)).fixarSelecao(((CompoundButton) v).isChecked()); // Set the value of checkbox to maintain its state.
//
//
//            if(((CheckBox) v).isChecked() == true & sinaletica == false){
//                gravar(null);
//            }
//            else if(((CrossSelling) registos.get(posicao)).obterSelecao() == true & sinaletica == true){
//                dialogoSinaletica();
//            }
//
//            else{
//                acessoBdCrossSelling.remover(registos.get(posicao).obterId());
//                atualizar();
//            }
//        }
//    };
//    */
//
///*
//
//
//	private Spinner.OnItemSelectedListener spnr_area_recomendacao_OnItemSelectedListener = new Spinner.OnItemSelectedListener(){
//
//		@Override
//		public void onItemSelected(AdapterView<?> spnr, View arg1, int position,long arg3) {
//
//			ItemSpinner item = (ItemSpinner) ((SpinnerAdaptador) spnr.getAdapter()).getItem(position);
//
//			((TextView) findViewById(R.id.txt_view_sinaletica)).setText(AppIF.SEM_TEXTO);
//
//			if(Boolean.valueOf(item.obterCodigo()) == true){
//				((TextView) findViewById(R.id.txt_view_sinaletica)).setText(SintaxeIF.SINALETICA);
//			}
//
//			((CrossSelling_Adaptador) adaptador).atualizar(item.obterId(), Boolean.valueOf(item.obterCodigo()));
//		}
//
//		@Override
//		public void onNothingSelected(AdapterView<?> arg0) {}
//
//	};
//
//
//
// */
//}
