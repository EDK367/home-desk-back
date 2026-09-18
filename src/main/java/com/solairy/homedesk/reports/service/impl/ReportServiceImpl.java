package com.solairy.homedesk.reports.service.impl;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.reports.repository.VwProductsLowestStockRepository;
import com.solairy.homedesk.reports.repository.VwProductsMostFrequentlyPurchasedRepository;
import com.solairy.homedesk.reports.repository.VwProductsMostInventoryMovementRepository;
import com.solairy.homedesk.reports.repository.VwTop10CustomersBySaleRepository;
import com.solairy.homedesk.reports.repository.VwTop10ProductsByRevenueRepository;
import com.solairy.homedesk.reports.repository.VwTop10ProductsSoldRepository;
import com.solairy.homedesk.reports.repository.VwTop5SuppliersByPurchaseRepository;
import com.solairy.homedesk.reports.service.ReportService;
import com.solairy.homedesk.reports.views.VwProductsLowestStock;
import com.solairy.homedesk.reports.views.VwProductsMostFrequentlyPurchased;
import com.solairy.homedesk.reports.views.VwProductsMostInventoryMovement;
import com.solairy.homedesk.reports.views.VwTop10CustomersBySale;
import com.solairy.homedesk.reports.views.VwTop10ProductsByRevenue;
import com.solairy.homedesk.reports.views.VwTop10ProductsSold;
import com.solairy.homedesk.reports.views.VwTop5SuppliersByPurchase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final VwTop10ProductsSoldRepository top10ProductsSoldRepository;
    private final VwTop10ProductsByRevenueRepository top10ProductsByRevenueRepository;
    private final VwTop10CustomersBySaleRepository top10CustomersBySaleRepository;
    private final VwTop5SuppliersByPurchaseRepository top5SuppliersByPurchaseRepository;
    private final VwProductsLowestStockRepository productsLowestStockRepository;
    private final VwProductsMostFrequentlyPurchasedRepository productsMostFrequentlyPurchasedRepository;
    private final VwProductsMostInventoryMovementRepository productsMostInventoryMovementRepository;

    @Override
    @Transactional(readOnly = true)
    public List<VwTop10ProductsSold> findTop10ProductsSold() {
        return top10ProductsSoldRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VwTop10ProductsByRevenue> findTop10ProductsByRevenue() {
        return top10ProductsByRevenueRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VwTop10CustomersBySale> findTop10CustomersBySale() {
        return top10CustomersBySaleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VwTop5SuppliersByPurchase> findTop5SuppliersByPurchase() {
        return top5SuppliersByPurchaseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<VwProductsLowestStock> findProductsLowestStock(Pageable pageable) {
        Page<VwProductsLowestStock> page = productsLowestStockRepository.findAll(pageable);
        return PageResponse.from(page);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<VwProductsMostFrequentlyPurchased> findProductsMostFrequentlyPurchased(Pageable pageable) {
        Page<VwProductsMostFrequentlyPurchased> page = productsMostFrequentlyPurchasedRepository.findAll(pageable);
        return PageResponse.from(page);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<VwProductsMostInventoryMovement> findProductsMostInventoryMovements(Pageable pageable) {
        Page<VwProductsMostInventoryMovement> page = productsMostInventoryMovementRepository.findAll(pageable);
        return PageResponse.from(page);
    }
}
