package com.mvp.hrs.service;

import static java.util.Objects.nonNull;

import com.mvp.hrs.model.HotelBooking;
import com.mvp.hrs.model.UserDetails;
import com.mvp.hrs.repo.DBRepo;
import com.mvp.hrs.repo.DBUserRepo;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelService {
  private static final Logger logger = LogManager.getLogger(HotelService.class);

  @Autowired DBRepo dbRepo;
  @Autowired DBUserRepo dbUserRepo;
  @Autowired UserDetails userDetails;
  private static final String EMPTY_JSON = "";

  public boolean saveUser(UserDetails userDetails) {
    try {
      Optional<UserDetails> userDetailsOptional = dbUserRepo.findById(userDetails.getUsername());
      if (!userDetailsOptional.isPresent()) {
        dbUserRepo.save(userDetails);
        return true;
      } else return false;
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
  }

  public boolean isValidUser(UserDetails userDetails) {
    Optional<UserDetails> userDetailsOptional = dbUserRepo.findById(userDetails.getUsername());
    if (userDetailsOptional.isPresent()) {
      if (userDetailsOptional.get().getPassword().equals(userDetails.getPassword())) {
        return true;
      }
    }
    return false;
  }

  public boolean book(HotelBooking hotelBooking) {
    if (nonNull(hotelBooking.getCustomerUserName())) {
      Optional<UserDetails> userName = dbUserRepo.findById(hotelBooking.getCustomerUserName());
      if (!userName.isPresent()) {
        userDetails.setUsername(hotelBooking.getCustomerUserName());
        userDetails.setPassword(UUID.randomUUID().toString());
        saveUser(userDetails);
      }
      dbRepo.save(hotelBooking);
      return true;
    }
    return false;
  }

  public List<HotelBooking> getAllBooking(String username) {
    return dbRepo.findByCustomerUserName(username);
  }

  public boolean updateBooking(Integer bookingId, HotelBooking hotelBooking) {
    Optional<HotelBooking> hotelBookingOptional = dbRepo.findById(bookingId);
    if (hotelBookingOptional.isPresent()) {
      HotelBooking hotelBookingObj = hotelBookingOptional.get();
      if (isUpdatedValue(hotelBooking.getHotelId())) {
        hotelBookingObj.setHotelId(hotelBooking.getHotelId());
      }
      if (isUpdatedValue(hotelBooking.getHotelName())) {
        hotelBookingObj.setHotelName(hotelBooking.getHotelName());
      }
      if (isUpdatedValue(hotelBooking.getBookingAmount())) {
        hotelBookingObj.setBookingAmount(hotelBooking.getBookingAmount());
      }
      if (isUpdatedValue(hotelBooking.getHotelBookingDate())) {
        hotelBookingObj.setHotelBookingDate(hotelBooking.getHotelBookingDate());
      }
      if (isUpdatedValue(hotelBooking.getDiscount())) {
        hotelBookingObj.setDiscount(hotelBooking.getDiscount());
      }
      if (isUpdatedValue(hotelBooking.getPayableAmount())) {
        hotelBookingObj.setPayableAmount(hotelBooking.getPayableAmount());
      }
      dbRepo.save(hotelBookingObj);
      return true;
    }
    return false;
  }

  public boolean deleteBooking(Integer bookingId) {
    if (dbRepo.findById(bookingId).isPresent()) {
      dbRepo.deleteById(bookingId);
      return true;
    }
    return false;
  }

  private boolean isUpdatedValue(String value) {
    return nonNull(value) && !(value.trim().equals(EMPTY_JSON));
  }

  private boolean isUpdatedValue(Integer value) {
    return nonNull(value) && !(value.equals(0));
  }
}
