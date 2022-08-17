package com.example.servicereaderjob.domain;

import java.util.List;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUser {

  private List<User> data;

}
