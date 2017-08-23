package util;

public class SineMaker {
	private double _mid;
	private double _ampl;
	private double _delta;
	private double _theta = -Math.PI/2.0;
	public SineMaker(double min, double max, double delta) {
		_mid = (max+min)/2.0;
		_ampl = (max-min)/2.0;
		_delta = delta;
	}
	public double getDblVal() {
		double result = _mid + _ampl*Math.sin(_theta);
		_theta += _delta;
		return result;
	}
	public int getIntVal() {
		return (int)Math.round(getIntVal());
	}
}
