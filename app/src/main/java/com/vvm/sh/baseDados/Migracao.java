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
                MIGRACAO_1_2, MIGRACAO_2_3, MIGRACAO_3_4, MIGRACAO_4_5, MIGRACAO_5_6, MIGRACAO_6_7, MIGRACAO_7_8, MIGRACAO_8_9, MIGRACAO_9_10,
                MIGRACAO_10_11, MIGRACAO_11_12, MIGRACAO_12_13, MIGRACAO_13_14, MIGRACAO_14_15, MIGRACAO_15_16, MIGRACAO_16_17, MIGRACAO_17_18, MIGRACAO_18_19, MIGRACAO_19_20,
                MIGRACAO_20_21, MIGRACAO_21_22, MIGRACAO_22_23, MIGRACAO_23_24, MIGRACAO_24_25, MIGRACAO_25_26, MIGRACAO_26_27, MIGRACAO_27_28, MIGRACAO_28_29, MIGRACAO_29_30,
                MIGRACAO_30_31, MIGRACAO_31_32, MIGRACAO_32_33, MIGRACAO_33_34, MIGRACAO_34_35, MIGRACAO_35_36, MIGRACAO_36_37, MIGRACAO_37_38
        };

        return migrations;
    }

    public static final Migration MIGRACAO_37_38 = new Migration(37, 38) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("DROP TABLE IF EXISTS 'atualizacoes' ");
                database.execSQL("DROP INDEX IF EXISTS index_atualizacoes_descricao ");
                database.execSQL("DROP INDEX IF EXISTS index_atualizacoes_api ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'atualizacoes' ("
                        + "'descricao' TEXT NOT NULL, "
                        + "'api' INTEGER NOT NULL, "
                        + "'tipo' INTEGER NOT NULL, "
                        + "'seloTemporal' TEXT, "
                        + "PRIMARY KEY (descricao, api)) ");

                database.execSQL("CREATE INDEX index_atualizacoes_descricao ON atualizacoes (descricao)");
                database.execSQL("CREATE INDEX index_atualizacoes_api ON atualizacoes (api)");



                database.execSQL("DROP TABLE IF EXISTS 'tipos' ");
                database.execSQL("DROP INDEX IF EXISTS index_tipos_tipo ");

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
                        + "FOREIGN KEY (tipo, api) REFERENCES atualizacoes (descricao, api)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_tipos_tipo ON tipos (tipo)");

            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_37_38: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };

                public static final Migration MIGRACAO_36_37 = new Migration(36, 37) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("DROP TABLE IF EXISTS 'atualizacoes' ");
                database.execSQL("DROP INDEX IF EXISTS index_atualizacoes_descricao ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'atualizacoes' ("
                        + "'descricao' TEXT NOT NULL, "
                        + "'api' INTEGER NOT NULL, "
                        + "'tipo' INTEGER NOT NULL, "
                        + "'seloTemporal' TEXT, "
                        + "PRIMARY KEY (descricao, api)) ");

                database.execSQL("CREATE UNIQUE INDEX index_atualizacoes_descricao ON atualizacoes (descricao)");
                database.execSQL("CREATE UNIQUE INDEX index_atualizacoes_api ON atualizacoes (api)");



                database.execSQL("DROP TABLE IF EXISTS 'tipos' ");
                database.execSQL("DROP INDEX IF EXISTS index_tipos_tipo ");

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
                        + "FOREIGN KEY (tipo) REFERENCES atualizacoes (descricao)  ON DELETE CASCADE, "
                        + "FOREIGN KEY (api) REFERENCES atualizacoes (api)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_tipos_tipo ON tipos (tipo)");

                //(cast(strftime('%s','now') as int))
            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_36_37: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };


    public static final Migration MIGRACAO_35_36 = new Migration(35, 36) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("DROP TABLE IF EXISTS 'atualizacoes' ");
                database.execSQL("DROP INDEX IF EXISTS index_atualizacoes_descricao ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'atualizacoes' ("
                        + "'descricao' TEXT NOT NULL, "
                        + "'api' INTEGER NOT NULL, "
                        + "'tipo' INTEGER NOT NULL, "
                        + "'seloTemporal' TEXT, "
                        + "PRIMARY KEY (descricao, api)) ");

                database.execSQL("CREATE UNIQUE INDEX index_atualizacoes_descricao ON atualizacoes (descricao)");
                database.execSQL("CREATE INDEX index_atualizacoes_api ON atualizacoes (api)");

                //(cast(strftime('%s','now') as int))
            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_35_36: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




    public static final Migration MIGRACAO_34_35 = new Migration(34, 35) {
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
                        + "'capaRelatorio' INTEGER NOT NULL DEFAULT   " + Sintaxe.Codigos.NAO_SELECIONADO + " ,  "
                        + "'criado' INTEGER NOT NULL DEFAULT  CURRENT_TIMESTAMP, "
                        + "'imagem' BLOB NOT NULL, "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_imagensResultado_idTarefa ON imagensResultado (idTarefa)");

                //(cast(strftime('%s','now') as int))
            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_34_35: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




    public static final Migration MIGRACAO_33_34 = new Migration(33, 34) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("ALTER TABLE registoVisitaResultado ADD COLUMN homologado INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_INT_0 + "");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'informacaoSstResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'responsavel' TEXT, "
                        + "'sincronizacao' INTEGER NOT NULL DEFAULT   " + Sintaxe.Codigos.NAO_SELECIONADO + " ,  "
                        + "PRIMARY KEY (idTarefa), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'obrigacaoLegalResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' INTEGER NOT NULL, "
                        + "PRIMARY KEY (idTarefa, id), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_obrigacaoLegalResultado_idTarefa ON obrigacaoLegalResultado (idTarefa)");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'certificadoTratamentoResultado' ("
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'idPraga' INTEGER NOT NULL, "
                        + "'idVisita' INTEGER NOT NULL, "
                        + "'idProdutoAplicado' INTEGER NOT NULL , "
                        + "'avaliacaoCondicoesHigiene' INTEGER NOT NULL , "
                        + "'avaliacaoManutencaoInstalacoes' INTEGER NOT NULL , "
                        + "'avaliacaoCondicoesArmazenamento' INTEGER NOT NULL , "
                        + "'observacaoVestigiosPragas' INTEGER  NOT NULL DEFAULT   " + Sintaxe.Codigos.NAO_SELECIONADO + " ,  "
                        + "'observacaoProdutosEmGel' INTEGER  NOT NULL DEFAULT   " + Sintaxe.Codigos.NAO_SELECIONADO + " ,  "
                        + "'observacao' TEXT , "
                        + "'sincronizacao' INTEGER NOT NULL DEFAULT   " + Sintaxe.Codigos.NAO_SELECIONADO + " ,  "

                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_certificadoTratamentoResultado_idAtividade ON certificadoTratamentoResultado (idAtividade)");





            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_33_34: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };


    public static final Migration MIGRACAO_32_33 = new Migration(32, 33) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("ALTER TABLE emailsResultado ADD COLUMN autorizado INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_INT_0 + "");
                database.execSQL("ALTER TABLE atividadesPendentes ADD COLUMN anuidade TEXT ");

            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_32_33: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };

    public static final Migration MIGRACAO_31_32 = new Migration(31, 32) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("CREATE TABLE IF NOT EXISTS 'medidasAveriguacao' ("
                            + "'id' INTEGER NOT NULL, "
                            + "'idMedida' INTEGER NOT NULL, "
                            + "'origem' INTEGER NOT NULL, "
                            + "PRIMARY KEY (id, idMedida, origem), "
                            + "FOREIGN KEY (id) REFERENCES relatorioAveriguacao (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_medidasAveriguacao_id ON medidasAveriguacao (id)");

            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_31_32: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };


    public static final Migration MIGRACAO_30_31 = new Migration(30, 31) {
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
                        + "'capaRelatorio' INTEGER NOT NULL DEFAULT   " + Sintaxe.Codigos.NAO_SELECIONADO + " ,  "
                        + "'imagem' BLOB NOT NULL, "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_imagensResultado_idTarefa ON imagensResultado (idTarefa)");

            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_29_30: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };


    public static final Migration MIGRACAO_29_30 = new Migration(29, 30) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("DROP TABLE IF EXISTS 'registoVisitaResultado' ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'registoVisitaResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'recebidoPor' TEXT NOT NULL, "
                        + "'funcao' TEXT NOT NULL, "
                        + "'observacao' TEXT, "
                        + "'sincronizacao' INTEGER NOT NULL DEFAULT   " + Sintaxe.Codigos.NAO_SELECIONADO + " ,  "
                        + "PRIMARY KEY (idTarefa), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_29_30: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




    public static final Migration MIGRACAO_28_29 = new Migration(28, 29) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("DROP TABLE IF EXISTS 'levantamentosRiscoResultado' ");
                database.execSQL("DROP INDEX IF EXISTS index_levantamentosRiscoResultado_idAtividade ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'levantamentosRiscoResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "

                        + "'tarefa' TEXT NOT NULL, "
                        + "'perigo' TEXT NOT NULL, "
                        + "'idModelo' INTEGER NOT NULL, "
                        + "'idTipoLevantamento' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL, "

                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_levantamentosRiscoResultado_idAtividade ON levantamentosRiscoResultado (idAtividade)");







                database.execSQL("DROP TABLE IF EXISTS 'propostaPlanoAcaoResultado' ");
                database.execSQL("DROP INDEX IF EXISTS index_propostaPlanoAcaoResultado_idAtividade ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'propostaPlanoAcaoResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY NOT NULL, "
                        + "'origem' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'idQuestao' INTEGER  NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'idMedida' INTEGER  NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'idNi' INTEGER  NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'idPrazo' INTEGER  NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'selecionado' INTEGER NOT NULL DEFAULT   " + Sintaxe.Codigos.NAO_SELECIONADO + " ,  "
                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE)  ");


                database.execSQL("CREATE INDEX index_propostaPlanoAcaoResultado_idAtividade ON propostaPlanoAcaoResultado (idAtividade)");



            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_26_27: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };





    public static final Migration MIGRACAO_27_28 = new Migration(27, 28) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("DROP TABLE IF EXISTS 'riscosResultado' ");
                database.execSQL("DROP INDEX IF EXISTS index_riscosResultado_idLevantamento ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'riscosResultado' ("
                        + "'idLevantamento' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY NOT NULL, "
                        + "'idRisco' INTEGER NOT NULL, "
                        + "'idRiscoEspecifico' INTEGER NOT NULL, "
                        + "'consequencias' TEXT NOT NULL, "
                        + "'nd' TEXT NOT NULL, "
                        + "'ne' TEXT NOT NULL, "
                        + "'nc' TEXT NOT NULL, "
                        + "'ni' TEXT NOT NULL, "
                        + "'idTipoRisco' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL DEFAULT " + Identificadores.Origens.ORIGEM_BD + " ,  "
                        + "FOREIGN KEY (idLevantamento) REFERENCES levantamentosRiscoResultado (id)  ON DELETE CASCADE)  ");

                database.execSQL("CREATE INDEX index_riscosResultado_idLevantamento ON riscosResultado (idLevantamento)");



            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_26_27: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };



    public static final Migration MIGRACAO_26_27 = new Migration(26, 27) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("DROP TABLE IF EXISTS 'atualizacoes' ");
                database.execSQL("DROP INDEX IF EXISTS index_atualizacoes_descricao ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'atualizacoes' ("
                        + "'descricao' TEXT NOT NULL, "
                        + "'tipo' INTEGER NOT NULL, "
                        + "'seloTemporal' TEXT, "
                        + "PRIMARY KEY (descricao)) ");

                database.execSQL("CREATE INDEX index_atualizacoes_descricao ON atualizacoes (descricao)");



                database.execSQL("DROP TABLE IF EXISTS 'tipos' ");
                database.execSQL("DROP INDEX IF EXISTS index_tipos_tipo ");



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

                database.execSQL("CREATE INDEX index_tipos_tipo ON tipos (tipo)");



            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_26_27: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };



    public static final Migration MIGRACAO_25_26 = new Migration(25, 26) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("CREATE TABLE IF NOT EXISTS 'relatorioAveriguacao' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'tipo' INTEGER NOT NULL, "
                        + "'data' INTEGER NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'idChecklist' INTEGER NOT NULL, "
                        + "'idArea' INTEGER NOT NULL, "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE)  ");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'relatorioAvaliacaoRiscosMedidas' ("
                        + "'idRelatorio' INTEGER NOT NULL, "
                        + "'idMedida' INTEGER NOT NULL, "
                        + "PRIMARY KEY (idRelatorio, idMedida), "
                        + "FOREIGN KEY (idRelatorio) REFERENCES relatorioAveriguacao (id)  ON DELETE CASCADE)  ");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'relatorioAuditoriaMedidas' ("
                        + "'idRelatorio' INTEGER NOT NULL, "
                        + "'nc' TEXT NOT NULL, " //TODO: ver se pode ser int
                        + "PRIMARY KEY (idRelatorio, nc), "
                        + "FOREIGN KEY (idRelatorio) REFERENCES relatorioAveriguacao (id)  ON DELETE CASCADE)  ");




                database.execSQL("CREATE TABLE IF NOT EXISTS 'relatorioAveriguacaoResultado' ("
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idRelatorio' INTEGER NOT NULL, "
                        + "'implementado' INTEGER NOT NULL, "
                        + "'idMedida' INTEGER NOT NULL, "
                        + "'nc' TEXT, "
                        + "'risco' TEXT, "
                        + "'idPonderacao' INTEGER NOT NULL, "
                        + "'data' INTEGER, "
                        + "FOREIGN KEY (idRelatorio) REFERENCES relatorioAveriguacao (id)  ON DELETE CASCADE)  ");



                database.execSQL("DROP TABLE IF EXISTS 'atualizacoes' ");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'atualizacoes' ("
                        + "'descricao' TEXT NOT NULL, "
                        + "'tipo' INTEGER NOT NULL, "
                        + "'seloTemporal' TEXT, "
                        + "PRIMARY KEY (descricao, tipo))");

                database.execSQL("CREATE UNIQUE INDEX index_atualizacoes_descricao ON atualizacoes (descricao)");

            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_25_26: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };






    public static final Migration MIGRACAO_24_25 = new Migration(24, 25) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("DROP TABLE IF EXISTS 'tiposTemplateAvrLevantamentos'");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'tiposTemplateAvrLevantamentos' ("
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idModelo' INTEGER NOT NULL , "
                        + "'tarefa' TEXT NOT NULL, "
                        + "'perigo' TEXT NOT NULL, "
                        + "'ativo' INTEGER NOT NULL) ");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'verificacaoEquipamentosResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'idEquipamento' INTEGER NOT NULL, "
                        + "'codigo' INTEGER NOT NULL , "
                        + "PRIMARY KEY (idAtividade, idEquipamento, codigo), "
                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE)  ");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'tiposNovos' ("
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idProvisorio' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'idDefinitivo' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'tipo' TEXT NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'codigo' TEXT , "
                        + "'idPai' TEXT , "
                        + "'estado' INTEGER NOT NULL DEFAULT " + Identificadores.Estados.Equipamentos.ESTADO_PENDENTE + " ,  "
                        + "'ativo' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_1 + " ,  "
                        + "'detalhe' TEXT) ");
            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_24_25: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };



    public static final Migration MIGRACAO_23_24 = new Migration(23, 24) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("CREATE TABLE IF NOT EXISTS 'riscosResultado' ("
                        + "'idLevantamento' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY NOT NULL, "
                        + "'idRisco' INTEGER NOT NULL, "
                        + "'idRiscoEspecifico' INTEGER NOT NULL, "
                        + "'consequencias' TEXT NOT NULL, "
                        + "'nd' INTEGER NOT NULL, "
                        + "'ne' INTEGER NOT NULL, "
                        + "'nc' INTEGER NOT NULL, "
                        + "'idTipoRisco' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL DEFAULT " + Identificadores.Origens.ORIGEM_BD + " ,  "
                        + "FOREIGN KEY (idLevantamento) REFERENCES levantamentosRiscoResultado (id)  ON DELETE CASCADE)  ");

                database.execSQL("CREATE INDEX index_riscosResultado_idLevantamento ON riscosResultado (idLevantamento)");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'tiposAtividadesPlaneaveis' ("
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'codigo' TEXT , "
                        + "'servId' TEXT NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'descricaoCompleta' TEXT NOT NULL, "
                        + "'equipa' TEXT NOT NULL, "
                        + "'sempreNecessario' INTEGER NOT NULL, "
                        + "'ordem' INTEGER NOT NULL, "
                        + "'relatorio' INTEGER NOT NULL, "
                        + "'observacao' TEXT , "
                        + "'ativo' INTEGER NOT NULL) ");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'tiposTemplateAvrLevantamentos' ("
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idModelo' INTEGER NOT NULL , "
                        + "'servId' TEXT NOT NULL, "
                        + "'tarefa' TEXT NOT NULL, "
                        + "'perigo' TEXT NOT NULL, "
                        + "'ativo' INTEGER NOT NULL) ");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'tiposTemplateAvrRiscos' ("
                        + "'id' INTEGER NOT NULL, "
                        + "'idLevantamento' INTEGER NOT NULL , "
                        + "'idRisco' INTEGER NOT NULL, "
                        + "'idRiscoEspecifico' INTEGER NOT NULL, "
                        + "'consequencias' TEXT NOT NULL, "
                        + "'ativo' INTEGER NOT NULL, "
                        + "PRIMARY KEY (id)) ");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'tiposTemplatesAVRMedidasRisco' ("
                        + "'id' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL , "
                        + "'idMedida' INTEGER NOT NULL, "
                        + "PRIMARY KEY (id, origem, idMedida), "
                        + "FOREIGN KEY (id) REFERENCES tiposTemplateAvrRiscos (id)  ON DELETE CASCADE)  ");




            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_23_24: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




    public static final Migration MIGRACAO_22_23 = new Migration(22, 23) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'propostaPlanoAcaoResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY NOT NULL, "
                        + "'origem' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'idQuestaoChecklis' INTEGER  NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'idMedida' INTEGER  NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'idNi' INTEGER  NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'idPrazo' INTEGER  NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'selecionado' INTEGER NOT NULL DEFAULT   " + Sintaxe.Codigos.NAO_SELECIONADO + " ,  "
                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE)  ");


                database.execSQL("CREATE INDEX index_propostaPlanoAcaoResultado_idAtividade ON propostaPlanoAcaoResultado (idAtividade)");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'planoAccaoResultado' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY NOT NULL, "
                        + "'idAtividadePlano' INTEGER NOT NULL,  "
                        + "'servId' TEXT  , "
                        + "'data' TEXT  , "
                        + "FOREIGN KEY (idAtividadePlano) REFERENCES planoAcaoAtividade (id)  ON DELETE CASCADE)  ");


                database.execSQL("CREATE INDEX index_planoAccaoResultado_idTarefa ON planoAccaoResultado (idTarefa)");


            }
            catch(SQLException | IllegalStateException e){
                Log.e("Migracao", "erro MIGRACAO_22_23: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };


    public static final Migration MIGRACAO_21_22 = new Migration(21, 22) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'planoAcao' ("
                        + "'idTarefa' INTEGER PRIMARY KEY NOT NULL, "
                        + "'anuidade' TEXT NOT NULL, "
                        + "'tst' TEXT , "
                        + "'cap' TEXT  , "
                        + "'email' TEXT  , "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE)  ");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'planoAcaoAtividade' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'servId' TEXT, "
                        + "'descricaoSimples' TEXT NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'equipaSst' TEXT NOT NULL, "
                        + "'sempreNecessario' INTEGER NOT NULL, "
                        + "'dataProgramada' TEXT NOT NULL, "
                        + "'dataExecucao' TEXT , "
                        + "'reprogramada' INTEGER NOT NULL, "
                        + "'observacao' TEXT, "
                        + "'fixo' INTEGER NOT NULL , "
                        + "'anuidade' TEXT, "
                        + "FOREIGN KEY (idTarefa) REFERENCES planoAcao (idTarefa)  ON DELETE CASCADE)  ");

                database.execSQL("CREATE INDEX index_planoAcaoAtividade_idTarefa ON planoAcaoAtividade (idTarefa)");



                database.execSQL("DROP TABLE IF EXISTS trabalhadoresVulneraveisResultado");
                database.execSQL("DROP INDEX IF EXISTS index_trabalhadoresVulneraveisResultado_idAtividade");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'trabalhadoresVulneraveisResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idVulnerabilidade' INTEGER NOT NULL, "
                        + "'homens' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'mulheres' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'origem' INTEGER NOT NULL DEFAULT " + Identificadores.Origens.ORIGEM_WS + ", "

                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_trabalhadoresVulneraveisResultado_idAtividade ON trabalhadoresVulneraveisResultado (idAtividade)");



            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_16_17: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




                public static final Migration MIGRACAO_20_21 = new Migration(20, 21) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("CREATE TABLE IF NOT EXISTS 'trabalhadoresVulneraveisResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idVulnerabilidade' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL, "

                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_trabalhadoresVulneraveisResultado_idAtividade ON trabalhadoresVulneraveisResultado (idAtividade)");





                database.execSQL(" ALTER TABLE itensChecklist RENAME TO tmp");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'itensChecklist' ("
                        + "'idChecklist' INTEGER NOT NULL , "
                        + "'idArea' INTEGER NOT NULL, "
                        + "'idSeccao' TEXT NOT NULL, "
                        + "'uid' TEXT NOT NULL, "
                        + "'descricao' TEXT, "
                        + "'tipo' TEXT NOT NULL, "
                        + "'codigo' TEXT, "
                        + "PRIMARY KEY (idChecklist, idArea, idSeccao, uid), "
                        + "FOREIGN KEY (idChecklist) REFERENCES checklist (id)  ON DELETE CASCADE) ");

                database.execSQL("DROP TABLE tmp");





            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_16_17: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };







    public static final Migration MIGRACAO_19_20 = new Migration(19, 20) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("ALTER TABLE atividadesPendentes ADD COLUMN idChecklist INTEGER NOT NULL DEFAULT " + Identificadores.SEM_VALOR + "");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'processosProdutivosResultado' ("
                        + "'id' INTEGER NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "PRIMARY KEY (id), "
                        + "FOREIGN KEY (id) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");




                database.execSQL("CREATE TABLE IF NOT EXISTS 'checklist' ("
                        + "'id' INTEGER PRIMARY KEY NOT NULL , "
                        + "'descricao' TEXT NOT NULL, "
                        + "'versao' TEXT NOT NULL) ");





                database.execSQL("CREATE TABLE IF NOT EXISTS 'areasChecklist' ("
                        + "'idChecklist' INTEGER NOT NULL , "
                        + "'idArea' INTEGER NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "PRIMARY KEY (idChecklist, idArea), "
                        + "FOREIGN KEY (idChecklist) REFERENCES checklist (id)  ON DELETE CASCADE) ");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'areasChecklistResultado' ("
                        + "'idAtividade' INTEGER NOT NULL , "
                        + "'idChecklist'  INTEGER NOT NULL, "
                        + "'idArea'  INTEGER NOT NULL, "
                        + "'id'  INTEGER PRIMARY KEY NOT NULL, "
                        + "'subDescricao' TEXT , "
                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");




                database.execSQL("CREATE TABLE IF NOT EXISTS 'seccoesChecklist' ("
                        + "'idChecklist' INTEGER NOT NULL , "
                        + "'idArea' INTEGER NOT NULL, "
                        + "'uid' TEXT NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'tipo' TEXT NOT NULL, "
                        + "PRIMARY KEY (idChecklist, idArea, uid), "
                        + "FOREIGN KEY (idChecklist) REFERENCES checklist (id)  ON DELETE CASCADE) ");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'itensChecklist' ("
                        + "'idChecklist' INTEGER NOT NULL , "
                        + "'idArea' INTEGER NOT NULL, "
                        + "'idSeccao' TEXT NOT NULL, "
                        + "'uid' TEXT NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'tipo' TEXT NOT NULL, "
                        + "'codigo' TEXT, "
                        + "PRIMARY KEY (idChecklist, idArea, idSeccao, uid), "
                        + "FOREIGN KEY (idChecklist) REFERENCES checklist (id)  ON DELETE CASCADE) ");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'questionarioChecklistResultado' ("
                        + "'idArea' INTEGER NOT NULL , "
                        + "'idSeccao' TEXT NOT NULL, "
                        + "'idItem' TEXT NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY NOT NULL, "
                        + "'tipo' TEXT NOT NULL, "

                        + "'resposta' TEXT, "
                        + "'nr' TEXT, "
                        + "'ni' TEXT, "

                        + "'ut1' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut1_CategoriasRisco' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut1_LocalRisco_A' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut1_LocalRisco_B' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut1_LocalRisco_C' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut1_LocalRisco_D' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut1_LocalRisco_E' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut1_LocalRisco_F' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "

                        + "'ut2' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut2_CategoriasRisco' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut2_LocalRisco_A' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut2_LocalRisco_B' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut2_LocalRisco_C' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut2_LocalRisco_D' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut2_LocalRisco_E' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'ut2_LocalRisco_F' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "

                        + "'observacao' TEXT, "
                        + "'origem' INTEGER NOT NULL, "
                        + "FOREIGN KEY (idArea) REFERENCES areasChecklistResultado (id)  ON DELETE CASCADE) ");



            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_19_20: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };








    public static final Migration MIGRACAO_18_19 = new Migration(18, 19) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL("CREATE TABLE IF NOT EXISTS 'registoVisitaResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'recebidoPor' TEXT NOT NULL, "
                        + "'funcao' TEXT NOT NULL, "
                        + "'observacao' TEXT, "
                        + "PRIMARY KEY (idTarefa), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");






                database.execSQL("CREATE TABLE IF NOT EXISTS 'trabalhoRealizadoResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' INTEGER NOT NULL, "
                        + "'informacao' TEXT, "
                        + "PRIMARY KEY (idTarefa, id), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_trabalhoRealizadoResultado_idTarefa ON trabalhoRealizadoResultado (idTarefa)");

            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_16_17: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };





    public static final Migration MIGRACAO_17_18 = new Migration(17, 18) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL(" ALTER TABLE clientes RENAME TO tmp");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'clientes' ("
                        + "'idTarefa' INTEGER PRIMARY KEY NOT NULL, "
                        + "'nome' TEXT NOT NULL, "
                        + "'morada' TEXT, "
                        + "'localidade' TEXT, "
                        + "'codigoPostal' TEXT, "
                        + "'cpAlf' TEXT, "
                        + "'freguesia' TEXT, "
                        + "'nif' TEXT, "
                        + "'actividade' TEXT, "
                        + "'actividade1' TEXT, "
                        + "'responsavel' TEXT , "
                        + "'telefone' TEXT , "
                        + "'telemovel' TEXT , "
                        + "'email' TEXT , "
                        + "'emailAutenticado' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'cae' TEXT , "
                        + "'cae1' TEXT , "
                        + "'segmento' TEXT , "

                        + "'trabalhadores' TEXT , "
                        + "'anomaliaExtintores' TEXT , "

                        + "'moveLife' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ,  "
                        + "'numeroAnalises' TEXT , "


                        + "'numeroCliente' TEXT , "
                        + "'servicoTp' TEXT , "
                        + "'servico' TEXT , "
                        + "'minutos' TEXT , "
                        + "'ultimaVisita' TEXT, "
                        + "'contrato' TEXT , "
                        + "'dataContrato' TEXT, "
                        + "'novo' TEXT , "
                        + "'dataInsercao' TEXT, "
                        + "'minutosRealizados' TEXT, "

                        + "'tipoPacote' TEXT, "
                        + "'anuidadeContrato' TEXT, "

                        + "'saldoCartaoVm' TEXT, "
                        + "'notas' TEXT, "
                        + "'periodo' TEXT, "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa) ON DELETE CASCADE) ");


                database.execSQL(" INSERT INTO clientes(idTarefa, nome, morada, localidade, codigoPostal, cpAlf) "
                        + "SELECT idTarefa, nome, morada, localidade, codigoPostal, cpAlf FROM tmp");

                database.execSQL("DROP TABLE tmp");


            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_16_17: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };




    public static final Migration MIGRACAO_16_17 = new Migration(16, 17) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {


                database.execSQL(" ALTER TABLE atividadeExecutadas RENAME TO tmp");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'atividadeExecutadas' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'ordem' TEXT NOT NULL, "
                        + "'idServico' TEXT NOT NULL, "
                        + "'descricao' TEXT NOT NULL, "
                        + "'dataProgramada' INTEGER NOT NULL, "
                        + "'dataExecucao' INTEGER NOT NULL, "
                        + "PRIMARY KEY (idTarefa, idServico, ordem), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");


                database.execSQL(" INSERT INTO atividadeExecutadas(idTarefa, ordem, idServico, descricao, dataProgramada, dataExecucao) "
                        + "SELECT idTarefa, ordem, idServico, descricao, dataProgramada, dataExecucao FROM tmp");

                database.execSQL("DROP TABLE tmp");

                database.execSQL("DROP INDEX IF EXISTS index_atividadeExecutadas_idTarefa");
                database.execSQL("CREATE INDEX index_atividadeExecutadas_idTarefa ON atividadeExecutadas (idTarefa)");




                database.execSQL(" ALTER TABLE utilizadores RENAME TO tmp");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'utilizadores' ("
                        + "'id' TEXT PRIMARY KEY NOT NULL, "
                        + "'area' TEXT NOT NULL, "
                        + "'nome' TEXT NOT NULL, "
                        + "'cap' TEXT, "
                        + "'api' INTEGER NOT NULL DEFAULT " + Identificadores.App.APP_SA + ", "
                        + "'email' TEXT ) ");

                database.execSQL(" INSERT INTO utilizadores(id, area, nome, api, email) "
                        + "SELECT id, area, nome, api, email FROM tmp");

                database.execSQL("DROP TABLE tmp");


            }
            catch(SQLException e){
                Log.e("Migracao", "erro MIGRACAO_16_17: " + e.getMessage());
                //Timber.e("erro MIGRACAO_2_3: " + e.getMessage());
            }
        }
    };



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

                database.execSQL("CREATE INDEX index_parqueExtintores_idTarefa ON parqueExtintores (idTarefa)");



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

                database.execSQL("CREATE INDEX index_tiposExtintor_idTarefa ON tiposExtintor (idTarefa)");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'moradas' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' TEXT NOT NULL , "
                        + "'tipo' INTEGER NOT NULL, "
                        + "'endereco' TEXT NOT NULL, "
                        + "'cp4' TEXT NOT NULL, "
                        + "'localidade' TEXT NOT NULL, "
                        + "PRIMARY KEY (idTarefa, id, tipo), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_moradas_idTarefa ON moradas (idTarefa)");



                database.execSQL("CREATE TABLE IF NOT EXISTS 'medidasResultado' ("
                        + "'id' INTEGER NOT NULL, "
                        + "'idMedida' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL, "
                        + "PRIMARY KEY (id, idMedida, origem)) ");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'categoriasProfissionaisResultado' ("
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idCategoriaProfissional' INTEGER NOT NULL, "
                        + "'idRegisto' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL, "
                        + "'homens' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " , "
                        + "'mulheres' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " ) ");



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

                database.execSQL("CREATE INDEX index_colaboradores_idTarefa ON colaboradores (idTarefa)");

                database.execSQL("CREATE TABLE IF NOT EXISTS 'colaboradoresResultado' ("
                        + "'idTarefa' INTEGER NOT NULL , "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idRegisto' TEXT , "
                        + "'nome' TEXT , "
                        + "'estado' TEXT , "
                        + "'idMorada' TEXT , "
                        + "'sexo' TEXT , "
                        + "'dataNascimento' INTEGER , "
                        + "'nacionalidade' TEXT, "
                        + "'dataAdmissao' INTEGER , "
                        + "'dataAdmissaoFuncao' INTEGER , "
                        + "'idCategoriaProfissional' INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " , "
                        + "'profissao' TEXT , "
                        + "'posto' TEXT , "
                        + "'origem' INTEGER NOT NULL, "
                        + "FOREIGN KEY (idRegisto) REFERENCES colaboradores (id)  ON DELETE CASCADE) ");





                database.execSQL("CREATE TABLE IF NOT EXISTS 'relatorioAmbientalResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'tipo' INTEGER NOT NULL, "

                        + "'marca' TEXT NOT NULL, "
                        + "'numeroSerie' TEXT NOT NULL, "
                        + "'data' INTEGER NOT NULL, "
                        + "'inicio' INTEGER NOT NULL, "
                        + "'termino' INTEGER NOT NULL, "
                        + "'idNebulosidade' INTEGER NOT NULL, "

                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_relatorioAmbientalResultado_idAtividade ON relatorioAmbientalResultado (idAtividade)");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'avaliacoesAmbientaisResultado' ("
                        + "'idRelatorio' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idArea' INTEGER NOT NULL, "
                        + "'anexoArea' TEXT, "

                        + "'nome' TEXT, "
                        + "'sexo' INTEGER NOT NULL DEFAULT " + Identificadores.SEXO_OMISSAO + " , "
                        + "'tipoIluminacao' INTEGER NOT NULL DEFAULT " + Identificadores.SEM_VALOR + " , "
                        + "'emedioLx' INTEGER NOT NULL DEFAULT " + Identificadores.SEM_VALOR + " , "
                        + "'eLxArea' INTEGER NOT NULL DEFAULT " + Identificadores.SEM_VALOR + " , "
                        + "'idElx' INTEGER NOT NULL DEFAULT " + Identificadores.SEM_VALOR + " , "
                        + "'eLx' TEXT, "

                        + "'temperatura' REAL NOT NULL DEFAULT " + Identificadores.VALOR_0 + " , "
                        + "'humidadeRelativa' REAL NOT NULL DEFAULT " + Identificadores.VALOR_0 + " , "
                        + "'homens'  INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " , "
                        + "'mulheres'  INTEGER NOT NULL DEFAULT " + Identificadores.VALOR_0 + " , "
                        + "FOREIGN KEY (idRelatorio) REFERENCES relatorioAmbientalResultado (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_avaliacoesAmbientaisResultado_idRelatorio ON avaliacoesAmbientaisResultado (idRelatorio)");


                database.execSQL("CREATE TABLE IF NOT EXISTS 'levantamentosRiscoResultado' ("
                        + "'idAtividade' INTEGER NOT NULL, "
                        + "'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "

                        + "'tarefa' TEXT NOT NULL, "
                        + "'perigo' TEXT NOT NULL, "
                        + "'idModelo' INTEGER NOT NULL, "
                        + "'origem' INTEGER NOT NULL, "

                        + "FOREIGN KEY (idAtividade) REFERENCES atividadesPendentes (id)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_levantamentosRiscoResultado_idAtividade ON levantamentosRiscoResultado (idAtividade)");
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
