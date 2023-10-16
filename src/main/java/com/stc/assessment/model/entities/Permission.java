package com.stc.assessment.model.entities;

import com.stc.assessment.common.model.entity.AuditBaseEntity;
import com.stc.assessment.model.enums.PermissionLevelEnum;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity()
@Table(name = "permission")
@Where(clause = "deleted <> true")
@SQLDelete(sql = "UPDATE {h-schema} permission SET deleted = true WHERE id = ?", check = ResultCheckStyle.COUNT)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission extends AuditBaseEntity {

    @NotNull
    @Column(name = "user_email")
    private String userEmail;

    @NotNull
    @Column(name = "permission_level")
    @Enumerated(value = EnumType.STRING)
    private PermissionLevelEnum permissionLevel;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private PermissionGroup permissionGroup;

}
