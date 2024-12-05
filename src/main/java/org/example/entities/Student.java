package org.example.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "student_count",
                query = """
                        select s from Student s
                        """
        ),
        @NamedQuery(
                name="count_of_students_departmentWise",
                query = """
                        select new org.example.dto.StudentCountFromEachDepartment(s.department,count(s.id))
                        from Student s
                        group by s.department
                """
        )
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany
    @JoinTable(
            name="student_courses",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    private List<Course> courses;
    private int age;

    private String name;

    private String department;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Course> getCourse() {
        return courses;
    }

    public void setCourse(List<Course> course) {
        this.courses = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
