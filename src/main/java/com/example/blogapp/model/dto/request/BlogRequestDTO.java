package com.example.blogapp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogRequestDTO {

    private String content;
    private String blogName;
    private String category;
    private List<String> tags;
}
