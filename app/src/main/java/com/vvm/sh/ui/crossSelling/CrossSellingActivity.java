package com.vvm.sh.ui.crossSelling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.util.adaptadores.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CrossSellingActivity extends BaseActivity {


    @BindView(R.id.rcl_registos)
    RecyclerView rcl_registos;

    @BindView(R.id.txt_sinaletica)
    TextView txt_sinaletica;

    private CrossSellingRecyclerAdapter crossSellingRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_selling);

        iniciarAtividade();
        obterRegistos("1", true);
    }



    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite iniciar a atividade
     */
    private void iniciarAtividade(){

        crossSellingRecyclerAdapter = new CrossSellingRecyclerAdapter();
        rcl_registos.setAdapter(crossSellingRecyclerAdapter);
        rcl_registos.setLayoutManager(new LinearLayoutManager(this));
    }


    private void obterRegistos(String idArea, boolean sinaletica){

        //--TESTE (apagar quando houver dados)

        List<Item> t1 = new ArrayList<>();
        t1.add(new CrossSelling(1, "Produto numero 1", "200 x 400", "tipo duplo", 1));
        t1.add(new CrossSelling(2, "Produto numero 2", null, null, 1));
        t1.add(new CrossSelling(2, "Produto numero 3", null, null, 0));

        crossSellingRecyclerAdapter.fixarRegistos(t1);

        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }


    /*
    private CheckBox.OnClickListener checkbox_OnClickListener = new CheckBox.OnClickListener(){

        @Override
        public void onClick(View v) {

            posicao = Integer.parseInt(v.getTag().toString());  // Here we get the position that we have set for the checkbox using setTag.
            ((CrossSelling) registos.get(posicao)).fixarSelecao(((CompoundButton) v).isChecked()); // Set the value of checkbox to maintain its state.


            if(((CheckBox) v).isChecked() == true & sinaletica == false){
                gravar(null);
            }
            else if(((CrossSelling) registos.get(posicao)).obterSelecao() == true & sinaletica == true){
                dialogoSinaletica();
            }

            else{
                acessoBdCrossSelling.remover(registos.get(posicao).obterId());
                atualizar();
            }
        }
    };
    */

/*


	private Spinner.OnItemSelectedListener spnr_area_recomendacao_OnItemSelectedListener = new Spinner.OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView<?> spnr, View arg1, int position,long arg3) {

			ItemSpinner item = (ItemSpinner) ((SpinnerAdaptador) spnr.getAdapter()).getItem(position);

			((TextView) findViewById(R.id.txt_view_sinaletica)).setText(AppIF.SEM_TEXTO);

			if(Boolean.valueOf(item.obterCodigo()) == true){
				((TextView) findViewById(R.id.txt_view_sinaletica)).setText(SintaxeIF.SINALETICA);
			}

			((CrossSelling_Adaptador) adaptador).atualizar(item.obterId(), Boolean.valueOf(item.obterCodigo()));
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {}

	};



 */
}
