package backscatter;

/**
 * Attributes of <code>Transfer</code>.
 * 
 * @author Artur Rataj
 */
public class TransferAttr {
    /**
     * Dimensionality.
     */
    public final int DIM;
    /**
     * Resolution along a single axis.
     */
    public final int RES;
    
    /**
     * New transfer attributes.
     * 
     * @param dim dimensionality, positive value
     * @param res resolution along one axis
     */
    public TransferAttr(int dim, int res) {
        DIM = dim;
        RES = res;
    }
}
