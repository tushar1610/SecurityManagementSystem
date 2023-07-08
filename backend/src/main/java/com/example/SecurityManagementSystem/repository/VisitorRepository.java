package com.example.SecurityManagementSystem.repository;

import com.example.SecurityManagementSystem.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {

    List<Visitor> findAllBySocietyUserFlatNo(String flatNo);

    List<Visitor> findAllByDate(LocalDate date);

    List<Visitor> findAllByAge(Integer age);

    List<Visitor> findAllByGender(String gender);

    Optional<Visitor> findByVisitorName(String visitorName);

    List<Visitor> findAllByDateAndSocietyUserFlatNo(LocalDate date, String flatNo);

}
