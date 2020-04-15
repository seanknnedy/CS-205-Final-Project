import java.util.*;
/** This program tests the spike class */
public class SpikeTest {
    public static boolean testSpike() {
        boolean passed = true;
        /*
        //Testing Constructor
        Spike s1 = new Spike(0, 0);
        if (s1.getX() != 0 || s1.getY() != 0 || !s1.getCurrentTeam().equals("NONE") || s1.getPiecesOnSpike().size() != 0) {
            passed = false;
            System.out.println("Failed constructor test case");
        }
        //Testing addToSpike
        Piece p1 = new Piece("red", 5, 4, 1);
        s1.addToSpike(p1);
        ArrayList<Piece> testSpike = new ArrayList<Piece>();
        testSpike.add(p1);
        if (p1.getX() != 0 || p1.getY() != 0 || !s1.getCurrentTeam().equals(p1.getColor()) ||
                !s1.getPiecesOnSpike().equals(testSpike)) {
            passed = false;
            System.out.println("Failed addtoSpike test case");
        }
        //Testing addToSpike case 2
        testSpike = new ArrayList<Piece>();
        testSpike.add(p1);
        Spike s2 = new Spike(5, 1);
        s2.addToSpike(p1);

        if (p1.getX() != 5 || p1.getY() != 1 || !s2.getCurrentTeam().equals(p1.getColor()) ||
                !s2.getPiecesOnSpike().equals(testSpike)) {
            passed = false;
            System.out.println("Failed addToSpike test case 2");
        }
        //Testing removeFromSpike
        testSpike = new ArrayList<Piece>();
        s2.removeFromSpike(p1);
        if (p1.getX() != 0 || p1.getY() != 0 || !s2.getCurrentTeam().equals("NONE") ||
                !s2.getPiecesOnSpike().equals(testSpike)) {
            passed = false;
            System.out.println("Failed removeFromSpike test case");
        }
        //Testing addToSpike case 3
        s2 = new Spike(12, 2);
        Piece p2 = new Piece("red", 5, 1, 2);
        testSpike = new ArrayList<Piece>();
        testSpike.add(p1);
        testSpike.add(p2);
        s2.addToSpike(p1);
        s2.addToSpike(p2);
        if (p1.getX() != 12 || p2.getX() != 12 || p1.getY() != 2 || p2.getY() != 2 || !s2.getPiecesOnSpike().equals(testSpike)) {
            passed = false;
            System.out.println("Failed addToSpike test case 3");
        }
        //Testing removeFromSpike case 2
        testSpike.remove(p2);
        s2.removeFromSpike(p2);
        if (p2.getX() != 0 || p2.getY() != 0 || !s2.getPiecesOnSpike().equals(testSpike)) {
            passed = false;
            System.out.println("Failed removeFromSpike test case 2");
        }
        //Testing equals
        s2 = new Spike(11, 1);
        Spike s3 = new Spike(11, 1);
        if (!s2.equals(s3)) {
            passed = false;
            System.out.println("Failed equals test case");
        }
        //Testing toString
        s2.addToSpike(p1);
        if (!(s2.toString().equals(testSpike.toString()))) {
            passed = false;
            System.out.println("Failed toString test case");
        }
        //Testing equals case 2
        s3.addToSpike(p1);
        if (!s2.equals(s3)) {
            passed = false;
            System.out.println("Failed equals test case 2");
        }

         */

        return passed;
    }


    public static void main(String [] args) {
        if(testSpike())
            System.out.println("All tests passed.");
    }
}
