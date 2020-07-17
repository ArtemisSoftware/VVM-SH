package com.vvm.sh.ui.crossSelling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.skydoves.powerspinner.IconSpinnerAdapter;
import com.skydoves.powerspinner.IconSpinnerItem;
import com.skydoves.powerspinner.PowerSpinnerView;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.OnCheckBoxItemListener;
import com.vvm.sh.util.interfaces.OnItemListener;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class CrossSellingActivity extends BaseDaggerActivity implements OnCheckBoxItemListener {





    @Override
    protected void intActivity(Bundle savedInstanceState) {

    }

    @Override
    protected int obterLayout() {
        return 0;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return null;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    public void onItemChecked(int posicao, boolean selecao) {

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
