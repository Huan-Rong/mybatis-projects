package site.bulibucai.dao;

import site.bulibucai.bean.Student;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-11
 */
public interface StudentMapper {

  Student selectById(int id);

}
