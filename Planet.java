/**
 * Planet
 */
public class Planet {
  // instance variables
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;

  static final double gForce = 6.67e-11;

  // constructor
  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    this.xxPos = xP;
    this.yyPos = yP;
    this.xxVel = xV;
    this.yyVel = yV;
    this.mass = m;
    this.imgFileName = img;
  }

  // constructor used to create a copy of the planet
  public Planet(Planet p) {
    this.xxPos = p.xxPos;
    this.yyPos = p.yyPos;
    this.xxVel = p.xxVel;
    this.yyVel = p.yyVel;
    this.mass = p.mass;
    this.imgFileName = p.imgFileName;
  }

  // helper method - distance between two planets
  public double calcDistance(Planet p) {
    double distance;

    double dx = p.xxPos - this.xxPos;
    double dy = p.yyPos - this.yyPos;
    distance = Math.sqrt((dx*dx) + (dy*dy));

    return distance;
  }

  public double calcForceExertedBy(Planet p) {
    double force;
    double distance = calcDistance(p);

    force = (gForce*this.mass*p.mass)/(distance*distance);

    return force;
  }


  
}