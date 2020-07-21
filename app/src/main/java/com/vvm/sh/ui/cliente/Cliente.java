package com.vvm.sh.ui.cliente;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.ui.agenda.modelos.Tarefa;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "clientes",
        foreignKeys = @ForeignKey(entity = Tarefa.class,
                                    parentColumns = "idTarefa",
                                    childColumns = "idTarefa",
                                    onDelete = CASCADE))
public class Cliente {

    @PrimaryKey
    @NonNull
    public int idTarefa;

    @NonNull
    @ColumnInfo(name = "nome")
    public String nome;

    @NonNull
    @ColumnInfo(name = "morada")
    public String morada;

    @NonNull
    @ColumnInfo(name = "localidade")
    public String localidade;

    @NonNull
    @ColumnInfo(name = "codigoPostal")
    public String codigoPostal;

    @NonNull
    @ColumnInfo(name = "cpAlf")
    public String cpAlf;

    @NonNull
    @ColumnInfo(name = "freguesia")
    public String freguesia;

    @NonNull
    @ColumnInfo(name = "nif")
    public String nif;

    @NonNull
    @ColumnInfo(name = "actividade")
    public String actividade;

    @NonNull
    @ColumnInfo(name = "actividade1")
    public String actividade1;

    @NonNull
    @ColumnInfo(name = "responsavel")
    public String responsavel;

    @NonNull
    @ColumnInfo(name = "telefone")
    public String telefone;

    @NonNull
    @ColumnInfo(name = "telemovel")
    public String telemovel;

    @NonNull
    @ColumnInfo(name = "email")
    public String email;

    @NonNull
    @ColumnInfo(name = "emailAutenticado")
    public boolean emailAutenticado;

    @NonNull
    @ColumnInfo(name = "cae")
    public String cae;

    @NonNull
    @ColumnInfo(name = "cae1")
    public String cae1;

    @NonNull
    @ColumnInfo(name = "moveLife")
    public boolean moveLife;

    @NonNull
    @ColumnInfo(name = "numeroAnalises")
    public String numeroAnalises;

    @NonNull
    @ColumnInfo(name = "segmento")
    public String segmento;



    @NonNull
    @ColumnInfo(name = "numeroCliente")
    public String numeroCliente;

    @NonNull
    @ColumnInfo(name = "servicoTP")
    public String servicoTP;

    @NonNull
    @ColumnInfo(name = "servico")
    public String servico;

    @NonNull
    @ColumnInfo(name = "minutos")
    public String minutos;

//    @NonNull
//    @ColumnInfo(name = "ultimaVisita")
//    public String ultimaVisita;

    @NonNull
    @ColumnInfo(name = "contrato")
    public String contrato;

//    @NonNull
//    @ColumnInfo(name = "dataContrato")
//    public String dataContrato;

    @NonNull
    @ColumnInfo(name = "novo")
    public String novo;



//    @NonNull
//    @ColumnInfo(name = "dataInsercao")
//    public String dataInsercao;

    @NonNull
    @ColumnInfo(name = "minutosRealizados")
    public String minutosRealizados;

    @NonNull
    @ColumnInfo(name = "periodo")
    public String periodo;


}
