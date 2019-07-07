package net.sf.jsqlparser.statement.common;

import net.sf.jsqlparser.expression.Expression;

public interface HasWhere {
    Expression getWhere();

    void setWhere(Expression where);
}
