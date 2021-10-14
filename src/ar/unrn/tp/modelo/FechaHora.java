package ar.unrn.tp.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FechaHora implements Comparable<FechaHora> {
	@Id
	@GeneratedValue
	private long id;
	private transient static final String FORMATOFECHA = "dd/MM/yyyy";
	private transient static final String FORMATOFECHAHORA = "dd/MM/yyyy HH:mm";
	private java.sql.Timestamp fechaHora;
	
	/**
	 * Crea una FechaHora con la fecha y hora actuales
	 */
	public FechaHora() {
		this.fechaHora = java.sql.Timestamp.valueOf((Instant.ofEpochMilli(new Date().getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime()));
	}
	
	/**
	 * Toma un string con el formato "dd/MM/yyyy HH:mm" y devuelve la FechaHora correspondiente
	 * @param fechaHora
	 * @throws ParseException
	 */
	public FechaHora(String fechaHora) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATOFECHAHORA);
		Date fechaTemp = sdf.parse(fechaHora);
		this.fechaHora = java.sql.Timestamp.valueOf(Instant.ofEpochMilli(fechaTemp.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime());
	}
	
	public FechaHora(LocalDateTime fechaHora) {
		this.fechaHora = java.sql.Timestamp.from(fechaHora.toInstant(OffsetDateTime.now().getOffset()));
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMATOFECHA);
		return sdf.format(fechaHora);
	}
	
	public boolean antes(FechaHora otraFechaHora) {
		return this.fechaHora.before(otraFechaHora.fechaHora);
	}
	
	public boolean despues(FechaHora otraFechaHora) {
		return this.fechaHora.after(otraFechaHora.fechaHora);
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

	private java.sql.Timestamp getFechaHora() {
		return fechaHora;
	}

	private void setFechaHora(java.sql.Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	private long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	private static String getFormatofecha() {
		return FORMATOFECHA;
	}

	private static String getFormatofechahora() {
		return FORMATOFECHAHORA;
	}
	
	
}
