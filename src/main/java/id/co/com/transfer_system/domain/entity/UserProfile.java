package id.co.com.transfer_system.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import java.util.Date;

@Entity
@Table(name = "MASTER_USER_PROFILE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile extends BaseDomain{

    @Id
    @SequenceGenerator(name = "SEQ_USER_PROFILE_IDX", sequenceName = "SEQ_USER_PROFILE_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_USER_PROFILE_IDX")
    @Column(name = "ID")
    private Long id;

    //    @NotNull
    @Column(length = 19, name = "CIF")
    private String cif;

    //    @NotNull
    @Column(length = 50, name = "USER_ALIAS", unique = true)
    private String userAlias;

    @Column(length = 30, name = "MOBILE_PHONE")
    private String mobilePhone;

    @Column(length = 50, name = "EMAIL")
    private String email;

    @Column(name = "DATE_LAST_SUCCESS_LOGIN")
    private Date dateLastSuccessLogin;

    @Column(name = "DATE_LAST_FAILED_LOGIN")
    private Date dateLastFailedLogin;

    @Column(name = "DATE_LAST_FAILED_MPIN")
    private Date dateLastFailedMpin;

    @Column(name = "DATE_LAST_FAILED_OTP")
    private Date dateLastFailedOtp;

    @NotNull
    @Column(name = "NUM_FAILED_LOGIN")
    private int numberFailedLogin;

    @NotNull
    @Column(name = "NUM_FAILED_OTP")
    private int numFailedOtp;

    @Column(name = "NUM_FAILED_PIN")
    private Integer numInvalidMPin;

    @NotNull
    @Column(name = "STATUS")
    private UserStatusEnum status;

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private UserInformation userInfo;

    @OneToOne(mappedBy = "userProfile", cascade = CascadeType.ALL)
    private UserAuthentication userAuthentication;
}
