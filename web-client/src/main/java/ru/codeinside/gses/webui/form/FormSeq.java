/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * Copyright (c) 2013, MPL CodeInside http://codeinside.ru
 */

package ru.codeinside.gses.webui.form;

import com.vaadin.ui.Form;
import ru.codeinside.gses.activiti.forms.FormID;

import java.io.Serializable;
import java.util.List;

public interface FormSeq extends Serializable {

  String getCaption();

  /**
   * Заполненные поля в порядке заполнения.
   */
  List<FormField> getFormFields();


  /**
   * Создание формы на основе предыдущей.
   */
  Form getForm(FormID formId, FormSeq previous);
}
