import com.strategicgains.hyperexpress.builder.UrlBuilder;
import com.strategicgains.syntaxe.ValidationEngine;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.restexpress.Request;
import org.restexpress.Response;

import java.io.IOException;

public class CompilerController {

    private final Compiler compiler;
    private static final UrlBuilder LOCATION_BUILDER = new UrlBuilder();

    public CompilerController() {
        compiler= new Compiler();
    }

    public String create(Request request, Response response)
    {
        try {
            final CFile cFile = request.getBodyAs(CFile.class, "C file not provided");
            ValidationEngine.validateAndThrow(cFile);
            final ParseTree ast = compiler.parse(cFile.getContent(), false);
            TreePrinterListener.resetOccurrencesMap();
            final TreePrinterListener treePrinterListener = new TreePrinterListener(compiler.getParser());
            ParseTreeWalker.DEFAULT.walk(treePrinterListener, ast);
            final String treeJson = treePrinterListener.toString();


            // Construct the response for create...
            response.setResponseCreated();

            return treeJson;
        } catch (final IOException e) {
            return e.getMessage();
        }
    }

    public String read(Request request, Response response) {
        final String leaf = request.getHeader("leaf");
        final Integer occurrence = TreePrinterListener.getOccurrence(leaf);
        return "{\"name\" : \""+leaf+"\" , \"occurrences\" : "+occurrence+"}";
    }

    private static class CFile {
        public String content;

        public CFile() {
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


}
