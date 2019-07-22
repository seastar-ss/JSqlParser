/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.insert;

import java.util.List;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;
import net.sf.jsqlparser.statement.common.HasLimit;
import net.sf.jsqlparser.statement.common.HasOrderBy;
import net.sf.jsqlparser.statement.common.HasWhere;
import net.sf.jsqlparser.statement.select.*;

public class Insert implements Statement, net.sf.jsqlparser.statement.common.HasMainTable, HasLimit, HasOrderBy, HasWhere {

    private Table table;
    private List<Column> columns;
    private ItemsList itemsList;
    private boolean useValues = true;
    private Select select;
    private boolean useSelectBrackets = true;
    private boolean useDuplicate = false;
    private List<Column> duplicateUpdateColumns;
    private List<Expression> duplicateUpdateExpressionList;
    private InsertModifierPriority modifierPriority = null;
    private boolean modifierIgnore = false;

    private boolean returningAllColumns = false;

    private List<SelectExpressionItem> returningExpressionList = null;

    private boolean useSet = false;
    private List<Column> setColumns;
    private List<Expression> setExpressionList;

    @Override
    public void accept(StatementVisitor statementVisitor) {
        statementVisitor.visit(this);
    }

    @Override
    public Table getTable() {
        return table;
    }

    @Override
    public void setTable(Table name) {
        table = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> list) {
        columns = list;
    }

    /**
     * Get the values (as VALUES (...) or SELECT)
     *
     * @return the values of the insert
     */
    public ItemsList getItemsList() {
        return itemsList;
    }

    public void setItemsList(ItemsList list) {
        itemsList = list;
    }

    public boolean isUseValues() {
        return useValues;
    }

    public void setUseValues(boolean useValues) {
        this.useValues = useValues;
    }

    public boolean isReturningAllColumns() {
        return returningAllColumns;
    }

    public void setReturningAllColumns(boolean returningAllColumns) {
        this.returningAllColumns = returningAllColumns;
    }

    public List<SelectExpressionItem> getReturningExpressionList() {
        return returningExpressionList;
    }

    public void setReturningExpressionList(List<SelectExpressionItem> returningExpressionList) {
        this.returningExpressionList = returningExpressionList;
    }

    public Select getSelect() {
        return select;
    }

    public void setSelect(Select select) {
        this.select = select;
    }

    public boolean isUseSelectBrackets() {
        return useSelectBrackets;
    }

    public void setUseSelectBrackets(boolean useSelectBrackets) {
        this.useSelectBrackets = useSelectBrackets;
    }

    public boolean isUseDuplicate() {
        return useDuplicate;
    }

    public void setUseDuplicate(boolean useDuplicate) {
        this.useDuplicate = useDuplicate;
    }

    public List<Column> getDuplicateUpdateColumns() {
        return duplicateUpdateColumns;
    }

    public void setDuplicateUpdateColumns(List<Column> duplicateUpdateColumns) {
        this.duplicateUpdateColumns = duplicateUpdateColumns;
    }

    public List<Expression> getDuplicateUpdateExpressionList() {
        return duplicateUpdateExpressionList;
    }

    public void setDuplicateUpdateExpressionList(List<Expression> duplicateUpdateExpressionList) {
        this.duplicateUpdateExpressionList = duplicateUpdateExpressionList;
    }

    public InsertModifierPriority getModifierPriority() {
        return modifierPriority;
    }

    public void setModifierPriority(InsertModifierPriority modifierPriority) {
        this.modifierPriority = modifierPriority;
    }

    public boolean isModifierIgnore() {
        return modifierIgnore;
    }

    public void setModifierIgnore(boolean modifierIgnore) {
        this.modifierIgnore = modifierIgnore;
    }

    public boolean isUseSet() {
        return useSet;
    }

    public void setUseSet(boolean useSet) {
        this.useSet = useSet;
    }

    public List<Column> getSetColumns() {
        return setColumns;
    }

    public void setSetColumns(List<Column> setColumns) {
        this.setColumns = setColumns;
    }

    public List<Expression> getSetExpressionList() {
        return setExpressionList;
    }

    public void setSetExpressionList(List<Expression> setExpressionList) {
        this.setExpressionList = setExpressionList;
    }

    @Override
    public String toString() {
        StringBuilder sql = new StringBuilder();

        sql.append("INSERT ");
        if (modifierPriority != null) {
            sql.append(modifierPriority.name()).append(" ");
        }
        if (modifierIgnore) {
            sql.append("IGNORE ");
        }
        sql.append("INTO ");
        sql.append(table).append(" ");
        if (columns != null) {
            sql.append(PlainSelect.getStringList(columns, true, true)).append(" ");
        }

        if (useValues) {
            sql.append("VALUES ");
        }

        if (itemsList != null) {
            sql.append(itemsList);
        } else {
            if (useSelectBrackets) {
                sql.append("(");
            }
            if (select != null) {
                sql.append(select);
            }
            if (useSelectBrackets) {
                sql.append(")");
            }
        }

        if (useSet) {
            sql.append("SET ");
            for (int i = 0; i < getSetColumns().size(); i++) {
                if (i != 0) {
                    sql.append(", ");
                }
                sql.append(setColumns.get(i)).append(" = ");
                sql.append(setExpressionList.get(i));
            }
        }

        if (useDuplicate) {
            sql.append(" ON DUPLICATE KEY UPDATE ");
            for (int i = 0; i < getDuplicateUpdateColumns().size(); i++) {
                if (i != 0) {
                    sql.append(", ");
                }
                sql.append(duplicateUpdateColumns.get(i)).append(" = ");
                sql.append(duplicateUpdateExpressionList.get(i));
            }
        }

        if (isReturningAllColumns()) {
            sql.append(" RETURNING *");
        } else if (getReturningExpressionList() != null) {
            sql.append(" RETURNING ").append(PlainSelect.
                    getStringList(getReturningExpressionList(), true, false));
        }

        return sql.toString();
    }

    @Override
    public Limit getLimit() {
        return null;
    }

    @Override
    public void setLimit(Limit limit) {

    }

    @Override
    public List<OrderByElement> getOrderByElements() {
        return null;
    }

    @Override
    public void setOrderByElements(List<OrderByElement> orderByElements) {

    }

    @Override
    public Expression getWhere() {
        return null;
    }

    @Override
    public void setWhere(Expression where) {

    }
}
