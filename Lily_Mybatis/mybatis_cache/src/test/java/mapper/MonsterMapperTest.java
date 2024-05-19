package mapper;

import com.lilystu.entity.Monster;
import com.lilystu.mapper.MonsterMapper;
import com.lilystu.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MonsterMapperTest {
    //这个是Sql 会话,通过它可以发出sql 语句
    private SqlSession sqlSession;
    private MonsterMapper monsterMapper;

    @Before
    public void init() {
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
    public void level1CacheTest() {
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");
        System.out.println(monster.hashCode());
//----------------------------测试一级缓存[一个一个的规则进行测试.]-----------------------
//1. 当我们再次查询id=2 的Monster 时，直接从一级缓存获取,不会再次发出sql
        monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");
        System.out.println(monster.hashCode());

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功");
    }

    @Test
    public void level2CacheTest() {
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");
        System.out.println(monster.getClass());
        if (sqlSession != null) {
            sqlSession.close();
        }
//        启用全局的二级缓存，再sqlSession关闭之后，去查询同一个id的monster，不会再发出Sql请求
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        monster = monsterMapper.getMonsterById(3);
        System.out.println("--" + monster + "--");
        System.out.println(monster.getClass());

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功");
    }

    //10.4 Mybatis 的一级缓存和二级缓存执行顺序
    @Test
    public void cacheSeqTest() {
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("-1-" + monster + "--");
        System.out.println(monster.getClass());
        System.out.println("-2-" + monster + "--");
        System.out.println(monster.getClass());
//        3. 运行效果, 可以看到，在一级缓存存在的情况下，依然是先查询二级缓存，但是因为
//           二级缓存，没有数据, 所以命中率都是0.0
        if (sqlSession != null) {
            sqlSession.close();
        }
//        1. 不会出现一级缓存和二级缓存中有同一个数据。因为二级缓存(数据)是在一级缓存关闭之后才有的
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        monster = monsterMapper.getMonsterById(3);
        System.out.println("-3-" + monster + "--");
        System.out.println(monster.getClass());
        monster = monsterMapper.getMonsterById(3);
        System.out.println("-4-" + monster + "--");
        System.out.println(monster.getClass());

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功");
    }

    @Test
    public void ehCacheTest() {
        Monster monster = monsterMapper.getMonsterById(3);
        System.out.println("-1-" + monster + "--");
        System.out.println(monster.getClass());
        if (sqlSession != null) {
            sqlSession.close();
        }
//        一级缓存失效
//        启用全局的二级缓存，再sqlSession关闭之后，去查询同一个id的monster，不会再发出Sql请求
        sqlSession = MyBatisUtils.getSqlSession();
        monsterMapper = sqlSession.getMapper(MonsterMapper.class);
        monster = monsterMapper.getMonsterById(3);
        System.out.println("-2-" + monster + "--");
        System.out.println(monster.getClass());

        monster = monsterMapper.getMonsterById(3);
        System.out.println("-3-" + monster + "--");
        System.out.println(monster.getClass());

        if (sqlSession != null) {
            sqlSession.close();
        }
        System.out.println("操作成功");
    }
}
