package model.vo;

<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;
=======
>>>>>>> fb27d3b250bd8f6a58566a2209de33d52d771446
import java.util.Date;

/**
 * Representation of a MovingViolation object
 */
public class VOMovingViolation implements Comparable<VOMovingViolation> {

<<<<<<< HEAD
  // TODO Definir los atributos de una infraccion
  //Atributos
  //----------------------------------------------------------------
  private int object_ID;
  private String location;
  private Date ticketDate;
  private String violationDesc;
  private boolean accidentIndicator;
  private int totalPaid;

  /**
   * Constructor de un objeto de infraccion
   *
   * @param pObject_ID id de la infraccion (valor unico)
   * @param pLocation ubicacion de la infraccion
   * @param pTicketDate fecha de la infraccion
   * @param pViolationDesc descripcion de la violacion
   * @param accidentIndicator idica si la infraccion se dio por accidente o no
   */
  public VOMovingViolation(String pObject_ID, String pLocation, String pTicketDate,
      String pViolationDesc, String accidentIndicator, String totalPaid) {
    object_ID = Integer.parseInt(pObject_ID);
    location = pLocation;
    try {
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      ticketDate = df.parse(pTicketDate.substring(0, 10));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    violationDesc = pViolationDesc;
    this.accidentIndicator = accidentIndicator.equals("Yes");
    this.totalPaid = Integer.valueOf(totalPaid);
  }

  /**
   * @return id - Identificador unico de la infraccion
   */
  public int objectId() {
    // TODO Auto-generated method stub
    return object_ID;
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
    return ticketDate;
  }

  /**
   * @return totalPaid - Cuanto dinero efectivamente paga el que recibio la infraccion en USD.
   */
  public int getTotalPaid() {
    // TODO Auto-generated method stub
    return totalPaid;
  }

  /**
   * @return accidentIndicator - Si hubo un accidente o no.
   */
  public boolean getAccidentIndicator() {
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
    int ans = 0;

    if (ticketDate.compareTo(o.getTicketIssueDate()) > 0) {
      ans = 1;
    } else if (ticketDate.compareTo(o.getTicketIssueDate()) < 0) {
      ans = -1;
    } else {
      if (object_ID > o.object_ID) {
        ans = 1;
      } else if (object_ID < o.object_ID) {
        ans = -1;
      }
    }

    return ans;
  }

  public String toString() {
    // TODO Convertir objeto en String (representacion que se muestra en la consola)
    return String
        .format("id: %s, location: %s, date: %s, paid: %s, description: %s", object_ID, location,
            ticketDate, totalPaid, violationDesc);
  }
=======
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
		objectId =Integer.parseInt(pObjectId);
		location = pLocation;
		ticketIssueDate = null; 
		totalPaid = Short.parseShort(pTotalpaid);						

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
>>>>>>> fb27d3b250bd8f6a58566a2209de33d52d771446
}
