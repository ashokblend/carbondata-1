/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.carbondata.spark.util

import java.io.File

import org.apache.spark.sql.{CarbonEnv, CarbonRelation}
import org.apache.spark.sql.common.util.CarbonHiveContext
import org.apache.spark.sql.common.util.CarbonHiveContext.sql
import org.apache.spark.sql.common.util.QueryTest

import org.carbondata.core.cache.dictionary.DictionaryColumnUniqueIdentifier
import org.carbondata.core.carbon.{CarbonDataLoadSchema, CarbonTableIdentifier}
import org.carbondata.core.constants.CarbonCommonConstants
import org.carbondata.spark.load.CarbonLoadModel
import org.carbondata.spark.load.CarbonLoaderUtil

import org.scalatest.BeforeAndAfterAll
import java.io.FileWriter
import java.io.BufferedWriter
import java.util.Random
import org.carbondata.core.carbon.metadata.encoder.Encoding
import org.carbondata.core.carbon.path.CarbonStorePath
import org.carbondata.core.carbon.path.CarbonStorePath
import org.carbondata.core.carbon.metadata.schema.table.CarbonTable
import org.carbondata.core.util.CarbonUtil
import org.carbondata.core.util.CarbonProperties

/**
  * Test Case for org.carbondata.spark.util.GlobalDictionaryUtil
  *
  * @date: Apr 10, 2016 10:34:58 PM
  * @See org.carbondata.spark.util.GlobalDictionaryUtil
  */
class AutoHighCardinalityIdentifyTestCase extends QueryTest with BeforeAndAfterAll {

  var filePath: String = _

  def buildCarbonLoadModel(relation: CarbonRelation,
    filePath: String,
    dimensionFilePath: String,
    header: String): CarbonLoadModel = {
    val carbonLoadModel = new CarbonLoadModel
    carbonLoadModel.setTableName(relation.cubeMeta.carbonTableIdentifier.getDatabaseName)
    carbonLoadModel.setDatabaseName(relation.cubeMeta.carbonTableIdentifier.getTableName)
    // carbonLoadModel.setSchema(relation.cubeMeta.schema)
    val table = relation.cubeMeta.carbonTable
    val carbonSchema = new CarbonDataLoadSchema(table)
    carbonLoadModel.setDatabaseName(table.getDatabaseName)
    carbonLoadModel.setTableName(table.getFactTableName)
    carbonLoadModel.setCarbonDataLoadSchema(carbonSchema)
    carbonLoadModel.setFactFilePath(filePath)
    carbonLoadModel.setDimFolderPath(dimensionFilePath)
    carbonLoadModel.setCsvHeader(header)
    carbonLoadModel.setCsvDelimiter(",")
    carbonLoadModel.setComplexDelimiterLevel1("\\$")
    carbonLoadModel.setComplexDelimiterLevel2("\\:")
    carbonLoadModel
  }

  override def beforeAll {
    buildTestData
    buildTable
  }

  def buildTestData() = {
    val pwd = new File(this.getClass.getResource("/").getPath + "/../../").getCanonicalPath
    filePath = pwd + "/target/highcarddata.csv"
    val file = new File(filePath)
    val writer = new BufferedWriter(new FileWriter(file))
    writer.write("hc1,c2,c3")
    writer.newLine()
    var i = 0
    val random = new Random
    for(i <- 0 until 2000000) {
      writer.write("a" + i + "," +
          "b" + i%1000 + "," +
          i%1000000 + "\n")
    }
    writer.close
  }

  def buildTable() = {
    try {
      sql("drop table if exists highcard")
      sql("""create table if not exists highcard
             (hc1 string, c2 string, c3 int)
             STORED BY 'org.apache.carbondata.format'""")
    } catch {
      case ex: Throwable => logError(ex.getMessage + "\r\n" + ex.getStackTraceString)
    }
  }

