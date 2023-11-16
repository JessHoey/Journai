package com.example.JournAI.Repository;

import com.example.JournAI.Model.ModelAI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryAI extends JpaRepository<ModelAI, Long> {

    @Query(value = "SELECT * FROM journai ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    List<ModelAI> findIdWithHighestValue();

    @Query(value = "Select start FROM journai ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    String findStart();

    @Query(value = "Select destination FROM journai ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    String findDestination();

    @Query(value = "Select range FROM journai ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    int findRange();

    @Query(value = "Select option FROM journai ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    List<String> findOption();

    @Query(value = "Select start FROM journai ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    String start();


    @Query(value = "Select destination FROM journai ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    String end();

    @Query(value = "Select option FROM journai ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    String option();

    @Query(value = "Select range FROM journai ORDER BY id DESC LIMIT 1;", nativeQuery = true)
    String range();
}
