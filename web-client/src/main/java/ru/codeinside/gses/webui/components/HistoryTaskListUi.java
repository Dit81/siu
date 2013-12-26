/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * Copyright (c) 2013, MPL CodeInside http://codeinside.ru
 */

package ru.codeinside.gses.webui.components;

import org.activiti.engine.history.HistoricTaskInstance;

import ru.codeinside.gses.webui.components.api.IRefresh;
import ru.codeinside.gses.webui.data.AbstractLazyLoadingQuery;
import ru.codeinside.gses.webui.data.HistoryTaskListQuery;

import com.vaadin.ui.Component;

public class HistoryTaskListUi extends BaseListUi<HistoricTaskInstance> implements IRefresh {

	private static final long serialVersionUID = -5501805681178998840L;

	public HistoryTaskListUi(AbstractLazyLoadingQuery<HistoricTaskInstance> loadingQuery) {
		super(loadingQuery);

		itemsTable.addContainerProperty("id", Component.class, null);
		itemsTable.addContainerProperty("name", String.class, null);
		itemsTable.addContainerProperty("exe", String.class, null);

		itemsTable.setVisibleColumns(HistoryTaskListQuery.NATURAL_COL_ORDER);
		itemsTable.setColumnHeaders(HistoryTaskListQuery.COL_HEADERS_ENGLISH);
	}
}
