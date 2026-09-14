INSERT INTO roles (name, description, is_active)
VALUES ('ADMINISTRATION', 'Administración general del sistema', true),
       ('PURCHASING', 'Gestión de compras y proveedores', true),
       ('INVENTORY', 'Gestión y control de inventario', true),
       ('SALES', 'Gestión de ventas y facturación', true) ON CONFLICT (name) DO NOTHING;

INSERT INTO users (id,
                   employee_number,
                   username,
                   password,
                   full_name,
                   email,
                   role_id,
                   is_active,
                   created_at,
                   updated_at)
SELECT gen_random_uuid(),
       1001,
       'admin',
       '$2a$10$VZx9UqAcG0DlqbO2g38wOeJmlNK/j9xk59da7YHlvWC88hWsZSuNm',
       'Administrador Principal',
       'admin@admin.com',
       r.id,
       true,
       NOW(),
       NOW()
FROM roles r
WHERE r.name = 'ADMINISTRATION' ON CONFLICT (username) DO NOTHING;

INSERT INTO users (id,
                   employee_number,
                   username,
                   password,
                   full_name,
                   email,
                   role_id,
                   is_active,
                   created_at,
                   updated_at)
SELECT gen_random_uuid(),
       1002,
       'compra',
       '$2a$10$VZx9UqAcG0DlqbO2g38wOeJmlNK/j9xk59da7YHlvWC88hWsZSuNm',
       'Encargado de Compras',
       'pur@pur.com',
       r.id,
       true,
       NOW(),
       NOW()
FROM roles r
WHERE r.name = 'PURCHASING' ON CONFLICT (username) DO NOTHING;

INSERT INTO users (id,
                   employee_number,
                   username,
                   password,
                   full_name,
                   email,
                   role_id,
                   is_active,
                   created_at,
                   updated_at)
SELECT gen_random_uuid(),
       1003,
       'inventory',
       '$2a$10$VZx9UqAcG0DlqbO2g38wOeJmlNK/j9xk59da7YHlvWC88hWsZSuNm',
       'Encargado de Inventario',
       'inv@inv.com',
       r.id,
       true,
       NOW(),
       NOW()
FROM roles r
WHERE r.name = 'INVENTORY' ON CONFLICT (username) DO NOTHING;

INSERT INTO users (id,
                   employee_number,
                   username,
                   password,
                   full_name,
                   email,
                   role_id,
                   is_active,
                   created_at,
                   updated_at)
SELECT gen_random_uuid(),
       1004,
       'sales',
       '$2a$10$VZx9UqAcG0DlqbO2g38wOeJmlNK/j9xk59da7YHlvWC88hWsZSuNm',
       'Vendedor HomeDesk',
       'sales@sales.com',
       r.id,
       true,
       NOW(),
       NOW()
FROM roles r
WHERE r.name = 'SALES' ON CONFLICT (username) DO NOTHING;
