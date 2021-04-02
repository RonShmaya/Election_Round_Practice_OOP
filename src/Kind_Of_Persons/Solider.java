package Kind_Of_Persons;

import java.time.LocalDate;

import Exseptions.AgeException;
import Exseptions.IdException;
import Interface.Armyable;

public class Solider extends Person implements Armyable {
	public static final int ARMY_AGE_MAX = 21;

	private boolean isCarryWeapon;

	public Solider(String name, int id, int birthday, int myBallotId) throws IdException, AgeException {
		super(name, id, birthday, myBallotId);

	}

	public void carryWeapon(boolean ans) {
		isCarryWeapon = ans;
	}

	public String toString() {
		StringBuffer back = new StringBuffer(getClass().getSimpleName() + " " + super.toString() + ", ");
		if (isCarryWeapon) {
			back.append("He is carry weapon \n");
		} else {
			back.append("He is Not carry weapon \n");
		}
		return back.toString();
	}

	@Override
	public Solider clone() throws CloneNotSupportedException {
		return (Solider) super.clone();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Solider)) {
			return false;
		}
		return super.equals(other);
	}

}
