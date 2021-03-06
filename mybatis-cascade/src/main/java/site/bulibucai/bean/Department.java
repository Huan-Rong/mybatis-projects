package site.bulibucai.bean;

/**
 * @description:
 * @author: Ian Zheng
 * @date: 2020-02-21
 */
public class Department {

  private Integer id;
  private String deptName;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  @Override
  public String toString() {
    return "Department{" +
        "id=" + id +
        ", deptName='" + deptName + '\'' +
        '}';
  }
}
