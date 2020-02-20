package site.bulibucai.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import site.bulibucai.bean.Student;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-13
 */
public interface StudentMapper {

  List<Student> getStusByLastNameLike(String lastName);

  Student getStuById(@Param("id") Integer id);

  Student getStuByIdAndLastName(@Param("id") Integer id, @Param("lastName") String lastName);

  Student getStuByPojo(Student student);

  Student getStuByMap(Map<String, Object> map);

  void insertStu(Student student);

  Integer updateStu(Student student);

  Integer delStuById(Integer id);

  Integer delStuByGender(String gender);
}
