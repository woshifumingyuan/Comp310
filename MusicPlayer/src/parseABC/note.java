package parseABC;

public class note {
	Character g;
	int sharp;
	int length;
	public note(Character g, int starp, int natural) {
		this.g=g;
		this.sharp = starp;
		this.length = natural;
	}
	public String toString() {
		return "(g,"+","+String.valueOf(sharp)+","+String.valueOf(natural)+")";
	}
}
