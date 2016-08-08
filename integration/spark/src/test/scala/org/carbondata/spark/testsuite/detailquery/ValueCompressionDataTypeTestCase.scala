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
package org.carbondata.spark.testsuite.detailquery

import org.apache.spark.sql.common.util.CarbonHiveContext._
import org.apache.spark.sql.common.util.QueryTest
import org.scalatest.BeforeAndAfterAll

import org.carbondata.core.constants.CarbonCommonConstants
import org.carbondata.core.datastorage.store.impl.FileFactory
import org.carbondata.core.datastorage.store.impl.FileFactory.FileType
import org.carbondata.core.util.CarbonProperties

/**
 * Created by  on 8/8/2016.
 */
class ValueCompressionDataTypeTestCase extends QueryTest with BeforeAndAfterAll {
  val tempDirPath = "./src/test/resources/temp"

  override def beforeAll {
    FileFactory.mkdirs(tempDirPath,FileType.LOCAL)
  }

  test("ActualDataType:double,ChangedDatatype:Short,CompressionType:NonDecimalMaxMin") {
    val tempFilePath = "./src/test/resources/temp/double2short.csv"
    try {
      sql("CREATE TABLE double2short (name String, value double) STORED BY 'org.apache.carbondata.format'")
      sql("CREATE TABLE double2short_hive (name String, value double)row format delimited fields terminated by ','")
      val data ="a,3.141111\nb,3.141212\nc,3.141313\nd,3.141515\ne,3.141616\nf,3.141616\ng,3.141717\nh,3.141818";
      writedata(tempFilePath, data)
      sql(s"LOAD data local inpath '${tempFilePath}' into table double2short options('fileheader'='name,value')")
      sql(s"LOAD data local inpath '${tempFilePath}' into table double2short_hive")
      checkAnswer(sql("select * from double2short"),
        sql("select * from double2short_hive"))

    } catch{
      case ex:Exception => ex.printStackTrace()
                           assert(false)
    } finally {
      sql("drop table if exists double2short")
      sql("drop table if exists double2short_hive")
      deleteFile(tempFilePath)
    }
  }
  
  test("ActualDataType:double,ChangedDatatype:byte,CompressionType:NonDecimalMaxMin") {
    val tempFilePath = "./src/test/resources/temp/double2byte.csv"
    try {
      sql("CREATE TABLE double2byte (name String, value double) STORED BY 'org.apache.carbondata.format'")
      sql("CREATE TABLE double2byte_hive (name String, value double)row format delimited fields terminated by ','")
      val data ="a,4.200001\nb,4.200009";
      writedata(tempFilePath, data)
      sql(s"LOAD data local inpath '${tempFilePath}' into table double2byte options('fileheader'='name,value')")
      sql(s"LOAD data local inpath '${tempFilePath}' into table double2byte_hive")
      checkAnswer(sql("select * from double2byte"),
        sql("select * from double2byte_hive"))

    } catch{
      case ex:Exception => ex.printStackTrace()
                           assert(false)
    } finally {
      sql("drop table if exists double2byte")
      sql("drop table if exists double2byte_hive")
      deleteFile(tempFilePath)
    }
  }

  def writedata(filePath: String, data: String) = {
    val dis = FileFactory.getDataOutputStream(filePath, FileFactory.getFileType(filePath))
    dis.writeBytes(data.toString())
    dis.close()
  }
  def deleteFile(filePath: String) {
    val file = FileFactory.getCarbonFile(filePath, FileFactory.getFileType(filePath))
    file.delete()
  }

  override def afterAll {
    deleteFile(tempDirPath)
  }
}
