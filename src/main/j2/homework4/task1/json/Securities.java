package j2.homework4.task1.json;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Securities {

    private ArrayList<ArrayList<Object>> data;
    private ArrayList<String> columns;


    @JsonGetter("columns")
    public ArrayList<String> getColumns() {
        return columns;
    }

    @JsonGetter("data")
    public ArrayList<ArrayList<Object>> getData() {
        return data;
    }

}