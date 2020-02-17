package site.bulibucai.bean;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-13
 */
public class Student {
  private Integer id;
  private String lastName;
  private String gender;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", lastName='" + lastName + '\'' +
        ", gender='" + gender + '\'' +
        '}';
  }
}
