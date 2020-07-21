package com.vvm.sh.ui.cliente;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.ui.agenda.modelos.Tarefa;

import java.util.Date;

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
    @ColumnInfo(name = "servicoTp")
    public String servicoTp;

    @NonNull
    @ColumnInfo(name = "servico")
    public String servico;

    @NonNull
    @ColumnInfo(name = "minutos")
    public String minutos;

    @NonNull
    @ColumnInfo(name = "ultimaVisita")
    public Date  ultimaVisita;

    @NonNull
    @ColumnInfo(name = "contrato")
    public String contrato;

    @NonNull
    @ColumnInfo(name = "dataContrato")
    public Date dataContrato;

    @NonNull
    @ColumnInfo(name = "novo")
    public String novo;



    @NonNull
    @ColumnInfo(name = "dataInsercao")
    public Date  dataInsercao;

    @NonNull
    @ColumnInfo(name = "minutosRealizados")
    public String minutosRealizados;

    @NonNull
    @ColumnInfo(name = "periodo")
    public String periodo;

    @Ignore
    public Cliente() {

    }


    public Cliente(int idTarefa, @NonNull String nome, @NonNull String morada, @NonNull String localidade,
                   @NonNull String codigoPostal, @NonNull String cpAlf, @NonNull String freguesia,
                   @NonNull String nif, @NonNull String actividade, String actividade1, @NonNull String responsavel,
                   @NonNull String telefone, @NonNull String telemovel, @NonNull String email,
                   boolean emailAutenticado, @NonNull String cae, @NonNull String cae1, boolean moveLife,
                   @NonNull String numeroAnalises, @NonNull String segmento, @NonNull String numeroCliente,
                   @NonNull String servicoTp, @NonNull String servico, @NonNull String minutos, @NonNull Date ultimaVisita,
                   @NonNull String contrato, @NonNull Date dataContrato, @NonNull String novo, @NonNull Date dataInsercao,
                   @NonNull String minutosRealizados, @NonNull String periodo) {
        this.idTarefa = idTarefa;
        this.nome = nome;
        this.morada = morada;
        this.localidade = localidade;
        this.codigoPostal = codigoPostal;
        this.cpAlf = cpAlf;
        this.freguesia = freguesia;
        this.nif = nif;
        this.actividade = actividade;
        this.actividade1 = actividade1;
        this.responsavel = responsavel;
        this.telefone = telefone;
        this.telemovel = telemovel;
        this.email = email;
        this.emailAutenticado = emailAutenticado;
        this.cae = cae;
        this.cae1 = cae1;
        this.moveLife = moveLife;
        this.numeroAnalises = numeroAnalises;
        this.segmento = segmento;
        this.numeroCliente = numeroCliente;
        this.servicoTp = servicoTp;
        this.servico = servico;
        this.minutos = minutos;
        this.ultimaVisita = ultimaVisita;
        this.contrato = contrato;
        this.dataContrato = dataContrato;
        this.novo = novo;
        this.dataInsercao = dataInsercao;
        this.minutosRealizados = minutosRealizados;
        this.periodo = periodo;
    }
}
