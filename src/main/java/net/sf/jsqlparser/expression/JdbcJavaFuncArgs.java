package net.sf.jsqlparser.expression;

import net.sf.jsqlparser.parser.ASTNodeAccessImpl;

public class JdbcJavaFuncArgs extends ASTNodeAccessImpl implements Expression  {

    String varName;
    String varDefaultValue;


    @Override
    public void accept(ExpressionVisitor expressionVisitor) {

    }


}
