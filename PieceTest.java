/** This program tests the Piece class */
public class PieceTest {
    public static boolean testPiece() {
       boolean passed = true;
       //Testing constructor
       Piece P1 = new Piece("blue",0,0,0);
       if (!P1.getColor().equals("blue") || P1.getX()!=0 || P1.getY()!=0 || P1.getBlot() || P1.getID()!=0) {
           passed = false;
           System.out.println("Failed constructor Test case");
       }
       //Testing constructor case 2
       Piece P2 = new Piece("blue",4,3,1);
       if (!P2.getColor().equals("blue") || P2.getX()!=4 || P2.getY()!=3 || P2.getBlot() || P2.getID()!=1) {
           passed = false;
           System.out.println("Failed constructor Test case 2");
        }
        //Testing equals
        Piece P3 = new Piece("blue",2,3,1);
       if (!P3.equals(P2)) {
           passed = false;
           System.out.println("Failed equals Test case");
       }
       //Testing switchBlot
       P2.setBlot(false);
       P2.switchBlot();
       if (!P2.getBlot() || P2.getX()!=0 || P2.getY()!=0) {
           passed = false;
           System.out.println("Failed switchBlot method");
       }
       //Testing switchBlot case 2
       P2.setBlot(true);
       P2.setX(3);
       P2.setY(5);
       P2.switchBlot();
       if (P2.getBlot() || P2.getX()!=3 || P2.getY()!=5) {
            passed = false;
            System.out.println("Failed switchBlot 2 method");
        }
       //Testing toString
       if (!P2.toString().equals("blue1")) {
           passed = false;
           System.out.println("Failed toString method");
       }
       //Testing isSameTeam
       if (!P1.isSameTeam(P3)){
           passed = false;
           System.out.println("Failed isSameTeam method");
       }

       return passed;
    }

    public static void main(String [] args) {
       if(testPiece())
           System.out.println("All tests passed.");
    }

}
