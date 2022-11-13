package com.example.ofc_gacha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.PasswordAuthentication;
import java.util.logging.Logger;

public class SignInWindow extends AppCompatActivity {

    private Button signIn;
    private Button signUp;
    private EditText username;
    private EditText passwd;
    private TextView us;
    private TextView pas;
    public static final int SignUpWindow= 1;
    public static final int GachaWindow= 2;
    private Intent intent;
    private SQLiteDatabase dataBase= null;
    private Toast toast1;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_window);

        dataBase= openOrCreateDatabase("Gacha", Context.MODE_PRIVATE, null);
        dataBase.execSQL("CREATE TABLE IF NOT EXISTS usuarios (idUsuario INTEGER, nombre VARCHAR, contrasenia VARCHAR, email VARCHAR)");
        dataBase.execSQL("CREATE TABLE IF NOT EXISTS personajes (idPersonaje INTEGER, nombre VARCHAR, descripcion VARCHAR)");
        dataBase.execSQL("CREATE TABLE IF NOT EXISTS biblioteca (nombre Varchar, idPersonaje INTEGER)");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (1,'Miku Nakano', 'Anime: Gotoubun no hanayome')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (2,'Kaguya Shinomiya', 'Anime: Kaguya-sama: Love is war')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (3,'Erza Scarlet', 'Anime: Fairy Tail')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (4,'Camie Utsushimi', 'Anime: Boku no hero')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (5,'Miku Hatsune', 'Idol Virtual ')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (6,'Emilia', 'Anime: Re:Zero')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (7,'Ai Hayasaka', 'Anime: Kaguya-sama: Love is War')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (8,'Jibril', 'Anime: No game no life')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (9,'Knekro/Sergio', 'Streamer de éxito')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (10,'Saber/Arturia Pendragón', 'Fate Stay Night')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (11,'Yor Forger', 'Anime: Spy x Family')");
        dataBase.execSQL("INSERT INTO personajes (idPersonaje, nombre, descripcion) VALUES (12,'Marin Kitagawa', 'Anime: Sono bisque doll')");
        username= findViewById(R.id.textUsername);


        passwd= findViewById(R.id.textPassword);

        us= findViewById(R.id.usernameTextVeiw);

        pas= findViewById(R.id.passwdTextView);

        signIn= findViewById(R.id.signInButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    dataBase.execSQL("INSERT INTO usuarios (nombre, contrasenia, email) VALUES ('a', 'a', 'a')");

                }catch (Exception ex){
                    Logger.getLogger(ex.getMessage());
                }


                if (username.getText().toString().isEmpty() || passwd.getText().toString().isEmpty()){
                     toast1 =
                            Toast.makeText(getApplicationContext(),
                                    R.string.error_empty, Toast.LENGTH_SHORT);

                    toast1.show();
                }else if(!comprobarNombre()){
                     toast1 =
                            Toast.makeText(getApplicationContext(),
                                    R.string.error_useduser, Toast.LENGTH_SHORT);

                    toast1.show();
               }else if(!comprobarPasswd()){
                     toast1 =
                            Toast.makeText(getApplicationContext(),
                                    R.string.error_incorrectpasswd, Toast.LENGTH_SHORT);

                    toast1.show();
                }else{
                    /*intent = new Intent(gachaWindow.this, gachaWindow.class);
                    intent.setData(Uri.parse(username.getText().toString()));
                    startActivityForResult(intent, GachaWindow);*/
                }
            }
        });

        signUp= findViewById(R.id.signUpBotton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(SignInWindow.this, SignUpWindow.class);
                startActivityForResult(intent, SignUpWindow);
            }
        });
    }

    private boolean comprobarPasswd() {

        try{
            cursor= dataBase.rawQuery("SELECT contrasenia FROM usuarios WHERE nombre='"
                    +username.getText().toString()+"'",null);

        }catch(Exception ex){
            Logger.getLogger(ex.getMessage());
        }

        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(passwd.getText().toString())) {

                return false;
            } else if(!cursor.getString(0).equals(passwd.getText().toString())){
                return true;
            }
        }

        return false;
    }

    public boolean comprobarNombre(){
         cursor= dataBase.rawQuery("SELECT nombre FROM usuarios WHERE nombre='"
                +username.getText().toString()+"'",null);
        if (cursor.getCount()==0){
            return false;
        }else{
            return true;
        }
    }
}