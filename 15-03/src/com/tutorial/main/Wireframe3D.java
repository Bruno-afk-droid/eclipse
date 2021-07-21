package com.tutorial.main;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

class Wireframe3D {

	protected int x;
	protected int y;
	protected int z;
	
	public int Avg_X;
	public int Avg_Y;
	public int Avg_depth;
	
	protected double Z_direction;
	protected double Y_direction;
	protected double X_direction;
	
	protected LinkedList<Polygon3D> Tetradon;
	protected LinkedList<Polygon3D> N;
	protected LinkedList<Polygon3D> V;
	protected LinkedList<BufferedImage> Texturemap;
	
	
	protected Area Hitbox;

	public Wireframe3D(int x,int y,int z,LinkedList<Polygon> Tetradon,LinkedList<int[]> Depth,LinkedList<BufferedImage> Texturemap) {
		this.x=x;
		this.y=y;
		this.z=z;
		this.Tetradon=convertToTetrodon(Tetradon, Depth);
		this.N=(LinkedList<Polygon3D>) this.Tetradon.clone();
		this.V=(LinkedList<Polygon3D>) this.Tetradon.clone();
		this.Texturemap=Texturemap;
		
		update();
	}
	
	public Wireframe3D(int x,int y,int z,LinkedList<Polygon3D> Tetradon,LinkedList<BufferedImage> Texturemap) {
		this.x=x;
		this.y=y;
		this.z=z;
		
		this.Tetradon=Tetradon;
		for(int i=0;i<this.Tetradon.size();i++)
		this.Tetradon.get(i).set(x, y, z);
			
		this.N=(LinkedList<Polygon3D>) this.Tetradon.clone();
		this.Texturemap= new LinkedList<BufferedImage>();
		for(int i=0;i<Texturemap.size();i++)
		this.Texturemap.add(Game.resize(Texturemap.get(i),(int)(Texturemap.get(i).getWidth()*1.5),(int)(Texturemap.get(i).getHeight()*1.5)));
		
		this.V=Tetradon;
		update();
	}
	
	public Wireframe3D() {
	}

	public void draw(Graphics2D g) {
		
		for(int i=0;i<Texturemap.size();i++) {
		Tetradon.get(i).draw(Texturemap.get(Tetradon.get(i).index), g);
		}
	}
	
	public void SetCoordinates(int x,int y) {
		this.x=x;
		this.y=y;
		update();
	}
	
	public void Turn(double Z_direction, double Y_direction,double X_direction) {
		this.Z_direction=Z_direction;
		this.Y_direction=Y_direction;
		this.X_direction=X_direction;
		update();
	}
	
	public void update() {
		Hitbox = new Area();
		int[] Avg_X = new int[this.N.size()];
		int[] Avg_Y = new int[this.N.size()];
		int[] Avg_depth = new int[this.N.size()];
		
		for(int i=0;i<this.N.size();i++) {
			Tetradon.set(i,N.get(i).TurnPolygon3D(this.Z_direction, this.Y_direction, this.X_direction));
			get(i).Move(this.x, this.y, this.z);
			get(i).update();
			
			Avg_X[i] = get(i).Avg_X;
			Avg_Y[i] = get(i).Avg_Y;
			Avg_depth[i] = get(i).Avg_depth;
			this.Hitbox.add(getArea(i));			
		}
		this.Avg_X=Game.Avarage_Array(Avg_X, this.N.size());
		this.Avg_Y=Game.Avarage_Array(Avg_Y, this.N.size());
		this.Avg_depth=Game.Avarage_Array(Avg_X, this.N.size());
		Tetradon = sortOnDepth(Tetradon);
	}
	
	public Rectangle getBounds() {
		Rectangle result = new Rectangle();
		for(int i=0;i<size();i++) 
		result.union(get(i).getBounds());
		
		return result;
	}
	
	public Area getArea() {
		return Hitbox;
	}
	
	public static LinkedList<Polygon3D> sortOnDepth(LinkedList<Polygon3D> polygons){
		List<Polygon3D> polygonsList = new ArrayList<Polygon3D>();
		
		for(Polygon3D poly: polygons) {
			polygonsList.add(poly);
		}
		
		Collections.sort(polygonsList,new Comparator<Polygon3D>() {
		    @Override
		    public int compare(Polygon3D o1, Polygon3D o2) {
		        return o2.Avg_depth - o1.Avg_depth < 0 ? 1 : -1;
		    }
		});		
		
		for(int i=0;i<polygons.size();i++) {
			polygons.set(i, polygonsList.get(i));
		}
		
		return polygons;
	}
	
	public LinkedList<DrawFrame> DrawView(){
		Area A = new Area();
		for(int i=0;i<Tetradon.size();i++) {
			
		}
		
		
		return null;
		
		
		
	}
	
	public Area getAreaCopy() {
		return new Area(Hitbox);
	}
	
	public int size() {	
		return Tetradon.size();
	}
	
	public Area getArea(int i) {
		return Tetradon.get(i).getArea();
	}
	
	public Polygon3D get(int i) {
		return Tetradon.get(i);
	}
	
	public Polygon3D getcopy(int i) {
		return new Polygon3D(get(i));	
	}
	
	private LinkedList<Polygon3D> convertToTetrodon(LinkedList<Polygon> T,LinkedList<int[]> Depth){
		LinkedList<Polygon3D> result = new LinkedList<Polygon3D>();
			for(int i=0;i<T.size();i++) {
				
				result.add(new Polygon3D(x, y, z, T.get(i).xpoints, T.get(i).ypoints, Depth.get(i), Depth.get(i).length,i));
			}
		
		return result;	
	}
		
	
}