  def relation(tableName: String): CarbonRelation = {
    CarbonEnv.getInstance(CarbonHiveContext).carbonCatalog
        .lookupRelation1(Option("default"), tableName, None)(CarbonHiveContext)
        .asInstanceOf[CarbonRelation]
  }
  
  private def checkDictFile(table: CarbonTable) = {
    val tableIdentifier = new CarbonTableIdentifier(table.getDatabaseName,
        table.getFactTableName, "1")
    val carbonTablePath = CarbonStorePath.getCarbonTablePath(CarbonHiveContext.hdfsCarbonBasePath,
        tableIdentifier)
    val newHc1 = table.getDimensionByName("highcard", "hc1")
    val newC2 = table.getDimensionByName("highcard", "c2")
    val dictFileHc1 = carbonTablePath.getDictionaryFilePath(newHc1.getColumnId)
    val dictFileC2 = carbonTablePath.getDictionaryFilePath(newC2.getColumnId)
    assert(!CarbonUtil.isFileExists(dictFileHc1))
    assert(CarbonUtil.isFileExists(dictFileC2))
  }

  private def checkMetaData(oldTable: CarbonTable, newTable: CarbonTable) = {
    val oldHc1 = oldTable.getDimensionByName("highcard", "hc1")
    val oldc2 = oldTable.getDimensionByName("highcard", "c2")
    val newHc1 = newTable.getDimensionByName("highcard", "hc1")
    val newC2 = newTable.getDimensionByName("highcard", "c2")
    assert(oldHc1.hasEncoding(Encoding.DICTIONARY))
    assert(oldc2.hasEncoding(Encoding.DICTIONARY))
    assert(!newHc1.hasEncoding(Encoding.DICTIONARY))
    assert(newC2.hasEncoding(Encoding.DICTIONARY))
  }

 test("auto identify high cardinality column in first load #396") {
    val oldTable = relation("highcard").cubeMeta.carbonTable
    sql(s"LOAD DATA LOCAL INPATH '$filePath' into table highcard")
    val newTable = relation("highcard").cubeMeta.carbonTable
    sql(s"select count(hc1) from highcard").show

    // check dictionary file
    checkDictFile(newTable)
    // check the meta data
    checkMetaData(oldTable, newTable)
  }
  
