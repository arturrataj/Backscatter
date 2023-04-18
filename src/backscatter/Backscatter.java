package backscatter;

import pl.gliwice.iitis.hedgeelleth.compiler.util.image.Array2d;

/**
 * CLI.
 * 
 * @author Artur Rataj
 */
public class Backscatter {
    public static void main(String[] args) {
        final int RES = 16;
        TransferAttr attr = new TransferAttr(2, RES);
        Transfer tf = new Transfer(attr);
        AbstractTransferFill fill = new ExampleTf();
        tf.fill(fill);
        System.out.println(tf.serialize());
    }
    
}
