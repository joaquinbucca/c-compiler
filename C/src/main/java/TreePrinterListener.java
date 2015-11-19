import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joaquin on 10/11/15.
 */
public class TreePrinterListener implements ParseTreeListener {
    private final List<String> ruleNames;
    private final StringBuilder builder = new StringBuilder();
    private static Map<String, Integer> occurrences= new HashMap<String, Integer>();

    public TreePrinterListener(Parser parser) {
        ruleNames = Arrays.asList(parser.getRuleNames());
    }

    public TreePrinterListener(List<String> ruleNames) {
        this.ruleNames = ruleNames;
    }

    public static void resetOccurrencesMap(){
        occurrences= new HashMap<String, Integer>();
    }

    public static Integer getOccurrence(String leaf){
        return occurrences.containsKey(leaf) ? occurrences.get(leaf) : 0;
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        if (builder.length() > 0) {
            builder.append(' ');
        }
        final String text = node.getSymbol().getText();
        if (occurrences.containsKey(text)) occurrences.put(text, occurrences.get(text) +1);
        else occurrences.put(text, 1);
        builder.append("{ \"text\" : \"").append(appendText(text)).append("\"");
        builder.append("}, ");
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        if (builder.length() > 0) {
            builder.append(' ');
        }
        builder.append("{ \"text\" : \"").append(appendText(node.getSymbol().getText())).append("\"");
        builder.append("}, ");
    }

    private String appendText(String text) {
        return text.replaceAll("\"", "");
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        if (builder.length() > 0) {
            builder.append(' ');
        }



        final int ruleIndex = ctx.getRuleIndex();
        final String ruleName;
        if (ruleIndex >= 0 && ruleIndex < ruleNames.size()) {
            ruleName = ruleNames.get(ruleIndex);
        }
        else {
            ruleName = Integer.toString(ruleIndex);
        }
        builder.append("{ \"text\" : \"").append(ruleName).append("\"");
        if (ctx.getChildCount() != 0) builder.append(", \"nodes\" : [");
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        final int i = builder.lastIndexOf(", ");
        final int length = builder.length();
        if (i == length - 2) builder.replace(i, length, "");
        if (ctx.getChildCount() == 0) builder.append("}, ");
        else builder.append("]}, ");
    }

    @Override
    public String toString() {
        final String response = builder.toString();
//        return "{\"text\": \"One\", \"nodes\": [{\"text\": \"Two\"}, {\"text\": \"Threeeeee\"}, {\"text\": \"Four\"}]}";
        return response.substring(0, response.length() - 2);
    }

}
