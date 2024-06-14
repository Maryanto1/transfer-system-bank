package id.co.com.transfer_system.domain.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "USER_INFORMATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInformation {

    @Id
    @SequenceGenerator(name = "SEQ_USER_INFORMATION_IDX", sequenceName = "SEQ_USER_INFORMATION_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_USER_INFORMATION_IDX")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME_FULL")
    private String nameFull;

    @Column(length = 50, name = "NAME_SHORT")
    private String nameShort;

    @Column(length = 1, name = "GENDER")
    private String gender;

    @Column(length = 1, name = "MARITAL_STATUS")
    private String maritalStatus;

    @Column(length = 50, name = "PLACE_OF_BIRTH")
    private String placeOfBirth;

    @Column(length = 100, name = "ADDRESS")
    private String address;

    @Column(length = 100, name = "MOTHER_NAME")
    private String motherName;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(length = 50, name = "CITIZENSHIP")
    private String citizenship;

    @Column(length = 3, name = "RESIDENT_TYPE")
    private String residentType;

    @Column(length = 50, name = "REFERRAL_ID")
    private String referralId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_PROFILE_ID", referencedColumnName = "ID")
    private UserProfile userProfile;

}
