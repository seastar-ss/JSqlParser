/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.util;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.values.ValuesStatement;

import java.util.ArrayList;
import java.util.List;

public final class SelectUtils {

    private static final String NOT_SUPPORTED_YET = "Not supported yet.";

    private SelectUtils() {
    }

    public static Select buildSelectFromTableAndExpressions(Table table, Expression... expr) {
        SelectItem[] list = new SelectItem[expr.length];
        for (int i = 0; i < expr.length; i++) {
            list[i] = new SelectExpressionItem(expr[i]);
        }
        return buildSelectFromTableAndSelectItems(table, list);
    }

    public static Select buildSelectFromTableAndExpressions(Table table, String... expr) throws JSQLParserException {
        SelectItem[] list = new SelectItem[expr.length];
        for (int i = 0; i < expr.length; i++) {
            list[i] = new SelectExpressionItem(CCJSqlParserUtil.parseExpression(expr[i]));
        }
        return buildSelectFromTableAndSelectItems(table, list);
    }

    public static Select buildSelectFromTableAndSelectItems(Table table, SelectItem... selectItems) {
        Select select = new Select();
        PlainSelect body = new PlainSelect();
        body.addSelectItems(selectItems);
        body.setFromItem(table);
        select.setSelectBody(body);
        return select;
    }

    /**
     * Builds select * from table.
     *
     * @param table
     * @return
     */
    public static Select buildSelectFromTable(Table table) {
        return buildSelectFromTableAndSelectItems(table, new AllColumns());
    }

    /**
     * Adds an expression to select statements. E.g. a simple column is an expression.
     *
     * @param select
     * @param expr
     */
    public static void addExpression(Select select, final Expression expr) {
        select.getSelectBody().accept(new SelectVisitor() {

            @Override
            public void visit(PlainSelect plainSelect) {
                plainSelect.getSelectItems().add(new SelectExpressionItem(expr));
            }

            @Override
            public void visit(SetOperationList setOpList) {
                throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
            }

            @Override
            public void visit(WithItem withItem) {
                throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
            }

            @Override
            public void visit(ValuesStatement aThis) {
                throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
            }
        });
    }

    /**
     * Adds a simple join to a select statement. The introduced join is returned for more
     * configuration settings on it (e.g. left join, right join).
     *
     * @param select
     * @param table
     * @param onExpression
     * @return
     */
    public static Join addJoin(Select select, final Table table, final Expression onExpression) {
        if (select.getSelectBody() instanceof PlainSelect) {
            PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
            List<Join> joins = plainSelect.getJoins();
            if (joins == null) {
                joins = new ArrayList<Join>();
                plainSelect.setJoins(joins);
            }
            Join join = new Join();
            join.setRightItem(table);
            join.setOnExpression(onExpression);
            joins.add(join);
            return join;
        }
        throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
    }

    /**
     * Adds group by to a plain select statement.
     *
     * @param select
     * @param expr
     */
    public static void addGroupBy(Select select, final Expression expr) {
        select.getSelectBody().accept(new SelectVisitor() {

            @Override
            public void visit(PlainSelect plainSelect) {
                plainSelect.addGroupByColumnReference(expr);
            }

            @Override
            public void visit(SetOperationList setOpList) {
                throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
            }

            @Override
            public void visit(WithItem withItem) {
                throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
            }

            @Override
            public void visit(ValuesStatement aThis) {
                throw new UnsupportedOperationException(NOT_SUPPORTED_YET);
            }
        });
    }

    /**
     * List the toString out put of the objects in the List that can be comma separated. If the List
     * is null or empty an empty string is returned.
     *
     * @param list        list of objects with toString methods
     * @param useComma    true if the list has to be comma separated
     * @param useBrackets true if the list has to be enclosed in brackets
     * @return comma separated list of the elements in the list
     */
    public static String getStringList(List<?> list, boolean useComma, boolean useBrackets) {
        StringBuilder ans = new StringBuilder();
//        String ans = "";
        String comma = ",";
        if (!useComma) {
            comma = "";
        }
        if (list != null) {
            if (useBrackets) {
                ans.append("(");
//                ans += "(";
            }

            for (int i = 0; i < list.size(); i++) {
                ans.append(list.get(i)).append((i < list.size() - 1) ? comma + " " : "");
//                ans += "" + list.get(i) + ((i < list.size() - 1) ? comma + " " : "");
            }

            if (useBrackets) {
                ans.append(")");
//                ans += ")";
            }
        }

        return ans.toString();
    }
}
