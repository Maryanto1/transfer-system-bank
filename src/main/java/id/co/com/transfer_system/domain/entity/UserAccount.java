package id.co.com.transfer_system.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_ACCOUNT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

    @Id
    @SequenceGenerator(name = "SEQ_User_Account_Visibility_IDX", sequenceName = "SEQ_User_Account_Visibility_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_User_Account_Visibility_IDX")
    @Column(name = "ID")
    private Long id;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne()
    @JoinColumn(name = "USER_PROFILE_ID", referencedColumnName = "ID")
    private UserProfile userProfile;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne()
    @JoinColumn(name = "USER_CARD_ID", referencedColumnName = "ID")
    private UserCard userCard;

    @Column(name = "ACCOUNT_NUMBER", unique = true)
    private String accountNumber;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "ACCOUNT_STATUS")
    private String accountStatus;

    @Column(name = "ESB_SOF_FLAG")
    private String esbSofFlag;

    @Column(name = "ESB_CHANNEL_INQ_FLAG")
    private String esbChannelInqFlag;

    @Column(name = "IS_SHARIAH")
    private Integer isShariah = 0; //0 : not shariah (konven)

    @Column(name = "DEFAULT_SOF_FLAG")
    private Integer defaultSofFlag = 1;

    @Column(name = "ALLOWED_SOF_FLAG")
    private Integer allowedSofFlag = 1;

    @Column(name = "SHOW_SOF_FLAG")
    private Integer showSofFlag = 1;

}
