/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.general.dataaccess.base;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

/**
 * Type to migrate the database. Gets initialized before hibernate.
 *
 */
public class DatabaseMigrator {

  /** Path of test data location. */
  private static final String testDataPath = "classpath:db/test-data";

  /** Path of master data location. */
  private static final String masterDataPath = "classpath:db/migration";

  /** Property is true if database migration is enabled. */
  private boolean enabled;

  /** The JDBC data source. */
  private DataSource dataSource;

  /** Property is true if test data should be included in migration. */
  private boolean testdata;

  /** Property is true if database should be cleaned before migration. */
  private boolean clean;

  /**
   * Migrate the database to the latest available migration.
   */
  public void migrate() {

    if (this.enabled) {
      final Flyway flyway = new Flyway();
      flyway.setDataSource(this.dataSource);
      if (this.testdata) {
        flyway.setLocations(masterDataPath, testDataPath);
      } else {
        flyway.setLocations(masterDataPath);
      }
      if (this.clean) {
        flyway.clean();
      }
      flyway.migrate();
    }
  }

  /**
   * Import test data.
   *
   * @param importTestDataPath path to directory with files with test data
   */
  public void importTestData(String importTestDataPath) {

    final Flyway flyway = new Flyway();
    flyway.setDataSource(this.dataSource);
    flyway.setLocations(importTestDataPath);
    flyway.migrate();
  }

  /**
   * @return enabled
   */
  public boolean isEnabled() {

    return this.enabled;
  }

  /**
   * @param enabled the enabled to set
   */
  public void setEnabled(boolean enabled) {

    this.enabled = enabled;
  }

  /**
   * @return datasource
   */
  public DataSource getDataSource() {

    return this.dataSource;
  }

  /**
   * @param datasource the datasource to set
   */
  public void setDataSource(DataSource datasource) {

    this.dataSource = datasource;
  }

  /**
   * @param testdata the testdata to set
   */
  public void setTestdata(boolean testdata) {

    this.testdata = testdata;
  }

  /**
   * @param clean the clean to set
   */
  public void setClean(boolean clean) {

    this.clean = clean;
  }

}
