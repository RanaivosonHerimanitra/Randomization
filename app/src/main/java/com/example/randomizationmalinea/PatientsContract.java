package com.example.randomizationmalinea;
import android.provider.BaseColumns;

/**
 * Created by herimanitra on 4/15/16.
 */
public class PatientsContract {
    public static final class PatientEntry implements BaseColumns {
        public static final String TABLE_NAME = "patient";
        public static final String COLUMN_ID_KEY = "_id";
        public static final String COLUMN_LOCATION_KEY = "location";
        public static final String COLUMN_NOM_KEY = "nom";
        public static final String COLUMN_PRENOM_KEY = "prenom";
        public static final String COLUMN_GROUPE_KEY="groupe";
    }
}
