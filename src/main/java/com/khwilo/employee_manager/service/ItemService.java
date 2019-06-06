package com.khwilo.employee_manager.service;

import com.khwilo.employee_manager.dao.ItemRepository;
import com.khwilo.employee_manager.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(
                item -> items.add(item)
        );

        return items;
    }

    public static int getWeeksToRenewal(Date assignedDate, int monthsReplacementCycle) {
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
