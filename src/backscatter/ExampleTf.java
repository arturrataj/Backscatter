package backscatter;

/**
 * An example filler of a two-dimensional <code>Transfer</code>.
 * 
 * @author Artur Rataj
 */
public class ExampleTf extends AbstractTransferFill {
    public ExampleTf() {
        super(2);
    }
    @Override
    public double value(double[] coord) {
        checkDim(coord);
        return softSin(coord[0]*(1.0 - coord[1]));
    }
}
