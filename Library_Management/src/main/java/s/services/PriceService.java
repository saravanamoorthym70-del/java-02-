package s.services;

import java.util.List;

import org.springframework.stereotype.Service;

import s.model.Price;
import s.repository.PriceRepository;

@Service
public class PriceService {
    private final PriceRepository repository;

    public PriceService(PriceRepository repository) {
        this.repository = repository;
    }

    public List<Price> getAllPrices() {
        return repository.findAll();
    }

    public Price getPriceById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Price not found"));
    }

    public Price addPrice(Price price) {
        return repository.save(price);
    }
}
