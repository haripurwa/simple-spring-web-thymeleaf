package com.school.spring.thymeleaf.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.school.spring.thymeleaf.model.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Integer> {
  List<Mahasiswa> findByNameContainingIgnoreCase(String keyword);

  @Query("UPDATE Mahasiswa t SET t.published = :published WHERE t.id = :id")
  @Modifying
  public void updatePublishedStatus(Integer id, boolean published);
}