  test("skip auto identify high cardinality column for column group") {
    CarbonProperties.getInstance.addProperty(CarbonCommonConstants.HIGH_CARDINALITY_THRESHOLD, "0")
    CarbonProperties.getInstance.addProperty(CarbonCommonConstants.HIGH_CARDINALITY_IN_ROW_COUNT_PERCENTAGE, "0")

    sql("create table normal (column1 string,column2 string,column3 string,column4 string,column5 string,column6 string,column7 string,column8 string,column9 string,column10 string,measure1 int,measure2 int,measure3 int,measure4 int) STORED BY 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/10dim_4msr.csv' INTO table normal options('FILEHEADER'='column1,column2,column3,column4,column5,column6,column7,column8,column9,column10,measure1,measure2,measure3,measure4')");
   
    sql("create table colgrp_highcard (column1 string,column2 string,column3 string,column4 string,column5 string,column6 string,column7 string,column8 string,column9 string,column10 string,measure1 int,measure2 int,measure3 int,measure4 int) STORED BY 'org.apache.carbondata.format' TBLPROPERTIES (\"COLUMN_GROUPS\"=\"(column2,column3,column4),(column7,column8,column9)\")")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/10dim_4msr.csv' INTO table colgrp_highcard options('FILEHEADER'='column1,column2,column3,column4,column5,column6,column7,column8,column9,column10,measure1,measure2,measure3,measure4')");

    
    
    val colGrpTable = relation("colgrp_highcard").cubeMeta.carbonTable
    val normalTable = relation("normal").cubeMeta.carbonTable
   
    // check dictionary file
    val colGrpTableIdentifier = new CarbonTableIdentifier(colGrpTable.getDatabaseName,
        colGrpTable.getFactTableName, "1")
    val colGrpTablePath = CarbonStorePath.getCarbonTablePath(CarbonHiveContext.hdfsCarbonBasePath,
        colGrpTableIdentifier)
    val normalTableIdentifier = new CarbonTableIdentifier(normalTable.getDatabaseName,
        normalTable.getFactTableName, "1")
    val normalTablePath = CarbonStorePath.getCarbonTablePath(CarbonHiveContext.hdfsCarbonBasePath,
        normalTableIdentifier)
        
    val colGrpCol = colGrpTable.getDimensionByName("colgrp_highcard", "column2")
    val normalCol = normalTable.getDimensionByName("normal", "column2")
    val colGrpFile = colGrpTablePath.getDictionaryFilePath(colGrpCol.getColumnId)
    val normalFile = normalTablePath.getDictionaryFilePath(normalCol.getColumnId)
    assert(CarbonUtil.isFileExists(colGrpFile))
    assert(!CarbonUtil.isFileExists(normalFile))
    // check the meta data
    val colGrpCol2 = colGrpTable.getDimensionByName("colgrp_highcard", "column2")
    val normalCol2 = normalTable.getDimensionByName("normal", "column2")
    assert(colGrpCol2.hasEncoding(Encoding.DICTIONARY))
    assert(!normalCol2.hasEncoding(Encoding.DICTIONARY))
   
  }
  test("skip auto identify high cardinality dictionary included column") {
    CarbonProperties.getInstance.addProperty(CarbonCommonConstants.HIGH_CARDINALITY_THRESHOLD, "0")
    CarbonProperties.getInstance.addProperty(CarbonCommonConstants.HIGH_CARDINALITY_IN_ROW_COUNT_PERCENTAGE, "0")
   
    sql("create table dictincluded (column1 string,column2 string,column3 string,column4 string,column5 string,column6 string,column7 string,column8 string,column9 string,column10 string,measure1 int,measure2 int,measure3 int,measure4 int) STORED BY 'org.apache.carbondata.format' TBLPROPERTIES ('DICTIONARY_INCLUDE'='measure1')")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/10dim_4msr.csv' INTO table dictIncluded options('FILEHEADER'='column1,column2,column3,column4,column5,column6,column7,column8,column9,column10,measure1,measure2,measure3,measure4')");

    
    
    val dictIncludeTable = relation("dictincluded").cubeMeta.carbonTable
    
    // check dictionary file
    val dictIncludeTableIdentifier = new CarbonTableIdentifier(dictIncludeTable.getDatabaseName,
        dictIncludeTable.getFactTableName, "1")
    val colGrpTablePath = CarbonStorePath.getCarbonTablePath(CarbonHiveContext.hdfsCarbonBasePath,
        dictIncludeTableIdentifier)
        
    val dictIncludeCol = dictIncludeTable.getDimensionByName("dictincluded", "measure1")
    val dictIncludeFile = colGrpTablePath.getDictionaryFilePath(dictIncludeCol.getColumnId)
    assert(CarbonUtil.isFileExists(dictIncludeFile))
    // check the meta data
    val dictIncludeMsr1 = dictIncludeTable.getDimensionByName("dictincluded", "measure1")
    assert(dictIncludeMsr1.hasEncoding(Encoding.DICTIONARY))
   
  }
  override def afterAll {
  CarbonProperties.getInstance.addProperty(CarbonCommonConstants.HIGH_CARDINALITY_THRESHOLD, CarbonCommonConstants.HIGH_CARDINALITY_THRESHOLD_DEFAULT)
    CarbonProperties.getInstance.addProperty(CarbonCommonConstants.HIGH_CARDINALITY_IN_ROW_COUNT_PERCENTAGE, CarbonCommonConstants.HIGH_CARDINALITY_IN_ROW_COUNT_PERCENTAGE_DEFAULT)
    
  }
    
}
