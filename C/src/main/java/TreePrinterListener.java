import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;
import java.util.List;

/**
 * Created by joaquin on 10/11/15.
 */
public class TreePrinterListener implements ParseTreeListener {
    private final List<String> ruleNames;
    private final StringBuilder builder = new StringBuilder();

    public TreePrinterListener(Parser parser) {
        ruleNames = Arrays.asList(parser.getRuleNames());
    }

    public TreePrinterListener(List<String> ruleNames) {
        this.ruleNames = ruleNames;
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        if (builder.length() > 0) {
            builder.append(' ');
        }
        builder.append("{ \"name\" : \"").append(node.getSymbol().getText()).append("\"");
        builder.append(", \"children\" : []}, ");
//        builder.append(Utils.escapeWhitespace(Trees.getNodeText(node, ruleNames), false));
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        if (builder.length() > 0) {
            builder.append(' ');
        }
        builder.append("{ \"name\" : \"").append(node.getSymbol().getText()).append("\"");
        builder.append(", \"children\" : []}, ");
//        builder.append(Utils.escapeWhitespace(Trees.getNodeText(node, ruleNames), false));
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        if (builder.length() > 0) {
            builder.append(' ');
        }


//        final boolean isLeaf = ctx.getChildCount() <= 0;

        final int ruleIndex = ctx.getRuleIndex();
        final String ruleName;
        if (ruleIndex >= 0 && ruleIndex < ruleNames.size()) {
            ruleName = ruleNames.get(ruleIndex);
//            if (isLeaf) ruleName += "( "+ctx.getPayload().getText()+" )";
        }
        else {
            ruleName = Integer.toString(ruleIndex);
        }
        builder.append("{ \"name\" : \"").append(ruleName).append("\"");
        builder.append(", \"children\" : [");
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        final int i = builder.lastIndexOf(", ");
        final int length = builder.length();
        if (i == length - 2) builder.replace(i, length, "");
        builder.append("]}, ");
    }

    @Override
    public String toString() {
        return builder.toString();
    }

}
