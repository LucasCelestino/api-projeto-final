package com.hardware.api.Repository;
import com.hardware.api.Model.Budget;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>
{
    @Query(value = "SELECT * FROM budgets WHERE budgets.id_user = :user", nativeQuery = true)
    List<Budget> findBudgetByUser(@Param("user") Long user);
}
