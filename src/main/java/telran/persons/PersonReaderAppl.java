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
import telran.persons.repo.PersonsRepository;

@SpringBootApplication
public class PersonReaderAppl {

	public static void main(String[] args) {
		ConfigurableApplicationContext cac = SpringApplication.run(PersonReaderAppl.class, args);
		PersonsRepository personRepo = cac.getBean(PersonsRepository.class);
		List<Person> list;
		Person person;
		// list = personRepo.findAll();
		// list.forEach(System.out::println);
		// person = personRepo.findById(943303).orElse(null);
		// System.out.println(person);
		// list = personRepo.findByAddressCity("city2");
		// list = personRepo.findByAddressCityIgnoreCaseAndBirthDateBetween("City2",
		// LocalDate.ofYearDay(2000, 1),
		// LocalDate.ofYearDay(2023, 365));
		// ++++++++++++++++++++++ HW
		// find persons whose names contain "symbol"
		// find persons whose salary between ""=""

//		double avrSalary=personRepo.findAllBy().peek(System.out::println).filter(p->
//		p instanceof Employee).collect(Collectors.
//				averagingInt(p->((Employee)p).getSalary()));
//		double avrSalary = personRepo.findAllBy().filter(p -> p instanceof Employee)
//				.collect(Collectors.averagingInt(p -> ((Employee) p).getSalary()));
//		System.out.println(avrSalary);

		//System.out.println(personRepo.getAvgSalary());
		list = personRepo.getPersonNameSortLimit("1", 5);
		list.forEach(System.out::println);
		
	}

	

}
