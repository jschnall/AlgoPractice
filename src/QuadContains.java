// Implemenatation of question from Zillow (Trulia) tech screen

public class QuadContains {
    public static class Point {
        double x;
        double y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Quad {
        int bottomLeft = 0;
        int topLeft = 1;
        int bottomRight = 2;
        int topRight = 3;

        Point[] corners = new Point[4];

        public Quad(Point[] points) {
            corners = points;
        }

        public boolean contains(Point point) {
            // on or right of left side?
            double m = getSlope(corners[bottomLeft], corners[topLeft]);
            if (getX(m, corners[bottomLeft], point.y) > point.x) {
                return false;
            }

            // on or left of right side?
            m = getSlope(corners[bottomRight], corners[topRight]);
            if (getX(m, corners[bottomRight], point.y) < point.x) {
                return false;
            }

            // on or below top side?
            m = getSlope(corners[topLeft], corners[topRight]);
            if (getY(m, corners[topLeft], point.x) < point.y) {
                return false;
            }

            // on or above bottom side?
            m = getSlope(corners[bottomLeft], corners[bottomRight]);
            if (getY(m, corners[bottomLeft], point.x) > point.y) {
                return false;
            }

            return true;
        }

        private double getY(double m, Point p1, double x2) {
            return m * (x2 - p1.x) + p1.y;
        }

        private double getX(double m, Point p1, double y2) {
            return (y2 - p1.y) / m + p1.x;
        }

        private double getSlope(Point p1, Point p2) {
            return (p2.y - p1.y) / (p2.x - p1.x);
        }
    }

    public static void main(String[] args) {
        Point[] corners = new Point[4];
        corners[0] = new Point(0, 0);
        corners[1] = new Point(2, 3);
        corners[2] = new Point(6, 0);
        corners[3] = new Point(4, 3);

        Quad quad = new Quad(corners);
        System.out.println(quad.contains(new Point(0, 0)));
        System.out.println(quad.contains(new Point(5, 5)));
        System.out.println(quad.contains(new Point(3, 1)));
        System.out.println(quad.contains(new Point(1, 1)));
        System.out.println(quad.contains(new Point(1, 2)));
        System.out.println(quad.contains(new Point(1, 3)));
        System.out.println(quad.contains(new Point(3, 3)));
    }
}
