package com.khwilo.employee_manager.controller;

import com.khwilo.employee_manager.dao.EmployeeRepository;
import com.khwilo.employee_manager.dao.ItemRepository;
import com.khwilo.employee_manager.model.Employee;
import com.khwilo.employee_manager.model.Item;
import com.khwilo.employee_manager.payload.ApiResponse;
import com.khwilo.employee_manager.payload.ItemRequest;
import com.khwilo.employee_manager.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ItemRepository itemRepository;

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

        int numberOfWeeks = getWeeksToRenewal(item.getAssignedDate(), itemRequest.getMonths());
        item.setWeeksToRenewal(numberOfWeeks);

        item.setEmployee(employee);

        itemRepository.save(item);

        return new ResponseEntity<>(
                new ApiResponse(200, itemRequest.getAsset() + " has been successfully been assigned"),
                HttpStatus.OK
        );
    }

    public int getWeeksToRenewal(Date assignedDate, int monthsReplacementCycle) {
        Calendar assignedCalender = Calendar.getInstance();
        assignedCalender.setTime(assignedDate);
        assignedCalender.add(Calendar.MONTH, monthsReplacementCycle); // Add months to replace to assigned date

        Date currentDate = new Date(Calendar.getInstance().getTime().getTime());
        Calendar currentCalender = Calendar.getInstance();
        currentCalender.setTime(currentDate);

        long expiryTime = assignedCalender.getTime().getTime();
        long diffInMilliseconds = Math.abs(expiryTime - currentCalender.getTime().getTime());
        long diff = TimeUnit.DAYS.convert(diffInMilliseconds, TimeUnit.MILLISECONDS);


        double numberOfWeeks = Math.floor(diff/7);

        return (int) numberOfWeeks;
    }
}
