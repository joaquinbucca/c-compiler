import com.strategicgains.restexpress.plugin.cors.CorsHeaderPlugin;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.restexpress.Flags;
import static io.netty.handler.codec.http.HttpHeaders.Names.*;
import org.restexpress.RestExpress;
import org.restexpress.serialization.DefaultSerializationProvider;
import org.restexpress.serialization.SerializationProvider;
import org.restexpress.util.Environment;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;


public class Compiler {
    public  MyCParser parser = null;

    private static final String SERVICE_NAME = "Tesis";


    public static void main(String[] args)
        throws IOException, RecognitionException
    {
        RestExpress.setDefaultSerializationProvider(new DefaultSerializationProvider());
        final Configuration configuration= Environment.load(args, Configuration.class);
        final RestExpress server = new RestExpress()
                .setName(SERVICE_NAME)
                .setBaseUrl(configuration.getBaseUrl());

        Routes.define(configuration, server);
        Relationships.define(server);
        new CorsHeaderPlugin("*")
                .flag(Flags.Auth.PUBLIC_ROUTE)
                .allowHeaders(CONTENT_TYPE, ACCEPT, AUTHORIZATION, REFERER, LOCATION)
                .exposeHeaders(LOCATION)
                .register(server);
        server.bind(configuration.getPort());
        server.awaitShutdown();
    }

    protected ParseTree parse(String code, boolean abortOnError)
        throws IOException, RecognitionException
    {
        return parse(new StringReader(code), abortOnError);
    }

    public CParser getParser() { return parser;}

    protected ParseTree parse(Reader reader, boolean abortOnError)
        throws IOException, RecognitionException
    {
        parser = new MyCParser(reader);
        final ParseTreeWalker walker = new ParseTreeWalker();
        final CBaseListener listener = new CBaseListener();
        final CParser.CompilationUnitContext program = parser.compilationUnit();
        walker.walk(listener, program);

        if (abortOnError && parser.getError() != null) {
            throw parser.getError();
        }

        return program.getChild(0);
    }
}
