package org.example.booksservice.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.SneakyThrows;
import org.example.booksservice.dto.request.BookDto;
import org.example.booksservice.dto.response.BookResponse;
import org.example.booksservice.entity.Book;
import org.example.booksservice.repository.BookRepository;
import org.example.booksservice.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final Cloudinary cloudinary;
    private final BookRepository bookRepository;
    private final WebClient webClient;

    public BookServiceImpl(Cloudinary cloudinary, BookRepository bookRepository, WebClient.Builder webClientBuilder) {
        this.cloudinary = cloudinary;
        this.bookRepository = bookRepository;
        this.webClient = webClientBuilder.build();
    }

    @SneakyThrows
    @Transactional
    public Book createBook(BookDto bookDto) {
        Optional<Book> book = bookRepository.findByTitle(bookDto.getTitle());
        if (book.isPresent()) {
            throw new IllegalArgumentException("Эта книга уже присутствует");
        }

        Map<String, Object> uploadImg = cloudinary.uploader().upload(bookDto.getImgUrl().getBytes(), ObjectUtils.emptyMap());
        Map<String, Object> uploadPdf = cloudinary.uploader().upload(bookDto.getPdfUrl().getBytes(),
                ObjectUtils.asMap("resource_type", "raw"));

        Book newBook = Book.builder()
                .genre(bookDto.getGenre())
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .pdfUrl(uploadPdf.get("url").toString())
                .author(bookDto.getAuthor())
                .imgUrl(uploadImg.get("url").toString())
                .createdAt(LocalDateTime.now())
                .build();

        return bookRepository.save(newBook);
    }

    public BookResponse getBookWithTitle(String title,String userId) {
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new IllegalArgumentException("Книга с таким названием не найдена"));

        saveDownload(book.getId(), userId);

        return mapBookToResponse(book);
    }

    public BookResponse getBookWithAuthor(String author) {
        Book book = bookRepository.findAllByAuthor(author)
                .orElseThrow(() -> new IllegalArgumentException("Книга с таким автором не найдена"));

        return mapBookToResponse(book);
    }

    public BookResponse getBookWithGenre(String genre) {
        Book book = bookRepository.findAllByGenre(genre)
                .orElseThrow(() -> new IllegalArgumentException("Книга с таким жанром не найдена"));

        return mapBookToResponse(book);
    }

    private BookResponse mapBookToResponse(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .genre(book.getGenre())
                .imgUrl(book.getImgUrl())
                .pdfUrl(book.getPdfUrl())
                .build();
    }

    private void saveDownload(Long bookId,String userId){
        webClient.post()
                .uri("http://localhost:8081/api/v1/downloads/{bookId}?userId={userId}",bookId,userId)
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }

}
