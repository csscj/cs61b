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
        Planet[] body = NBody.readPlanets(filename);

        String imageToDraw = "./images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        StdDraw.enableDoubleBuffering();
        for (double t=0;t<=T;t+=dt){
            double[] xForce = new double[body.length];
            double[] yForce = new double[body.length];

            for(int i=0;i<body.length;i++){
                xForce[i] = body[i].calcNetForceExertedByX(body);
                yForce[i] = body[i].calcNetForceExertedByY(body);
            }

            for(int i=0;i<body.length;i++) {
                body[i].update(dt,xForce[i],yForce[i]);
            }
            StdDraw.picture(0, 0, imageToDraw);

            for(int i=0;i<body.length;i++) {
                body[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            }
            StdOut.printf("%d\n", body.length);
            StdOut.printf("%.2e\n", radius);
            for (int i = 0; i < body.length; i++) {
                StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        body[i].xxPos, body[i].yyPos, body[i].xxVel,
                        body[i].yyVel, body[i].mass, body[i].imgFileName);
        }
        }


}

