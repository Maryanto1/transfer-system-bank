package id.co.com.transfer_system.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_CARD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCard {

    @Id
    @SequenceGenerator(name = "SEQ_User_Card_IDX", sequenceName = "SEQ_User_Card_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_User_Card_IDX")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NO_CARD")
    private String noCard;

    //    @NotNull
    @Column(name = "CARD_TYPE")
    private String cardType;

}
