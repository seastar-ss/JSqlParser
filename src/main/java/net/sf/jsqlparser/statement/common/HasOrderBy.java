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

import net.sf.jsqlparser.statement.select.OrderByElement;

import java.util.List;

public interface HasOrderBy {
    List<OrderByElement> getOrderByElements();

    void setOrderByElements(List<OrderByElement> orderByElements);
}
