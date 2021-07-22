package com.firstproject.main;

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class GameObject implements Cloneable {

	public Position Position;
	protected ID id;
	public Position Vel=new Position(0,0,0);
	
	public GameObject(Position Position) {
		this.Position=Position;
		this.id = ID.Test;
	}
	
	public abstract void tick();
	public abstract void render(Graphics2D g);
	
	
	public void setId(ID id) {
		this.id=id;
	}
	
    public Object clone() throws
    CloneNotSupportedException 
    { 
    	return super.clone(); 
	} 
    
    
	
}
