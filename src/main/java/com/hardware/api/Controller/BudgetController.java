package com.hardware.api.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;
import com.hardware.api.DTO.BudgetDTO;
import com.hardware.api.DTO.UserDTO;
import com.hardware.api.Repository.BudgetRepository;
import com.hardware.api.Service.BudgetService;
import com.hardware.api.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController implements ControllerInterface<BudgetDTO>
{

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private UserService userService;

    @Override
    @GetMapping
    public ResponseEntity<List<BudgetDTO>> getAll()
    {
        return ResponseEntity.status(HttpStatus.OK).body(budgetService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id)
    {
        BudgetDTO budgetDTO = budgetService.findById(id);
        
        if(budgetDTO != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(budgetDTO);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getByUser(@PathVariable("id") Long id)
    {
        UserDTO user = userService.findById(id);

        // List<BudgetDTO> budgetList = budgetService.findBudgetByUser(user.getId());

        // return ResponseEntity.status(HttpStatus.OK).body(budgetList);
        if(user != null)
        {
            List<BudgetDTO> budgetList = budgetService.findBudgetByUser(user.getId());

            if(budgetList.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            return ResponseEntity.status(HttpStatus.OK).body(budgetList);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @PostMapping
    public ResponseEntity<BudgetDTO> post(@Valid @RequestBody BudgetDTO budgetDTO) throws URISyntaxException
    {

        BudgetDTO dto = budgetService.create(budgetDTO);

        if(dto != null)
        {
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();

            return ResponseEntity.status(HttpStatus.CREATED).location(location).body(dto);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable("id") Long id)
    {
        if(budgetService.delete(id))
        {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
}
