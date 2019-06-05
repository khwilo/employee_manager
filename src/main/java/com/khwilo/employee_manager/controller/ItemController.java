package com.khwilo.employee_manager.controller;

import com.khwilo.employee_manager.dao.EmployeeRepository;
import com.khwilo.employee_manager.dao.ItemRepository;
import com.khwilo.employee_manager.model.Employee;
import com.khwilo.employee_manager.model.Item;
import com.khwilo.employee_manager.payload.ApiResponse;
import com.khwilo.employee_manager.payload.ItemRequest;
import com.khwilo.employee_manager.service.EmployeeService;
import com.khwilo.employee_manager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @PostMapping("/assign/{id}")
    public ResponseEntity<Object> assignItem(
            @PathVariable("id") String id, @Valid @RequestBody ItemRequest itemRequest) {
        Long employeeId = Long.parseLong(id);
        if (!employeeRepository.existsById(employeeId)) {
            return new ResponseEntity<>(
                    new ApiResponse(404, "Employee not found!"),
                    HttpStatus.NOT_FOUND
            );
        }

        if (!employeeRepository.existsByEmailAddress(itemRequest.getAdminEmail())) {
            return new ResponseEntity<>(
                    new ApiResponse(404, "Employee not found!"),
                    HttpStatus.NOT_FOUND
            );
        }

        Employee admin = employeeService.getEmployeeByEmailAddress(itemRequest.getAdminEmail());
        if (!admin.isAdmin()) {
            return new ResponseEntity<>(
                    new ApiResponse(401, "This action requires admin access!"),
                    HttpStatus.UNAUTHORIZED
            );
        }

        Item item = new Item(itemRequest.getAsset(), itemRequest.getAccount(), itemRequest.getMonths());

        Employee employee = employeeService.getEmployeeById(employeeId);

        int numberOfWeeks = ItemService.getWeeksToRenewal(item.getAssignedDate(), itemRequest.getMonths());
        item.setWeeksToRenewal(numberOfWeeks);

        item.setEmployee(employee);

        itemRepository.save(item);

        return new ResponseEntity<>(
                new ApiResponse(200, itemRequest.getAsset() + " has been successfully been assigned"),
                HttpStatus.OK
        );
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getAllItems() {
        if (itemService.getAllItems().isEmpty()) {
            return new ResponseEntity<>(
                    new ApiResponse(404, "No item has been added yet!"),
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }
}
