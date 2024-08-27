package telran.persons.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import telran.persons.dto.Person;

public interface PersonsRepository extends MongoRepository<Person, Integer> {

	List<Person> findByAddressCity(String string);

	List<Person> findByAddressCityIgnoreCaseAndBirthDateBetween(String string, LocalDate ofYearDay,
			LocalDate ofYearDay2);
//@Query("{'salary': {''}}")
	// '$eq': =
	// '$ne': !=
	// '$gt': >
	// '$lt': <
	// '$gte': >=
	// '$lte': <=

	void deleteByIdLessThan(int i);

	boolean existsByAddressFlatIn(Object o);

	Stream<Person> findAllBy();

	@Aggregation("{$group: {_id:null, avgSalary:{$avg:$salary}}}")
	double getAvgSalary();

	@Aggregation(pipeline = {"{$match:{'name': {$regex:?0}}}", "{$sort:{'birthDate': -1}}",
			"{$limit:?1}"})
	List<Person> getPersonNameSortLimit(String string, int i);

	

}
