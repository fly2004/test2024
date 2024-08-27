package telran.persons;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.persons.dto.Employee;
import telran.persons.dto.Person;
import telran.persons.repo.PersonRepoCustom;
import telran.persons.repo.PersonsRepository;

@SpringBootApplication
public class PersonReaderCustomAppl {


	
	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(PersonReaderCustomAppl.class, args);
		PersonRepoCustom pr1 = cac.getBean(PersonRepoCustom.class);
		Person p = pr1.addPerson(new Person());
				System.out.println(p);

//				pr1.getAll().forEach(System.out::println);
//
//				System.out.println(pr1.findByNameAndId("name11", 9999));
//				System.out.println(pr1.findByNameAndId("name11", 1000000));

	//	pr1.displayAll();
				pr1.displayGroupByNameCount();
	}

}
