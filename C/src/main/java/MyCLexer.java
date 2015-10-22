
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.RecognitionException;

import java.io.IOException;
import java.io.Reader;

public class MyCLexer extends CLexer {
    private RecognitionException error;

    public MyCLexer(CharStream stream) {
        super(stream);
    }

    public MyCLexer(Reader reader)
        throws IOException
    {
        this(new ANTLRInputStream(reader));
    }

    @Override
    public void recover(RecognitionException re) {
        super.recover(re);
        error = re;
    }

    public RecognitionException getError() {
        return error;
    }
}
