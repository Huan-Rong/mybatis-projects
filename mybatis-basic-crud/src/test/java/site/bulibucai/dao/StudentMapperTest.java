package site.bulibucai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    String driverName = getDriverName(connection);
    Reader reader = Resources.getResourceAsReader("student_" + driverName + ".sql");
    scriptRunner.runScript(reader);
  }

  public static String getDriverName(Connection connection) throws SQLException {
    String originalDriverName = connection.getMetaData().getDriverName().toUpperCase();
    if (originalDriverName.indexOf("MYSQL") != -1) {
      return "mysql";
    } else if (originalDriverName.indexOf("ORACLE") != -1) {
      return "oracle";
    } else if (originalDriverName.indexOf("SQL SERVER") != -1) {
      return "sqlserver";
    }
    return null;
  }

  @Test
  public void testGetStuByLastNameLikeReturnMap() {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
      Map<Integer, Student> stuByLastNameLikeReturnMap = mapper.getStuByLastNameLikeReturnMap("%a%");
      Assertions.assertEquals(1, stuByLastNameLikeReturnMap.size());
      Assertions.assertEquals(true, stuByLastNameLikeReturnMap.containsKey(1));
      Assertions.assertEquals("nana", stuByLastNameLikeReturnMap.get(1).getLastName());
    }
  }
  
  @Test
  public void testGetStuByIdReturnMap() {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
      Map<String, Object> stuByIdReturnMap = mapper.getStuByIdReturnMap(1);
      Assertions.assertEquals("nana", stuByIdReturnMap.get("lastName"));
      Assertions.assertEquals("1", stuByIdReturnMap.get("gender"));
    }
  }
  
  @Test
  public void testGetStusByLastNameLike() {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
      List<Student> stus = mapper.getStusByLastNameLike("%a%");
      Assertions.assertNotNull(stus);
      Assertions.assertEquals(1, stus.size());
    }
  }

  @Test
  public void testGetStuById() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
      Student student = studentMapper.getStuById(1);
      Assertions.assertNotNull(student);
      Assertions.assertEquals("nana", student.getLastName());
    }
  }

  @Test
  public void testGetStuByIdAndLastName() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession();) {
      StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
      Student student = mapper.getStuByIdAndLastName(1, "nana");
      Assertions.assertNotNull(student);
    }
  }

  @Test
  public void testGetStuByPojo() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
      Student student = new Student();
      student.setId(1);
      student.setLastName("nana");
      Student stu = mapper.getStuByPojo(student);
      Assertions.assertNotNull(stu);
      Assertions.assertNotEquals(student, stu);
    }
  }

  @Test
  public void testGetStuByMap() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
      Map<String, Object> map = new HashMap<>();
      map.put("id", 1);
      map.put("lastName", "nana");
      Student student = mapper.getStuByMap(map);
      Assertions.assertNotNull(student);
    }
  }

  @Test
  public void testInsertStu() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
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
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
      Student student = new Student();
      student.setId(1);
      Integer count = studentMapper.updateStu(student);
      Assertions.assertEquals(1, count);
    }
  }

  @Test
  public void testDelStuById() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
      Integer count = studentMapper.delStuById(1);
      Assertions.assertEquals(1, count);
    }
  }

  @Test
  public void testDelStuByGender() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);
      Integer count = studentMapper.delStuByGender("1");
      Assertions.assertEquals(2, count);
    }
  }
}
