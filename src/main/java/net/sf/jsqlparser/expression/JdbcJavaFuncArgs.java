package net.sf.jsqlparser.expression;

import net.sf.jsqlparser.parser.ASTNodeAccessImpl;

import java.util.List;

public class JdbcJavaFuncArgs extends ASTNodeAccessImpl implements Expression  {

    String varName;
    String varDefaultValue;
    List<String> listVarDefaultValue;
    boolean isList;
    String type;

    public String getVarName() {
        return varName;
    }

    public JdbcJavaFuncArgs setVarName(String varName) {
        this.varName = varName;
        return this;
    }

    public String getVarDefaultValue() {
        return varDefaultValue;
    }

    public JdbcJavaFuncArgs setVarDefaultValue(String varDefaultValue) {
        this.varDefaultValue = varDefaultValue;
        return this;
    }

    public boolean isList() {
        return isList;
    }

    public JdbcJavaFuncArgs setList(boolean list) {
        isList = list;
        return this;
    }

    public String getType() {
        return type;
    }

    public JdbcJavaFuncArgs setType(String type) {
        this.type = type;
        return this;
    }

    public List<String> getListVarDefaultValue() {
        return listVarDefaultValue;
    }

    public JdbcJavaFuncArgs setListVarDefaultValue(List<String> listVarDefaultValue) {
        this.listVarDefaultValue = listVarDefaultValue;
        return this;
    }

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {

    }


}
