package cnn.imhtb.ad.entity;

import cnn.imhtb.ad.constant.CommonStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author PinTeh
 * @date 2019/7/26
 */
@Data
@Entity
@Table(name = "ad_user")
public class AdUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "token",nullable = false)
    private String token;

    @Column(name = "user_status",nullable = false)
    private Integer userStatus;

    @Column(name = "create_time",nullable = false)
    private Date createTime;

    @Column(name = "update_time",nullable = false)
    private Date updateTime;

    public AdUser(String username, String token) {

        this.username = username;
        this.token = token;
        this.userStatus = CommonStatus.VALID.getStatus();
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }

}
