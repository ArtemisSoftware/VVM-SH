package com.vvm.sh.ui.imagens.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemCrossSellingBinding;
import com.vvm.sh.util.itens.ImagemQuadradaView;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;
    private int layoutResource;
    private Context mContext;

    public BibliotecaAdapter(Context context, List<String> imgURLs) {
        super(context, R.layout.item_biblioteca, imgURLs);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutResource = layoutResource;
        mContext = context;
    }

    private static class ViewHolder{
        ImagemQuadradaView image;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        final ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_biblioteca, parent, false);
            holder = new ViewHolder();
            holder.image = (ImagemQuadradaView) convertView.findViewById(R.id.gridImageView);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext)
                .load(getItem(position))
                .into(holder.image);


        return convertView;

//        final BibliotecaViewHolder holder;
//        if(convertView == null){
//
//            ItemCrossSellingBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_biblioteca, parent, false);
//            holder = new BibliotecaViewHolder(binding.getRoot());
//
//            convertView.setTag(holder);
//        }
//        else{
//            holder = (BibliotecaViewHolder) convertView.getTag();
//        }
//
//        Glide.with(mContext)
//                .load(getItem(position))
//                .into(holder.binding.gridImageView);
//

//        return convertView;
    }
}