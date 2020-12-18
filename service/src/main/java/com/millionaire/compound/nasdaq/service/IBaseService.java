package com.millionaire.compound.nasdaq.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IBaseService<E extends Serializable, PK extends Serializable> {

        E get(PK id);
        public E save(E entity);
        public void update(E entity);


        public void delete(PK id);

        boolean exists(PK id);

        public List<E> getAllByOrder(Map<String, Object> equalParam, boolean isAsc, String... orderPros);

//        public List<E> findByProperties(Map<String, Object> param);
}
