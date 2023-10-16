package com.stc.assessment.model.entities;

import com.stc.assessment.common.model.entity.AuditBaseEntity;
import com.stc.assessment.model.enums.ItemTypeEnum;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity()
@Table(name = "item")
@Where(clause = "deleted <> true")
@SQLDelete(sql = "UPDATE {h-schema} item SET deleted = true WHERE id = ?", check = ResultCheckStyle.COUNT)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item extends AuditBaseEntity {

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private ItemTypeEnum type;

    @Column(name = "name")
    private String name;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_group_id")
    private PermissionGroup permissionGroup;

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private File file;
}
