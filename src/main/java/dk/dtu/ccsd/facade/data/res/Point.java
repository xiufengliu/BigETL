package dk.dtu.ccsd.facade.data.res;

/**
 * Created by xiuli on 3/4/15.
 */
public class Point {
    long x;
    double y;
    public Point(long x, double y){
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
