package com.example.salifdiarra_lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

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

    public void onClickBtn_valider(View view) {
        EditText et_motDePasse = (EditText)findViewById(R.id.et_motDePasse);
        TextView tv_validation =  (TextView)findViewById(R.id.tv_validation);
        String regexMotDePasse = "^(?=.{10,})" + //Au moins 10 caractères
                                 "(?=.*[a-z])" + //Au moins 1 minuscule
                                 "(?=.*[A-Z])" + //Au moins 1 MAJUSCULE
                                 "(?=.*[0-9])" + //Au moins 1 chiffre
                                 //Au moins un caractère spécial autorisé
                                 "(?=.*[@#$%&()\\[\\]{}_=<>+\\-!?*/|:;.,‘\"~^]).*$";

        if (Pattern.matches(regexMotDePasse, et_motDePasse.getText())) {
            tv_validation.setText(R.string.texteMotDePasseValide);
            tv_validation.setTextColor(getResources().getColor(R.color.couleurMotDePasseValide));
        } else {
            tv_validation.setText(R.string.texteMotDePasseInvalide);
            tv_validation.setTextColor(getResources().getColor(R.color.couleurMotDePasseInvalide));
        }
    }
}