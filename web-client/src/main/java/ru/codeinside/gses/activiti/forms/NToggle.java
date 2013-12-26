/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * Copyright (c) 2013, MPL CodeInside http://codeinside.ru
 */

package ru.codeinside.gses.activiti.forms;

final class NToggle implements ToggleNode {

  final String id;
  final PropertyType propertyType;
  final PropertyNode propertyNode;
  final String toggleValue;
  final boolean toggleTo;
  final PropertyNode[] toggleNodes;

  NToggle(final String id, final PropertyType propertyType,
          final PropertyNode propertyNode, final String toggleValue, final boolean toggleTo, final PropertyNode[] toggleNodes) {
    this.id = id;
    this.propertyType = propertyType;
    this.propertyNode = propertyNode;
    this.toggleValue = toggleValue;
    this.toggleTo = toggleTo;
    this.toggleNodes = toggleNodes;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public PropertyType getPropertyType() {
    return propertyType;
  }


  @Override
  public PropertyNode getToggler() {
    return propertyNode;
  }

  @Override
  public String getToggleValue() {
    return toggleValue;
  }

  @Override
  public boolean getToggleTo() {
    return toggleTo;
  }

  @Override
  public PropertyNode[] getToggleNodes() {
    return toggleNodes;
  }

  @Override
  public String getUnderline() {
    return null;
  }

  @Override
  public String getTip() {
    return null;
  }

  @Override
  public NullAction getNullAction() {
    return NullAction.skip;
  }

  @Override
  public boolean isReadable() {
    return true;
  }

  @Override
  public boolean isWritable() {
    return true;
  }
}
