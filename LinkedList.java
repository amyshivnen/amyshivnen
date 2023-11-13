package generics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Node;

import application.Student;

public class LinkedList<T> {
    private List<T> list;

    public LinkedList() {
        this.list = new ArrayList<>();
    }

    // Method to add a generic object to the end of the list if it doesn't already exist
    public void add(T obj) {
        if (!list.contains(obj)) {
            list.add(obj);
        }
    }

    // Method to add a generic object in the correct sorted position in the list
    public void add(T obj, int position) {
        list.add(position - 1, obj);
    }

    // 3. Delete any object from the list where the object is passed in as a parameter.
    public void delete(T obj) {
        list.remove(obj);
    }

    // 4. Display all object data in the list.
    public void display() {
        for (T obj : list) {
            System.out.println(obj);
        }
    }

    // 5. Return the first generic object in the list.
    public T getFirst() {
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    //6. Return the last generic object in the list.
    public T getLast() {
        if (!list.isEmpty()) {
            return list.get(list.size() - 1);
        }
        return null;
    }

    //7. Return the next generic object in the list(where the current object is input into the
    //method as a parameter). 
    public T getNext(T currentObj) {
        int index = list.indexOf(currentObj);
        if (index != -1 && index < list.size() - 1) {
            return list.get(index + 1);
        }
        return null;
    }

    //8. Check to see if the list is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }

    //9. Check to see if the list already contains a generic object input as a parameter and
    //returns a boolean. 
    public boolean contains(T obj) {
        return list.contains(obj);
    }

    //10. Custom function.Remove an object at a specific index in the list.
    public void removeAtIndex(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        } else {
            System.out.println("Invalid index. The index should be between 0 and " + (list.size() - 1));
        }
    }

	public Student remove() {
		return null;
	}

	public int size() {
		return 0;
	}

	public Student get(int insertIndex) {
		return null;
	}

	public Node getHead() {
		return null;
	}

	public Iterator<Student> iterator() {
		return null;
	}


}
