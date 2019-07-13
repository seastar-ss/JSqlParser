/*-
 * #%L
 * JSQLParser library
 * %%
 * Copyright (C) 2004 - 2019 JSQLParser
 * %%
 * Dual licensed under GNU LGPL 2.1 or Apache License 2.0
 * #L%
 */
package net.sf.jsqlparser.statement.create.table;

import net.sf.jsqlparser.util.SelectUtils;

public class NamedConstraint extends Index {

    @Override
    public String toString() {
        String idxSpecText = SelectUtils.getStringList(getIndexSpec(), false, false);
        return (getName() != null ? "CONSTRAINT " + getName() + " " : "")
                + getType() + " " + SelectUtils.getStringList(getColumnsNames(), true, true) + (!"".
                equals(idxSpecText) ? " " + idxSpecText : "");
    }
}
