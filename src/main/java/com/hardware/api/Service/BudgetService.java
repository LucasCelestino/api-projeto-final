package com.hardware.api.Service;

import java.util.List;
import java.util.Optional;

import com.hardware.api.DTO.BudgetDTO;
import com.hardware.api.DTO.UserDTO;
import com.hardware.api.Mapper.BudgetMapper;
import com.hardware.api.Mapper.UserMapper;
import com.hardware.api.Model.Budget;
import com.hardware.api.Repository.BudgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService implements ServiceInterface<BudgetDTO>
{

    @Autowired
    private BudgetRepository budgetRepository;

    @Autowired
    private BudgetMapper budgetMapper;

    @Autowired
    private UserMapper userMapper;

    // @Override
    public List<BudgetDTO> findAll()
    {
        return budgetMapper.toDTO(budgetRepository.findAll());
    }

    // @Override
    public BudgetDTO findById(Long id)
    {
        Optional<Budget> optionalBudget = budgetRepository.findById(id);

        if(optionalBudget.isPresent())
        {
            return budgetMapper.toDTO(optionalBudget.get());
        }

        return null;
    }

    public List<BudgetDTO> findByUser(UserDTO user)
    {
        List<Budget> optionalBudget = budgetRepository.findByUser(userMapper.toEntity(user));

        return budgetMapper.toDTO(optionalBudget);
    }

    // @Override
    public BudgetDTO create(BudgetDTO budgetDTO)
    {
        Budget newBudget = budgetRepository.save(budgetMapper.toEntity(budgetDTO));

        return budgetMapper.toDTO(newBudget);
    }

    // @Override
    public boolean update(BudgetDTO budgetDTO) 
    {
        if(budgetRepository.existsById(budgetDTO.getId()))
        {
            budgetRepository.save(budgetMapper.toEntity(budgetDTO));

            return true;
        }

        return false;
    }

    // @Override
    public boolean delete(Long id)
    {
        if(budgetRepository.existsById(id))
        {
            budgetRepository.deleteById(id);
            
            return true;
        }

        return false;
    }
    
}
