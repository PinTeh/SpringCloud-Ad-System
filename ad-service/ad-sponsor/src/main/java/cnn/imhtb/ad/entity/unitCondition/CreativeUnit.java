package cnn.imhtb.ad.entity.unitCondition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "creative_unit")
public class CreativeUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "creative_id", nullable = false)
    private Long creativeId;

    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    public CreativeUnit(Long creativeId, Long unitId) {
        this.creativeId = creativeId;
        this.unitId = unitId;
    }
}