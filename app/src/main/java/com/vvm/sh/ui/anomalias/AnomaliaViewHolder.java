package com.vvm.sh.ui.anomalias;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ItemAnomaliaBinding;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.adaptadores.ItemViewHolder;

import butterknife.BindView;

public class AnomaliaViewHolder extends RecyclerView.ViewHolder {

    ItemAnomaliaBinding binding;

    public AnomaliaViewHolder(@NonNull View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

}
