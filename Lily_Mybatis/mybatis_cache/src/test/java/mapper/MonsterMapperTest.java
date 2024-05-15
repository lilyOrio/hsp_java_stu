package mapper;

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
}
