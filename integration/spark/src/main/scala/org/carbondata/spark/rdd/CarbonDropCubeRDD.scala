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

package org.carbondata.spark.rdd

import java.util

import scala.collection.JavaConverters._

import org.apache.hadoop.mapreduce.Job
import org.apache.spark.{Logging, Partition, SparkContext, TaskContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.execution.command.Partitioner

import org.carbondata.core.carbon.AbsoluteTableIdentifier
import org.carbondata.core.carbon.datastore.block.TableBlockInfo
import org.carbondata.core.carbon.datastore.BlockIndexStore
import org.carbondata.core.carbon.datastore.SegmentTaskIndexStore
import org.carbondata.hadoop.CarbonInputFormat
import org.carbondata.hadoop.CarbonInputSplit
import org.carbondata.query.carbon.result.RowResult
import org.carbondata.query.scanner.impl.{CarbonKey, CarbonValue}
import org.carbondata.spark.KeyVal
import org.carbondata.spark.load.CarbonLoaderUtil
import org.carbondata.spark.util.CarbonQueryUtil
import org.carbondata.spark.util.QueryPlanUtil

class CarbonDropCubeRDD[K, V](
    sc: SparkContext,
    keyClass: KeyVal[K, V],
    schemaName: String,
    cubeName: String,
    partitioner: Partitioner,
    absTableIdentifier: AbsoluteTableIdentifier)
  extends RDD[(K, V)](sc, Nil) with Logging {

  sc.setLocalProperty("spark.scheduler.pool", "DDL")

  val defaultParallelism = sc.defaultParallelism

  override def getPartitions: Array[Partition] = {
    val startTime = System.currentTimeMillis();
    val (carbonInputFormat: CarbonInputFormat[RowResult], job: Job) =
      QueryPlanUtil.createCarbonInputFormat(absTableIdentifier)

    val result = new util.ArrayList[Partition](defaultParallelism)
    val validSegments = job.getConfiguration.get(CarbonInputFormat.INPUT_SEGMENT_NUMBERS)
    val nodesPerBlock = new util.ArrayList[TableBlockInfo]()
    if(!validSegments.isEmpty) {
      // get splits
      val splits = carbonInputFormat.getSplits(job)
      val carbonInputSplits = splits.asScala.map(_.asInstanceOf[CarbonInputSplit])

      val blockList = carbonInputSplits.map(inputSplit =>
        new TableBlockInfo(inputSplit.getPath.toString,
          inputSplit.getStart, inputSplit.getSegmentId,
          inputSplit.getLocations, inputSplit.getLength
        )
      )
      if(!blockList.isEmpty) {
        // group blocks to nodes, tasks
        val nodeBlockMapping =
          CarbonLoaderUtil.nodeBlockTaskMapping(blockList.asJava, -1, defaultParallelism)

        var i = 0
        // Create Spark Partition for each task and assign blocks
        nodeBlockMapping.asScala.foreach { entry =>
           result.add(new CarbonSparkPartition(id, i, Seq(entry._1).toArray, nodesPerBlock))
           i += 1;
        }
      } else {
        logInfo("No blocks identified to scan")
        result.add(new CarbonSparkPartition(id, 0, Seq("").toArray, nodesPerBlock))
      }
    }
    else {
      result.add(new CarbonSparkPartition(id, 0, Seq("").toArray, nodesPerBlock))
    }
    SegmentTaskIndexStore.getInstance.clear(absTableIdentifier)
    result.toArray(new Array[Partition](result.size()))
  }

  override def compute(theSplit: Partition, context: TaskContext): Iterator[(K, V)] = {

    val iter = new Iterator[(K, V)] {
      BlockIndexStore.getInstance.clear(absTableIdentifier)

      var havePair = false
      var finished = false

      override def hasNext: Boolean = {
        if (!finished && !havePair) {
          finished = !false
          havePair = !finished
        }
        !finished
      }

      override def next(): (K, V) = {
        if (!hasNext) {
          throw new java.util.NoSuchElementException("End of stream")
        }
        havePair = false
        val row = new CarbonKey(null)
        val value = new CarbonValue(null)
        keyClass.getKey(row, value)
      }
    }
    iter
  }
}

