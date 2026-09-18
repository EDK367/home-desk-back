-- ======================================================================
-- REPORTES
-- ======================================================================

-- ======================================================================
-- 1. REPORTES DE PRODUCTOS E INVENTARIO
-- ======================================================================

-- ======================================================================
-- 1.1 Top 10 productos más vendidos
-- ======================================================================
CREATE OR REPLACE VIEW vw_top_10_products_sold AS
SELECT
    p.id AS product_id,
    p.sku,
    p.name AS product,
    c.name AS category,
    p.sale_price,
    COALESCE(SUM(si.quantity), 0) AS total_quantity_sold,
    COALESCE(SUM(si.subtotal), 0)::NUMERIC(14,2) AS total_revenue
FROM products p
    INNER JOIN categories c
    ON c.id = p.category_id
    INNER JOIN sale_items si
    ON si.product_id = p.id
    INNER JOIN sales s
    ON s.id = si.sale_id
GROUP BY
    p.id,
    p.sku,
    p.name,
    c.name,
    p.sale_price
ORDER BY
    total_quantity_sold DESC,
    total_revenue DESC
LIMIT 10;


-- ======================================================================
-- 1.2 Productos con menor existencia disponible
-- ======================================================================
CREATE OR REPLACE VIEW vw_products_lowest_stock AS
SELECT
    p.id AS product_id,
    p.sku,
    p.name AS product,
    c.name AS category,
    i.quantity_on_hand AS quantity_on_hand,
    p.min_stock_level
FROM products p
    INNER JOIN categories c
    ON c.id = p.category_id
    LEFT JOIN inventory i
    ON i.product_id = p.id
WHERE p.is_active = TRUE
ORDER BY
    quantity_on_hand ASC,
    p.name ASC;


-- ======================================================================
-- 1.3 Productos con mayor cantidad de movimientos
-- ======================================================================--
CREATE OR REPLACE VIEW vw_products_most_inventory_movements AS
SELECT
    p.id AS product_id,
    p.sku,
    p.name AS product,
    c.name AS category,
    COUNT(im.id) AS movement_count
FROM products p
    INNER JOIN categories c on c.id = p.category_id
    LEFT JOIN inventory_movements im
    ON im.product_id = p.id
    LEFT JOIN inventory i
    ON i.product_id = p.id
GROUP BY
    p.id,
    p.sku,
    p.name,
    c.name,
    i.quantity_on_hand
ORDER BY
    movement_count DESC;


-- ======================================================================
-- 2. REPORTES DE COMPRAS Y PROVEEDORES
-- ======================================================================

-- ======================================================================
-- 2.2 Top 5 proveedores con mayor monto acumulado de compras
-- ======================================================================
CREATE OR REPLACE VIEW vw_top_5_suppliers_by_purchases AS
SELECT
    s.id AS supplier_id,
    s.name AS supplier_name,
    COUNT(p.id) AS purchase_count,
    SUM(p.total_amount) AS total_purchase_amount,
    MAX(p.purchase_date) AS last_purchase_date
FROM suppliers s
    INNER JOIN purchases p
    ON p.supplier_id = s.id
GROUP BY
    s.id,
    s.name
ORDER BY
    total_purchase_amount DESC,
    purchase_count DESC
LIMIT 5;


-- ======================================================================
-- 2.3 Productos adquiridos con mayor frecuencia
-- ======================================================================
CREATE OR REPLACE VIEW vw_products_most_frequently_purchased AS
SELECT
    p.id AS product_id,
    p.sku,
    p.name AS product,
    c.name AS category,
    COUNT(pi.purchase_id) AS purchase_count,
    SUM(pi.quantity) AS total_quantity_purchased,
    SUM(pi.subtotal) AS total_purchase_amount
FROM products p
    INNER JOIN categories c
    ON c.id = p.category_id
    INNER JOIN purchase_items pi
    ON pi.product_id = p.id
    INNER JOIN purchases pu
    ON pu.id = pi.purchase_id
GROUP BY
    p.id,
    p.sku,
    p.name,
    c.name
ORDER BY
    purchase_count DESC,
    total_quantity_purchased DESC
;


-- ======================================================================
-- 3. REPORTES DE VENTAS Y CLIENTES
-- ======================================================================

-- ======================================================================
-- 3.2 Top 10 clientes con mayor monto acumulado de compras
-- ======================================================================
CREATE OR REPLACE VIEW vw_top_10_customers_by_sales AS
SELECT
    c.id AS id,
    c.full_name AS customer_name,
    COUNT(s.customer_id) AS sale_count,
    SUM(s.total_amount) AS total_purchase_amount,
    MAX(s.sale_date) AS last_purchase_date
FROM customers c
    INNER JOIN sales s
    ON s.customer_id = c.id
GROUP BY
    c.id,
    c.full_name
ORDER BY
    total_purchase_amount DESC,
    sale_count DESC
LIMIT 10;

-- ======================================================================
-- 3.3 Top 10 productos que han generado mayores ingresos
-- ======================================================================
CREATE OR REPLACE VIEW vw_top_10_products_by_revenue AS
SELECT
    p.id AS product_id,
    p.sku,
    p.name AS product_name,
    c.name AS category_name,
    SUM(si.quantity) AS total_quantity_sold,
    SUM(si.subtotal) AS total_revenue
FROM products p
    INNER JOIN categories c
    ON c.id = p.category_id
    INNER JOIN sale_items si
    ON si.product_id = p.id
    INNER JOIN sales s
    ON s.id = si.sale_id
GROUP BY
    p.id,
    p.sku,
    p.name,
    c.name
ORDER BY
    total_revenue DESC,
    total_quantity_sold DESC
LIMIT 10;
