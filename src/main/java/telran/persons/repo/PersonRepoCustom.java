package telran.persons.repo;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;

import telran.persons.dto.Person;

@Repository
public class PersonRepoCustom {
	@Autowired
	MongoTemplate template;

	public Person addPerson(Person person) {

		return template.save(person);

	}

	public List<Person> getAll() {
		return template.findAll(Person.class);
	}

	public Person findByNameAndId(String name, int id) {
		Criteria criteria = Criteria.where("name").is(name).and("id").gt(id);

		Query query = new Query();
		query.addCriteria(criteria);
//		List<Person> list = template.find(query, Person.class);
//
//		if (list.isEmpty())
//			throw new RuntimeException("Person not found");
//		return list.get(0);
		
	
		return template.findOne(query, Person.class);

	}
	public void displayAll()
	{
		MongoCollection<Document> persons = template.getCollection("my_person");



		FindIterable<Document> documents = persons.find();
		for(Document d : documents)
		System.out.println(d.toJson());
}
	public void displayGroupByNameCount()
	{
		TypedAggregation<Person> personAggregator = Aggregation.newAggregation(Person.class, Aggregation.group("name").count().as("count"));
		AggregationResults<Map> aggregation = template.aggregate(personAggregator, Map.class);
		List<Map> mappedResult = aggregation.getMappedResults();
		System.out.println(mappedResult);
	}


}
