
public class PieceTest {
    public static boolean testPiece() {
       boolean passed = true;
       Piece P1 = new Piece("blue",0,0);
       if (!P1.getColor().equals("blue") || P1.getX()!=0 || P1.getY()!=0 || P1.getBlot()) {
           passed = false;
           System.out.println("Failed constructor Test case");
       }

       return passed;
    }

    public static void main(String [] args) {
       if(testPiece())
           System.out.println("All tests passed.");
    }

}
