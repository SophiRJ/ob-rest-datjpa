package com.example.ob_rest_datajpa.controller;

import com.example.ob_rest_datajpa.entities.Book;
import com.example.ob_rest_datajpa.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private final Logger log= LoggerFactory.getLogger(BookController.class);
    //Atributo
    private BookRepository bookRepository;
    //Constructor

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    //CRUD sobre la entidad book

    //
    /**
     *Buscar todos los libros lista libros (ArrayList de Libros)
     * http://localhost:8080/api/books
     * @return
     */
    @GetMapping("/api/books")
    public List<Book> findAll(){
        //Recuperar y devolver los libros en bd
        return bookRepository.findAll();
    }
    /**
    * http://localhost:8080/api/books/1
    * http://localhost:8080/api/books/2
    * @param id
    * @return
    */
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
    /**
     * Metodo POST no coalisiona con findall porque son diferentes metodos HTTP; GET vs. POST
     * @param headers
     * @param book
     * @return
     */
    //Crear un nuevo libro en base de datos -> que devuelva el libro creado
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book, @RequestHeader HttpHeaders headers){
        System.out.println(headers.get("User-Agent"));
        //Guardar el libro recibido por parametro en la base de datos y devolvemos el nuevo libro
        if(book.getId()!=null){// quiere decir  ue existe el id y por tanto no es una creacion
            log.warn("trying to create a book with id");
            System.out.println("trying to create a book with id");
            return ResponseEntity.badRequest().build();

        }
        Book result=bookRepository.save(book);
        return ResponseEntity.ok(result);// el nuevo libro devuelve la clase primaria
    }
    /**
     * actualizar un libro existente en base de datos
     */

    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId()==null){// si no tiene id quiere decir que es una creacion
            log.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        if(!bookRepository.existsById(book.getId())){//si mete un id que no existe
            log.warn("Trying to update a non existent book");
            return ResponseEntity.notFound().build();
        }
        //El proceso de actualizacion
        Book result=bookRepository.save(book);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if(!bookRepository.existsById(id)){//si mete un id que no existe
            log.warn("Trying to delete a non existent book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();//Cuando se borra algo
    }
    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll(){
        log.info("REST Resquest for delete all books");
        bookRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
