package com.vvm.sh.ui.ocorrencias;

import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.metodos.Conversor;
import com.vvm.sh.util.metodos.Datas;

public class Ocorrencia extends Item {

    private String descricaoDepartamento, contrato, dataEntrada, marca, situacao;
    private String dias, observacao;
    private boolean fiscalizado;


    public Ocorrencia(int id, String descricao, int fiscalizado, String dias, String observacao) {
        super(id, descricao);

        this.dias = dias;
        this.observacao = observacao;
        this.fiscalizado = Conversor.converter_Integer_Para_Boolean(fiscalizado);
    }


    public Ocorrencia(int id, String descricaoTipo, String descricaoDepartamento, String contrato, String dataEntrada, String marca, String estado) {
        super(id, descricaoTipo);

        this.descricaoDepartamento = descricaoDepartamento;
        this.contrato = contrato;
        this.dataEntrada = Datas.converterData(dataEntrada, Datas.DATA_FORMATO_DD_MM_YYYY);
        this.marca = marca;
        this.situacao = estado;
    }


    /**
     * Metodo que permite obter os dias
     * @return  os dias
     */
    public String obterDuracao(){
        return dias;
    }


    /**
     * Metodo que permite obter a observacao
     * @return  a observacao
     */
    public String obterObservacao(){
        return observacao;
    }


    /**
     * Metodo que permite obter o restado da fiscalizacao
     * @return true caso esteja fiscalizado ou false caso contrario
     */
    public boolean obterEstadoFiscalizacao(){
        return fiscalizado;
    }


    /**
     * Metodo que permite obter a fiscalizacao
     * @return  a fiscalizacao
     */
    public String obterFiscalizacao(){

        if(fiscalizado == false){
            return "SintaxeIF.NAO_FISCALIZADO";
        }
        else{
            return "SintaxeIF.FISCALIZADO";
        }
    }







    /**
     * Metodo que devolve o departamento da ocorrência
     * @return o departamento da ocorrência
     */
    public String obterDepartamento(){
        return descricaoDepartamento;
    }




    /**
     * Metodo que devolve o numero do contrato da ocorrência
     * @return o numero do contrato da ocorrência
     */
    public String obterContrato(){
        return contrato;
    }




    /**
     * Metodo que devolve a data de entrada da ocorrência
     * @return a data de entrada da ocorrência
     */
    public String obterDataEntrada(){
        return dataEntrada;
    }



    /**
     * Metodo que devolve a marca da ocorrência
     * @return a marca da entrada da ocorrência
     */
    public String obterMarca(){
        return marca;
    }



    /**
     * Metodo que devolve o estado da ocorrência
     * @return o estado da ocorrência
     */
    public String obterSituacao(){
        return situacao;
    }


}
