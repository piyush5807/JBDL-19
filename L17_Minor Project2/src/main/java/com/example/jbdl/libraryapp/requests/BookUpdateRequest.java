package com.example.jbdl.libraryapp.requests;

import com.example.jbdl.libraryapp.models.Genre;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookUpdateRequest {

    private String name;
    private Genre genre;
    private Integer cost;
}
