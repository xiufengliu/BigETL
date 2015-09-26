package dk.dtu.ccsd.core.service;

import dk.dtu.ccsd.core.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dk.dtu.ccsd.core.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonDao personDao;

	@Autowired
	public PersonServiceImpl(PersonDao personDao) {
		super();
		this.personDao = personDao;
	}

	public Person getPersonById(Integer id) {
		return personDao.findById(id);
	}

	@Transactional
	public void savePerson(Person person) {
		personDao.insert(person);
	}

//	public void updatePerson(Person p) {
//		personDao.update(p);
//	}

}
