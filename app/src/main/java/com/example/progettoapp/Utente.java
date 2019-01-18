package com.example.progettoapp;

import java.io.Serializable;

public class Utente implements Serializable {
   String nome;
   String cognome;
   int eta;
   String clan;

   public Utente(String nome, String cognome,String clan, int eta){
       this.nome=nome;
       this.cognome=cognome;
       this.eta= eta;
       this.clan=clan;

   }
}
