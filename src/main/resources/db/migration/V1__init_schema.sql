-- ======================================================================
-- HOME DESK DB
-- ======================================================================

-- ======================================================================
-- EXTENSIONS
-- ======================================================================
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- ======================================================================
-- SEGURIDAD
-- ======================================================================
CREATE TABLE roles
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(30) NOT NULL UNIQUE,
    description VARCHAR(150),
    is_active   BOOLEAN     NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);


CREATE TABLE users
(
    id              UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    employee_number INTEGER      NOT NULL UNIQUE,
    username        VARCHAR(50)  NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    full_name       VARCHAR(150) NOT NULL,
    email           VARCHAR(150) NOT NULL UNIQUE,
    role_id         BIGINT       NOT NULL,
    is_active       BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMPTZ  NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_users_role
        FOREIGN KEY (role_id)
            REFERENCES roles (id)
);

CREATE INDEX idx_users_role_id ON users (role_id);
CREATE INDEX idx_users_username ON users (username);
CREATE INDEX idx_users_email ON users (email);


-- ======================================================================
-- CATALOGO
-- ======================================================================

CREATE TABLE categories
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255),
    is_active   BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);


CREATE TABLE products
(
    id              BIGSERIAL PRIMARY KEY,
    sku             VARCHAR(50)    NOT NULL UNIQUE,
    name            VARCHAR(150)   NOT NULL,
    description     TEXT,
    category_id     BIGINT         NOT NULL,
    unit_of_measure VARCHAR(20)    NOT NULL DEFAULT 'UNIT',
    sale_price      NUMERIC(12, 2) NOT NULL,
    min_stock_level INTEGER        NOT NULL DEFAULT 0,
    is_active       BOOLEAN        NOT NULL DEFAULT TRUE,
    created_at      TIMESTAMPTZ    NOT NULL DEFAULT NOW(),
    updated_at      TIMESTAMPTZ    NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_products_category
        FOREIGN KEY (category_id)
            REFERENCES categories (id),

    CONSTRAINT chk_products_sale_price
        CHECK (sale_price >= 0),

    CONSTRAINT chk_products_min_stock
        CHECK (min_stock_level >= 0)
);

CREATE INDEX idx_products_category_id ON products (category_id);
CREATE INDEX idx_products_name ON products (name);
CREATE INDEX idx_products_sku ON products (sku);


-- ======================================================================
-- PROVEEDORES
-- ======================================================================
CREATE TABLE phone_numbers
(
    id    BIGSERIAL PRIMARY KEY,
    phone VARCHAR(30) NOT NULL
);


