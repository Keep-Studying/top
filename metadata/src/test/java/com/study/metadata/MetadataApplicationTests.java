package com.study.metadata;

import com.study.metadata.domain.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MetadataApplicationTests {

    @Resource
    private MongoTemplate mongoTemplate;


    @Test
    void addStudent() {
        int a = 1;
        while (a <= 100) {
            Student student = new Student();
            student.setName("name" + a);
            student.setUnicode(UUID.randomUUID().toString());
            Student insert = mongoTemplate.insert(student);
            System.out.println(insert);
            a++;
        }
    }

    @Test
    void findByName() {
        Query query = new Query(Criteria.where("name").is("name1"));
        List<Student> students = mongoTemplate.find(query, Student.class);
        System.out.println(students);
    }
}
