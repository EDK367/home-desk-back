package com.solairy.homedesk.audit.service.impl;

import com.solairy.homedesk.audit.repository.AuditLogRepository;
import com.solairy.homedesk.audit.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {

    private final AuditLogRepository auditLogRepository;

}
