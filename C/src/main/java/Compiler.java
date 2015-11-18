import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.restexpress.RestExpress;
import org.restexpress.serialization.DefaultSerializationProvider;
import org.restexpress.serialization.SerializationProvider;
import org.restexpress.util.Environment;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 *
 */
public class Compiler {
    public  MyCParser parser = null;
    private static final String SERVICE_NAME = "Blogging Example";
    public static void main(String[] args)
        throws IOException, RecognitionException
    {
        final Compiler compiler = new Compiler();

        // Validate arguments
//        if (args.length == 0) {
//            System.out.println("Please, specify some files to compile...");
//            System.exit(1);
//        }

        RestExpress.setDefaultSerializationProvider(new DefaultSerializationProvider());
        final Configuration configuration= Environment.load(args, Configuration.class);
        final RestExpress server = new RestExpress()
                .setName(SERVICE_NAME)
                .setBaseUrl(configuration.getBaseUrl());

        Routes.define(configuration, server);
        Relationships.define(server);
        server.bind(configuration.getPort());
        server.awaitShutdown();
//        // Process each file
//        for (final String arg : args) {
//            final FileReader file = new FileReader(arg);
//            try {
//                final ParseTree ast = compiler.parse(file, true);
//                System.out.println("TREE:\n" + ast + "\n");
//            }
//            finally {
//                file.close();
//            }
//        }
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
