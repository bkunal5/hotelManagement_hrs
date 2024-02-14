package com.mvp.hrs.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "HrsCustomerDetails")
@Accessors(chain = true)
public class UserDetails {
  @Id private String username;
  private String password;
}
