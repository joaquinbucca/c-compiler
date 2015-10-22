
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.ParserATNSimulator;

import java.io.IOException;
import java.io.Reader;

public class MyCParser extends CParser {
    private RecognitionException error = null;
    private final MyCLexer lexer;

    public MyCParser(Reader reader)
        throws IOException
    {
        this(new MyCLexer(reader));
    }

    public MyCParser(MyCLexer lexer) {
        super(new CommonTokenStream(lexer));
        this.lexer = lexer;
    }

    public RecognitionException getError() {

        if (error == null) {
            error = lexer.getError();
        }
        
        return error;
    }


//    private CommonTreeAdaptor createAdaptor() {
//        return new CommonTreeAdaptor() {
//            public Object create(Token token) {
//                return NodeFactory.create(token);
//            }
//        };
//    }
}
