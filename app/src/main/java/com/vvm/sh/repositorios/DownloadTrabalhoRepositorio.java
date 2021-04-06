package com.vvm.sh.repositorios;

import androidx.annotation.NonNull;

import com.vvm.sh.baseDados.dao.TransferenciasDao;

public class DownloadTrabalhoRepositorio {


    private final TransferenciasDao transferenciasDao;

    public DownloadTrabalhoRepositorio(@NonNull TransferenciasDao transferenciasDao) {
        this.transferenciasDao = transferenciasDao;
    }

}
