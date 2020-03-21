package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository("PersonDaoService")
public class PersonDaoService implements PersonDao {

	private static List<Person> DB = new ArrayList<>();

	@Override
	public int insertPerson(UUID id, Person person) {
		// TODO Auto-generated method stub
		DB.add(new Person(id, person.getName()));
		return 1;
	}

	@Override
	public List<Person> selectAllPeople() {
		// TODO Auto-generated method stub
		return DB;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		// TODO Auto-generated method stub
		return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
	}

	@Override
	public int deletePersonById(UUID id) {
		// TODO Auto-generated method stub
		Optional<Person> personMayBe = selectPersonById(id);
		if (personMayBe.isEmpty()) {
			return 0;
		} else {
			DB.remove(personMayBe.get());
			return 1;
		}
	}

	@Override
	public int updatePersonById(UUID id, Person personToUpdate) {
		// TODO Auto-generated method stub
		return selectPersonById(id).map(person -> {
			int indexOfPersonToDelete = DB.indexOf(person);
			if (indexOfPersonToDelete >= 0) {
				DB.set(indexOfPersonToDelete, new Person(id,personToUpdate.getName()));
				return 1;
			} else {
				return 0;
			}
		}).orElse(0);
	}

}
