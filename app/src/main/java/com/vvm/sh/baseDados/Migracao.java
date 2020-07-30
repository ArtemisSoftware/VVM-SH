package com.vvm.sh.baseDados;

import android.database.SQLException;
import android.util.Log;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migracao {

    public static final Migration[] obterMigracoes(){

        Migration migrations [] =  new Migration []{
                MIGRACAO_1_2, MIGRACAO_2_3, MIGRACAO_3_4, MIGRACAO_4_5, MIGRACAO_5_6, MIGRACAO_6_7
        };

        return migrations;
    }


    public static final Migration MIGRACAO_7_8 = new Migration(7, 8) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {

                database.execSQL("CREATE TABLE IF NOT EXISTS 'anomaliasResultado' ("
                        + "'idTarefa' INTEGER NOT NULL, "
                        + "'id' INTEGER  PRIMARY KEY AUTOINCREMENT NOT NULL, "
                        + "'idAnomalia' INTEGER NOT NULL, "
                        + "'observacao' TEXT , "
                        + "PRIMARY KEY (idTarefa, id), "
                        + "FOREIGN KEY (idTarefa) REFERENCES tarefas (idTarefa)  ON DELETE CASCADE) ");

                database.execSQL("CREATE INDEX index_anomaliasResultado_idTarefa ON anomaliasResultado (idTarefa)");



//                acoesFormacaoResultado
//                CAMPO_ID_ATIVIDADE_PENDENTE +" INTEGER," +
//                        CAMPO_ID_DESIGNACAO +" INTEGER," +
//                        CAMPO_LOCAL +" TEXT," +
//                        CAMPO_DATA + " DATETIME," +
//                        CAMPO_INICIO + " DATETIME," +
//                        CAMPO_TERMINO + " DATETIME," +




//formandosResultado
                //    CAMPO_ID_ATIVIDADE_PENDENTE +" INTEGER NOT NULL," +
//    CAMPO_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
//    CAMPO_NOME +" TEXT," +
//    CAMPO_BI_CARTAO_CIDADAO + " TEXT," +
//    CAMPO_SEXO + " TEXT," +
//    CAMPO_NISS + " TEXT," +
//    CAMPO_DATA_NASCIMENTO + " DATETIME," +
//    CAMPO_NATURALIDADE + " TEXT," +
//    CAMPO_NACIONALIDADE + " TEXT," +
//    CAMPO_SELECIONADO + " INTEGER DEFAULT 0," +
//    CAMPO_ID_QUADRO_PESSOAL + " INTEGER," +
//    CAMPO_ORIGEM + " INTEGER DEFAULT " + IdentificadoresIF.ORIGEM_DADOS_WS + ", " +





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
