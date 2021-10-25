package com.api.repository;

import com.api.entities.Movie;
import com.api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
