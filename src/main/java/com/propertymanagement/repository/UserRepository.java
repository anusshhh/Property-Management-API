package com.propertymanagement.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.propertymanagement.entity.UserEntity;
@Repository
public interface UserRepository extends CrudRepository<UserEntity,Long>{
	public Optional<UserEntity> findByEmailAndPassword(String email,String password);
	public Optional<UserEntity> findByEmail(String email);

}
