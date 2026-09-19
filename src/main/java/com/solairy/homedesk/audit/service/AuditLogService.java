package com.solairy.homedesk.audit.service;

import com.solairy.homedesk.audit.dto.request.AuditLogRequest;
import com.solairy.homedesk.audit.dto.response.AuditLogResponse;
import com.solairy.homedesk.auth.model.User;
import com.solairy.homedesk.common.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface AuditLogService {

    PageResponse<AuditLogResponse> findAll(Pageable pageable);

    AuditLogResponse log(AuditLogRequest request);

    void log(String action, String module, String entityName, String entityId, String description);

    void log(User user, String action, String module, String entityName, String entityId, String description);
}
