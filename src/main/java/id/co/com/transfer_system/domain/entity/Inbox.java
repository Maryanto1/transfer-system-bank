package id.co.com.transfer_system.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "T_INBOX")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inbox extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_PROFILE_ID", nullable = false)
    private Long userProfileId;

    @Column(name = "CHANNEL_CODE", nullable = false)
    private String channelCode;

    @Column(name = "TRANSACTION_GROUP")
    private String transactionGroup;

    @Column(name = "TRANSACTION_ID", nullable = false)
    private String transactionId;

    @Column(name = "TRANSACTION_DESCRIPTION")
    private String transactionDescription;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TRANSACTION_DATETIME", nullable = false)
    private Date transactionDateTime;

    @Column(name = "TRANSACTION_STATUS", nullable = true)
    private int transactionStatus;

    @Column(name = "TRANSACTION_CURRENCY")
    private String transactionCurrency;

    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;

    @Column(name = "TRANSACTION_AMOUNT_EQUIVALENT")
    private BigDecimal transactionAmountEquivalent;

    @Column(name = "FEE_AMOUNT")
    private BigDecimal feeAmount;

    @Column(name = "ESB_ERROR_CODE")
    private String esbErrorCode;

    @Column(name = "SRC_SYSTEM_ERROR_CODE")
    private String srcSystemErrorCode;

    @Column(name = "ERROR_DESC")
    private String errorDesc;

    @Column(name = "SRC_ACCOUNT_NO")
    private String srcAccountNo;

    @Column(name = "SRC_ACCOUNT_TYPE")
    private String srcAccountType;

    @Column(name = "SRC_ACCOUNT_OWNER_NAME")
    private String srcAccountOwnerName;

    @Column(name = "SRC_ACCOUNT_CURRENCY")
    private String srcAccountCurrency;

    @Column(name = "SRC_AMOUNT")
    private BigDecimal srcAmount;

    @Column(name = "SRC_AMOUNT_EQUIVALENT")
    private BigDecimal srcAmountEquivalent;

    @Column(name = "SRC_BANK_CODE")
    private String srcBankCode;

    @Column(name = "SRC_BANK_NAME")
    private String srcBankName;

    @Column(name = "DST_ACCOUNT_NO")
    private String dstAccountNo;

    @Column(name = "DST_ACCOUNT_OWNER_NAME")
    private String dstAccountOwnerName;

    @Column(name = "DST_ACCOUNT_CURRENCY")
    private String dstAccountCurrency;

    @Column(name = "DST_AMOUNT")
    private BigDecimal dstAmount;

    @Column(name = "DST_AMOUNT_EQUIVALENT")
    private BigDecimal dstAmountEquivalent;

    @Column(name = "DST_BANK_CODE")
    private String dstBankCode;

    @Column(name = "DST_BANK_NAME")
    private String dstBankName;

    @Column(name = "BP_CUSTOMER_NAME")
    private String bpCustomerName;

    @Column(name = "TRAN_ADDITIONAL_DATA1", length = 20000)
    private String tranAdditionalData1;

    @Column(name = "TRAN_ADDITIONAL_DATA2", length = 20000)
    private String tranAdditionalData2;

    @Column(name = "TRAN_ADDITIONAL_DATA3", length = 20000)
    private String tranAdditionalData3;

    @Column(name = "RECEIPT_ITEM" ,length = 4000)
    private String receiptItem;

    @Column(name = "LONGITUDE" )
    private String longitude;

    @Column(name = "LATITUDE" )
    private String latitude;

}
