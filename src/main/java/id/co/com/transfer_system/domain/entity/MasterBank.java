package id.co.com.transfer_system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MASTER_BANK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterBank extends BaseDomain{

    @Id
    @SequenceGenerator(name = "SEQ_MASTER_BANK_ID", sequenceName = "SEQ_MASTER_BANK_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_MASTER_BANK_ID")
    @Column(name = "ID")
    private Long id;

    @Column(length = 100, name = "BANK_NAME")
    private String bankName;

    @Column(name = "BANK_CODE")
    private String bankCode;

    @Column(name = "ORDERING", length = 3, nullable = false)
    @JsonIgnore
    private Integer ordering;

    @Column(name = "BIFAST")
    private Boolean isBifast = false; // bifast -> true

    @Column(name = "BIFAST_CODE")
    private String bifastCode;

}
