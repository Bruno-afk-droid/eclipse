package com.firstproject.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.util.LinkedList;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -526359079235965552L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;

	public static Input Input;
	public static MouseInput MouseInput;
	public static int FPS_now = 0;

	public static Handler handler;
	public static LinkedList<JButton> BUTTONS = new LinkedList<JButton>();
	public static LinkedList<JSpinner> SPINNERS = new LinkedList<JSpinner>();
	public static LinkedList<JSlider> SLIDERS = new LinkedList<JSlider>();
	public static LinkedList<JComboBox> JCOMBOBOX = new LinkedList<JComboBox>();
	public static LinkedList<JTextArea> JTEXTAREA = new LinkedList<JTextArea>();
	public static Window Window;

	// Container content = aWindow.getContentPane();
	public Game() {
		handler = new Handler();
		AnimationEditor.Setup.All();
		Input = new Input(handler);
		MouseInput = new MouseInput();
		this.addKeyListener(Input);
		this.addMouseListener(MouseInput);
		this.addMouseMotionListener(MouseInput);

		Window = new Window(WIDTH, HEIGHT, "NAME", this);

		double H = 15;
		Skeloton S = new Skeloton(new Position(0, 0, 0), new Angle3D(),
				new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 0), 0, H, new Bone[] {
						// torso
						new Bone(new Position(0, 0, 0), new Angle3D(10, 0, -90), 9, H, new Bone[] { new Bone(
								new Position(0, 0, 0), new Angle3D(-1, 0, -90), 13, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(-5, 0, -90), 11, H,
										new Bone[] {

												// neck
												new Bone(new Position(0, 0, 0), new Angle3D(-20, 0, -90), 11, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(0, 0, -90), 4, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(5, 0, -90), 4, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(5, 0, -90), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), }, null),

												// arm R
												new Bone(new Position(0, 0, 0), new Angle3D(-20, 0, -130), 11, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(30, 0, 170), 11, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(0, 0, 135), 22, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0, 135), 22, H,
																				new Bone[] {

																				}, null), }, null), }, null), }, null),

												// arm L
												new Bone(new Position(0, 0, 0), new Angle3D(-20, 0, -50), 11, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(30, 0, 10), 11, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(0, 0, 45), 22, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0, 45), 22, H,
																				new Bone[] {

																				}, null), }, null), }, null), }, null),

										}, null), }, null), }, null),

						// Leg R
						new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 170), 13, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 90), 34, H,
										new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 90), 22, H,
												new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(-45, 0, 90), 8,
														H, new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(0, 0, 90), 0, H, new Bone[] {

																}, null), }, null), }, null), }, null), }, null),

						// Leg L
						new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 10), 13, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 90), 34, H,
										new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 90), 22, H,
												new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(-45, 0, 90), 8,
														H, new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(0, 0, 90), 0, H, new Bone[] {

																}, null), }, null), }, null), }, null), }, null) },
						null) });

		S = AnimationList.Idle().getFrame();
		S.update();
		Animation k = AnimationList.KickAttack();
		// handler.Buttons.add(new Grid(new Position(0, 180, 0), new Rectangle(0, 35),
		// true));
		// for (int i = 0; i < k.KeyFrames.size(); i++)
		// ((Grid) handler.Buttons.getLast()).add(i,
		// new Grid(new Position(0, 0, 0), k.Frames[k.KeyFrames.get(i)].clone(),
		// k.KeyFrames.get(i)));

		LinkedList<Move> M = new LinkedList<Move>();

		M.add(new Move(AnimationList.Idle(), new Position(0, 0, 0), new LinkedList<Integer>()));

		handler.addObject(new Test(new Position(0, 400, 0), 150, new Rectangle(-45, -90, 90, 135), S.clone(),
				new MobState(new Position(0, 400, 0), (LinkedList<Move>) M.clone())));

		M.clear();
		M.add(new Move(AnimationList.Idle(), (Position) null, new LinkedList<Integer>()));
		handler.addObject(new Dummy(new Position(350, 400, 0), 150, new Rectangle(-45, -90, 90, 135), S.clone(),
				new MobState(new Position(350, 400, 0), (LinkedList<Move>) M.clone())));
		AnimationEditor.SELECT.MOB((MOB) Handler.object.get(0));
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTricks = 60;
		double ns = 1000000000 / amountOfTricks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: "+frames);
				FPS_now = frames;
				frames = 0;
			}

		}
		stop();
	}

	private void tick() {
		handler.tick();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		g.dispose();
		bs.show();

	}

	public static void main(String args[]) {
		new Game();
	}

	public static String[] ConvertToObjectArguments(String S) {
		LinkedList<String> r = new LinkedList<String>();
		if (S == null)
			return null;

		if (S.length() == 0)
			return null;

		int n = 0;
		String selected = "";
		int s = 0;
		while (S.charAt(s) != '[')
			s++;
		s++;

		for (int i = 2; i < S.length(); i++) {
			if (S.charAt(i) == '[')
				n++;
			if (S.charAt(i) == ']')
				n--;

			if (!((n == 0) && ((S.charAt(i) == '|')))) {
				selected += S.charAt(i);
				if (((S.charAt(i) == '|') || (S.charAt(i) == ']')) && (n == 0)) {
					r.add(selected);
					selected = "";
				} else {
					if (S.length() - 1 == i) {
						if (selected.length() != 0) {
							r.add(selected.substring(0, selected.length() - 1));
						}
					}
				}
			} else {
				if (selected.length() != 0) {
					r.add(selected);
					selected = "";
				}
			}

			// if (!((S.charAt(i) == '|') || (S.charAt(i) == '[') || (S.charAt(i) == ']')))

		}

		String[] R = new String[r.size()];
		for (int i = 0; i < r.size(); i++) {
			R[i] = r.get(i);
		}

		return R;
	}

	public static void PrintWithEnters(String string) {
		for (int i = 0; i < string.length(); i++) {

			System.out.print(string.charAt(i));
			if ((string.charAt(i) == '{') || (string.charAt(i) == '}'))
				System.out.println();
		}
	}

	public static Point2D.Double Turn(Point2D.Double P1, Point2D.Double P2, double direction) {

		double angleRad = Math.toRadians(direction);
		double cosThetha = Math.cos(angleRad); // The angle COS
		double sinThetha = Math.sin(angleRad); // The angle SIN
		double dx = (P1.x - P2.x); // Difference (Point in transformed to origo)
		double dy = (P1.y - P2.y); // Difference -- || --

		double ptX = P2.x + (dx * cosThetha - dy * sinThetha);
		double ptY = P2.y + (dx * sinThetha + dy * cosThetha);

		return new Point2D.Double(ptX, ptY);
	}

	public static Point rotatePoint(Point pt, Point center, double angleDeg) {
		// http://en.wikipedia.org/wiki/Rotation_matrix

		double angleRad = Math.toRadians(Game.Deg(angleDeg));
		double cosThetha = Math.cos(angleRad); // The angle COS
		double sinThetha = Math.sin(angleRad); // The angle SIN
		double dx = (pt.x - center.x); // Difference (Point in transformed to origo)
		double dy = (pt.y - center.y); // Difference -- || --

		int ptX = center.x + (int) (dx * cosThetha - dy * sinThetha);
		int ptY = center.y + (int) (dx * sinThetha + dy * cosThetha);

		return new Point(ptX, ptY);
	}

	private static double Deg(double angleDeg) {
		while (angleDeg > 360)
			angleDeg -= 360;
		while (angleDeg < 0)
			angleDeg += 360;
		return angleDeg;
	}

	public static Point2D.Double LengthDir(double Length, double Dir) {
		return Turn(new Point2D.Double(Length, 0), new Point2D.Double(0, 0), Dir);
	}

	public static Position Turn3D(Position C, Position P, Angle3D angle) {

		double cX = C.x;
		double cY = C.y;
		double cZ = C.z;

		double pX = P.x + 50;
		double pY = P.y;
		double pZ = P.z;

		Point2D.Double XZ = Turn(new Point2D.Double(pX, pZ), new Point2D.Double(cX, cZ), angle.Y_direction);
		Point2D.Double YZ = Turn(new Point2D.Double(pY, XZ.y), new Point2D.Double(cY, cZ), angle.X_direction);
		Point2D.Double XY = Turn(new Point2D.Double(XZ.x, YZ.x), new Point2D.Double(cX, cY), angle.Z_direction);

		return new Position((int) XY.x, (int) XY.y, (int) YZ.y);
	}

	public static Position LengthDir3D(double Length, Angle3D angle) {
		Position result = new Position((int) Length, 0, 0);
		result.Turn(new Position(0, 0, 0), angle);
		return result;
	}

	public static Point2D[] getIntersectionPoint(Line2D line, Rectangle2D rectangle) {

		Point2D[] p = new Point2D[4];

		// Top line
		p[0] = getIntersectionPoint(line, new Line2D.Double(rectangle.getX(), rectangle.getY(),
				rectangle.getX() + rectangle.getWidth(), rectangle.getY()));
		// Bottom line
		p[1] = getIntersectionPoint(line, new Line2D.Double(rectangle.getX(), rectangle.getY() + rectangle.getHeight(),
				rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight()));
		// Left side...
		p[2] = getIntersectionPoint(line, new Line2D.Double(rectangle.getX(), rectangle.getY(), rectangle.getX(),
				rectangle.getY() + rectangle.getHeight()));
		// Right side
		p[3] = getIntersectionPoint(line, new Line2D.Double(rectangle.getX() + rectangle.getWidth(), rectangle.getY(),
				rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight()));

		return p;

	}

	public static Point2D getIntersectionPoint(Line2D lineA, Line2D lineB) {

		double x1 = lineA.getX1();
		double y1 = lineA.getY1();
		double x2 = lineA.getX2();
		double y2 = lineA.getY2();

		double x3 = lineB.getX1();
		double y3 = lineB.getY1();
		double x4 = lineB.getX2();
		double y4 = lineB.getY2();

		Point2D p = null;

		double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
		if (d != 0) {
			double xi = ((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d;
			double yi = ((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d;

			p = new Point2D.Double(xi, yi);

		}
		return p;
	}

	public static Point2D.Double lineLineIntersection(Point2D.Double A, Point2D.Double B, Point2D.Double C,
			Point2D.Double D) {
		// Line AB represented as a1x + b1y = c1
		double a1 = B.y - A.y;
		double b1 = A.x - B.x;
		double c1 = a1 * (A.x) + b1 * (A.y);

		// Line CD represented as a2x + b2y = c2
		double a2 = D.y - C.y;
		double b2 = C.x - D.x;
		double c2 = a2 * (C.x) + b2 * (C.y);

		double determinant = a1 * b2 - a2 * b1;

		if (determinant == 0) {
			// The lines are parallel. This is simplified
			// by returning a pair of FLT_MAX
			return null;
		} else {
			double x = (b2 * c1 - b1 * c2) / determinant;
			double y = (a1 * c2 - a2 * c1) / determinant;
			return new Point2D.Double(x, y);
		}
	}

	public static Point2D Turn90Deg(Point2D.Double Point, Point2D.Double C) {
		Point2D.Double p = (Double) Point.clone();
		Point2D.Double center = (Double) C.clone();

		Point2D.Double relativePoint = new Point2D.Double(p.x + center.x, p.y + center.y);

		// For 90deg CW: -y, x
		// For 90deg CC: y, -x
		Point2D.Double rotatedPoint = new Point2D.Double(relativePoint.y, relativePoint.y); // 90deg CW
		Point2D.Double finalPoint = new Point2D.Double(rotatedPoint.x + center.x, rotatedPoint.y + center.y);
		return finalPoint;
	}

	public static Optional<Point2D.Double> getIntersectionPoint(Line2D.Double L1, Line2D.Double L2) {
		return Game.getIntersectionPoint(L1.getX1(), L1.getY1(), L2.getX2(), L2.getY2());
	}

	public static Optional<Point2D.Double> getIntersectionPoint(double m1, double b1, double m2, double b2) {

		if (m1 == m2) {
			return Optional.empty();
		}

		double x = (b2 - b1) / (m1 - m2);
		double y = m1 * x + b1;

		Point2D.Double point = new Point2D.Double();
		point.setLocation(x, y);
		return Optional.of(point);
	}

	public void logMatrix(float[][] m) {
		int cols = m[0].length;
		int rows = m.length;
		System.out.println(rows + "x" + cols);
		System.out.println("----------------");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static Position matmul(float[][] a, Position b) {
		return matrixToPos(matmul(a, posToMatrix(b)));
	}

	public static float[][] matmul(float[][] a, float[][] b) {
		int colsA = a[0].length;
		int rowsA = a.length;
		int colsB = b[0].length;
		int rowsB = b.length;

		if (colsA != rowsB) {
			return null;
		}

		float result[][] = new float[rowsA][colsB];

		for (int i = 0; i < rowsA; i++) {
			for (int j = 0; j < colsB; j++) {
				float sum = 0;
				for (int k = 0; k < colsA; k++) {
					sum += a[i][k] * b[k][j];
				}
				result[i][j] = sum;
			}
		}

		return result;
	}

	public static float[][] posToMatrix(Position v) {
		float[][] m = new float[3][1];
		m[0][0] = v.x;
		m[1][0] = v.y;
		m[2][0] = v.z;
		return m;
	}

	public static Position matrixToPos(float[][] m) {
		Position v = new Position(0, 0, 0);
		v.x = m[0][0];
		v.y = m[1][0];
		if (m.length > 2) {
			v.z = m[2][0];
		}
		return v;
	}

}

/*
 * Skeloton S = new Skeloton( new Position(0, 0,0),new Angle3D(), new Bone[] {
 * new Bone(new Position(0,0,0), new Angle3D(0,0,0), 0, H, new Bone[] { //torso
 * new Bone(new Position(0,0,0), new Angle3D(10,0,-90), 9, H, new Bone[] { new
 * Bone(new Position(0,0,0), new Angle3D(-1,0,-90), 13, H, new Bone[] { new
 * Bone(new Position(0,0,0), new Angle3D(-5,0,-90), 11, H, new Bone[] {
 * 
 * //neck new Bone(new Position(0,0,0), new Angle3D(-20,0,-90), 11, H, new
 * Bone[] { new Bone(new Position(0,0,0), new Angle3D(0,0,-90), 4, H, new Bone[]
 * { new Bone(new Position(0,0,0), new Angle3D(5,0,-90), 4, H, new Bone[] { new
 * Bone(new Position(0,0,0), new Angle3D(5,0,-90), 0, H, new Bone[] {
 * 
 * }, null), }, null), }, null), }, null),
 * 
 * //arm R new Bone(new Position(0,0,0), new Angle3D(-20,0,-130), 11, H, new
 * Bone[] { new Bone(new Position(0,0,0), new Angle3D(30,0,170), 11, H, new
 * Bone[] { new Bone(new Position(0,0,0), new Angle3D(0,0,135), 22, H, new
 * Bone[] { new Bone(new Position(0,0,0), new Angle3D(0,0,135), 22, H, new
 * Bone[] {
 * 
 * }, null), }, null), }, null), }, null),
 * 
 * //arm L new Bone(new Position(0,0,0), new Angle3D(-20,0,-50), 11, H, new
 * Bone[] { new Bone(new Position(0,0,0), new Angle3D(30,0,10), 11, H, new
 * Bone[] { new Bone(new Position(0,0,0), new Angle3D(0,0,45), 22, H, new Bone[]
 * { new Bone(new Position(0,0,0), new Angle3D(0,0,45), 22, H, new Bone[] {
 * 
 * }, null), }, null), }, null), }, null),
 * 
 * }, null), }, null), }, null),
 * 
 * //Leg R new Bone(new Position(0,0,0), new Angle3D(0,0,170), 13, H, new Bone[]
 * { new Bone(new Position(0,0,0), new Angle3D(0,0,90), 34, H, new Bone[] { new
 * Bone(new Position(0,0,0), new Angle3D(0,0,90), 22, H, new Bone[] { new
 * Bone(new Position(0,0,0), new Angle3D(-45,0,90), 8, H, new Bone[] { new
 * Bone(new Position(0,0,0), new Angle3D(0,0,90), 0, H, new Bone[] {
 * 
 * }, null), }, null), }, null), }, null), }, null),
 * 
 * //Leg L new Bone(new Position(0,0,0), new Angle3D(0,0,10), 13, H, new Bone[]
 * { new Bone(new Position(0,0,0), new Angle3D(0,0,90), 34, H, new Bone[] { new
 * Bone(new Position(0,0,0), new Angle3D(0,0,90), 22, H, new Bone[] { new
 * Bone(new Position(0,0,0), new Angle3D(-45,0,90), 8, H, new Bone[] { new
 * Bone(new Position(0,0,0), new Angle3D(0,0,90), 0, H, new Bone[] {
 * 
 * }, null), }, null), }, null), }, null), }, null) },null) } );
 */
