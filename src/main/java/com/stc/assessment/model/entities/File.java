package com.stc.assessment.model.entities;

import com.stc.assessment.common.model.entity.AuditBaseEntity;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity()
@Table(name = "file")
@Where(clause = "deleted <> true")
@SQLDelete(sql = "UPDATE {h-schema} file SET deleted = true WHERE id = ?", check = ResultCheckStyle.COUNT)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File extends AuditBaseEntity {

    @Lob
    @Column(name = "file_binary")
    private byte[] fileBinary;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
