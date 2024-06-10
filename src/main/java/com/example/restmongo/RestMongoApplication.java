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
}
