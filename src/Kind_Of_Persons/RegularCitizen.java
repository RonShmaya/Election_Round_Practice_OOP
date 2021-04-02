package Kind_Of_Persons;

import Exseptions.AgeException;
import Exseptions.IdException;

public class RegularCitizen extends Person {

	public RegularCitizen(String name, int id, int birthday, int myBallotId) throws IdException, AgeException {
		super(name, id, birthday, myBallotId);
	}

	@Override
	public RegularCitizen clone() throws CloneNotSupportedException {
		return (RegularCitizen) super.clone();
	}

	public boolean equals(Object other) {// override
		if (!(other instanceof RegularCitizen)) {
			return false;
		}
		return super.equals(other);
	}

	public String toString() {
		StringBuffer back = new StringBuffer(getClass().getSimpleName() + " " + super.toString() + "\n");
		return back.toString();
	}
}
