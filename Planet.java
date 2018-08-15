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

  public double xNetF;
  public double yNetF;

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

  public double calcForceExertedByX(Planet p) {
    double dx = p.xxPos - this.xxPos;

    double forceX = (calcForceExertedBy(p)*dx)/calcDistance(p);

    return forceX;
  }

  public double calcForceExertedByY(Planet p) {
    double dy = p.yyPos - this.yyPos;

    double forceY = (calcForceExertedBy(p)*dy)/calcDistance(p);

    return forceY;
  }

  public double calcNetForceExertedByX(Planet[] planets) {
    double xForceNet = 0;

    for (Planet p : planets) {
      if(this.equals(p)) {
        continue;
      } else {
        xForceNet = xForceNet + this.calcForceExertedByX(p);
      }

    }
    return xForceNet;
  }

  public double calcNetForceExertedByY(Planet[] planets) {
    double yForceNet = 0;

    for (Planet p : planets) {
      if(this.equals(p)) {
        continue;
      } else {
        yForceNet = yForceNet + this.calcForceExertedByY(p);
      }

    }
    return yForceNet;
  }

  public void update(double dt, double fX, double fY) {
    double xNetAcceleration;
    double yNetAcceleration;

    xNetAcceleration = fX / this.mass;
    yNetAcceleration = fY / this.mass;
    
    this.xxVel = this.xxVel + (dt * xNetAcceleration);
    this.yyVel = this.yyVel + (dt * yNetAcceleration);

    this.xxPos = this.xxPos + (dt * this.xxVel);
    this.yyPos = this.yyPos + (dt * this.yyVel);
    System.out.println(this.xxPos);
    System.out.println(this.yyPos);
  }

  public void draw() {
    String filename = "./images/" + this.imgFileName;
    StdDraw.picture(this.xxPos, this.yyPos, filename);
  }
}
