package br.com.arnaldo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.arnaldo.exception.ResourceNotFoundException;
import br.com.arnaldo.model.Person;
import br.com.arnaldo.repository.PersonRepository;

@Service
public class PersonServices {

	@Autowired
	PersonRepository repository;

	public Person create(Person person) {

		return repository.save(person);
	}

	public Person findById(Long id) {

		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
	}

	public Person update(Person person) {
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return repository.save(entity);

	}

	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
	}

	public List<Person> findAll() {
		return repository.findAll();
	}

}
