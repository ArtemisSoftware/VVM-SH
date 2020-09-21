package com.vvm.sh.ui.registoVisita.modelos;

import androidx.room.Ignore;

public class RegistoVisita {

    public boolean clienteValido = false;

    @Ignore
    public boolean trabalhoValido = false;

    @Ignore
    public int numeroTrabalhos = 0;

    @Ignore
    public boolean assinaturaValido = false;

    @Ignore
    public boolean valido = false;

    public RegistoVisita(boolean clienteValido) {
        this.clienteValido = clienteValido;
    }
}
