package net.sf.jsqlparser.statement.common;

import net.sf.jsqlparser.statement.select.OrderByElement;

import java.util.List;

public interface HasOrderBy {
    List<OrderByElement> getOrderByElements();

    void setOrderByElements(List<OrderByElement> orderByElements);
}
