package id.co.com.transfer_system.domain.entity;

import jakarta.persistence.*;

public class SystemParam extends BaseDomain{

    @Id
    @SequenceGenerator(name = "SEQ_SYSCONFIG_IDX", sequenceName = "SEQ_SYSCONFIG_IDX")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SEQ_SYSCONFIG_IDX")
    @Column(name = "ID")
    private Long id;

    @Column(name = "KEY", unique = true, nullable = false)
    private String key;

    @Column(name = "VALUE", length = 4000, nullable = false)
    private String value;

    @Column(name = "MODULE", nullable = true)
    private String module;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    public SystemParam() {
    }
}
