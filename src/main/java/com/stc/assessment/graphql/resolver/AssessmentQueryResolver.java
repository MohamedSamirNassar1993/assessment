package com.stc.assessment.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.stc.assessment.graphql.filter.ItemFilter;
import com.stc.assessment.model.payload.response.ItemResponse;
import com.stc.assessment.service.AssessmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Slf4j
public class AssessmentQueryResolver implements GraphQLQueryResolver {

    private final AssessmentService assessmentService;

    public ItemResponse getFileMetaData(ItemFilter filter) {

        log.info("<---- GraphQL getAllFilesMetaData --->");
        return assessmentService.fetchFileMetaData(filter);
    }

}
