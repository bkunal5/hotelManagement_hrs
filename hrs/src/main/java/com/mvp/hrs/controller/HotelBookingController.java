package com.mvp.hrs.controller;

import com.mvp.hrs.model.HotelBooking;
import com.mvp.hrs.model.ResponseBody;
import com.mvp.hrs.model.UserDetails;
import com.mvp.hrs.service.HotelService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
public class HotelBookingController {
  private static final Logger logger = LogManager.getLogger(HotelBookingController.class);

  @Autowired HotelService hotelService;
  @Autowired ResponseBody responseBody;

  @PostMapping("/login")
  public ResponseEntity<ResponseBody> login(@RequestBody UserDetails userDetails) {
    logger.info("Received Login Request from User {}", userDetails.getUsername());
    if (hotelService.isValidUser(userDetails)) {
      logger.info("User is valid with username {}", userDetails.getUsername());
      responseBody.setMsg("Login Successful");
      return new ResponseEntity<>(responseBody, HttpStatus.OK);
    } else {
      logger.info("User is not valid with username {}", userDetails.getUsername());
      responseBody.setMsg("Bad Credentials");
      return new ResponseEntity(responseBody, HttpStatus.FORBIDDEN);
    }
  }

  @PostMapping("/addUser")
  public ResponseEntity<ResponseBody> addUser(@RequestBody UserDetails userDetails) {
    logger.info("Received New Registration Request from User {}", userDetails.getUsername());
    if (hotelService.saveUser(userDetails)) {
      logger.info("User is registered successfully with username {}", userDetails.getUsername());
      responseBody.setMsg("Registration Successful");
      return new ResponseEntity(responseBody, HttpStatus.OK);
    } else {
      logger.info("User can not be registered with username {}", userDetails.getUsername());
      responseBody.setMsg("Registration Unsuccessful");
      return new ResponseEntity(responseBody, HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/book")
  public ResponseEntity<ResponseBody> book(@RequestBody HotelBooking hotelBooking) {
    if (hotelService.book(hotelBooking)) {
      logger.info(
          "User Booking is successful with username {}", hotelBooking.getCustomerUserName());
      responseBody.setMsg("Registration Successful");
      return new ResponseEntity(responseBody, HttpStatus.OK);
    } else {
      logger.info("User Booking failed with username {}", hotelBooking.getCustomerUserName());
      responseBody.setMsg("Registration Unsuccessful");
      return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/getAllBooking/{username}")
  public List<HotelBooking> getListBookings(@PathVariable String username) {
    return hotelService.getAllBooking(username);
  }

  @DeleteMapping("/delete/{bookingId}")
  public ResponseEntity<ResponseBody> deleteBooking(@PathVariable Integer bookingId) {
    if (hotelService.deleteBooking(bookingId)) {
      logger.info("Booking successfully deleted with booking id {}", bookingId);
      responseBody.setMsg("Booking Successfully Deleted");
      return new ResponseEntity(responseBody, HttpStatus.OK);
    } else {
      logger.info("No Booking found with booking id {}", bookingId);
      responseBody.setMsg("No Booking Found");
      return new ResponseEntity(responseBody, HttpStatus.NOT_FOUND);
    }
  }

  @PutMapping("/update/{bookingId}")
  public ResponseEntity<ResponseBody> updateBooking(
      @PathVariable Integer bookingId, @RequestBody HotelBooking hotelBooking) {
    if (hotelService.updateBooking(bookingId, hotelBooking)) {
      logger.info("Booking successfully updated booking id {}", bookingId);
      responseBody.setMsg("Booking Successfully updated");
      return new ResponseEntity(responseBody, HttpStatus.OK);
    } else {
      logger.info("No Booking found for bookingId {}", bookingId);
      responseBody.setMsg("No booking found");
      return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
  }
}
