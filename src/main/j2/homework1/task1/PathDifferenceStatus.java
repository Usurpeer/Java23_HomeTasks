package j2.homework1.task1;

public enum PathDifferenceStatus {
    NotExists("хотя бы один из двух путей ведет к несуществующему файлу/каталогу"),

    SameFIle("Оба пути ведут к одному файлу"),

    BiggerFile("оба пути ведут к файлу, при этом размер path1 > path2"),

    SmallerFile("оба пути ведут к файлу, при этом размер path1 < path2"),

    SameSizeFile("оба пути ведут к файлу, при этом размеры файлов совпадают"),
    SameDirectory("оба пути ведут к одной директории"),
    SameAbsoluteNameDepth("глубина (число частей) абсолютного пути к файлам одинаковая"),
    SamePrefix("пути имеют общее начало (не считая корня)"),
    SameRoot("пути имеют общий корень"),
    SubPath("path2 находится внутри path1"),
    ParentPath("path1 находится внутри path2");


    private final String description;

    PathDifferenceStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
