package com.spring.userApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.userApp.dao.DependentDAO;
import com.spring.userApp.dao.DependentDAOImpl;
import com.spring.userApp.domain.Dependent;

@Service
public class DependentServiceImpl implements DependentService {

	@Autowired
	private DependentDAO dependentDAO;

	public void setDependentDAO(DependentDAO dependentDAO) {
		this.dependentDAO = dependentDAO;
	}

	@Override
	@Transactional
	public void addDependent(Dependent dependent) {
		System.out.println("---> Adding Dependent: " + dependent);
		dependentDAO.addDependent(dependent);
	}

	@Override
	@Transactional
	public void deleteDependent(Integer id) {
		dependentDAO.deleteDependent(id);
	}

	@Override
	@Transactional
	public Dependent getDependent(Integer id) {
		return dependentDAO.getDependent(id);
	}

	@Override
	@Transactional
	public List<Dependent> getAllDependents() {
		System.out.println("----> Retrieving all dependents.");
		return dependentDAO.getAllDependents();
	}

}
