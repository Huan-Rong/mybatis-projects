package site.bulibucai;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-13
 */
public class MyBatisTest {

  @Test
  public void testConnection() throws IOException {
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Assertions.assertNotNull(sqlSession);
    }
  }

}
