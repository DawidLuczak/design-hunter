package sample.workspace.drawingtools;

import java.util.concurrent.atomic.AtomicReference;

public class Point {
	private AtomicReference<Double> x, y;
	
	public Point(double x, double y){
		this.x = new AtomicReference<>(x);
		this.y = new AtomicReference<>(y);
	}
	
	public void swapX(Point p){
		this.x.set(p.x.getAndSet(this.x.get()));
	}
	
	public void swapY(Point p){
		this.y.set(p.y.getAndSet(this.y.get()));
	}
	
	public void swap(Point p) {
		swapX(p);
		swapY(p);
	}
	
	public double getX() {
		return x.get();
	}
	
	public void setX(double x) {
		this.x.set(x);
	}
	
	public double getY() {
		return y.get();
	}
	
	public void setY(double y) {
		this.y.set(y);
	}
	
	public double distanceX(Point p){
		return Math.abs(this.getX() - p.getX());
	}
	
	public double distanceY(Point p){
		return Math.abs(this.getY() - p.getY());
	}
	
	public double distance(Point p){
		return Math.sqrt(Math.pow(distanceX(p), 2) + Math.pow(distanceY(p), 2));
	}
	
	public double angle(Point p){
		double angle = (this.getX() * p.getX() + this.getY() * p.getY()) / Math.sqrt((Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) * (Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2))));
		return angle;
	}
	
}
