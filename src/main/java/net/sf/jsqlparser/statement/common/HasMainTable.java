package net.sf.jsqlparser.statement.common;

import net.sf.jsqlparser.schema.Table;

public interface HasMainTable {
    Table getTable();

    void setTable(Table name);
}
