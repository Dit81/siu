/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * Copyright (c) 2014, MPL CodeInside http://codeinside.ru
 */

package ru.codeinside.adm.ui.employee;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.provider.CachingLocalEntityProvider;
import com.vaadin.addon.jpacontainer.util.DefaultQueryModifierDelegate;
import com.vaadin.data.Property;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import org.tepi.filtertable.FilterTable;
import ru.codeinside.adm.database.Employee;
import ru.codeinside.adm.ui.DateColumnGenerator;
import ru.codeinside.adm.ui.FilterDecorator_;
import ru.codeinside.jpa.ActivitiEntityManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

/**
 * Виджет для просмотра всех сотрудников
 */
public class TableAllEmployee extends TableEmployee {

  final FilterTable table = new FilterTable();
  HorizontalLayout hl = new HorizontalLayout();
  private Button show = createButton("Просмотр");
  private Button edit = createButton("Редактирование");
  private Button unblock = createButton("Разблокировать");
  private Button block = createButton("Заблокировать");

  public TableAllEmployee(final boolean lockedFilterValue) {
    super();
    this.lockedFilterValue = lockedFilterValue;

    setSizeFull();
    setSpacing(true);
    table.setSizeFull();
    table.setFilterBarVisible(true);
    table.setSelectable(true);
    table.setImmediate(true);
    table.setRowHeaderMode(Table.ROW_HEADER_MODE_HIDDEN);
    table.setColumnCollapsingAllowed(true);
    table.setColumnReorderingAllowed(true);
    table.setMultiSelect(false);
    table.addListener(new Property.ValueChangeListener() {
      @Override
      public void valueChange(Property.ValueChangeEvent event) {
        if (event.getProperty().getValue() == null) {
          buttonVisibleFalse();
        } else {
          show.setVisible(true);
          edit.setVisible(true);
          if (lockedFilterValue) {
            hl.removeComponent(block);
            unblock.setVisible(true);
            hl.addComponent(unblock);
          } else {
            hl.removeComponent(unblock);
            block.setVisible(true);
            hl.addComponent(block);
          }
        }
      }
    });
    buildContainer(table);
    table.setColumnHeaders(new String[]{
      "Логин",
      "ФИО",
      "Права",
      "Дата регистрации",
      "Создатель",
      "Организация"});
    table.setFilterDecorator(new FilterDecorator_());
    buttonVisibleFalse();
    addContextMenu(table);
    addComponent(table);
    setExpandRatio(table, 1f);
  }

  private void buildContainer(FilterTable table) {
    final JPAContainer<Employee> container = new JPAContainer<Employee>(Employee.class);
    container.setReadOnly(true);
    container.setEntityProvider(new CachingLocalEntityProvider<Employee>(Employee.class, ActivitiEntityManager.INSTANCE));
    container.getEntityProvider().setQueryModifierDelegate(new DefaultQueryModifierDelegate() {
      @Override
      public void queryHasBeenBuilt(CriteriaBuilder criteriaBuilder, CriteriaQuery<?> query) {
        if (query.getSelection().getJavaType().equals(Long.class)) {
          Root root = query.getRoots().iterator().next();
          Selection x = criteriaBuilder.countDistinct(root);
          query.select(x);
        } else {
          query.distinct(true);
        }
      }
    });
    container.addNestedContainerProperty("organization.name");
    container.addContainerFilter(new Compare.Equal("locked", lockedFilterValue));
    table.setContainerDataSource(container);
    table.setVisibleColumns(new Object[]{"login", "fio", "roles", "date", "creator", "organization.name"});
    table.addGeneratedColumn("date", new DateColumnGenerator("dd.MM.yyyy HH:mm:ss"));
    table.addGeneratedColumn("roles", new RolesColumn());
    table.setColumnExpandRatio("login", 1f);
    table.setColumnExpandRatio("fio", 1.5f);
    table.setColumnExpandRatio("roles", 4f);
    table.setColumnExpandRatio("date", 1f);
    table.setColumnExpandRatio("creator", 1f);
    table.setColumnExpandRatio("organization.name", 1f);
  }

  public void addButtonToLayout(HorizontalLayout hr) {
    this.hr = hr;
    hl.setSpacing(true);
    hl.addComponent(show);
    hl.addComponent(edit);
    hr.addComponent(hl);
  }

  private Button createButton(String caption) {
    Button button = null;
    if (caption.equals("Просмотр")) {
      button = new Button(caption, new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
          view(table);
          buttonVisibleFalse();
          hr.setVisible(false);
        }
      });
    } else if (caption.equals("Редактирование")) {
      button = new Button(caption, new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
          edit(table);
          buttonVisibleFalse();
          hr.setVisible(false);
        }
      });
    } else if (caption.equals("Разблокировать")) {
      button = new Button(caption, new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
          unlockUserActionHandler(table);
        }
      });
    } else if (caption.equals("Заблокировать")) {
      button = new Button(caption, new Button.ClickListener() {
        @Override
        public void buttonClick(Button.ClickEvent event) {
          lockUserActionHandler(table);
        }
      });
    }
    return button;
  }

  private void buttonVisibleFalse() {
    show.setVisible(false);
    edit.setVisible(false);
    unblock.setVisible(false);
    block.setVisible(false);
  }
}
