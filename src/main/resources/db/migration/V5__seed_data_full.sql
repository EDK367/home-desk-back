-- ======================================================================
-- Datos iniciales completos para HomeDesk
-- ======================================================================

-- ======================================================================
-- 1. CATEGORIAS
-- ======================================================================
INSERT INTO categories (name, description, is_active, created_at, updated_at)
VALUES
    ('Escritorios', 'Escritorios ergonómicos, de altura regulable y mesas ejecutivas para oficina y estudio', true, NOW(), NOW()),
    ('Sillas de Oficina', 'Sillas ergonómicas, ejecutivas y operativas diseñadas para soporte postural prolongado', true, NOW(), NOW()),
    ('Accesorios Ergonómicos', 'Soportes para monitores, reposapiés, brazos mecánicos y organizadores de espacio', true, NOW(), NOW()),
    ('Iluminación de Escritorio', 'Lámparas LED con regulación de brillo, temperatura de luz y carga inalámbrica', true, NOW(), NOW()),
    ('Almacenamiento y Archivo', 'Cajoneras rodantes, estanterías modulares y libreros para organización documental', true, NOW(), NOW()),
    ('Organización de Cables', 'Soluciones integrales de canaletas, pasacables y gestión eléctrica bajo mesa', true, NOW(), NOW())
ON CONFLICT (name) DO NOTHING;

-- ======================================================================
-- 2. PRODUCTOS
-- ======================================================================
INSERT INTO products (sku, name, description, category_id, unit_of_measure, sale_price, min_stock_level, is_active, created_at, updated_at)
VALUES
    (
        'PROD-ESC-001',
        'Escritorio Eléctrico Elevable StandDesk Pro 160x80',
        'Escritorio de altura ajustable con motor dual silencioso, estructura de acero reforzado y memoria programable de 4 alturas',
        (SELECT id FROM categories WHERE name = 'Escritorios'),
        'UNIT',
        3499.00,
        5,
        true,
        NOW(),
        NOW()
    ),
    (
        'PROD-ESC-002',
        'Escritorio Compacto de Madera Nogal 120x60',
        'Mesa de trabajo minimalista elaborada en melamina de alta densidad con acabado texturizado de nogal y patas metálicas',
        (SELECT id FROM categories WHERE name = 'Escritorios'),
        'UNIT',
        1850.00,
        4,
        true,
        NOW(),
        NOW()
    ),
    (
        'PROD-SIL-001',
        'Silla Ergonómica Ejecutiva Titan Mesh',
        'Silla de oficina de alto rendimiento con respaldo de malla transpirable, soporte lumbar 3D y descansabrazos 4D',
        (SELECT id FROM categories WHERE name = 'Sillas de Oficina'),
        'UNIT',
        2200.00,
        6,
        true,
        NOW(),
        NOW()
    ),
    (
        'PROD-SIL-002',
        'Silla Giratoria Acolchada Confort Plus',
        'Silla ergonómica operativa con tapizado en tela de alta durabilidad, espuma de inyección viscoelástica y pistón clase 4',
        (SELECT id FROM categories WHERE name = 'Sillas de Oficina'),
        'UNIT',
        1450.00,
        5,
        true,
        NOW(),
        NOW()
    ),
    (
        'PROD-ACC-001',
        'Brazo Mecánico para Doble Monitor 17-32 Pulgadas',
        'Soporte articulado de aluminio para dos pantallas con sistema de resorte de gas, pasacables integrado y fijación VESA',
        (SELECT id FROM categories WHERE name = 'Accesorios Ergonómicos'),
        'UNIT',
        780.00,
        8,
        true,
        NOW(),
        NOW()
    ),
    (
        'PROD-SET-001',
        'Set de Organización y Guiado de Cables para Escritorio',
        'Juego integral que incluye canaleta bajo cubierta, 10 clips magnéticos, columna vertebrada articulada y bridas de velcro',
        (SELECT id FROM categories WHERE name = 'Organización de Cables'),
        'SET',
        290.00,
        10,
        true,
        NOW(),
        NOW()
    ),
    (
        'PROD-SET-002',
        'Set Ergonómico Reposamuñecas y Tapete XXL',
        'Conjunto ergonómico que contiene tapete de escritorio de cuero sintético resistente al agua y descansamuñecas de espuma viscoelástica',
        (SELECT id FROM categories WHERE name = 'Accesorios Ergonómicos'),
        'SET',
        340.00,
        8,
        true,
        NOW(),
        NOW()
    ),
    (
        'PROD-ILU-001',
        'Barra de Luz LED para Monitor ScreenBar Pro',
        'Lámpara de iluminación asimétrica para monitor que previene el reflejo en pantalla, incluye control dial inalámbrico',
        (SELECT id FROM categories WHERE name = 'Iluminación de Escritorio'),
        'UNIT',
        480.00,
        7,
        true,
        NOW(),
        NOW()
    ),
    (
        'PROD-ALM-001',
        'Cajonera Metálica Rodante con Cerradura Central',
        'Mueble archivero metálico de tres gavetas sobre ruedas reforzadas con freno y sistema de bloqueo general con llave',
        (SELECT id FROM categories WHERE name = 'Almacenamiento y Archivo'),
        'UNIT',
        1250.00,
        4,
        true,
        NOW(),
        NOW()
    ),
    (
        'PROD-SET-003',
        'Set de Limpieza y Mantenimiento de Pantallas y Periféricos',
        'Kit profesional que incluye cepillo retráctil de cerdas suaves, soplador de aire, solución limpiadora antiestática y paño de microfibra',
        (SELECT id FROM categories WHERE name = 'Accesorios Ergonómicos'),
        'SET',
        175.00,
        12,
        true,
        NOW(),
        NOW()
    )
