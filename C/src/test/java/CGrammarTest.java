import junit.framework.Assert;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;

public class CGrammarTest {


    @Test public void testSimpleC() throws IOException {
        final Compiler compiler = new Compiler();
        final ParseTree ast = compiler.parse("main(){\n" +
                "    printf(\"Hello World\");\n" +
                "}", false);

        System.out.println(ast);
        Assert.assertNotNull(ast);
    }
}
