package com.vvm.sh.util.mapeamento;

import com.vvm.sh.util.metodos.ImagemUtil;

public class ImagemMapper {

    public String asByteArray(byte[] imagem) {
        return ImagemUtil.converterByteArray(imagem);
    }

}
