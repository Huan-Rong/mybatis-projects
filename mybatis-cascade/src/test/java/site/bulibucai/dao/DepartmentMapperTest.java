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
import site.bulibucai.bean.Department;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-24
 */
public class DepartmentMapperTest {

  private static SqlSessionFactory sqlSessionFactory;

  @BeforeAll
  public static void setUp() throws IOException, SQLException {
    InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    DataSource dataSource = sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();

    Connection connection = dataSource.getConnection();
    ScriptRunner scriptRunner = new ScriptRunner(connection);
    scriptRunner.setLogWriter(null);
    scriptRunner.setErrorLogWriter(null);
    scriptRunner.setAutoCommit(true);
    scriptRunner.setStopOnError(false);
    
    String dbName = getDbName(connection);
    Reader reader = Resources.getResourceAsReader("dept-" + dbName + ".sql");
    scriptRunner.runScript(reader);
  }

  private static String getDbName(Connection connection) throws SQLException {
    String driverName = connection.getMetaData().getDriverName().toUpperCase();
    if (driverName.indexOf("MYSQL") != -1) {
      return "mysql";
    } else if (driverName.indexOf("ORACLE") != -1) {
      return "oracle";
    } else if (driverName.indexOf("SQLSERVER") != -1 || driverName.indexOf("SQL SERVER") != -1) {
      return "sqlserver";
    }
    return null;
  }

  @Test
  public void testGetDeptById() {

    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
      Department dept = mapper.getDeptById(1);
      Assertions.assertNotNull(dept);
      Assertions.assertEquals("dev", dept.getDeptName());
    }
  }
}
