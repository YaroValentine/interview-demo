package demo.interview.interviewdemo.app;


import com.codeborne.xlstest.XLS;
import demo.interview.interviewdemo.model.TestData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class DataSources {

    public static Iterator<Object> practiceFormTestData() {
        List<Object> list = new ArrayList<>();
        Iterator<String[]> rows = ExcelReader.getData("Practice Form Test Data", "data");
        while (rows.hasNext()) {
            String[] cells = rows.next();
            list.add(new Object[]{new TestData()
                    .setFirstName(cells[1])
                    .setLastName(cells[2])
                    .setEmail(cells[3])
                    .setGender(cells[4])
                    .setDateOfBirth(cells[5])
                    .setSubjects(new ArrayList<>(Arrays.stream(cells[6].split(",")).collect(Collectors.toList())))
                    .setMobile(cells[7])
                    .setHobbies(new ArrayList<>(Arrays.stream(cells[8].split(",")).collect(Collectors.toList())))
                    .setAddress(cells[9])
                    .setState(cells[10])
                    .setCity(cells[11])
            });
        }
        return list.iterator();
    }

    /**
     * @return Test Data Iterator parsed via com.codeborne.xlstest
     * @throws IOException
     */
    public static Iterator<Object> getData() throws IOException {
        List<Object> list = new ArrayList<>();
        InputStream is = DataSources.class.getClassLoader()
                .getResourceAsStream("testdata/Practice Form Test Data.xlsx");

        assert is != null;
        XLS xls = new XLS(is);
        Sheet sheet = xls.excel.getSheetAt(0);
        Iterator<Row> iterator = sheet.rowIterator();

        while (iterator.hasNext()) {
            Row row = iterator.next();
            list.add(new Object[]{new TestData()
                    .setFirstName(row.getCell(1).toString())
                    .setLastName(row.getCell(2).toString())
                    .setEmail(row.getCell(3).toString())
                    .setGender(row.getCell(4).toString())
                    .setDateOfBirth(row.getCell(5).toString())
                    .setSubjects(new ArrayList<>(
                            Arrays.stream(row.getCell(6).toString().split(","))
                                    .collect(Collectors.toList())))
                    .setMobile(row.getCell(7).toString())
                    .setHobbies(new ArrayList<>(
                            Arrays.stream(row.getCell(8).toString().split(","))
                                    .collect(Collectors.toList())))
                    .setAddress(row.getCell(9).toString())
                    .setState(row.getCell(10).toString())
                    .setCity(row.getCell(11).toString())
            });
        }
        return list.iterator();
    }


}
