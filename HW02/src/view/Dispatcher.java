package view;
import java.util.*;

public class Dispatcher extends Observable {
	public void notifyAll(Object param) {
		setChanged();
		notifyObservers(param);
	}
}
