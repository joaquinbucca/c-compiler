
public class NodeFactory {
//    public static Node create(Token token) {
//        final Node node;
//
//        if (token == null) {
//            node = new Node(null);
//        } else {
//            switch (token.getType()) {
//                case MyCLexer.Alignas:
//                    node = new IntValue(token);
//                    break;
//                case MyLexer.FLOAT:
//                    node = new FloatValue(token);
//                    break;
//                case MyLexer.BOOLEAN:
//                    node = new BoolValue(token);
//                    break;
//                case MyLexer.STRING:
//                    node = new StringValue(token);
//                    break;
//                case MyLexer.INTEGER_TYPE:
//                    node = new IntType(token);
//                    break;
//                case MyLexer.FLOAT_TYPE:
//                    node = new FloatType(token);
//                    break;
//                case MyLexer.STRING_TYPE:
//                    node = new StringType(token);
//                    break;
//                case MyLexer.BOOLEAN_TYPE:
//                    node = new BoolType(token);
//                    break;
//                case MyLexer.ADD:
//                    node = new Add(token);
//                    break;
//                case MyLexer.SUB:
//                    node = new Sub(token);
//                    break;
//                case MyLexer.MUL:
//                    node = new Mul(token);
//                    break;
//                case MyLexer.DIV:
//                    node = new Div(token);
//                    break;
//                case MyLexer.GT:
//                    node = new GreaterThan(token);
//                    break;
//                case MyLexer.LT:
//                    node = new LowerThan(token);
//                    break;
//                case MyLexer.EQ:
//                    node = new Equals(token);
//                    break;
//                case MyLexer.UNEQ:
//                    node = new UnEquals(token);
//                    break;
//                case MyLexer.OR:
//                    node = new Or(token);
//                    break;
//                case MyLexer.AND:
//                    node = new And(token);
//                    break;
//                case MyLexer.PRINT:
//                    node = new Print(token);
//                    break;
//                case MyLexer.DECL:
//                    node = new Decl(token);
//                    break;
//                case MyLexer.ASSIGN:
//                    node = new Assign(token);
//                    break;
//                case MyLexer.IF:
//                    node = new If(token);
//                    break;
//                case MyLexer.WHILE:
//                    node = new While(token);
//                    break;
//                case MyLexer.THEN:
//                    node = new ThenBlock(token);
//                    break;
//                case MyLexer.ID:
//                    node = new Id(token);
//                    break;
//                case MyLexer.FUNC:
//                    node= new Function(token);
//                    break;
//                case MyLexer.RETURN:
//                    node= new Return(token);
//                    break;
//                case MyLexer.VOID:
//                    node= new Void(token);
//                    break;
//                case MyLexer.OB:
//                    node= new FunctionCallOrParameters(token);
//                    break;
//                case MyLexer.EOF:
//                    node = new Program(token);
//                    break;
//                default:
//                    node = new Node(token);
//            }
//        }
//
//
//        return node;
//    }
}
