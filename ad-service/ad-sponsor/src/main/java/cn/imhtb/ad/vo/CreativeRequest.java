package cn.imhtb.ad.vo;

import cn.imhtb.ad.constant.CommonStatus;
import cn.imhtb.ad.entity.Creative;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreativeRequest {

    private String name;
    private Integer type;
    private Integer materialType;
    private Integer height;
    private Integer width;
    private Long size;
    private Integer duration;
    private Long userId;
    private String url;

    /**
     * 将request转换成实体类，省着service去转化
     * @return
     */
    public Creative convertToEntity() {

        Creative creative = new Creative();
        creative.setName(name);
        creative.setType(type);
        creative.setMaterialType(materialType);
        creative.setHeight(height);
        creative.setWidth(width);
        creative.setSize(size);
        creative.setDuration(duration);
        creative.setAuditStatus(CommonStatus.VALID.getStatus());
        creative.setUserId(userId);
        creative.setUrl(url);
        creative.setCreateTime(new Date());
        creative.setUpdateTime(creative.getCreateTime());

        return creative;
    }
}