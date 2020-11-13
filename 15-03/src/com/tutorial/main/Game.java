package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	public static boolean BUp = false;
	public static boolean BLeft = false;
	public static boolean BRight = false;
	public static boolean BDown = false;
	public static boolean BSpace = false;
	
	private Thread thread;
	private boolean running = false;

	public static Handler handler;
	private HUD hud;
	
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH,  HEIGHT, "let's Build a Game!",this);
		
		hud = new HUD();
		
		

		int[] pX = {WIDTH/2*-1,WIDTH/4*-1,0,WIDTH/4,WIDTH/2,WIDTH/2*-1};
		int[] pY = {0,-128,-64,-128,0,0};
		
			handler.addObject(new Ground(WIDTH/2, HEIGHT/3*2,pX, pY, null, null, ID.Ground, 0, 0, 0, handler));
			
			

			int[] X = {-10,-11,-16,-16,-6 ,6  ,16 ,16,11,10,0,-10
					  ,-10,-11,-16,-16,-6 ,6  ,16 ,16,11,10,0,-10};
			
			int[] Y=  {7  ,1  ,-4 ,-16,-20,-20,-16,-4,1 ,7,11,7
					  ,7  ,1  ,-4 ,-16,-20,-20,-16,-4,1 ,7,11,7};
			
			int[] Z=  {-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5
				       ,5,5,5,5,5,5,5,5,5,5,5,5};
			
			int[] L=  {12,X.length/2};
 
			/*
			int[] X = {-10,-11,-16,-16,-6 ,6  ,16 ,16,11,10,0,-10
 						,-10,-11,-16,-16,-6 ,6  ,16 ,16,11,10,0,-10};
			int[] Y=  {7  ,1  ,-4 ,-16,-20,-20,-16,-4,1 ,7,11,7,
						7  ,1  ,-4 ,-16,-20,-20,-16,-4,1 ,7,11,7};
			int[] Z=  {-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,-5,
					   5,5,5,5,5,5,5,5,5,5,5,5};
			int[] L=  {X.length/2,X.length/2};
			 */

		handler.addObject(new Player(WIDTH/2-64-1, HEIGHT/2-64,X,Y, Z, L, ID.Player, 0, 0, 0, handler));

		 
		
		

	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		
		
		
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTricks = 60.0;
		double ns = 1000000000 / amountOfTricks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: "+frames);
				frames = 0;
			}
			
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		hud.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
		public static int clamp(int var, int min, int max) {
			if(var >= max)
				return var = max;
			else if(var <= min)
				return var = min;
			else return var;
		}
		
		public static double clamp(double var, double min, double max) {
			if(var >= max)
				return var = max;
			else if(var <= min)
				return var = min;
			else return var;
		}
		
		public static double split(double var, double min, double max) {
			double midle = (max+min)/2;
			 	
			if (var >=  midle) {
			if (var >=  min)
			return min; }else {
			if (var <=  midle) 
			if (var <=  max)
			return max; }
			return var;
		}
		
		public static int sign(int var) {
			return clamp(var,-1,1);
		}
		
		public static double sign(double var) {
			return clamp(var,-1,1);
		}
	
		public static void main(String args[]) {
			new Game();
		}

		public static Point rotatePoint(Point pt, Point center, double angleDeg) {
	        // http://en.wikipedia.org/wiki/Rotation_matrix
	        
	        double angleRad = Math.toRadians(angleDeg);
	        double cosThetha = Math.cos(angleRad); //The angle COS
	        double sinThetha = Math.sin(angleRad); //The angle SIN
	        double dx = (pt.x - center.x); //Difference (Point in transformed to origo)
	        double dy = (pt.y - center.y); //Difference -- || --
	 
	        int ptX = center.x + (int) (dx * cosThetha - dy * sinThetha);
	        int ptY = center.y + (int) (dx * sinThetha + dy * cosThetha);
	 
	        return new Point(ptX, ptY);
	    }
		
		public static Point lengtDir(double D,int L) {
			return rotatePoint(new Point(0,L),new Point(0,0),D);
		}
		
		
		public static int Min(int[] A) {
			if(A==null)return 0;
			int M=A[1];
			for(int i=0;i<A.length;i++) {
				if(A[i]<M)M=A[i];
			}
			
			return M;
		}
		
		public static int Max(int[] A) {
			if(A.length==0)return 0;
			int M=A[1];
			for(int i=0;i<A.length;i++) {
				if(A[i]>M)M=A[i];
			}
			
			return M;
		}
		
		public static int Min(ArrayList<Integer> A) {
			if(A.size()==0)return 0;
			int M=A.get(0);
			for(int i=0;i<A.size();i++) {
				if(A.get(i)<M)M=A.get(i);
			}
			
			return M;
		}
		
		public static int Max(ArrayList<Integer> A) {
			if(A.size()==0)return 0;
			int M=A.get(0);
			for(int i=0;i<A.size();i++) {
				if(A.get(i)>M)M=A.get(i);
			}
			
			return M;
		}
		
		
		public static float getAngle(Point cor,Point target) {
		    return (float) Math.toDegrees(Math.atan2(target.x - cor.x, target.y - cor.y));
		}
		
		

		public static double Deg(double D) {
			while(D>=360)D-=360;
			while(D<0)D+=360;
			return D;
		}
		
		public static double Deg(float D) {
			while(D>360)D-=360;
			while(D<0)D+=360;
			return D;
		}
		
		public static int Avarage_Array(int[] j) {
			int AV=0;
			for(int i=0;i<j.length;i++) {
			AV+=j[i];	
			}
			
			return AV/j.length;
		}
		
		public static int Avarage_ArrayList(ArrayList<Integer> A) {
			int AV=0;
			for(int i=0;i<A.size();i++) {
			AV+=A.get(i);	
			}
			
			if(AV!=0)
			return AV/A.size();
			else return 0;
		}
		
		public static int[] Array_to_ArrayList(ArrayList<Integer> A) {
			int[] R = new int[A.size()];
			for(int i=0;i<A.size();i++) {
				R[i]=A.get(i);
			}
			
			return R;
		}
		public static ArrayList<Integer> ArrayList_to_Array(int[] A){
			ArrayList<Integer> R = new ArrayList<Integer>();
			for(int i=0;i<A.length;i++)
			R.add(A[i]);
			return R;
		}
		
		public static int[] Array_Clamp(int[] A,int B,int E) {
			int[] R= new int[E];
			for(int i=B;i<E;i++) {
				R[i]=A[i];
			}
			return R;
		}
		
		public static boolean inDeg(double D,double A, double B) {
			if(B<A)
			return(((D>=A)&&(D<=360))||(D>=0)&&(D<=B)); else
			if(B>A)
			return((D>=A)&&(D<=B)); 
			return((A==D)&&(B==D));

		}
		
		static double distance(int x1, int y1, int x2, int y2) 
		{ 
		    // Calculating distance 
		    return Math.sqrt(Math.pow(x2 - x1, 2) +  
		                Math.pow(y2 - y1, 2) * 1.0); 
		}

		static boolean inList(ArrayList<GameObject> A,GameObject B) {
			for(int i=0;i<A.size();i++)	{
			if(A.get(i)==B)
			return true;
			}
			return false;
		}
			static boolean inArray(int [] A,int B) {
				int T=0;
				for(int i=0;i<A.length;i++)	{
				T+=A[i];
				if(T==B)
				return true;				
				}
			return false;
		}
			/*
    Area a = new Area(new Rectangle(1, 1, 5, 5));
    PathIterator iterator = a.getPathIterator(null);
    float[] floats = new float[6];
    Polygon polygon = new Polygon();
    while (!iterator.isDone()) {
        int type = iterator.currentSegment(floats);
        int X = (int) floats[0];
        int X = (int) floats[1];
        if(type != PathIterator.SEG_CLOSE) {
            polygon.addPoint(X, X);
            System.out.println("adding x = " + X + ", y = " + Y);
        }
        iterator.next();
    }
    }
			 */
			/*
			Area AR = new Area();
			
			for(int i=0;i<A.size();i++) {
			AR.add(new Area(A.get(i)));
			}
			
			Polygon result = new Polygon();
			for(int i=0;i<A.size();i++) {
				Polygon Pol=A.get(i);
				
				
				for(int j=0;j<Pol.npoints;j++) {

						result.addPoint(Pol.xpoints[j], Pol.ypoints[j]);
				}
				
			}
			
			return result;
			 */
		public static Polygon outlineTetrahedron(LinkedList<Polygon> A) {
			Area AR = new Area();
			
			for(int i=0;i<A.size();i++) {
			AR.add(new Area(A.get(i)));
			}
			
		
		    PathIterator iterator = AR.getPathIterator(null);
		    float[] floats = new float[6];
		    Polygon result = new Polygon();
		    while (!iterator.isDone()) {
		        int type = iterator.currentSegment(floats);
		        int X = (int) floats[0];
		        int Y = (int) floats[1];
		        if(type != PathIterator.SEG_CLOSE) {
		            result.addPoint(X, Y);
		            //System.out.println("adding x = " + X + ", y = " + Y);
		        }
		        iterator.next();
		    }
			return result;
		}
		public void renderTetrahedron(LinkedList<Polygon> A,Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			for(int i=0;i<A.size();i++) {
			g2d.draw(A.get(i));
			}
		}


}
