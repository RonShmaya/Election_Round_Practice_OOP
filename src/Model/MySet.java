package Model;

import Kind_Of_Persons.Person;

public class MySet<T extends Person> {
	public static final int SIZE_DEFULT = 2;
	private T[] arr;
	private int counter;

	public MySet(int sizeStrat) {
		if (sizeStrat <= 0) {
			arr = (T[]) new Person[SIZE_DEFULT];
		} else {
			arr = (T[]) new Person[sizeStrat];
		}
	}

	public MySet() {
		this(0);
	}

	public boolean contains(T ObjToAdd) {
		for (int i = 0; i < counter; i++) {
			if (arr[i].equalsbyId(ObjToAdd.getId())) {
				return true;
			}
		}
		return false;
	}

	public int getIndexPersonById(int id) {
		for (int i = 0; i < counter; i++) {
			if (arr[i].equalsbyId(id)) {
				return i;
			}
		}
		return -1;
	}

	public boolean add(T ObjToAdd) {
		if (contains(ObjToAdd)) {
			return false;
		}
		arr[counter] = ObjToAdd;
		counter++;
		if (counter == arr.length) {
			bigDataArray();
		}
		return true;
	}

	private void bigDataArray() {
		T[] copy = (T[]) new Person[arr.length * SIZE_DEFULT];
		System.arraycopy(arr, 0, copy, 0, arr.length);
		arr = copy;
	}

	public T get(int index) {
		if (index >= 0 && index < counter) {
			return arr[index];
		}
		return null;
	}

	public int size() {
		return counter;
	}

	public int capacity() {
		return arr.length;
	}

	public String toString() {
		StringBuffer back = new StringBuffer(arr.getClass().getSimpleName() + ": , ");
		for (int i = 0; i < counter; i++) {
			back.append(arr[i].toString() + "\n");
		}
		return back.toString();
	}

	public void clear(Person citizen) {
		if (arr[counter - 1].getId() == citizen.getId()) {
			arr[counter - 1] = null;
			counter--;
		}
	}

	public boolean isEmpty() {
		if (counter == 0) {
			return true;
		}
		return false;
	}
}