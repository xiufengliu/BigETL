package dk.dtu.ccsd.facade.data.req;

/**
 * Person data for creating a new person.
 * 
 * @author Trey
 */
public class PersonRequest {

	private String userName;
	private String firstName;
	private String lastName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
