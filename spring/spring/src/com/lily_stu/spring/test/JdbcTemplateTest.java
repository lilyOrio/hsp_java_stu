package com.lily_stu.spring.test;

import com.lily_stu.spring.bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateTest {

    @Test
    public void testDataSourceByJdbcTemplate() throws SQLException {

        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        DataSource dataSource = ioc.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        System.out.println("connection= " + connection);
        connection.close();
    }

    @Test
    public void addDataByJdbcTemplate() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
//        得到JdbcTemplate bean
        JdbcTemplate bean = ioc.getBean(JdbcTemplate.class);
        // 1. 添加方式1
        // String sql = "INSERT INTO monster VALUES(400, '红孩儿', '枪法厉害')";
        // bean.execute(sql);
        //2. 添加方式2, 绑定参数
        String sql = "INSERT INTO monster VALUES(?, ?, ?)";
        int affected = bean.update(sql, 700, "红孩儿2", "枪法厉害2");
        System.out.println("add ok affected= " + affected);
    }

    @Test
    public void updateDataByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate bean = ioc.getBean(JdbcTemplate.class);
        String sql = "UPDATE monster SET skill = ? WHERE id=?";
        int affected = bean.update(sql, "美女计", 300);
        System.out.println("affected= " + affected);
        System.out.println("update data ok~");
    }

    /**
     * batch add data
     * 批量添加二个monster 白蛇精和青蛇精
     */
    @Test
    public void addBatchDataByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate bean = ioc.getBean(JdbcTemplate.class);//添加..
        String sql = "INSERT INTO monster VALUES(?, ?, ?)";
        List<Object[]> objects = new ArrayList<>();
        objects.add(new Object[]{500, "白蛇精", "吃人"});
        objects.add(new Object[]{600, "青蛇精", "吃小孩"});
        bean.batchUpdate(sql, objects);
    }

    /**
     * 查询id=100 的monster 并封装到Monster 实体对象
     */
    @Test
    public void selectDataByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate bean = ioc.getBean(JdbcTemplate.class);
        String sql = "SELECT id,name,skill FROM monster WHERE id = ?";
        //下面这个rowmapper 是一个接口，可以将查询的结果，封装到你指定的Monster 对象中.
        RowMapper<Monster> rowMapper = new BeanPropertyRowMapper<>(Monster.class);
        Monster monster = bean.queryForObject(sql, rowMapper, 100);
        System.out.println("monster= " + monster);
    }

    /**
     * 查询多条记录
     */
    @Test
    public void selectMulDataByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate bean = ioc.getBean(JdbcTemplate.class);
        String sql = "SELECT id,name,skill FROM monster WHERE id = ?";
        //下面这个rowmapper 是一个接口，可以将查询的结果，封装到你指定的Monster 对象中.
        RowMapper<Monster> rowMapper = new BeanPropertyRowMapper<>(Monster.class);
        List<Monster> monsterList = bean.query(sql, rowMapper, 100);
        for (Monster monster : monsterList) {
            System.out.println("monster= " + monster);
        }
    }

    /**
     * 查询返回结果只有一行一列的值
     */
    @Test
    public void selectScalarByJdbcTemplate() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到JdbcTemplate bean
        JdbcTemplate bean = ioc.getBean(JdbcTemplate.class);
        String sql = "SELECT name FROM monster WHERE id = 100";
        String name = bean.queryForObject(sql, String.class);
        System.out.println(name);
    }

    /**
     * 使用Map 传入具名参数完成操作，比如添加
     */
    @Test
    public void testDataByNamedParameterJdbcTemplate() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到NamedParameterJdbcTemplate bean
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                ioc.getBean(NamedParameterJdbcTemplate.class);
        String sql = "INSERT INTO monster VALUES(:my_id,:name,:skill)";
        Map<String, Object> map_parameter = new HashMap<>();
        map_parameter.put("my_id",800);
        map_parameter.put("name", "螃蟹精");
        map_parameter.put("skill", "钳子无敌大法");
        namedParameterJdbcTemplate.update(sql,map_parameter);
        System.out.println("add data ok~");
    }

    /**
     * 使用SqlParameterSource 传入具名参数完成操作，比如添加
     */
    @Test
    public void operDataBySqlparametersoruce() {
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("JdbcTemplate_ioc.xml");
        //得到NamedParameterJdbcTemplate bean
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                ioc.getBean(NamedParameterJdbcTemplate.class);
        String sql = "INSERT INTO monster VALUES(:monsterId:name,:skill)";
        Monster monster = new Monster(900, "狐狸精", "狐媚之术");
        SqlParameterSource source = new BeanPropertySqlParameterSource(monster);
        namedParameterJdbcTemplate.update(sql, source);
        System.out.println("add ok~");
    }

}
