package com.gedoumi.crowd.api.dataobj.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@DynamicInsert
@DynamicUpdate
@Data
public class UserInvite {

    /**
     * 注册者用户ID
     */
    @Id
    private Long registerId;

    /**
     * 邀请人用户ID
     */
    private Long inviterId;

}
