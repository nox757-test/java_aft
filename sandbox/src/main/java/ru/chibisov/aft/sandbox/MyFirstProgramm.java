package ru.chibisov.aft.sandbox;

public class MyFirstProgramm {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(4.0, 4.0);
        System.out.println(p1.distance(p2));
        System.out.println(p2.distance(p1));
        System.out.println(distance(p1, p2));
        System.out.println(distance(p2, p1));
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
    }
}