package j2.homework4.task1;

import java.util.ArrayList;
import java.util.Map;

public class FilterArrayList {

    /**
     * Метод фильтрует по значению traded, если оно совпало, добавляет в результирующий массив
     *
     * @param columns список строк именований столбцов
     * @param data    список списков данных столбцов Moex data
     * @param traded  свойство is_traded для фильтрации data
     * @return возвращает отфильтрованный data
     */
    public static ArrayList<ArrayList<Object>> filterOnTraded(ArrayList<String> columns,
                                                              ArrayList<ArrayList<Object>> data, int traded) {
        // индекс свойства is_traded, значит оно под таким же индексом в data
        int indexTraded = columns.indexOf("is_traded");

        ArrayList<ArrayList<Object>> newList = new ArrayList<>();

        for (ArrayList<Object> d : data) {
            if ((int) d.get(indexTraded) == traded) {
                newList.add(d);
            }
        }

        return newList;
    }

    /**
     * Метод selectИт data по указанным полям
     *
     * @param columns             массив строк именований столбцов
     * @param data                список списков данных столбцов Moex data
     * @param titleFieldsAndIndex мапа ключ: имя столбца для селекта и значение: её индекс в columns
     * @return возвращает список упакованных объектов MoexData
     */
    public static ArrayList<MoexData> selectOnFields(ArrayList<String> columns,
                                                     ArrayList<ArrayList<Object>> data,
                                                     Map<String, Integer> titleFieldsAndIndex) {
        setIndexes(columns, titleFieldsAndIndex);

        return select(data, titleFieldsAndIndex);
    }

    // осуществляет создание и заполнение новых объектов
    private static ArrayList<MoexData> select(ArrayList<ArrayList<Object>> data, Map<String, Integer> titleFieldsAndIndex) {
        ArrayList<MoexData> filteredList = new ArrayList<>();

        // для каждого набора значений создать и записать Moex в список
        for (ArrayList<Object> listData : data) {
            setFields(listData, titleFieldsAndIndex, filteredList);
        }

        return filteredList;
    }

    private static void setFields(ArrayList<Object> listData, Map<String, Integer> titleFieldsAndIndex,
                                  ArrayList<MoexData> filteredList){
        MoexData moexData = new MoexData();
        moexData.setSecid((String) listData.get(titleFieldsAndIndex.get("secid")));
        moexData.setShortname((String) listData.get(titleFieldsAndIndex.get("shortname")));
        moexData.setRegnumber((String) listData.get(titleFieldsAndIndex.get("regnumber")));
        moexData.setName((String) listData.get(titleFieldsAndIndex.get("name")));
        moexData.setEmitent_title((String) listData.get(titleFieldsAndIndex.get("emitent_title")));
        moexData.setEmitent_inn((String) listData.get(titleFieldsAndIndex.get("emitent_inn")));
        moexData.setEmitent_okpo((String) listData.get(titleFieldsAndIndex.get("emitent_okpo")));

        filteredList.add(moexData);
    }
    private static void setFields2(ArrayList<Object> listData, Map<String, Integer> titleFieldsAndIndex,
                                  ArrayList<MoexData> filteredList){
        MoexData moexData = new MoexData();

        moexData.setSecid(
                listData.get(
                        titleFieldsAndIndex.get("secid")) == null ? "" :
                        (String) listData.get(titleFieldsAndIndex.get("secid")));

        moexData.setShortname(
                listData.get(
                        titleFieldsAndIndex.get("shortname")) == null ? "" :
                        (String) listData.get(titleFieldsAndIndex.get("shortname")));

        moexData.setRegnumber(
                listData.get(
                        titleFieldsAndIndex.get("regnumber")) == null ? "" :
                        (String) listData.get(titleFieldsAndIndex.get("regnumber")));

        moexData.setName(
                listData.get(
                        titleFieldsAndIndex.get("name")) == null ? "" :
                        (String) listData.get(titleFieldsAndIndex.get("name")));

        moexData.setEmitent_title(
                listData.get(
                        titleFieldsAndIndex.get("emitent_title")) == null ? "" :
                        (String) listData.get(titleFieldsAndIndex.get("emitent_title")));

        moexData.setEmitent_inn(
                listData.get(
                        titleFieldsAndIndex.get("emitent_inn")) == null ? "" :
                        (String) listData.get(titleFieldsAndIndex.get("emitent_inn")));

        moexData.setEmitent_okpo(
                listData.get(
                        titleFieldsAndIndex.get("emitent_okpo")) == null ? "" :
                        (String) listData.get(titleFieldsAndIndex.get("emitent_okpo")));

        filteredList.add(moexData);
    }
    // заполняет номерами индексов в значение titleFieldsAndIndex
    private static void setIndexes(ArrayList<String> columns, Map<String, Integer> titleFieldsAndIndex) {

        for (Map.Entry<String, Integer> entry : titleFieldsAndIndex.entrySet()) {
            int index = columns.indexOf(entry.getKey());

            if (index != -1) {
                entry.setValue(index);
            }
        }
    }
}