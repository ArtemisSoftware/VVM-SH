package com.vvm.sh.baseDados.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.vvm.sh.baseDados.entidades.Tarefa;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

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

    @ColumnInfo(name = "morada")
    public String morada;

    @ColumnInfo(name = "localidade")
    public String localidade;

    @ColumnInfo(name = "codigoPostal")
    public String codigoPostal;

    @ColumnInfo(name = "cpAlf")
    public String cpAlf;

    @ColumnInfo(name = "freguesia")
    public String freguesia;

    @ColumnInfo(name = "nif")
    public String nif;

    @ColumnInfo(name = "actividade")
    public String actividade;

    @ColumnInfo(name = "actividade1")
    public String actividade1;

    @ColumnInfo(name = "responsavel")
    public String responsavel;

    @ColumnInfo(name = "telefone")
    public String telefone;

    @ColumnInfo(name = "telemovel")
    public String telemovel;

    @ColumnInfo(name = "email")
    public String email;

    @NonNull
    @ColumnInfo(name = "emailAutenticado", defaultValue = Identificadores.VALOR_0)
    public boolean emailAutenticado;

    @ColumnInfo(name = "cae")
    public String cae;

    @ColumnInfo(name = "cae1")
    public String cae1;

    @ColumnInfo(name = "segmento")
    public String segmento;


    //st

    @ColumnInfo(name = "trabalhadores")
    public String trabalhadores;

    @ColumnInfo(name = "anomaliaExtintores")
    public String anomaliaExtintores;


    //sa


    @NonNull
    @ColumnInfo(name = "moveLife", defaultValue = Identificadores.VALOR_0)
    public boolean moveLife;

    @ColumnInfo(name = "numeroAnalises")
    public String numeroAnalises;





    @ColumnInfo(name = "numeroCliente")
    public String numeroCliente;

    @ColumnInfo(name = "servicoTp")
    public String servicoTp;

    @ColumnInfo(name = "servico")
    public String servico;

    @ColumnInfo(name = "minutos")
    public String minutos;

    @ColumnInfo(name = "novo")
    public String novo;

    @ColumnInfo(name = "contrato")
    public String contrato;

    @ColumnInfo(name = "ultimaVisita")
    public String  ultimaVisita;

    @ColumnInfo(name = "dataContrato")
    public String dataContrato;

    @ColumnInfo(name = "dataInsercao")
    public String  dataInsercao;

    @ColumnInfo(name = "minutosRealizados")
    public String minutosRealizados;

    @ColumnInfo(name = "periodo")
    public String periodo;


    @ColumnInfo(name = "tipoPacote")
    public String tipoPacote;

    @ColumnInfo(name = "anuidadeContrato")
    public String anuidadeContrato;





    @ColumnInfo(name = "saldoCartaoVm")
    public String saldoCartaoVm;

    @ColumnInfo(name = "notas")
    public String notas;



    @Ignore
    public Cliente() {

    }

    public Cliente(int idTarefa, String nome, String morada, String localidade, String codigoPostal, String cpAlf, String freguesia,
                   String nif, String actividade, String actividade1, String responsavel, String telefone,
                   String telemovel, String email, boolean emailAutenticado, String cae, String cae1, String segmento,
                   String trabalhadores, String anomaliaExtintores, boolean moveLife, String numeroAnalises,
                   String numeroCliente, String servicoTp, String servico, String minutos, String novo, String contrato,
                   String ultimaVisita, String dataContrato, String dataInsercao, String minutosRealizados, String periodo,
                   String tipoPacote, String anuidadeContrato, String saldoCartaoVm, String notas) {
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
        this.segmento = segmento;
        this.trabalhadores = trabalhadores;
        this.anomaliaExtintores = anomaliaExtintores;
        this.moveLife = moveLife;
        this.numeroAnalises = numeroAnalises;
        this.numeroCliente = numeroCliente;
        this.servicoTp = servicoTp;
        this.servico = servico;
        this.minutos = minutos;
        this.novo = novo;
        this.contrato = contrato;
        this.ultimaVisita = ultimaVisita;
        this.dataContrato = dataContrato;
        this.dataInsercao = dataInsercao;
        this.minutosRealizados = minutosRealizados;
        this.periodo = periodo;
        this.tipoPacote = tipoPacote;
        this.anuidadeContrato = anuidadeContrato;
        this.saldoCartaoVm = saldoCartaoVm;
        this.notas = notas;
    }
}
