package com.vvm.sh.baseDados;

import android.database.SQLException;
import android.util.Log;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

public class Migracao {

    public static final Migration[] obterMigracoes(){

        Migration migrations [] =  new Migration []{
                MIGRACAO_1_2, MIGRACAO_2_3, MIGRACAO_3_4, MIGRACAO_4_5, MIGRACAO_5_6, MIGRACAO_6_7, MIGRACAO_7_8, MIGRACAO_8_9, MIGRACAO_9_10, MIGRACAO_10_11,
                MIGRACAO_11_12, MIGRACAO_12_13, MIGRACAO_13_14, MIGRACAO_14_15, MIGRACAO_15_16

        };

        return migrations;
    }


    public static final Migration MIGRACAO_15_16 = new Migration(15, 16) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("CREATE TABLE IF NOT EXISTS 'parqueExtintores' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idServico' TEXT NOT NULL, "
                        + "'contrato' TEXT NOT NULL, "
                        + "'idExtintor' TEXT NOT NULL, "
                        + "'quantidade' INTEGER NOT NULL, "
                        + "'dataValidade' INTEGER NOT NULL, "
                        + "'idMorada' TEXT NOT NULL, "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_parqueExtintores_idTarefa ON tarefas (idTarefa)");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'parqueExtintoresResultado' ("
                        + "'id' INTEGER NOT NULL , "
                        + "'valido' INTEGER NOT NULL, "
                        + "'dataValidade' INTEGER NOT NULL, "
                        + "PRIMARY KEY (id), "
                        + "FOREIGN KEY (id) REFERENCES parqueExtintores (id)  ON DELETE CASCADE) ");




                database.execSQL("CREATE TABLE IF NOT EXISTS 'tiposExtintor' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' TEXT NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "PRIMARY KEY (idTarefa, id), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_tiposExtintor_idTarefa ON tarefas (idTarefa)");

/*
                database.execSQL("CREATE TABLE IF NOT EXISTS 'moradas' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' TEXT NOT NULL , "
                        + "'tipo' INTEGER NOT NULL, "
                        + "'endereco' TEXT NOT NULL, "
                        + "'cp4' TEXT NOT NULL, "
                        + "'localidade' TEXT NOT NULL, "
                        + "PRIMARY KEY (idTarefa, id, tipo), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_moradas_idTarefa ON tarefas (idTarefa)");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'colaboradores' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idColaborador' TEXT NOT NULL, "
                        + "'nome' TEXT NOT NULL, "
                        + "'estado' TEXT NOT NULL, "
                        + "'idMorada' TEXT NOT NULL, "
                        + "'sexo' TEXT NOT NULL, "
                        + "'dataNascimento' INTEGER NOT NULL, "
                        + "'nacionalidade' TEXT, "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_colaboradores_idTarefa ON tarefas (idTarefa)");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'colaboradoresResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' INTEGER NOT NULL, "
                        + "'nome' TEXT , "
                        + "'estado' TEXT , "
                        + "'idMorada' TEXT , "
                        + "'sexo' TEXT , "
                        + "'dataNascimento' INTEGER , "
                        + "'nacionalidade' TEXT, "
                        + "'dataAdmissao' INTEGER , "
                        + "'dataAdmissaoFuncao' INTEGER , "
                        + "'idCategoriaProfissional' INTEGER , "
                        + "'profissao' TEXT , "
                        + "'posto' TEXT , "
                        + "'origem' INTEGER NOT NULL, "
                        + "PRIMARY KEY (id), "
                        + "FOREIGN KEY (id) REFERENCES colaboradores (id)  ON DELETE CASCADE) ");




                database.execSQL("CREATE TABLE IF NOT EXISTS 'medidasResultado' ("
                        + "'id' INTEGER NOT NULL, "
                        + "'idMedida' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL, "
                        + "PRIMARY KEY (id, idMedida, origem)) ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'categoriasProfissionaisResultado' ("
                        + "'id' INTEGER NOT NULL, "
                        + "'idCategoriaProfissional' INTEGER NOT NULL, "
                        + "'idRegisto' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL, "
                        + "'homens' REAL , "
                        + "'mulheres' REAL , "
                        + "PRIMARY KEY (id)) ");





                database.execSQL("CREATE TABLE IF NOT EXISTS 'relatorioAmbientalResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'tipo' INTEGER NOT NULL, "



                        //falta
                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_atividadesPendentes_idAtividade ON atividadesPendentes (id)");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'avaliacoesAmbientaisResultado' ("
                        + "'idRelatorio' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idArea' INTEGER NOT NULL, "
                        + "'anexoArea' TEXT, "

                        + "'nome' TEXT, "
                        + "'sexo' INTEGER , "
                        + "'tipoIluminacao' INTEGER, "
                        + "'emedioLx' INTEGER, "
                        + "'eLxArea' INTEGER , "
                        + "'idElx' INTEGER, "
                        + "'eLx' TEXT, "

                        + "'temperatura' REAL , "
                        + "'humidadeRelativa' REAL , "
                        + "'homens' INTEGER , "
                        + "'mulheres' INTEGER  "
                        + ") ");

*/


            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_14_15: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };









