package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Number {
	@Id
	private int year;
	private long number;
	
	public Number() {
		number = 1;
		year = new FechaHora().getYear();
	}
	
	public long number() {
		return this.number;
	}
	
	public int year() {
		return this.year;
	}
	
	public String nextYearId() {
		return number + "-" + year;
	}
	
	public void increase() {
		this.number++;
	}

	private long getNumber() {
		return number;
	}

	private void setNumber(long number) {
		this.number = number;
	}

	private int getYear() {
		return year;
	}

	private void setYear(int year) {
		this.year = year;
	}
	
	
	
}
