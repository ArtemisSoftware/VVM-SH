package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.baseDados.EmailDao;

public class UploadRepositorio {


    private final SegurancaAlimentarApi api;

    //private final EmailDao emailDao;


    public UploadRepositorio(@NonNull SegurancaAlimentarApi api/*, @NonNull EmailDao emailDao*/) {
        this.api = api;
        //this.emailDao = emailDao;
    }
}
