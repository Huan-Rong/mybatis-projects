package site.bulibucai.dao;

import site.bulibucai.bean.Student;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-13
 */
public interface StudentMapper {

  Student getStuById(Integer id);

  void insertStu(Student student);

  Integer updateStu(Student student);

  Integer delStuById(Integer id);

  Integer delStuByGender(String gender);
}
