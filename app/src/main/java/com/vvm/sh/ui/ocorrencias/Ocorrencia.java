package com.vvm.sh.ui.ocorrencias;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.ui.agenda.modelos.Tarefa;
import com.vvm.sh.util.adaptadores.Item;
import com.vvm.sh.util.interfaces.CheckBoxIF;
import com.vvm.sh.util.metodos.Conversor;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "ocorrencias",
        indices = {@Index(value="idTarefa", unique = false) },

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class Ocorrencia  {


    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;



    @NonNull
    @ColumnInfo(name = "idOcorrencia")
    public String idOcorrencia;


    @NonNull
    @ColumnInfo(name = "tipo")
    public String tipo;


    @NonNull
    @ColumnInfo(name = "descricaoTipo")
    public String descricaoTipo;

    @NonNull
    @ColumnInfo(name = "descricaoDepartamento")
    public String descricaoDepartamento;


    @NonNull
    @ColumnInfo(name = "contrato")
    public String contrato;


    @NonNull
    @ColumnInfo(name = "dataEntrada")
    public String dataEntrada;


    @ColumnInfo(name = "dataResolucao")
    public Date dataResolucao;


    @NonNull
    @ColumnInfo(name = "descricaoOcorrencia")
    public String descricaoOcorrencia;


    @NonNull
    @ColumnInfo(name = "marca")
    public String marca;


    @NonNull
    @ColumnInfo(name = "estado")
    public String estado;


    @Ignore
    public Ocorrencia() {
    }


    public Ocorrencia(int idTarefa, @NonNull String idOcorrencia, @NonNull String tipo,
                      @NonNull String descricaoTipo, @NonNull String descricaoDepartamento, @NonNull String contrato,
                      @NonNull String dataEntrada, Date dataResolucao, @NonNull String descricaoOcorrencia,
                      @NonNull String marca, @NonNull String estado) {
        this.idTarefa = idTarefa;
        this.idOcorrencia = idOcorrencia;
        this.tipo = tipo;
        this.descricaoTipo = descricaoTipo;
        this.descricaoDepartamento = descricaoDepartamento;
        this.contrato = contrato;
        this.dataEntrada = dataEntrada;
        this.dataResolucao = dataResolucao;
        this.descricaoOcorrencia = descricaoOcorrencia;
        this.marca = marca;
        this.estado = estado;
    }


//    private String descricaoDepartamento, contrato, dataEntrada, marca, situacao;
//    private String dias, observacao, data, codigo;
//    private boolean fiscalizado;
//    private boolean selecionado;
//
//
//    private int tipo;
//    public static final int TIPO_OCORRENCIA = 1;
//    public static final int TIPO_NOVA_OCORRENCIA = 2;
//    public static final int TIPO_HISTORICO_OCORRENCIA = 3;
//    public static final int TIPO_TIPIFICACAO = 4;
//    public static final int TIPO_REGISTO_NOVA_OCORRENCIA = 5;
//
//
//    public Ocorrencia(int id, String descricao, int fiscalizado, String dias, String observacao) {
//        super(id, descricao);
//
//        this.dias = dias;
//        this.observacao = observacao;
//        this.fiscalizado = Conversor.converter_Integer_Para_Boolean(fiscalizado);
//        this.tipo = TIPO_NOVA_OCORRENCIA;
//    }
//
//
//    public Ocorrencia(int id, String descricaoTipo, String descricaoDepartamento, String contrato, String dataEntrada, String marca, String estado) {
//        super(id, descricaoTipo);
//
//        this.descricaoDepartamento = descricaoDepartamento;
//        this.contrato = contrato;
//        this.dataEntrada = DatasUtil.converterData(dataEntrada, DatasUtil.FORMATO_DD_MM_YYYY);
//        this.marca = marca;
//        this.situacao = estado;
//        this.tipo = TIPO_OCORRENCIA;
//    }
//
//    public Ocorrencia(String data, String situacao, String observacao, String departamento) {
//        super(0, "");
//        this.data = DatasUtil.converterData(data, DatasUtil.FORMATO_DD_MM_YYYY);
//        this.situacao = situacao;
//        this.observacao = observacao;
//        this.descricaoDepartamento = departamento;
//
//        this.tipo = TIPO_HISTORICO_OCORRENCIA;
//    }
//
//
//    public Ocorrencia(int id, String descricao, String codigo, int opcao, int selecionado) {
//        super(id, descricao);
//        this.codigo = codigo;
//        this.selecionado = Conversor.converter_Integer_Para_Boolean(selecionado);
//
//        if(opcao == 1) {
//            this.tipo = TIPO_TIPIFICACAO;
//        }
//        else{
//            this.tipo = TIPO_REGISTO_NOVA_OCORRENCIA;
//        }
//    }
//
//
//    /**
//     * Metodo que permite obter o codigo
//     * @return  o codigo
//     */
//    public String obterCodigo(){
//        return codigo;
//    }
//
//
//    /**
//     * Metodo que permite obter os dias
//     * @return  os dias
//     */
//    public String obterDuracao(){
//        return dias;
//    }
//
//
//    /**
//     * Metodo que permite obter a observacao
//     * @return  a observacao
//     */
//    public String obterObservacao(){
//        return observacao;
//    }
//
//
//    /**
//     * Metodo que permite obter o restado da fiscalizacao
//     * @return true caso esteja fiscalizado ou false caso contrario
//     */
//    public boolean obterEstadoFiscalizacao(){
//        return fiscalizado;
//    }
//
//
//    /**
//     * Metodo que permite obter a fiscalizacao
//     * @return  a fiscalizacao
//     */
//    public String obterFiscalizacao(){
//
//        if(fiscalizado == false){
//            return "SintaxeIF.NAO_FISCALIZADO";
//        }
//        else{
//            return "SintaxeIF.FISCALIZADO";
//        }
//    }
//
//
//
//
//
//
//
//    /**
//     * Metodo que devolve o departamento da ocorrência
//     * @return o departamento da ocorrência
//     */
//    public String obterDepartamento(){
//        return descricaoDepartamento;
//    }
//
//
//
//
//    /**
//     * Metodo que devolve o numero do contrato da ocorrência
//     * @return o numero do contrato da ocorrência
//     */
//    public String obterContrato(){
//        return contrato;
//    }
//
//
//
//
//    /**
//     * Metodo que devolve a dados de entrada da ocorrência
//     * @return a dados de entrada da ocorrência
//     */
//    public String obterDataEntrada(){
//        return dataEntrada;
//    }
//
//
//
//    /**
//     * Metodo que devolve a marca da ocorrência
//     * @return a marca da entrada da ocorrência
//     */
//    public String obterMarca(){
//        return marca;
//    }
//
//
//
//    /**
//     * Metodo que devolve o estado da ocorrência
//     * @return o estado da ocorrência
//     */
//    public String obterSituacao(){
//        return situacao;
//    }
//
//
//    /**
//     * Metodo que devolve a dados do historico
//     * @return dados do historico
//     */
//    public String obterData(){
//        return data;
//    }
//
//    /**
//     * Metodo que permite obter o tipo de ocorrencia
//     * @return o tipo de ocorrencia
//     */
//    public int obterTipo(){
//        return tipo;
//    }
//
//    @Override
//    public void fixarSelecao(boolean selecao) {
//        this.selecionado = selecao;
//    }
//
//    @Override
//    public boolean obterSelecao() {
//        return selecionado;
//    }
}
