package main;

import java.awt.Rectangle;

import javax.swing.Timer;

import util.*;

public class BallModel implements IStrategyFac{
	SwitcherStrategy switcher = new SwitcherStrategy()
	IRandomizer rand = IRandomizer.Singleton
	int MaxR = 20;
	int MinR = 5;
	int MaxSpeed = 20;
	Rectangle Maxvel = new Rectangle(MaxSpeed, MaxSpeed);
	int timeSlice = 50;
	Timer timer = new Timer(timeSlice, (e)->{
		
	});
	IViewAdapter viewAdpt = IViewAdapter.NULL;
	IViewUpdateAdapter viewUpdateAdpt = IViewUpdateAdapter.NULL;
	
	
	public void start() {
		
	}
	
	public void clearBalls() {
		
	}
	
	public IStrategyFac makeStrategyFac(final String classname) {
		if(null == classname) return _errorStrategyFac;
		return new IStrategyFac() {

			@Override
			public IUpdateStrategy make() {
				// TODO Auto-generated method stub
				return loadStrategy(fixName(classname));
			}
			
			public String toString() {
				return classname;
			}
		};
	}
	
	public SwitcherStrategy getSwitcherStrategy() {
		
	}
	public BallModel(IViewAdapter viewadp, IviewUpdateAdapter viewupdateAdpt) {
		viewAdpt = viewadp;
		viewUpdateAdpt = viewupdateAdpt;
	}
	public void loadBall(IUpdateStrategy strategy) {
		
	}
	public IStrategyFac combineStrategyFacs(final IStrategyFac stratFac1, final IStrategyFac stratFac2) {
		if(null == stratFac1 || null == stratFac2) return _errorStrategyFac;
		return new IStrategyFac() {

			@Override
			public IUpdateStrategy make() {
				return new MultiStrategy(stratFac1.make(), stratFac2.make());
			}
			public String toString() {
				return stratFac1.toString() +"-"+ stratFac2.toString();
			}
		};
	}
	public void switchSwitcherStrategy(IUpdateStrategy strat) {
		
	}
	public void update(Object g) {
		
	}
	public String fixName(Object classname) {
		return classname.toString();
	}
}
