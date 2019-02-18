package model.vo;

import java.util.Date;

/**
 * Representation of a MovingViolation object
 */
public class VOMovingViolation implements Comparable<VOMovingViolation> {

	// TODO Definir los atributos de una infraccion
	private int objectId;

	private String location;

	private Date ticketIssueDate;

	private short totalPaid;

	private boolean accidentIndicator;

	private String violationDesc;

	/**
	 * Metodo constructor
	 */
	public VOMovingViolation( String pObjectId,String pLocation, String pTicketIssueDate, String pTotalpaid, String pAccidentIndicator, String pViolationDesc)
	{
		objectId =Integer.toString(pObjectId);
		location = pLocation;
		ticketIssueDate = null; // TODO fix
		totalPaid = 							

	}

	/**
	 * @return id - Identificador unico de la infraccion
	 */
	public int objectId() {
		// TODO Auto-generated method stub
		return objectId;
	}

	/**
	 * @return location - Direccion en formato de texto.
	 */
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	/**
	 * @return date - Fecha cuando se puso la infraccion .
	 */
	public Date getTicketIssueDate() {
		// TODO Auto-generated method stub
		return ticketIssueDate;
	}

	/**
	 * @return totalPaid - Cuanto dinero efectivamente paga el que recibio la
	 *         infraccion en USD.
	 */
	public int getTotalPaid() {
		// TODO Auto-generated method stub
		return totalPaid;
	}

	/**
	 * @return accidentIndicator - Si hubo un accidente o no.
	 */
	public Boolean getAccidentIndicator() {
		// TODO Auto-generated method stub
		return accidentIndicator;
	}

	/**
	 * @return description - Descripcion textual de la infraccion.
	 */
	public String getViolationDescription() {
		// TODO Auto-generated method stub
		return violationDesc;
	}

	@Override
	public int compareTo(VOMovingViolation o) {
		// TODO implementar la comparacion "natural" de la clase
		if (getTicketIssueDate().compareTo(o.getTicketIssueDate()) < 0) {
			return 1;
		} else if (getTicketIssueDate().compareTo(o.getTicketIssueDate()) > 0) {

			return -1;
		}else {
			if (objectId<o.objectId())
			{
				return -1;
			}
			else if (objectId>o.objectId())
			{
				return 1;
			}
			else return 0;
		}
	}

	public String toString() {
		// TODO Convertir objeto en String (representacion que se muestra en la consola)
		return "-";
	}
}
