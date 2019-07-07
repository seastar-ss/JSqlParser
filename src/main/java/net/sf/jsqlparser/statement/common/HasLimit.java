package net.sf.jsqlparser.statement.common;

import net.sf.jsqlparser.statement.select.Limit;

public interface HasLimit {
    void setLimit(Limit limit);

    Limit getLimit();
}
