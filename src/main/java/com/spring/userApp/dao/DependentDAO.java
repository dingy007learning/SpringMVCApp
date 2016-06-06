/**
 * 
 */
package com.spring.userApp.dao;

import java.util.List;

import com.spring.userApp.domain.Dependent;

/**
 * @author dineshkp
 *
 */
public interface DependentDAO {
	
	public void addDependent(Dependent contact);
	public void deleteDependent(Integer id);
	public Dependent getDependent(Integer id);
	public List<Dependent> getAllDependents();

}
