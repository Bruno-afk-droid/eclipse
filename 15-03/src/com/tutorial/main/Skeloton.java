package com.tutorial.main;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.tutorial.main.Skeloton.Bone;

class Skeloton {
	protected LinkedList<Bone> Bones = new LinkedList<Bone>();
	protected Bone[][] SkelotonMap;
	protected int x;
	protected int y;
	protected int z;
	
	protected double Z_direction;
	protected double Y_direction;
	protected double X_direction;
	
	
	public Skeloton(){
		
	}
	
	public Skeloton(LinkedList<Bone> Bones) {
		
	}
	
	public void Draw(Graphics2D g) {
		for(int i=0;i<Bones.size();i++) {
			Bones.get(i).Draw(g);
		}
		
	}
	
	public void update() {
		
		for(int i=0;i<Bones.size();i++) 
		Bones.get(i).update();
		
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
	
	public Bone[][] convertToSkelotonMap(LinkedList<Bone>Bones){
		
		return SkelotonMap;
	}
	
	class Bone { 
		protected Wireframe3D casing=null;
		protected Bone Owner=null;
		
		
		protected int x;
		protected int y;
		protected int z;
		
		public int Avg_X;
		public int Avg_Y;
		public int Avg_depth;
		
		protected int npx;
		protected int npy;
		protected int npz;
		
		protected double Z_direction;
		protected double Y_direction;
		protected double X_direction;
		
		
		protected LinkedList<Bone> StackedOn = new LinkedList<Bone>();
		
		public Bone(int x,int y,int z,double Z_direction,double Y_direction,double X_direction,Bone Owner,LinkedList<Bone> StackedOn,Wireframe3D casing) {
			this.npx=x-Owner.x;
			this.npy=y-Owner.y;
			this.npz=z-Owner.z;
						
			this.Z_direction=Z_direction;
			this.Y_direction=Y_direction;
			this.X_direction=X_direction;
			
			this.casing=casing;
			this.StackedOn=StackedOn;
			
			update();
		}
		
		public Bone(int x,int y,int z,double Z_direction,double Y_direction,double X_direction,LinkedList<Bone> StackedOn,Wireframe3D casing) {
			this.x=x;
			this.y=y;
			this.z=z;
			
			this.Z_direction=Z_direction;
			this.Y_direction=Y_direction;
			this.X_direction=X_direction;
			
			this.casing=casing;
			this.StackedOn=StackedOn;
			update();
		}
		
		public void Draw(Graphics2D g) {
			if(casing!=null) {
			
			casing.draw(g);
			}
		}
		
		public void update() {
			
			if(Owner!=null) {
				this.x=Owner.x+npx;
				this.y=Owner.y+npy;
				this.z=Owner.z+npz;
				
				Point pt = (Game.rotatePoint(new Point(x, z),new Point(Owner.x,Owner.z),Owner.Y_direction));
				x=pt.x;
				z=pt.y;
				pt = (Game.rotatePoint(new Point(y, z),new Point(Owner.y,Owner.z),Owner.X_direction));
				y=pt.x;
				z=pt.y;
				pt = (Game.rotatePoint(new Point(x, y),new Point(Owner.x,Owner.y),Owner.Z_direction));
				x=pt.x;
				y=pt.y;
				
			}
			
			if(casing!=null) {
			casing.x=x;
			casing.y=y;
			casing.z=z;
			casing.Z_direction=Z_direction;
			casing.Y_direction=Y_direction;
			casing.X_direction=X_direction;
			casing.update();
			Avg_X=casing.Avg_X;
			Avg_Y=casing.Avg_Y;
			Avg_depth=casing.Avg_depth;
			}
			
			
			
			for(int i=0;i<StackedOn.size();i++) 
			StackedOn.get(i).update();	
			
			
		}
		
		public void Turn(double Z_direction,double Y_direction,double X_direction) {
			this.Z_direction=Z_direction;
			this.Y_direction=Y_direction;
			this.X_direction=X_direction;
			update();
		}
	}

	
}
