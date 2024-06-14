package id.co.com.transfer_system.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class BaseDomain implements Serializable {

    private static final long serialVersionUID = -8951512887557038928L;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "CREATED_BY", length = 50, nullable = true)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "MODIFICATION_DATE", nullable = true)
    private Date modificationDate;

    @Column(name = "MODIFICATION_BY", length = 50, nullable = true)
    private String modificationBy;

    @Column(name = "IS_DELETED", length = 1, nullable = false, columnDefinition = "integer default 0")
    private Integer isDeleted;
}
