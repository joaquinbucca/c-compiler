import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Utils;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Trees;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joaquin on 10/11/15.
 */
public class JsonPrinterListener extends CBaseListener {

    private final List<String> ruleNames;
    private final StringBuilder builder = new StringBuilder();

    public JsonPrinterListener(Parser parser) {
        ruleNames = Arrays.asList(parser.getRuleNames());
    }

    public JsonPrinterListener(List<String> ruleNames) {
        this.ruleNames = ruleNames;
    }


    @Override
    public String toString() {
        return builder.toString();
    }
    private String getRuleName(CParser.CompilationUnitContext ctx) {
        final int ruleIndex = ctx.getRuleIndex();
        final String ruleName;
        if (ruleIndex >= 0 && ruleIndex < ruleNames.size()) {
            ruleName = ruleNames.get(ruleIndex);
        }
        else {
            ruleName = Integer.toString(ruleIndex);
        }
        return ruleName;
    }






    @Override
    public void enterCompilationUnit(CParser.CompilationUnitContext ctx) {
        final String ruleName = getRuleName(ctx);
        builder.append("{ \"name\" : \"").append(ruleName).append("\"");
        builder.append(", \"children\" : [");
    }
    @Override
    public void exitCompilationUnit(CParser.CompilationUnitContext ctx) {
        builder.append("]}");
    }

}
