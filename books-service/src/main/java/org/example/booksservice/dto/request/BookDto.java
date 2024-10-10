package org.example.booksservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookDto {

    String title;
    String author;
    String genre;
    String description;
    MultipartFile imgUrl;
    MultipartFile pdfUrl;
}
