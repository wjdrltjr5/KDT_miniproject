package org.kdt;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
@Slf4j
public class Main {
    public static void main(String[] args) {
        SqlSession session = Config.getConnection();
        List<DeptDto> test = session.selectList("DeptMapper.findAll");
        for(DeptDto d : test){
            System.out.println(d);
        }
        log.info("test");
    }
}