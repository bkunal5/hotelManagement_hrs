package com.mvp.hrs.model;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
@Entity
@Table(name = "HotelBookingData")
@Accessors(chain = true)
public class HotelBooking {
  @Id @GeneratedValue private int bookingId;
  private String hotelId;
  private String hotelName;
  private String hotelBookingDate;
  private String customerUserName;
  private int bookingAmount;
  private int payableAmount;
  private int discount;
}
