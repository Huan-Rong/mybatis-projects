package site.bulibucai.bean;

import org.apache.ibatis.type.Alias;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-10
 */
@Alias("stu")
public class Student {
  private int id;
  private String lastName;
  private char gender;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", lastName='" + lastName + '\'' +
        ", gender=" + gender +
        '}';
  }
}
