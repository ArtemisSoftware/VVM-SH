package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.api.SegurancaAlimentarApi;
import com.vvm.sh.baseDados.EmailDao;
import com.vvm.sh.baseDados.dao.UploadDao;

public class UploadRepositorio {


    private final SegurancaAlimentarApi api;

    private final UploadDao uploadDao;


    public UploadRepositorio(@NonNull SegurancaAlimentarApi api, @NonNull UploadDao uploadDao) {
        this.api = api;
        this.uploadDao = uploadDao;
    }
}
