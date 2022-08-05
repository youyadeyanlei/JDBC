package com.pojo;


import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoices {

  private long customerId;
  private String firstName;
  private String lastName;
  private Date birthDate;
  private String phone;
  private String address;
  private String city;
  private String state;
  private int points;



}
