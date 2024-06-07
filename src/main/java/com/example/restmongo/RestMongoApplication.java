package com.example.restmongo;

import com.example.restmongo.model.Address;
import com.example.restmongo.model.Gender;
import com.example.restmongo.model.Student;
import com.example.restmongo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class RestMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestMongoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			Address address = new Address(
					"Ukraine",
					"Kharkiv",
					"NE9"
			);
			String email = "timchenko.vld@gmail.com";
			Student student = new Student(
					"Vlad",
					"Tymchenko",
					email,
					Gender.MALE,
					address,
					List.of("Java"),
					BigDecimal.TEN,
					LocalDateTime.now()
			);
//			usingMongoTemplateAndQuery(repository, mongoTemplate, mail, student);

			repository.findStudentByEmail(email)
					.ifPresentOrElse(s -> {
						System.out.println(s + " student already exists");
					}, () -> {repository.insert(student);});
		};
	}

	private static void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String mail, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(mail));

		List<Student> students = mongoTemplate.find(query, Student.class);

		if (students.size() > 1) {
			throw new IllegalStateException("Error");
		}
		if (students.isEmpty()) {
			repository.insert(student);
		} else {
			System.out.println(student + " student already exists");
		}
	}
}
