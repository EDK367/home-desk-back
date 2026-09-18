package com.solairy.homedesk.reports.service;

import com.solairy.homedesk.common.response.PageResponse;
import com.solairy.homedesk.reports.views.VwProductsLowestStock;
import com.solairy.homedesk.reports.views.VwProductsMostFrequentlyPurchased;
import com.solairy.homedesk.reports.views.VwProductsMostInventoryMovement;
import com.solairy.homedesk.reports.views.VwTop10CustomersBySale;
import com.solairy.homedesk.reports.views.VwTop10ProductsByRevenue;
import com.solairy.homedesk.reports.views.VwTop10ProductsSold;
import com.solairy.homedesk.reports.views.VwTop5SuppliersByPurchase;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReportService {

    List<VwTop10ProductsSold> findTop10ProductsSold();

    List<VwTop10ProductsByRevenue> findTop10ProductsByRevenue();

    List<VwTop10CustomersBySale> findTop10CustomersBySale();

    List<VwTop5SuppliersByPurchase> findTop5SuppliersByPurchase();

    PageResponse<VwProductsLowestStock> findProductsLowestStock(Pageable pageable);

    PageResponse<VwProductsMostFrequentlyPurchased> findProductsMostFrequentlyPurchased(Pageable pageable);

    PageResponse<VwProductsMostInventoryMovement> findProductsMostInventoryMovements(Pageable pageable);
}
