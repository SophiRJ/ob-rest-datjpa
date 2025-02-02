package com.example.ob_rest_datajpa.controller;

import com.example.ob_rest_datajpa.entities.Book;
import com.example.ob_rest_datajpa.repository.BookRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    //Buscar un solo libro en base de datos segun id
    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable Long id){
        Optional<Book> bookOpt= bookRepository.findById(id);
        //Opcion 1
        if(bookOpt.isPresent())
            //Devuelve una respuesta OK con un valor ->el libro
             return ResponseEntity.ok(bookOpt.get());
          else
              //Devuelve una respuesta NOTFOUND
             return ResponseEntity.notFound().build();

        //Opcion 2
        //return bookOpt.orElse(null);
        //return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    //Crear un nuevo libro en base de datos -> que devuelva el libro creado
    @PostMapping("/api/books")
    public Book create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        //Guardar el libro recibido por parametro en la base de datos y devolvemos el nuevo libro
        return bookRepository.save(book);
    }
    //actualizar un libro existente en base de datos
    //Borrar un libro en bd
}
