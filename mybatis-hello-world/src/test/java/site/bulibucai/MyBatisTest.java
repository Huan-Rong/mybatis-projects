package site.bulibucai;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import site.bulibucai.bean.Student;
import site.bulibucai.dao.StudentMapper;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-10
 */
public class MyBatisTest {

  @Test
  public void testConnection() throws IOException {
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    Assert.assertNotNull(sqlSessionFactory);

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Student student = sqlSession.selectOne("site.bulibucai.dao.StudentMapper.selectById", 1);
      Assert.assertNotNull(student.getLastName());
      Assert.assertNotNull(student);
    }
  }

  @Test
  public void testConnectionUsingInterface() throws IOException {
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    Assert.assertNotNull(sqlSessionFactory);

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
      Student student = studentMapper.selectById(1);
      Assert.assertNotNull(student.getLastName());
      Assert.assertNotNull(student);
    }
  }

}
