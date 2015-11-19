import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.*;

/**
 * Created by joaquin on 10/11/15.
 */
public class JsonVistitor {

    private CParser parser;

    public JsonVistitor(CParser parser) {
        this.parser = parser;
    }

    public String visit(ParseTree parseTree) {
        if (parseTree instanceof ErrorNode) return visitError((ErrorNode) parseTree);
        else if (parseTree instanceof TerminalNode) return visitTerminal((TerminalNode) parseTree);
        return visitRule((RuleNode) parseTree);
    }

    private String visitError(ErrorNode node) {
        return "{ \"text\" : \"" + appendText(node.getSymbol().getText()) + "\"" + "}, ";
    }

    private String visitTerminal(TerminalNode node) {
        return "{ \"text\" : \"" + appendText(node.getSymbol().getText()) + "\"" + "}, ";
    }
    private String appendText(String text) {
        return text.replaceAll("\"", "");
    }

    private String visitRule(RuleNode parseTree) {
        StringBuilder json = new StringBuilder();
        final RuleContext ctx = parseTree.getRuleContext();
        if (ctx instanceof CParser.TranslationUnitContext) {
            for (int i = 0; i < parseTree.getChildCount(); i++) {
                json.append(visit(parseTree)); //todo: see how to include comma to separate externalDeclarations
            }
            return json.toString();
        }
        return printRule(parseTree);
    }

    private String printRule(RuleNode parseTree) {
        StringBuilder json = new StringBuilder().append("{ \"text\" : \"").append(parseTree.getText());
        final int childCount = parseTree.getChildCount();
        if (childCount > 0) {
            json.append("\", \"nodes\" : [");
            for (int i = 0; i < childCount; i++) {
                final ParseTree child = parseTree.getChild(i);
                json.append(visit(child));
                if (i < childCount -1) json.append(" , ");
            }
            json.append("]");
        }
        return json.append("}").toString();
    }
}
