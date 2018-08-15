/**
 * Nbody
 */
public class NBody {
  public static String imageToDraw = "images/starfield.jpg";

  public static double readRadius(String filename) {
    In in = new In(filename);
    
    double N = in.readInt();
    double radius = in.readDouble();

    return radius;
  }

  public static Planet[] readPlanets(String filename) {
    In in = new In(filename);
    
    int N = in.readInt();
    double radius = in.readDouble();

    Planet[] planets = new Planet[N];

    for (int i = 0; i < N; i++) {
      double xPos = in.readDouble();
      double yPos = in.readDouble();
      double xVel = in.readDouble();
      double yVel = in.readDouble();
      double m = in.readDouble();
      String img = in.readString();

      Planet planet = new Planet(xPos, yPos, xVel, yVel, m, img);
      planets[i] = planet;
    }


    return planets;
  }
  
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];

    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);

    /** Sets up the universe so it goes from 
    radius to radius */
		StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
    StdDraw.clear();
    /* Stamps copies of starfield.jpg */
    StdDraw.picture(0, 0, imageToDraw);
    // Draw planets
    for (Planet planet : planets) {
      planet.draw();
    }

    String audio = "audio/2001.mid";
    StdAudio.play(audio);

    StdDraw.enableDoubleBuffering();   

    double time = 0;
		while (time < T) {
      System.out.println(time);
      System.out.println(T);
      System.out.println(time < T);
      double[] xForces = new double[planets.length];
      double[] yForces = new double[planets.length];

      for (int i = 0; i < planets.length; i++) {
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for (int i = 0; i < planets.length; i++) {
        planets[i].update(dt, xForces[i], yForces[i]);
      }

      StdDraw.picture(0, 0, imageToDraw);

      for (Planet p : planets) {
        p.draw();
      }

      StdDraw.show();

      StdDraw.pause(10);
      time += dt;
		}
  }
}