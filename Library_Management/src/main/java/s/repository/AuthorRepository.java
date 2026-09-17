package s.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import s.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
