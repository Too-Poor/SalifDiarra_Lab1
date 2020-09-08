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

    /*
    Les validations doivent être refaites quand l'application est fermée, puis rouverte.
    Cela permet de retrouver l'application dans le même état si la langue est modifiée
    en cours d'exécution.
     */
    @Override
    public void onResume(){
        super.onResume();

        CheckBox chk_afficherMotDePasse = (CheckBox)findViewById(R.id.chk_afficherMotDePasse);
        EditText et_motDePasse = (EditText)findViewById(R.id.et_motDePasse);
        TextView tv_validation =  (TextView)findViewById(R.id.tv_validation);

        modifierAffichageMotDePasse(chk_afficherMotDePasse, et_motDePasse);
        validerMotDePasse(et_motDePasse, tv_validation);
    }

    public void onClickChk_afficherMotDePasse(View view) {
        CheckBox chk_afficherMotDePasse = (CheckBox)findViewById(R.id.chk_afficherMotDePasse);
        EditText et_motDePasse = (EditText)findViewById(R.id.et_motDePasse);

        modifierAffichageMotDePasse(chk_afficherMotDePasse, et_motDePasse);
    }

    private void modifierAffichageMotDePasse(CheckBox chk_afficherMotDePasse, EditText et_motDePasse) {
        int debutSelection;
        int finSelection;
        if (chk_afficherMotDePasse.isChecked()) {
            //Sauvegarder la position du curseur.
            debutSelection = et_motDePasse.getSelectionStart();
            finSelection = et_motDePasse.getSelectionEnd();

            //Afficher le mot de passe.
            et_motDePasse.setTransformationMethod(null);

            //Remettre le curseur là où il était avant la transformation.
            et_motDePasse.setSelection(debutSelection, finSelection);
        } else {
            //Sauvegarder la position du curseur.
            debutSelection = et_motDePasse.getSelectionStart();
            finSelection = et_motDePasse.getSelectionEnd();

            //Cacher le mot de passe.
            et_motDePasse.setTransformationMethod(new PasswordTransformationMethod());

            //Remettre le curseur là où il était avant la transformation.
            et_motDePasse.setSelection(debutSelection, finSelection);
        }
    }

    public void onClickBtn_valider(View view) {
        EditText et_motDePasse = (EditText)findViewById(R.id.et_motDePasse);
        TextView tv_validation =  (TextView)findViewById(R.id.tv_validation);

        validerMotDePasse(et_motDePasse, tv_validation);
    }

    public void validerMotDePasse(EditText et_motDePasse, TextView tv_validation) {
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