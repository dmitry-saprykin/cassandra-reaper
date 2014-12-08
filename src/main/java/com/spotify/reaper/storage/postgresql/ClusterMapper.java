/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.spotify.reaper.storage.postgresql;

import com.google.common.collect.Sets;

import com.spotify.reaper.core.Cluster;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ClusterMapper implements ResultSetMapper<Cluster> {

  public Cluster map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    String[] seedHosts = (String[]) r.getArray("seed_hosts").getArray();
    return new Cluster.Builder(r.getString("name"), r.getString("partitioner"),
                               Sets.newHashSet(Arrays.asList(seedHosts))).build();
  }

}
