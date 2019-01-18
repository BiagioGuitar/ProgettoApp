package com.example.progettoapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * @author Biagio Murano
 *
 * FileUtility class contiente metodi statici in overloading per:
 *
 * salvataggio e il recupero di primitivi e boleani utilizzando le SharedPreference
 * savataggio e il recupero di Oggetti salvandoli nella memoria interna del dispostivo utilizzando FileOutputStream e FileInputStream
 *
 *
 */

public class FileUtility {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Context context;

    static FileOutputStream fos;
    static ObjectOutputStream oos;
    static String filePathOutput;
    static String filePathInPut;

    static FileInputStream fis;
    static ObjectInputStream ois;

    static Object obj;

    /**
     * il metodo createPreference Crea la SharedPreference.
     * Non deve essere invocato ma
     * viene automaticamente chiamato all'interno di altri metodi se necessario
     *
     * @param c contest
     *
     *  <strong>interfaccia utilizzata<strong/> {@link SharedPreferences}
     */

    private static void createPreference(Context c) {
        context = c;

        sharedPreferences = context.getSharedPreferences("pref", MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    /**
     * Il metodo viene invocato per salvare un valore all'interno di una SharedPreference
     *
     * @param c      contesto
     * @param key    etichetta sotto la quale salvare il valore
     * @param value  valore
     *
     * <strong>interfaccia utilizzata<strong/> {@link SharedPreferences}
     */

    public static void savePreference(Context c, String key, int value) {
        createPreference(c);
        editor.putInt(key, value).apply();
    }

    /**
     * Il metodo viene invocato per salvare un valore all'interno di una SharedPreference
     *
     * @param c      contesto
     * @param key    etichetta sotto la quale salvare il valore
     * @param value  valore
     *
     * <strong>interfaccia utilizzata<strong/> {@link SharedPreferences}
     *
     */

    public static void savePreference(Context c, String key, String value) {
        createPreference(c);
        editor.putString(key, value).apply();
    }

    /**
     * Il metodo viene invocato per salvare un valore all'interno di una SharedPreference
     *
     * @param c      contesto
     * @param key    etichetta sotto la quale salvare il valore
     * @param value  valore
     *
     *  <strong>interfaccia utilizzata<strong/> {@link SharedPreferences}
     *
     */

    public static void savePreference(Context c, String key, boolean value) {
        createPreference(c);
        editor.putBoolean(key, value).apply();
    }

    /**
     * Il metodo viene invocato per recuperare un valore all'interno di una SharedPreference
     *
     * @param c            contesto
     * @param key           nome dell'etichetta del valore da recuperare
     * @param defaultValue  valore di ritorno se la risorsa nel parametro @key non è presente
     * @return ritorna il primitivo recuperato corrispondente
     *
     *  <strong>interfaccia utilizzata<strong/> {@link SharedPreferences}
     */


    public static int getPreference(Context c, String key, int defaultValue) {
        if (sharedPreferences == null) createPreference(c);
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     *  Il metodo viene invocato per recuperare un valore all'interno di una SharedPreference
     *
     * @param c            contesto
     * @param key           nome dell'etichetta del valore da recuperare
     * @param defaultValue  valore di ritorno se la risorsa nel parametro @key non è presente
     * @return ritorna il primitivo recuperato corrispondente
     *
     *  <strong>interfaccia utilizzata<strong/> {@link SharedPreferences}
     */

    public static String getPreference(Context c, String key, String defaultValue) {
        if (sharedPreferences == null) createPreference(c);
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     *  Il metodo viene invocato per recuperare un valore all'interno di una SharedPreference
     *
     * @param c            contesto
     * @param key           nome dell'etichetta del valore da recuperare
     * @param defaultValue  valore di ritorno se la risorsa nel parametro @key non è presente
     * @return ritorna il primitivo recuperato corrispondente
     *
     *  <strong>interfaccia utilizzata<strong/> {@link SharedPreferences}
     */

    public static boolean getPreference(Context c, String key, boolean defaultValue) {
        if (sharedPreferences == null) createPreference(c);
        createPreference(c);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

//-------------Salvare e recuperare oggetti dalla memoria----------------------------------------------




    /**
     * Il metodo salvaOggettoInMemoria salva un oggetto in memoria
     *
     * L'oggetto savato deve implementare l'interfaccia Serializable
     *
     * per accedere al percorso dove il file è stato salvato:
     * cliccare su View in alto a sinistra
     * cliccare su Tools Windows
     * cliccare su device file explorer
     *
     * nella directory com.example.nomeProgetto/files
     *
     * sostituendo nomeProgetto con il mome del progetto corrente
     *
     * @param c     contesto
     * @param file  etichetta sotto la quale salvare l'oggetto
     * @param obj   Oggetto da salvare
     *
     * <strong></>classi utilizzate </strong>{@link ObjectOutputStream}, {@link FileOutputStream}
     */



    public static void salvaOggettoInMemoria(Context c,String file, Object obj) {
        filePathOutput = file;
        context=c;
        //File file1 = new File(file);

        try {
            fos = context.openFileOutput(file,MODE_PRIVATE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            oos = new ObjectOutputStream(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Il metodo recuperaOggettoDallaMemoria recupera un'oggetto precedentemente salvato in  memoria
     *
     * L oggetto deve aver implementato l'interfaccia Serializzable
     *
     *  per accedere al percorso dove il file è stato salvato:
     *      * cliccare su View in alto a sinistra
     *      * cliccare su Tools Windows
     *      * cliccare su device file explorer
     *      *
     *      * nella directory com.example.nomeProgetto/files
     *      *
     *      * sostituendo nomeProgetto con il mome del progetto corrente
     *
     * @param c     contesto
     * @param file  etichetta sotto la quale è stato salvato l'oggetto
     * @return ritorna l'oggetto recuperato
     *
     * <strong></>classi utilizzate </strong>{@link ObjectInputStream}, {@link FileInputStream}
     */


    public static Object recuperaOggettoDallaMemoria(Context c ,String file) {
         filePathInPut =file;
         context = c;
        try {
            fis = context.openFileInput(filePathInPut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ois = new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Object obj = null;
        try {
            obj = ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
