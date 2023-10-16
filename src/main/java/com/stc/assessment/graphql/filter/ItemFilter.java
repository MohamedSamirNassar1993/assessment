package com.stc.assessment.graphql.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemFilter {

    private int offset;
    private int limit;
    private String email;
    private Integer id;
}
