package com.lily_stu.web.datavalid;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Monster {
    private Integer id;

    @NotEmpty(message = "电子邮件不为空~~~~")
    private String email;

    @NotNull(message = "年龄必须填写1-100")
    @Range(min = 1, max = 120)
    private Integer age;

    private String name = "lily";

    @NotNull(message = "生日不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull(message = "薪水不能为空")
    @NumberFormat(pattern = "###,###.##")
    private float salary;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Monster(Integer id, String email, Integer age, String name, Date birthday, float salary) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.name = name;
        this.birthday = birthday;
        this.salary = salary;
    }

    public Monster() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", salary=" + salary +
                '}';
    }
}
