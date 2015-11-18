import com.strategicgains.hyperexpress.HyperExpress;
import com.strategicgains.hyperexpress.builder.TokenResolver;
import com.strategicgains.hyperexpress.builder.UrlBuilder;
import com.strategicgains.syntaxe.ValidationEngine;
import io.netty.handler.codec.http.HttpMethod;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.restexpress.Request;
import org.restexpress.Response;

import java.io.IOException;
import java.util.UUID;

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
            final TreePrinterListener treePrinterListener = new TreePrinterListener(compiler.getParser());
            ParseTreeWalker.DEFAULT.walk(treePrinterListener, ast);
            final String treeJson = treePrinterListener.toString();

            final TokenResolver resolver = HyperExpress.bind("parseId", UUID.fromString(treeJson).toString());
            final String locationPattern = request.getNamedUrl(HttpMethod.GET, Constants.PARSE_ROUTE);
            response.addLocationHeader(LOCATION_BUILDER.build(locationPattern, resolver));

            // Construct the response for create...
            response.setResponseCreated();

            return treeJson;
        } catch (final IOException e) {
            return e.getMessage();
        }
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