ON CONFLICT (sku) DO NOTHING;

-- ======================================================================
-- 3. NUMEROS
-- ======================================================================
INSERT INTO phone_numbers (phone)
SELECT p.phone
FROM (VALUES
    ('+502 2334-5501'),
    ('+502 2456-7892'),
    ('+502 2210-9983'),
    ('+502 6677-1124'),
    ('+502 2399-4455'),
    ('+502 5544-3326')
) AS p(phone)
WHERE NOT EXISTS (SELECT 1 FROM phone_numbers pn WHERE pn.phone = p.phone);

-- ======================================================================
-- 4. PROVEEDORES
-- ======================================================================
INSERT INTO suppliers (name, tax_number, contact_name, email, address, is_active, created_at, updated_at)
VALUES
    (
        'Mobiliario y Ergonomía de Guatemala S.A.',
        'NIT-9845123-1',
        'Carlos Alberto Morales Soto',
        'contacto@mobiliariogt.com',
        'Calzada Roosevelt 14-25 Zona 11, Ciudad de Guatemala',
        true,
        NOW(),
        NOW()
    ),
    (
        'Distribuidora Tecnológica del Altiplano S.A.',
        'NIT-6523190-4',
        'Andrea Beatriz Fuentes López',
        'ventas@distecnoaltiplano.gt',
        'Avenida Las Américas 8-40 Zona 13, Ciudad de Guatemala',
        true,
        NOW(),
        NOW()
    ),
    (
        'Importaciones y Diseños Corporativos de Centroamérica',
        'NIT-1123489-K',
        'Roberto Gómez Estrada',
        'rgomez@disenoscorp.com.gt',
        '7ma Avenida 12-23 Zona 9, Ciudad de Guatemala',
        true,
        NOW(),
        NOW()
    ),
    (
        'Soluciones Metálicas y Ensambles Industriales S.A.',
        'NIT-4432109-8',
        'Lucía Méndez Castillo',
        'servicioalcliente@metalicasindustriales.gt',
        'Kilómetro 18.5 Carretera a El Salvador, Fraijanes',
        true,
        NOW(),
        NOW()
    ),
    (
        'Accesorios y Ergonomía Profesional del Sur S.A.',
        'NIT-7788990-2',
        'Fernando José Castillo Paz',
        'info@ergonomiaprosur.gt',
        'Boulevard Los Próceres 24-69 Zona 10, Ciudad de Guatemala',
        true,
        NOW(),
        NOW()
    )
ON CONFLICT (tax_number) DO NOTHING;

-- ======================================================================
-- 5. ASOCIACIÓN TELEFONO - PROVEEDOR
-- ======================================================================
INSERT INTO phone_suppliers (supplier_id, phone_id)
VALUES
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-9845123-1'),
        (SELECT id FROM phone_numbers WHERE phone = '+502 2334-5501')
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-6523190-4'),
        (SELECT id FROM phone_numbers WHERE phone = '+502 2456-7892')
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-1123489-K'),
        (SELECT id FROM phone_numbers WHERE phone = '+502 2210-9983')
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-4432109-8'),
        (SELECT id FROM phone_numbers WHERE phone = '+502 6677-1124')
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-7788990-2'),
        (SELECT id FROM phone_numbers WHERE phone = '+502 2399-4455')
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-9845123-1'),
        (SELECT id FROM phone_numbers WHERE phone = '+502 5544-3326')
    )
ON CONFLICT (supplier_id, phone_id) DO NOTHING;

