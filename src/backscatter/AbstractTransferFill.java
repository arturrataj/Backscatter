package backscatter;

import static java.lang.Math.*;

/**
 * Fills <code>Transfer</code> with values.
 * 
 * @author Artur Rataj
 */
public abstract class AbstractTransferFill {
    /**
     * Dimensionality or -1 for accept any.
     */
    public final int DIM;
    
    /**
     * A new abstract fill function.
     * 
     * @param dim dimensionality, -1 for any; must be compatible with filled transfer functions
     */
    public AbstractTransferFill(int dim) {
        DIM = dim;
    }
    /**
     * Checks if coordinates are compliant with <code>DIM</code>. Tu be used in </code>value()<code>.
     * 
     * @param coord  passed to <code>value</code>
     */
    protected boolean checkDim(double[] coord) {
        return DIM == -1 || coord.length == DIM;
    }
    /**
     * Value to set a given point.
     * 
     * @param coord coordinates 0 ... 1; number of elements depends on the filled
     * transfer function's dimensions and must obey <code>DIM</code>.
     * 
     * @return value
     */
    public abstract double value(double[] coord);
    /**
     * An utility function. Softens a 0 ... 1 value into one in the same range, but with derivatives at extreme points
     * being zero.
     * 
     * @param x value to soften
     * @return softened value 
     */
    public static double softSin(double x) {
        return (sin(x*PI - PI/2.0) + 1.0)/2.0;
    }
}
