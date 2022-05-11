package com.hardware.api.Repository;
import java.util.List;

import com.hardware.api.Model.Budget;
import com.hardware.api.Model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long>
{
    List<Budget> findByUser(User user);
}
