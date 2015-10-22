import org.antlr.v4.runtime.RecognitionException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 *
 */
public class Compiler {
    public static void main(String[] args)
        throws IOException, RecognitionException
    {
        final Compiler compiler = new Compiler();

        // Validate arguments
        if (args.length == 0) {
            System.out.println("Please, specify some files to compile...");
            System.exit(1);
        }

        // Process each file
        for (final String arg : args) {
            final FileReader file = new FileReader(arg);
            try {
                final Node ast = compiler.parse(file, true);
                System.out.println("TREE:\n" + ast + "\n");
            }
            finally {
                file.close();
            }
        }
    }

    protected Node parse(String code, boolean abortOnError)
        throws IOException, RecognitionException
    {
        return parse(new StringReader(code), abortOnError);
    }

    protected Node parse(Reader reader, boolean abortOnError)
        throws IOException, RecognitionException
    {
        final MyCParser parser = new MyCParser(reader);
        final CParser.UnaryExpressionContext program = parser.unaryExpression();

        if (abortOnError && parser.getError() != null) {
            throw parser.getError();
        }

        return (Node) program.getRuleContext();
    }
}
