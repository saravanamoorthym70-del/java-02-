package s.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import s.model.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
