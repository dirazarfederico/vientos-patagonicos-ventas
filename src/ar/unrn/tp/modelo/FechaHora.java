package ar.unrn.tp.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class FechaHora implements Comparable<FechaHora> {
	private static final String FORMATOFECHA = "dd/MM/yyyy";
	private static final String FORMATOFECHAHORA = "dd/MM/yyyy HH:mm";
	private LocalDateTime fechaHora;
	
	/**
	 * Crea una FechaHora con la fecha y hora actuales
	 */
	public FechaHora() {
		this.fechaHora = Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	/**
	 * Toma un string con el formato "dd/MM/yyyy HH:mm" y devuelve la FechaHora correspondiente
	 * @param fechaHora
	 * @throws ParseException
	 */
	public FechaHora(String fechaHora) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATOFECHAHORA);
		Date fechaTemp = sdf.parse(fechaHora);
		this.fechaHora = Instant.ofEpochMilli(fechaTemp.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATOFECHA);
		return sdf.format(fechaHora);
	}
	
	public boolean antes(FechaHora otraFechaHora) {
		Date fecha, otraFecha;
		fecha = Date.from(this.fechaHora.atZone(ZoneId.systemDefault()).toInstant());
		otraFecha = Date.from(otraFechaHora.fechaHora.atZone(ZoneId.systemDefault()).toInstant());
		
		return fecha.before(otraFecha);
	}
	
	public boolean despues(FechaHora otraFechaHora) {
		Date fecha, otraFecha;
		fecha = Date.from(this.fechaHora.atZone(ZoneId.systemDefault()).toInstant());
		otraFecha = Date.from(otraFechaHora.fechaHora.atZone(ZoneId.systemDefault()).toInstant());
		
		return fecha.after(otraFecha);
	}

	@Override
	public int compareTo(FechaHora otraFechaHora) {
		
		if(this.antes(otraFechaHora))
			return -1;
		else if(this.despues(otraFechaHora))
			return 1;
		else
			return 0;
	}
}
