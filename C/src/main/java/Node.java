import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.RuleNode;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Stack;

public class Node extends RuleContext implements Opcodes {


    private static final long serialVersionUID = 2493562359138766863L;

    public Node() {
        super();
    }

    public Node getChild(int i) {
        return (Node) super.getChild(i);
    }

    public void toString(StringBuilder builder, String indent) {
        builder.append(indent);
        builder.append(getClass().getSimpleName());
        builder.append("<").append(getText()).append(">");
        builder.append('\n');

        final int count = getChildCount();
        for (int i = 0; i < count; i++) {
            getChild(i).toString(builder, indent + "   ");
        }
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        toString(builder, "");

        return builder.toString();
    }
}
