package com.vvm.sh.baseDados;

import android.database.SQLException;
import android.util.Log;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migracao {

    public static final Migration[] obterMigracoes(){

        Migration migrations [] =  new Migration []{
                MIGRACAO_1_2
        };

        return migrations;
    }


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
