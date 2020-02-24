package site.bulibucai.dao;

import site.bulibucai.bean.Employee;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-21
 */
public interface EmployeeMapper {

  Employee getEmpByIdReturnEmpAndDept(Integer id);

  Employee getEmpByIdReturnEmpAndDept2(Integer id);

  Employee getEmpByIdReturnEmpAndDept3(Integer id);
}
