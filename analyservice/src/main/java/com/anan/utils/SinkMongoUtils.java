package com.anan.utils;

import org.bson.Document;

/**
 * @author wuguozhu
 * @date 2019/9/8 20:23
 */
public class SinkMongoUtils {


    /**
     * key/value 数据类型插入mango
     * @param database 数据库
     * @param tableName 表名
     * @param key
     * @param values
     */
    public static void SinkMongo(String database,
                                 String tableName,
                                 String key,
                                 Long values){
        Document doc = MongoUtils.findByOne(tableName, database, key);
        if (doc == null) {
            doc = new Document();
            doc.put("info", key);
            doc.put("count", values);
        } else {
            Long resultCount = doc.getLong("count");
            Long total = resultCount + values;

            doc.put("count",total);

        }
        MongoUtils.saveOrUpdateMongo(tableName,database,doc);
    }
}
