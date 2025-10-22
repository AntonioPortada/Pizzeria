package com.antonio.Pizzeria.service;

import com.antonio.Pizzeria.persistence.entity.PizzaEntity;
import com.antonio.Pizzeria.persistence.repository.PizzaPageSortRepository;
import com.antonio.Pizzeria.persistence.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPageSortRepository pizzaPageSortRepository;

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageable = PageRequest.of(page, elements);
        return pizzaPageSortRepository.findAll(pageable);
    }

    public PizzaEntity get(int idPizza) {
        return pizzaRepository.findById(idPizza).orElse(null);
    }

    public Optional<PizzaEntity> save(PizzaEntity pizza) {
        return Optional.of(pizzaRepository.save(pizza));
    }

    public boolean delete(int idPizza) {
        pizzaRepository.deleteById(idPizza);
        return get(idPizza) == null;
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy, String sortDirection) {
        System.out.println(this.pizzaRepository.countByVeganTrue());

        //Pageable pageRequest = PageRequest.of(page, elements, Sort.by(sortBy)); //primera opción solo ordena por campo
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageRequest = PageRequest.of(page, elements, sort); //segunda opción, ordena y de forma desc o asc
        return this.pizzaPageSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(
                () -> new RuntimeException("La pizza no existe")
        );
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionContainsIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainsIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }
}