CREATE TABLE suppliers
(
    id           BIGSERIAL PRIMARY KEY,

    name         VARCHAR(150) NOT NULL,
    tax_number   VARCHAR(30) UNIQUE,
    contact_name VARCHAR(150),
    email        VARCHAR(150),
    address      VARCHAR(255),
    is_active    BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at   TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    updated_at   TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_suppliers_name ON suppliers (name);
CREATE INDEX idx_suppliers_tax_number ON suppliers (tax_number);

CREATE TABLE phone_suppliers
(
    id          BIGSERIAL PRIMARY KEY,
    supplier_id BIGINT NOT NULL,
    phone_id    BIGINT NOT NULL,

    CONSTRAINT uq_phone_suppliers
        UNIQUE (supplier_id, phone_id),

    CONSTRAINT fk_phone_suppliers_supplier
        FOREIGN KEY (supplier_id)
            REFERENCES suppliers (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_phone_suppliers_phone
        FOREIGN KEY (phone_id)
            REFERENCES phone_numbers (id)
            ON DELETE CASCADE
);


CREATE INDEX idx_phone_suppliers_supplier_id
    ON phone_suppliers (supplier_id);

CREATE INDEX idx_phone_suppliers_phone_id
    ON phone_suppliers (phone_id);


CREATE TABLE supplier_products
(
    id                 BIGSERIAL PRIMARY KEY,
    supplier_id        BIGINT      NOT NULL,
    product_id         BIGINT      NOT NULL,
    supplier_sku       VARCHAR(30),
    cost               NUMERIC(12, 2),
    last_purchase_cost NUMERIC(12, 2),
    created_at         TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at         TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT uq_supplier_products
        UNIQUE (supplier_id, product_id),

    CONSTRAINT fk_supplier_products_supplier
        FOREIGN KEY (supplier_id)
            REFERENCES suppliers (id),

    CONSTRAINT fk_supplier_products_product
        FOREIGN KEY (product_id)
            REFERENCES products (id),

    CONSTRAINT chk_supplier_products_cost
        CHECK (cost IS NULL OR cost >= 0),

    CONSTRAINT chk_supplier_products_last_cost
        CHECK (last_purchase_cost IS NULL OR last_purchase_cost >= 0)
);

CREATE INDEX idx_supplier_products_supplier_id
    ON supplier_products (supplier_id);

CREATE INDEX idx_supplier_products_product_id
    ON supplier_products (product_id);


-- ======================================================================
-- ESTADO DE PROVEEDORES
-- ======================================================================
CREATE TABLE purchase_statuses
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(30) NOT NULL UNIQUE,
    description VARCHAR(150),
    is_active   BOOLEAN     NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- ======================================================================
-- COMPRAS
-- ======================================================================
CREATE TABLE purchases
(
    id            BIGSERIAL PRIMARY KEY,
    supplier_id   BIGINT         NOT NULL,
    user_id       UUID           NOT NULL,
    status_id     BIGINT         NOT NULL,
    purchase_date TIMESTAMPTZ    NOT NULL DEFAULT NOW(),
    subtotal      NUMERIC(14, 2) NOT NULL,
    tax_amount    NUMERIC(14, 2) NOT NULL DEFAULT 0,
    total_amount  NUMERIC(14, 2) NOT NULL,
    notes         VARCHAR(255),

    created_at    TIMESTAMPTZ    NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMPTZ    NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_purchases_supplier
        FOREIGN KEY (supplier_id)
            REFERENCES suppliers (id),

    CONSTRAINT fk_purchases_user
        FOREIGN KEY (user_id)
            REFERENCES users (id),

    CONSTRAINT fk_purchases_status
        FOREIGN KEY (status_id)
            REFERENCES purchase_statuses (id),

    CONSTRAINT chk_purchases_subtotal
        CHECK (subtotal >= 0),

    CONSTRAINT chk_purchases_tax
        CHECK (tax_amount >= 0),

    CONSTRAINT chk_purchases_total
        CHECK (total_amount >= 0)
);

CREATE INDEX idx_purchases_supplier_id
    ON purchases (supplier_id);

CREATE INDEX idx_purchases_user_id
    ON purchases (user_id);

CREATE INDEX idx_purchases_status_id
    ON purchases (status_id);

CREATE INDEX idx_purchases_purchase_date
    ON purchases (purchase_date);


CREATE TABLE purchase_items
(
    id          BIGSERIAL PRIMARY KEY,
    purchase_id BIGINT         NOT NULL,
    product_id  BIGINT         NOT NULL,
    quantity    INTEGER        NOT NULL,
    unit_cost   NUMERIC(12, 2) NOT NULL,
    subtotal    NUMERIC(14, 2) NOT NULL,
    created_at  TIMESTAMPTZ    NOT NULL DEFAULT NOW(),

    CONSTRAINT uq_purchase_items
        UNIQUE (purchase_id, product_id),

    CONSTRAINT fk_purchase_items_purchase
        FOREIGN KEY (purchase_id)
            REFERENCES purchases (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_purchase_items_product
        FOREIGN KEY (product_id)
            REFERENCES products (id),

    CONSTRAINT chk_purchase_items_quantity
        CHECK (quantity > 0),

    CONSTRAINT chk_purchase_items_unit_cost
        CHECK (unit_cost >= 0),

    CONSTRAINT chk_purchase_items_subtotal
        CHECK (subtotal >= 0)
);

CREATE INDEX idx_purchase_items_purchase_id
    ON purchase_items (purchase_id);

CREATE INDEX idx_purchase_items_product_id
    ON purchase_items (product_id);


-- ======================================================================
-- CLIENTES
-- ======================================================================

CREATE TABLE customers
(
    id         BIGSERIAL PRIMARY KEY,
    full_name  VARCHAR(150) NOT NULL,
    tax_number VARCHAR(30) UNIQUE,
    phone      VARCHAR(30),
    email      VARCHAR(150),
    address    VARCHAR(255),
    is_active  BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ  NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_customers_full_name
    ON customers (full_name);

CREATE INDEX idx_customers_tax_number
    ON customers (tax_number);

CREATE INDEX idx_customers_email
    ON customers (email);


-- ======================================================================
-- ESTADO DE VENTAS
-- ======================================================================

CREATE TABLE sale_statuses
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(30) NOT NULL UNIQUE,
    description VARCHAR(150),
    is_active   BOOLEAN     NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);


-- ======================================================================
-- METODOS DE PAGO
-- ======================================================================

CREATE TABLE payment_methods
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(150),
    is_active   BOOLEAN     NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);


-- ======================================================================
-- VENTAS
-- ======================================================================

CREATE TABLE sales
(
    id               BIGSERIAL PRIMARY KEY,
    customer_id      BIGINT         NOT NULL,
    user_id          UUID           NOT NULL,
    status_id        BIGINT         NOT NULL,
    invoice_number   VARCHAR(30)    NOT NULL UNIQUE,
    sale_date        TIMESTAMPTZ    NOT NULL DEFAULT NOW(),
    subtotal         NUMERIC(14, 2) NOT NULL,
    tax_amount       NUMERIC(14, 2) NOT NULL DEFAULT 0,
    total_amount     NUMERIC(14, 2) NOT NULL,
    invoice_pdf_path VARCHAR(255),
    created_at       TIMESTAMPTZ    NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMPTZ    NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_sales_customer
        FOREIGN KEY (customer_id)
            REFERENCES customers (id),

    CONSTRAINT fk_sales_user
        FOREIGN KEY (user_id)
            REFERENCES users (id),

    CONSTRAINT fk_sales_status
        FOREIGN KEY (status_id)
            REFERENCES sale_statuses (id),

    CONSTRAINT chk_sales_subtotal
        CHECK (subtotal >= 0),

    CONSTRAINT chk_sales_tax
        CHECK (tax_amount >= 0),

    CONSTRAINT chk_sales_total
        CHECK (total_amount >= 0)
);

CREATE INDEX idx_sales_customer_id
    ON sales (customer_id);

CREATE INDEX idx_sales_user_id
    ON sales (user_id);

CREATE INDEX idx_sales_status_id
    ON sales (status_id);

CREATE INDEX idx_sales_sale_date
    ON sales (sale_date);

CREATE INDEX idx_sales_invoice_number
    ON sales (invoice_number);


CREATE TABLE sale_items
(
    id         BIGSERIAL PRIMARY KEY,
    sale_id    BIGINT         NOT NULL,
    product_id BIGINT         NOT NULL,
    quantity   INTEGER        NOT NULL,
    unit_price NUMERIC(12, 2) NOT NULL,
    subtotal   NUMERIC(14, 2) NOT NULL,
    created_at TIMESTAMPTZ    NOT NULL DEFAULT NOW(),

    CONSTRAINT uq_sale_items
        UNIQUE (sale_id, product_id),

    CONSTRAINT fk_sale_items_sale
        FOREIGN KEY (sale_id)
            REFERENCES sales (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_sale_items_product
        FOREIGN KEY (product_id)
            REFERENCES products (id),

    CONSTRAINT chk_sale_items_quantity
        CHECK (quantity > 0),

    CONSTRAINT chk_sale_items_unit_price
        CHECK (unit_price >= 0),

    CONSTRAINT chk_sale_items_subtotal
        CHECK (subtotal >= 0)
);

CREATE INDEX idx_sale_items_sale_id
    ON sale_items (sale_id);

CREATE INDEX idx_sale_items_product_id
    ON sale_items (product_id);


-- ======================================================================
-- METODOS DE PAGO EN COMPRAS
-- ======================================================================
CREATE TABLE sale_payments
(
    id                BIGSERIAL PRIMARY KEY,
    sale_id           BIGINT         NOT NULL,
    payment_method_id BIGINT         NOT NULL,
    amount            NUMERIC(14, 2) NOT NULL,
    payment_date      TIMESTAMPTZ    NOT NULL DEFAULT NOW(),
    reference_number  VARCHAR(100),
    notes             VARCHAR(255),
    created_at        TIMESTAMPTZ    NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_sale_payments_sale
        FOREIGN KEY (sale_id)
            REFERENCES sales (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_sale_payments_method
        FOREIGN KEY (payment_method_id)
            REFERENCES payment_methods (id),

    CONSTRAINT chk_sale_payments_amount
        CHECK (amount > 0)
);

CREATE INDEX idx_sale_payments_sale_id
    ON sale_payments (sale_id);

CREATE INDEX idx_sale_payments_payment_method_id
    ON sale_payments (payment_method_id);

CREATE INDEX idx_sale_payments_payment_date
    ON sale_payments (payment_date);


-- ======================================================================
-- INVENTARIO
-- ======================================================================

CREATE TABLE inventory
(
    id               BIGSERIAL PRIMARY KEY,
    product_id       BIGINT      NOT NULL UNIQUE,
    quantity_on_hand INTEGER     NOT NULL DEFAULT 0,
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_inventory_product
        FOREIGN KEY (product_id)
            REFERENCES products (id),

    CONSTRAINT chk_inventory_quantity
        CHECK (quantity_on_hand >= 0)
);


CREATE TABLE inventory_lots
(
    id                 BIGSERIAL PRIMARY KEY,
    product_id         BIGINT         NOT NULL,
    purchase_item_id   BIGINT         NOT NULL UNIQUE,
    quantity_received  INTEGER        NOT NULL,
    quantity_remaining INTEGER        NOT NULL,
    unit_cost          NUMERIC(12, 2) NOT NULL,
    received_at        TIMESTAMPTZ    NOT NULL DEFAULT NOW(),
    created_at         TIMESTAMPTZ    NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_inventory_lots_product
        FOREIGN KEY (product_id)
            REFERENCES products (id),

    CONSTRAINT fk_inventory_lots_purchase_item
        FOREIGN KEY (purchase_item_id)
            REFERENCES purchase_items (id),

    CONSTRAINT chk_inventory_lots_received
        CHECK (quantity_received > 0),

    CONSTRAINT chk_inventory_lots_remaining
        CHECK (
            quantity_remaining >= 0
                AND quantity_remaining <= quantity_received
            ),

    CONSTRAINT chk_inventory_lots_unit_cost
        CHECK (unit_cost >= 0)
);

CREATE INDEX idx_inventory_lots_product_received
    ON inventory_lots (product_id, received_at);

CREATE INDEX idx_inventory_lots_product_id
    ON inventory_lots (product_id);

CREATE INDEX idx_inventory_lots_purchase_item_id
    ON inventory_lots (purchase_item_id);


CREATE TABLE inventory_movements
(
    id             BIGSERIAL PRIMARY KEY,
    product_id     BIGINT      NOT NULL,
    user_id        UUID        NOT NULL,
    movement_type  VARCHAR(30) NOT NULL,
    quantity       INTEGER     NOT NULL,
    balance_after  INTEGER     NOT NULL,
    reference_type VARCHAR(30),
    reference_id   BIGINT,
    costing_method VARCHAR(10),
    notes          VARCHAR(255),
    created_at     TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_inventory_movements_product
        FOREIGN KEY (product_id)
            REFERENCES products (id),

    CONSTRAINT fk_inventory_movements_user
        FOREIGN KEY (user_id)
            REFERENCES users (id),

    CONSTRAINT chk_inventory_movements_quantity
        CHECK (quantity <> 0),

    CONSTRAINT chk_inventory_movements_balance
        CHECK (balance_after >= 0)
);

CREATE INDEX idx_inventory_movements_product_id
    ON inventory_movements (product_id);

CREATE INDEX idx_inventory_movements_user_id
    ON inventory_movements (user_id);

CREATE INDEX idx_inventory_movements_reference
    ON inventory_movements (reference_type, reference_id);

CREATE INDEX idx_inventory_movements_created_at
    ON inventory_movements (created_at);


CREATE TABLE inventory_movement_lots
(
    id          BIGSERIAL PRIMARY KEY,
    movement_id BIGINT         NOT NULL,
    lot_id      BIGINT         NOT NULL,
    quantity    INTEGER        NOT NULL,
    unit_cost   NUMERIC(12, 2) NOT NULL,

    CONSTRAINT fk_inventory_movement_lots_movement
        FOREIGN KEY (movement_id)
            REFERENCES inventory_movements (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_inventory_movement_lots_lot
        FOREIGN KEY (lot_id)
            REFERENCES inventory_lots (id),

    CONSTRAINT chk_inventory_movement_lots_quantity
        CHECK (quantity > 0),

    CONSTRAINT chk_inventory_movement_lots_unit_cost
        CHECK (unit_cost >= 0)
);

CREATE INDEX idx_inventory_movement_lots_movement_id
    ON inventory_movement_lots (movement_id);

CREATE INDEX idx_inventory_movement_lots_lot_id
    ON inventory_movement_lots (lot_id);


-- ======================================================================
-- AUDITORIA
-- ======================================================================

CREATE TABLE audit_logs
(
    id          BIGSERIAL PRIMARY KEY,
    user_id     UUID        NOT NULL,
    action      VARCHAR(30) NOT NULL,
    module      VARCHAR(50) NOT NULL,
    entity_name VARCHAR(50),
    entity_id   VARCHAR(50),
    description VARCHAR(255),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_audit_logs_user
        FOREIGN KEY (user_id)
            REFERENCES users (id)
);

CREATE INDEX idx_audit_logs_user_id
    ON audit_logs (user_id);

CREATE INDEX idx_audit_logs_module
    ON audit_logs (module);

CREATE INDEX idx_audit_logs_action
    ON audit_logs (action);

CREATE INDEX idx_audit_logs_created_at
    ON audit_logs (created_at);