package ar.unrn.tp.main;

import java.text.ParseException;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import ar.unrn.tp.modelo.FechaHora;
import ar.unrn.tp.modelo.TarjetaCredito;

public class Main {
	
	public static void main(String[] args) throws ParseException {
		
		FechaHora inicio, fin;
		
		inicio = new FechaHora("12/07/2021 00:00:00");
		
		fin = new FechaHora("31/12/2021 00:00:00");
		
		FechaHora hoy = new FechaHora();
		
		System.out.println(hoy.despues(inicio) && hoy.antes(fin));
		
	}
}
