package cn.imhtb.ad.entity;

import cn.imhtb.ad.constant.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ad_unit")
public class AdUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "plan_id", nullable = false)
    private Long planId;

    @Basic
    @Column(name = "unit_name", nullable = false)
    private String unitName;

    @Basic
    @Column(name = "unit_status", nullable = false)
    private Integer unitStatus;

    /**
     * 广告位类型(开屏（应用启动）, 贴片（电影开头）, 中贴(影片中间)...)
     */
    @Basic
    @Column(name = "position_type", nullable = false)
    private Integer positionType;

    @Basic
    @Column(name = "budget", nullable = false)
    private Long budget;

    @Basic
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Basic
    @Column(name = "update_time", nullable = false)
    private Date updateTime;

    public AdUnit(Long planId, String unitName,
                  Integer positionType, Long budget) {
        this.planId = planId;
        this.unitName = unitName;
        this.unitStatus = CommonStatus.VALID.getStatus();
        this.positionType = positionType;
        this.budget = budget;
        this.createTime = new Date();
        this.updateTime = this.createTime;
    }
}
