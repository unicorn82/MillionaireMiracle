package com.millionaire.compound.stock.service.impl;

import com.millionaire.compound.stock.service.IBaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public abstract class BaseServiceImpl<E extends Serializable, PK extends Serializable> implements IBaseService<E, PK> {

    protected JpaRepository<E, PK> baseRepository;

    public abstract void setBaseRepository(JpaRepository<E, PK> baseRepository);

    @Override
    public E get(PK id) {
        return baseRepository.getOne(id);
    }

    @Override

    public E save(E entity) {
        return baseRepository.save(entity);
    }

    @Override
    public void update(E entity) {
//        Optional<E> ePersisted = this.baseRepository.findOne(entity);
//        baseRepository..update(entity);
    }

    @Override
    public void delete(PK id) {
        baseRepository.deleteById(id);
    }

    @Override
    public boolean exists(PK id) {
        return baseRepository.existsById(id);
    }


//    @Override
//    public List<E> findByProperties(Map<String, Object> param) {
//        return baseRepository. baseDao.findByProperties(param);
//    }
}
