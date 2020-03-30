package ru.chibisov.aft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    private Point p1;
    private Point p2;

    @Test
    void checkDistancePlane1() {
        p1 = new Point(1.0, 1.0);
        p2 = new Point(4.0, 4.0);
        Assert.assertEquals(p1.distance(p2), 4.242640687119285);
        Assert.assertEquals(p2.distance(p1), 4.242640687119285);
    }

    @Test
    void checkDistancePlane2() {
        p1 = new Point(-1.0, 1.0);
        p2 = new Point(-4.0, 4.0);
        Assert.assertEquals(p1.distance(p2), 4.242640687119285);
        Assert.assertEquals(p2.distance(p1), 4.242640687119285);
    }

    @Test
    void checkDistancePlane3() {
        p1 = new Point(-1.0, -1.0);
        p2 = new Point(-4.0, -4.0);
        Assert.assertEquals(p1.distance(p2), 4.242640687119285);
        Assert.assertEquals(p2.distance(p1), 4.242640687119285);
    }

    @Test
    void checkDistancePlane4() {
        p1 = new Point(1.0, -1.0);
        p2 = new Point(4.0, -4.0);
        Assert.assertEquals(p1.distance(p2), 4.242640687119285);
        Assert.assertEquals(p2.distance(p1), 4.242640687119285);
    }

    @Test
    void checkDistanceZero() {
        p1 = new Point(0.0, 0.0);
        p2 = new Point(0.0, 0.0);
        Assert.assertEquals(p1.distance(p2), 0.0);
        Assert.assertEquals(p2.distance(p1), 0.0);
    }
}
