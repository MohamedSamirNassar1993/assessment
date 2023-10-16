package com.stc.assessment.model.entities;

import com.stc.assessment.common.model.entity.AuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity()
@Table(name = "permission_groups")
@Where(clause = "deleted <> true")
@SQLDelete(sql = "UPDATE {h-schema} permission_groups SET deleted = true WHERE id = ?", check = ResultCheckStyle.COUNT)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionGroup extends AuditBaseEntity {

    @NotNull
    @Column(name = "group_name")
    private String name;

    @OneToMany(targetEntity=Item.class, mappedBy="permissionGroup",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("modifiedDate DESC")
    private List<Item> items;

    @OneToMany(targetEntity=Permission.class, mappedBy="permissionGroup",cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("modifiedDate DESC")
    private List<Permission> permissions;
}
