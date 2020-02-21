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
import site.bulibucai.bean.Employee;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-21
 */
public class EmployeeMapperTest {

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
    scriptRunner.setErrorLogWriter(null);
    scriptRunner.setLogWriter(null);

    String dbName = getDbName(connection);
    Reader reader = Resources.getResourceAsReader("employee-" + dbName + ".sql");
    scriptRunner.runScript(reader);
  }

  private static String getDbName(Connection connection) throws SQLException {
    String driverName = connection.getMetaData().getDriverName().toUpperCase();
    if (driverName.indexOf("MYSQL") != -1) {
      return "mysql";
    } else if (driverName.indexOf("ORACLE") != -1) {
      return "oracle";
    } else if (driverName.indexOf("SQL SERVER") != -1 || driverName.indexOf("SQLSERVER") != -1) {
      return "sqlserver";
    }
    return null;
  }

  @Test
  public void testGetEmpByIdReturnEmpAndDept() {
    try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
      EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
      Employee employee = mapper.getEmpByIdReturnEmpAndDept(1);
      Assertions.assertNotNull(employee);
      Assertions.assertEquals("nana", employee.getLastName());
      Assertions.assertNotNull(employee.getDept());
      Assertions.assertEquals(1, employee.getDept().getId());
      Assertions.assertEquals("dev", employee.getDept().getDeptName());
    }
  }
}
