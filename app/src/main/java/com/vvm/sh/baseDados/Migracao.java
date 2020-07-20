package com.vvm.sh.baseDados;

import android.database.SQLException;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migracao {

    public static final Migration[] obterMigracoes(){

        Migration migrations [] =  new Migration []{
                MIGRATION_1_2
        };

        return migrations;
    }


    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            try {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'utilizadores' ("
                        + "'id' TEXT PRIMARY KEY NOT NULL, "
                        + "'area' TEXT NOT NULL, "
                        + "'nome' TEXT NOT NULL, "
                        + "'email' TEXT NOT NULL) ");

                //Timber.d("MIGRATION_1_2: success");
            }
            catch(SQLException e){
                //Timber.e("erro MIGRATION_1_2: " + e.getMessage());
            }
        }
    };

}
