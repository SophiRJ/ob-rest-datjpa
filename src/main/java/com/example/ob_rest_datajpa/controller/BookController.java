package com.example.ob_rest_datajpa.controller;

import com.example.ob_rest_datajpa.entities.Book;
import com.example.ob_rest_datajpa.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    //Atributo
    private BookRepository bookRepository;
    //Constructor

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //CRUD sobre la entidad book

    //Buscar todos los libros lista libros
    /*
    * http://localhost:8080/api/books
    * @return
    */
    @GetMapping("/api/books")
    public List<Book> findAll(){
        //Recuperar y devolver los libros en bd
        return bookRepository.findAll();
    }

    //Buscar un solo libro en bd segun id
    //public Book findById(Long id){}
    //Crear un nuevo libro en base de datos
    //actualizar un libro existente en base de datos
    //Borrar un libro en bd
}
