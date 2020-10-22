package com.vvm.sh.ui.imagens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityAtividadesPendentesBinding;
import com.vvm.sh.databinding.ActivityBibliotecaImagensBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesViewModel;
import com.vvm.sh.util.metodos.DiretoriasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class BibliotecaImagensActivity extends BaseDaggerActivity {


    private ActivityBibliotecaImagensBinding activityBibliotecaImagensBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private ImagemViewModel viewModel;




    @BindView(R.id.gridView)
    GridView gridView;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ImagemViewModel.class);

        activityBibliotecaImagensBinding = (ActivityBibliotecaImagensBinding) activityBinding;
        activityBibliotecaImagensBinding.setLifecycleOwner(this);
        activityBibliotecaImagensBinding.setViewmodel(viewModel);

        activityBibliotecaImagensBinding.spnrGaleria.setOnItemSelectedListener(spnr_galeria_ItemSelected);

        subscreverObservadores();

        viewModel.obterDiretoriasImagens();
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_biblioteca_imagens;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }


    private void iniciarBiblioteca(){

//        directorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Log.d(TAG, "onItemClick: selected: " + directories.get(position));
//
//                //setup our image grid for the directory chosen
//                setupGridView(directories.get(position));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }


    //---------------------
    //Metodos locais
    //---------------------

     private void setupGaleria(String selectedDirectory){

        final List<String> imgURLs = DiretoriasUtil.getFilePaths(selectedDirectory);

        if(imgURLs.size() > 0){



//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Log.d(TAG, "onItemClick: selected an image: " + imgURLs.get(position));
//
//                    setImage(imgURLs.get(position), galleryImage);
//                    mSelectedImage = imgURLs.get(position);
//                }
//            });
        }
    }



    //-------------------
    //Eventos
    //--------------------


    MaterialSpinner.OnItemSelectedListener spnr_galeria_ItemSelected = new MaterialSpinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            Tipo galeria = (Tipo) activityBibliotecaImagensBinding.spnrGaleria.getItems().get(position);
            viewModel.obterCaminhoImagens(DiretoriasUtil.getFilePaths(galeria.codigo));
            //setupGaleria(galeria);
        }
    };




//
//    //constants
//    private static final int NUM_GRID_COLUMNS = 3;
//    private static final int NEW_PHOTO_REQUEST = 3567;
//
//
//    //widgets
//    private ImageView galleryImage;
//    private Spinner directorySpinner;
//
//    //vars
//    private ArrayList<String> directories;
//    private String mSelectedImage;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
//        galleryImage = (ImageView) view.findViewById(R.id.galleryImageView);
//        gridView = (GridView) view.findViewById(R.id.gridView);
//        directorySpinner = (Spinner) view.findViewById(R.id.spinnerDirectory);
//        directories = new ArrayList<>();
//        Log.d(TAG, "onCreateView: started.");
//
//        ImageView close = (ImageView) view.findViewById(R.id.close);
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: closing the gallery fragment.");
//                getActivity().setResult(NEW_PHOTO_REQUEST);
//                getActivity().finish();
//            }
//        });
//
//
//        TextView choose = (TextView) view.findViewById(R.id.choose);
//        choose.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: photo has been chosen.");
//
//                if(!mSelectedImage.equals("")){
//                    getActivity().setResult(
//                            NEW_PHOTO_REQUEST,
//                            getActivity().getIntent().putExtra(getString(R.string.intent_new_gallery_photo), mSelectedImage));
//                    getActivity().finish();
//                }
//            }
//        });
//
//        init();
//
//        return view;
//    }
//
//

//
//

//
//
//    private void setImage(String imgURL, ImageView imageView){
//        Log.d(TAG, "setImage: setting image");
//
//        Glide.with(getActivity())
//                .load(imgURL)
//                .into(imageView);
//    }


}