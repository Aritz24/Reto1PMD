package com.example.ofc_gacha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private  SQLiteDatabase dataBase= null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_window);



        dataBase= openOrCreateDatabase("Gacha", Context.MODE_PRIVATE, null);
        dataBase.execSQL("Create TABLE IF NOT EXISTS usuarios (idUsuario INTEGER, nombre VARCHAR, contraseña VARCHAR)");
        dataBase.execSQL("CREATE TABLE IF NOT EXISTS personajes (idPersonaje INTEGER, nombre VARCHAR, descripcion VARCHAR)");
        dataBase.execSQL("CREATE TABLE IF NOT EXISTS biblioteca (idUsuario INTEGER, idPersonaje INTEGER)");
        signIn= findViewById(R.id.signInButton);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty() || passwd.getText().toString().isEmpty()){

                }else if(!comprobarBD()){

                }else{

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

        username= findViewById(R.id.textPersonName);

        passwd= findViewById(R.id.textPasswd);

        us= findViewById(R.id.usernameTextVeiw);

        pas= findViewById(R.id.passwdTextView);

    }

    public boolean comprobarBD(){
        if (dataBase.rawQuery("SELECT * FROM usuarios WHERE nombre="+username.getText().toString()+"").isNull()){

        }
        return true;
    }
}