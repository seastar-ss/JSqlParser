package net.sf.jsqlparser.statement.common;

import net.sf.jsqlparser.schema.Table;

public interface HasColumnExpression {
    boolean addColExpression(Table table, String column,String alias);
    int removeAllColExpression();

}
