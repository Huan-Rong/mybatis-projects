package site.bulibucai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import site.bulibucai.bean.Student;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-13
 */
public class StudentMapperTest {
  private static SqlSessionFactory sqlSessionFactory;

  @BeforeAll
  public static void setUp() throws IOException, SQLException {
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
    Connection connection = dataSource.getConnection();
    ScriptRunner scriptRunner = new ScriptRunner(connection);
    scriptRunner.setAutoCommit(true);
    scriptRunner.setStopOnError(false);
    scriptRunner.setLogWriter(null);
    scriptRunner.setErrorLogWriter(null);

    Reader reader = Resources.getResourceAsReader("createDb.sql");
    scriptRunner.runScript(reader);
  }

  @Test
  public void testGetStuById() {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
      Student student = studentMapper.getStuById(1);
      Assertions.assertNotNull(student);
      Assertions.assertEquals("nana", student.getLastName());
    }
  }

  @Test
  public void testInsertStu() {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
      Student student = new Student();
      student.setGender("1");
      student.setLastName("baby");
      Assertions.assertNull(student.getId());
      studentMapper.insertStu(student);
      Assertions.assertNotNull(student.getId());
      Assertions.assertEquals(3, student.getId());
    }
  }

  @Test
  public void testUpdateStu() {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
      Student student = new Student();
      student.setId(1);
      Integer count = studentMapper.updateStu(student);
      Assertions.assertEquals(1, count);
    }
  }
  
  @Test
  public void testDelStuById() {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
      Integer count = studentMapper.delStuById(1);
      Assertions.assertEquals(1, count);
    }
  }

  @Test
  public void testDelStuByGender() {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
      Integer count = studentMapper.delStuByGender("1");
      Assertions.assertEquals(2, count);
    }
  }
}
