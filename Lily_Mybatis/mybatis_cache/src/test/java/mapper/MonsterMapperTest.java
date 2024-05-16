package mapper;

import com.sun.org.apache.bcel.internal.generic.MULTIANEWARRAY;
import lilystu.entity.Monster;
import lilystu.mapper.MonsterMapper;
import lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class MonsterMapperTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    @Before
    public void init(){
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        System.out.println("MonsterMapper = " + monsterMapper.getClass());
    }
    //测查询单个对象
    @Test
    public void getMonsterById() {
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println(monster);
        if (sqlSession != null) {
            sqlSession.close();
        }
    }
    @Test
    public void findAllMonster() {
        List<Monster> monsterList = monsterMapper.findAllMonster();
        for (Monster monster : monsterList) {
            System.out.println(monster);
        }
        if (sqlSession != null) {
            sqlSession.close();
        }
    }

    @Test
    public void level1CacheTest(){
        Monster monster = monsterMapper.getMonsterById(2);
        System.out.println("--" + monster + "--");
        System.out.println(monster.getClass());
//----------------------------测试一级缓存[一个一个的规则进行测试.]-----------------------
//1. 当我们再次查询id=2 的Monster 时，直接从一级缓存获取,不会再次发出sql
        monster = monsterMapper.getMonsterById(2);
        System.out.println("--" + monster + "--");
        System.out.println(monster.getClass());

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功");
    }

    @Test
    public void level2CacheTest(){
        Monster monster = monsterMapper.getMonsterById(2);
        System.out.println("--" + monster + "--");
        System.out.println(monster.getClass());
        if (sqlSession != null) {
            sqlSession.close();
        }
//        启用全局的二级缓存，再sqlSession关闭之后，去查询同一个id的monster，不会再发出Sql请求
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        monster = monsterMapper.getMonsterById(2);
        System.out.println("--" + monster + "--");
        System.out.println(monster.getClass());

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功");
    }
}
