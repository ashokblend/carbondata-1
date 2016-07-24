/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.carbondata.core.carbon.metadata.schema.table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.carbondata.core.constants.CarbonCommonConstants;

/**
 * It has table properties details
 */
public class TableProperties implements Serializable {


  /**
   *
   */
  private static final long serialVersionUID = 1L;


  private List<String> dictionaryIncludedColumns =
      new ArrayList<>(CarbonCommonConstants.DEFAULT_COLLECTION_SIZE);

  private List<String> dictionaryExcludedColumns =
      new ArrayList<>(CarbonCommonConstants.DEFAULT_COLLECTION_SIZE);

  private List<List<String>> columnGroupColumns =
      new ArrayList<>(CarbonCommonConstants.DEFAULT_COLLECTION_SIZE);

  public TableProperties(Map<String, String> tableProperties) {
    init(tableProperties);
  }

  private void init(Map<String, String> tableProperties) {
    if (null == tableProperties) {
      return;
    }
    String dictIncludeColStr = tableProperties.get(CarbonCommonConstants.DICTIONARY_INCLUDE);
    if (null != dictIncludeColStr) {
      String[] dictIncludeCols = dictIncludeColStr.split(",");
      for (String dictIncludeCol : dictIncludeCols) {
        dictionaryIncludedColumns.add(dictIncludeCol);
      }
    }
    String dictExcludeColStr = tableProperties.get(CarbonCommonConstants.DICTIONARY_EXCLUDE);
    if (null != dictExcludeColStr) {
      String[] dictExcludeCols = dictExcludeColStr.split(",");
      for (String dictExcludeCol : dictExcludeCols) {
        dictionaryExcludedColumns.add(dictExcludeCol);
      }
    }

    String colGroupsStr = tableProperties.get(CarbonCommonConstants.COLUMN_GROUPS);
    if (null != colGroupsStr) {
      Matcher matcher = Pattern.compile("\\(([^)]+)\\)").matcher(colGroupsStr);
      while (matcher.find()) {
        List<String> colList = new ArrayList<>(CarbonCommonConstants.DEFAULT_COLLECTION_SIZE);
        String oneGroup = matcher.group(1);
        String[] colGrpStr = oneGroup.split(",");
        for (String col : colGrpStr) {
          colList.add(col);
        }
        columnGroupColumns.add(colList);
      }
    }

  }

  public boolean isDictionaryIncludeColumn(String column) {
    return dictionaryIncludedColumns.contains(column);
  }

  public boolean isDictionaryExcludeColumn(String column) {
    return dictionaryIncludedColumns.contains(column);
  }

  public boolean isColumnGroupColumn(String column) {

    for (List<String> colList : columnGroupColumns) {
      if (colList.contains(column)) {
        return true;
      }
    }
    return false;
  }
}
