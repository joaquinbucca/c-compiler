import org.antlr.v4.runtime.tree.*;

/**
 * Created by joaquin on 10/11/15.
 */
public class JsonVistitor {

    public String visit(ParseTree parseTree) {
        String json = "{ \"name\" : \"" + parseTree.getText() +"\", \"children\" : [";
        for (int i = 0; i < parseTree.getChildCount(); i++) {
            final ParseTree child = parseTree.getChild(i);
            json += visit(child);
            if (i < parseTree.getChildCount() -1) json+= " , ";
        }
        return json + "]}";
    }

}
