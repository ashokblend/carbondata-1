package org.carbondata.spark.testsuite.joinquery

import org.apache.spark.sql.common.util.CarbonHiveContext._
import org.apache.spark.sql.common.util.QueryTest
import org.scalatest.BeforeAndAfterAll

class EquiJoinTestCase extends QueryTest with BeforeAndAfterAll  {
   override def beforeAll {
    //loading to hive table
    sql("create table employee_hive (empid string,empname string,mobileName string,mobileColor string,salary int)")
    sql("create table mobile_hive (mobileId string,mobileName string, mobileColor string, sales int)");
    sql("LOAD DATA LOCAL INPATH './src/test/resources/join/employee.csv' into table employee_hive")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/join/mobile.csv' into table mobile_hive")
    //loading to carbon table
    sql("create table employee (empid string,empname string,mobileName string,mobileColor string,salary int) stored by 'org.apache.carbondata.format'")
    sql("create table mobile (mobileId string,mobileName string, mobileColor string, sales int) stored by 'org.apache.carbondata.format'");
    sql("LOAD DATA LOCAL INPATH './src/test/resources/join/employee.csv' into table employee")
    sql("LOAD DATA LOCAL INPATH './src/test/resources/join/mobile.csv' into table mobile")
   }
   
   test("test equijoin query") {
        checkAnswer(
      sql("select employee.empname,mobile.mobilename from employee,mobile where employee.mobilename = mobile.mobilename"),
      sql("select employee_hive.empname,mobile_hive.mobilename from employee_hive,mobile_hive where employee_hive.mobilename = mobile_hive.mobilename"))
  }
  override def afterAll {
    sql("drop table employee_hive")
    sql("drop table mobile_hive")
    sql("drop table employee")
    sql("drop table mobile")
  }
}