package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import org.example.dto.CourseAndEnrolledStudentCount;
import org.example.dto.DepartmentAndCountWithEnrollments;
import org.example.dto.StudentCountFromEachDepartment;
import org.example.dto.StudentsWhichOptedCoursesFromCseDept;
import org.example.entities.Course;
import org.example.entities.Student;
import org.example.persistence.MyOwnPersistenceInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.*;
public class Main {
    public static void main(String[] args) {
        Map<String,String> propertyMap = new HashMap<>();

        //if you wanna see what queries goes to database
        propertyMap.put("hibernate.show_sql","true");



        EntityManagerFactory emf =new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new MyOwnPersistenceInfo(),propertyMap);

        EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            /* - courses and no of enrolled students in that course

            String jpql1 = """
                   select new org.example.dto.CourseAndEnrolledStudentCount(c,count(s.id)) 
                   from Course c join c.students s
                   group by c.id
                    """;

             */

            /* students and course names offered by cse dept
            String jpql1 = """
                        select new org.example.dto.StudentsWhichOptedCoursesFromCseDept(s,c.courseName)
                        from Student s
                        join s.courses c
                        where c.handlingDepartment="cse"
                        order by s.name desc
                        """;

             */

            /* - courses

            String jpql1 = """
                        select new org.example.dto.DepartmentAndCountWithEnrollments(c.handlingDepartment ,count(c) )
                        from Course c
                        group by c.handlingDepartment
                        order by count(c) desc 
                        """;

             */

            /*
            String jpql1= """
                        select s from Student s 
                        join s.courses c 
                        where s.department != c.handlingDepartment
                        """;

             */

            TypedQuery<StudentCountFromEachDepartment> query = em
                    .createNamedQuery("count_of_students_departmentWise", StudentCountFromEachDepartment.class);
            query.getResultList().forEach(o->System.out.println(o.name()+" , "+o.count()));

            em.getTransaction().commit();
        }finally{
            em.close();
        }
    }
}