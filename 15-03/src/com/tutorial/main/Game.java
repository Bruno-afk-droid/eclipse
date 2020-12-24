package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1550691097823471818L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	public static boolean BUp = false;
	public static boolean BLeft = false;
	public static boolean BRight = false;
	public static boolean BDown = false;
	public static boolean BSpace = false;
	
	//test
	
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
			
			
			/*
			int[] X = {16,-16,-16,16,16,-16,-16,16};
			
			int[] Y=  {-16,-16,16,16,-16,-16,16,16};
			
			int[] Z=  {-8,-8,-8,-8,8,8,8,8};
			
			int[] L=  {4,4};
			*/
			
			int[] X = {-10,-11,-16,-16,-6 ,6  ,16 ,16,11,10,0,-10
					  ,-10,-11,-16,-16,-6 ,6  ,16 ,16,11,10,0,-10};
			
			int[] Y=  {7  ,1  ,-4 ,-16,-20,-20,-16,-4,1 ,7,11,7
					  ,7  ,1  ,-4 ,-16,-20,-20,-16,-4,1 ,7,11,7};
			
			int[] Z=  {-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20,-20
				       ,20,20,20,20,20,20,20,20,20,20,20,20};
			
			int[] L=  {12,X.length/2};
			 
		for(int i=0;i<1;i++)
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
		
		g.setColor(Color.WHITE);
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
		
		public static int MinIndex(int[] A) {
			int I=0;
			if(A.length==1)return 0;
			int M=A[1];
			for(int i=0;i<A.length;i++) {
				if(A[i]>M) {M=A[i];
				I=i;}
			}
			
			return I;
		}
		
		
		
		public static int MaxIndex(int[] A) {
			int I=0;
			if(A.length==1)return 0;
			int M=A[1];
			for(int i=0;i<A.length;i++) {
				if(A[i]>M) {M=A[i];
				I=i;}
			}
			
			return I;
		}
		
		public static int Max(int[] A) {
			if(A==null)return 0;
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
		
		public static int MinLIndex(LinkedList<Integer> tDnow) {
			int I=0;
			if(tDnow.size()==0)return 0;
			int M=tDnow.get(0);
			for(int i=0;i>tDnow.size();i++) {
				if(tDnow.get(i)>M) {M=tDnow.get(i); I=i;}
			}
			
			return I;
		}		
		
		public static int Max(ArrayList<Integer> A) {
			if(A.size()==0)return 0;
			int M=A.get(0);
			for(int i=0;i<A.size();i++) {
				if(A.get(i)>M)M=A.get(i);
			}
			
			return M;
		}
		
		public static int MaxLIndex(LinkedList<Integer> tDnow) {
			int I=0;
			if(tDnow.size()==0)return 0;
			int M=tDnow.get(0);
			for(int i=0;i<tDnow.size();i++) {
				if(tDnow.get(i)>M) {M=tDnow.get(i); I=i;}
			}
			
			return I;
		}		
		
		public static float getAngle(Point center,Point target) {
		    float angle = (float) (Math.atan2(target.y - center.y, target.x - center.x)* 180 / Math.PI);

		    if(angle < 0){
		        angle += 360;
		    }

		    return angle;
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
		
		
		public static int Avarage_Array(int[] j,int L) {
			int AV=0;
			for(int i=0;i<L;i++) {
			AV+=j[i];	
			}
			
			return AV/j.length;
		}
		
		public static double Avarage_Array(double[] j,int L) {
			int AV=0;
			for(int i=0;i<L;i++) {
			AV+=j[i];	
			}
			
			return AV/j.length;
		}
		
		public static int Avarage_ArrayList(LinkedList<Integer> tDnow) {
			int AV=0;
			for(int i=0;i<tDnow.size();i++) {
			AV+=tDnow.get(i);	
			}
			
			if(AV!=0)
			return AV/tDnow.size();
			else return 1;
		}
		
		public static int[] Array_to_ArrayList(LinkedList<Integer> A) {
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
			
			public static LinkedList<Polygon> sortOnDepth(LinkedList<Polygon> p,LinkedList<Integer> a){
				LinkedList<Polygon> Poly = new LinkedList<Polygon>();
				
				while(p.size()!=0) {
				int I = Game.MaxLIndex(a);
				Poly.add(p.get(I));
				a.remove(I);
				p.remove(I);
				}
				
				return Poly;		
			}
			
			public static LinkedList<int[]> sortOnDepthArray(LinkedList<int[]> p,LinkedList<Integer> b){
				LinkedList<int[]> Poly = new LinkedList<int[]>();
				
				while(p.size()!=0) {
				int I = Game.MaxLIndex(b);
				Poly.add(p.get(I));
				b.remove(I);
				p.remove(I);
				}
				
				return Poly;		
			}
			
			public static BufferedImage getBlankImage(int width, int height) {
			    return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
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
			  private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
			      BufferedImage dest = src.getSubimage(0, 0, rect.width, rect.height);
			      return dest; 
			   }
			
		public static Polygon outlineTetrahedron(LinkedList<Polygon> A) {
			Area AR = new Area();
			for(int i=0;i<A.size();i++) {
				AR.add(new Area(A.get(i)));
			}

		    Area a = new Area(AR);
		    PathIterator iterator = a.getPathIterator(null);
		    float[] floats = new float[6];
		    Polygon polygon = new Polygon();
		    while (!iterator.isDone()) {
		        int type = iterator.currentSegment(floats);
		        int x = (int) floats[0];
		        int y = (int) floats[1];
		        if(type != PathIterator.SEG_CLOSE) {
		            polygon.addPoint(x, y);
		            //System.out.println("adding x = " + x + ", y = " + y);
		        }
		        iterator.next();
		    }

			return polygon;
		}
		
		public static Area getTetrahedronArea(LinkedList<Polygon> A) {
			Area AR = new Area();
			for(int i=0;i<A.size();i++) {
				AR.add(new Area(A.get(i)));
			}

			return AR;
		}
		
		public void renderTetrahedron(LinkedList<Polygon> A,Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			for(int i=0;i<A.size();i++) {
			g2d.draw(A.get(i));
			}
		}

		public static Polygon RectangleToPolygon(Rectangle rect) {
			   Polygon result = new Polygon(); 
			   result.addPoint(rect.x, rect.y); 
			   result.addPoint(rect.x + rect.width, rect.y); 
			   result.addPoint(rect.x + rect.width, rect.y + rect.height); 
			   result.addPoint(rect.x, rect.y + rect.height);
			   return result; 
			} 
		public static BufferedImage ScaleBufferedimage(BufferedImage B,double W,double H) {
		BufferedImage before = B;
		int w = before.getWidth();
		int h = before.getHeight();
		BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(W, H);
		AffineTransformOp scaleOp = 
		   new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(before, after);
		return after;
		}
		
		public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
		    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

		    Graphics2D g2d = dimg.createGraphics();
		    g2d.drawImage(tmp, 0, 0, null);
		    g2d.dispose();

		    return dimg;
		}  
		
		public static LinkedList<Polygon> shortOnDepth(LinkedList<int[]> Depth,LinkedList<Polygon> P){
			int[] D = new int[Depth.size()];
			for(int i=0;i<Depth.size();i++) {
				D[i]=Avarage_Array(Depth.get(i),Depth.get(i).length);
			}
			
			
			LinkedList<Polygon> result= new LinkedList<Polygon>();
			Area A = new Area();
			
			while(D[MinIndex(D)]!=D[MaxIndex(D)]) {
			D[MinIndex(D)]=D[MaxIndex(D)];
			Area B = new Area(P.get(MinIndex(D)));
			B.subtract(A);
			
			if(!B.isEmpty())
			result.add(P.get(MinIndex(D)));
			}
			

			return result;	
		}
		public static double CheckAreaSize(Area A) {
			Rectangle B = A.getBounds();
			
			return B.getWidth()*B.getHeight();		
		}
		
		public static Polygon MovePolygon(Polygon P,int x,int y) {
			
			for(int i=0;i<P.npoints;i++) {
				P.xpoints[i]+=x;
				P.ypoints[i]+=y;
			}
			
			
			return P;
		}
}

	

class Pseudo3D
{
    static BufferedImage computeImage(
        BufferedImage image,
        Point2D p0, Point2D p1, Point2D p2, Point2D p3)
    {
        int w = image.getWidth();
        int h = image.getHeight();    
        
        BufferedImage result =
            new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Point2D ip0 = new Point2D.Double(0,0);
        Point2D ip1 = new Point2D.Double(0,h);
        Point2D ip2 = new Point2D.Double(w,h);
        Point2D ip3 = new Point2D.Double(w,0);

        Matrix3D m = computeProjectionMatrix(
            new Point2D[] {  p0,  p1,  p2,  p3 },
            new Point2D[] { ip0, ip1, ip2, ip3 });
        Matrix3D mInv = new Matrix3D(m);
        mInv.invert();

        for (int y = 0; y < h; y++)
        {
            for (int x = 0; x < w; x++)
            {
                Point2D p = new Point2D.Double(x,y);
                mInv.transform(p);
                int ix = (int)p.getX();
                int iy = (int)p.getY();
                if (ix >= 0 && ix < w && iy >= 0 && iy < h)
                {
                    int rgb = image.getRGB(ix, iy);
                    result.setRGB(x, y, rgb);
                }
            }
        }
        return result;
    }

    // From https://math.stackexchange.com/questions/296794
    private static Matrix3D computeProjectionMatrix(Point2D p0[], Point2D p1[])
    {
        Matrix3D m0 = computeProjectionMatrix(p0);
        Matrix3D m1 = computeProjectionMatrix(p1);
        m1.invert();
        m0.mul(m1);
        return m0;
    }

    // From https://math.stackexchange.com/questions/296794
    private static Matrix3D computeProjectionMatrix(Point2D p[])
    {
        Matrix3D m = new Matrix3D(
            p[0].getX(), p[1].getX(), p[2].getX(),
            p[0].getY(), p[1].getY(), p[2].getY(),
            1, 1, 1);
        Point3D p3 = new Point3D(p[3].getX(), p[3].getY(), 1);
        Matrix3D mInv = new Matrix3D(m);
        mInv.invert();
        mInv.transform(p3);
        m.m00 *= p3.x;
        m.m01 *= p3.y;
        m.m02 *= p3.z;
        m.m10 *= p3.x;
        m.m11 *= p3.y;
        m.m12 *= p3.z;
        m.m20 *= p3.x;
        m.m21 *= p3.y;
        m.m22 *= p3.z;
        return m;
    }

    private static class Point3D
    {
        double x;
        double y;
        double z;

        Point3D(double x, double y, double z)
        {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static class Matrix3D
    {
        double m00;
        double m01;
        double m02;
        double m10;
        double m11;
        double m12;
        double m20;
        double m21;
        double m22;

        Matrix3D(
            double m00, double m01, double m02,
            double m10, double m11, double m12,
            double m20, double m21, double m22)
        {
            this.m00 = m00;
            this.m01 = m01;
            this.m02 = m02;
            this.m10 = m10;
            this.m11 = m11;
            this.m12 = m12;
            this.m20 = m20;
            this.m21 = m21;
            this.m22 = m22;
        }

        Matrix3D(Matrix3D m)
        {
            this.m00 = m.m00;
            this.m01 = m.m01;
            this.m02 = m.m02;
            this.m10 = m.m10;
            this.m11 = m.m11;
            this.m12 = m.m12;
            this.m20 = m.m20;
            this.m21 = m.m21;
            this.m22 = m.m22;
        }

        // From http://www.dr-lex.be/random/matrix_inv.html
        void invert()
        {
            double invDet = 1.0 / determinant();
            double nm00 = m22 * m11 - m21 * m12;
            double nm01 = -(m22 * m01 - m21 * m02);
            double nm02 = m12 * m01 - m11 * m02;
            double nm10 = -(m22 * m10 - m20 * m12);
            double nm11 = m22 * m00 - m20 * m02;
            double nm12 = -(m12 * m00 - m10 * m02);
            double nm20 = m21 * m10 - m20 * m11;
            double nm21 = -(m21 * m00 - m20 * m01);
            double nm22 = m11 * m00 - m10 * m01;
            m00 = nm00 * invDet;
            m01 = nm01 * invDet;
            m02 = nm02 * invDet;
            m10 = nm10 * invDet;
            m11 = nm11 * invDet;
            m12 = nm12 * invDet;
            m20 = nm20 * invDet;
            m21 = nm21 * invDet;
            m22 = nm22 * invDet;
        }

        // From http://www.dr-lex.be/random/matrix_inv.html
        double determinant()
        {
            return
                m00 * (m11 * m22 - m12 * m21) +
                m01 * (m12 * m20 - m10 * m22) +
                m02 * (m10 * m21 - m11 * m20);
        }

        final void mul(double factor)
        {
            m00 *= factor;
            m01 *= factor;
            m02 *= factor;

            m10 *= factor;
            m11 *= factor;
            m12 *= factor;

            m20 *= factor;
            m21 *= factor;
            m22 *= factor;
        }

        void transform(Point3D p)
        {
            double x = m00 * p.x + m01 * p.y + m02 * p.z;
            double y = m10 * p.x + m11 * p.y + m12 * p.z;
            double z = m20 * p.x + m21 * p.y + m22 * p.z;
            p.x = x;
            p.y = y;
            p.z = z;
        }

        void transform(Point2D pp)
        {
            Point3D p = new Point3D(pp.getX(), pp.getY(), 1.0);
            transform(p);
            pp.setLocation(p.x / p.z, p.y / p.z);
        }

        void mul(Matrix3D m)
        {
            double nm00 = m00 * m.m00 + m01 * m.m10 + m02 * m.m20;
            double nm01 = m00 * m.m01 + m01 * m.m11 + m02 * m.m21;
            double nm02 = m00 * m.m02 + m01 * m.m12 + m02 * m.m22;

            double nm10 = m10 * m.m00 + m11 * m.m10 + m12 * m.m20;
            double nm11 = m10 * m.m01 + m11 * m.m11 + m12 * m.m21;
            double nm12 = m10 * m.m02 + m11 * m.m12 + m12 * m.m22;

            double nm20 = m20 * m.m00 + m21 * m.m10 + m22 * m.m20;
            double nm21 = m20 * m.m01 + m21 * m.m11 + m22 * m.m21;
            double nm22 = m20 * m.m02 + m21 * m.m12 + m22 * m.m22;

            m00 = nm00;
            m01 = nm01;
            m02 = nm02;
            m10 = nm10;
            m11 = nm11;
            m12 = nm12;
            m20 = nm20;
            m21 = nm21;
            m22 = nm22;
        }
    }
}


