package com.github.zhalabkevich.transaction_manager.controller;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionControllerTest {
    private final TransactionController controller = new TransactionController();
    private final ResourceBundle resource = ResourceBundle.getBundle("prop");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(resource.getString("prop.datePattern"));
    private final  LocalDateTime START = LocalDateTime.parse(resource.getString("prop.start"), formatter);
    private final  LocalDateTime END = LocalDateTime.parse(resource.getString("prop.end"), formatter);
    private final  String MERCHANT = resource.getString("prop.merchant");
    @Test
    void getTotal() {
      int total =  controller.getTotal(MERCHANT, START, END);
      assertEquals(1, total);
    }

    @Test
    void getAverageValue() {
        BigDecimal avg = controller.getAverageValue(MERCHANT, START, END);
        assertEquals(new BigDecimal("59.99"),avg);
    }
}