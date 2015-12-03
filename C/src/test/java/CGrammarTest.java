import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class CGrammarTest {


    @Test
    public void testSimpleC() throws IOException {
        final Compiler compiler = new Compiler();
        String code =
                "#include airport.h;" +
                "\n" +
                "#define MAX;" +
                "\n" +
                "typedef struct\n" +
                "{\n" +
                "\tint id ;\n" +
                "\tint tm ;\n" +
                "} plane;\n" +
                "\n";
        final ParseTree ast = compiler.parse(code, false);

        Assert.assertNotNull(ast);

        final TreePrinterListener treePrinterListener = new TreePrinterListener(compiler.getParser());
        ParseTreeWalker.DEFAULT.walk(treePrinterListener, ast);
        final String formatted = treePrinterListener.toString();
        System.out.println(formatted);


        final String reconstructedFileWithoutSpaces= treePrinterListener.getLeafsAsText();
        code = getStringWithoutSpaces(code);
        Assert.assertArrayEquals(reconstructedFileWithoutSpaces.getBytes(), code.getBytes());
    }

    private String getStringWithoutSpaces(String code) {
        String code1 = code.replaceAll("\\s+", "");
        code1 = code1.replaceAll("\n", "");
        code1 = code1.replaceAll("\t", "");
        return code1;
    }
}
