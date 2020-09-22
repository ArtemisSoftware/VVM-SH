package com.vvm.sh.ui.registoVisita.modelos;

import androidx.room.Ignore;

public class RegistoVisita {

    public boolean clienteValido = false;

    public boolean trabalhoValido = false;

    public int numeroTrabalhos = 0;

    public boolean assinaturaValido = false;

    @Ignore
    public boolean valido = false;

    public RegistoVisita(boolean clienteValido, boolean trabalhoValido, int numeroTrabalhos, boolean assinaturaValido) {
        this.clienteValido = clienteValido;
        this.trabalhoValido = trabalhoValido;
        this.numeroTrabalhos = numeroTrabalhos;
        this.assinaturaValido = assinaturaValido;

        this.valido = assinaturaValido & trabalhoValido & assinaturaValido;
    }
}
