package com.spring.userApp.service;

import java.util.List;

import com.spring.userApp.domain.Dependent;

public interface DependentService {

	
	public void addDependent(Dependent dependent);
	
	public void deleteDependent(Integer id);
	
	public Dependent getDependent(Integer id);
	
	public List<Dependent> getAllDependents();
}
