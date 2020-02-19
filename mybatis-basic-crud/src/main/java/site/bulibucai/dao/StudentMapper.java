package site.bulibucai.dao;

import org.apache.ibatis.annotations.Param;
import site.bulibucai.bean.Student;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-13
 */
public interface StudentMapper {

  Student getStuById(@Param("id") Integer id);

  Student getStuByIdAndLastName(Integer id, String lastName);

  void insertStu(Student student);

  Integer updateStu(Student student);

  Integer delStuById(Integer id);

  Integer delStuByGender(String gender);
}
