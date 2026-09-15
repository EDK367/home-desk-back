package com.solairy.homedesk.purchases.service.impl;

import com.solairy.homedesk.common.exception.GlobalException;
import com.solairy.homedesk.purchases.dto.response.PurchaseItemResponse;
import com.solairy.homedesk.purchases.mapper.PurchaseItemMapper;
import com.solairy.homedesk.purchases.model.PurchaseItem;
import com.solairy.homedesk.purchases.repository.PurchaseItemRepository;
import com.solairy.homedesk.purchases.service.PurchaseItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseItemServiceImpl implements PurchaseItemService {

    private final PurchaseItemRepository repository;
    private final PurchaseItemMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<PurchaseItemResponse> findByPurchaseId(Long purchaseId) {
        return repository.findByPurchaseId(purchaseId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PurchaseItemResponse findById(Long id) {
        PurchaseItem item = repository.findById(id)
                .orElseThrow(() -> new GlobalException(HttpStatus.NOT_FOUND, "Ítem de compra no encontrado con id: " + id));
        return mapper.toResponse(item);
    }
}
