package com.akash.quiz_app.repository;

import com.akash.quiz_app.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
