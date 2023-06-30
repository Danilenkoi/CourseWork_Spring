package ru.skypro.coursework.coursework_spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.skypro.coursework.coursework_spring.status.LotStatus;
import ru.skypro.coursework.coursework_spring.model.LotModel;

public interface LotPagingRepository extends PagingAndSortingRepository<LotModel, Integer> {
    Page<LotModel> findAllByStatusIs(LotStatus status, Pageable pageable);

}
