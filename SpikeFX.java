import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


/** The SpikeFX class creates a javaFX representation of the Spike class */
public class SpikeFX extends Polygon {
    private Spike spike;
    private int xCoord;
    private int yCoord;
    private int spikeId;
    private Polygon spikeImg;
/** The Constructor draws a triangle with pre-set dimensions using an input spike object. If the y coordinate of the
 * spike is 1, the triangle is drawn pointing downwards. Otherwise, it is drawn pointed upwards. The color of the triangle
 * is set based on whether the spike ID is even or odd.
 * @param object A spike object
 */
    public SpikeFX(Spike object) {
        spike = object;
        spikeId = object.getSpikeID();
        xCoord = object.getX();
        yCoord = object.getY();

        if (yCoord==1)
             spikeImg = new Polygon(
                     10.0, 20.0,
                              30.0, 110.0,
                              50.0, 20.0);
        else
            spikeImg = new Polygon(
                    10.0, 110.0,
                             30, 20.0,
                             50.0, 110.0);

        if (spikeId % 2 == 0) {
            spikeImg.setFill(Color.SADDLEBROWN);
        } else {
            spikeImg.setFill(Color.TAN);
        }
    }

    //Returns the FX of the spike
    public Polygon getTriangle() {
        return spikeImg;
    }

    //Returns the x coordinate of the spike
    public int getX() {
        return xCoord;
    }

    //Returns the y coordinate of the spike
    public int getY() {
        return yCoord;
    }
    //Returns the spike object
    public Spike getSpike(){
        return spike;
    }

}
