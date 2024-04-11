package org.kdt;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Config {
    private static SqlSessionFactory sqlSessionFactory;
    static {

        String resource = "config/Configuration.xml";

        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static SqlSession getConnection(){
        return sqlSessionFactory.openSession();
    }

    public static void close(SqlSession session){
        session.close();
    }
}
