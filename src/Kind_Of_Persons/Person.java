package Kind_Of_Persons;

import java.net.InterfaceAddress;
import java.time.LocalDate;

import Exseptions.AgeException;
import Exseptions.IdException;
import Model.Ballot;

public abstract class Person implements Cloneable {
	public static final int AGE = 18;
	public static final int MIN_BIRTHDAY_INPUT = 1900;
	protected String name;
	protected int id;
	protected int birthday;
	protected int myBallotId;

	public Person(String name, int id, int birthday, int myBallotId) throws IdException, AgeException {// full
		setName(name);
		setId(id);
		setMyBallotId(myBallotId);
		setBirthday(birthday);
	}

	public void setBirthday(int birthday) throws AgeException {
		LocalDate date = LocalDate.now();
		int checkIfAge18 = date.getYear() - birthday;
		if (checkIfAge18 > AGE && birthday >= MIN_BIRTHDAY_INPUT) {
			this.birthday = birthday;
		} else {
			throw new AgeException(birthday);
		}
	}

	public void setId(int id) throws IdException {
		String check = String.valueOf(id);
		if (id > 0 && check.length() == 9) {
			this.id = id;
		} else {
			throw new IdException();
		}
	}

	public boolean setName(String name) {
		if (name != null && name != "") {
			this.name = name;
			return true;
		}
		this.name = "name_not_enter";
		return false;
	}

	public boolean setMyBallotId(int myBallotId) {
		if (myBallotId >= 0 && myBallotId < Ballot.idBollat) {
			this.myBallotId = myBallotId;
			return true;
		}
		this.myBallotId = -1;
		return false;
	}

	public int getId() {
		return id;
	}

	public int getbirthday() {
		return birthday;
	}

	public int getBallotId() {
		return myBallotId;
	}

	public String getName() {
		return this.name;
	}

	public boolean equals(Object other) {// override
		if (!(other instanceof Person)) {
			return false;
		}
		Person otherCitizenForShore = (Person) other;
		if (otherCitizenForShore.id != this.id) {
			return false;
		} else {
			return true;
		}
	}

	public boolean equalsbyId(int id) {// check citizen by id
		if (this.id == id) {
			return true;
		}
		return false;
	}

	public String toString() {
		String back = "Name: " + name + ", id: " + id + ", birthday: " + birthday + ", ballot: " + myBallotId;
		return back;
	}
	@Override
	public Person clone() throws CloneNotSupportedException {
		return (Person) super.clone();
	}
}
