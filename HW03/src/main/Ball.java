package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class Ball implements IUpdateStrategy, Observer{
	Point location;
	int radius;
	Point velocity;
	Color color;
	Component container;
	IUpdateStrategy st;
	public void move() {
		location.x = location.x + velocity.x;
		location.y = location.y + velocity.y;
	}
	public void bounce() {
		velocity.x *= -1;
		velocity.y *= -1;
	}
	public void setLocation(Point p) {
		location = p;
	}
	public void setVelocity(Point v) {
		velocity = v;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void setRadius(int r) {
		radius = r;
	}
	public int getRadius() {
		return radius;
	}
	public Point getvelocity() {
		return velocity;
	}
	public Color getColor() {
		return color;
	}
	public IUpdateStrategy getStrategy() {
		return st;
	}
	public void setStrategy(IUpdateStrategy stategy) {
		
	}
	public void paint(Graphics g) {
		g.fillOval(location.x, location.y, radius, radius);
	}
	public Ball(Point p, Point v, Component continer, Color c, int r, IUpdateStrategy strategy) {
		location = p;
		velocity = v;
		container = continer;
		color = c;
		radius = r;
		st = strategy;
	}
	
	@Override
	public void update(Observable O, Object arg) {
		paint(g);
		move();
	}
	
	
}
