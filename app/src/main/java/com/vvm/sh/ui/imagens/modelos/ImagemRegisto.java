package com.vvm.sh.ui.imagens.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.Tipo;

public class ImagemRegisto {

    @Embedded
    public ImagemResultado imagem;


    @ColumnInfo(name = "descricao")
    public String descricao;
}
