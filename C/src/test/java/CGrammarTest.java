import junit.framework.Assert;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class CGrammarTest {


    @Test
    public void testSimpleC() throws IOException {
        final Compiler compiler = new Compiler();
        final ParseTree ast = compiler.parse("/* Airport simulation */\n" +
                "\n" +
                "#include caca.h;" +
                "\n" +
                "#define MAX;" +
                "\n" +
                "typedef struct\n" +
                "{\n" +
                "\tint id ;\n" +
                "\tint tm ;\n" +
                "} plane;\n" +
                "\n", false);

//            final JsonVistitor jsonVistitor = new JsonVistitor();
//            final String visit = jsonVistitor.visit(ast);
//            System.out.println(visit);
        final TreePrinterListener treePrinterListener = new TreePrinterListener(compiler.getParser());
        ParseTreeWalker.DEFAULT.walk(treePrinterListener, ast);
        final String formatted = treePrinterListener.toString();
        System.out.println(formatted);
//        System.out.println(ast);
        Assert.assertNotNull(ast);
    }
}
