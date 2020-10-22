package com.vvm.sh.databinding;

import android.widget.GridView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.ui.agenda.adaptadores.MarcacaoRecyclerAdapter;
import com.vvm.sh.ui.agenda.adaptadores.OnAgendaListener;
import com.vvm.sh.ui.agenda.modelos.Marcacao;

import java.util.List;

public class ImagemBinding {


    @BindingAdapter({"caminhoImagens"})
    public static void setImagensGaleria(GridView view, List<String> items/*, OnAgendaListener listener*/) {

        if(items == null){
            return;
        }

        int gridWidth = view.getContext().getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridWidth/3;
        view.setColumnWidth(imageWidth);


//        //use the grid adapter to adapter the images to gridview
//        GridImageAdapter adapter = new GridImageAdapter(getActivity(), R.layout.layout_grid_imageview, imgURLs);
//        gridView.setAdapter(adapter);
//
//        MarcacaoRecyclerAdapter adapter = (MarcacaoRecyclerAdapter) view.getAdapter();
//
//        if(adapter == null){
//            adapter = new MarcacaoRecyclerAdapter(view.getContext(), items, listener);
//
//            view.setAdapter(adapter);
//        }
//        else{
//            adapter.atualizar(items);
//        }
    }



}
