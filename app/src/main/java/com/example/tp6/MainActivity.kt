package com.example.tp6

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    private lateinit var Nom:EditText
    private lateinit var Prenom:EditText
    private lateinit var Tel:EditText
    private lateinit var Email:EditText
    private lateinit var Login:EditText
    private lateinit var MDP:EditText
    private lateinit var btnValider:Button
    private lateinit var btnAnnuler:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Nom= findViewById(R.id.Nom)
        Prenom= findViewById(R.id.Prenom)
        Tel= findViewById(R.id.Phone)
        Email= findViewById(R.id.Email)
        Login= findViewById(R.id.Login)
        MDP= findViewById(R.id.Mdp)
        btnValider=findViewById(R.id.btnValider)

        btnAnnuler=findViewById(R.id.btnAnnuler)
        btnValider.setOnClickListener{
      if ((Nom.getText().length===0)||(Prenom.getText().length===0)||(Tel.getText().length===0)||(Email.getText().length===0)||(Login.getText().length===0)||(MDP.getText().length===0)){
            val ad: AlertDialog.Builder
            ad = AlertDialog.Builder(this)
            ad.setMessage("Tous les champs doivent être remplis")
            ad.setTitle("Attention")
            ad.setPositiveButton("ok"){
                    dialogInterface, i -> this
            }
            val a = ad.create()
            a.show()
        } else{
           val values =  ContentValues();
          values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_NOM, Nom.text.toString());
          values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PRENOM, Prenom.text.toString())
          values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_PHONE, Tel.text.toString())
          values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_EMAIL, Email.text.toString());
          values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_LOGIN, Login.text.toString());
          values.put(EtudiantBC.EtudiantEntry.COLUMN_NAME_MDP, MDP.text.toString());
          val mDbHelper =  EtudiantDBHelper(getApplicationContext())
          val db = mDbHelper.getWritableDatabase()
           db.insert("etudiant",null,values)
          val intent = Intent(this, ListEtudiant::class.java)
          startActivity(intent)
          db.close();
          mDbHelper.close();
        }
    }
        btnAnnuler.setOnClickListener{
            val ad: AlertDialog.Builder
            ad = AlertDialog.Builder(this)
            ad.setMessage("Voulez vous vraiment remettre à Zero les champs?")
            ad.setTitle("Attention")
            ad.setPositiveButton("oui"){
                    dialogInterface, i -> this
                Nom.setText("")
                Prenom.setText("")
                Tel.setText("")
                Email.setText("")
                Login.setText("")
                MDP.setText("")
            }
            ad.setNegativeButton("non"){
                    dialogInterface, i -> this
            }
            val a = ad.create()
            a.show()

        }



    }
}