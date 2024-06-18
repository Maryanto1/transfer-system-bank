package id.co.com.transfer_system.constant;

public enum TransferStatusEnum {
    SUCCESS("Sukses"),
    PENDING("Dalam Proses"),
    REJECT("Ditolak");

    private String status;
    TransferStatusEnum(String status){
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
