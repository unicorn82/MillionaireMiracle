package com.millionaire.compound.hibernate.dao;

import com.millionaire.compound.hibernate.entity.basic.MiracleStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface ExtendedRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {


    List<T> findByAttributeContainsText(String attributeName, String text);


}