-- ======================================================================
-- 6. PRODUCTOS POR PROVEEDOR
-- ======================================================================
INSERT INTO supplier_products (supplier_id, product_id, supplier_sku, cost, last_purchase_cost, created_at, updated_at)
VALUES
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-9845123-1'),
        (SELECT id FROM products WHERE sku = 'PROD-ESC-001'),
        'SUP1-DESK-01',
        2350.00,
        2350.00,
        NOW(),
        NOW()
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-9845123-1'),
        (SELECT id FROM products WHERE sku = 'PROD-ESC-002'),
        'SUP1-DESK-02',
        1250.00,
        1250.00,
        NOW(),
        NOW()
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-6523190-4'),
        (SELECT id FROM products WHERE sku = 'PROD-SIL-001'),
        'SUP2-CHAIR-01',
        1450.00,
        1450.00,
        NOW(),
        NOW()
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-6523190-4'),
        (SELECT id FROM products WHERE sku = 'PROD-SIL-002'),
        'SUP2-CHAIR-02',
        950.00,
        950.00,
        NOW(),
        NOW()
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-7788990-2'),
        (SELECT id FROM products WHERE sku = 'PROD-ACC-001'),
        'SUP5-ACC-01',
        480.00,
        480.00,
        NOW(),
        NOW()
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-7788990-2'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-001'),
        'SUP5-SET-01',
        160.00,
        160.00,
        NOW(),
        NOW()
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-7788990-2'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-002'),
        'SUP5-SET-02',
        190.00,
        190.00,
        NOW(),
        NOW()
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-7788990-2'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-003'),
        'SUP5-SET-03',
        95.00,
        95.00,
        NOW(),
        NOW()
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-1123489-K'),
        (SELECT id FROM products WHERE sku = 'PROD-ILU-001'),
        'SUP3-ILU-01',
        280.00,
        280.00,
        NOW(),
        NOW()
    ),
    (
        (SELECT id FROM suppliers WHERE tax_number = 'NIT-4432109-8'),
        (SELECT id FROM products WHERE sku = 'PROD-ALM-001'),
        'SUP4-ALM-01',
        820.00,
        820.00,
        NOW(),
        NOW()
    )
ON CONFLICT (supplier_id, product_id) DO NOTHING;

-- ======================================================================
-- 7. ESTADOS DE COMPRA
-- ======================================================================
INSERT INTO purchase_statuses (name, description, is_active, created_at)
VALUES
    ('SOLICITADA', 'Orden de compra solicitada y pendiente de autorización por gerencia', true, NOW()),
    ('APROBADA', 'Orden de compra autorizada y transmitida formalmente al proveedor', true, NOW()),
    ('EN_TRANSITO', 'Mercadería despachada por el proveedor en tránsito a bodega', true, NOW()),
    ('RECIBIDA', 'Mercadería recibida satisfactoriamente en bodega con ingreso a inventario', true, NOW()),
    ('CANCELADA', 'Orden de compra anulada por el departamento de compras o proveedor', true, NOW())
ON CONFLICT (name) DO NOTHING;

-- ======================================================================
-- 8. COMPRAS
-- ======================================================================
INSERT INTO purchases (supplier_id, user_id, status_id, purchase_date, subtotal, tax_amount, total_amount, notes, created_at, updated_at)
SELECT
    s.id,
    u.id,
    ps.id,
    p.purchase_date,
    p.subtotal,
    p.tax_amount,
    p.total_amount,
    p.notes,
    p.purchase_date,
    p.purchase_date
FROM (VALUES
    (
        'NIT-9845123-1',
        'compra',
        'RECIBIDA',
        NOW() - INTERVAL '30 days',
        36000.00::numeric,
        4320.00::numeric,
        40320.00::numeric,
        'Adquisición inicial de escritorios eléctricos y compactos para surtido de tienda'
    ),
    (
        'NIT-6523190-4',
        'compra',
        'RECIBIDA',
        NOW() - INTERVAL '25 days',
        31250.00::numeric,
        3750.00::numeric,
        35000.00::numeric,
        'Lote de sillas ejecutivas de alta gama y operativas ergonómicas'
    ),
    (
        'NIT-7788990-2',
        'compra',
        'RECIBIDA',
        NOW() - INTERVAL '20 days',
        22950.00::numeric,
        2754.00::numeric,
        25704.00::numeric,
        'Reabastecimiento de brazos dobles para monitores y sets ergonómicos'
    ),
    (
        'NIT-1123489-K',
        'compra',
        'RECIBIDA',
        NOW() - INTERVAL '15 days',
        5600.00::numeric,
        672.00::numeric,
        6272.00::numeric,
        'Adquisición de barras de luz LED para monitor con dial inalámbrico'
    ),
    (
        'NIT-4432109-8',
        'compra',
        'RECIBIDA',
        NOW() - INTERVAL '10 days',
        9840.00::numeric,
        1180.80::numeric,
        11020.80::numeric,
        'Recepción de cajoneras rodantes de tres gavetas en acero reforzado'
    )
) AS p(supplier_tax, username, status_name, purchase_date, subtotal, tax_amount, total_amount, notes)
JOIN suppliers s ON s.tax_number = p.supplier_tax
JOIN users u ON u.username = p.username
JOIN purchase_statuses ps ON ps.name = p.status_name
WHERE NOT EXISTS (SELECT 1 FROM purchases pu WHERE pu.notes = p.notes);

