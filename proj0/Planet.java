public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G=6.67e-11;
    public Planet(double xP,double yP,double xV,double yV,double m,String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;

    }
    public double calcDistance(Planet p2){
        double distance;
        distance=Math.sqrt(Math.pow(xxPos-p2.xxPos,2)+Math.pow(yyPos-p2.yyPos,2));
        return distance;
    }
    public double calcForceExertedBy(Planet p2){
        double F;
        F=(G*mass* p2.mass)/Math.pow(calcDistance(p2),2);
        return F;
    }
    public double calcForceExertedByX(Planet p2){
        double Fx;
        Fx=calcForceExertedBy(p2)*((p2.xxPos-xxPos)/calcDistance(p2));
        return Fx;
    }
    public double calcForceExertedByY(Planet p2){
        double Fy;
        Fy=calcForceExertedBy(p2)*((p2.yyPos-yyPos)/calcDistance(p2));
        return Fy;
    }
    public double calcNetForceExertedByX(Planet[] planets){
        double Fnetx=0;
        for(Planet planet:planets){
            if (!(this.equals(planet))){
                Fnetx+=this.calcForceExertedByX(planet);
            }
        }
        return Fnetx;
    }
    public double calcNetForceExertedByY(Planet[] planets){
        double Fnety=0;
        for(Planet planet:planets){
            if (!(this.equals(planet))){
                Fnety+=this.calcForceExertedByY(planet);
            }
        }
        return Fnety;
    }
    public void update(double dt, double fX, double fY){
        double ax;
        double ay;
        ax = fX/mass;
        ay = fY/mass;
        xxVel = xxVel+dt*ax;
        yyVel = yyVel+dt*ay;
        xxPos = xxPos+dt*xxVel;
        yyPos = yyPos+dt*yyVel;
    }
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + this.imgFileName);
    }


}
