package org.api.stockmarket.options.repository;

import org.api.stockmarket.options.entity.OptionContract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionContractRepository extends JpaRepository<OptionContract, Long> {
}
