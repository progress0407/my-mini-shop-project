package swcho.mini.mvc.domain.item;

public enum ItemType {

    NOTEBOOK("노트북"), KEYBOARD("키보드"), ETC("기타");

    private String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
