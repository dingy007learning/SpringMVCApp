package com.spring.userApp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.userApp.domain.Dependent;

@Repository
public class DependentDAOImpl implements DependentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DependentDAOImpl.class);
	
	@Override
	public void addDependent(Dependent dependent) {
		LOGGER.info("Adding Dependent: " + dependent);
		getCurrentSession().save(dependent);
	}

	@Override
	public void deleteDependent(Integer id) {
		LOGGER.info("Deleting Dependent with ID: " + id);
		Session session = getCurrentSession();
		Dependent dependent = (Dependent) session.load(Dependent.class, id);
		if (dependent != null) {
			LOGGER.debug("Deleting Dependent: " + dependent);
			session.delete(dependent);
			LOGGER.debug("Deleted");
		}

	}

	@Override
	public Dependent getDependent(Integer id) {
		LOGGER.info("Retrieving Dependent with ID: " + id);
		Dependent dependent = (Dependent)getCurrentSession().get(Dependent.class, id);
		return dependent;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dependent> getAllDependents() {
		LOGGER.info("Retrieving all Dependents");
		List<Dependent> dependentList = null;
		try {
			dependentList = (List<Dependent>)getCurrentSession().createQuery("from Dependent").list();
		}
		catch (HibernateException err) {
			LOGGER.error(err.getLocalizedMessage(), "Exception occured during Dependent List fetch from DB.");
		}
		return dependentList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dependent> searchByName(String name) {
		LOGGER.info("Searching for Dependent with Name: " + name);
		Criteria crit = getCurrentSession().createCriteria(Dependent.class);
		crit.add(Restrictions.like("name", "%"+name+"%"));
		List<Dependent> dependentList = (List<Dependent>)crit.list();
		return dependentList;
	}
	
	public void updateDependent(Dependent updatedDependentValues, Integer dependentIdToUpdate) {
		Query query = getCurrentSession().createQuery("update Dependent set name = :name, dob = :dob, gender = :gender where dependentId = :dependentId" );
		query.setParameter("name", updatedDependentValues.getName());
		query.setParameter("dob", updatedDependentValues.getDob());
		query.setParameter("gender", updatedDependentValues.getGender());
		query.setParameter("dependentId", dependentIdToUpdate);
		query.executeUpdate();
		LOGGER.info("Updated Dependent: " + updatedDependentValues);
	}
	
	private Session getCurrentSession() {
		if (sessionFactory == null) {
			System.out.println("---> SessionFactory is null");
		}
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

}
