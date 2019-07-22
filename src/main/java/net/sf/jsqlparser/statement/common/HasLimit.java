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

import net.sf.jsqlparser.statement.select.Limit;

public interface HasLimit {
    Limit getLimit();

    void setLimit(Limit limit);
}