-- ======================================================================
-- 9. DETALLE DE COMPRAS
-- ======================================================================
INSERT INTO purchase_items (purchase_id, product_id, quantity, unit_cost, subtotal, created_at)
VALUES
    (
        (SELECT id FROM purchases WHERE notes = 'Adquisición inicial de escritorios eléctricos y compactos para surtido de tienda'),
        (SELECT id FROM products WHERE sku = 'PROD-ESC-001'),
        10,
        2350.00,
        23500.00,
        NOW() - INTERVAL '30 days'
    ),
    (
        (SELECT id FROM purchases WHERE notes = 'Adquisición inicial de escritorios eléctricos y compactos para surtido de tienda'),
        (SELECT id FROM products WHERE sku = 'PROD-ESC-002'),
        10,
        1250.00,
        12500.00,
        NOW() - INTERVAL '30 days'
    ),
    (
        (SELECT id FROM purchases WHERE notes = 'Lote de sillas ejecutivas de alta gama y operativas ergonómicas'),
        (SELECT id FROM products WHERE sku = 'PROD-SIL-001'),
        15,
        1450.00,
        21750.00,
        NOW() - INTERVAL '25 days'
    ),
    (
        (SELECT id FROM purchases WHERE notes = 'Lote de sillas ejecutivas de alta gama y operativas ergonómicas'),
        (SELECT id FROM products WHERE sku = 'PROD-SIL-002'),
        10,
        950.00,
        9500.00,
        NOW() - INTERVAL '25 days'
    ),
    (
        (SELECT id FROM purchases WHERE notes = 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos'),
        (SELECT id FROM products WHERE sku = 'PROD-ACC-001'),
        20,
        480.00,
        9600.00,
        NOW() - INTERVAL '20 days'
    ),
    (
        (SELECT id FROM purchases WHERE notes = 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-001'),
        30,
        160.00,
        4800.00,
        NOW() - INTERVAL '20 days'
    ),
    (
        (SELECT id FROM purchases WHERE notes = 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-002'),
        25,
        190.00,
        4750.00,
        NOW() - INTERVAL '20 days'
    ),
    (
        (SELECT id FROM purchases WHERE notes = 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-003'),
        40,
        95.00,
        3800.00,
        NOW() - INTERVAL '20 days'
    ),
    (
        (SELECT id FROM purchases WHERE notes = 'Adquisición de barras de luz LED para monitor con dial inalámbrico'),
        (SELECT id FROM products WHERE sku = 'PROD-ILU-001'),
        20,
        280.00,
        5600.00,
        NOW() - INTERVAL '15 days'
    ),
    (
        (SELECT id FROM purchases WHERE notes = 'Recepción de cajoneras rodantes de tres gavetas en acero reforzado'),
        (SELECT id FROM products WHERE sku = 'PROD-ALM-001'),
        12,
        820.00,
        9840.00,
        NOW() - INTERVAL '10 days'
    )
ON CONFLICT (purchase_id, product_id) DO NOTHING;

-- ======================================================================
-- 10. LOTES DE INVENTARIO
-- ======================================================================
INSERT INTO inventory_lots (product_id, purchase_item_id, quantity_received, quantity_remaining, unit_cost, received_at, created_at)
SELECT
    pr.id,
    pi.id,
    l.quantity_received,
    l.quantity_remaining,
    l.unit_cost,
    l.received_at,
    l.received_at
FROM (VALUES
    ('PROD-ESC-001', 'Adquisición inicial de escritorios eléctricos y compactos para surtido de tienda', 10, 8, 2350.00::numeric, NOW() - INTERVAL '30 days'),
    ('PROD-ESC-002', 'Adquisición inicial de escritorios eléctricos y compactos para surtido de tienda', 10, 9, 1250.00::numeric, NOW() - INTERVAL '30 days'),
    ('PROD-SIL-001', 'Lote de sillas ejecutivas de alta gama y operativas ergonómicas', 15, 12, 1450.00::numeric, NOW() - INTERVAL '25 days'),
    ('PROD-SIL-002', 'Lote de sillas ejecutivas de alta gama y operativas ergonómicas', 10, 8, 950.00::numeric, NOW() - INTERVAL '25 days'),
    ('PROD-ACC-001', 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos', 20, 16, 480.00::numeric, NOW() - INTERVAL '20 days'),
    ('PROD-SET-001', 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos', 30, 25, 160.00::numeric, NOW() - INTERVAL '20 days'),
    ('PROD-SET-002', 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos', 25, 20, 190.00::numeric, NOW() - INTERVAL '20 days'),
    ('PROD-SET-003', 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos', 40, 35, 95.00::numeric, NOW() - INTERVAL '20 days'),
    ('PROD-ILU-001', 'Adquisición de barras de luz LED para monitor con dial inalámbrico', 20, 17, 280.00::numeric, NOW() - INTERVAL '15 days'),
    ('PROD-ALM-001', 'Recepción de cajoneras rodantes de tres gavetas en acero reforzado', 12, 10, 820.00::numeric, NOW() - INTERVAL '10 days')
) AS l(product_sku, purchase_notes, quantity_received, quantity_remaining, unit_cost, received_at)
JOIN products pr ON pr.sku = l.product_sku
JOIN purchases pu ON pu.notes = l.purchase_notes
JOIN purchase_items pi ON pi.purchase_id = pu.id AND pi.product_id = pr.id
ON CONFLICT (purchase_item_id) DO NOTHING;

-- ======================================================================
-- 11. EXISTENCIAS EN INVENTARIO
-- ======================================================================
INSERT INTO inventory (product_id, quantity_on_hand, updated_at)
SELECT
    pr.id,
    inv.quantity_on_hand,
    NOW()
FROM (VALUES
    ('PROD-ESC-001', 8),
    ('PROD-ESC-002', 9),
    ('PROD-SIL-001', 12),
    ('PROD-SIL-002', 8),
    ('PROD-ACC-001', 16),
    ('PROD-SET-001', 25),
    ('PROD-SET-002', 20),
    ('PROD-SET-003', 35),
    ('PROD-ILU-001', 17),
    ('PROD-ALM-001', 10)
) AS inv(product_sku, quantity_on_hand)
JOIN products pr ON pr.sku = inv.product_sku
ON CONFLICT (product_id) DO UPDATE
SET quantity_on_hand = EXCLUDED.quantity_on_hand,
    updated_at = NOW();

-- ======================================================================
-- 12. MOVIMIENTOS DE INVENTARIO (INVENTORY MOVEMENTS)
-- ======================================================================
INSERT INTO inventory_movements (product_id, user_id, movement_type, quantity, balance_after, reference_type, reference_id, costing_method, notes, created_at)
SELECT
    pr.id,
    u.id,
    m.movement_type,
    m.quantity,
    m.balance_after,
    m.reference_type,
    pu.id,
    m.costing_method,
    m.notes,
    m.created_at
FROM (VALUES
    ('PROD-ESC-001', 'inventory', 'ENTRADA_COMPRA', 10, 10, 'PURCHASE', 'Adquisición inicial de escritorios eléctricos y compactos para surtido de tienda', 'PEPS', 'Ingreso inicial por recepción de compra #1', NOW() - INTERVAL '30 days'),
    ('PROD-SIL-001', 'inventory', 'ENTRADA_COMPRA', 15, 15, 'PURCHASE', 'Lote de sillas ejecutivas de alta gama y operativas ergonómicas', 'PEPS', 'Ingreso inicial por recepción de compra #2', NOW() - INTERVAL '25 days'),
    ('PROD-ACC-001', 'inventory', 'ENTRADA_COMPRA', 20, 20, 'PURCHASE', 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos', 'PEPS', 'Ingreso inicial por recepción de compra #3', NOW() - INTERVAL '20 days'),
    ('PROD-SET-001', 'inventory', 'ENTRADA_COMPRA', 30, 30, 'PURCHASE', 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos', 'PEPS', 'Ingreso inicial por recepción de compra #3', NOW() - INTERVAL '20 days'),
    ('PROD-ILU-001', 'inventory', 'ENTRADA_COMPRA', 20, 20, 'PURCHASE', 'Adquisición de barras de luz LED para monitor con dial inalámbrico', 'PEPS', 'Ingreso inicial por recepción de compra #4', NOW() - INTERVAL '15 days'),
    ('PROD-ALM-001', 'inventory', 'ENTRADA_COMPRA', 12, 12, 'PURCHASE', 'Recepción de cajoneras rodantes de tres gavetas en acero reforzado', 'PEPS', 'Ingreso inicial por recepción de compra #5', NOW() - INTERVAL '10 days')
) AS m(product_sku, username, movement_type, quantity, balance_after, reference_type, purchase_notes, costing_method, notes, created_at)
JOIN products pr ON pr.sku = m.product_sku
JOIN users u ON u.username = m.username
JOIN purchases pu ON pu.notes = m.purchase_notes
WHERE NOT EXISTS (SELECT 1 FROM inventory_movements im WHERE im.notes = m.notes AND im.product_id = pr.id);

-- ======================================================================
-- 13. LOTES DE MOVIMIENTO
-- ======================================================================
INSERT INTO inventory_movement_lots (movement_id, lot_id, quantity, unit_cost)
SELECT
    im.id,
    il.id,
    ml.quantity,
    ml.unit_cost
FROM (VALUES
    ('PROD-ESC-001', 'Ingreso inicial por recepción de compra #1', 'Adquisición inicial de escritorios eléctricos y compactos para surtido de tienda', 10, 2350.00::numeric),
    ('PROD-SIL-001', 'Ingreso inicial por recepción de compra #2', 'Lote de sillas ejecutivas de alta gama y operativas ergonómicas', 15, 1450.00::numeric),
    ('PROD-ACC-001', 'Ingreso inicial por recepción de compra #3', 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos', 20, 480.00::numeric),
    ('PROD-SET-001', 'Ingreso inicial por recepción de compra #3', 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos', 30, 160.00::numeric),
    ('PROD-ILU-001', 'Ingreso inicial por recepción de compra #4', 'Adquisición de barras de luz LED para monitor con dial inalámbrico', 20, 280.00::numeric),
    ('PROD-ALM-001', 'Ingreso inicial por recepción de compra #5', 'Recepción de cajoneras rodantes de tres gavetas en acero reforzado', 12, 820.00::numeric)
) AS ml(product_sku, movement_notes, purchase_notes, quantity, unit_cost)
JOIN products pr ON pr.sku = ml.product_sku
JOIN inventory_movements im ON im.product_id = pr.id AND im.notes = ml.movement_notes
JOIN purchases pu ON pu.notes = ml.purchase_notes
JOIN purchase_items pi ON pi.purchase_id = pu.id AND pi.product_id = pr.id
JOIN inventory_lots il ON il.purchase_item_id = pi.id
WHERE NOT EXISTS (
    SELECT 1 FROM inventory_movement_lots iml
    WHERE iml.movement_id = im.id AND iml.lot_id = il.id
);

-- ======================================================================
-- 14. CLIENTES
-- ======================================================================
INSERT INTO customers (full_name, tax_number, phone, email, address, is_active, created_at, updated_at)
VALUES
    (
        'Juan Pablo Martínez Soto',
        'CF-1002345-1',
        '+502 4123-4567',
        'juan.martinez@gmail.com',
        '12 Calle 4-55 Zona 14, Ciudad de Guatemala',
        true,
        NOW(),
        NOW()
    ),
    (
        'María Fernanda López Rivas',
        'CF-2003456-2',
        '+502 5234-5678',
        'marifer.lopez@outlook.com',
        'Avenida La Reforma 9-30 Zona 10, Ciudad de Guatemala',
        true,
        NOW(),
        NOW()
    ),
    (
        'Tecnologías y Consultorías Integradas S.A.',
        'NIT-3456789-3',
        '+502 2345-6789',
        'adquisiciones@tecnointegrada.com',
        'Diagonal 6 10-01 Zona 10, Las Margaritas Torre 2',
        true,
        NOW(),
        NOW()
    ),
    (
        'Carlos Enrique Ramírez Paz',
        'CF-4567890-4',
        '+502 5678-9012',
        'carlos.ramirez@hotmail.com',
        'Condominio Las Luces, Casa 45, Santa Catarina Pinula',
        true,
        NOW(),
        NOW()
    ),
    (
        'Estudio de Arquitectura e Interiorismo Nova S.A.',
        'NIT-5678901-5',
        '+502 2456-7890',
        'compras@novaarquitectura.gt',
        'Vía 5 4-50 Zona 4, Cuatro Grados Norte',
        true,
        NOW(),
        NOW()
    )
ON CONFLICT (tax_number) DO NOTHING;

-- ======================================================================
-- 15. ESTADOS DE VENTA
-- ======================================================================
INSERT INTO sale_statuses (name, description, is_active, created_at)
VALUES
    ('PENDIENTE', 'Venta registrada pendiente de validación de pago o preparación', true, NOW()),
    ('PAGADA', 'Pago recibido y validado, pendiente de despacho o entrega al cliente', true, NOW()),
    ('ENTREGADA', 'Mercadería despachada y entregada al cliente con acuse de recibido', true, NOW()),
    ('COMPLETADA', 'Venta finalizada exitosamente con cobro y entrega conformes', true, NOW()),
    ('CANCELADA', 'Venta anulada sin impacto financiero ni despacho de inventario', true, NOW())
ON CONFLICT (name) DO NOTHING;

-- ======================================================================
-- 16. METODOS DE PAGO
-- ======================================================================
INSERT INTO payment_methods (name, description, is_active, created_at)
VALUES
    ('CASH', 'Pago en efectivo en moneda de curso legal (Quetzales)', true, NOW()),
    ('CREDIT_CARD', 'Pago mediante tarjeta de crédito internacional o local Visa y MasterCard', true, NOW()),
    ('DEBIT_CARD', 'Pago directo mediante débito bancario autorizado', true, NOW()),
    ('BANK_TRANSFER', 'Transferencia bancaria directa inmediata vía ACH o banca en línea', true, NOW())
ON CONFLICT (name) DO NOTHING;

-- ======================================================================
-- 17. VENTAS
-- ======================================================================
INSERT INTO sales (customer_id, user_id, status_id, invoice_number, sale_date, subtotal, tax_amount, total_amount, invoice_pdf_path, created_at, updated_at)
SELECT
    c.id,
    u.id,
    ss.id,
    s.invoice_number,
    s.sale_date,
    s.subtotal,
    s.tax_amount,
    s.total_amount,
    s.invoice_pdf_path,
    s.sale_date,
    s.sale_date
FROM (VALUES
    (
        'CF-1002345-1',
        'sales',
        'COMPLETADA',
        'FACT-001-00001',
        NOW() - INTERVAL '12 days',
        7778.00::numeric,
        933.36::numeric,
        8711.36::numeric,
        '/invoices/2026/FACT-001-00001.pdf'
    ),
    (
        'CF-2003456-2',
        'sales',
        'COMPLETADA',
        'FACT-001-00002',
        NOW() - INTERVAL '9 days',
        2540.00::numeric,
        304.80::numeric,
        2844.80::numeric,
        '/invoices/2026/FACT-001-00002.pdf'
    ),
    (
        'NIT-3456789-3',
        'sales',
        'COMPLETADA',
        'FACT-001-00003',
        NOW() - INTERVAL '7 days',
        9335.00::numeric,
        1120.20::numeric,
        10455.20::numeric,
        '/invoices/2026/FACT-001-00003.pdf'
    ),
    (
        'CF-4567890-4',
        'sales',
        'COMPLETADA',
        'FACT-001-00004',
        NOW() - INTERVAL '4 days',
        2330.00::numeric,
        279.60::numeric,
        2609.60::numeric,
        '/invoices/2026/FACT-001-00004.pdf'
    ),
    (
        'NIT-5678901-5',
        'sales',
        'COMPLETADA',
        'FACT-001-00005',
        NOW() - INTERVAL '2 days',
        7450.00::numeric,
        894.00::numeric,
        8344.00::numeric,
        '/invoices/2026/FACT-001-00005.pdf'
    )
) AS s(customer_tax, username, status_name, invoice_number, sale_date, subtotal, tax_amount, total_amount, invoice_pdf_path)
JOIN customers c ON c.tax_number = s.customer_tax
JOIN users u ON u.username = s.username
JOIN sale_statuses ss ON ss.name = s.status_name
ON CONFLICT (invoice_number) DO NOTHING;

-- ======================================================================
-- 18. DETALLE DE VENTAS
-- ======================================================================
INSERT INTO sale_items (sale_id, product_id, quantity, unit_price, subtotal, created_at)
VALUES
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00001'),
        (SELECT id FROM products WHERE sku = 'PROD-ESC-001'),
        2,
        3499.00,
        6998.00,
        NOW() - INTERVAL '12 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00001'),
        (SELECT id FROM products WHERE sku = 'PROD-ACC-001'),
        1,
        780.00,
        780.00,
        NOW() - INTERVAL '12 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00002'),
        (SELECT id FROM products WHERE sku = 'PROD-SIL-001'),
        1,
        2200.00,
        2200.00,
        NOW() - INTERVAL '9 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00002'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-002'),
        1,
        340.00,
        340.00,
        NOW() - INTERVAL '9 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00003'),
        (SELECT id FROM products WHERE sku = 'PROD-SIL-001'),
        2,
        2200.00,
        4400.00,
        NOW() - INTERVAL '7 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00003'),
        (SELECT id FROM products WHERE sku = 'PROD-SIL-002'),
        2,
        1450.00,
        2900.00,
        NOW() - INTERVAL '7 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00003'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-001'),
        4,
        290.00,
        1160.00,
        NOW() - INTERVAL '7 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00003'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-003'),
        5,
        175.00,
        875.00,
        NOW() - INTERVAL '7 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00004'),
        (SELECT id FROM products WHERE sku = 'PROD-ESC-002'),
        1,
        1850.00,
        1850.00,
        NOW() - INTERVAL '4 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00004'),
        (SELECT id FROM products WHERE sku = 'PROD-ILU-001'),
        1,
        480.00,
        480.00,
        NOW() - INTERVAL '4 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00005'),
        (SELECT id FROM products WHERE sku = 'PROD-ACC-001'),
        3,
        780.00,
        2340.00,
        NOW() - INTERVAL '2 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00005'),
        (SELECT id FROM products WHERE sku = 'PROD-ILU-001'),
        2,
        480.00,
        960.00,
        NOW() - INTERVAL '2 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00005'),
        (SELECT id FROM products WHERE sku = 'PROD-ALM-001'),
        2,
        1250.00,
        2500.00,
        NOW() - INTERVAL '2 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00005'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-002'),
        4,
        340.00,
        1360.00,
        NOW() - INTERVAL '2 days'
    ),
    (
        (SELECT id FROM sales WHERE invoice_number = 'FACT-001-00005'),
        (SELECT id FROM products WHERE sku = 'PROD-SET-001'),
        1,
        290.00,
        290.00,
        NOW() - INTERVAL '2 days'
    )
ON CONFLICT (sale_id, product_id) DO NOTHING;

-- ======================================================================
-- 19. PAGOS DE VENTAS
-- ======================================================================
INSERT INTO sale_payments (sale_id, payment_method_id, amount, payment_date, reference_number, notes, created_at)
SELECT
    sa.id,
    pm.id,
    p.amount,
    p.payment_date,
    p.reference_number,
    p.notes,
    p.payment_date
FROM (VALUES
    ('FACT-001-00001', 'CREDIT_CARD', 8711.36::numeric, NOW() - INTERVAL '12 days', 'AUTH-VISA-984210', 'Pago autorizado con tarjeta de crédito en 6 cuotas'),
    ('FACT-001-00002', 'DEBIT_CARD', 2844.80::numeric, NOW() - INTERVAL '9 days', 'POS-DEB-553120', 'Pago en punto de venta con tarjeta de débito'),
    ('FACT-001-00003', 'BANK_TRANSFER', 10455.20::numeric, NOW() - INTERVAL '7 days', 'TRF-BANRURAL-884129', 'Transferencia ACH aprobada y confirmada en cuenta corporativa'),
    ('FACT-001-00004', 'CASH', 2609.60::numeric, NOW() - INTERVAL '4 days', 'RECIBO-EFE-00412', 'Pago liquidado en caja en efectivo en moneda nacional'),
    ('FACT-001-00005', 'BANK_TRANSFER', 8344.00::numeric, NOW() - INTERVAL '2 days', 'TRF-BI-3391024', 'Transferencia directa desde Banco Industrial')
) AS p(invoice_number, payment_method_name, amount, payment_date, reference_number, notes)
JOIN sales sa ON sa.invoice_number = p.invoice_number
JOIN payment_methods pm ON pm.name = p.payment_method_name
WHERE NOT EXISTS (
    SELECT 1 FROM sale_payments sp
    WHERE sp.sale_id = sa.id AND sp.payment_method_id = pm.id AND sp.reference_number = p.reference_number
);



-- ======================================================================
-- 20. SECUENCIAS
-- ======================================================================
SELECT setval(pg_get_serial_sequence('categories', 'id'), COALESCE(MAX(id), 1)) FROM categories;
SELECT setval(pg_get_serial_sequence('products', 'id'), COALESCE(MAX(id), 1)) FROM products;
SELECT setval(pg_get_serial_sequence('phone_numbers', 'id'), COALESCE(MAX(id), 1)) FROM phone_numbers;
SELECT setval(pg_get_serial_sequence('suppliers', 'id'), COALESCE(MAX(id), 1)) FROM suppliers;
SELECT setval(pg_get_serial_sequence('phone_suppliers', 'id'), COALESCE(MAX(id), 1)) FROM phone_suppliers;
SELECT setval(pg_get_serial_sequence('supplier_products', 'id'), COALESCE(MAX(id), 1)) FROM supplier_products;
SELECT setval(pg_get_serial_sequence('purchase_statuses', 'id'), COALESCE(MAX(id), 1)) FROM purchase_statuses;
SELECT setval(pg_get_serial_sequence('purchases', 'id'), COALESCE(MAX(id), 1)) FROM purchases;
SELECT setval(pg_get_serial_sequence('purchase_items', 'id'), COALESCE(MAX(id), 1)) FROM purchase_items;
SELECT setval(pg_get_serial_sequence('customers', 'id'), COALESCE(MAX(id), 1)) FROM customers;
SELECT setval(pg_get_serial_sequence('sale_statuses', 'id'), COALESCE(MAX(id), 1)) FROM sale_statuses;
SELECT setval(pg_get_serial_sequence('payment_methods', 'id'), COALESCE(MAX(id), 1)) FROM payment_methods;
SELECT setval(pg_get_serial_sequence('sales', 'id'), COALESCE(MAX(id), 1)) FROM sales;
SELECT setval(pg_get_serial_sequence('sale_items', 'id'), COALESCE(MAX(id), 1)) FROM sale_items;
SELECT setval(pg_get_serial_sequence('sale_payments', 'id'), COALESCE(MAX(id), 1)) FROM sale_payments;
SELECT setval(pg_get_serial_sequence('inventory', 'id'), COALESCE(MAX(id), 1)) FROM inventory;
SELECT setval(pg_get_serial_sequence('inventory_lots', 'id'), COALESCE(MAX(id), 1)) FROM inventory_lots;
SELECT setval(pg_get_serial_sequence('inventory_movements', 'id'), COALESCE(MAX(id), 1)) FROM inventory_movements;
SELECT setval(pg_get_serial_sequence('inventory_movement_lots', 'id'), COALESCE(MAX(id), 1)) FROM inventory_movement_lots;
