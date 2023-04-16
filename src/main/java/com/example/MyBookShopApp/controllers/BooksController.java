package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.BookRepository;
import com.example.MyBookShopApp.data.ResourceStorage;
import com.example.MyBookShopApp.data.SearchWordDto;
import com.example.MyBookShopApp.data.struct.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookRepository bookRepository;
    private final ResourceStorage storage;

    @Autowired
    public BooksController(BookRepository bookRepository, ResourceStorage storage) {
        this.bookRepository = bookRepository;
        this.storage = storage;
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("nameGetter")
    public String nameGetter() {
        return "";
    }

    @GetMapping("/{slug}")
    public String bookPage(@PathVariable("slug") String slug, Model model){
        Book book = bookRepository.findBookBySlug(slug);
        model.addAttribute("slugBook",book);
        return "/books/slug";
    }

    @PostMapping("/{slug}/img/save")
    public String saveNewBookImage(@RequestParam("file")MultipartFile file, @PathVariable("slug") String slug) throws IOException{
        Logger.getLogger(BooksController.class.getSimpleName()).info("inside method " +slug);
        String savePath = storage.saveNewBookImage(file,slug);
        Book bookToUpdate = bookRepository.findBookBySlug(slug);
        bookToUpdate.setImage(savePath);
        bookRepository.save(bookToUpdate);

        return "redirect:/books/" + slug;
    }

    @GetMapping("/download/{hash}")
    public ResponseEntity<ByteArrayResource> bookFile(@PathVariable("hash") String hash) throws IOException{
        Path path = storage.getBookFilePath(hash);
        Logger.getLogger(BooksController.class.getSimpleName()).info("book file path: " + path);

        MediaType mediaType = storage.getFileMime(hash);
        Logger.getLogger(BooksController.class.getSimpleName()).info("book file mime type: " + mediaType);

        byte[] data = storage.getBookFileByArray(hash);
        Logger.getLogger(BooksController.class.getSimpleName()).info("book file data length: " + data.length);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
                .contentType(mediaType)
                .contentLength(data.length)
                .body(new ByteArrayResource(data));
    }
}
