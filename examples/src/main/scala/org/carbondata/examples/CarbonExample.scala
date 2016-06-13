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

package org.carbondata.examples

import java.io.File
import scala.io.Source
import org.apache.hadoop.hive.conf.HiveConf
import org.carbondata.core.util.CarbonProperties
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.CarbonContext

import org.carbondata.examples.util.InitForExamples

object CarbonExample {
  def main(args: Array[String]) {
       val file: File = new File(args(0))
    val basePath: String = file.getParentFile.getAbsolutePath

    // get current directory:/examples
    val currentDirectory = new File(this.getClass.getResource("/").getPath + "/../../")
      .getCanonicalPath

    // specify parameters
    val storeLocation = basePath + "/store"
     def currentPath: String = new File(this.getClass.getResource("/").getPath + "/../../")
    .getCanonicalPath
     val hiveMetaPath = storeLocation + "/hivemetadata"

    val kettleHome = new File(currentPath + "/../processing/carbonplugins").getCanonicalPath
 
   val sc = new SparkContext(new SparkConf()
      .setAppName("CarbonExample")
      .setMaster("local[2]"))
    sc.setLogLevel("ERROR")

    val cc = new CarbonContext(sc, storeLocation)
    cc.setConf("javax.jdo.option.ConnectionURL","jdbc:derby:;databaseName="+storeLocation+"/metastore_db;create=true")
    cc.setConf("carbon.kettle.home", kettleHome)
    cc.setConf("hive.metastore.warehouse.dir", hiveMetaPath)
    cc.setConf(HiveConf.ConfVars.HIVECHECKFILEFORMAT.varname, "false")

    // whether use table split partition
    // true -> use table split partition, support multiple partition loading
    // false -> use node split partition, support data load by host partition
    CarbonProperties.getInstance().addProperty("carbon.table.split.partition.enable", "false")

   
    Source.fromFile(basePath + File.separator + "test.sql").getLines()
      .foreach { x => processCommand(x, cc) }

  }

  def processCommand(cmd: String, cc: CarbonContext): Unit = {
    // scalastyle:off
    if (!cmd.startsWith("#")) {
      println(s"executing>>>>>>$cmd")
      val result = cc.sql(cmd)
      result.show(100)
      println(s"executed>>>>>>$cmd")
    }
  }
}
