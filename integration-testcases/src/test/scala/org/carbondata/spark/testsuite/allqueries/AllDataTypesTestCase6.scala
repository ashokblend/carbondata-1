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

package org.carbondata.spark.testsuite.allqueries

import java.io.File

import org.apache.spark.sql.Row
import org.apache.spark.sql.common.util.CarbonHiveContext._
import org.apache.spark.sql.common.util.{NonRunningTests, QueryTest}
import org.scalatest.BeforeAndAfterAll

import org.carbondata.core.constants.CarbonCommonConstants
import org.carbondata.core.util.CarbonProperties

/**
  * Test Class for all queries on multiple datatypes
  * Manohar
  */
class AllDataTypesTestCase6 extends QueryTest with BeforeAndAfterAll {

  override def beforeAll {

    val currentDirectory = new File(this.getClass.getResource("/").getPath + "/../../")
      .getCanonicalPath

    try {
      sql(
        "create table Carbon_automation_test6 (imei string,deviceInformationId int,MAC string," +
        "deviceColor string,device_backColor string,modelId string,marketName string,AMSize " +
        "string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate " +
        "string,bomCode string,internalModels string, deliveryTime string, channelsId string, " +
        "channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince " +
        "string, deliveryCity string,deliveryDistrict string, deliveryStreet string, " +
        "oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry " +
        "string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet " +
        "string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, " +
        "Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, " +
        "Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber " +
        "string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, " +
        "Latest_YEAR int, Latest_MONTH int, Latest_DAY int, Latest_HOUR string, Latest_areaId " +
        "string, Latest_country string, Latest_province string, Latest_city string, " +
        "Latest_district string, Latest_street string, Latest_releaseId string, " +
        "Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, " +
        "Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, " +
        "Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, " +
        "Latest_phonePADPartitionedVersions string, Latest_operatorId string, " +
        "gamePointDescription string, gamePointId int,contractNumber int) stored by 'org.apache" +
        ".carbondata.format'")
      CarbonProperties.getInstance()
        .addProperty(CarbonCommonConstants.CARBON_TIMESTAMP_FORMAT,
          CarbonCommonConstants.CARBON_TIMESTAMP_DEFAULT_FORMAT
        )
      sql("LOAD DATA LOCAL INPATH '" + currentDirectory +
          "/src/test/resources/100_olap.csv' INTO table Carbon_automation_test6 OPTIONS" +
          "('DELIMITER'= ',' ,'QUOTECHAR'= '\"', 'FILEHEADER'= 'imei,deviceInformationId,MAC," +
          "deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked," +
          "series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName," +
          "deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict," +
          "deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId," +
          "ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId," +
          "Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber," +
          "Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer," +
          "Active_webTypeDataVerNumber,Active_operatorsVersion," +
          "Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR," +
          "Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district," +
          "Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion," +
          "Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer," +
          "Latest_webTypeDataVerNumber,Latest_operatorsVersion," +
          "Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")

      /*sql(
        "create table hivetable(imei string,deviceInformationId int,MAC string,deviceColor " +
          "string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize " +
          "string,CUPAudit string,CPIClocked string,series string,productionDate timestamp," +
          "bomCode string,internalModels string, deliveryTime string, channelsId string, " +
          "channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince " +
          "string, deliveryCity string,deliveryDistrict string, deliveryStreet string, " +
          "oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry " +
          "string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet " +
          "string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, " +
          "Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, " +
          "Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber" +
          " string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, " +
          "Latest_YEAR int, Latest_MONTH int, Latest_DAY int, Latest_HOUR string, " +
          "Latest_areaId string, Latest_country string, Latest_province string, Latest_city " +
          "string, Latest_district string, Latest_street string, Latest_releaseId string, " +
          "Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, " +
          "Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string," +
          " Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, " +
          "Latest_phonePADPartitionedVersions string, Latest_operatorId string, " +
          "gamePointDescription string, gamePointId int,contractNumber int) row format " +
          "delimited fields terminated by ','"
      )*/

      sql("create table hivetable (imei string,deviceInformationId int,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string,contractNumber int, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR int, Latest_MONTH int, Latest_DAY int, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointId int,gamePointDescription string)row format delimited fields terminated by ','");
          
      sql(
        "LOAD DATA local inpath'" + currentDirectory + "/src/test/resources/100_olap.csv'" +
          " overwrite INTO table hivetable"
      )

      sql(
        "create table myvmallTest (imei String,uuid String,MAC String,device_color String," +
        "device_shell_color String,device_name String,product_name String,ram String,rom  String," +
        "cpu_clock String,series String,check_date String,check_month int , check_day int," +
        "check_hour int,bom String,inside_name String,packing_date  String,packing_year String," +
        "packing_month String,packing_day String,packing_hour String,customer_name String," +
        "deliveryAreaId String,deliveryCountry String, deliveryProvince String,deliveryCity " +
        "String,deliveryDistrict String,packing_list_no String,order_no String,Active_check_time " +
        "String,Active_check_year int, Active_check_month int,Active_check_day int," +
        "Active_check_hour int, ActiveAreaId String,ActiveCountry String,ActiveProvince String," +
        "Activecity String, ActiveDistrict String,Active_network String,Active_firmware_version " +
        "String, Active_emui_version String,Active_os_version String,Latest_check_time String, " +
        "Latest_check_year int,Latest_check_month int,Latest_check_day int, Latest_check_hour " +
        "int,Latest_areaId String,Latest_country String,Latest_province  String,Latest_city " +
        "String,Latest_district String,Latest_firmware_version String, Latest_emui_version " +
        "String,Latest_os_version String,Latest_network String,site String, site_desc String," +
        "product String,product_desc String,check_year int) " +
        "stored by 'org.apache.carbondata.format'")
      sql("LOAD DATA LOCAL INPATH '" + currentDirectory +
          "/src/test/resources/100_VMALL_1_Day_DATA_2015-09-15.csv' INTO table myvmallTest " +
          "OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"', 'FILEHEADER'= 'imei,uuid,MAC," +
          "device_color,device_shell_color,device_name,product_name,ram,rom,cpu_clock,series," +
          "check_date,check_year,check_month,check_day,check_hour,bom,inside_name,packing_date," +
          "packing_year,packing_month,packing_day,packing_hour,customer_name,deliveryAreaId," +
          "deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,packing_list_no," +
          "order_no,Active_check_time,Active_check_year,Active_check_month,Active_check_day," +
          "Active_check_hour,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict," +
          "Active_network,Active_firmware_version,Active_emui_version,Active_os_version," +
          "Latest_check_time,Latest_check_year,Latest_check_month,Latest_check_day," +
          "Latest_check_hour,Latest_areaId,Latest_country,Latest_province,Latest_city," +
          "Latest_district,Latest_firmware_version,Latest_emui_version,Latest_os_version," +
          "Latest_network,site,site_desc,product,product_desc')")
      sql(
        "create table if not exists traffic_2g_3g_4g (source_info string , app_category_id string" +
        " ,app_category_name string ,app_sub_category_id string , app_sub_category_name string ," +
        "rat_name string ,imsi string ,offer_msisdn string , offer_id string ,offer_option_1 " +
        "string ,offer_option_2 string ,offer_option_3 string , msisdn string ,package_type " +
        "string ,package_price string ,tag_imsi string ,tag_msisdn string ,province string ,city " +
        "string ,area_code string ,tac string ,imei string , terminal_type string ,terminal_brand" +
        " string ,terminal_model string ,price_level string  ,network string ,shipped_os string ," +
        "wifi string ,wifi_hotspot string ,gsm string , wcdma string ,td_scdma string ,lte_fdd " +
        "string ,lte_tdd string ,cdma string , screen_size string ,screen_resolution string ," +
        "host_name string ,website_name string , operator string ,srv_type_name string ,tag_host " +
        "string ,cgi string ,cell_name string , coverity_type1 string ,coverity_type2 string ," +
        "coverity_type3 string ,coverity_type4  string ,coverity_type5 string ,latitude string ," +
        "longitude string ,azimuth string , tag_cgi string ,apn string ,user_agent string ,day " +
        "string ,hour string ,`min` string , is_default_bear int ,eps_bearer_id string ,qci int ," +
        "user_filter string , analysis_period string, up_throughput decimal,down_throughput " +
        "decimal, up_pkt_num decimal,down_pkt_num decimal,app_request_num decimal," +
        "pkt_num_len_1_64  decimal,pkt_num_len_64_128 decimal,pkt_num_len_128_256 decimal," +
        "pkt_num_len_256_512  decimal,pkt_num_len_512_768 decimal,pkt_num_len_768_1024 decimal," +
        "pkt_num_len_1024_all  decimal,ip_flow_mark decimal)" +
        " stored by 'org.apache.carbondata.format'")

      sql("LOAD DATA LOCAL INPATH '" + currentDirectory +
          "/src/test/resources/FACT_UNITED_DATA_INFO_sample_cube.csv' INTO table traffic_2g_3g_4g" +
          "OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"', 'FILEHEADER'= '')")

      sql(
        "Create table cube_restructure444 (a0 STRING,a STRING,b0 INT)  stored by 'org.apache" +
        ".carbondata.format'")
      sql("LOAD DATA LOCAL INPATH '" + currentDirectory +
          "/src/test/resources/restructure_cube.csv'" +
          " INTO TABLE cube_restructure444 OPTIONS('DELIMITER'=',', 'QUOTECHAR'= '\"')")

      sql(
        "create table  Carbon_automation_vmall_test1 (imei string,deviceInformationId int,MAC " +
        "string,deviceColor string,device_backColor string,modelId string, marketName string," +
        "AMSize string,ROMSize string,CUPAudit string,CPIClocked string, series string," +
        "productionDate timestamp,bomCode string,internalModels string, deliveryTime string, " +
        "channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, " +
        "deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet " +
        "string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, " +
        "ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, " +
        "ActiveStreet string, ActiveOperatorId string, Active_releaseId string, " +
        "Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, " +
        "Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string," +
        "Active_webTypeDataVerNumber string, Active_operatorsVersion string, " +
        "Active_phonePADPartitionedVersions string, Latest_YEAR int, Latest_MONTH int, Latest_DAY" +
        " int, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province " +
        "string, Latest_city string, Latest_district string, Latest_street string, " +
        "Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, " +
        "Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, " +
        "Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, " +
        "Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, " +
        "Latest_operatorId string, gamePointDescription string,gamePointId decimal,contractNumber" +
        " decimal) stored by 'org.apache.carbondata.format'")
      sql("LOAD DATA LOCAL INPATH 'hdfs://hacluster/mano/Vmall_100_olap.csv' INTO table " +
          "Carbon_automation_vmall_test1 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\\\"', " +
          "'FILEHEADER'= 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId," +
          "marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode," +
          "internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry," +
          "deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber," +
          "contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity," +
          "ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion," +
          "Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion," +
          "Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion," +
          "Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR," +
          "Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district," +
          "Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion," +
          "Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer," +
          "Latest_webTypeDataVerNumber,Latest_operatorsVersion," +
          "Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
      sql(
        "create table Carbon_automation_test5 (imei string,deviceInformationId int,MAC string," +
        "deviceColor string,device_backColor string,modelId string,marketName string,AMSize " +
        "string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate " +
        "string,bomCode string,internalModels string, deliveryTime string, channelsId string, " +
        "channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince " +
        "string, deliveryCity string,deliveryDistrict string, deliveryStreet string, " +
        "oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry " +
        "string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet " +
        "string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, " +
        "Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, " +
        "Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber " +
        "string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, " +
        "Latest_YEAR int, Latest_MONTH int, Latest_DAY int, Latest_HOUR string, Latest_areaId " +
        "string, Latest_country string, Latest_province string, Latest_city string, " +
        "Latest_district string, Latest_street string, Latest_releaseId string, " +
        "Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, " +
        "Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, " +
        "Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, " +
        "Latest_phonePADPartitionedVersions string, Latest_operatorId string, " +
        "gamePointDescription string, gamePointId int,contractNumber int) stored by 'org.apache" +
        ".carbondata.format'")
      sql("LOAD DATA LOCAL INPATH '" + currentDirectory +
          "/src/test/resources/100_olap.csv' INTO table Carbon_automation_test5 OPTIONS" +
          "('DELIMITER'= ',' ,'QUOTECHAR'= '\"', 'FILEHEADER'= 'imei,deviceInformationId,MAC," +
          "deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked," +
          "series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName," +
          "deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict," +
          "deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId," +
          "ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId," +
          "Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber," +
          "Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer," +
          "Active_webTypeDataVerNumber,Active_operatorsVersion," +
          "Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR," +
          "Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district," +
          "Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion," +
          "Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer," +
          "Latest_webTypeDataVerNumber,Latest_operatorsVersion," +
          "Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")

      sql("create schema myschema")
      sql("create schema myschema1")
      sql("create schema drug")
    }catch {
      case e : Exception => print("ERROR : " + e.getMessage)
    }
  }

  override def afterAll {
    try {
      sql("drop table Carbon_automation_test6")
      sql("drop table hivetable")
      sql("drop table myvmallTest")
      sql("drop table traffic_2g_3g_4g")
      sql("drop table cube_restructure444")
      sql("drop table Carbon_automation_vmall_test1")
      sql("drop table Carbon_automation_test5")
      sql("drop schema myschema")
      sql("drop schema myschema1")
      sql("drop schema drug")
    }catch {
      case e : Exception => print("ERROR : " + e.getMessage)
    }
  }

  //AllDataTypesTestCases2

  //Test-46
  test("select sum(deviceinformationid)+10 as a ,series  from Carbon_automation_test6 group by series.")({

    checkAnswer(
      sql("select sum(deviceinformationid)+10 as a ,series  from Carbon_automation_test6 group by series"),
      Seq(Row(900451, "6Series"), Row(1410606, "0Series"), Row(800391, "4Series"), Row(920401, "8Series"), Row(1620257, "7Series"), Row(300060, "1Series"), Row(1321584, "5Series"), Row(800323, "9Series"), Row(710330, "3Series"), Row(810414, "2Series")))
  })



  //Test-48
  test("select sum(latest_year)+10 as a ,series  from Carbon_automation_test6 group by series")({

    checkAnswer(
      sql("select sum(latest_year)+10 as a ,series  from Carbon_automation_test6 group by series"),
      Seq(Row(18145, "6Series"), Row(30235, "0Series"), Row(16130, "4Series"), Row(22175, "8Series"), Row(22175, "7Series"), Row(6055, "1Series"), Row(34265, "5Series"), Row(16130, "9Series"), Row(16130, "3Series"), Row(18145, "2Series")))
  })

  //Test-49
  test("select sum(deviceinformationid)+10.32 as a ,series  from Carbon_automation_test6 group by series")({

    checkAnswer(
      sql("select sum(deviceinformationid)+10.32 as a ,series  from Carbon_automation_test6 group by series"),
      Seq(Row(900451.32, "6Series"), Row(1410606.32, "0Series"), Row(800391.32, "4Series"), Row(920401.32, "8Series"), Row(1620257.32, "7Series"), Row(300060.32, "1Series"), Row(1321584.32, "5Series"), Row(800323.32, "9Series"), Row(710330.32, "3Series"), Row(810414.32, "2Series")))
  })



  //TC_051
  test("select sum(latest_year)+10.364 as a,series  from Carbon_automation_test6 group by series")({
    checkAnswer(
      sql("select sum(latest_year)+10.364 as a,series  from Carbon_automation_test6 group by series"),
      Seq(Row(18145.364, "6Series"), Row(30235.364, "0Series"), Row(16130.364, "4Series"), Row(22175.364, "8Series"), Row(22175.364, "7Series"), Row(6055.364, "1Series"), Row(34265.364, "5Series"), Row(16130.364, "9Series"), Row(16130.364, "3Series"), Row(18145.364, "2Series")))
  })

  //TC_052
  test("select avg(deviceinformationid)+10 as a ,series  from Carbon_automation_test6 group by series")({
    checkAnswer(
      sql("select avg(deviceinformationid)+10 as a ,series  from Carbon_automation_test6 group by series"),
      Seq(Row(100059.0, "6Series"), Row(94049.73333333334, "0Series"), Row(100057.625, "4Series"), Row(83681.90909090909, "8Series"), Row(147305.18181818182, "7Series"), Row(100026.66666666667, "1Series"), Row(77749.64705882352, "5Series"), Row(100049.125, "9Series"), Row(88800.0, "3Series"), Row(90054.88888888889, "2Series")))
  })

  //TC_054
  test("select avg(latest_year)+10 as a ,series  from Carbon_automation_test6 group by series")({
    checkAnswer(
      sql("select avg(latest_year)+10 as a ,series  from Carbon_automation_test6 group by series"),
      Seq(Row(2025.0, "6Series"), Row(2025.0, "0Series"), Row(2025.0, "4Series"), Row(2025.0, "8Series"), Row(2025.0, "7Series"), Row(2025.0, "1Series"), Row(2025.0, "5Series"), Row(2025.0, "9Series"), Row(2025.0, "3Series"), Row(2025.0, "2Series")))
  })


  //TC_072
  test("select sum(deviceInformationId) a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select sum(deviceInformationId) a  from Carbon_automation_test6"),
      Seq(Row(9594717)))
  })

  //TC_073
  test("select sum(channelsId) a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select sum(channelsId) a  from Carbon_automation_test6"),
      Seq(Row(428.0)))
  })

  //TC_074
  test("select sum(bomCode)  a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select sum(bomCode)  a  from Carbon_automation_test6"),
      Seq(Row(9594717.0)))
  })

  //TC_075
  test("select sum(Latest_MONTH)  a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select sum(Latest_MONTH)  a  from Carbon_automation_test6"),
      Seq(Row(693)))
  })

  //TC_078
  test("select sum( DISTINCT channelsId) a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select sum( DISTINCT channelsId) a  from Carbon_automation_test6"),
      Seq(Row(428.0)))
  })

  //TC_083
  test("select avg(deviceInformationId) a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select avg(deviceInformationId) a  from Carbon_automation_test6"),
      Seq(Row(96916.33333333333)))
  })

  //TC_084
  test("select avg(channelsId) a  from Carbon_automation_test6", NonRunningTests)({
    checkAnswer(
      sql("select avg(channelsId) a  from Carbon_automation_test6"),
      Seq(Row(4.3232323232323235)))
  })

  //TC_085
  test("select avg(bomCode)  a  from Carbon_automation_test6", NonRunningTests)({
    checkAnswer(
      sql("select avg(bomCode)  a  from Carbon_automation_test6"),
      Seq(Row(96916.33333333333)))
  })

  //TC_086
  test("select avg(Latest_MONTH)  a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select avg(Latest_MONTH)  a  from Carbon_automation_test6"),
      Seq(Row(7.0)))
  })


  //TC_104
  test("select var_pop(deviceInformationId)  as a from Carbon_automation_test6")({
    checkAnswer(
      sql("select var_pop(deviceInformationId)  as a from Carbon_automation_test6"),
      Seq(Row(9.310415559636362E9)))
  })


  //TC_114
  test("select percentile_approx(deviceInformationId,0.2,5) as a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select percentile_approx(deviceInformationId,0.2,5) as a  from Carbon_automation_test6"),
      sql("select percentile_approx(deviceInformationId,0.2,5) as a  from hivetable"))
  })


  //TC_119
  test("select variance(gamePointId) as a   from Carbon_automation_test6")({
    checkAnswer(
      sql("select variance(gamePointId) as a   from Carbon_automation_test6"),
      sql("select variance(gamePointId) as a   from hivetable"))
  })

  //TC_120
  test("select var_pop(gamePointId)  as a from Carbon_automation_test6")({
    checkAnswer(
      sql("select var_pop(gamePointId)  as a from Carbon_automation_test6"),
      sql("select var_pop(gamePointId)  as a from hivetable"))
  })

  //TC_121
  test("select var_samp(gamePointId) as a  from Carbon_automation_test6")({
    checkAnswer(
      sql("select var_samp(gamePointId) as a  from Carbon_automation_test6"),
      sql("select var_samp(gamePointId) as a  from hivetable"))
  })

  //TC_123
  test("select stddev_samp(gamePointId)  as a from Carbon_automation_test6")({
    checkAnswer(
      sql("select stddev_samp(gamePointId)  as a from Carbon_automation_test6"),
      sql("select stddev_samp(gamePointId)  as a from hivetable"))
  })

  //TC_126
  test("select corr(gamePointId,gamePointId)  as a from Carbon_automation_test6")({
    checkAnswer(
      sql("select corr(gamePointId,gamePointId)  as a from Carbon_automation_test6"),
      Seq(Row(0.9999999999999999)))
  })


  //TC_130
  test("select percentile_approx(gamePointId,0.2,5) as a  from Carbon_automation_test6",
    NonRunningTests)({
    checkAnswer(
      sql("select percentile_approx(gamePointId,0.2,5) as a  from Carbon_automation_test6"),
      sql("select percentile_approx(gamePointId,0.2,5) as a  from hivetable"))
  })



  //TC_135
  test("select FIRST(imei) a from Carbon_automation_test6", NonRunningTests)({
    checkAnswer(
      sql("select FIRST(imei) a from Carbon_automation_test6"),
      sql("select FIRST(imei) a from hivetable"))
  })


  //TC_137
  test("select series,count(imei) a from Carbon_automation_test6 group by series order by a",
    NonRunningTests)({
    checkAnswer(
      sql("select series,count(imei) a from Carbon_automation_test6 group by series order by a"),
      sql("select series,count(imei) a from hivetable group by series order by a"))
  })

  //TC_143
  test("select deliveryProvince,series,sum(deviceInformationId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series")({
    checkAnswer(
      sql("select deliveryProvince,series,sum(deviceInformationId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      sql("select deliveryProvince,series,sum(deviceInformationId) a from hivetable group by deliveryProvince,series order by deliveryProvince,series"))
  })

  //TC_144
  test("select deliveryProvince,series,sum(channelsId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series")({
    checkAnswer(
      sql("select deliveryProvince,series,sum(channelsId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      sql("select deliveryProvince,series,sum(channelsId) a from hivetable group by deliveryProvince,series order by deliveryProvince,series"))
  })

  //TC_145
  test("select deliveryProvince,series,sum(bomCode) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series")({
    checkAnswer(
      sql("select deliveryProvince,series,sum(bomCode) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      sql("select deliveryProvince,series,sum(bomCode) a from hivetable group by deliveryProvince,series order by deliveryProvince,series"))
  })


  //TC_148
  test("select deliveryProvince,series,avg(deviceInformationId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series")({
    checkAnswer(
      sql("select deliveryProvince,series,avg(deviceInformationId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      Seq(Row("Guangdong Province", "0Series", 100051.66666666667), Row("Guangdong Province", "1Series", 100032.0), Row("Guangdong Province", "2Series", 100042.5), Row("Guangdong Province", "3Series", 82043.4), Row("Guangdong Province", "4Series", 100059.0), Row("Guangdong Province", "5Series", 74319.28571428571), Row("Guangdong Province", "6Series", 100038.0), Row("Guangdong Province", "7Series", 55027.0), Row("Guangdong Province", "8Series", 100050.33333333333), Row("Guangdong Province", "9Series", 100061.5), Row("Hubei Province", "0Series", 70029.0), Row("Hubei Province", "1Series", 100005.0), Row("Hubei Province", "2Series", 100061.0), Row("Hubei Province", "3Series", 100077.0), Row("Hubei Province", "4Series", 100035.5), Row("Hubei Province", "5Series", 1000.0), Row("Hubei Province", "6Series", 100065.0), Row("Hubei Province", "7Series", 216687.16666666666), Row("Hubei Province", "8Series", 100030.33333333333), Row("Hubei Province", "9Series", 100031.0), Row("Hunan Province", "0Series", 100033.16666666667), Row("Hunan Province", "1Series", 100013.0), Row("Hunan Province", "2Series", 70025.0), Row("Hunan Province", "3Series", 100013.0), Row("Hunan Province", "4Series", 100050.2), Row("Hunan Province", "5Series", 88926.55555555556), Row("Hunan Province", "6Series", 100045.5), Row("Hunan Province", "7Series", 70023.33333333333), Row("Hunan Province", "8Series", 64029.8), Row("Hunan Province", "9Series", 100032.0)))
  })

  //TC_149
  test("select deliveryProvince,series,avg(channelsId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series", NonRunningTests)({
    checkAnswer(
      sql("select deliveryProvince,series,avg(channelsId) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      Seq(Row("Guangdong Province", "0Series", 3.3333333333333335), Row("Guangdong Province", "1Series", 7.0), Row("Guangdong Province", "2Series", 6.5), Row("Guangdong Province", "3Series", 4.2), Row("Guangdong Province", "4Series", 7.0), Row("Guangdong Province", "5Series", 3.5714285714285716), Row("Guangdong Province", "6Series", 3.0), Row("Guangdong Province", "7Series", 1.5), Row("Guangdong Province", "8Series", 3.3333333333333335), Row("Guangdong Province", "9Series", 6.0), Row("Hubei Province", "0Series", 5.0), Row("Hubei Province", "1Series", 1.0), Row("Hubei Province", "2Series", 3.0), Row("Hubei Province", "3Series", 6.0), Row("Hubei Province", "4Series", 4.5), Row("Hubei Province", "5Series", 3.0), Row("Hubei Province", "6Series", 6.0), Row("Hubei Province", "7Series", 4.833333333333333), Row("Hubei Province", "8Series", 5.666666666666667), Row("Hubei Province", "9Series", 6.0), Row("Hunan Province", "0Series", 4.833333333333333), Row("Hunan Province", "1Series", 6.0), Row("Hunan Province", "2Series", 5.333333333333333), Row("Hunan Province", "3Series", 4.5), Row("Hunan Province", "4Series", 3.6), Row("Hunan Province", "5Series", 4.888888888888889), Row("Hunan Province", "6Series", 4.666666666666667), Row("Hunan Province", "7Series", 4.333333333333333), Row("Hunan Province", "8Series", 3.0), Row("Hunan Province", "9Series", 3.25)))
  })

  //TC_150
  test("select deliveryProvince,series,avg(bomCode) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series", NonRunningTests)({
    checkAnswer(
      sql("select deliveryProvince,series,avg(bomCode) a from Carbon_automation_test6 group by deliveryProvince,series order by deliveryProvince,series"),
      Seq(Row("Guangdong Province", "0Series", 100051.66666666667), Row("Guangdong Province", "1Series", 100032.0), Row("Guangdong Province", "2Series", 100042.5), Row("Guangdong Province", "3Series", 82043.4), Row("Guangdong Province", "4Series", 100059.0), Row("Guangdong Province", "5Series", 74319.28571428571), Row("Guangdong Province", "6Series", 100038.0), Row("Guangdong Province", "7Series", 55027.0), Row("Guangdong Province", "8Series", 100050.33333333333), Row("Guangdong Province", "9Series", 100061.5), Row("Hubei Province", "0Series", 70029.0), Row("Hubei Province", "1Series", 100005.0), Row("Hubei Province", "2Series", 100061.0), Row("Hubei Province", "3Series", 100077.0), Row("Hubei Province", "4Series", 100035.5), Row("Hubei Province", "5Series", 1000.0), Row("Hubei Province", "6Series", 100065.0), Row("Hubei Province", "7Series", 216687.16666666666), Row("Hubei Province", "8Series", 100030.33333333333), Row("Hubei Province", "9Series", 100031.0), Row("Hunan Province", "0Series", 100033.16666666667), Row("Hunan Province", "1Series", 100013.0), Row("Hunan Province", "2Series", 70025.0), Row("Hunan Province", "3Series", 100013.0), Row("Hunan Province", "4Series", 100050.2), Row("Hunan Province", "5Series", 88926.55555555556), Row("Hunan Province", "6Series", 100045.5), Row("Hunan Province", "7Series", 70023.33333333333), Row("Hunan Province", "8Series", 64029.8), Row("Hunan Province", "9Series", 100032.0)))
  })

  //TC_161
  test("select Latest_DAY from Carbon_automation_test6 where Latest_DAY in (select  Latest_DAY from Carbon_automation_test6) order by Latest_DAY", NonRunningTests)({
    checkAnswer(
      sql("select Latest_DAY from Carbon_automation_test6 where Latest_DAY in (select  Latest_DAY from Carbon_automation_test6) order by Latest_DAY"),
      sql("select Latest_DAY from Carbon_automation_test6 where Latest_DAY in (select  Latest_DAY from hivetable) order by Latest_DAY"))
  })



  //TC_164
  test("select channelsId from Carbon_automation_test6 where channelsId in (select  channelsId from Carbon_automation_test6) order by channelsId", NonRunningTests)({
    checkAnswer(
      sql("select channelsId from Carbon_automation_test6 where channelsId in (select  channelsId from Carbon_automation_test6) order by channelsId"),
      sql("select channelsId from Carbon_automation_test6 where channelsId in (select  channelsId from hivetable) order by channelsId"))
  })

  //TC_165
  test("select  imei, sum(deviceInformationId) as a  from Carbon_automation_test6 where deviceInformationId in (select deviceInformationId  from Carbon_automation_test6) group by deviceInformationId,imei", NonRunningTests)({
    checkAnswer(
      sql("select  imei, sum(deviceInformationId) as a  from Carbon_automation_test6 where deviceInformationId in (select deviceInformationId  from Carbon_automation_test6) group by deviceInformationId,imei"),
      sql("select  imei, sum(deviceInformationId) as a  from hivetable where deviceInformationId in (select deviceInformationId  from hivetable) group by deviceInformationId,imei"))
  })

  //TC_170
  test("select Upper(series) a ,channelsId from Carbon_automation_test6 group by series,channelsId order by channelsId", NonRunningTests)({
    checkAnswer(
      sql("select Upper(series) a ,channelsId from Carbon_automation_test6 group by series,channelsId order by channelsId"),
      sql("select Upper(series) a ,channelsId from hivetable group by series,channelsId order by channelsId"))
  })


  //TC_190
  test("select series,gamePointId as a from Carbon_automation_test6  order by series asc limit 10", NonRunningTests)({
    checkAnswer(
      sql("select series,gamePointId as a from Carbon_automation_test6  order by series asc limit 10"),
      sql("select series,gamePointId as a from hivetable  order by series asc limit 10"))
  })

  //TC_191
  test("select series,gamePointId as a from Carbon_automation_test6  order by series desc limit 10", NonRunningTests)({
    checkAnswer(
      sql("select series,gamePointId as a from Carbon_automation_test6  order by series desc limit 10"),
      sql("select series,gamePointId as a from hivetable  order by series desc limit 10"))
  })

  //TC_209
  test("select * from (select if( Latest_areaId=3,NULL,Latest_areaId) as babu,NULL a from Carbon_automation_test6) qq where babu<=>a", NonRunningTests)({
    checkAnswer(
      sql("select * from (select if( Latest_areaId=3,NULL,Latest_areaId) as babu,NULL a from Carbon_automation_test6) qq where babu<=>a"),
      sql("select * from (select if( Latest_areaId=3,NULL,Latest_areaId) as babu,NULL a from hivetable) qq where babu<=>a"))
  })


  //TC_233
  test("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR channelsId <=1 and series='7Series'", NonRunningTests)({
    checkAnswer(
      sql("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR channelsId <=1 and series='7Series'"),
      sql("select imei,gamePointId, channelsId,series  from hivetable where channelsId >=10 OR channelsId <=1 and series='7Series'"))
  })

  //TC_234
  test("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR channelsId <=1 or series='7Series'", NonRunningTests)({
    checkAnswer(
      sql("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR channelsId <=1 or series='7Series'"),
      sql("select imei,gamePointId, channelsId,series  from hivetable where channelsId >=10 OR channelsId <=1 or series='7Series'"))
  })

  //TC_235
  test("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR (channelsId <=1 and series='1Series')", NonRunningTests)({
    checkAnswer(
      sql("select imei,gamePointId, channelsId,series  from Carbon_automation_test6 where channelsId >=10 OR (channelsId <=1 and series='1Series')"),
      Seq(Row("1AA100005", 2593.0, "1", "1Series")))
  })

  //TC_236
  test("select sum(gamePointId) a from Carbon_automation_test6 where channelsId >=10 OR (channelsId <=1 and series='1Series')", NonRunningTests)({
    checkAnswer(
      sql("select sum(gamePointId) a from Carbon_automation_test6 where channelsId >=10 OR (channelsId <=1 and series='1Series')"),
      Seq(Row(2593.0)))
  })

  //TC_237
  test("select * from (select imei,if(imei='1AA100060',NULL,imei) a from Carbon_automation_test6) aa  where a IS NULL", NonRunningTests)({
    checkAnswer(
      sql("select * from (select imei,if(imei='1AA100060',NULL,imei) a from Carbon_automation_test6) aa  where a IS NULL"),
      sql("select * from (select imei,if(imei='1AA100060',NULL,imei) a from hivetable) aa  where a IS NULL"))
  })


  //TC_280
  test("SELECT Activecity, imei, ActiveCountry, ActiveDistrict FROM (select * from Carbon_automation_test6) SUB_QRY ORDER BY ActiveCountry ASC, ActiveDistrict ASC, Activecity ASC", NonRunningTests)({
    checkAnswer(
      sql("SELECT Activecity, imei, ActiveCountry, ActiveDistrict FROM (select * from Carbon_automation_test6) SUB_QRY ORDER BY ActiveCountry ASC, ActiveDistrict ASC, Activecity ASC"),
      sql("SELECT Activecity, imei, ActiveCountry, ActiveDistrict FROM (select * from hivetable) SUB_QRY ORDER BY ActiveCountry ASC, ActiveDistrict ASC, Activecity ASC"))
  })

  //TC_281
  test("SELECT Activecity, ActiveCountry, ActiveDistrict, MIN(imei) AS Min_imei, MAX(imei) AS Max_imei FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY Activecity, ActiveCountry, ActiveDistrict ORDER BY Activecity ASC, ActiveCountry ASC, ActiveDistrict ASC", NonRunningTests)({
    checkAnswer(
      sql("SELECT Activecity, ActiveCountry, ActiveDistrict, MIN(imei) AS Min_imei, MAX(imei) AS Max_imei FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY Activecity, ActiveCountry, ActiveDistrict ORDER BY Activecity ASC, ActiveCountry ASC, ActiveDistrict ASC"),
      Seq(Row("changsha", "Chinese", "yuhua", "1AA1000", "1AA10008"), Row("guangzhou", "Chinese", "longhua", "1AA1", "1AA100075"), Row("shenzhen", "Chinese", "longgang", "1AA100013", "1AA100083"), Row("wuhan", "Chinese", "hongshan", "1AA10", "1AA100078"), Row("xiangtan", "Chinese", "xiangtan", "1AA10000", "1AA100084"), Row("yichang", "Chinese", "yichang", "1AA100", "1AA100076"), Row("zhuzhou", "Chinese", "tianyuan", "1AA100002", "1AA100081")))
  })


  //TC_322
  test("SELECT ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series = \"5Series\" GROUP BY ActiveCountry, ActiveDistrict, deliveryCity ORDER BY ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series = \"5Series\" GROUP BY ActiveCountry, ActiveDistrict, deliveryCity ORDER BY ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC"),
      Seq(Row("Chinese", "hongshan", "guangzhou", 2015), Row("Chinese", "longgang", "changsha", 2015), Row("Chinese", "longgang", "guangzhou", 2015), Row("Chinese", "longgang", "shenzhen", 2015), Row("Chinese", "longgang", "zhuzhou", 2015), Row("Chinese", "longhua", "zhuzhou", 2015), Row("Chinese", "tianyuan", "shenzhen", 2015), Row("Chinese", "tianyuan", "zhuzhou", 2015), Row("Chinese", "xiangtan", "shenzhen", 2015), Row("Chinese", "xiangtan", "xiangtan", 2015), Row("Chinese", "yichang", "guangzhou", 2015), Row("Chinese", "yichang", "xiangtan", 2015), Row("Chinese", "yuhua", "changsha", 2015), Row("Chinese", "yuhua", "shenzhen", 2015), Row("Chinese", "yuhua", "wuhan", 2015), Row("Chinese", "yuhua", "zhuzhou", 4030)))
  })

  //TC_323
  test("SELECT ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE deliveryCity = \"zhuzhou\" GROUP BY ActiveCountry, ActiveDistrict, deliveryCity ORDER BY ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE deliveryCity = \"zhuzhou\" GROUP BY ActiveCountry, ActiveDistrict, deliveryCity ORDER BY ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC"),
      Seq(Row("Chinese", "hongshan", "zhuzhou", 2015), Row("Chinese", "longgang", "zhuzhou", 6045), Row("Chinese", "longhua", "zhuzhou", 6045), Row("Chinese", "tianyuan", "zhuzhou", 6045), Row("Chinese", "yichang", "zhuzhou", 2015), Row("Chinese", "yuhua", "zhuzhou", 8060)))
  })

  //TC_324
  test("SELECT modelId, ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE modelId > \"2000\" GROUP BY modelId, ActiveCountry, ActiveDistrict, deliveryCity ORDER BY modelId ASC, ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT modelId, ActiveCountry, ActiveDistrict, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE modelId > \"2000\" GROUP BY modelId, ActiveCountry, ActiveDistrict, deliveryCity ORDER BY modelId ASC, ActiveCountry ASC, ActiveDistrict ASC, deliveryCity ASC"),
      Seq(Row("2008", "Chinese", "tianyuan", "changsha", 2015), Row("2069", "Chinese", "yuhua", "changsha", 2015), Row("2074", "Chinese", "longgang", "wuhan", 2015), Row("2133", "Chinese", "xiangtan", "shenzhen", 2015), Row("2142", "Chinese", "tianyuan", "yichang", 2015), Row("2151", "Chinese", "yuhua", "zhuzhou", 2015), Row("2164", "Chinese", "yuhua", "xiangtan", 2015), Row("2167", "Chinese", "longgang", "changsha", 2015), Row("2176", "Chinese", "longgang", "xiangtan", 2015), Row("2201", "Chinese", "yichang", "wuhan", 2015), Row("2300", "Chinese", "tianyuan", "zhuzhou", 2015), Row("2319", "Chinese", "hongshan", "guangzhou", 2015), Row("2320", "Chinese", "xiangtan", "guangzhou", 2015), Row("2355", "Chinese", "xiangtan", "shenzhen", 2015), Row("2381", "Chinese", "yuhua", "xiangtan", 2015), Row("2408", "Chinese", "xiangtan", "guangzhou", 2015), Row("2415", "Chinese", "yuhua", "changsha", 2015), Row("2457", "Chinese", "yichang", "yichang", 2015), Row("2479", "Chinese", "xiangtan", "yichang", 2015), Row("2531", "Chinese", "yuhua", "wuhan", 2015), Row("2563", "Chinese", "hongshan", "changsha", 2015), Row("2574", "Chinese", "longgang", "shenzhen", 2015), Row("2591", "Chinese", "yichang", "xiangtan", 2015), Row("2594", "Chinese", "tianyuan", "yichang", 2015), Row("2597", "Chinese", "longgang", "guangzhou", 2015), Row("2644", "Chinese", "xiangtan", "xiangtan", 2015), Row("2696", "Chinese", "longhua", "zhuzhou", 2015), Row("2705", "Chinese", "xiangtan", "guangzhou", 2015), Row("273", "Chinese", "yichang", "wuhan", 2015), Row("2759", "Chinese", "yuhua", "yichang", 2015), Row("2761", "Chinese", "xiangtan", "xiangtan", 2015), Row("2765", "Chinese", "hongshan", "yichang", 2015), Row("2797", "Chinese", "xiangtan", "xiangtan", 2015), Row("2799", "Chinese", "yuhua", "yichang", 2015), Row("2823", "Chinese", "longhua", "yichang", 2015), Row("2828", "Chinese", "yichang", "zhuzhou", 2015), Row("2930", "Chinese", "longhua", "yichang", 2015), Row("2940", "Chinese", "tianyuan", "zhuzhou", 2015), Row("2963", "Chinese", "xiangtan", "changsha", 2015), Row("297", "Chinese", "longgang", "zhuzhou", 2015), Row("396", "Chinese", "xiangtan", "changsha", 2015), Row("44", "Chinese", "hongshan", "guangzhou", 2015), Row("446", "Chinese", "yichang", "yichang", 2015), Row("466", "Chinese", "xiangtan", "guangzhou", 2015), Row("47", "Chinese", "hongshan", "guangzhou", 2015), Row("477", "Chinese", "xiangtan", "yichang", 2015), Row("499", "Chinese", "xiangtan", "shenzhen", 2015), Row("513", "Chinese", "longhua", "zhuzhou", 2015), Row("546", "Chinese", "xiangtan", "changsha", 2015), Row("631", "Chinese", "tianyuan", "shenzhen", 2015), Row("68", "Chinese", "hongshan", "wuhan", 2015), Row("716", "Chinese", "yuhua", "zhuzhou", 2015), Row("776", "Chinese", "longgang", "zhuzhou", 2015), Row("839", "Chinese", "hongshan", "zhuzhou", 2015), Row("864", "Chinese", "hongshan", "guangzhou", 2015), Row("872", "Chinese", "xiangtan", "shenzhen", 2015), Row("93", "Chinese", "hongshan", "yichang", 2015), Row("987", "Chinese", "yichang", "wuhan", 2015)))
  })

  //TC_325
  test("SELECT imei, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE modelId > \"2000\" GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE modelId > \"2000\" GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"),
      sql("SELECT imei, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(gamePointId) AS Sum_gamePointId FROM (select * from hivetable) SUB_QRY WHERE modelId > \"2000\" GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"))
  })

  //TC_326
  test("SELECT imei, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE imei >= \"1AA1000000\" GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE imei >= \"1AA1000000\" GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"),
      sql("SELECT imei, deliveryCity, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(gamePointId) AS Sum_gamePointId FROM (select * from hivetable) SUB_QRY WHERE imei >= \"1AA1000000\" GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"))
  })

  //TC_328
  test("SELECT imei, deliveryCity, series, Latest_YEAR, gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE imei >= \"1AA1000000\" ORDER BY series ASC", NonRunningTests)({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, Latest_YEAR, gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE imei >= \"1AA1000000\" ORDER BY series ASC"),
      sql("SELECT imei, deliveryCity, series, Latest_YEAR, gamePointId FROM (select * from hivetable) SUB_QRY WHERE imei >= \"1AA1000000\" ORDER BY series ASC"))
  })

  //TC_330
  test("SELECT deliveryCity, channelsId, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, channelsId ORDER BY deliveryCity ASC, channelsId ASC")({
    checkAnswer(
      sql("SELECT deliveryCity, channelsId, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, channelsId ORDER BY deliveryCity ASC, channelsId ASC"),
      sql("SELECT deliveryCity, channelsId, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from hivetable) SUB_QRY GROUP BY deliveryCity, channelsId ORDER BY deliveryCity ASC, channelsId ASC"))
  })

  //TC_331
  test("SELECT series, imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, AVG(gamePointId) AS Avg_gamePointId, SUM(gamepointid) AS Sum_gamepointid FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, AVG(gamePointId) AS Avg_gamePointId, SUM(gamepointid) AS Sum_gamepointid FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      sql("SELECT series, imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, AVG(gamePointId) AS Avg_gamePointId, SUM(gamepointid) AS Sum_gamepointid FROM (select * from hivetable) SUB_QRY GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"))
  })

  //TC_332
  test("SELECT imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId, SUM(gamepointid) AS Sum_gamepointid, COUNT(series) AS Count_series FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId, SUM(gamepointid) AS Sum_gamepointid, COUNT(series) AS Count_series FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"),
      sql("SELECT imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId, SUM(gamepointid) AS Sum_gamepointid, COUNT(series) AS Count_series FROM (select * from hivetable) SUB_QRY GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"))
  })

  //TC_333
  test("SELECT imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId, SUM(gamepointid) AS Sum_gamepointid, COUNT(DISTINCT series) AS DistinctCount_series FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId, SUM(gamepointid) AS Sum_gamepointid, COUNT(DISTINCT series) AS DistinctCount_series FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"),
      sql("SELECT imei, deliveryCity, SUM(Latest_DAY) AS Sum_Latest_DAY, SUM(Latest_MONTH) AS Sum_Latest_MONTH, SUM(contractNumber) AS Sum_contractNumber, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId, SUM(gamepointid) AS Sum_gamepointid, COUNT(DISTINCT series) AS DistinctCount_series FROM (select * from hivetable) SUB_QRY GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"))
  })

  //TC_334
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series = \"7Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series = \"7Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from hivetable) SUB_QRY WHERE series = \"7Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"))
  })

  //TC_335
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series > \"5Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series > \"5Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from hivetable) SUB_QRY WHERE series > \"5Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"))
  })

  //TC_336
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series >= \"4Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series >= \"4Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from hivetable) SUB_QRY WHERE series >= \"4Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"))
  })

  //TC_337
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series < \"3Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series < \"3Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from hivetable) SUB_QRY WHERE series < \"3Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"))
  })

  //TC_338
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series <= \"5Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE series <= \"5Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from hivetable) SUB_QRY WHERE series <= \"5Series\" GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"))
  })

  //TC_339
  test("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE deliveryCity LIKE '%wuhan%' GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY WHERE deliveryCity LIKE '%wuhan%' GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"),
      sql("SELECT series, imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from hivetable) SUB_QRY WHERE deliveryCity LIKE '%wuhan%' GROUP BY series, imei, deliveryCity ORDER BY series ASC, imei ASC, deliveryCity ASC"))
  })

  //TC_350
  test("SELECT imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE NOT(deliveryCity = \"wuhan\") GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE NOT(deliveryCity = \"wuhan\") GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"),
      sql("SELECT imei, deliveryCity, SUM(gamePointId) AS Sum_gamePointId, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from hivetable) SUB_QRY WHERE NOT(deliveryCity = \"wuhan\") GROUP BY imei, deliveryCity ORDER BY imei ASC, deliveryCity ASC"))
  })

  //TC_365
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from hivetable) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"))
  })

  //TC_366
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, AVG(deviceInformationId) AS Avg_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, AVG(deviceInformationId) AS Avg_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, AVG(deviceInformationId) AS Avg_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from hivetable) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"))
  })

  //TC_367
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, COUNT(deviceInformationId) AS Count_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, COUNT(deviceInformationId) AS Count_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, COUNT(deviceInformationId) AS Count_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from hivetable) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"))
  })

  //TC_368
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, COUNT(DISTINCT deviceInformationId) AS LONG_COL_0, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, COUNT(DISTINCT deviceInformationId) AS LONG_COL_0, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, COUNT(DISTINCT deviceInformationId) AS LONG_COL_0, SUM(gamePointId) AS Sum_gamePointId FROM (select * from hivetable) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"))
  })

  //TC_370
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, MAX(deviceInformationId) AS Max_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, MAX(deviceInformationId) AS Max_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, MAX(deviceInformationId) AS Max_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from hivetable) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"))
  })

  //TC_371
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, MIN(deviceInformationId) AS Min_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, MIN(deviceInformationId) AS Min_deviceInformationId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0)))
  })

  //TC_374
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, AVG(gamePointId) AS Avg_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, AVG(gamePointId) AS Avg_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, AVG(gamePointId) AS Avg_gamePointId FROM (select * from hivetable) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"))
  })

  //TC_375
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(gamePointId) AS Count_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(gamePointId) AS Count_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(gamePointId) AS Count_gamePointId FROM (select * from hivetable) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"))
  })

  //TC_376
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(DISTINCT gamePointId) AS DistinctCount_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(DISTINCT gamePointId) AS DistinctCount_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 1), Row("1AA10", "yichang", "7Series", 2015, 10, 1), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 1), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 1), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 1), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 1), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 1), Row("1AA100004", "yichang", "4Series", 2015, 100004, 1), Row("1AA100005", "yichang", "1Series", 2015, 100005, 1), Row("1AA100006", "changsha", "6Series", 2015, 100006, 1), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1), Row("1AA10001", "changsha", "2Series", 2015, 10001, 1), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 1), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 1), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 1), Row("1AA100013", "changsha", "1Series", 2015, 100013, 1), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 1), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 1), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 1), Row("1AA100018", "yichang", "8Series", 2015, 100018, 1), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 1), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 1), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 1), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 1), Row("1AA100024", "changsha", "6Series", 2015, 100024, 1), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 1), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 1), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 1), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 1), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 1), Row("1AA100035", "changsha", "5Series", 2015, 100035, 1), Row("1AA100036", "changsha", "5Series", 2015, 100036, 1), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1), Row("1AA100040", "yichang", "8Series", 2015, 100040, 1), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 1), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 1), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 1), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 1), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 1), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 1), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1), Row("1AA100050", "yichang", "2Series", 2015, 100050, 1), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 1), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 1), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 1), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 1), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 1), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 1), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1), Row("1AA100062", "yichang", "9Series", 2015, 100062, 1), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 1), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 1), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 1), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 1), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1), Row("1AA100072", "changsha", "4Series", 2015, 100072, 1), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 1), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 1), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 1), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 1), Row("1AA100077", "yichang", "3Series", 2015, 100077, 1), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 1), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 1), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 1), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 1), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 1), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 1)))
  })

  //TC_377
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MAX(gamePointId) AS Max_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MAX(gamePointId) AS Max_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0)))
  })

  //TC_378
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei ASC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0)))
  })

  //TC_379
  test("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei DESC, deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT imei, deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei, deliveryCity, series ORDER BY imei DESC, deliveryCity ASC, series ASC"),
      Seq(Row("1AA100084", "guangzhou", "0Series", 2015, 100084, 2826.0), Row("1AA100083", "zhuzhou", "0Series", 2015, 100083, 2192.0), Row("1AA100082", "xiangtan", "5Series", 2015, 100082, 2348.0), Row("1AA100081", "shenzhen", "5Series", 2015, 100081, 613.0), Row("1AA100080", "shenzhen", "9Series", 2015, 100080, 954.0), Row("1AA10008", "shenzhen", "5Series", 2015, 10008, 813.0), Row("1AA100079", "xiangtan", "4Series", 2015, 100079, 1098.0), Row("1AA100078", "yichang", "2Series", 2015, 100078, 1434.0), Row("1AA100077", "yichang", "3Series", 2015, 100077, 2077.0), Row("1AA100076", "wuhan", "0Series", 2015, 100076, 732.0), Row("1AA100075", "shenzhen", "3Series", 2015, 100075, 2507.0), Row("1AA100074", "wuhan", "6Series", 2015, 100074, 907.0), Row("1AA100073", "zhuzhou", "4Series", 2015, 100073, 2488.0), Row("1AA100072", "changsha", "4Series", 2015, 100072, 448.0), Row("1AA100071", "guangzhou", "0Series", 2015, 100071, 1973.0), Row("1AA100070", "guangzhou", "0Series", 2015, 100070, 1567.0), Row("1AA10007", "xiangtan", "8Series", 2015, 10007, 1350.0), Row("1AA100069", "xiangtan", "8Series", 2015, 100069, 1491.0), Row("1AA100068", "guangzhou", "8Series", 2015, 100068, 412.0), Row("1AA100067", "wuhan", "4Series", 2015, 100067, 572.0), Row("1AA100066", "zhuzhou", "6Series", 2015, 100066, 1864.0), Row("1AA100065", "xiangtan", "0Series", 2015, 100065, 901.0), Row("1AA100064", "zhuzhou", "6Series", 2015, 100064, 865.0), Row("1AA100063", "yichang", "2Series", 2015, 100063, 1226.0), Row("1AA100062", "yichang", "9Series", 2015, 100062, 2952.0), Row("1AA100061", "changsha", "6Series", 2015, 100061, 1407.0), Row("1AA100060", "xiangtan", "8Series", 2015, 100060, 538.0), Row("1AA10006", "guangzhou", "3Series", 2015, 10006, 2478.0), Row("1AA100059", "shenzhen", "4Series", 2015, 100059, 1337.0), Row("1AA100058", "guangzhou", "5Series", 2015, 100058, 2635.0), Row("1AA100057", "zhuzhou", "9Series", 2015, 100057, 2288.0), Row("1AA100056", "wuhan", "6Series", 2015, 100056, 750.0), Row("1AA100055", "yichang", "7Series", 2015, 100055, 1728.0), Row("1AA100054", "shenzhen", "7Series", 2015, 100054, 1368.0), Row("1AA100053", "wuhan", "2Series", 2015, 100053, 1655.0), Row("1AA100052", "zhuzhou", "6Series", 2015, 100052, 845.0), Row("1AA100051", "guangzhou", "2Series", 2015, 100051, 1407.0), Row("1AA100050", "yichang", "2Series", 2015, 100050, 29.0), Row("1AA10005", "xiangtan", "8Series", 2015, 10005, 1608.0), Row("1AA100049", "guangzhou", "0Series", 2015, 100049, 2890.0), Row("1AA100048", "guangzhou", "3Series", 2015, 100048, 2399.0), Row("1AA100047", "zhuzhou", "9Series", 2015, 100047, 1823.0), Row("1AA100046", "guangzhou", "3Series", 2015, 100046, 1077.0), Row("1AA100045", "xiangtan", "2Series", 2015, 100045, 2553.0), Row("1AA100044", "guangzhou", "8Series", 2015, 100044, 1697.0), Row("1AA100043", "guangzhou", "9Series", 2015, 100043, 571.0), Row("1AA100042", "shenzhen", "3Series", 2015, 100042, 2745.0), Row("1AA100041", "shenzhen", "5Series", 2015, 100041, 2734.0), Row("1AA100040", "yichang", "8Series", 2015, 100040, 2078.0), Row("1AA10004", "guangzhou", "5Series", 2015, 10004, 1717.0), Row("1AA100039", "shenzhen", "8Series", 2015, 100039, 1750.0), Row("1AA100038", "shenzhen", "6Series", 2015, 100038, 1229.0), Row("1AA100037", "xiangtan", "7Series", 2015, 100037, 1015.0), Row("1AA100036", "changsha", "5Series", 2015, 100036, 2224.0), Row("1AA100035", "changsha", "5Series", 2015, 100035, 2142.0), Row("1AA100034", "guangzhou", "2Series", 2015, 100034, 2061.0), Row("1AA100033", "wuhan", "8Series", 2015, 100033, 760.0), Row("1AA100032", "shenzhen", "1Series", 2015, 100032, 1053.0), Row("1AA100031", "yichang", "7Series", 2015, 100031, 1080.0), Row("1AA100030", "zhuzhou", "7Series", 2015, 100030, 1333.0), Row("1AA10003", "xiangtan", "7Series", 2015, 10003, 2071.0), Row("1AA100029", "xiangtan", "2Series", 2015, 100029, 1691.0), Row("1AA100028", "zhuzhou", "5Series", 2015, 100028, 2849.0), Row("1AA100027", "zhuzhou", "0Series", 2015, 100027, 2436.0), Row("1AA100026", "yichang", "7Series", 2015, 100026, 1768.0), Row("1AA100025", "guangzhou", "0Series", 2015, 100025, 1724.0), Row("1AA100024", "changsha", "6Series", 2015, 100024, 2483.0), Row("1AA100023", "guangzhou", "5Series", 2015, 100023, 2194.0), Row("1AA100022", "zhuzhou", "5Series", 2015, 100022, 1999.0), Row("1AA100021", "changsha", "0Series", 2015, 100021, 1778.0), Row("1AA100020", "shenzhen", "5Series", 2015, 100020, 256.0), Row("1AA10002", "wuhan", "0Series", 2015, 10002, 2972.0), Row("1AA100019", "zhuzhou", "5Series", 2015, 100019, 2194.0), Row("1AA100018", "yichang", "8Series", 2015, 100018, 441.0), Row("1AA100017", "xiangtan", "9Series", 2015, 100017, 2205.0), Row("1AA100016", "changsha", "3Series", 2015, 100016, 1873.0), Row("1AA100015", "xiangtan", "4Series", 2015, 100015, 2863.0), Row("1AA100014", "zhuzhou", "5Series", 2015, 100014, 151.0), Row("1AA100013", "changsha", "1Series", 2015, 100013, 355.0), Row("1AA100012", "xiangtan", "4Series", 2015, 100012, 568.0), Row("1AA100011", "guangzhou", "0Series", 2015, 100011, 202.0), Row("1AA100010", "zhuzhou", "3Series", 2015, 100010, 79.0), Row("1AA10001", "changsha", "2Series", 2015, 10001, 298.0), Row("1AA100009", "yichang", "0Series", 2015, 100009, 1841.0), Row("1AA100008", "changsha", "8Series", 2015, 100008, 1442.0), Row("1AA100007", "changsha", "9Series", 2015, 100007, 1991.0), Row("1AA100006", "changsha", "6Series", 2015, 100006, 2572.0), Row("1AA100005", "yichang", "1Series", 2015, 100005, 2593.0), Row("1AA100004", "yichang", "4Series", 2015, 100004, 2970.0), Row("1AA100003", "zhuzhou", "5Series", 2015, 100003, 2239.0), Row("1AA100002", "changsha", "0Series", 2015, 100002, 1341.0), Row("1AA100001", "xiangtan", "0Series", 2015, 100001, 505.0), Row("1AA1000000", "yichang", "7Series", 2015, 1000000, 1600.0), Row("1AA100000", "wuhan", "9Series", 2015, 100000, 136.0), Row("1AA10000", "guangzhou", "7Series", 2015, 10000, 2175.0), Row("1AA1000", "wuhan", "5Series", 2015, 1000, 692.0), Row("1AA100", "xiangtan", "5Series", 2015, 100, 1271.0), Row("1AA10", "yichang", "7Series", 2015, 10, 1714.635), Row("1AA1", "yichang", "7Series", 2015, 1, 2738.562)))
  })

  //TC_380
  test("SELECT deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(imei) AS Count_imei, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, series ORDER BY deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(imei) AS Count_imei, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, series ORDER BY deliveryCity ASC, series ASC"),
      Seq(Row("changsha", "0Series", 4030, 200023, 2, 1341.0), Row("changsha", "1Series", 2015, 100013, 1, 355.0), Row("changsha", "2Series", 2015, 10001, 1, 298.0), Row("changsha", "3Series", 2015, 100016, 1, 1873.0), Row("changsha", "4Series", 2015, 100072, 1, 448.0), Row("changsha", "5Series", 4030, 200071, 2, 2142.0), Row("changsha", "6Series", 6045, 300091, 3, 1407.0), Row("changsha", "8Series", 2015, 100008, 1, 1442.0), Row("changsha", "9Series", 2015, 100007, 1, 1991.0), Row("guangzhou", "0Series", 12090, 600310, 6, 202.0), Row("guangzhou", "2Series", 4030, 200085, 2, 1407.0), Row("guangzhou", "3Series", 6045, 210100, 3, 1077.0), Row("guangzhou", "5Series", 6045, 210085, 3, 1717.0), Row("guangzhou", "7Series", 2015, 10000, 1, 2175.0), Row("guangzhou", "8Series", 4030, 200112, 2, 412.0), Row("guangzhou", "9Series", 2015, 100043, 1, 571.0), Row("shenzhen", "1Series", 2015, 100032, 1, 1053.0), Row("shenzhen", "3Series", 4030, 200117, 2, 2507.0), Row("shenzhen", "4Series", 2015, 100059, 1, 1337.0), Row("shenzhen", "5Series", 8060, 310150, 4, 256.0), Row("shenzhen", "6Series", 2015, 100038, 1, 1229.0), Row("shenzhen", "7Series", 2015, 100054, 1, 1368.0), Row("shenzhen", "8Series", 2015, 100039, 1, 1750.0), Row("shenzhen", "9Series", 2015, 100080, 1, 954.0), Row("wuhan", "0Series", 4030, 110078, 2, 732.0), Row("wuhan", "2Series", 2015, 100053, 1, 1655.0), Row("wuhan", "4Series", 2015, 100067, 1, 572.0), Row("wuhan", "5Series", 2015, 1000, 1, 692.0), Row("wuhan", "6Series", 4030, 200130, 2, 750.0), Row("wuhan", "8Series", 2015, 100033, 1, 760.0), Row("wuhan", "9Series", 2015, 100000, 1, 136.0), Row("xiangtan", "0Series", 4030, 200066, 2, 505.0), Row("xiangtan", "2Series", 4030, 200074, 2, 1691.0), Row("xiangtan", "4Series", 6045, 300106, 3, 568.0), Row("xiangtan", "5Series", 4030, 100182, 2, 1271.0), Row("xiangtan", "7Series", 4030, 110040, 2, 1015.0), Row("xiangtan", "8Series", 8060, 220141, 4, 538.0), Row("xiangtan", "9Series", 2015, 100017, 1, 2205.0), Row("yichang", "0Series", 2015, 100009, 1, 1841.0), Row("yichang", "1Series", 2015, 100005, 1, 2593.0), Row("yichang", "2Series", 6045, 300191, 3, 29.0), Row("yichang", "3Series", 2015, 100077, 1, 2077.0), Row("yichang", "4Series", 2015, 100004, 1, 2970.0), Row("yichang", "7Series", 12090, 1300123, 6, 1080.0), Row("yichang", "8Series", 4030, 200058, 2, 441.0), Row("yichang", "9Series", 2015, 100062, 1, 2952.0), Row("zhuzhou", "0Series", 4030, 200110, 2, 2192.0), Row("zhuzhou", "3Series", 2015, 100010, 1, 79.0), Row("zhuzhou", "4Series", 2015, 100073, 1, 2488.0), Row("zhuzhou", "5Series", 10075, 500086, 5, 151.0), Row("zhuzhou", "6Series", 6045, 300182, 3, 845.0), Row("zhuzhou", "7Series", 2015, 100030, 1, 1333.0), Row("zhuzhou", "9Series", 4030, 200104, 2, 1823.0)))
  })

  //TC_381
  test("SELECT deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(DISTINCT imei) AS DistinctCount_imei, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, series ORDER BY deliveryCity ASC, series ASC")({
    checkAnswer(
      sql("SELECT deliveryCity, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR, SUM(deviceInformationId) AS Sum_deviceInformationId, COUNT(DISTINCT imei) AS DistinctCount_imei, MIN(gamePointId) AS Min_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, series ORDER BY deliveryCity ASC, series ASC"),
      Seq(Row("changsha", "0Series", 4030, 200023, 2, 1341.0), Row("changsha", "1Series", 2015, 100013, 1, 355.0), Row("changsha", "2Series", 2015, 10001, 1, 298.0), Row("changsha", "3Series", 2015, 100016, 1, 1873.0), Row("changsha", "4Series", 2015, 100072, 1, 448.0), Row("changsha", "5Series", 4030, 200071, 2, 2142.0), Row("changsha", "6Series", 6045, 300091, 3, 1407.0), Row("changsha", "8Series", 2015, 100008, 1, 1442.0), Row("changsha", "9Series", 2015, 100007, 1, 1991.0), Row("guangzhou", "0Series", 12090, 600310, 6, 202.0), Row("guangzhou", "2Series", 4030, 200085, 2, 1407.0), Row("guangzhou", "3Series", 6045, 210100, 3, 1077.0), Row("guangzhou", "5Series", 6045, 210085, 3, 1717.0), Row("guangzhou", "7Series", 2015, 10000, 1, 2175.0), Row("guangzhou", "8Series", 4030, 200112, 2, 412.0), Row("guangzhou", "9Series", 2015, 100043, 1, 571.0), Row("shenzhen", "1Series", 2015, 100032, 1, 1053.0), Row("shenzhen", "3Series", 4030, 200117, 2, 2507.0), Row("shenzhen", "4Series", 2015, 100059, 1, 1337.0), Row("shenzhen", "5Series", 8060, 310150, 4, 256.0), Row("shenzhen", "6Series", 2015, 100038, 1, 1229.0), Row("shenzhen", "7Series", 2015, 100054, 1, 1368.0), Row("shenzhen", "8Series", 2015, 100039, 1, 1750.0), Row("shenzhen", "9Series", 2015, 100080, 1, 954.0), Row("wuhan", "0Series", 4030, 110078, 2, 732.0), Row("wuhan", "2Series", 2015, 100053, 1, 1655.0), Row("wuhan", "4Series", 2015, 100067, 1, 572.0), Row("wuhan", "5Series", 2015, 1000, 1, 692.0), Row("wuhan", "6Series", 4030, 200130, 2, 750.0), Row("wuhan", "8Series", 2015, 100033, 1, 760.0), Row("wuhan", "9Series", 2015, 100000, 1, 136.0), Row("xiangtan", "0Series", 4030, 200066, 2, 505.0), Row("xiangtan", "2Series", 4030, 200074, 2, 1691.0), Row("xiangtan", "4Series", 6045, 300106, 3, 568.0), Row("xiangtan", "5Series", 4030, 100182, 2, 1271.0), Row("xiangtan", "7Series", 4030, 110040, 2, 1015.0), Row("xiangtan", "8Series", 8060, 220141, 4, 538.0), Row("xiangtan", "9Series", 2015, 100017, 1, 2205.0), Row("yichang", "0Series", 2015, 100009, 1, 1841.0), Row("yichang", "1Series", 2015, 100005, 1, 2593.0), Row("yichang", "2Series", 6045, 300191, 3, 29.0), Row("yichang", "3Series", 2015, 100077, 1, 2077.0), Row("yichang", "4Series", 2015, 100004, 1, 2970.0), Row("yichang", "7Series", 12090, 1300123, 6, 1080.0), Row("yichang", "8Series", 4030, 200058, 2, 441.0), Row("yichang", "9Series", 2015, 100062, 1, 2952.0), Row("zhuzhou", "0Series", 4030, 200110, 2, 2192.0), Row("zhuzhou", "3Series", 2015, 100010, 1, 79.0), Row("zhuzhou", "4Series", 2015, 100073, 1, 2488.0), Row("zhuzhou", "5Series", 10075, 500086, 5, 151.0), Row("zhuzhou", "6Series", 6045, 300182, 3, 845.0), Row("zhuzhou", "7Series", 2015, 100030, 1, 1333.0), Row("zhuzhou", "9Series", 4030, 200104, 2, 1823.0)))
  })

  //TC_382
  test("SELECT deliveryCity, Latest_YEAR, imei, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, Latest_YEAR, imei ORDER BY deliveryCity ASC, Latest_YEAR ASC, imei ASC")({
    checkAnswer(
      sql("SELECT deliveryCity, Latest_YEAR, imei, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, Latest_YEAR, imei ORDER BY deliveryCity ASC, Latest_YEAR ASC, imei ASC"),
      sql("SELECT deliveryCity, Latest_YEAR, imei, SUM(gamePointId) AS Sum_gamePointId, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from hivetable) SUB_QRY GROUP BY deliveryCity, Latest_YEAR, imei ORDER BY deliveryCity ASC, Latest_YEAR ASC, imei ASC"))
  })

  //TC_383
  test("SELECT deliveryCity, imei, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, imei, series ORDER BY deliveryCity ASC, imei ASC, series ASC")({
    checkAnswer(
      sql("SELECT deliveryCity, imei, series, SUM(Latest_YEAR) AS Sum_Latest_YEAR FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY deliveryCity, imei, series ORDER BY deliveryCity ASC, imei ASC, series ASC"),
      Seq(Row("changsha", "1AA100002", "0Series", 2015), Row("changsha", "1AA100006", "6Series", 2015), Row("changsha", "1AA100007", "9Series", 2015), Row("changsha", "1AA100008", "8Series", 2015), Row("changsha", "1AA10001", "2Series", 2015), Row("changsha", "1AA100013", "1Series", 2015), Row("changsha", "1AA100016", "3Series", 2015), Row("changsha", "1AA100021", "0Series", 2015), Row("changsha", "1AA100024", "6Series", 2015), Row("changsha", "1AA100035", "5Series", 2015), Row("changsha", "1AA100036", "5Series", 2015), Row("changsha", "1AA100061", "6Series", 2015), Row("changsha", "1AA100072", "4Series", 2015), Row("guangzhou", "1AA10000", "7Series", 2015), Row("guangzhou", "1AA100011", "0Series", 2015), Row("guangzhou", "1AA100023", "5Series", 2015), Row("guangzhou", "1AA100025", "0Series", 2015), Row("guangzhou", "1AA100034", "2Series", 2015), Row("guangzhou", "1AA10004", "5Series", 2015), Row("guangzhou", "1AA100043", "9Series", 2015), Row("guangzhou", "1AA100044", "8Series", 2015), Row("guangzhou", "1AA100046", "3Series", 2015), Row("guangzhou", "1AA100048", "3Series", 2015), Row("guangzhou", "1AA100049", "0Series", 2015), Row("guangzhou", "1AA100051", "2Series", 2015), Row("guangzhou", "1AA100058", "5Series", 2015), Row("guangzhou", "1AA10006", "3Series", 2015), Row("guangzhou", "1AA100068", "8Series", 2015), Row("guangzhou", "1AA100070", "0Series", 2015), Row("guangzhou", "1AA100071", "0Series", 2015), Row("guangzhou", "1AA100084", "0Series", 2015), Row("shenzhen", "1AA100020", "5Series", 2015), Row("shenzhen", "1AA100032", "1Series", 2015), Row("shenzhen", "1AA100038", "6Series", 2015), Row("shenzhen", "1AA100039", "8Series", 2015), Row("shenzhen", "1AA100041", "5Series", 2015), Row("shenzhen", "1AA100042", "3Series", 2015), Row("shenzhen", "1AA100054", "7Series", 2015), Row("shenzhen", "1AA100059", "4Series", 2015), Row("shenzhen", "1AA100075", "3Series", 2015), Row("shenzhen", "1AA10008", "5Series", 2015), Row("shenzhen", "1AA100080", "9Series", 2015), Row("shenzhen", "1AA100081", "5Series", 2015), Row("wuhan", "1AA1000", "5Series", 2015), Row("wuhan", "1AA100000", "9Series", 2015), Row("wuhan", "1AA10002", "0Series", 2015), Row("wuhan", "1AA100033", "8Series", 2015), Row("wuhan", "1AA100053", "2Series", 2015), Row("wuhan", "1AA100056", "6Series", 2015), Row("wuhan", "1AA100067", "4Series", 2015), Row("wuhan", "1AA100074", "6Series", 2015), Row("wuhan", "1AA100076", "0Series", 2015), Row("xiangtan", "1AA100", "5Series", 2015), Row("xiangtan", "1AA100001", "0Series", 2015), Row("xiangtan", "1AA100012", "4Series", 2015), Row("xiangtan", "1AA100015", "4Series", 2015), Row("xiangtan", "1AA100017", "9Series", 2015), Row("xiangtan", "1AA100029", "2Series", 2015), Row("xiangtan", "1AA10003", "7Series", 2015), Row("xiangtan", "1AA100037", "7Series", 2015), Row("xiangtan", "1AA100045", "2Series", 2015), Row("xiangtan", "1AA10005", "8Series", 2015), Row("xiangtan", "1AA100060", "8Series", 2015), Row("xiangtan", "1AA100065", "0Series", 2015), Row("xiangtan", "1AA100069", "8Series", 2015), Row("xiangtan", "1AA10007", "8Series", 2015), Row("xiangtan", "1AA100079", "4Series", 2015), Row("xiangtan", "1AA100082", "5Series", 2015), Row("yichang", "1AA1", "7Series", 2015), Row("yichang", "1AA10", "7Series", 2015), Row("yichang", "1AA1000000", "7Series", 2015), Row("yichang", "1AA100004", "4Series", 2015), Row("yichang", "1AA100005", "1Series", 2015), Row("yichang", "1AA100009", "0Series", 2015), Row("yichang", "1AA100018", "8Series", 2015), Row("yichang", "1AA100026", "7Series", 2015), Row("yichang", "1AA100031", "7Series", 2015), Row("yichang", "1AA100040", "8Series", 2015), Row("yichang", "1AA100050", "2Series", 2015), Row("yichang", "1AA100055", "7Series", 2015), Row("yichang", "1AA100062", "9Series", 2015), Row("yichang", "1AA100063", "2Series", 2015), Row("yichang", "1AA100077", "3Series", 2015), Row("yichang", "1AA100078", "2Series", 2015), Row("zhuzhou", "1AA100003", "5Series", 2015), Row("zhuzhou", "1AA100010", "3Series", 2015), Row("zhuzhou", "1AA100014", "5Series", 2015), Row("zhuzhou", "1AA100019", "5Series", 2015), Row("zhuzhou", "1AA100022", "5Series", 2015), Row("zhuzhou", "1AA100027", "0Series", 2015), Row("zhuzhou", "1AA100028", "5Series", 2015), Row("zhuzhou", "1AA100030", "7Series", 2015), Row("zhuzhou", "1AA100047", "9Series", 2015), Row("zhuzhou", "1AA100052", "6Series", 2015), Row("zhuzhou", "1AA100057", "9Series", 2015), Row("zhuzhou", "1AA100064", "6Series", 2015), Row("zhuzhou", "1AA100066", "6Series", 2015), Row("zhuzhou", "1AA100073", "4Series", 2015), Row("zhuzhou", "1AA100083", "0Series", 2015)))
  })


  //TC_389
  test("SELECT imei, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei ORDER BY imei ASC")({
    checkAnswer(
      sql("SELECT imei, SUM(deviceInformationId) AS Sum_deviceInformationId FROM (select * from Carbon_automation_test6) SUB_QRY GROUP BY imei ORDER BY imei ASC"),
      Seq(Row("1AA1", 1), Row("1AA10", 10), Row("1AA100", 100), Row("1AA1000", 1000), Row("1AA10000", 10000), Row("1AA100000", 100000), Row("1AA1000000", 1000000), Row("1AA100001", 100001), Row("1AA100002", 100002), Row("1AA100003", 100003), Row("1AA100004", 100004), Row("1AA100005", 100005), Row("1AA100006", 100006), Row("1AA100007", 100007), Row("1AA100008", 100008), Row("1AA100009", 100009), Row("1AA10001", 10001), Row("1AA100010", 100010), Row("1AA100011", 100011), Row("1AA100012", 100012), Row("1AA100013", 100013), Row("1AA100014", 100014), Row("1AA100015", 100015), Row("1AA100016", 100016), Row("1AA100017", 100017), Row("1AA100018", 100018), Row("1AA100019", 100019), Row("1AA10002", 10002), Row("1AA100020", 100020), Row("1AA100021", 100021), Row("1AA100022", 100022), Row("1AA100023", 100023), Row("1AA100024", 100024), Row("1AA100025", 100025), Row("1AA100026", 100026), Row("1AA100027", 100027), Row("1AA100028", 100028), Row("1AA100029", 100029), Row("1AA10003", 10003), Row("1AA100030", 100030), Row("1AA100031", 100031), Row("1AA100032", 100032), Row("1AA100033", 100033), Row("1AA100034", 100034), Row("1AA100035", 100035), Row("1AA100036", 100036), Row("1AA100037", 100037), Row("1AA100038", 100038), Row("1AA100039", 100039), Row("1AA10004", 10004), Row("1AA100040", 100040), Row("1AA100041", 100041), Row("1AA100042", 100042), Row("1AA100043", 100043), Row("1AA100044", 100044), Row("1AA100045", 100045), Row("1AA100046", 100046), Row("1AA100047", 100047), Row("1AA100048", 100048), Row("1AA100049", 100049), Row("1AA10005", 10005), Row("1AA100050", 100050), Row("1AA100051", 100051), Row("1AA100052", 100052), Row("1AA100053", 100053), Row("1AA100054", 100054), Row("1AA100055", 100055), Row("1AA100056", 100056), Row("1AA100057", 100057), Row("1AA100058", 100058), Row("1AA100059", 100059), Row("1AA10006", 10006), Row("1AA100060", 100060), Row("1AA100061", 100061), Row("1AA100062", 100062), Row("1AA100063", 100063), Row("1AA100064", 100064), Row("1AA100065", 100065), Row("1AA100066", 100066), Row("1AA100067", 100067), Row("1AA100068", 100068), Row("1AA100069", 100069), Row("1AA10007", 10007), Row("1AA100070", 100070), Row("1AA100071", 100071), Row("1AA100072", 100072), Row("1AA100073", 100073), Row("1AA100074", 100074), Row("1AA100075", 100075), Row("1AA100076", 100076), Row("1AA100077", 100077), Row("1AA100078", 100078), Row("1AA100079", 100079), Row("1AA10008", 10008), Row("1AA100080", 100080), Row("1AA100081", 100081), Row("1AA100082", 100082), Row("1AA100083", 100083), Row("1AA100084", 100084)))
  })

  //TC_422
  test("select  min(channelsName) from Carbon_automation_test6 where  deviceinformationid is  null", NonRunningTests)({
    checkAnswer(
      sql("select  min(channelsName) from Carbon_automation_test6 where  deviceinformationid is  null"),
      sql("select  min(channelsName) from hivetable where  deviceinformationid is  null"))
  })

  //TC_423
  test("select  max(channelsName) from  Carbon_automation_test6 where  deviceinformationid is  null", NonRunningTests)({
    checkAnswer(
      sql("select  max(channelsName) from  Carbon_automation_test6 where  deviceinformationid is  null"),
      sql("select  max(channelsName) from  hivetable where  deviceinformationid is  null"))
  })

  //TC_437
  test("SELECT sum(deviceInformationId) FROM Carbon_automation_test6 where imei is NOT null")({
    checkAnswer(
      sql("SELECT sum(deviceInformationId) FROM Carbon_automation_test6 where imei is NOT null"),
      Seq(Row(9594717)))
  })

  //TC_441
  test("select variance(gamepointid), var_pop(gamepointid)  from Carbon_automation_test6 where channelsid>2")({
    checkAnswer(
      sql("select variance(gamepointid), var_pop(gamepointid)  from Carbon_automation_test6 where channelsid>2"),
      sql("select variance(gamepointid), var_pop(gamepointid)  from hivetable where channelsid>2"))
  })

  //TC_445
  test("select variance(bomcode), var_pop(gamepointid)  from Carbon_automation_test6 where activeareaid>3")({
    checkAnswer(
      sql("select variance(bomcode), var_pop(gamepointid)  from Carbon_automation_test6 where activeareaid>3"),
      sql("select variance(bomcode), var_pop(gamepointid)  from hivetable where activeareaid>3"))
  })

  //TC_447
  test("select var_samp(contractNumber) from Carbon_automation_test6", NonRunningTests)({
    checkAnswer(
      sql("select var_samp(contractNumber) from Carbon_automation_test6"),
      sql("select var_samp(contractNumber) from hivetable"))
  })

  //TC_464
  test("select covar_pop(gamePointId,deviceInformationId ) from Carbon_automation_test6")({
    checkAnswer(
      sql("select covar_pop(gamePointId,deviceInformationId ) from Carbon_automation_test6"),
      sql("select covar_pop(gamePointId,deviceInformationId ) from hivetable"))
  })

  //TC_471
  test("select corr(Latest_MONTH, gamePointId) from Carbon_automation_test6", NonRunningTests)({
    checkAnswer(
      sql("select corr(Latest_MONTH, gamePointId) from Carbon_automation_test6"),
      sql("select corr(Latest_MONTH, gamePointId) from hivetable"))
  })

  //TC_498
  test("select covar_pop(gamePointId, contractNumber) from Carbon_automation_test6")({
    checkAnswer(
      sql("select covar_pop(gamePointId, contractNumber) from Carbon_automation_test6"),
      sql("select covar_pop(gamePointId, contractNumber) from hivetable"))
  })

  //TC_499
  test("select covar_samp(gamePointId, contractNumber) from Carbon_automation_test6")({
    checkAnswer(
      sql("select covar_samp(gamePointId, contractNumber) from Carbon_automation_test6"),
      sql("select covar_pop(gamePointId, contractNumber) from hivetable"))
  })

