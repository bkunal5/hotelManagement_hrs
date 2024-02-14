package com.mvp.hrs.repo;

import com.mvp.hrs.model.HotelBooking;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBRepo extends CrudRepository<HotelBooking, Integer> {
  List<HotelBooking> findByCustomerUserName(String customerUserName);
}
