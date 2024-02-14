package com.mvp.hrs.repo;

import com.mvp.hrs.model.UserDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBUserRepo extends CrudRepository<UserDetails, String> {}
