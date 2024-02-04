package j2.homework3.task1;

public class MoexData {
    private String secid;
    private String shortname;
    private String regnumber;
    private String name;
    private String emitent_title;
    private String emitent_inn;
    private String emitent_okpo;

    public MoexData() {
    }

    public MoexData(String secid, String shortname, String regnumber, String name, String emitent_title, String emitent_inn,
                    String emitent_okpo) {
        this.secid = secid;
        this.shortname = shortname;
        this.regnumber = regnumber;
        this.name = name;
        this.emitent_title = emitent_title;
        this.emitent_inn = emitent_inn;
        this.emitent_okpo = emitent_okpo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmitent_inn() {
        return emitent_inn;
    }

    public void setEmitent_inn(String emitent_inn) {
        this.emitent_inn = emitent_inn;
    }

    public String getEmitent_okpo() {
        return emitent_okpo;
    }

    public void setEmitent_okpo(String emitent_okpo) {
        this.emitent_okpo = emitent_okpo;
    }

    public String getEmitent_title() {
        return emitent_title;
    }

    public void setEmitent_title(String emitent_title) {
        this.emitent_title = emitent_title;
    }

    public String getRegnumber() {
        return regnumber;
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    @Override
    public String toString() {
        return "Moex{" +
                "secid='" + secid + '\'' +
                ", shortname='" + shortname + '\'' +
                ", regnumber='" + regnumber + '\'' +
                ", name='" + name + '\'' +
                ", emitent_title='" + emitent_title + '\'' +
                ", emitent_inn='" + emitent_inn + '\'' +
                ", emitent_okpo='" + emitent_okpo + '\'' +
                '}';
    }
}
