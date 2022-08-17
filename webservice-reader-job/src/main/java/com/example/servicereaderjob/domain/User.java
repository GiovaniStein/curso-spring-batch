package com.example.servicereaderjob.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
  private int id;
  private String name;
  private String email;
  private String gender;
  private String status;

  @Override
  public String toString() {
    return "User [email=" + email + ", gender=" + gender + ", id=" + id + ", name=" + name + ", status=" + status + "]";
  }

}
