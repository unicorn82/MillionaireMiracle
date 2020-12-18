package com.millionaire.compound.hibernate.dao.impl;

import com.millionaire.compound.hibernate.entity.basic.MiracleStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface NasdaqRepository extends JpaRepository<MiracleStock, Integer> {

}
