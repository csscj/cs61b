public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int num = in.readInt();
        Planet[] body = new Planet[num];

        in.readDouble();
        for(int i=0;i<num;i++){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            body[i] = new Planet(xP,yP,xV,yV,m,img);
        }
        return body;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] Body = NBody.readPlanets(filename);

        String imageToDraw = "./images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        StdDraw.enableDoubleBuffering();
        for (double t=0;t<=T;t+=dt){
            double[] xForce = new double[Body.length];
            double[] yForce = new double[Body.length];

            for(int i=0;i<Body.length;i++){
                xForce[i] = Body[i].calcNetForceExertedByX(Body);
                yForce[i] = Body[i].calcNetForceExertedByY(Body);
            }

            for(int i=0;i<Body.length;i++) {
                Body[i].update(dt,xForce[i],yForce[i]);
            }
            StdDraw.picture(0, 0, imageToDraw);

            for(int i=0;i<Body.length;i++) {
                Body[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            }
            StdOut.printf("%d\n", Body.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < Body.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        Body[i].xxPos, Body[i].yyPos, Body[i].xxVel,
                        Body[i].yyVel, Body[i].mass, Body[i].imgFileName);
        }
        }


}

