package com.mwahdin.library.repository;

import com.mwahdin.library.model.Factor;
import com.mwahdin.library.model.Payed;
import com.mwahdin.library.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FactorRepository extends JpaRepository<Factor, Long> {
    Optional<Factor> findByUserAndPayed(User user, Payed payed);
}
