package com.example.servicereaderjob.reader;

import com.example.servicereaderjob.domain.ResponseUser;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.servicereaderjob.domain.User;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserReader implements ItemReader<User> {

    private RestTemplate restTemplate = new RestTemplate();
    private Integer page = 1;
    private List<User> users = new ArrayList<>();
    private Integer total = 0;
    private int userIndex = 0;
    @Value("${chunkSize}")
    private Integer chunkSize;
    @Value("${pageSize}")
    private Integer pageSize;
    @Value("${limit}")
    private Integer limit;

    @Override
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        User user;
        if (userIndex < users.size())
            user = users.get(userIndex);
        else
            user = null;
        userIndex++;
        return user;
    }

    private List<User> fetchUserDataFromAPI() {
        ResponseEntity<ResponseUser> response = restTemplate.getForEntity(String.format("https://gorest.co.in//public/v1/users?page=%d", page), ResponseUser.class);
        return response.getBody().getData();
    }

    @BeforeChunk
    private void beforeChunk(ChunkContext context) {
        for (Integer i = 0; i < chunkSize; i += pageSize) {
            if (total >= limit) return;
            users.addAll(fetchUserDataFromAPI());
            total += users.size();
            page++;
        }

    }
    @AfterChunk
    private void afterChunk(ChunkContext context) {
      System.out.println("Fim do Chunk");
      userIndex = 0;
      users = new ArrayList<>();
    }

}
