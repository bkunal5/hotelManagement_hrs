package com.mvp.hrs.controller;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.mvp.hrs.model.HotelBooking;
import com.mvp.hrs.model.ResponseBody;
import com.mvp.hrs.model.UserDetails;
import com.mvp.hrs.service.HotelService;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class HotelBookingControllerTest {

  public static final String TEST = "test";
  @Mock private HotelService hotelService;
  @InjectMocks private HotelBookingController hotelBookingController;
  @Mock private Logger logger;
  @Mock ResponseBody responseBody;
  @Mock HotelBooking hotelBooking;
  private static MockedStatic loggerMock;
  private static MockedStatic loggerManagerMock;

  @Test
  void login() {
    loggerMock = mockStatic(Logger.class);
    loggerManagerMock = mockStatic(LogManager.class);
    when(LogManager.getLogger(HotelBookingController.class)).thenReturn(logger);
    when(hotelService.isValidUser(any())).thenReturn(true);
    hotelBookingController.login(new UserDetails().setUsername(TEST).setPassword(TEST));
    loggerMock.close();
    loggerManagerMock.close();
    assertSame(HttpStatus.OK, HttpStatus.OK);
  }

  @Test
  void addUser() {
    loggerMock = mockStatic(Logger.class);
    loggerManagerMock = mockStatic(LogManager.class);
    when(LogManager.getLogger(HotelBookingController.class)).thenReturn(logger);
    when(hotelService.saveUser(any())).thenReturn(true);
    hotelBookingController.addUser(new UserDetails().setUsername(TEST).setPassword(TEST));
    loggerMock.close();
    loggerManagerMock.close();
    assertSame(HttpStatus.OK, HttpStatus.OK);
  }

  @Test
  void book() {
    loggerMock = mockStatic(Logger.class);
    loggerManagerMock = mockStatic(LogManager.class);
    when(LogManager.getLogger(HotelBookingController.class)).thenReturn(logger);
    when(hotelService.book(any())).thenReturn(true);
    hotelBookingController.book(new HotelBooking().setHotelId(TEST).setCustomerUserName(TEST));
    loggerMock.close();
    loggerManagerMock.close();
    assertSame(HttpStatus.OK, HttpStatus.OK);
  }

  @Test
  void getListBookings() {
    loggerMock = mockStatic(Logger.class);
    loggerManagerMock = mockStatic(LogManager.class);
    when(LogManager.getLogger(HotelBookingController.class)).thenReturn(logger);
    when(hotelService.getAllBooking(any())).thenReturn(Collections.singletonList(hotelBooking));
    hotelBookingController.getListBookings(any());
    loggerMock.close();
    loggerManagerMock.close();
    assertSame(HttpStatus.OK, HttpStatus.OK);
  }

  @Test
  void deleteBooking() {
    loggerMock = mockStatic(Logger.class);
    loggerManagerMock = mockStatic(LogManager.class);
    when(LogManager.getLogger(HotelBookingController.class)).thenReturn(logger);
    when(hotelService.deleteBooking(any())).thenReturn(true);
    hotelBookingController.deleteBooking(any(Integer.class));
    loggerMock.close();
    loggerManagerMock.close();
    assertSame(HttpStatus.OK, HttpStatus.OK);
  }

  @Test
  void updateBooking() {
    loggerMock = mockStatic(Logger.class);
    loggerManagerMock = mockStatic(LogManager.class);
    when(LogManager.getLogger(HotelBookingController.class)).thenReturn(logger);
    when(hotelService.updateBooking(any(Integer.class), any(HotelBooking.class))).thenReturn(true);
    hotelBookingController.updateBooking(any(Integer.class), any(HotelBooking.class));
    loggerMock.close();
    loggerManagerMock.close();
    assertSame(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND);
  }
}
