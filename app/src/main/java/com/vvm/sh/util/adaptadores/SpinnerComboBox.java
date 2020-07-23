package com.vvm.sh.util.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

import com.vvm.sh.util.itens.ItemSpinner;

@SuppressLint("AppCompatCustomView")
public class SpinnerComboBox extends Spinner {

    private boolean selecao = false;

    public SpinnerComboBox(Context context) {
        super(context);
    }

    public SpinnerComboBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Metodo que permite obter o item selecionado
     * @return um item
     */
    public ItemSpinner obterItem() {
        return ((SpinnerBaseAdaptador) getAdapter()).obterItem();
    }


    /**
     * Metodo que indica se a spinner foi selecionada<br>
     * @return true caso tenha sido selecionada ou fals caso contrário
     */
    public boolean selecionado(){

        if(selecao == false){
            selecao = true;
            return false;
        }

        return true;
    }


}
