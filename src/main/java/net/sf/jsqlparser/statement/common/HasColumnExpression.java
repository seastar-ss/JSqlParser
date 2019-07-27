/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.common;

import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;

public interface HasColumnExpression {
    boolean addColExpression(Table table, String column, String alias);

    boolean addColExpression(Column column, String alias);

    int removeAllColExpression();

}
