package s.services;

import java.util.List;

import org.springframework.stereotype.Service;

import s.model.Author;
import s.repository.AuthorRepository;

@Service
public class AuthorService {
    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Author addAuthor(Author author) {
        return repository.save(author);
    }

    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    public Author getAuthorById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
    }

    public Author updateAuthor(Long id, Author updateAuthor) {
        Author existingAuthor = getAuthorById(id);
        existingAuthor.setName(updateAuthor.getName());
        existingAuthor.setEmail(updateAuthor.getEmail());
        return repository.save(existingAuthor);
    }

    public void deleteAuthor(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Author not found");
        }
        repository.deleteById(id);
    }
}
