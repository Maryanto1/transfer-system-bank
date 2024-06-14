package id.co.com.transfer_system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Table(name = "USER_AUTHENTICATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthentication extends BaseDomain{

    @Id
    @SequenceGenerator(name = "SEQ_USER_AUTHENTICATION_IDX", sequenceName = "SEQ_USER_AUTHENTICATION_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_USER_AUTHENTICATION_IDX")
    @Column(name = "ID")
    private Long id;

    //    @NotNull
    @Column(name = "USERNAME", unique = true)
    private String username;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "MPIN")
    private String mpin;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_PROFILE_ID", referencedColumnName = "ID")
    private UserProfile userProfile;

}
