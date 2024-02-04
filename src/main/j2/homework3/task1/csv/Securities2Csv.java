package j2.homework3.task1.csv;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import j2.homework3.task1.MoexData;

import java.io.Writer;
import java.util.ArrayList;

public class Securities2Csv {
    public static boolean save(ArrayList<MoexData> weatherList, Writer writer) {
        try {
            StatefulBeanToCsv<MoexData> beanToCsv = new StatefulBeanToCsvBuilder<MoexData>(writer)
                    .build();
            beanToCsv.write(weatherList);
            return true;
        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            return false;
        }
    }
}
