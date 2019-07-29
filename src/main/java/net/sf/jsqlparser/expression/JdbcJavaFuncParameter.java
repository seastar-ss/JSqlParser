/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.expression;

import net.sf.jsqlparser.parser.ASTNodeAccessImpl;

import java.util.List;

public class JdbcJavaFuncParameter extends ASTNodeAccessImpl implements Expression {
    /*
    * select * from t_info where id=
     * ${com.test.interface_sql.TestClz#invokeParam(String strParam:"string",Integer intParam:7,List<Long> ids:[0,3,4])}
     * and name in (${getList(List<String> strs:["a","b"])})
     */

    private String funcName;
    private String className;

    private List<JdbcJavaFuncArgs> funcArgsList;

    public JdbcJavaFuncParameter() {
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<JdbcJavaFuncArgs> getFuncArgsList() {
        return funcArgsList;
    }

    public void setFuncArgsList(List<JdbcJavaFuncArgs> funcArgsList) {
        this.funcArgsList = funcArgsList;
    }

    @Override
    public void accept(ExpressionVisitor expressionVisitor) {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("#{");
        sb.append(className == null ? "" : className).append(funcName).append('(');
        if (funcArgsList != null && funcArgsList.size() > 0) {
            for (JdbcJavaFuncArgs item : funcArgsList)
                sb.append(item);
        }
        sb.append(")}");
        return sb.toString();
    }
}
