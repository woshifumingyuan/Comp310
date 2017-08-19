package solution;

import java.util.Observable;

public class Dispatcher extends Observable{
	public void notifyAll(Object arg){
		setChanged();
		notifyObservers(arg);
	}	 
}
