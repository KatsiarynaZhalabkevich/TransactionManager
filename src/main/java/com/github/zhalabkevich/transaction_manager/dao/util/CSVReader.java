package com.github.zhalabkevich.transaction_manager.dao.util;

import com.github.zhalabkevich.transaction_manager.bean.Transaction;
import com.github.zhalabkevich.transaction_manager.bean.Type;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CSVReader {

    private final static CSVReader instance = new CSVReader();
    private final ResourceBundle resource = ResourceBundle.getBundle("prop");
    private final static String ID = "ID";
    private final static String DATE = " Date";
    private final static String AMOUNT = " Amount";
    private final static String MERCHANT = " Merchant";
    private final static String TYPE = " Type";
    private final static String RELATED_TRANSACTION = " Related Transaction";


    private CSVReader() {
    }

    public static CSVReader getInstance() {
        return instance;
    }


    public List<Transaction> readCSVFile(String path) throws IOException {
        Reader in = new FileReader(path);
        List<Transaction> list = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
        for (CSVRecord record : records) {
            String id = record.get(ID);
            LocalDateTime date = LocalDateTime.parse(
                    record.get(DATE).trim(),
                    DateTimeFormatter.ofPattern(resource.getString("prop.datePattern"))
            );
            BigDecimal amount = new BigDecimal(record.get(AMOUNT).trim());
            String merchant = record.get(MERCHANT).trim();
            Type type = Type.valueOf(record.get(TYPE).trim());
            Transaction relatedTransaction = (record.get(RELATED_TRANSACTION) != null)
                    ? new Transaction(record.get(RELATED_TRANSACTION).trim())
                    : new Transaction();
            Transaction tr = new Transaction(id, date, amount, merchant, type, relatedTransaction);
            list.add(tr);
        }

        return list;
    }
}
