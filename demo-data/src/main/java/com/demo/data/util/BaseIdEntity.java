package com.demo.data.util;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author bill
 */
@MappedSuperclass
@Data
public abstract class BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id",length = 64, nullable = false,unique=true)
    protected String id = BaseIDUtils.randomUUID();

}
