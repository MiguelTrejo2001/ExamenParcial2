package pe.upeu.edu.pe.graphQl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import pe.upeu.edu.pe.graphQl.dto.LibroRequest;
import pe.upeu.edu.pe.graphQl.entity.Autor;
import pe.upeu.edu.pe.graphQl.entity.Editorial;
import pe.upeu.edu.pe.graphQl.entity.Libro;
import pe.upeu.edu.pe.graphQl.repository.AutorRepository;
import pe.upeu.edu.pe.graphQl.repository.EditorialRepository;
import pe.upeu.edu.pe.graphQl.repository.LibroRepository;


import java.util.List;

@Controller
public class LibroController{

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private EditorialRepository editorialRepository;
    @Autowired
    private LibroRepository libroRepository;

    @QueryMapping
    public List<Autor> listarAutor() {
        return autorRepository.findAll();
    }
    @QueryMapping
    public List<Editorial> listarEditorial() {
        return editorialRepository.findAll();
    }
    @QueryMapping
    public List<Libro> listarLibro(){
        return libroRepository.findAll();
    }

    @QueryMapping
    public Libro listarLibroPorId(@Argument int id) {
        return libroRepository.findById(id).orElse(null);
    }
    @MutationMapping
    public Libro guardarLibro(@Argument LibroRequest libroRequest){
        Autor autor = autorRepository.findById(libroRequest.id_autor()).orElse(null);
        Editorial editorial = editorialRepository.findById(libroRequest.id_editorial()).orElse(null);
        Libro libroBBDD = new Libro();

        libroBBDD.setTitulo(libroRequest.titulo());
        libroBBDD.setDescripcion(libroRequest.descripcion());
        libroBBDD.setPagina(libroRequest.pagina());
        libroBBDD.setEdicion(libroRequest.edicion());
        libroBBDD.setAutor(autor);
        libroBBDD.setEditorial(editorial);

        return libroRepository.save(libroBBDD);
    }
    @MutationMapping
    public Libro actualizarLibro(@Argument int id, @Argument LibroRequest libroRequest){
        Autor autor = autorRepository.findById(libroRequest.id_autor()).orElse(null);
        Editorial editorial = editorialRepository.findById(libroRequest.id_editorial()).orElse(null);
        Libro libroBBDD = new Libro();

        libroBBDD.setId_libro(id);
        libroBBDD.setTitulo(libroRequest.titulo());
        libroBBDD.setDescripcion(libroRequest.descripcion());
        libroBBDD.setPagina(libroRequest.pagina());
        libroBBDD.setEdicion(libroRequest.edicion());
        libroBBDD.setAutor(autor);
        libroBBDD.setEditorial(editorial);
        return libroRepository.save(libroBBDD);
    }

    @MutationMapping
    public void eliminarLibro(@Argument int id) {
        libroRepository.deleteById(id);
    }

}
