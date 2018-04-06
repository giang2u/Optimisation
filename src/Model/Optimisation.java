package Model;

import java.util.ArrayList;
import java.util.Scanner;

import Algo.RecuitSimule;
import Algo.Tabou;

public class Optimisation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Tache> lt = new ArrayList<>();
		
		Tache t = new Tache(5);
		Tache t2 = new Tache(4);
		Tache t3 = new Tache(3);
		Tache t4 = new Tache(6);
		Tache t5 = new Tache(5);
		Tache t6 = new Tache(7);
		/*Tache t7 = new Tache(8);
		Tache t8 = new Tache(1);
		Tache t9 = new Tache(5);
		Tache t10 = new Tache(4);*/
		
		lt.add(t);
		lt.add(t2);
		lt.add(t3);
		lt.add(t4);
		lt.add(t5);
		lt.add(t6);
		/*lt.add(t7);
		lt.add(t8);
		lt.add(t9);
		lt.add(t10);*/
		
		
		
		System.out.println("Choisissez votre Algo 1 pour Tabou ou 2 pour Recuit simule:");
		Scanner scinit = new Scanner(System.in);
		int choix = scinit.nextInt();
		
		
		switch(choix) { 
		case 1:
			System.out.println("La taille de la liste de tabou!");
			Scanner sc = new Scanner(System.in);
			int taille = sc.nextInt();
			


			System.out.println("Nombre de Processeur!");
			Scanner scp = new Scanner(System.in);
			int nbProc = scp.nextInt();
			

			System.out.println("Nombre de Tache!");
			Scanner sct = new Scanner(System.in);
			int nbTache = sct.nextInt();
			

			System.out.println("Nombre d'iteration!");
			Scanner sci = new Scanner(System.in);
			int iter = sct.nextInt();
			
			Tabou tabou = new Tabou(taille, nbProc, nbTache, iter);
			tabou.tabou();
			break;
		case 2: 
			System.out.println("Valeur de la temperature:");
			Scanner scRecuit = new Scanner(System.in);
			int temp = scRecuit.nextInt();
			


			System.out.println("Nombre de Processeur!");
			scRecuit = new Scanner(System.in);
			int nbProcRecuit = scRecuit.nextInt();
			

			System.out.println("Nombre de Tache!");
			scRecuit = new Scanner(System.in);
			int nbTacheRecuit = scRecuit.nextInt();
			
			
			System.out.println("Definition des bornes pour les valeurs des taches:");
			System.out.println("Valeur minimale:");
			scRecuit = new Scanner(System.in);
			int borneInferieur = scRecuit.nextInt();
			
			System.out.println("Valeur maximale:");
			scRecuit = new Scanner(System.in);
			int borneSuperieur = scRecuit.nextInt();
			
			
			RecuitSimule rc = new RecuitSimule();
			ArrayList<Processeur> p = rc.recuitSimule(nbTacheRecuit, nbProcRecuit, temp, borneInferieur, borneSuperieur);
			
			for ( Processeur pp : p) {
				
			System.out.println("\nproc  " + pp.getDuree());
			for ( Tache tt : pp.getListTache()) {
				System.out.print("   Tache  " + tt.getDuree());
			}
			}
		
		}
		
		
		/*ArrayList<Processeur> p = rc.recuitSimule(lt, 6, 1000000);

		ArrayList<Processeur> p = rc.recuitSimule(lt, 2, 1000000);
		
		for ( Processeur pp : p) {
			
		System.out.println("\nproc  " + pp.getDuree());
		for ( Tache tt : pp.getListTache()) {
			System.out.print("   Tache  " + tt.getDuree());
		}
		}*/
		

	}

}
