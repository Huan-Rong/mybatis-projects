package site.bulibucai.dao;

import site.bulibucai.bean.Department;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-21
 */
public interface DepartmentMapper {

  Department getDeptById(Integer id);
}
