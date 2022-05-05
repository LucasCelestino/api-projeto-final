package com.hardware.api.Mapper;

import java.util.ArrayList;
import java.util.List;

import com.hardware.api.DTO.BudgetDTO;
import com.hardware.api.Model.Budget;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class BudgetMapper 
{
    public Budget toEntity(BudgetDTO budgetDTO)
    {
        Budget budget = new Budget();

        budget.setId(budgetDTO.getId());
        budget.setUser(budgetDTO.getUser());
        budget.setPart(budget.getPart());

        return budget;
    }

    public BudgetDTO toDTO(Budget budget)
    {
        BudgetDTO budgetDTO = new BudgetDTO();

        budgetDTO.setId(budget.getId());
        budgetDTO.setUser(budget.getUser());
        budgetDTO.setPart(budget.getPart());

        return budgetDTO;
    }

    public List<BudgetDTO> toDTO(List<Budget> list)
    {
		List<BudgetDTO> lista = new ArrayList<>();

		for (Budget p : list) {
			lista.add(this.toDTO(p));
		}

		return lista;
	}
}