//AllDataTypesTestCase3


  //TC_270
  test("SELECT AMSize, ActiveAreaId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE AMSize < \"0RAM size\" GROUP BY AMSize, ActiveAreaId ORDER BY AMSize ASC, ActiveAreaId ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT AMSize, ActiveAreaId, SUM(gamePointId) AS Sum_gamePointId FROM (select * from Carbon_automation_test6) SUB_QRY WHERE AMSize < \"0RAM size\" GROUP BY AMSize, ActiveAreaId ORDER BY AMSize ASC, ActiveAreaId ASC"),
      Seq())
  })

  //TC_612
  test("SELECT Carbon_automation_test6.gamePointId AS gamePointId,Carbon_automation_test6.AMSize AS AMSize, Carbon_automation_test6.ActiveCountry AS ActiveCountry, Carbon_automation_test6.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test6) SUB_QRY ) Carbon_automation_test6 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test6) SUB_QRY ) Carbon_automation_test61 ON Carbon_automation_test6.AMSize = Carbon_automation_test61.AMSize WHERE Carbon_automation_test6.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test6.AMSize, Carbon_automation_test6.ActiveCountry, Carbon_automation_test6.Activecity ,Carbon_automation_test6.gamePointId ORDER BY Carbon_automation_test6.AMSize ASC, Carbon_automation_test6.ActiveCountry ASC, Carbon_automation_test6.Activecity ASC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test6.gamePointId AS gamePointId,Carbon_automation_test6.AMSize AS AMSize, Carbon_automation_test6.ActiveCountry AS ActiveCountry, Carbon_automation_test6.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test6) SUB_QRY ) Carbon_automation_test6 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test6) SUB_QRY ) Carbon_automation_test61 ON Carbon_automation_test6.AMSize = Carbon_automation_test61.AMSize WHERE Carbon_automation_test6.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test6.AMSize, Carbon_automation_test6.ActiveCountry, Carbon_automation_test6.Activecity ,Carbon_automation_test6.gamePointId ORDER BY Carbon_automation_test6.AMSize ASC, Carbon_automation_test6.ActiveCountry ASC, Carbon_automation_test6.Activecity ASC"), "TC_612.csv")
  })


  //VMALL_Per_TC_000
  test("select count(*) from    myvmallTest", NonRunningTests) ({
    checkAnswer(
      sql("select count(*) from    myvmallTest"),
      Seq(Row(1003)))
  })

  //VMALL_Per_TC_001
  test("SELECT product_name, count(distinct imei) DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY product_name ORDER BY product_name ASC", NonRunningTests) ({
    validateResult(sql("SELECT product_name, count(distinct imei) DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY product_name ORDER BY product_name ASC"), "VMALL_Per_TC_001.csv")

  })

  //VMALL_Per_TC_002
  test("SELECT device_name, product, product_name, COUNT(DISTINCT imei) AS DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY device_name, product, product_name ORDER BY device_name ASC, product ASC, product_name ASC", NonRunningTests) ({
    validateResult(sql("SELECT device_name, product, product_name, COUNT(DISTINCT imei) AS DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY device_name, product, product_name ORDER BY device_name ASC, product ASC, product_name ASC"), "VMALL_Per_TC_002.csv")
  })

  //VMALL_Per_TC_004
  test("SELECT device_color FROM (select * from myvmallTest) SUB_QRY GROUP BY device_color ORDER BY device_color ASC", NonRunningTests) ({
    validateResult(sql("SELECT device_color FROM (select * from myvmallTest) SUB_QRY GROUP BY device_color ORDER BY device_color ASC"), "VMALL_Per_TC_004.csv")
  })

  //VMALL_Per_TC_005
  test("SELECT product_name  FROM (select * from myvmallTest) SUB_QRY GROUP BY product_name ORDER BY  product_name ASC", NonRunningTests) ({
    validateResult(sql("SELECT product_name  FROM (select * from myvmallTest) SUB_QRY GROUP BY product_name ORDER BY  product_name ASC"), "VMALL_Per_TC_005.csv")
  })

  //VMALL_Per_TC_006
  test("SELECT product, COUNT(DISTINCT packing_list_no) AS LONG_COL_0 FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC", NonRunningTests) ({
    validateResult(sql("SELECT product, COUNT(DISTINCT packing_list_no) AS LONG_COL_0 FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC"), "VMALL_Per_TC_006.csv")
  })

  //VMALL_Per_TC_007
  test("select count(distinct imei) DistinctCount_imei from myvmallTest", NonRunningTests) ({
    checkAnswer(
      sql("select count(distinct imei) DistinctCount_imei from myvmallTest"),
      Seq(Row(1001)))
  })

  //VMALL_Per_TC_008
  test("Select count(imei),deliveryCountry  from myvmallTest group by deliveryCountry order by deliveryCountry asc", NonRunningTests) ({
    validateResult(sql("Select count(imei),deliveryCountry  from myvmallTest group by deliveryCountry order by deliveryCountry asc"), "VMALL_Per_TC_008.csv")
  })

  //VMALL_Per_TC_009
  test("select (t1.hnor6emui/t2.totalc)*100 from (select count (Active_emui_version)  as hnor6emui from myvmallTest where Active_emui_version=\"EmotionUI_2.1\")t1,(select count(Active_emui_version) as totalc from myvmallTest)t2", NonRunningTests) ({
    checkAnswer(
      sql("select (t1.hnor6emui/t2.totalc)*100 from (select count (Active_emui_version)  as hnor6emui from myvmallTest where Active_emui_version=\"EmotionUI_2.1\")t1,(select count(Active_emui_version) as totalc from myvmallTest)t2"),
      Seq(Row(0.09970089730807577)))
  })

  //VMALL_Per_TC_012
  test("SELECT Active_os_version, count(distinct imei) DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_os_version ORDER BY Active_os_version ASC", NonRunningTests) ({
    validateResult(sql("SELECT Active_os_version, count(distinct imei) DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_os_version ORDER BY Active_os_version ASC"), "VMALL_Per_TC_012.csv")
  })

  //VMALL_Per_TC_B015
  test("SELECT product, count(distinct imei) DistinctCount_imei FROM myvmallTest GROUP BY product ORDER BY product ASC", NonRunningTests) ({
    validateResult(sql("SELECT product, count(distinct imei) DistinctCount_imei FROM myvmallTest GROUP BY product ORDER BY product ASC"), "VMALL_Per_TC_B015.csv")
  })

  //VMALL_Per_TC_B016
  test("SELECT Active_emui_version, product, product_desc, COUNT(DISTINCT imei) AS DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_emui_version, product, product_desc ORDER BY Active_emui_version ASC, product ASC, product_desc ASC", NonRunningTests) ({
    validateResult(sql("SELECT Active_emui_version, product, product_desc, COUNT(DISTINCT imei) AS DistinctCount_imei FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_emui_version, product, product_desc ORDER BY Active_emui_version ASC, product ASC, product_desc ASC"), "VMALL_Per_TC_B016.csv")
  })

  //VMALL_Per_TC_B018
  test("SELECT Active_emui_version FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_emui_version ORDER BY Active_emui_version ASC", NonRunningTests) ({
    validateResult(sql("SELECT Active_emui_version FROM (select * from myvmallTest) SUB_QRY GROUP BY Active_emui_version ORDER BY Active_emui_version ASC"), "VMALL_Per_TC_B018.csv")
  })

  //VMALL_Per_TC_B019
  test("SELECT product FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC", NonRunningTests) ({
    validateResult(sql("SELECT product FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC"), "VMALL_Per_TC_B019.csv")
  })

  //VMALL_Per_TC_B020
  test("SELECT product, COUNT(DISTINCT Active_emui_version) AS LONG_COL_0 FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC", NonRunningTests) ({
    validateResult(sql("SELECT product, COUNT(DISTINCT Active_emui_version) AS LONG_COL_0 FROM (select * from myvmallTest) SUB_QRY GROUP BY product ORDER BY product ASC"), "VMALL_Per_TC_B020.csv")
  })

  //VMALL_Per_TC_023
  test("SELECT  count(imei) as distinct_imei,series FROM (select * from    myvmallTest   ) SUB_QRY where series LIKE 'series1%' group by series", NonRunningTests) ({
    validateResult(sql("SELECT  count(imei) as distinct_imei,series FROM (select * from    myvmallTest   ) SUB_QRY where series LIKE 'series1%' group by series"), "VMALL_Per_TC_023.csv")
  })

  //VMALL_Per_TC_038
  test("select Latest_network, count(distinct imei) as imei_number from  myvmallTest  group by Latest_network", NonRunningTests) ({
    validateResult(sql("select Latest_network, count(distinct imei) as imei_number from  myvmallTest  group by Latest_network"), "VMALL_Per_TC_038.csv")
  })

  //VMALL_Per_TC_039
  test("select device_name, count(distinct imei) as imei_number from  myvmallTest  group by device_name", NonRunningTests) ({
    validateResult(sql("select device_name, count(distinct imei) as imei_number from  myvmallTest  group by device_name"), "VMALL_Per_TC_039.csv")
  })


  //VMALL_Per_TC_040
  test("select product_name, count(distinct imei) as imei_number from  myvmallTest  group by product_name", NonRunningTests) ({
    validateResult(sql("select product_name, count(distinct imei) as imei_number from  myvmallTest  group by product_name"), "VMALL_Per_TC_040.csv")
  })

  //VMALL_Per_TC_041
  test("select deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity", NonRunningTests) ({
    validateResult(sql("select deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity"), "VMALL_Per_TC_041.csv")
  })

  //VMALL_Per_TC_042
  test("select device_name, deliverycity,count(distinct imei) as imei_number from  myvmallTest  group by device_name,deliverycity", NonRunningTests) ({
    validateResult(sql("select device_name, deliverycity,count(distinct imei) as imei_number from  myvmallTest  group by device_name,deliverycity"), "VMALL_Per_TC_042.csv")
  })

  //VMALL_Per_TC_043
  test("select product_name, device_name, count(distinct imei) as imei_number from  myvmallTest  group by product_name,device_name", NonRunningTests) ({
    validateResult(sql("select product_name, device_name, count(distinct imei) as imei_number from  myvmallTest  group by product_name,device_name"), "VMALL_Per_TC_043.csv")
  })

  //VMALL_Per_TC_044
  test("select product_name,deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity,product_name1", NonRunningTests) ({
    validateResult(sql("select product_name,deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity,product_name"), "VMALL_Per_TC_044.csv")
  })

  //VMALL_Per_TC_045
  test("select product_name,deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity,product_name", NonRunningTests) ({
    validateResult(sql("select product_name,deliverycity, count(distinct imei) as imei_number from  myvmallTest  group by deliverycity,product_name"), "VMALL_Per_TC_045.csv")
  })

  //VMALL_Per_TC_046
  test("select check_day,check_hour, count(distinct imei) as imei_number from  myvmallTest  group by check_day,check_hour", NonRunningTests) ({
    checkAnswer(
      sql("select check_day,check_hour, count(distinct imei) as imei_number from  myvmallTest  group by check_day,check_hour"),
      Seq(Row(15,-1,1000),Row(null,null,1)))
  })

  //VMALL_Per_TC_047
  test("select device_color,product_name, count(distinct imei) as imei_number from  myvmallTest  group by device_color,product_name order by product_name limit 1000", NonRunningTests) ({
    validateResult(sql("select device_color,product_name, count(distinct imei) as imei_number from  myvmallTest  group by device_color,product_name order by product_name limit 1000"), "VMALL_Per_TC_047.csv")
  })

  //VMALL_Per_TC_048
  test("select packing_hour,deliveryCity,device_color,count(distinct imei) as imei_number from  myvmallTest  group by packing_hour,deliveryCity,device_color order by deliveryCity  limit 1000", NonRunningTests) ({
    validateResult(sql("select packing_hour,deliveryCity,device_color,count(distinct imei) as imei_number from  myvmallTest  group by packing_hour,deliveryCity,device_color order by deliveryCity  limit 1000"), "VMALL_Per_TC_048.csv")
  })

  //VMALL_Per_TC_049
  test("SELECT product_name, count(distinct imei) DistinctCount_imei FROM  myvmallTest  GROUP BY product_name ORDER BY product_name ASC", NonRunningTests) ({
    validateResult(sql("SELECT product_name, count(distinct imei) DistinctCount_imei FROM  myvmallTest  GROUP BY product_name ORDER BY product_name ASC"), "VMALL_Per_TC_049.csv")
  })

  //VMALL_Per_TC_051
  test("SELECT device_color, product_name, COUNT(DISTINCT imei) AS DistinctCount_imei FROM  myvmallTest  GROUP BY device_color, product_name ORDER BY device_color ASC, product_name ASC", NonRunningTests) ({
    validateResult(sql("SELECT device_color, product_name, COUNT(DISTINCT imei) AS DistinctCount_imei FROM  myvmallTest  GROUP BY device_color, product_name ORDER BY device_color ASC, product_name ASC"), "VMALL_Per_TC_051.csv")
  })

  //VMALL_Per_TC_053
  test("SELECT product_name FROM  myvmallTest  SUB_QRY GROUP BY product_name ORDER BY product_name ASC", NonRunningTests) ({
    validateResult(sql("SELECT product_name FROM  myvmallTest  SUB_QRY GROUP BY product_name ORDER BY product_name ASC"), "VMALL_Per_TC_053.csv")
  })

  //VMALL_Per_TC_054
  test("SELECT product_name, COUNT(DISTINCT Active_emui_version) AS LONG_COL_0 FROM  myvmallTest  GROUP BY product_name ORDER BY product_name ASC", NonRunningTests) ({
    validateResult(sql("SELECT product_name, COUNT(DISTINCT Active_emui_version) AS LONG_COL_0 FROM  myvmallTest  GROUP BY product_name ORDER BY product_name ASC"), "VMALL_Per_TC_054.csv")
  })

  //SmartPCC_Perf_TC_002
  test("select MSISDN,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where RAT_NAME='GERAN' group by MSISDN having total < 1073741824 order by total desc", NonRunningTests)({
    checkAnswer(
      sql("select MSISDN,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where RAT_NAME='GERAN' group by MSISDN having total < 1073741824 order by total desc"),
      Seq(Row("8613893462639", 2874640), Row("8613993676885", 73783), Row("8618394185970", 23865), Row("8618793100458", 15112), Row("8618794812876", 14411), Row("8615120474362", 6936), Row("8613893853351", 6486), Row("8618393012284", 5700), Row("8613993800024", 5044), Row("8618794965341", 4840), Row("8613993899110", 4364), Row("8613519003078", 2485), Row("8613649905753", 2381), Row("8613893600602", 2346), Row("8615117035070", 1310), Row("8618700943475", 1185), Row("8613919791668", 928), Row("8615209309657", 290), Row("8613993104233", 280)))
  })


  //SmartPCC_Perf_TC_004
  test("select APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number, sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_CATEGORY_NAME", NonRunningTests)({
    checkAnswer(
      sql("select APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number, sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_CATEGORY_NAME"),
      Seq(Row("Network_Admin", 5, 12402), Row("Web_Browsing", 6, 2972886), Row("IM", 2, 29565), Row("Tunnelling", 1, 4364), Row("Game", 1, 2485), Row("", 4, 24684)))
  })

  //SmartPCC_Perf_TC_005
  test("select APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where APP_CATEGORY_NAME='Web_Browsing' group by APP_CATEGORY_NAME order by msidn_number desc", NonRunningTests)({
    checkAnswer(
      sql("select APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where APP_CATEGORY_NAME='Web_Browsing' group by APP_CATEGORY_NAME order by msidn_number desc"),
      Seq(Row("Web_Browsing", 6, 2972886)))
  })

  //SmartPCC_Perf_TC_006
  test("select APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME", NonRunningTests)({
    checkAnswer(
      sql("select APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME"),
      Seq(Row("QQ_Media", 1, 5700), Row("DNS", 5, 12402), Row("QQ_IM", 1, 23865), Row("HTTP", 4, 2896722), Row("XiaYiDao", 1, 2485), Row("HTTP_Browsing", 1, 2381), Row("HTTPS", 1, 73783), Row("", 4, 24684), Row("SSL", 1, 4364)))
  })

  //SmartPCC_Perf_TC_008
  test("select TERMINAL_BRAND,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by TERMINAL_BRAND", NonRunningTests)({
    checkAnswer(
      sql("select TERMINAL_BRAND,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by TERMINAL_BRAND"),
      Seq(Row("OPPO", 1, 928), Row("HTC", 2, 2661), Row("美奇", 1, 2485), Row("NOKIA", 1, 14411), Row("MARCONI", 1, 2874640), Row("SUNUP", 1, 290), Row("TIANYU", 1, 23865), Row("LANBOXING", 1, 4364), Row("BBK", 1, 6936), Row("SECURE", 1, 1185), Row("MOTOROLA", 3, 80137), Row("DAXIAN", 1, 6486), Row("LENOVO", 1, 2346), Row("", 1, 4840), Row("山水", 1, 5700), Row("SANGFEI", 1, 15112)))
  })


  //SmartPCC_Perf_TC_010
  test("select TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by TERMINAL_TYPE", NonRunningTests)({
    checkAnswer(
      sql("select TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by TERMINAL_TYPE"),
      Seq(Row(" ", 2, 2875825), Row("SMARTPHONE", 8, 123420), Row("", 1, 4840), Row("FEATURE PHONE", 8, 42301)))
  })

  //SmartPCC_Perf_TC_011
  test("select TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where RAT_NAME='GERAN' group by TERMINAL_TYPE order by total desc", NonRunningTests)({
    checkAnswer(
      sql("select TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where RAT_NAME='GERAN' group by TERMINAL_TYPE order by total desc"),
      Seq(Row(" ", 2, 2875825), Row("SMARTPHONE", 8, 123420), Row("FEATURE PHONE", 8, 42301), Row("", 1, 4840)))
  })

  //SmartPCC_Perf_TC_012
  test("select CGI,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by CGI", NonRunningTests)({
    checkAnswer(
      sql("select CGI,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by CGI"),
      Seq(Row("460003772902063", 1, 73783), Row("460003788311323", 1, 5700), Row("460003777109392", 1, 6486), Row("460003787211338", 1, 1310), Row("460003776440020", 1, 5044), Row("460003773401611", 1, 2381), Row("460003767804016", 1, 4840), Row("460003784806621", 1, 1185), Row("460003787360026", 1, 14411), Row("460003785041401", 1, 6936), Row("460003766033446", 1, 15112), Row("460003776906411", 1, 4364), Row("460003782800719", 1, 2874640), Row("460003764930757", 1, 928), Row("460003788410098", 1, 23865), Row("460003763202233", 1, 2485), Row("460003763606253", 1, 290), Row("460003788100762", 1, 280), Row("460003784118872", 1, 2346)))
  })

  //SmartPCC_Perf_TC_014
  test("select RAT_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by RAT_NAME", NonRunningTests)({
    checkAnswer(
      sql("select RAT_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by RAT_NAME"),
      Seq(Row("GERAN", 19, 3046386)))
  })

  //SmartPCC_Perf_TC_015
  test("select DAY,HOUR,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by DAY,HOUR", NonRunningTests)({
    checkAnswer(
      sql("select DAY,HOUR,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by DAY,HOUR"),
      Seq(Row("8-1", "23", 19, 3046386)))
  })

  //SmartPCC_Perf_TC_016
  test("select DAY,HOUR,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where hour between 20 and 24 group by DAY,HOUR order by total desc", NonRunningTests)({
    checkAnswer(
      sql("select DAY,HOUR,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where hour between 20 and 24 group by DAY,HOUR order by total desc"),
      Seq(Row("8-1", "23", 19, 3046386)))
  })

  //SmartPCC_Perf_TC_017
  test("select APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME", NonRunningTests)({
    checkAnswer(
      sql("select APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME"),
      Seq(Row("HTTPS", "Web_Browsing", 1, 73783), Row("QQ_IM", "IM", 1, 23865), Row("HTTP_Browsing", "Web_Browsing", 1, 2381), Row("XiaYiDao", "Game", 1, 2485), Row("", "", 4, 24684), Row("SSL", "Tunnelling", 1, 4364), Row("HTTP", "Web_Browsing", 4, 2896722), Row("QQ_Media", "IM", 1, 5700), Row("DNS", "Network_Admin", 5, 12402)))
  })

  //SmartPCC_Perf_TC_018
  test("select APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where  APP_CATEGORY_NAME='Web_Browsing' group by APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME order by total desc", NonRunningTests)({
    checkAnswer(
      sql("select APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where  APP_CATEGORY_NAME='Web_Browsing' group by APP_SUB_CATEGORY_NAME,APP_CATEGORY_NAME order by total desc"),
      Seq(Row("HTTP", "Web_Browsing", 4, 2896722), Row("HTTPS", "Web_Browsing", 1, 73783), Row("HTTP_Browsing", "Web_Browsing", 1, 2381)))
  })

  //SmartPCC_Perf_TC_019
  test("select TERMINAL_BRAND,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME,TERMINAL_BRAND", NonRunningTests)({
    checkAnswer(
      sql("select TERMINAL_BRAND,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by APP_SUB_CATEGORY_NAME,TERMINAL_BRAND"),
      Seq(Row("SECURE", "HTTP", 1, 1185), Row("HTC", "HTTP_Browsing", 1, 2381), Row("TIANYU", "QQ_IM", 1, 23865), Row("DAXIAN", "HTTP", 1, 6486), Row("BBK", "", 1, 6936), Row("山水", "QQ_Media", 1, 5700), Row("LENOVO", "", 1, 2346), Row("LANBOXING", "SSL", 1, 4364), Row("MOTOROLA", "DNS", 2, 6354), Row("MOTOROLA", "HTTPS", 1, 73783), Row("SANGFEI", "", 1, 15112), Row("美奇", "XiaYiDao", 1, 2485), Row("NOKIA", "HTTP", 1, 14411), Row("", "DNS", 1, 4840), Row("MARCONI", "HTTP", 1, 2874640), Row("OPPO", "DNS", 1, 928), Row("HTC", "DNS", 1, 280), Row("SUNUP", "", 1, 290)))
  })

  //SmartPCC_Perf_TC_021
  test("select RAT_NAME,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g   group by APP_SUB_CATEGORY_NAME,RAT_NAME", NonRunningTests)({
    checkAnswer(
      sql("select RAT_NAME,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g   group by APP_SUB_CATEGORY_NAME,RAT_NAME"),
      Seq(Row("GERAN", "QQ_Media", 1, 5700), Row("GERAN", "HTTP", 4, 2896722), Row("GERAN", "XiaYiDao", 1, 2485), Row("GERAN", "", 4, 24684), Row("GERAN", "QQ_IM", 1, 23865), Row("GERAN", "DNS", 5, 12402), Row("GERAN", "HTTPS", 1, 73783), Row("GERAN", "SSL", 1, 4364), Row("GERAN", "HTTP_Browsing", 1, 2381)))
  })

  //SmartPCC_Perf_TC_022
  test("select RAT_NAME,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where  RAT_NAME='GERAN' group by APP_SUB_CATEGORY_NAME,RAT_NAME order by total desc", NonRunningTests)({
    checkAnswer(
      sql("select RAT_NAME,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  where  RAT_NAME='GERAN' group by APP_SUB_CATEGORY_NAME,RAT_NAME order by total desc"),
      Seq(Row("GERAN", "HTTP", 4, 2896722), Row("GERAN", "HTTPS", 1, 73783), Row("GERAN", "", 4, 24684), Row("GERAN", "QQ_IM", 1, 23865), Row("GERAN", "DNS", 5, 12402), Row("GERAN", "QQ_Media", 1, 5700), Row("GERAN", "SSL", 1, 4364), Row("GERAN", "XiaYiDao", 1, 2485), Row("GERAN", "HTTP_Browsing", 1, 2381)))
  })

  //SmartPCC_Perf_TC_023
  test("select TERMINAL_TYPE,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g   group by APP_SUB_CATEGORY_NAME,TERMINAL_TYPE", NonRunningTests)({
    checkAnswer(
      sql("select TERMINAL_TYPE,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g   group by APP_SUB_CATEGORY_NAME,TERMINAL_TYPE"),
      Seq(Row("SMARTPHONE", "", 1, 2346), Row("SMARTPHONE", "QQ_IM", 1, 23865), Row("FEATURE PHONE", "QQ_Media", 1, 5700), Row("SMARTPHONE", "DNS", 3, 6634), Row("FEATURE PHONE", "HTTP", 1, 6486), Row("SMARTPHONE", "HTTPS", 1, 73783), Row("FEATURE PHONE", "XiaYiDao", 1, 2485), Row("SMARTPHONE", "HTTP_Browsing", 1, 2381), Row(" ", "HTTP", 2, 2875825), Row("FEATURE PHONE", "", 3, 22338), Row("", "DNS", 1, 4840), Row("FEATURE PHONE", "DNS", 1, 928), Row("FEATURE PHONE", "SSL", 1, 4364), Row("SMARTPHONE", "HTTP", 1, 14411)))
  })

  //SmartPCC_Perf_TC_025
  test("select HOUR,cgi,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT+down_THROUGHPUT) as total from  traffic_2g_3g_4g  group by HOUR,cgi,APP_SUB_CATEGORY_NAME", NonRunningTests)({
    checkAnswer(
      sql("select HOUR,cgi,APP_SUB_CATEGORY_NAME,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT+down_THROUGHPUT) as total from  traffic_2g_3g_4g  group by HOUR,cgi,APP_SUB_CATEGORY_NAME"),
      Seq(Row("23", "460003788311323", "QQ_Media", 1, 5700), Row("23", "460003763606253", "", 1, 290), Row("23", "460003784806621", "HTTP", 1, 1185), Row("23", "460003776440020", "DNS", 1, 5044), Row("23", "460003772902063", "HTTPS", 1, 73783), Row("23", "460003782800719", "HTTP", 1, 2874640), Row("23", "460003776906411", "SSL", 1, 4364), Row("23", "460003788410098", "QQ_IM", 1, 23865), Row("23", "460003766033446", "", 1, 15112), Row("23", "460003763202233", "XiaYiDao", 1, 2485), Row("23", "460003764930757", "DNS", 1, 928), Row("23", "460003787211338", "DNS", 1, 1310), Row("23", "460003767804016", "DNS", 1, 4840), Row("23", "460003773401611", "HTTP_Browsing", 1, 2381), Row("23", "460003784118872", "", 1, 2346), Row("23", "460003785041401", "", 1, 6936), Row("23", "460003777109392", "HTTP", 1, 6486), Row("23", "460003788100762", "DNS", 1, 280), Row("23", "460003787360026", "HTTP", 1, 14411)))
  })

  //SmartPCC_Perf_TC_026
  test("select RAT_NAME,APP_SUB_CATEGORY_NAME,TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by RAT_NAME,APP_SUB_CATEGORY_NAME,TERMINAL_TYPE", NonRunningTests)({
    checkAnswer(
      sql("select RAT_NAME,APP_SUB_CATEGORY_NAME,TERMINAL_TYPE,count(distinct MSISDN) as msidn_number,sum(UP_THROUGHPUT)+sum(DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  group by RAT_NAME,APP_SUB_CATEGORY_NAME,TERMINAL_TYPE"),
      Seq(Row("GERAN", "SSL", "FEATURE PHONE", 1, 4364), Row("GERAN", "HTTP", "SMARTPHONE", 1, 14411), Row("GERAN", "", "SMARTPHONE", 1, 2346), Row("GERAN", "QQ_IM", "SMARTPHONE", 1, 23865), Row("GERAN", "QQ_Media", "FEATURE PHONE", 1, 5700), Row("GERAN", "DNS", "SMARTPHONE", 3, 6634), Row("GERAN", "HTTPS", "SMARTPHONE", 1, 73783), Row("GERAN", "HTTP", "FEATURE PHONE", 1, 6486), Row("GERAN", "XiaYiDao", "FEATURE PHONE", 1, 2485), Row("GERAN", "HTTP_Browsing", "SMARTPHONE", 1, 2381), Row("GERAN", "HTTP", " ", 2, 2875825), Row("GERAN", "", "FEATURE PHONE", 3, 22338), Row("GERAN", "DNS", "", 1, 4840), Row("GERAN", "DNS", "FEATURE PHONE", 1, 928)))
  })

  //SmartPCC_Perf_TC_027
  test("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN", NonRunningTests)({
    checkAnswer(
      sql("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN"),
      Seq(Row("8613993676885", "HTTPS", 1, 73783), Row("8618394185970", "QQ_IM", 1, 23865), Row("8613993800024", "DNS", 1, 5044), Row("8613893600602", "", 1, 2346), Row("8613919791668", "DNS", 1, 928), Row("8618793100458", "", 1, 15112), Row("8618794812876", "HTTP", 1, 14411), Row("8618700943475", "HTTP", 1, 1185), Row("8613993104233", "DNS", 1, 280), Row("8615120474362", "", 1, 6936), Row("8615209309657", "", 1, 290), Row("8613893462639", "HTTP", 1, 2874640), Row("8615117035070", "DNS", 1, 1310), Row("8613519003078", "XiaYiDao", 1, 2485), Row("8613893853351", "HTTP", 1, 6486), Row("8613649905753", "HTTP_Browsing", 1, 2381), Row("8618794965341", "DNS", 1, 4840), Row("8613993899110", "SSL", 1, 4364), Row("8618393012284", "QQ_Media", 1, 5700)))
  })

  //SmartPCC_Perf_TC_028
  test("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.TERMINAL_BRAND='HTC' and t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN", NonRunningTests)({
    checkAnswer(
      sql("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.TERMINAL_BRAND='HTC' and t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN"),
      Seq(Row("8613993104233", "DNS", 1, 280), Row("8613649905753", "HTTP_Browsing", 1, 2381)))
  })

  //SmartPCC_Perf_TC_029
  test("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.TERMINAL_BRAND='HTC' and t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN order by total desc", NonRunningTests)({
    checkAnswer(
      sql("select t2.MSISDN,t1.APP_SUB_CATEGORY_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.TERMINAL_BRAND='HTC' and t1.MSISDN=t2.MSISDN group by t1.APP_SUB_CATEGORY_NAME,t2.MSISDN order by total desc"),
      Seq(Row("8613649905753", "HTTP_Browsing", 1, 2381), Row("8613993104233", "DNS", 1, 280)))
  })

  //SmartPCC_Perf_TC_030
  test("select t2.MSISDN,t1.RAT_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.MSISDN=t2.MSISDN group by t1.RAT_NAME,t2.MSISDN", NonRunningTests)({
    checkAnswer(
      sql("select t2.MSISDN,t1.RAT_NAME,count(t1.MSISDN) as msidn_number,sum(t1.UP_THROUGHPUT)+sum(t1.DOWN_THROUGHPUT) as total from  traffic_2g_3g_4g  t1, viptable t2 where t1.MSISDN=t2.MSISDN group by t1.RAT_NAME,t2.MSISDN"),
      Seq(Row("8618794965341", "GERAN", 1, 4840), Row("8613993676885", "GERAN", 1, 73783), Row("8613893462639", "GERAN", 1, 2874640), Row("8613993800024", "GERAN", 1, 5044), Row("8618394185970", "GERAN", 1, 23865), Row("8613893853351", "GERAN", 1, 6486), Row("8613919791668", "GERAN", 1, 928), Row("8613993104233", "GERAN", 1, 280), Row("8613893600602", "GERAN", 1, 2346), Row("8618393012284", "GERAN", 1, 5700), Row("8613519003078", "GERAN", 1, 2485), Row("8618793100458", "GERAN", 1, 15112), Row("8615117035070", "GERAN", 1, 1310), Row("8615120474362", "GERAN", 1, 6936), Row("8613649905753", "GERAN", 1, 2381), Row("8615209309657", "GERAN", 1, 290), Row("8613993899110", "GERAN", 1, 4364), Row("8618794812876", "GERAN", 1, 14411), Row("8618700943475", "GERAN", 1, 1185)))
  })

  //SmartPCC_Perf_TC_031
  test("select level, sum(sumUPdown) as total, count(distinct MSISDN) as MSISDN_count from (select MSISDN, t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT as sumUPdown, if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>52428800, '>50M', if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>10485760,'50M~10M',if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>1048576, '10M~1M','<1m'))) as level from  traffic_2g_3g_4g  t1) t2 group by level", NonRunningTests)({
    checkAnswer(
      sql("select level, sum(sumUPdown) as total, count(distinct MSISDN) as MSISDN_count from (select MSISDN, t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT as sumUPdown, if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>52428800, '>50M', if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>10485760,'50M~10M',if((t1.UP_THROUGHPUT+t1.DOWN_THROUGHPUT)>1048576, '10M~1M','<1m'))) as level from  traffic_2g_3g_4g  t1) t2 group by level"),
      Seq(Row("<1m", 171746, 18), Row("10M~1M", 2874640, 1)))
  })

  //SmartPCC_Perf_TC_036
  test("select SOURCE_INFO,APP_CATEGORY_ID,APP_CATEGORY_NAME,AREA_CODE,CITY,UP_THROUGHPUT,DOWN_THROUGHPUT from Traffic_2G_3G_4G where MSISDN='8615209309657' and APP_CATEGORY_ID='-1'", NonRunningTests)({
    checkAnswer(
      sql("select SOURCE_INFO,APP_CATEGORY_ID,APP_CATEGORY_NAME,AREA_CODE,CITY,UP_THROUGHPUT,DOWN_THROUGHPUT from Traffic_2G_3G_4G where MSISDN='8615209309657' and APP_CATEGORY_ID='-1'"),
      Seq(Row("GN", "-1", "", "0930", "临夏", 200, 90)))
  })


  //AllDataTypesTestcase4


  //TC_1063
  test("TC_1063", NonRunningTests) {
    sql("CREATE TABLE table5 (AMSize STRING as col1, Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table table5")
  }

  //TC_1064
  test("TC_1064", NonRunningTests) {
    sql("CREATE TABLE myschema.table6 (AMSize STRING as col1,Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table6")
  }

  //TC_1067
  test("TC_1067", NonRunningTests) {
    sql("CREATE TABLE table9 AMSize STRING as col1, Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table table9")
  }

  //TC_1068
  test("TC_1068", NonRunningTests) {
    sql("CREATE table myschema.table10 (AMSize STRING as col1,Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table10")
  }

  //TC_1071
  test("TC_1071", NonRunningTests) {
    sql("CREATE table myschema.table29 (AMSize STRING as col1) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table29")
  }

  //TC_1072
  test("TC_1072", NonRunningTests) {
    sql("CREATE table table30 (AMSize STRING as col1) stored by 'org.apache.carbondata.format'")
    sql("drop table table30")
  }

  //TC_1078
  test("TC_1078", NonRunningTests) {
    sql("CREATE table table36 (AMSize STRING as col1,deviceInformationId STRING as col2,Latest_Day INT as col3) stored by 'org.apache.carbondata.format'")
    sql("drop table table36")
  }

  //TC_1079
  test("TC_1079", NonRunningTests) {
    sql("CREATE table myschema.table37 (AMSize STRING as col1,deviceInformationId STRING as col2,Latest_Day INT as col3) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table37")
  }

  //TC_1084
  test("TC_1084", NonRunningTests) {
    sql("CREATE table myschema.table42 (bomCode INT as col1,Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table42")
  }

  //TC_1085
  test("TC_1085", NonRunningTests) {
    sql("CREATE table table43 (bomCode INT as col1,Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table table43")
  }

  //TC_1089
  test("TC_1089", NonRunningTests) {
    sql("CREATE table myschema.table47 (AMSize STRING as col1,Latest_Day decimal as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table47")
  }

  //TC_1090
  test("TC_1090", NonRunningTests) {
    sql("CREATE table table48 (AMSize STRING as col1,Latest_Day decimal as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table table48")
  }

  //TC_1094
  test("TC_1094", NonRunningTests) {
    sql("CREATE table myschema.table52 (AMSize decimal as col1,Latest_Day decimal as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table52")
  }

  //TC_1095
  test("TC_1095", NonRunningTests) {
    sql("CREATE table table53 (col1 decimal as col1,Latest_Day decimal as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table table53")
  }

  //DTS2015102804520
  test("DTS2015102804520", NonRunningTests) {
    sql("CREATE table default.table431 (bomCode INT as col1,Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table default.table431")
  }

  //TC_1118
  test("TC_1118", NonRunningTests) {
    sql("CREATE table table5_drop (AMSize STRING as col1,Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table table5_drop")
  }

  //TC_1119
  test("TC_1119", NonRunningTests) {
    sql("CREATE table myschema.table6_drop (AMSize STRING as col1,Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table6_drop")
  }


  //TC_1122
  test("TC_1122", NonRunningTests) {
    sql("CREATE table table9_drop (AMSize STRING as col1,Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table table9_drop")
  }

  //TC_1123
  test("TC_1123", NonRunningTests) {
    sql("CREATE table myschema.table10_drop (AMSize STRING as col1,Latest_Day INT as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table10_drop")
  }


  //TC_1126
  test("TC_1126", NonRunningTests) {
    sql("CREATE table myschema.table29_drop (AMSize STRING as col1) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table29_drop")
  }

  //TC_1127
  test("TC_1127", NonRunningTests) {
    sql("CREATE table table30_drop (AMSize STRING as col1) stored by 'org.apache.carbondata.format'")
    sql("drop table table30_drop")
  }
  //TC_1131
  test("TC_1131", NonRunningTests) {
    sql("CREATE table table36_drop (AMSize STRING as col1,deviceInformationId STRING as col2,Latest_Day INT as col3) stored by 'org.apache.carbondata.format'")
    sql("drop table table36_drop")
  }

  //TC_1132
  test("TC_1132", NonRunningTests) {
    sql("CREATE table myschema.table37_drop (AMSize STRING as col1,deviceInformationId STRING as col2,Latest_Day INT as col3) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table37_drop")
  }

  //TC_1137
  test("TC_1137", NonRunningTests) {
    sql("CREATE table myschema.table47_drop (AMSize STRING as col1,Latest_Day decimal as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table47_drop")
  }

  //TC_1138
  test("TC_1138", NonRunningTests) {
    sql("CREATE table table48_drop (AMSize STRING as col1,Latest_Day decimal as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table table48_drop")
  }

  //TC_1141
  test("TC_1141", NonRunningTests) {
    sql("CREATE table myschema.table52_drop (AMSize decimal as col1,Latest_Day decimal as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table myschema.table52_drop")
  }

  //TC_1142
  test("TC_1142", NonRunningTests) {
    sql("CREATE table table53_drop (col1 decimal as col1,Latest_Day decimal as col2) stored by 'org.apache.carbondata.format'")
    sql("drop table table53_drop")
  }

  //DTS2015112610913_03
  test("DTS2015112610913_03", NonRunningTests) {
    sql("CREATE table table_restructure61 (a0 STRING,b0 INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/restructure_table.csv' INTO table table_restructuRE61 OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '\"',' FILEHEADER'= 'a0,b0')");
    sql("drop table table_restructure61")
  }

  //TC_1156
  test("TC_1156", NonRunningTests) {
    sql("create table test (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test"),
      Seq(Row(100)))
    sql("drop table test")
  }

  //TC_1163
  test("TC_1163") {
    sql("create table thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon OPTIONS('DELIMITER'= ',','QUOTECHAR'=  '\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon"),
      Seq(Row(100)))
    sql("drop table thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon")
  }

  //TC_1166
  test("TC_1166", NonRunningTests) {
    sql("create table test15 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test15 OPTIONS('DELIMITER'= ';',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test15"),
      Seq(Row(0)))
    sql("drop table test15")
  }

  //TC_1167
  test("TC_1167") {
    sql("create table test11 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test11 OPTIONS('DELIMITER'= ',' ,'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test11"),
      Seq(Row(100)))
    sql("drop table test11")
  }

  //TC_1168
  test("TC_1168") {
    sql("create table test2 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test2 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR' ='/', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test2"),
      Seq(Row(100)))
    sql("drop table test2")
  }

  //TC_1170
  test("TC_1170") {
    sql("create table test200 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test200 OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test200"),
      Seq(Row(100)))
    sql("drop table test200")
  }

  //TC_1171
  test("TC_1171") {
    sql("create table test500 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string, productionDate TIMESTAMP,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    CarbonProperties.getInstance()
      .addProperty(CarbonCommonConstants.CARBON_TIMESTAMP_FORMAT, "dd/MM/yyyy HH:mm")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData6.csv' INTO table test500 OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    checkAnswer(
      sql("select count(*) from test500"),
      Seq(Row(100)))
    sql("drop table test500")
    CarbonProperties.getInstance()
      .addProperty(CarbonCommonConstants.CARBON_TIMESTAMP_FORMAT,
        CarbonCommonConstants.CARBON_TIMESTAMP_DEFAULT_FORMAT
      )
  }

  //TC_1173
  test("TC_1173") {
    sql("create table test9 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test9 OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '/',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test9"),
      Seq(Row(100)))
    sql("drop table test9")
  }

  //TC_1174
  test("TC_1174") {
    sql("create table test16 (AMSize STRING,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test16 OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test16"),
      Seq(Row(100)))
    sql("drop table test16")
  }

  //TC_1175
  test("TC_1175") {
    sql("create schema IF NOT EXISTS myschema1")
    sql("create table myschema1.test17 (AMSize STRING,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table myschema1.test17 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*)  from myschema1.test17"),
      Seq(Row(100)))
    sql("drop table myschema1.test17")
    //sql("drop schema myschema1")
  }

  //TC_1176
  test("TC_1176", NonRunningTests) {
    sql("create table test (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test"),
      Seq(Row(100)))
    sql("drop table test")
  }

  //TC_1183
  test("TC_1183") {
    sql("create table thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon"),
      Seq(Row(100)))
    sql("drop table thebigdealisadealofdealneverdealadealtodealdealaphonetodealofdealkingisakingofkingdon")
  }

  //TC_1186
  test("TC_1186", NonRunningTests) {
    sql("create table test15 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test15 OPTIONS('DELIMITER'= ';',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test15"),
      Seq(Row(0)))
    sql("drop table test15")
  }

  //TC_1187
  test("TC_1187") {
    sql("create table test11 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test11 OPTIONS('DELIMITER'= ',' ,'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test11"),
      Seq(Row(100)))
    sql("drop table test11")
  }

  //TC_1188
  test("TC_1188") {
    sql("create table test2 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test2 OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '/',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test2"),
      Seq(Row(100)))
    sql("drop table test2")
  }

  //TC_1190
  test("TC_1190") {
    sql("create table test200 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test200 OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test200"),
      Seq(Row(100)))
    sql("drop table test200")
  }

  //TC_1193
  test("TC_1193") {
    sql("create table test9 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test9 OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '/',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test9"),
      Seq(Row(100)))
    sql("drop table test9")
  }

  //TC_1194
  test("TC_1194") {
    sql("create table test16 (AMSize STRING,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table test16 OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*) from test16"),
      Seq(Row(100)))
    sql("drop table test16")
  }

  //TC_1195
  test("TC_1195") {
    sql("create schema IF NOT EXISTS myschema")
    sql("create table myschema.test17 (AMSize STRING,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table myschema.test17 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(*)  from myschema.test17"),
      Seq(Row(100)))
    sql("drop table myschema.test17")
  }

  //DTS2015111808892
  test("DTS2015111808892") {
    sql("CREATE table table_restructure (a0 STRING,b0 INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/restructure_table.csv' INTO table table_restructure OPTIONS('DELIMITER'= ',', 'QUOTECHAR'=  '\"')")
    checkAnswer(
      sql("select count(*)  from table_restructure"),
      Seq(Row(100)))
    sql("drop table table_restructure")
  }

  //DTS2015111809054
  test("DTS2015111809054", NonRunningTests) {
    sql("create table tableDTS2015111809054 (key string,name string,gamepointid decimal,price decimal) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO table tableDTS2015111809054 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"', 'FILEHEADER'= '')")
    checkAnswer(
      sql("select count(*)  from tableDTS2015111809054"),
      Seq(Row(21)))
    sql("drop table tableDTS2015111809054")
  }

  //DTS2015112006803_01
  test("DTS2015112006803_01") {
    sql("CREATE table incloading_DTS2015112006803_01 (a0 STRING,b0 INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/restructure_table.csv' INTO table incloading_DTS2015112006803_01 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'=  '\"')")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/restructure_table.csv' INTO table incloading_DTS2015112006803_01 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'=  '\"')")
    checkAnswer(
      sql("select count(*) from incloading_DTS2015112006803_01"),
      Seq(Row(200)))
    sql("drop table incloading_DTS2015112006803_01")
  }


  //DTS2015112710336
  test("DTS2015112710336", NonRunningTests) {
    sql("create table rock (key string as col1,name string as col3,gamepointid decimal,price decimal) with dimFile RELATION (FACT.deviceid=col1) INCLUDE ( col1,col3)")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO table rock OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"\"', 'FILEHEADER'= '')")
    checkAnswer(
      sql("select count(*)  from rock"),
      Seq(Row(21)))
    sql("drop table rock")
  }

  //DTS2015111810813
  test("DTS2015111810813", NonRunningTests) {
    sql("create table single (imei string,deviceInformationId INT,mac string,productdate timestamp,updatetime timestamp,gamePointId decimal,contractNumber decimal) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/vmallFact_headr.csv' INTO table single OPTIONS('DELIMITER'= '\001', 'QUOTECHAR'= '\"', 'FILEHEADER'= 'imei,deviceInformationId,mac,productdate,updatetime,gamePointId,contractNumber')")
    checkAnswer(
      sql("select count(*) from single"),
      Seq(Row(10)))
    sql("drop table single")
  }

  //DTS2015101504861
  test("DTS2015101504861", NonRunningTests) {
    sql("create table test1970 (imei string,productionDate timestamp,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData2.csv' INTO table test1970 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"', 'FILEHEADER'= 'imei,productionDate,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select imei from test1970 where productionDate='2015-07-06 12:07:00'"),
      Seq())
    sql("drop table test1970")
  }

  //DTS2015101209623
  test("DTS2015101209623") {
    sql("create table test1971 (imei string,productionDate timestamp,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData2.csv' INTO table test1971 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"', 'FILEHEADER'= 'imei,productionDate,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select imei from test1971 WHERE gamepointId is NULL"),
      Seq())
    sql("drop table test1971")
  }

  //TC_1326
  test("TC_1326", NonRunningTests) {
    sql("create table testincomp (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData4.csv' INTO table testincomp OPTIONS('DELIMITER'= ',',' QUOTECHAR'=  '\"',' FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select channelsId from testincomp order by imei ASC limit 0"),
      Seq())
    sql("drop table testincomp")
  }

  //TC_1327
  test("TC_1327", NonRunningTests) {
    sql("create table test01 (key string,name string,gamepointid decimal,price decimal) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,name)")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO table test01 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"', 'FILEHEADER'= '')")
    checkAnswer(
      sql("select count(*) from test01"),
      Seq(Row(21)))
    sql("drop table test01")
  }

  //TC_1328
  test("TC_1328", NonRunningTests) {
    sql("create table test01 (key string,name string as col1,gamepointid decimal,price decimal) with dimFile RELATION (FACT.deviceid=key) INCLUDE ( key,col1)")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/100_default_date_11_Withheaders.csv' DIMENSION FROM dimFile:'./src/test/resources/dimFile.csv' INTO table test01 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"', 'FILEHEADER'= '')")
    checkAnswer(
      sql("select count(*) from test01"),
      Seq(Row(21)))
    sql("drop table test01")
  }



  //DTS2015120304016
  test("DTS2015120304016") {
    sql("CREATE table incloading1 (a0 STRING,b0 INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/restructure_table.csv' INTO table incloading1 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'=  '\"\"')")
    sql("drop table incloading1")
    sql("CREATE table incloading1 (a0 STRING,b0 INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/restructure_table.csv' INTO table incloading1 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'=  '\"\"')")
    checkAnswer(
      sql("select count(*) from incloading1"),
      Seq(Row(100)))
    sql("drop table incloading1")
  }

  //TC_1252
  test("TC_1252") {
    sql("create table table7 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    checkAnswer(
      sql("show segments for table table7"),
      Seq())
    sql("drop table table7")
  }

  //TC_1253
  test("TC_1253") {
    sql("create table table123 (imei string,deviceInformationId INT,MAC string,deviceColor string, device_backColor string,modelId string, marketName string, AMSize string, ROMSize string, CUPAudit string, CPIClocked string, series string, productionDate string, bomCode string, internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince  string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict  string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR  INT, Latest_MONTH INT, Latest_DAY INT, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string,gamePointId decimal,contractNumber decimal) stored by 'org.apache.carbondata.format'")
    checkAnswer(
      sql("show segments for table table123"),
      Seq())
    sql("drop table table123")
  }

  //TC_1254
  test("TC_1254", NonRunningTests) {
    sql("create table table9 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table9 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table9 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("show segments for table table9"),
      Seq(Row("0","Success","2015-11-05 17:43:21.0"," 2015-11-05 17:43:22.0"),Row("1","Success","2015-11-05 17:43:43.0"," 2015-11-05 17:43:44.0")))
    sql("drop table table9")
  }

  //TC_1257
  test("TC_1257") {
    sql("create table table12 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    checkAnswer(
      sql("show segments for table table12"),
      Seq())
    sql("drop table table12")
  }

  //TC_1258
  test("TC_1258", NonRunningTests) {
    sql("create table table13 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table13 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("sHOw segMents for table table13"),
      Seq(Row("0","Success","2015-11-05 18:09:40.0"," 2015-11-05 18:09:41.0")))
    sql("drop table table13")
  }

  //DTS2015112006803_02
  test("DTS2015112006803_02", NonRunningTests) {
    sql("create table table14 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table14 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("select * from table14")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table14 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("select * from table14")
    checkAnswer(
      sql("show segments for table table14"),
      Seq(Row("1","Success","2015-11-05 17:43:21.0"," 2015-11-05 17:43:22.0"),Row("0","Success","2015-11-05 17:43:43.0"," 2015-11-05 17:43:44.0")))
    sql("drop table table14")
  }

  //DTS2015110901347
  test("DTS2015110901347", NonRunningTests) {
    sql("create table table15 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table15 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table15 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table15 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table15 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("show segments for table table15"),
      Seq(Row("3","Success","2015-11-05 17:43:21.0"," 2015-11-05 17:43:22.0"),Row("2","Success","2015-11-05 17:43:21.0"," 2015-11-05 17:43:22.0"),Row("1","Success","2015-11-05 17:43:21.0"," 2015-11-05 17:43:22.0"),Row("0","Success","2015-11-05 17:43:43.0"," 2015-11-05 17:43:44.0")))
    sql("drop table table15")
  }

  //DTS2015121707872
  test("DTS2015121707872", NonRunningTests) {
    sql("create table t202 (imei string,deviceInformationId INT,mac string,productdate timestamp,updatetime timestamp,gamePointId decimal,contractNumber decimal) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/test1t.csv' INTO table t202 OPTIONS ('DELIMITER'= ',', 'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,mac,productdate,updatetime,gamePointId,contractNumber')")
    checkAnswer(
      sql("show segments for table t202"),
      Seq())
    sql("drop table t202")
  }


  //DTS2015102211549
  test("DTS2015102211549") {
    sql("create table table23 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table23 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select imei,max(gamePointId) FROM table23 where imei=\"1AA10006\" group by imei"),
      Seq(Row("1AA10006",2478.0)))
    sql("drop table table23")
  }

  //DTS2015102309588_01
  test("DTS2015102309588_01") {
    sql("create table table24 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table24 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select imei,sum(distinct gamePointId) FROM table24 where imei=\"1AA10006\" group by imei limit 1"),
      Seq(Row("1AA10006",2478.0)))
    sql("drop table table24")
  }

  //DTS2015102309588_02
  test("DTS2015102309588_02") {
    sql("create table table25 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table25 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    checkAnswer(
      sql("select count(imei),count(distinct gamePointId) FROM table25 group by imei limit 1"),
      Seq(Row(1,1)))
    sql("drop table table25")
  }


    //TC_1291
  test("TC_1291", NonRunningTests) {
    sql("create table table1 (imei string,deviceInformationId INT,MAC string,deviceColor string, device_backColor string,modelId string, marketName string, AMSize string, ROMSize string, CUPAudit string, CPIClocked string, series string, productionDate string, bomCode string, internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince  string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict  string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR  INT, Latest_MONTH INT, Latest_DAY INT, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string,gamePointId decimal,contractNumber decimal) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/100.csv' INTO table table1 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'=  '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("delete load 0 from  table table1")
    checkAnswer(
      sql("select imei from table1"),
      Seq())
    sql("drop table table1")
  }

  //TC_1293
  test("TC_1293", NonRunningTests) {
    sql("create table table3 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table3 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from  table table3")
    checkAnswer(
      sql("select gamePointId from table3"),
      Seq())
    sql("drop table table3")
  }

  //TC_1294
  test("TC_1294", NonRunningTests) {
    sql("create table myschema1.table4 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table myschema1.table4 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from  table myschema1.table4")
    checkAnswer(
      sql("select channelsId from myschema1.table4"),
      Seq())
    sql("drop table myschema1.table4")
  }

  //TC_1295
  test("TC_1295", NonRunningTests) {
    sql("create table table5 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table5 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from table table5")
    checkAnswer(
      sql("select AMSize from table5"),
      Seq())
    sql("drop table table5")
  }

  //TC_1296
  test("TC_1296", NonRunningTests) {
    sql("create table table6 (AMSize STRING,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table6 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from  table table6")
    checkAnswer(
      sql("select deviceInformationId from table6"),
      Seq())
    sql("drop table table6")
  }

  //TC_1297
  test("TC_1297", NonRunningTests) {
    sql("create table table7 (AMSize STRING,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("delete load 0 from table table7")
    checkAnswer(
      sql("select AMSize from table7"),
      Seq())
    sql("drop table table7")
  }

  //TC_1302
  test("TC_1302", NonRunningTests) {
    sql("create table table12 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table12 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table12 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0,1 from  table table12")
    checkAnswer(
      sql("select gamePointId from table12 limit 1"),
      Seq())
    sql("drop table table12")
  }

  //TC_1303
  test("TC_1303", NonRunningTests) {
    sql("create table table13 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table13 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table13 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from  table table13")
    checkAnswer(
      sql("select imei from table13 limit 1"),
      Seq(Row("1AA1")))
    sql("drop table table13")
  }

  //TC_1306
  test("TC_1306") {
    sql("create table table16 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table16 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table16 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0 from  table table16")
    sql("delete load 1 from  table table16")
    checkAnswer(
      sql("select gamePointId from table16 limit 1"),
      Seq())
    sql("drop table table16")
  }

  //TC_1308
  test("TC_1308", NonRunningTests) {
    sql("create table table18 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table18 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("delete load 0,1 from  table table18")
    checkAnswer(
      sql("select ActiveCountry from table18 limit 1"),
      Seq())
    sql("drop table table18")
  }

  //TC_1309
  test("TC_1309") {
    sql("create table table19 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH  './src/test/resources/TestData1.csv' INTO table table19 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId')")
    sql("DELETE LOAD 0 FROM  table table19")
    checkAnswer(
      sql("select deviceInformationId from table19"),
      Seq())
    sql("drop table table19")
  }

  //TC_1311
  test("TC_1311", NonRunningTests) {
    sql("create table testretention (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete load 0 from table testretention")
    checkAnswer(
      sql("select imei,deviceInformationId from testretention"),
      Seq())
    sql("drop table testretention")
  }

  //TC_1312
  test("TC_1312", NonRunningTests) {
    sql("create table testretention1 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention1 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention1 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete load 0 from table testretention1")
    checkAnswer(
      sql("select imei,deviceInformationId from testretention1  where imei='1AA1'"),
      Seq(Row("1AA1",1.0)))
    sql("drop table testretention1")
  }

  //TC_1314
  test("TC_1314", NonRunningTests) {
    sql("create table testretention3 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention3 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention3 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete load 0 from table testretention3")
    checkAnswer(
      sql("select imei,AMSize from testretention3 where gamePointId=1407"),
      Seq(Row("1AA100051" , "3RAM size"),Row("1AA100061" , "0RAM size")))
    sql("drop table testretention3")
  }
  //TC_1316
  test("TC_1316", NonRunningTests) {
    sql("create table testretention5 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention5 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention5 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete load 0,1 from table testretention5")
    checkAnswer(
      sql("select imei,deviceInformationId from testretention5"),
      Seq())
    sql("drop table testretention5")
  }

  //TC_1318
  test("TC_1318", NonRunningTests) {
    sql("create table testretention6 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention6 OPTIONS('DELIMITER'= ',', 'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete from table testretention6 where productionDate before '2015-07-05 12:07:28'")
    checkAnswer(
      sql("select count(*)  from testretention6"),
      Seq(Row(54)))
    sql("drop table testretention6")
  }

  //TC_1322
  test("TC_1322", NonRunningTests) {
    sql("create table testretention9 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention9 OPTIONS('DELIMITER'= ',','QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete from table testretention9 where productionDate before '2015-10-06 12:07:28 '")
    checkAnswer(
      sql("select count(*) from testretention9"),
      Seq(Row(3)))
    sql("drop table testretention9")
  }

  //DTS2015110209900
  test("DTS2015110209900", NonRunningTests) {
    sql("create table table_restructure63  (a0 STRING,b0 INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/restructure_table.csv' INTO table table_restructure63 OPTIONS('DELIMITER'= ',','QUOTECHAR'=  '\"\"', 'FILEHEADER'= 'a0,b0')")
    sql("delete load 0 from table table_RESTRUCTURE63")
    checkAnswer(
      sql("select * from table_restructure63 limit 1"),
      Seq())
    sql("drop table table_restructure63")
  }


  //DTS2015110209543
  test("DTS2015110209543", NonRunningTests) {
    sql("create table testretention13 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention13 OPTIONS('DELIMITER'= ',','QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete from table testretention13 where productionDate before '2015-09-08 12:07:28'")
    sql("delete from table testretention13 where productionDate before '2000-09-08 12:07:28'")
    checkAnswer(
      sql("select count(*) from testretention13"),
      Seq(Row(31)))
    sql("drop table testretention13")
  }

  //DTS2015101506341
  test("DTS2015101506341", NonRunningTests) {
    sql("create table testretention13 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table testretention13 OPTIONS('DELIMITER'= ',','QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate') \"")
    sql("delete from table testretention13 where productiondate before '2015-09-08 12:07:28'")
    checkAnswer(
      sql("select count(*) from testretention13"),
      Seq())
    sql("drop table testretention13")
  }

  //DTS2015112611263
  test("DTS2015112611263", NonRunningTests) {
    sql("create table makamraghutest002 (imei string,deviceInformationId INT,MAC string,deviceColor string,device_backColor string,modelId string,marketName string,AMSize string,ROMSize string,CUPAudit string,CPIClocked string,series string,productionDate timestamp,bomCode string,internalModels string, deliveryTime string, channelsId string, channelsName string , deliveryAreaId string, deliveryCountry string, deliveryProvince string, deliveryCity string,deliveryDistrict string, deliveryStreet string, oxSingleNumber string, ActiveCheckTime string, ActiveAreaId string, ActiveCountry string, ActiveProvince string, Activecity string, ActiveDistrict string, ActiveStreet string, ActiveOperatorId string, Active_releaseId string, Active_EMUIVersion string, Active_operaSysVersion string, Active_BacVerNumber string, Active_BacFlashVer string, Active_webUIVersion string, Active_webUITypeCarrVer string,Active_webTypeDataVerNumber string, Active_operatorsVersion string, Active_phonePADPartitionedVersions string, Latest_YEAR INT, Latest_MONTH INT, Latest_DAY INT, Latest_HOUR string, Latest_areaId string, Latest_country string, Latest_province string, Latest_city string, Latest_district string, Latest_street string, Latest_releaseId string, Latest_EMUIVersion string, Latest_operaSysVersion string, Latest_BacVerNumber string, Latest_BacFlashVer string, Latest_webUIVersion string, Latest_webUITypeCarrVer string, Latest_webTypeDataVerNumber string, Latest_operatorsVersion string, Latest_phonePADPartitionedVersions string, Latest_operatorId string, gamePointDescription string,gamePointId decimal,contractNumber decimal) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/100.csv' INTO table makamraghutest002 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/100.csv' INTO table makamraghutest002 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/100.csv' INTO table makamraghutest002 OPTIONS('DELIMITER'= ',' ,'QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,MAC,deviceColor,device_backColor,modelId,marketName,AMSize,ROMSize,CUPAudit,CPIClocked,series,productionDate,bomCode,internalModels,deliveryTime,channelsId,channelsName,deliveryAreaId,deliveryCountry,deliveryProvince,deliveryCity,deliveryDistrict,deliveryStreet,oxSingleNumber,contractNumber,ActiveCheckTime,ActiveAreaId,ActiveCountry,ActiveProvince,Activecity,ActiveDistrict,ActiveStreet,ActiveOperatorId,Active_releaseId,Active_EMUIVersion,Active_operaSysVersion,Active_BacVerNumber,Active_BacFlashVer,Active_webUIVersion,Active_webUITypeCarrVer,Active_webTypeDataVerNumber,Active_operatorsVersion,Active_phonePADPartitionedVersions,Latest_YEAR,Latest_MONTH,Latest_DAY,Latest_HOUR,Latest_areaId,Latest_country,Latest_province,Latest_city,Latest_district,Latest_street,Latest_releaseId,Latest_EMUIVersion,Latest_operaSysVersion,Latest_BacVerNumber,Latest_BacFlashVer,Latest_webUIVersion,Latest_webUITypeCarrVer,Latest_webTypeDataVerNumber,Latest_operatorsVersion,Latest_phonePADPartitionedVersions,Latest_operatorId,gamePointId,gamePointDescription')")
    sql("delete load 0 from table makamraghutest002")
    checkAnswer(
      sql("select count(*) from makamraghutest002 where series='8Series'"),
      Seq(Row(11)))
    sql("drop table makamraghutest002")
  }

  //TC_1339
  test("TC_1339", NonRunningTests) {
    sql("create table test323 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table test323 OPTIONS('DELIMITER'= ',','QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete from table test323  where productiondate before '2015-08-07 12:07:28'")
    checkAnswer(
      sql("select productiondate from test323 order by imei ASC limit 3"),
      Seq(Row(null,"2015-08-07 12:07:28.0","2015-08-08 12:07:28.0")))
    sql("drop table test323")
  }

  //TC_1340
  test("TC_1340", NonRunningTests) {
    sql("create table test60 (imei string,AMSize string,channelsId string,ActiveCountry string, Activecity string,productionDate timestamp,gamePointId decimal,deviceInformationId INT) stored by 'org.apache.carbondata.format'")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table test60 OPTIONS('DELIMITER'= ',','QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/TestData3.csv' INTO table test60 OPTIONS('DELIMITER'= ',','QUOTECHAR'= '\"\"', 'FILEHEADER'= 'imei,deviceInformationId,AMSize,channelsId,ActiveCountry,Activecity,gamePointId,productionDate')")
    sql("delete load 0 from table test60")
    sql("delete from table test60 where productionDate before '2015-07-25 12:07:28'")
    checkAnswer(
      sql("select count(*)from test60"),
      Seq(Row(76)))
    sql("drop table test60")
  }



  //TestDataTypes5

  //TC_503
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou")))
  })

  //TC_504
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(760.0,"7RAM size","Chinese","yichang"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_505
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(760.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_506
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(1098.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan")))
  })

  //TC_507
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize <= \"5RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize <= \"5RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2194.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(1778.0,"0RAM size","Chinese","changsha"),Row(1098.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan")))
  })

  //TC_509
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize, gamePointId ,deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({

    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize, gamePointId ,deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_509.csv")
  })

  //TC_510
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '5RAM %' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '5RAM %' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan")))
  })

  //TC_511
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize BETWEEN \"2RAM size\" AND \"6RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize BETWEEN \"2RAM size\" AND \"6RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou")))
  })

  //TC_512
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1691.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou")))
  })

  //TC_514
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize = \"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize = \"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha"),Row(1098.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(760.0,"7RAM size","Chinese","yichang"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_515
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize > \"6RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize > \"6RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1098.0,"0RAM size","Chinese","changsha"),Row(1778.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou")))
  })

  //TC_516
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize >= \"5RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize >= \"5RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha",19558.0),Row(2194.0,"0RAM size","Chinese","changsha",24134.0),Row(1098.0,"0RAM size","Chinese","changsha",12078.0),Row(2593.0,"0RAM size","Chinese","changsha",28523.0),Row(79.0,"0RAM size","Chinese","guangzhou",869.0),Row(2849.0,"0RAM size","Chinese","shenzhen",31339.0),Row(750.0,"0RAM size","Chinese","wuhan",8250.0),Row(1407.0,"0RAM size","Chinese","wuhan",15477.0),Row(1442.0,"0RAM size","Chinese","wuhan",15862.0),Row(2483.0,"0RAM size","Chinese","wuhan",27313.0),Row(1341.0,"0RAM size","Chinese","zhuzhou",14751.0),Row(1333.0,"1RAM size","Chinese","guangzhou",11997.0),Row(256.0,"1RAM size","Chinese","shenzhen",2304.0),Row(2175.0,"1RAM size","Chinese","xiangtan",19575.0),Row(2734.0,"1RAM size","Chinese","xiangtan",24606.0),Row(202.0,"1RAM size","Chinese","xiangtan",1818.0),Row(2399.0,"1RAM size","Chinese","xiangtan",21591.0),Row(2078.0,"1RAM size","Chinese","yichang",18702.0),Row(1864.0,"1RAM size","Chinese","yichang",16776.0),Row(2745.0,"1RAM size","Chinese","zhuzhou",24705.0),Row(1973.0,"2RAM size","Chinese","changsha",3946.0),Row(1350.0,"2RAM size","Chinese","xiangtan",2700.0),Row(2863.0,"3RAM size","Chinese","changsha",40082.0),Row(1999.0,"3RAM size","Chinese","guangzhou",27986.0),Row(2192.0,"3RAM size","Chinese","shenzhen",30688.0),Row(907.0,"3RAM size","Chinese","shenzhen",12698.0),Row(1053.0,"3RAM size","Chinese","shenzhen",14742.0),Row(2488.0,"3RAM size","Chinese","shenzhen",34832.0),Row(2635.0,"3RAM size","Chinese","wuhan",36890.0),Row(1407.0,"3RAM size","Chinese","xiangtan",19698.0),Row(1337.0,"3RAM size","Chinese","xiangtan",18718.0),Row(1080.0,"3RAM size","Chinese","xiangtan",15120.0),Row(1491.0,"3RAM size","Chinese","yichang",20874.0),Row(1608.0,"3RAM size","Chinese","zhuzhou",22512.0),Row(1655.0,"3RAM size","Chinese","zhuzhou",23170.0),Row(2436.0,"3RAM size","Chinese","zhuzhou",34104.0),Row(1691.0,"4RAM size","Chinese","changsha",37202.0),Row(2288.0,"4RAM size","Chinese","changsha",50336.0),Row(2572.0,"4RAM size","Chinese","changsha",56584.0),Row(813.0,"4RAM size","Chinese","changsha",17886.0),Row(901.0,"4RAM size","Chinese","changsha",19822.0),Row(865.0,"4RAM size","Chinese","changsha",19030.0),Row(1728.0,"4RAM size","Chinese","guangzhou",38016.0),Row(538.0,"4RAM size","Chinese","shenzhen",11836.0),Row(1717.0,"4RAM size","Chinese","shenzhen",37774.0),Row(1077.0,"4RAM size","Chinese","wuhan",23694.0),Row(1714.635,"4RAM size","Chinese","wuhan",37721.96999999999),Row(2553.0,"4RAM size","Chinese","wuhan",56166.0),Row(1600.0,"4RAM size","Chinese","xiangtan",35200.0),Row(412.0,"4RAM size","Chinese","xiangtan",9064.0),Row(2890.0,"4RAM size","Chinese","xiangtan",63580.0),Row(2826.0,"4RAM size","Chinese","xiangtan",62172.0),Row(1991.0,"4RAM size","Chinese","xiangtan",43802.0),Row(1841.0,"4RAM size","Chinese","xiangtan",40502.0),Row(29.0,"4RAM size","Chinese","yichang",638.0),Row(441.0,"4RAM size","Chinese","yichang",9702.0),Row(136.0,"4RAM size","Chinese","yichang",2992.0),Row(732.0,"4RAM size","Chinese","yichang",16104.0)))
  })

  //TC_517
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize < \"4RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize < \"4RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1691.0,"4RAM size","Chinese","changsha",37202.0),Row(901.0,"4RAM size","Chinese","changsha",19822.0),Row(865.0,"4RAM size","Chinese","changsha",19030.0),Row(2572.0,"4RAM size","Chinese","changsha",56584.0),Row(2288.0,"4RAM size","Chinese","changsha",50336.0),Row(813.0,"4RAM size","Chinese","changsha",17886.0),Row(1728.0,"4RAM size","Chinese","guangzhou",38016.0),Row(1717.0,"4RAM size","Chinese","shenzhen",37774.0),Row(538.0,"4RAM size","Chinese","shenzhen",11836.0),Row(1077.0,"4RAM size","Chinese","wuhan",23694.0),Row(2553.0,"4RAM size","Chinese","wuhan",56166.0),Row(1714.635,"4RAM size","Chinese","wuhan",37721.96999999999),Row(1600.0,"4RAM size","Chinese","xiangtan",35200.0),Row(412.0,"4RAM size","Chinese","xiangtan",9064.0),Row(2890.0,"4RAM size","Chinese","xiangtan",63580.0),Row(1991.0,"4RAM size","Chinese","xiangtan",43802.0),Row(1841.0,"4RAM size","Chinese","xiangtan",40502.0),Row(2826.0,"4RAM size","Chinese","xiangtan",62172.0),Row(29.0,"4RAM size","Chinese","yichang",638.0),Row(441.0,"4RAM size","Chinese","yichang",9702.0),Row(136.0,"4RAM size","Chinese","yichang",2992.0),Row(732.0,"4RAM size","Chinese","yichang",16104.0),Row(2077.0,"5RAM size","Chinese","changsha",10385.0),Row(692.0,"5RAM size","Chinese","changsha",3460.0),Row(2507.0,"5RAM size","Chinese","guangzhou",12535.0),Row(2205.0,"5RAM size","Chinese","guangzhou",11025.0),Row(2478.0,"5RAM size","Chinese","wuhan",12390.0),Row(572.0,"6RAM size","Chinese","changsha",5148.0),Row(2061.0,"6RAM size","Chinese","changsha",18549.0),Row(1768.0,"6RAM size","Chinese","guangzhou",15912.0),Row(2142.0,"6RAM size","Chinese","shenzhen",19278.0),Row(1434.0,"6RAM size","Chinese","wuhan",12906.0),Row(1823.0,"6RAM size","Chinese","wuhan",16407.0),Row(568.0,"6RAM size","Chinese","xiangtan",5112.0),Row(298.0,"6RAM size","Chinese","xiangtan",2682.0),Row(2952.0,"6RAM size","Chinese","zhuzhou",26568.0),Row(151.0,"7RAM size","Chinese","changsha",1057.0),Row(1750.0,"7RAM size","Chinese","wuhan",12250.0),Row(1724.0,"7RAM size","Chinese","wuhan",12068.0),Row(505.0,"7RAM size","Chinese","wuhan",3535.0),Row(760.0,"7RAM size","Chinese","yichang",5320.0),Row(1271.0,"7RAM size","Chinese","yichang",8897.0),Row(2239.0,"7RAM size","Chinese","zhuzhou",15673.0),Row(2738.562,"8RAM size","Chinese","guangzhou",27385.619999999995),Row(355.0,"8RAM size","Chinese","shenzhen",3550.0),Row(2970.0,"8RAM size","Chinese","wuhan",29700.0),Row(1873.0,"8RAM size","Chinese","xiangtan",18730.0),Row(1229.0,"8RAM size","Chinese","xiangtan",12290.0),Row(2972.0,"8RAM size","Chinese","yichang",29720.0),Row(2194.0,"8RAM size","Chinese","yichang",21940.0),Row(845.0,"8RAM size","Chinese","zhuzhou",8450.0),Row(613.0,"8RAM size","Chinese","zhuzhou",6130.0),Row(1226.0,"8RAM size","Chinese","zhuzhou",12260.0),Row(2224.0,"9RAM size","Chinese","changsha",22240.0),Row(1015.0,"9RAM size","Chinese","changsha",10150.0),Row(1697.0,"9RAM size","Chinese","shenzhen",16970.0),Row(1368.0,"9RAM size","Chinese","shenzhen",13680.0),Row(1567.0,"9RAM size","Chinese","wuhan",15670.0),Row(448.0,"9RAM size","Chinese","xiangtan",4480.0),Row(2071.0,"9RAM size","Chinese","xiangtan",20710.0),Row(2348.0,"9RAM size","Chinese","xiangtan",23480.0),Row(954.0,"9RAM size","Chinese","xiangtan",9540.0),Row(571.0,"9RAM size","Chinese","yichang",5710.0)))
  })

  //TC_520
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, gamePointId,Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, gamePointId,Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(1973.0,"Chinese","2RAM size","changsha"),Row(1973.0,"Chinese","2RAM size","changsha"),Row(1350.0,"Chinese","2RAM size","xiangtan"),Row(1350.0,"Chinese","2RAM size","xiangtan"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese"
        ,"4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang")))
  })

  //TC_525
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity,gamePointId, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity,gamePointId, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize"),"TC_525.csv")})

  //TC_526
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId ASC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId ASC"),"TC_526.csv")
  })

  //TC_527
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT  AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId DESC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT  AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId DESC"),"TC_527.csv")
  })

  //TC_532
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC 1", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_532.csv")
  })


  //TC_535
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize,deviceInformationId,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId ASC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize,deviceInformationId,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId ASC"),"TC_535.csv")
  })

  //TC_536
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry, deviceInformationId,gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId DESC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry, deviceInformationId,gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId DESC"),"TC_536.csv")
  })

  //TC_541
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_541.csv")
  })

  //TC_544
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.gamePointId DESC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.gamePointId DESC"),"TC_544.csv")
  })

  //TC_545
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, deviceInformationId,gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, deviceInformationId,gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC"),"TC_545.csv")
  })

  //TC_546
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC"),"TC_546.csv")
  })

  //TC_548
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize,gamePointId, ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize,gamePointId, ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_548.csv")
  })


  //TC_551
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId, First(Carbon_automation_test5.deviceInformationId) AS First_deviceInformationId FROM ( SELECT AMSize,deviceInformationId, gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId, First(Carbon_automation_test5.deviceInformationId) AS First_deviceInformationId FROM ( SELECT AMSize,deviceInformationId, gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row("0RAM size","Chinese","changsha",84293.0,100079),Row("0RAM size","Chinese","guangzhou",869.0,100010),Row("0RAM size","Chinese","shenzhen",31339.0,100028),Row("0RAM size","Chinese","wuhan",66902.0,100008),Row("0RAM size","Chinese","zhuzhou",14751.0,100002),Row("1RAM size","Chinese","guangzhou",11997.0,100030),Row("1RAM size","Chinese","shenzhen",2304.0,100020),Row("1RAM size","Chinese","xiangtan",67590.0,10000),Row("1RAM size","Chinese","yichang",35478.0,100040),Row("1RAM size","Chinese","zhuzhou",24705.0,100042),Row("2RAM size","Chinese","changsha",3946.0,100071),Row("2RAM size","Chinese","xiangtan",2700.0,10007),Row("3RAM size","Chinese","changsha",40082.0,100015),Row("3RAM size","Chinese","guangzhou",27986.0,100022),Row("3RAM size","Chinese","shenzhen",92960.0,100073),Row("3RAM size","Chinese","wuhan",36890.0,100058),Row("3RAM size","Chinese","xiangtan",53536.0,100031),Row("3RAM size","Chinese","yichang",20874.0,100069),Row("3RAM size","Chinese","zhuzhou",79786.0,100027),Row("4RAM size","Chinese","changsha",200860.0,100057),Row("4RAM size","Chinese","guangzhou",38016.0,100055),Row("4RAM size","Chinese","shenzhen",49610.0,100060),Row("4RAM size","Chinese","wuhan",117581.96999999999,100045),Row("4RAM size","Chinese","xiangtan",254320.0,100049),Row("4RAM size","Chinese","yichang",29436.0,100000),Row("5RAM size","Chinese","changsha",13845.0,100077),Row("5RAM size","Chinese","guangzhou",23560.0,100075),Row("5RAM size","Chinese","wuhan",12390.0,10006),Row("6RAM size","Chinese","changsha",23697.0,100034),Row("6RAM size","Chinese","guangzhou",15912.0,100026),Row("6RAM size","Chinese","shenzhen",19278.0,100035),Row("6RAM size","Chinese","wuhan",29313.0,100047),Row("6RAM size","Chinese","xiangtan",7794.0,10001),Row("6RAM size","Chinese","zhuzhou",26568.0,100062),Row("7RAM size","Chinese","changsha",1057.0,100014),Row("7RAM size","Chinese","wuhan",27853.0,100001),Row("7RAM size","Chinese","yichang",14217.0,100),Row("7RAM size","Chinese","zhuzhou",15673.0,100003),Row("8RAM size","Chinese","guangzhou",27385.619999999995,1),Row("8RAM size","Chinese","shenzhen",3550.0,100013),Row("8RAM size","Chinese","wuhan",29700.0,100004),Row("8RAM size","Chinese","xiangtan",31020.0,100016),Row("8RAM size","Chinese","yichang",51660.0,10002),Row("8RAM size","Chinese","zhuzhou",26840.0,100052),Row("9RAM size","Chinese","changsha",32390.0,100036),Row("9RAM size","Chinese","shenzhen",30650.0,100044),Row("9RAM size","Chinese","wuhan",15670.0,100070),Row("9RAM size","Chinese","xiangtan",58210.0,100072),Row("9RAM size","Chinese","yichang",5710.0,100043)))
  })

  //TC_552
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.deviceInformationId) AS Sum_deviceInformationId, First(Carbon_automation_test5.gamePointId) AS First_gamePointId FROM ( SELECT AMSize,gamePointId ,deviceInformationId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.deviceInformationId) AS Sum_deviceInformationId, First(Carbon_automation_test5.gamePointId) AS First_gamePointId FROM ( SELECT AMSize,gamePointId ,deviceInformationId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 INNER JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row("0RAM size","Chinese","changsha",4401364,2593.0),Row("0RAM size","Chinese","guangzhou",1100110,79.0),Row("0RAM size","Chinese","shenzhen",1100308,2849.0),Row("0RAM size","Chinese","wuhan",4401639,1442.0),Row("0RAM size","Chinese","zhuzhou",1100022,1341.0),Row("1RAM size","Chinese","guangzhou",900270,1333.0),Row("1RAM size","Chinese","shenzhen",900180,256.0),Row("1RAM size","Chinese","xiangtan",2790900,2175.0),Row("1RAM size","Chinese","yichang",1800954,2078.0),Row("1RAM size","Chinese","zhuzhou",900378,2745.0),Row("2RAM size","Chinese","changsha",200142,1973.0),Row("2RAM size","Chinese","xiangtan",20014,1350.0),Row("3RAM size","Chinese","changsha",1400210,2863.0),Row("3RAM size","Chinese","guangzhou",1400308,1999.0),Row("3RAM size","Chinese","shenzhen",5603668,1053.0),Row("3RAM size","Chinese","wuhan",1400812,2635.0),Row("3RAM size","Chinese","xiangtan",4201974,1080.0),Row("3RAM size","Chinese","yichang",1400966,1491.0),Row("3RAM size","Chinese","zhuzhou",2941190,2436.0),Row("4RAM size","Chinese","changsha",11225038,2572.0),Row("4RAM size","Chinese","guangzhou",2201210,1728.0),Row("4RAM size","Chinese","shenzhen",2421408,1717.0),Row("4RAM size","Chinese","wuhan",4402222,2553.0),Row("4RAM size","Chinese","xiangtan",33004774,1600.0),Row("4RAM size","Chinese","yichang",8803168,29.0),Row("5RAM size","Chinese","changsha",505385,2077.0),Row("5RAM size","Chinese","guangzhou",1000460,2507.0),Row("5RAM size","Chinese","wuhan",50030,2478.0),Row("6RAM size","Chinese","changsha",1800909,2061.0),Row("6RAM size","Chinese","guangzhou",900234,1768.0),Row("6RAM size","Chinese","shenzhen",900315,2142.0),Row("6RAM size","Chinese","wuhan",1801125,1823.0),Row("6RAM size","Chinese","xiangtan",990117,298.0),Row("6RAM size","Chinese","zhuzhou",900558,2952.0),Row("7RAM size","Chinese","changsha",700098,151.0),Row("7RAM size","Chinese","wuhan",2100455,505.0),Row("7RAM size","Chinese","yichang",700931,1271.0),Row("7RAM size","Chinese","zhuzhou",700021,2239.0),Row("8RAM size","Chinese","guangzhou",10,2738.562),Row("8RAM size","Chinese","shenzhen",1000130,355.0),Row("8RAM size","Chinese","wuhan",1000040,2970.0),Row("8RAM size","Chinese","xiangtan",2000540,1873.0),Row("8RAM size","Chinese","yichang",1100250,2972.0),Row("8RAM size","Chinese","zhuzhou",3001960,845.0),Row("9RAM size","Chinese","changsha",2000730,2224.0),Row("9RAM size","Chinese","shenzhen",2000980,1697.0),Row("9RAM size","Chinese","wuhan",1000700,1567.0),Row("9RAM size","Chinese","xiangtan",3102370,2071.0),Row("9RAM size","Chinese","yichang",1000430,571.0)))
  })

  //TC_553
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou")))
  })

  //TC_554
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(760.0,"7RAM size","Chinese","yichang"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_555
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(760.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_556
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1098.0,"0RAM size","Chinese","changsha"),Row(1778.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan")))
  })

  //TC_557
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize <= \"5RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize <= \"5RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(1098.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan")))
  })

  //TC_558
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '%1%' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5. gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '%1%' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5. gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou")))
  })

  //TC_559
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize, gamePointId ,deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize, gamePointId ,deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_559.csv")
  })

  //TC_560
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '5RAM %' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize LIKE '5RAM %' GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan")))
  })

  //TC_561
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize BETWEEN \"2RAM size\" AND \"6RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize BETWEEN \"2RAM size\" AND \"6RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(813.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou")))
  })

  //TC_562
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize IN (\"4RAM size\",\"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2288.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou")))
  })

  //TC_564
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize = \"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize = \"8RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1098.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(1778.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(572.0,"6RAM size","Chinese","changsha"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(760.0,"7RAM size","Chinese","yichang"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_565
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize > \"6RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize > \"6RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(1098.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou")))
  })

  //TC_566
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize >= \"5RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize >= \"5RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1778.0,"0RAM size","Chinese","changsha",19558.0),Row(1098.0,"0RAM size","Chinese","changsha",12078.0),Row(2194.0,"0RAM size","Chinese","changsha",24134.0),Row(2593.0,"0RAM size","Chinese","changsha",28523.0),Row(79.0,"0RAM size","Chinese","guangzhou",869.0),Row(2849.0,"0RAM size","Chinese","shenzhen",31339.0),Row(1442.0,"0RAM size","Chinese","wuhan",15862.0),Row(1407.0,"0RAM size","Chinese","wuhan",15477.0),Row(2483.0,"0RAM size","Chinese","wuhan",27313.0),Row(750.0,"0RAM size","Chinese","wuhan",8250.0),Row(1341.0,"0RAM size","Chinese","zhuzhou",14751.0),Row(1333.0,"1RAM size","Chinese","guangzhou",11997.0),Row(256.0,"1RAM size","Chinese","shenzhen",2304.0),Row(2175.0,"1RAM size","Chinese","xiangtan",19575.0),Row(202.0,"1RAM size","Chinese","xiangtan",1818.0),Row(2399.0,"1RAM size","Chinese","xiangtan",21591.0),Row(2734.0,"1RAM size","Chinese","xiangtan",24606.0),Row(2078.0,"1RAM size","Chinese","yichang",18702.0),Row(1864.0,"1RAM size","Chinese","yichang",16776.0),Row(2745.0,"1RAM size","Chinese","zhuzhou",24705.0),Row(1973.0,"2RAM size","Chinese","changsha",3946.0),Row(1350.0,"2RAM size","Chinese","xiangtan",2700.0),Row(2863.0,"3RAM size","Chinese","changsha",40082.0),Row(1999.0,"3RAM size","Chinese","guangzhou",27986.0),Row(907.0,"3RAM size","Chinese","shenzhen",12698.0),Row(1053.0,"3RAM size","Chinese","shenzhen",14742.0),Row(2488.0,"3RAM size","Chinese","shenzhen",34832.0),Row(2192.0,"3RAM size","Chinese","shenzhen",30688.0),Row(2635.0,"3RAM size","Chinese","wuhan",36890.0),Row(1337.0,"3RAM size","Chinese","xiangtan",18718.0),Row(1407.0,"3RAM size","Chinese","xiangtan",19698.0),Row(1080.0,"3RAM size","Chinese","xiangtan",15120.0),Row(1491.0,"3RAM size","Chinese","yichang",20874.0),Row(2436.0,"3RAM size","Chinese","zhuzhou",34104.0),Row(1655.0,"3RAM size","Chinese","zhuzhou",23170.0),Row(1608.0,"3RAM size","Chinese","zhuzhou",22512.0),Row(1691.0,"4RAM size","Chinese","changsha",37202.0),Row(813.0,"4RAM size","Chinese","changsha",17886.0),Row(901.0,"4RAM size","Chinese","changsha",19822.0),Row(865.0,"4RAM size","Chinese","changsha",19030.0),Row(2288.0,"4RAM size","Chinese","changsha",50336.0),Row(2572.0,"4RAM size","Chinese","changsha",56584.0),Row(1728.0,"4RAM size","Chinese","guangzhou",38016.0),Row(538.0,"4RAM size","Chinese","shenzhen",11836.0),Row(1717.0,"4RAM size","Chinese","shenzhen",37774.0),Row(1077.0,"4RAM size","Chinese","wuhan",23694.0),Row(1714.635,"4RAM size","Chinese","wuhan",37721.96999999999),Row(2553.0,"4RAM size","Chinese","wuhan",56166.0),Row(1991.0,"4RAM size","Chinese","xiangtan",43802.0),Row(1841.0,"4RAM size","Chinese","xiangtan",40502.0),Row(1600.0,"4RAM size","Chinese","xiangtan",35200.0),Row(412.0,"4RAM size","Chinese","xiangtan",9064.0),Row(2890.0,"4RAM size","Chinese","xiangtan",63580.0),Row(2826.0,"4RAM size","Chinese","xiangtan",62172.0),Row(441.0,"4RAM size","Chinese","yichang",9702.0),Row(136.0,"4RAM size","Chinese","yichang",2992.0),Row(29.0,"4RAM size","Chinese","yichang",638.0),Row(732.0,"4RAM size","Chinese","yichang",16104.0)))
  })

  //TC_567
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize < \"4RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity , SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId FROM ( SELECT AMSize, ActiveCountry, Activecity,gamePointId FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE NOT(Carbon_automation_test5.AMSize < \"4RAM size\") GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(2288.0,"4RAM size","Chinese","changsha",50336.0),Row(2572.0,"4RAM size","Chinese","changsha",56584.0),Row(813.0,"4RAM size","Chinese","changsha",17886.0),Row(1691.0,"4RAM size","Chinese","changsha",37202.0),Row(901.0,"4RAM size","Chinese","changsha",19822.0),Row(865.0,"4RAM size","Chinese","changsha",19030.0),Row(1728.0,"4RAM size","Chinese","guangzhou",38016.0),Row(538.0,"4RAM size","Chinese","shenzhen",11836.0),Row(1717.0,"4RAM size","Chinese","shenzhen",37774.0),Row(1077.0,"4RAM size","Chinese","wuhan",23694.0),Row(1714.635,"4RAM size","Chinese","wuhan",37721.96999999999),Row(2553.0,"4RAM size","Chinese","wuhan",56166.0),Row(1991.0,"4RAM size","Chinese","xiangtan",43802.0),Row(1841.0,"4RAM size","Chinese","xiangtan",40502.0),Row(2826.0,"4RAM size","Chinese","xiangtan",62172.0),Row(1600.0,"4RAM size","Chinese","xiangtan",35200.0),Row(412.0,"4RAM size","Chinese","xiangtan",9064.0),Row(2890.0,"4RAM size","Chinese","xiangtan",63580.0),Row(29.0,"4RAM size","Chinese","yichang",638.0),Row(732.0,"4RAM size","Chinese","yichang",16104.0),Row(441.0,"4RAM size","Chinese","yichang",9702.0),Row(136.0,"4RAM size","Chinese","yichang",2992.0),Row(692.0,"5RAM size","Chinese","changsha",3460.0),Row(2077.0,"5RAM size","Chinese","changsha",10385.0),Row(2507.0,"5RAM size","Chinese","guangzhou",12535.0),Row(2205.0,"5RAM size","Chinese","guangzhou",11025.0),Row(2478.0,"5RAM size","Chinese","wuhan",12390.0),Row(572.0,"6RAM size","Chinese","changsha",5148.0),Row(2061.0,"6RAM size","Chinese","changsha",18549.0),Row(1768.0,"6RAM size","Chinese","guangzhou",15912.0),Row(2142.0,"6RAM size","Chinese","shenzhen",19278.0),Row(1434.0,"6RAM size","Chinese","wuhan",12906.0),Row(1823.0,"6RAM size","Chinese","wuhan",16407.0),Row(298.0,"6RAM size","Chinese","xiangtan",2682.0),Row(568.0,"6RAM size","Chinese","xiangtan",5112.0),Row(2952.0,"6RAM size","Chinese","zhuzhou",26568.0),Row(151.0,"7RAM size","Chinese","changsha",1057.0),Row(505.0,"7RAM size","Chinese","wuhan",3535.0),Row(1750.0,"7RAM size","Chinese","wuhan",12250.0),Row(1724.0,"7RAM size","Chinese","wuhan",12068.0),Row(760.0,"7RAM size","Chinese","yichang",5320.0),Row(1271.0,"7RAM size","Chinese","yichang",8897.0),Row(2239.0,"7RAM size","Chinese","zhuzhou",15673.0),Row(2738.562,"8RAM size","Chinese","guangzhou",27385.619999999995),Row(355.0,"8RAM size","Chinese","shenzhen",3550.0),Row(2970.0,"8RAM size","Chinese","wuhan",29700.0),Row(1873.0,"8RAM size","Chinese","xiangtan",18730.0),Row(1229.0,"8RAM size","Chinese","xiangtan",12290.0),Row(2194.0,"8RAM size","Chinese","yichang",21940.0),Row(2972.0,"8RAM size","Chinese","yichang",29720.0),Row(1226.0,"8RAM size","Chinese","zhuzhou",12260.0),Row(845.0,"8RAM size","Chinese","zhuzhou",8450.0),Row(613.0,"8RAM size","Chinese","zhuzhou",6130.0),Row(2224.0,"9RAM size","Chinese","changsha",22240.0),Row(1015.0,"9RAM size","Chinese","changsha",10150.0),Row(1368.0,"9RAM size","Chinese","shenzhen",13680.0),Row(1697.0,"9RAM size","Chinese","shenzhen",16970.0),Row(1567.0,"9RAM size","Chinese","wuhan",15670.0),Row(448.0,"9RAM size","Chinese","xiangtan",4480.0),Row(2071.0,"9RAM size","Chinese","xiangtan",20710.0),Row(2348.0,"9RAM size","Chinese","xiangtan",23480.0),Row(954.0,"9RAM size","Chinese","xiangtan",9540.0),Row(571.0,"9RAM size","Chinese","yichang",5710.0)))
  })


  //TC_570
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, gamePointId,Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, gamePointId,Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(1098.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2593.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(2194.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(1778.0,"Chinese","0RAM size","changsha"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(79.0,"Chinese","0RAM size","guangzhou"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(2849.0,"Chinese","0RAM size","shenzhen"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(750.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1407.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(1442.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(2483.0,"Chinese","0RAM size","wuhan"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1341.0,"Chinese","0RAM size","zhuzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(1333.0,"Chinese","1RAM size","guangzhou"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(256.0,"Chinese","1RAM size","shenzhen"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(2175.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(202.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2734.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2399.0,"Chinese","1RAM size","xiangtan"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(2078.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(1864.0,"Chinese","1RAM size","yichang"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(2745.0,"Chinese","1RAM size","zhuzhou"),Row(1973.0,"Chinese","2RAM size","changsha"),Row(1973.0,"Chinese","2RAM size","changsha"),Row(1350.0,"Chinese","2RAM size","xiangtan"),Row(1350.0,"Chinese","2RAM size","xiangtan"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(2863.0,"Chinese","3RAM size","changsha"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(1999.0,"Chinese","3RAM size","guangzhou"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(2488.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(907.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(2192.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(1053.0,"Chinese","3RAM size","shenzhen"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(2635.0,"Chinese","3RAM size","wuhan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1080.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1407.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1337.0,"Chinese","3RAM size","xiangtan"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1491.0,"Chinese","3RAM size","yichang"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1608.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(1655.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2436.0,"Chinese","3RAM size","zhuzhou"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(2288.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(865.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(901.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(813.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(2572.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1691.0,"Chinese","4RAM size","changsha"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1728.0,"Chinese","4RAM size","guangzhou"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(1717.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(538.0,"Chinese","4RAM size","shenzhen"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(2553.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1077.0,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(1714.635,"Chinese","4RAM size","wuhan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(2890.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(412.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(2826.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),
        Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1600.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1991.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(1841.0,"Chinese","4RAM size","xiangtan"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(136.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(441.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(29.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(732.0,"Chinese","4RAM size","yichang"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(692.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2077.0,"Chinese","5RAM size","changsha"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2205.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2507.0,"Chinese","5RAM size","guangzhou"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2478.0,"Chinese","5RAM size","wuhan"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(2061.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(572.0,"Chinese","6RAM size","changsha"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(1768.0,"Chinese","6RAM size","guangzhou"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(2142.0,"Chinese","6RAM size","shenzhen"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1823.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(1434.0,"Chinese","6RAM size","wuhan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(298.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(568.0,"Chinese","6RAM size","xiangtan"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(2952.0,"Chinese","6RAM size","zhuzhou"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(151.0,"Chinese","7RAM size","changsha"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(505.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1724.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1750.0,"Chinese","7RAM size","wuhan"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(1271.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(760.0,"Chinese","7RAM size","yichang"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2239.0,"Chinese","7RAM size","zhuzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(2738.562,"Chinese","8RAM size","guangzhou"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(355.0,"Chinese","8RAM size","shenzhen"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(2970.0,"Chinese","8RAM size","wuhan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1873.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(1229.0,"Chinese","8RAM size","xiangtan"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2972.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(2194.0,"Chinese","8RAM size","yichang"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(845.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(1226.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(613.0,"Chinese","8RAM size","zhuzhou"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(2224.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1015.0,"Chinese","9RAM size","changsha"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1697.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1368.0,"Chinese","9RAM size","shenzhen"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(1567.0,"Chinese","9RAM size","wuhan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(2071.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(448.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(954.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(2348.0,"Chinese","9RAM size","xiangtan"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang"),Row(571.0,"Chinese","9RAM size","yichang")))
  })


  //TC_575
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity,gamePointId, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity,gamePointId, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize"),"TC_575.csv")
  })

  //TC_576
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId ASC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId ASC"),"TC_576.csv")
  })

  //TC_577
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT  AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId DESC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.deviceInformationId AS deviceInformationId FROM ( SELECT  AMSize, gamePointId,ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.deviceInformationId DESC"),"TC_577.csv")
  })

  //TC_582
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC1", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_582.csv")
  })

  //TC_585
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize,deviceInformationId,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId ASC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize,deviceInformationId,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId ASC"),"TC_585.csv")
  })

  //TC_586
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry, deviceInformationId,gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId DESC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry, deviceInformationId,gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.gamePointId DESC"),"TC_586.csv")
  })


  //TC_591
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, deviceInformationId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_591.csv")
  })


  //TC_594
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.gamePointId DESC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.gamePointId DESC"),"TC_594.csv")
  })

  //TC_595
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, deviceInformationId,gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, deviceInformationId,gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC"),"TC_595.csv")
  })

  //TC_596
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.gamePointId AS gamePointId FROM ( SELECT AMSize, ActiveCountry,deviceInformationId, gamePointId,Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.AMSize DESC, Carbon_automation_test5.gamePointId DESC"),"TC_596.csv")
  })


  //TC_598
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize,gamePointId, ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    validateResult(sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.deviceInformationId AS deviceInformationId, Carbon_automation_test5.Activecity AS Activecity, Carbon_automation_test5.AMSize AS AMSize FROM ( SELECT AMSize,gamePointId, ActiveCountry,deviceInformationId, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 LEFT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize ORDER BY Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),"TC_598.csv")
  })

  //TC_601
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId, First(Carbon_automation_test5.deviceInformationId) AS First_deviceInformationId FROM ( SELECT AMSize,deviceInformationId, gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 Left join ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.gamePointId) AS Sum_gamePointId, First(Carbon_automation_test5.deviceInformationId) AS First_deviceInformationId FROM ( SELECT AMSize,deviceInformationId, gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 Left join ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row("0RAM size","Chinese","changsha",84293.0,100005),Row("0RAM size","Chinese","guangzhou",869.0,100010),Row("0RAM size","Chinese","shenzhen",31339.0,100028),Row("0RAM size","Chinese","wuhan",66902.0,100056),Row("0RAM size","Chinese","zhuzhou",14751.0,100002),Row("1RAM size","Chinese","guangzhou",11997.0,100030),Row("1RAM size","Chinese","shenzhen",2304.0,100020),Row("1RAM size","Chinese","xiangtan",67590.0,100041),Row("1RAM size","Chinese","yichang",35478.0,100040),Row("1RAM size","Chinese","zhuzhou",24705.0,100042),Row("2RAM size","Chinese","changsha",3946.0,100071),Row("2RAM size","Chinese","xiangtan",2700.0,10007),Row("3RAM size","Chinese","changsha",40082.0,100015),Row("3RAM size","Chinese","guangzhou",27986.0,100022),Row("3RAM size","Chinese","shenzhen",92960.0,100073),Row("3RAM size","Chinese","wuhan",36890.0,100058),Row("3RAM size","Chinese","xiangtan",53536.0,100051),Row("3RAM size","Chinese","yichang",20874.0,100069),Row("3RAM size","Chinese","zhuzhou",79786.0,100027),Row("4RAM size","Chinese","changsha",200860.0,100006),Row("4RAM size","Chinese","guangzhou",38016.0,100055),Row("4RAM size","Chinese","shenzhen",49610.0,100060),Row("4RAM size","Chinese","wuhan",117581.96999999999,100045),Row("4RAM size","Chinese","xiangtan",254320.0,1000000),Row("4RAM size","Chinese","yichang",29436.0,100050),Row("5RAM size","Chinese","changsha",13845.0,1000),Row("5RAM size","Chinese","guangzhou",23560.0,100075),Row("5RAM size","Chinese","wuhan",12390.0,10006),Row("6RAM size","Chinese","changsha",23697.0,100067),Row("6RAM size","Chinese","guangzhou",15912.0,100026),Row("6RAM size","Chinese","shenzhen",19278.0,100035),Row("6RAM size","Chinese","wuhan",29313.0,100047),Row("6RAM size","Chinese","xiangtan",7794.0,10001),Row("6RAM size","Chinese","zhuzhou",26568.0,100062),Row("7RAM size","Chinese","changsha",1057.0,100014),Row("7RAM size","Chinese","wuhan",27853.0,100001),Row("7RAM size","Chinese","yichang",14217.0,100),Row("7RAM size","Chinese","zhuzhou",15673.0,100003),Row("8RAM size","Chinese","guangzhou",27385.619999999995,1),Row("8RAM size","Chinese","shenzhen",3550.0,100013),Row("8RAM size","Chinese","wuhan",29700.0,100004),Row("8RAM size","Chinese","xiangtan",31020.0,100016),Row("8RAM size","Chinese","yichang",51660.0,10002),Row("8RAM size","Chinese","zhuzhou",26840.0,100052),Row("9RAM size","Chinese","changsha",32390.0,100036),Row("9RAM size","Chinese","shenzhen",30650.0,100044),Row("9RAM size","Chinese","wuhan",15670.0,100070),Row("9RAM size","Chinese","xiangtan",58210.0,10003),Row("9RAM size","Chinese","yichang",5710.0,100043)))
  })

  //TC_602
  test("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.deviceInformationId) AS Sum_deviceInformationId, First(Carbon_automation_test5.gamePointId) AS First_gamePointId FROM ( SELECT AMSize,gamePointId ,deviceInformationId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 Left join ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity, SUM(Carbon_automation_test5.deviceInformationId) AS Sum_deviceInformationId, First(Carbon_automation_test5.gamePointId) AS First_gamePointId FROM ( SELECT AMSize,gamePointId ,deviceInformationId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 Left join ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row("0RAM size","Chinese","changsha",4401364,1098.0),Row("0RAM size","Chinese","guangzhou",1100110,79.0),Row("0RAM size","Chinese","shenzhen",1100308,2849.0),Row("0RAM size","Chinese","wuhan",4401639,750.0),Row("0RAM size","Chinese","zhuzhou",1100022,1341.0),Row("1RAM size","Chinese","guangzhou",900270,1333.0),Row("1RAM size","Chinese","shenzhen",900180,256.0),Row("1RAM size","Chinese","xiangtan",2790900,2734.0),Row("1RAM size","Chinese","yichang",1800954,2078.0),Row("1RAM size","Chinese","zhuzhou",900378,2745.0),Row("2RAM size","Chinese","changsha",200142,1973.0),Row("2RAM size","Chinese","xiangtan",20014,1350.0),Row("3RAM size","Chinese","changsha",1400210,2863.0),Row("3RAM size","Chinese","guangzhou",1400308,1999.0),Row("3RAM size","Chinese","shenzhen",5603668,2488.0),Row("3RAM size","Chinese","wuhan",1400812,2635.0),Row("3RAM size","Chinese","xiangtan",4201974,1407.0),Row("3RAM size","Chinese","yichang",1400966,1491.0),Row("3RAM size","Chinese","zhuzhou",2941190,1608.0),Row("4RAM size","Chinese","changsha",11225038,2288.0),Row("4RAM size","Chinese","guangzhou",2201210,1728.0),Row("4RAM size","Chinese","shenzhen",2421408,1717.0),Row("4RAM size","Chinese","wuhan",4402222,1714.635),Row("4RAM size","Chinese","xiangtan",33004774,2890.0),Row("4RAM size","Chinese","yichang",8803168,136.0),Row("5RAM size","Chinese","changsha",505385,2077.0),Row("5RAM size","Chinese","guangzhou",1000460,2205.0),Row("5RAM size","Chinese","wuhan",50030,2478.0),Row("6RAM size","Chinese","changsha",1800909,2061.0),Row("6RAM size","Chinese","guangzhou",900234,1768.0),Row("6RAM size","Chinese","shenzhen",900315,2142.0),Row("6RAM size","Chinese","wuhan",1801125,1823.0),Row("6RAM size","Chinese","xiangtan",990117,298.0),Row("6RAM size","Chinese","zhuzhou",900558,2952.0),Row("7RAM size","Chinese","changsha",700098,151.0),Row("7RAM size","Chinese","wuhan",2100455,505.0),Row("7RAM size","Chinese","yichang",700931,1271.0),Row("7RAM size","Chinese","zhuzhou",700021,2239.0),Row("8RAM size","Chinese","guangzhou",10,2738.562),Row("8RAM size","Chinese","shenzhen",1000130,355.0),Row("8RAM size","Chinese","wuhan",1000040,2970.0),Row("8RAM size","Chinese","xiangtan",2000540,1873.0),Row("8RAM size","Chinese","yichang",1100250,2972.0),Row("8RAM size","Chinese","zhuzhou",3001960,845.0),Row("9RAM size","Chinese","changsha",2000730,2224.0),Row("9RAM size","Chinese","shenzhen",2000980,1697.0),Row("9RAM size","Chinese","wuhan",1000700,1567.0),Row("9RAM size","Chinese","xiangtan",3102370,448.0),Row("9RAM size","Chinese","yichang",1000430,571.0)))
  })

  //TC_603
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize, gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, ActiveDistrict,  AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize = \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId  ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou")))
  })

  //TC_604
  test("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize > \"1RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity,Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(732.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(760.0,"7RAM size","Chinese","yichang"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_605
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,  gamePointId,ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize >= \"2RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry,Carbon_automation_test5.gamePointId, Carbon_automation_test5.Activecity ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan"),Row(2863.0,"3RAM size","Chinese","changsha"),Row(1999.0,"3RAM size","Chinese","guangzhou"),Row(907.0,"3RAM size","Chinese","shenzhen"),Row(2192.0,"3RAM size","Chinese","shenzhen"),Row(1053.0,"3RAM size","Chinese","shenzhen"),Row(2488.0,"3RAM size","Chinese","shenzhen"),Row(2635.0,"3RAM size","Chinese","wuhan"),Row(1080.0,"3RAM size","Chinese","xiangtan"),Row(1337.0,"3RAM size","Chinese","xiangtan"),Row(1407.0,"3RAM size","Chinese","xiangtan"),Row(1491.0,"3RAM size","Chinese","yichang"),Row(1655.0,"3RAM size","Chinese","zhuzhou"),Row(2436.0,"3RAM size","Chinese","zhuzhou"),Row(1608.0,"3RAM size","Chinese","zhuzhou"),Row(1691.0,"4RAM size","Chinese","changsha"),Row(2288.0,"4RAM size","Chinese","changsha"),Row(901.0,"4RAM size","Chinese","changsha"),Row(2572.0,"4RAM size","Chinese","changsha"),Row(813.0,"4RAM size","Chinese","changsha"),Row(865.0,"4RAM size","Chinese","changsha"),Row(1728.0,"4RAM size","Chinese","guangzhou"),Row(538.0,"4RAM size","Chinese","shenzhen"),Row(1717.0,"4RAM size","Chinese","shenzhen"),Row(1714.635,"4RAM size","Chinese","wuhan"),Row(2553.0,"4RAM size","Chinese","wuhan"),Row(1077.0,"4RAM size","Chinese","wuhan"),Row(2890.0,"4RAM size","Chinese","xiangtan"),Row(412.0,"4RAM size","Chinese","xiangtan"),Row(1600.0,"4RAM size","Chinese","xiangtan"),Row(1991.0,"4RAM size","Chinese","xiangtan"),Row(1841.0,"4RAM size","Chinese","xiangtan"),Row(2826.0,"4RAM size","Chinese","xiangtan"),Row(732.0,"4RAM size","Chinese","yichang"),Row(441.0,"4RAM size","Chinese","yichang"),Row(136.0,"4RAM size","Chinese","yichang"),Row(29.0,"4RAM size","Chinese","yichang"),Row(2077.0,"5RAM size","Chinese","changsha"),Row(692.0,"5RAM size","Chinese","changsha"),Row(2205.0,"5RAM size","Chinese","guangzhou"),Row(2507.0,"5RAM size","Chinese","guangzhou"),Row(2478.0,"5RAM size","Chinese","wuhan"),Row(2061.0,"6RAM size","Chinese","changsha"),Row(572.0,"6RAM size","Chinese","changsha"),Row(1768.0,"6RAM size","Chinese","guangzhou"),Row(2142.0,"6RAM size","Chinese","shenzhen"),Row(1434.0,"6RAM size","Chinese","wuhan"),Row(1823.0,"6RAM size","Chinese","wuhan"),Row(568.0,"6RAM size","Chinese","xiangtan"),Row(298.0,"6RAM size","Chinese","xiangtan"),Row(2952.0,"6RAM size","Chinese","zhuzhou"),Row(151.0,"7RAM size","Chinese","changsha"),Row(1750.0,"7RAM size","Chinese","wuhan"),Row(1724.0,"7RAM size","Chinese","wuhan"),Row(505.0,"7RAM size","Chinese","wuhan"),Row(1271.0,"7RAM size","Chinese","yichang"),Row(760.0,"7RAM size","Chinese","yichang"),Row(2239.0,"7RAM size","Chinese","zhuzhou"),Row(2738.562,"8RAM size","Chinese","guangzhou"),Row(355.0,"8RAM size","Chinese","shenzhen"),Row(2970.0,"8RAM size","Chinese","wuhan"),Row(1229.0,"8RAM size","Chinese","xiangtan"),Row(1873.0,"8RAM size","Chinese","xiangtan"),Row(2194.0,"8RAM size","Chinese","yichang"),Row(2972.0,"8RAM size","Chinese","yichang"),Row(613.0,"8RAM size","Chinese","zhuzhou"),Row(845.0,"8RAM size","Chinese","zhuzhou"),Row(1226.0,"8RAM size","Chinese","zhuzhou"),Row(2224.0,"9RAM size","Chinese","changsha"),Row(1015.0,"9RAM size","Chinese","changsha"),Row(1697.0,"9RAM size","Chinese","shenzhen"),Row(1368.0,"9RAM size","Chinese","shenzhen"),Row(1567.0,"9RAM size","Chinese","wuhan"),Row(448.0,"9RAM size","Chinese","xiangtan"),Row(954.0,"9RAM size","Chinese","xiangtan"),Row(2348.0,"9RAM size","Chinese","xiangtan"),Row(2071.0,"9RAM size","Chinese","xiangtan"),Row(571.0,"9RAM size","Chinese","yichang")))
  })

  //TC_606
  test("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC", NonRunningTests) ({
    checkAnswer(
      sql("SELECT  Carbon_automation_test5.gamePointId AS gamePointId,Carbon_automation_test5.AMSize AS AMSize, Carbon_automation_test5.ActiveCountry AS ActiveCountry, Carbon_automation_test5.Activecity AS Activecity FROM ( SELECT AMSize,gamePointId, ActiveCountry, Activecity FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_test5 RIGHT JOIN ( SELECT ActiveCountry, Activecity, AMSize FROM (select * from Carbon_automation_test5) SUB_QRY ) Carbon_automation_vmall_test1 ON Carbon_automation_test5.AMSize = Carbon_automation_vmall_test1.AMSize WHERE Carbon_automation_test5.AMSize < \"3RAM size\" GROUP BY Carbon_automation_test5.AMSize, Carbon_automation_test5.ActiveCountry, Carbon_automation_test5.Activecity, Carbon_automation_test5.gamePointId ORDER BY Carbon_automation_test5.AMSize ASC, Carbon_automation_test5.ActiveCountry ASC, Carbon_automation_test5.Activecity ASC"),
      Seq(Row(1098.0,"0RAM size","Chinese","changsha"),Row(1778.0,"0RAM size","Chinese","changsha"),Row(2194.0,"0RAM size","Chinese","changsha"),Row(2593.0,"0RAM size","Chinese","changsha"),Row(79.0,"0RAM size","Chinese","guangzhou"),Row(2849.0,"0RAM size","Chinese","shenzhen"),Row(1407.0,"0RAM size","Chinese","wuhan"),Row(750.0,"0RAM size","Chinese","wuhan"),Row(1442.0,"0RAM size","Chinese","wuhan"),Row(2483.0,"0RAM size","Chinese","wuhan"),Row(1341.0,"0RAM size","Chinese","zhuzhou"),Row(1333.0,"1RAM size","Chinese","guangzhou"),Row(256.0,"1RAM size","Chinese","shenzhen"),Row(2399.0,"1RAM size","Chinese","xiangtan"),Row(2734.0,"1RAM size","Chinese","xiangtan"),Row(202.0,"1RAM size","Chinese","xiangtan"),Row(2175.0,"1RAM size","Chinese","xiangtan"),Row(2078.0,"1RAM size","Chinese","yichang"),Row(1864.0,"1RAM size","Chinese","yichang"),Row(2745.0,"1RAM size","Chinese","zhuzhou"),Row(1973.0,"2RAM size","Chinese","changsha"),Row(1350.0,"2RAM size","Chinese","xiangtan")))
  })

  //Test-16
  test("select imei, Latest_DAY+ 10 as a  from Carbon_automation_test5", NonRunningTests) {
    validateResult(sql("select imei, Latest_DAY+ 10 as a  from Carbon_automation_test5"),"TC_016.csv");
  }
  //Test-17
  test("select imei, gamePointId+ 10 as Total from Carbon_automation_test5", NonRunningTests) {
    validateResult(sql("select imei, gamePointId+ 10 as Total from Carbon_automation_test5"),"TC_017.csv");
  }
  //Test-18
  test("select imei, modelId+ 10 Total from Carbon_automation_test5 ", NonRunningTests) {
    validateResult(sql("select imei, modelId+ 10 Total from Carbon_automation_test5 "),"TC_018.csv");
  }
  //Test-19

  test("select imei, gamePointId+contractNumber as a  from Carbon_automation_test5 ", NonRunningTests) {
    validateResult(sql("select imei, gamePointId+contractNumber as a  from Carbon_automation_test5 "),"TC_019.csv");
  }

  //Test-20
  test("select imei, deviceInformationId+gamePointId as Total from Carbon_automation_test5", NonRunningTests) {
    validateResult(sql("select imei, deviceInformationId+gamePointId as Total from Carbon_automation_test5"),"TC_020.csv");
  }
  //Test-21
  test("select imei, deviceInformationId+deviceInformationId Total from Carbon_automation_test5", NonRunningTests) {
    validateResult(sql("select imei, deviceInformationId+deviceInformationId Total from Carbon_automation_test5"),"TC_021.csv");
  }

    test("select percentile(deviceInformationId,array(0,0.2,0.3,1))  as  a from Carbon_automation_test5", NonRunningTests)
  {
    checkAnswer(
      sql("select percentile(deviceInformationId,array(0,0.2,0.3,1))  as  a from Carbon_automation_test5"),
      Seq(Row(1.0, 100006.6, 100016.4, 1000000.0)))

    //     validateResult(sql("select percentile(deviceInformationId,array(0,0.2,0.3,1))  as  a from Carbon_automation_test5"),"TC_112.csv");
  }


}