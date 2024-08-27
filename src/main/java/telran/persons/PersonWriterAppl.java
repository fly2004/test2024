package telran.persons;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.persons.dto.*;
import telran.persons.repo.PersonsRepository;


@SpringBootApplication
public class PersonWriterAppl
{
	static final String FILE_NAME = "persons.data";
	static final int N_PERSONS = 100;
	static final int N_CITIES = 5;
	static final int N_COMPANIES = 5;
	static final int N_GARTENS = 5;
	static final int EMP_PROB = 50;
	static final int MIN_CHILD_YEAR = 2006;
	static final int MAX_CHILD_YEAR = 2024;
	static final int MIN_EMP_YEAR = 1950;
	static final int MAX_EMP_YEAR = 2010;
	static final int MIN_SALARY = 6000;
	static final int MAX_SALARY = 100000;
	static final String[] TITLES = {"Manager", "Programmer", "Devop", "Cleaner"};
	static final Random random = new Random();	
	
	public static void main(String[] args) throws IOException
	{
		List<Person> list = getRandomPersons();
		ConfigurableApplicationContext cac = SpringApplication.run(PersonWriterAppl.class, args);
		PersonsRepository personRepo = cac.getBean(PersonsRepository.class);
		personRepo.saveAll(list);
	}

	private static List<Person> getRandomPersons()
	{
		return Stream.generate(PersonWriterAppl::getRandomPerson).limit(N_PERSONS)
				.collect(Collectors.toList());
	}
	
	private static Person getRandomPerson()
	{
		Person person = getRandomCommonPerson();
		return getRandomNumber(1, 100) <= EMP_PROB ? getRandomEmployee(person) 
				: getRandomChild(person);
	}

	private static Person getRandomChild(Person person)
	{
		String garten = "garten" + getRandomNumber(1, N_GARTENS);
		
		return new Child(person.id, person.name, person.address, 
				getRandomDate(MIN_CHILD_YEAR, MAX_CHILD_YEAR), garten);
	}

	private static Person getRandomEmployee(Person person)
	{
		LocalDate birthDate = getRandomDate(MIN_EMP_YEAR, MAX_EMP_YEAR);
		String company = "company" + getRandomNumber(1, N_COMPANIES);
		String title = TITLES[getRandomNumber(0, TITLES.length-1)];
		int salary = getRandomNumber(MIN_SALARY, MAX_SALARY);
		return new Employee(person.id, person.name, person.address, birthDate, company, title, salary);
	}

	private static LocalDate getRandomDate(int minYear, int maxYear)
	{
		int year = getRandomNumber(minYear, maxYear);
		int month = getRandomNumber(1, 12);
		int day = getRandomNumber(1, 28);
		return LocalDate.of(year, month, day);
	}

	private static Person getRandomCommonPerson()
	{
		int id = getRandomNumber(100_000, 999_999);
		String name = "name" + getRandomNumber(1, 20);
		Address address = getRandomAddress();
		return new Person(id, name, address, null);
	}

	private static Address getRandomAddress()
	{
		String city = "city" + getRandomNumber(1, N_CITIES);
		String street = "street" + getRandomNumber(1, 20);
		int building = getRandomNumber(1, 100);
		int flat = getRandomNumber(1, 30);
		return new Address(city, street, building, flat);
	}

	private static int getRandomNumber(int min, int max)
	{
		return random.ints(1, min, max+1).findFirst().getAsInt();
	}
}
