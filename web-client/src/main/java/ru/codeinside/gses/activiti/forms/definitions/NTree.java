/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * Copyright (c) 2013, MPL CodeInside http://codeinside.ru
 */

package ru.codeinside.gses.activiti.forms.definitions;

import com.google.common.collect.ImmutableMap;
import ru.codeinside.gses.activiti.forms.api.definitions.PropertyNode;
import ru.codeinside.gses.activiti.forms.api.definitions.PropertyTree;
import ru.codeinside.gses.activiti.forms.api.duration.DurationPreference;

import java.util.Arrays;
import java.util.Map;

final class NTree implements PropertyTree {

  final PropertyNode[] nodes;
  final ImmutableMap<String, PropertyNode> index;
  final DurationPreference durationPreference;
  final String formKey;
  private final boolean signatureRequired;

  public NTree(PropertyNode[] nodes, Map<String, PropertyNode> index, DurationPreference durationPreference, String formKey, boolean signatureRequired) {
    this.nodes = nodes;
    this.signatureRequired = signatureRequired;
    this.index = ImmutableMap.copyOf(index);
    this.durationPreference = durationPreference;
    this.formKey = formKey;
  }

  @Override
  public PropertyNode[] getNodes() {
    return nodes;
  }

  @Override
  public ImmutableMap<String, PropertyNode> getIndex() {
    return index;
  }

  @Override
  public DurationPreference getDurationPreference() {
    return durationPreference;
  }

  @Override
  public String getFormKey() {
    return formKey;
  }

  @Override
  public boolean isSignatureRequired() {
    return signatureRequired;
  }

  @Override
  public String toString() {
    return "{nodes=" + Arrays.toString(nodes).replaceAll("\\},", "},\n").replaceAll("\\[\\{", "\n[{") +
      ", durationPreference=" + durationPreference +
      ", formKey='" + formKey + '\'' +
      '}';
  }
}
