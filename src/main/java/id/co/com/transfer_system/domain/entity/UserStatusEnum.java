package id.co.com.transfer_system.domain.entity;

public enum UserStatusEnum {
    ACTIVE("active"),
    BLOCKED("blocked"),
    SUSPECT("suspect"),
    DELETED("deleted"),
    EXPIRED("expired");

    private final String key;

    UserStatusEnum(String key){
        this.key = key;
    }

    public String getKey(){
        return this.key;
    }

}
