package com.propertymanagement.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.propertymanagement.entity.PropertyEntity;
@Repository
public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {
	public List<PropertyEntity> findAllByUserEntityId(Long userId);

}
