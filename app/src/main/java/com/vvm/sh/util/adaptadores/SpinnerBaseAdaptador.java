package com.vvm.sh.util.adaptadores;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.vvm.sh.util.itens.ItemSpinner;

import java.util.ArrayList;

public abstract class SpinnerBaseAdaptador  extends ArrayAdapter<ItemSpinner> {


    private final String  SEPARADOR_ID =  " # ";

    protected ItemSpinner[] registos;

    protected Context contexto;

    protected int posicao;


    public SpinnerBaseAdaptador(Context contexto, ArrayList<ItemSpinner> registos) {
        super(contexto, android.R.layout.simple_spinner_item, obterRegistos(registos));

        this.contexto = contexto;
        this.registos = obterRegistos(registos);
    }

    public SpinnerBaseAdaptador(Context contexto, String[] registos) {
        super(contexto, android.R.layout.simple_spinner_item, obterRegistos(registos));

        this.contexto = contexto;
        this.registos = obterRegistos(registos);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        this.posicao = ((Spinner) parent).getSelectedItemPosition();

        return obterTexto(position);
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        TextView txt = obterTexto(position);

        txt.setHeight(40);
        txt.setPadding(10, 0, 0, 0);
        txt.setGravity(Gravity.CENTER_VERTICAL);
        return txt;
    }


    //-----------------------
    //Metodos locais
    //-----------------------



    /**
     * Metodo que permite obter os registos do adaptador
     * @param info registos a formatar
     * @return  os registos do adaptador
     */
    protected static ItemSpinner [] obterRegistos(ArrayList<ItemSpinner> info){

        ItemSpinner [] resultado = {};

        if(info.size() != 0){

            resultado = new ItemSpinner [info.size()];

            for(int index = 0; index < info.size(); ++index){
                resultado[index] = info.get(index);
            }
        }

        return resultado;
    }


    /**
     * Metodo que permite obter os registos do adaptador
     * @param info registos a formatar
     * @return  os registos do adaptador
     */
    protected static ItemSpinner [] obterRegistos(String[] info){

        ItemSpinner [] resultado = {};

        if(info.length != 0){

            resultado = new ItemSpinner [info.length];

            for(int index = 0; index < info.length; ++index){
                //resultado[index] = new ItemSpinner(info[index]);
            }
        }

        return resultado;
    }




    /**
     * Metodo que permite obter a posicao de um identificador
     * @param id o identificador a pesquisar
     * @return a posicao do indetificador
     */
    public int obterPosicaoId(String id){

        int resultado = 0;

        for(int index = 0; index < registos.length; ++index){

            if(id.equals(registos[index].obterId() + "")){
                resultado = index;
                break;
            }
            /*
            if(registos[index].obterId().equals(id) == true){
                resultado = index;
                break;
            }
            */
        }

        return resultado;
    }



    /**
     * Metodo que permite obter a posicao de uma descricao
     * @param descricao a descricao
     * @return a posicao da descricao
     */
    public int obterPosicaoDescricao(String descricao){

        int resultado = 0;

        for(int index = 0; index < registos.length; ++index){

            if(registos[index].obterDescricao().equals(descricao) == true){
                resultado = index;
                break;
            }
        }

        return resultado;
    }







    /**
     * Metodo que permite obter o texto da spinner
     * @param posicao a posicao do texto
     * @return um objeto
     */
    private TextView obterTexto(int posicao){

        TextView txt = new TextView(contexto);
        txt.setTextColor(Color.BLACK);

        try{

            /*
            if(AppConfigIF.VERSAO_TESTE){
                txt.setText(registos[posicao].obterId() + SEPARADOR_ID + registos[posicao].obterDescricao());
            }
            else{*/
                txt.setText(registos[posicao].obterDescricao());
            //}
        }
        catch(IndexOutOfBoundsException w){
            this.posicao = 0;
        }

        return txt;
    }


    public int getCount(){
        return registos.length;
    }

    public int obterPosicao(){
        return posicao;
    }

/*
    public String obterIdItem(){
        return registos[posicao].obterId();
    }
    */

    public String obterDescricao(){
        return registos[posicao].obterDescricao();
    }

    public ItemSpinner obterItem(){
        return registos[posicao];
    }


}