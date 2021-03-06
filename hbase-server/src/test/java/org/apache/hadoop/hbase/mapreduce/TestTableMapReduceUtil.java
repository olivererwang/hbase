/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hbase.mapreduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.LargeTests;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import static org.junit.Assert.*;

/**
 * Test class TableMapReduceUtil
 */

@Category(LargeTests.class)
public class TestTableMapReduceUtil {

  /**
   * Test different variants ofinitTableMapperJob method
   */
  @Test
  public void testInitTableMapperJob() throws Exception {
    Configuration configuration = new Configuration();
    Job job = new Job(configuration, "tableName");
    // test 
    TableMapReduceUtil.initTableMapperJob("Table", new Scan(), Import.Importer.class, Text.class,
        Text.class, job, false, HLogInputFormat.class);
    assertEquals(HLogInputFormat.class, job.getInputFormatClass());
    assertEquals(Import.Importer.class, job.getMapperClass());
    assertEquals(LongWritable.class, job.getOutputKeyClass());
    assertEquals(Text.class, job.getOutputValueClass());
    assertNull(job.getCombinerClass());
    assertEquals("Table", job.getConfiguration().get(TableInputFormat.INPUT_TABLE));

    configuration = new Configuration();
    job = new Job(configuration, "tableName");
    TableMapReduceUtil.initTableMapperJob(Bytes.toBytes("Table"), new Scan(),
        Import.Importer.class, Text.class, Text.class, job, false, HLogInputFormat.class);
    assertEquals(HLogInputFormat.class, job.getInputFormatClass());
    assertEquals(Import.Importer.class, job.getMapperClass());
    assertEquals(LongWritable.class, job.getOutputKeyClass());
    assertEquals(Text.class, job.getOutputValueClass());
    assertNull(job.getCombinerClass());
    assertEquals("Table", job.getConfiguration().get(TableInputFormat.INPUT_TABLE));

    configuration = new Configuration();
    job = new Job(configuration, "tableName");
    TableMapReduceUtil.initTableMapperJob(Bytes.toBytes("Table"), new Scan(),
        Import.Importer.class, Text.class, Text.class, job);
    assertEquals(TableInputFormat.class, job.getInputFormatClass());
    assertEquals(Import.Importer.class, job.getMapperClass());
    assertEquals(LongWritable.class, job.getOutputKeyClass());
    assertEquals(Text.class, job.getOutputValueClass());
    assertNull(job.getCombinerClass());
    assertEquals("Table", job.getConfiguration().get(TableInputFormat.INPUT_TABLE));

    configuration = new Configuration();
    job = new Job(configuration, "tableName");
    TableMapReduceUtil.initTableMapperJob(Bytes.toBytes("Table"), new Scan(),
        Import.Importer.class, Text.class, Text.class, job, false);
    assertEquals(TableInputFormat.class, job.getInputFormatClass());
    assertEquals(Import.Importer.class, job.getMapperClass());
    assertEquals(LongWritable.class, job.getOutputKeyClass());
    assertEquals(Text.class, job.getOutputValueClass());
    assertNull(job.getCombinerClass());
    assertEquals("Table", job.getConfiguration().get(TableInputFormat.INPUT_TABLE));
  }
}
