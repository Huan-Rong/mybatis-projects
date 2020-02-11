package site.bulibucai.bean;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-10
 */
public class Student {
  private int id;
  private String last_name;
  private char gender;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
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
        ", last_name='" + last_name + '\'' +
        ", gender=" + gender +
        '}';
  }
}
