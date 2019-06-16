/**
 * Copyright © 2018 organization baomidou
 * <pre>
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
 * <pre/>
 */
package org.tang.jsj.ds.provider;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.JdbcUtils;
import org.tang.jsj.ds.DynamicDataSourceCreator;
import org.tang.jsj.ds.spring.boot.autoconfigure.DataSourceProperty;
import org.tang.jsj.ds.spring.boot.autoconfigure.DynamicDataSourceProperties;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * YML和数据库数据源提供者
 *
 * @author TaoYu Kanyuxia
 * @since 1.0.0
 */
@Slf4j
public class YmlDbDynamicDataSourceProvider implements DynamicDataSourceProvider{

    @Setter
    private String driverClassName;
    @Setter
    private String url;
    @Setter
    private String username;
    @Setter
    private String password;

    /**
     * 多数据源参数
     */
    private DynamicDataSourceProperties properties;
    /**
     * 多数据源创建器
     */
    private DynamicDataSourceCreator dynamicDataSourceCreator;


    public YmlDbDynamicDataSourceProvider(String driverClassName, String url, String username, String password) {
        this.driverClassName = driverClassName;
        this.password = password;
        this.url = url;
        this.username = username;
    }

    public YmlDbDynamicDataSourceProvider(DynamicDataSourceProperties properties,DynamicDataSourceCreator dynamicDataSourceCreator) {
        DataSourceProperty dataSourceProperty =  properties.getDatasource().get("master");
        this.properties = properties;
        this.dynamicDataSourceCreator = dynamicDataSourceCreator;
        this.driverClassName =dataSourceProperty.getDriverClassName();
        this.url = dataSourceProperty.getUrl();
        this.username = dataSourceProperty.getUsername();
        this.password = dataSourceProperty.getPassword();

    }




    @Override
    public Map<String, DataSource> loadDataSources() {
        Map<String, DataSource>  dataSourceMap = new HashMap<>();

//        Map<String, DataSource>  conf_dataSourceMap = new HashMap<>();
//
//        Map<String, DataSourceProperty> dataSourcePropertiesMap = properties.getDatasource();
//        for (Map.Entry<String, DataSourceProperty> item : dataSourcePropertiesMap.entrySet()) {
//            String pollName = item.getKey();
//            DataSourceProperty dataSourceProperty = item.getValue();
//            dataSourceProperty.setPollName(pollName);
//            conf_dataSourceMap.put(pollName, dynamicDataSourceCreator.createDataSource(dataSourceProperty));
//        }

        Map<String, DataSource>  db_dataSourceMap = readyLoadDataSources();

//        dataSourceMap.putAll(conf_dataSourceMap);
        dataSourceMap.putAll(db_dataSourceMap);

        return dataSourceMap;
    }



    protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {

        Map<String,DataSourceProperty> map=new HashMap<>();
        DataSourceProperty primaryDataSourceProperty = new DataSourceProperty();
        primaryDataSourceProperty.setDriverClassName(driverClassName);
        primaryDataSourceProperty.setUrl(url);
        primaryDataSourceProperty.setUsername(username);
        primaryDataSourceProperty.setPassword(password);
        primaryDataSourceProperty.setPollName("master");
        map.put("master",primaryDataSourceProperty);


        ResultSet rs =  statement.executeQuery("select org_id , org_no, username,password,url,driveClassName from t_sys_db_info");

        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount=rsmd.getColumnCount();
        List<String> colNameList=new ArrayList<String>();
        for(int i=0;i<colCount;i++){
            colNameList.add(rsmd.getColumnName(i+1));
        }

        while(rs.next()){
            DataSourceProperty dataSourceProperty = new DataSourceProperty();
            String ds_key = "";
            for(int i=0;i<colCount;i++){
                String key=colNameList.get(i);
                if(key.equals("org_id")){
                    String value=rs.getString(colNameList.get(i));
                    dataSourceProperty.setPollName(value);
                    ds_key = value;
                }
                else if(key.equals("username")){
                    String value=rs.getString(colNameList.get(i));
                    dataSourceProperty.setUsername(value);
                }
                else if(key.equals("password")){
                    String value=rs.getString(colNameList.get(i));
                    dataSourceProperty.setPassword(value);
                }
                else if(key.equals("url")){
                    String value=rs.getString(colNameList.get(i));
                    dataSourceProperty.setUrl(value);
                }
                else if(key.equals("driveClassName")){
                    String value=rs.getString(colNameList.get(i));
                    dataSourceProperty.setDriverClassName(value);
                }
            }
            map.put(ds_key, dataSourceProperty);
        }
        return map;
    }


    public Map<String, DataSource> readyLoadDataSources() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(driverClassName);
            log.info("成功加载数据库驱动程序");
            conn = DriverManager.getConnection(url, username, password);
            log.info("成功获取数据库连接");
            stmt = conn.createStatement();
            Map<String, DataSourceProperty> dataSourcePropertiesMap = executeStmt(stmt);
            Map<String, DataSource> dataSourceMap = new HashMap<>(dataSourcePropertiesMap.size());
            for (Map.Entry<String, DataSourceProperty> item : dataSourcePropertiesMap.entrySet()) {
                String pollName = item.getKey();
                DataSourceProperty dataSourceProperty = item.getValue();
                dataSourceProperty.setPollName(pollName);
                dataSourceMap.put(pollName, dynamicDataSourceCreator.createDataSource(dataSourceProperty));
            }
            return dataSourceMap;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.closeConnection(conn);
            JdbcUtils.closeStatement(stmt);
        }
        return null;
    }



}
