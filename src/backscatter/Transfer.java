package backscatter;

/**
 * A transfer function.
 * 
 * @author Artur Rataj
 */
public class Transfer {
    /**
     * Attributes.
     */
    protected TransferAttr ATTR;
    /**
     * Values.
     */
    protected double[] VALUE;
    
    /**
     * New transfer function.
     * 
     * @param attr attributes
     */
    public Transfer(TransferAttr attr) {
        ATTR = attr;
        VALUE = new double[(int)Math.pow(ATTR.RES, ATTR.DIM)];
    }
    /**
     * Switches <code>point</code> to the next value. If the first call is done with
     * all zeroes and the calls are performed until false is returned, all values
     * are iterated over.<br>
     * 
     * See <code>fill()</code> for example usage.
     * 
     * @param point value indices
     * @param coord if not null, value real coordinates updated according to
     * <code>point</code>; incremental update which must start at all zeroes
     * for both <code>point</code> and <code>coord</code>
     * @return if there are any elements left; if false, <code>point</code> is
     * all <code>RES - 1</code> and <code>coord</code> is all <code>1</code>
     */
    protected boolean iterate(int[] point, double[] coord) {
        for(int d = 0; d < ATTR.DIM; ++d)
            if(point[d] == ATTR.RES - 1) {
                point[d] = 0;
                coord[d] = 0.0;
                if(d == ATTR.RES - 1)
                    return false;
            } else {
                ++point[d];
                coord[d] = point[d]*1.0/(ATTR.RES - 1);
                break;
            }
        return true;
    }
    /**
     * Fills all of this function's values.
     * 
     * @param fill filling expression
     */
    public void fill(AbstractTransferFill fill) {
        int[] point = new int[ATTR.DIM];
        double[] coord = new double[ATTR.DIM];
        SCAN:
        while(true) {
            set(point, fill.value(coord));
            if(!iterate(point, coord))
                break;
        }
    }
    /**
     * Point indices to <code>VALUE</code>'s index.
     * 
     * @param point indices
     * @return index of respective element in <code>VALUE</code>
     */
    protected int toIndex(int[] point) {
        int index = 0;
        for(int d = 0; d < ATTR.DIM; ++d) {
            index *= ATTR.RES;
            index += point[d];
        }
        return index;
    }
    /**
     * Sets a single value.
     * 
     * @param point indices, 0 ... ATTR.RES - 1
     * @param value new value
     */
    public void set(int[] point, double value) {
        VALUE[toIndex(point)] = value;
    }
    /**
     * Gets a single value.
     * 
     * @param point indices, 0 ... <code>ATTR.RES</code> - 1
     * @param value at <code>point</code>
     */
    public double get(int[] point) {
        return VALUE[toIndex(point)];
    }
    /**
     * Serializes values.
     * 
     * @return serial value description, for up to 3 dimensions compatible with
     * Gnuplot
     */
    public String serialize() {
        StringBuilder out = new StringBuilder();
        int[] point = new int[ATTR.DIM];
        double[] coord = new double[ATTR.DIM];
        SCAN:
        while(true) {
            if(point[0] != 0)
                out.append(" ");
            out.append(get(point));
            for(int d = 1; d < ATTR.DIM; ++d)
                if(point[d] == ATTR.RES - 1)
                    out.append("\n");
            if(!iterate(point, coord))
                break;
        }
        return out.toString();
    }
}
