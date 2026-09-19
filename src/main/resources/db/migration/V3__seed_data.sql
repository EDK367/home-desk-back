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
       100,
       'adminstrador',
       '$2a$12$QkqxMkbcNfZ9D4DshRGt5.eyE8xExcmhmGBmgkh7bIv.fB3u1KXpu',
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
       101,
       'comprador',
       '$2a$12$QkqxMkbcNfZ9D4DshRGt5.eyE8xExcmhmGBmgkh7bIv.fB3u1KXpu',
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
       102,
       'inventario',
       '$2a$12$QkqxMkbcNfZ9D4DshRGt5.eyE8xExcmhmGBmgkh7bIv.fB3u1KXpu',
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
       103,
       'vendedor',
       '$2a$12$QkqxMkbcNfZ9D4DshRGt5.eyE8xExcmhmGBmgkh7bIv.fB3u1KXpu',
       'Vendedor HomeDesk',
       'sales@sales.com',
       r.id,
       true,
       NOW(),
       NOW()
FROM roles r
WHERE r.name = 'SALES' ON CONFLICT (username) DO NOTHING;