    public static final Migration MIGRACAO_14_15 = new Migration(14, 15) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("CREATE TABLE IF NOT EXISTS 'sinistralidadesResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'acidentesComBaixa' INTEGER NOT NULL, "
                        + "'diasUteisPerdidos' REAL NOT NULL, "
                        + "'totalTrabalhadores' INTEGER NOT NULL, "
                        + "'horasAnoTrabalhadores' REAL NOT NULL, "
                        + "'faltas' REAL NOT NULL, "
                        + "PRIMARY KEY (idTarefa), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");


            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_14_15: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




    public static final Migration MIGRACAO_13_14 = new Migration(13, 14) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL(" ALTER TABLE atividadesPendentes RENAME TO tmp");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'atividadesPendentes' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'dataProgramada' INTEGER NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'servId' TEXT NOT NULL , "
                        + "'idRelatorio' INTEGER NOT NULL , "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE)  ");


                database.execSQL(" INSERT INTO atividadesPendentes(idTarefa, id, dataProgramada, descricao, servId, idRelatorio) "
                        + "SELECT idTarefa, id, dataProgramada, descricao, servId, formacao as idRelatorio FROM tmp");

                database.execSQL("DROP TABLE tmp");

                database.execSQL("DROP INDEX IF EXISTS index_atividadesPendentes_idTarefa");
                database.execSQL("CREATE INDEX index_atividadesPendentes_idTarefa ON atividadesPendentes (idTarefa)");


            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_13_14: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };





    public static final Migration MIGRACAO_12_13 = new Migration(12, 13) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("ALTER TABLE utilizadores ADD COLUMN api INTEGER NOT NULL DEFAULT " + Identificadores.App.APP_SA + "");



                database.execSQL(" ALTER TABLE tipos RENAME TO tmp");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'tipos' ("
                        + "'id' INTEGER NOT NULL, "
                        + "'tipo' TEXT NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'codigo' TEXT NOT NULL, "
                        + "'idPai' TEXT NOT NULL, "
                        + "'ativo' INTEGER NOT NULL, "
                        + "'detalhe' TEXT NOT NULL, "
                        + "'api' INTEGER NOT NULL, "
                        + "PRIMARY KEY (id, tipo, api), "
                        + "FOREIGN KEY (tipo) REFERENCES atualizacoes (descricao)  ON DELETE CASCADE) ");

                //database.execSQL("CREATE INDEX index_tipos_tipo ON tipos (tipo)");

                database.execSQL(" INSERT INTO tipos(id, tipo, descricao, codigo, idPai, ativo, detalhe, api) "
                        + "SELECT id, tipo, descricao, codigo, idPai, ativo, cast(detalhe as text) as detalhe , " + Identificadores.App.APP_SA + " as api FROM tmp");

                database.execSQL("DROP TABLE tmp");

                database.execSQL("DROP INDEX IF EXISTS index_tipos_tipo");
                database.execSQL("CREATE INDEX index_tipos_tipo ON tipos (tipo)");









                database.execSQL(" ALTER TABLE tarefas RENAME TO tmp");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'tarefas' ("
                        + "'idTarefa' INTEGER PRIMARY KEY NOT NULL, "
                        + "'idUtilizador' TEXT NOT NULL, "
                        + "'ordem' TEXT NOT NULL, "
                        + "'prefixoCt' TEXT NOT NULL, "
                        + "'data' INTEGER NOT NULL, "
                        + "'api' INTEGER NOT NULL) ");

                database.execSQL(" INSERT INTO tarefas(idTarefa, idUtilizador, ordem, prefixoCt, data, api) "
                        + "SELECT idTarefa, idUtilizador, ordem, prefixoCt, data , " + Identificadores.App.APP_SA + " as api FROM tmp");

                database.execSQL("DROP TABLE tmp");


            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_12_13: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };








    public static final Migration MIGRACAO_11_12 = new Migration(11, 12) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("DROP TABLE IF EXISTS imagensResultado");
                database.execSQL("DROP INDEX IF EXISTS index_imagensResultado_idTarefa");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'imagensResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'idImagem' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'id' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL, "
                        + "'imagem' BLOB NOT NULL, "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_imagensResultado_idTarefa ON imagensResultado (idTarefa)");


            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_11_12: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




    public static final Migration MIGRACAO_10_11 = new Migration(10, 11) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("ALTER TABLE tarefas ADD COLUMN app INTEGER NOT NULL DEFAULT 1 ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'imagensResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL, "
                        + "'imagem' BLOB NOT NULL, "
                        + "PRIMARY KEY (id, origem), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_imagensResultado_idTarefa ON imagensResultado (idTarefa)");

                database.execSQL("ALTER TABLE clientes ADD COLUMN saldoCartaoVm TEXT NOT NULL DEFAULT ''");
                database.execSQL("ALTER TABLE clientes ADD COLUMN notas TEXT  NOT NULL DEFAULT ''");

            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_10_11: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




    public static final Migration MIGRACAO_9_10 = new Migration(9, 10) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("CREATE TABLE IF NOT EXISTS 'ocorrenciaResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' INTEGER NOT NULL, "
                        + "'observacao' TEXT , "
                        + "'fiscalizado' INTEGER NOT NULL, "
                        + "'dias' TEXT , "
                        + "PRIMARY KEY (idTarefa, id), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_ocorrenciaResultado_idTarefa ON ocorrenciaResultado (idTarefa)");

            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_2_3: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };







    public static final Migration MIGRACAO_8_9 = new Migration(8, 9) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("CREATE TABLE IF NOT EXISTS 'crossSellingResultado' ("
                        + "'id' INTEGER NOT NULL , "
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'idAreaRecomendacao' INTEGER NOT NULL , "
                        + "'idDimensao' INTEGER NOT NULL DEFAULT " + Identificadores.SEM_VALOR + " , "
                        + "'idTipo' INTEGER NOT NULL DEFAULT " + Identificadores.SEM_VALOR + " , "
                        + "PRIMARY KEY (id, idTarefa), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_crossSellingResultado_idTarefa ON crossSellingResultado (idTarefa)");


                database.execSQL("DROP TABLE IF EXISTS atividadesPendentesResultado");
                database.execSQL("CREATE TABLE IF NOT EXISTS 'atividadesPendentesResultado' ("
                        + "'id' INTEGER NOT NULL, "
                        + "'idEstado' INTEGER NOT NULL , "
                        + "'tempoExecucao' TEXT  , "
                        + "'dataExecucao' INTEGER  , "
                        + "'idAnomalia' INTEGER NOT NULL DEFAULT " + Identificadores.SEM_VALOR + " , "
                        + "'observacao' TEXT  , "
                        + "PRIMARY KEY (id), "
                        + "FOREIGN KEY (id) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("DROP INDEX IF EXISTS index_atividadesPendentesResultado_id");
                database.execSQL("CREATE INDEX index_atividadesPendentesResultado_id ON atividadesPendentesResultado (id)");
            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_2_3: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };






    public static final Migration MIGRACAO_7_8 = new Migration(7, 8) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("CREATE TABLE IF NOT EXISTS 'anomaliasResultado' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'id' INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idAnomalia' INTEGER NOT NULL, "
                        + "'observacao' TEXT , "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_anomaliasResultado_idTarefa ON anomaliasResultado (idTarefa)");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'acoesFormacaoResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'idDesignacao' INTEGER NOT NULL, "
                        + "'local' TEXT NOT NULL , "
                        + "'data' INTEGER NOT NULL , "
                        + "'inicio' INTEGER NOT NULL , "
                        + "'termino' INTEGER NOT NULL , "
                        + "PRIMARY KEY (idAtividade), "
                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_acoesFormacaoResultado_idAtividade ON acoesFormacaoResultado (idAtividade)");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'formandosResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'id' INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'nome' TEXT NOT NULL , "
                        + "'biCartaoCidadao' TEXT NOT NULL , "
                        + "'sexo' TEXT NOT NULL , "
                        + "'niss' TEXT NOT NULL , "
                        + "'dataNascimento' INTEGER NOT NULL , "
                        + "'naturalidade' TEXT NOT NULL , "
                        + "'nacionalidade' TEXT NOT NULL , "
                        + "'selecionado' INTEGER NOT NULL DEFAULT " + Sintaxe.Codigos.NAO_SELECIONADO + " , "
                        + "'origem' INTEGER NOT NULL DEFAULT " + Identificadores.Origens.ORIGEM_BD + " , "
                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_formandosResultado_idAtividade ON formandosResultado (idAtividade)");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'atividadesPendentesResultado' ("
                        + "'id' INTEGER NOT NULL, "
                        + "'idEstado' INTEGER NOT NULL , "
                        + "'tempoExecucao' TEXT  , "
                        + "'dataExecucao' INTEGER  , "
                        + "'idAnomalia' INTEGER , "
                        + "'observacao' TEXT  , "
                        + "PRIMARY KEY (id), "
                        + "FOREIGN KEY (id) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_atividadesPendentesResultado_id ON atividadesPendentesResultado (id)");
            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_2_3: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };






    public static final Migration MIGRACAO_6_7 = new Migration(6, 7) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("CREATE TABLE IF NOT EXISTS 'resultados' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'id' INTEGER NOT NULL, "
                        + "'sincronizado' INTEGER NOT NULL, "
                        + "PRIMARY KEY (idTarefa, id), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'emailsResultado' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'endereco' TEXT , "
                        + "'autorizacao' TEXT NOT NULL, "
                        + "'idAutorizacao' INTEGER NOT NULL, "
                        + "PRIMARY KEY (idTarefa), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_2_3: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };





    public static final Migration MIGRACAO_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL(" ALTER TABLE tipos RENAME TO tmp");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'tipos' ("
                        + "'id' INTEGER NOT NULL, "
                        + "'tipo' TEXT NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'codigo' TEXT NOT NULL, "
                        + "'idPai' TEXT NOT NULL, "
                        + "'ativo' INTEGER NOT NULL, "
                        + "'detalhe' TEXT NOT NULL, "
                        + "PRIMARY KEY (id, tipo), "
                        + "FOREIGN KEY (tipo) REFERENCES atualizacoes (descricao)  ON DELETE CASCADE) ");

                //database.execSQL("CREATE INDEX index_tipos_tipo ON tipos (tipo)");

                database.execSQL(" INSERT INTO tipos(id, tipo, descricao, codigo, idPai, ativo, detalhe) "
                        + "SELECT id, tipo, descricao, codigo, idPai, ativo, cast(detalhe as text) as detalhe FROM tmp");

                database.execSQL("DROP TABLE tmp");

                database.execSQL("DROP INDEX IF EXISTS index_tipos_tipo");
                database.execSQL("CREATE INDEX index_tipos_tipo ON tipos (tipo)");
                //Timber.d("MIGRACAO_2_3: success");
            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_2_3: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




    public static final Migration MIGRACAO_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'atividadesPendentes' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'dataProgramada' INTEGER NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'servId' TEXT NOT NULL , "
                        + "'formacao' INTEGER NOT NULL , "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE)  ");

                database.execSQL("CREATE INDEX index_atividadesPendentes_idTarefa ON atividadesPendentes (idTarefa)");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'ocorrencias' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idOcorrencia' TEXT NOT NULL, "
                        + "'tipo' TEXT NOT NULL, "
                        + "'descricaoTipo' TEXT NOT NULL, "
                        + "'descricaoDepartamento' TEXT NOT NULL, "
                        + "'contrato' TEXT NOT NULL,"
                        + "'dataEntrada' INTEGER NOT NULL,"
                        + "'dataResolucao' INTEGER ,"
                        + "'descricaoOcorrencia' TEXT NOT NULL,"
                        + "'marca' TEXT NOT NULL,"
                        + "'estado' TEXT NOT NULL,"
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE)  ");

                database.execSQL("CREATE INDEX index_ocorrencias_idTarefa ON ocorrencias (idTarefa)");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'ocorrenciasHistorico' ("
                        + "'idOcorrencia' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'data' INTEGER NOT NULL, "
                        + "'estado' TEXT NOT NULL, "
                        + "'observacao' TEXT , "
                        + "'departamento' TEXT NOT NULL, "
                        + "FOREIGN KEY (idOcorrencia) REFERENCES ocorrencias (id)  ON DELETE CASCADE)  ");

                database.execSQL("CREATE INDEX index_ocorrenciasHistorico_idOcorrencia ON ocorrenciasHistorico (idOcorrencia)");


                //Timber.d("MIGRACAO_3_4: success");
            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_3_4: " + e.getMessage());
                //Timber.e("erro MIGRACAO_3_4: " + e.getMessage());
            }
        }
    };


    public static final Migration MIGRACAO_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'anomalias' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'data' INTEGER NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'observacao' TEXT , "
                        + "'contacto' TEXT , "
                        + "'tipo' TEXT NOT NULL,"
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE)  ");

                database.execSQL("CREATE INDEX index_anomalias_idTarefa ON anomalias (idTarefa)");

                //Timber.d("MIGRACAO_3_4: success");
            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_3_4: " + e.getMessage());
                //Timber.e("erro MIGRACAO_3_4: " + e.getMessage());
            }
        }
    };



    public static final Migration MIGRACAO_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'tarefas' ("
                        + "'idTarefa' INTEGER PRIMARY KEY NOT NULL, "
                        + "'idUtilizador' TEXT NOT NULL, "
                        + "'ordem' TEXT NOT NULL, "
                        + "'prefixoCt' TEXT NOT NULL, "
                        + "'data' INTEGER NOT NULL) ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'atividadeExecutadas' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'ordem' TEXT NOT NULL, "
                        + "'idServico' TEXT NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'dataProgramada' INTEGER NOT NULL, "
                        + "'dataExecucao' INTEGER NOT NULL, "
                        + "PRIMARY KEY (idTarefa, idServico), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");


                database.execSQL("CREATE INDEX index_atividadeExecutadas_idTarefa ON atividadeExecutadas (idTarefa)");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'clientes' ("
                        + "'idTarefa' INTEGER PRIMARY KEY NOT NULL, "
                        + "'nome' TEXT NOT NULL, "
                        + "'morada' TEXT NOT NULL, "
                        + "'localidade' TEXT NOT NULL, "
                        + "'codigoPostal' TEXT NOT NULL, "
                        + "'cpAlf' TEXT NOT NULL, "
                        + "'freguesia' TEXT NOT NULL, "
                        + "'nif' TEXT NOT NULL, "
                        + "'actividade' TEXT NOT NULL, "
                        + "'actividade1' TEXT, "
                        + "'responsavel' TEXT NOT NULL, "
                        + "'telefone' TEXT NOT NULL, "
                        + "'telemovel' TEXT NOT NULL, "
                        + "'email' TEXT NOT NULL, "
                        + "'emailAutenticado' INTEGER NOT NULL, "
                        + "'cae' TEXT NOT NULL, "
                        + "'cae1' TEXT NOT NULL, "
                        + "'moveLife' INTEGER NOT NULL, "
                        + "'numeroAnalises' TEXT NOT NULL, "
                        + "'segmento' TEXT NOT NULL, "
                        + "'numeroCliente' TEXT NOT NULL, "
                        + "'servicoTp' TEXT NOT NULL, "
                        + "'servico' TEXT NOT NULL, "
                        + "'minutos' TEXT NOT NULL, "
                        + "'ultimaVisita' INTEGER NOT NULL, " //TODO: este campo pode vir vazio
                        + "'contrato' TEXT NOT NULL, "
                        + "'dataContrato' INTEGER NOT NULL, "
                        + "'novo' TEXT NOT NULL, "
                        + "'dataInsercao' INTEGER NOT NULL, "
                        + "'minutosRealizados' TEXT NOT NULL, "
                        + "'periodo' TEXT NOT NULL, "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa) ON DELETE CASCADE) ");



                //Timber.d("MIGRACAO_2_3: success");
            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_2_3: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




    public static final Migration MIGRACAO_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'utilizadores' ("
                        + "'id' TEXT PRIMARY KEY NOT NULL, "
                        + "'area' TEXT NOT NULL, "
                        + "'nome' TEXT NOT NULL, "
                        + "'email' TEXT NOT NULL) ");

                //Timber.d("MIGRACAO_1_2: success");
            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_1_2: " + e.getMessage());
                //Timber.e("erro MIGRACAO_1_2: " + e.getMessage());
            }
        }
    };

}
