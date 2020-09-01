package com.example.salifdiarra_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickChk_afficherMotDePasse(View view) {
        CheckBox chk_afficherMotDePasse = (CheckBox)findViewById(R.id.chk_afficherMotDePasse);
        EditText et_motDePasse = (EditText)findViewById(R.id.et_motDePasse);
        int debutSelection;
        int finSelection;

        if (chk_afficherMotDePasse.isChecked()) {
            //Sauvegarder la position du curseur.
            debutSelection = et_motDePasse.getSelectionStart();
            finSelection = et_motDePasse.getSelectionEnd();

            et_motDePasse.setTransformationMethod(null);

            //Remettre le curseur là où il était avant la transformation.
            et_motDePasse.setSelection(debutSelection, finSelection);
        } else {
            debutSelection = et_motDePasse.getSelectionStart();
            finSelection = et_motDePasse.getSelectionEnd();

            et_motDePasse.setTransformationMethod(new PasswordTransformationMethod());

            et_motDePasse.setSelection(debutSelection, finSelection);
        }
    }

}