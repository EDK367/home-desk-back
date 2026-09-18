--
-- PostgreSQL database dump
--

\restrict d0f8aHvdqobZaPh16cxu4OgV8qxBySHMw4GSZXUtV4EGXLXEPEsdPwZQYHiZfpD

-- Dumped from database version 18.6
-- Dumped by pg_dump version 18.6

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.roles VALUES (1, 'ADMINISTRATION', 'Administración general del sistema', true, '2026-09-17 13:28:12.297665-06', '2026-09-17 13:28:12.297665-06');
INSERT INTO public.roles VALUES (2, 'PURCHASING', 'Gestión de compras y proveedores', true, '2026-09-17 13:28:12.297665-06', '2026-09-17 13:28:12.297665-06');
INSERT INTO public.roles VALUES (3, 'INVENTORY', 'Gestión y control de inventario', true, '2026-09-17 13:28:12.297665-06', '2026-09-17 13:28:12.297665-06');
INSERT INTO public.roles VALUES (4, 'SALES', 'Gestión de ventas y facturación', true, '2026-09-17 13:28:12.297665-06', '2026-09-17 13:28:12.297665-06');


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.users VALUES ('de67c80a-2fa0-41cb-9b61-0b332e84eeb6', 100, 'admin', '$2a$10$VZx9UqAcG0DlqbO2g38wOeJmlNK/j9xk59da7YHlvWC88hWsZSuNm', 'Administrador Principal', 'admin@admin.com', 1, true, '2026-09-17 13:28:12.297665-06', '2026-09-17 13:28:12.297665-06');
INSERT INTO public.users VALUES ('82f586e1-0c57-47c1-8832-5eab20d9d15f', 101, 'compra', '$2a$10$VZx9UqAcG0DlqbO2g38wOeJmlNK/j9xk59da7YHlvWC88hWsZSuNm', 'Encargado de Compras', 'pur@pur.com', 2, true, '2026-09-17 13:28:12.297665-06', '2026-09-17 13:28:12.297665-06');
INSERT INTO public.users VALUES ('996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 102, 'inventory', '$2a$10$VZx9UqAcG0DlqbO2g38wOeJmlNK/j9xk59da7YHlvWC88hWsZSuNm', 'Encargado de Inventario', 'inv@inv.com', 3, true, '2026-09-17 13:28:12.297665-06', '2026-09-17 13:28:12.297665-06');
INSERT INTO public.users VALUES ('00fe08f8-ef7c-465a-a33e-2bc50667c283', 103, 'sales', '$2a$10$VZx9UqAcG0DlqbO2g38wOeJmlNK/j9xk59da7YHlvWC88hWsZSuNm', 'Vendedor HomeDesk', 'sales@sales.com', 4, true, '2026-09-17 13:28:12.297665-06', '2026-09-17 13:28:12.297665-06');


--
-- Data for Name: audit_logs; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.categories VALUES (1, 'Escritorios', 'Escritorios ergonómicos, de altura regulable y mesas ejecutivas para oficina y estudio', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.categories VALUES (2, 'Sillas de Oficina', 'Sillas ergonómicas, ejecutivas y operativas diseñadas para soporte postural prolongado', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.categories VALUES (3, 'Accesorios Ergonómicos', 'Soportes para monitores, reposapiés, brazos mecánicos y organizadores de espacio', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.categories VALUES (4, 'Iluminación de Escritorio', 'Lámparas LED con regulación de brillo, temperatura de luz y carga inalámbrica', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.categories VALUES (5, 'Almacenamiento y Archivo', 'Cajoneras rodantes, estanterías modulares y libreros para organización documental', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.categories VALUES (6, 'Organización de Cables', 'Soluciones integrales de canaletas, pasacables y gestión eléctrica bajo mesa', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customers VALUES (1, 'Juan Pablo Martínez Soto', 'CF-1002345-1', '+502 4123-4567', 'juan.martinez@gmail.com', '12 Calle 4-55 Zona 14, Ciudad de Guatemala', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.customers VALUES (2, 'María Fernanda López Rivas', 'CF-2003456-2', '+502 5234-5678', 'marifer.lopez@outlook.com', 'Avenida La Reforma 9-30 Zona 10, Ciudad de Guatemala', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.customers VALUES (3, 'Tecnologías y Consultorías Integradas S.A.', 'NIT-3456789-3', '+502 2345-6789', 'adquisiciones@tecnointegrada.com', 'Diagonal 6 10-01 Zona 10, Las Margaritas Torre 2', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.customers VALUES (4, 'Carlos Enrique Ramírez Paz', 'CF-4567890-4', '+502 5678-9012', 'carlos.ramirez@hotmail.com', 'Condominio Las Luces, Casa 45, Santa Catarina Pinula', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.customers VALUES (5, 'Estudio de Arquitectura e Interiorismo Nova S.A.', 'NIT-5678901-5', '+502 2456-7890', 'compras@novaarquitectura.gt', 'Vía 5 4-50 Zona 4, Cuatro Grados Norte', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.customers VALUES (6, 'Sofía Alejandra Morales Gómez', 'CF-6789012-6', '+502 5112-3344', 'sofia.morales@gmail.com', '14 Calle 6-12 Zona 10, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (7, 'Inversiones y Desarrollos Urbanos Maya S.A.', 'NIT-7890123-7', '+502 2366-5500', 'compras@idurbanasmaya.com', 'Diagonal 6 12-42 Zona 10, Torre Design Center, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (8, 'Diego Alejandro Castillo Cabrera', 'CF-8901234-8', '+502 4789-1122', 'diego.castillo@yahoo.com', '5ta Avenida 11-30 Zona 1, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (9, 'Soluciones Digitales e Innovación S.A.', 'NIT-9012345-9', '+502 2333-8899', 'adquisiciones@solucionesdigitales.gt', 'Avenida Las Américas 18-02 Zona 14, Edificio Tiffany, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (10, 'Valeria Beatriz Rodas Méndez', 'CF-1234509-0', '+502 5901-2233', 'valeria.rodas@outlook.com', 'Boulevard Vista Hermosa 21-45 Zona 15, VH 1, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (11, 'Consultores Financieros y Fiscales de Occidente S.A.', 'NIT-2345610-1', '+502 7765-4321', 'administracion@consultoresoccidente.com', '4ta Calle 14-20 Zona 3, Quetzaltenango', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (12, 'Rodrigo Javier Pineda Fuentes', 'CF-3456721-2', '+502 4233-7788', 'rodrigo.pineda@gmail.com', 'Condominio Senderos del Bosque Casa 12, San Lucas Sacatepéquez', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (13, 'Corporación Médica Especializada San Rafael S.A.', 'NIT-4567832-3', '+502 2277-9900', 'compras@medicasanrafael.com.gt', '6ta Avenida 3-22 Zona 10, Edificio Sixtino II, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (14, 'Gabriela María Santizo Orellana', 'CF-5678943-4', '+502 5445-6677', 'gaby.santizo@hotmail.com', 'Km 16.5 Carretera a El Salvador, Fraijanes', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (16, 'Héctor Francisco Villagrán Solís', 'CF-7890165-6', '+502 4667-8899', 'hector.villagran@gmail.com', '10ma Calle 2-45 Zona 14, Residenciales Las Conchas, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (17, 'Logística y Envíos Express del Caribe S.A.', 'NIT-8901276-7', '+502 2490-5566', 'operaciones@logisticadelcaribe.com', 'Calzada Atanasio Tzul 22-00 Zona 12, Parque Industrial, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (18, 'Claudia Marcela Quiñónez Barrios', 'CF-9012387-8', '+502 5889-0011', 'claudia.quinonez@gmail.com', '18 Avenida 15-30 Zona 11, Colonia Mariscal, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (19, 'Bufete Jurídico y Asociados LegalPro S.A.', 'NIT-1023498-9', '+502 2311-4455', 'servicios@legalproguatemala.com', '12 Calle 1-25 Zona 10, Edificio Géminis 10 Torre Norte, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (20, 'Luis Eduardo Cifuentes Estrada', 'CF-2134509-1', '+502 4334-5566', 'luis.cifuentes@yahoo.com', 'Avenida Simeón Cañas 8-12 Zona 2, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.customers VALUES (21, 'Maria luisa', '456132986', '+502 465316', 'cliente@gamil.com', '4ta venida de no se que lugar', true, '2026-09-17 19:28:55.745573-06', '2026-09-17 19:28:55.745619-06');
INSERT INTO public.customers VALUES (15, 'Agencia Creativa Pixel & Diseño S.A.', 'NIT-6789054-5', '+502 2360-1123', 'hola@pixeldiseno.gt', 'Ruta 4 6-30 Zona 4, Edificio Campus Tec, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 19:38:27.638644-06');


--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.flyway_schema_history VALUES (1, '1', 'init schema', 'SQL', 'V1__init_schema.sql', 126350236, 'postgres', '2026-09-17 13:28:11.997416', 177, true);
INSERT INTO public.flyway_schema_history VALUES (2, '2', 'sessions', 'SQL', 'V2__sessions.sql', 1281772378, 'postgres', '2026-09-17 13:28:12.256134', 11, true);
INSERT INTO public.flyway_schema_history VALUES (3, '3', 'seed data', 'SQL', 'V3__seed_data.sql', 801541244, 'postgres', '2026-09-17 13:28:12.287282', 10, true);
INSERT INTO public.flyway_schema_history VALUES (4, '4', 'user employee sequence', 'SQL', 'V4__user_employee_sequence.sql', -284140159, 'postgres', '2026-09-17 13:28:12.31413', 6, true);
INSERT INTO public.flyway_schema_history VALUES (5, '5', 'seed data full', 'SQL', 'V5__seed_data_full.sql', 1764054850, 'postgres', '2026-09-17 13:28:12.334414', 97, true);
INSERT INTO public.flyway_schema_history VALUES (6, '6', 'complet seed', 'SQL', 'V6__complet_seed.sql', -1792486000, 'postgres', '2026-09-17 13:28:12.486017', 87, true);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.products VALUES (1, 'PROD-ESC-001', 'Escritorio Eléctrico Elevable StandDesk Pro 160x80', 'Escritorio de altura ajustable con motor dual silencioso, estructura de acero reforzado y memoria programable de 4 alturas', 1, 'UNIT', 3499.00, 5, true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.products VALUES (2, 'PROD-ESC-002', 'Escritorio Compacto de Madera Nogal 120x60', 'Mesa de trabajo minimalista elaborada en melamina de alta densidad con acabado texturizado de nogal y patas metálicas', 1, 'UNIT', 1850.00, 4, true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.products VALUES (3, 'PROD-SIL-001', 'Silla Ergonómica Ejecutiva Titan Mesh', 'Silla de oficina de alto rendimiento con respaldo de malla transpirable, soporte lumbar 3D y descansabrazos 4D', 2, 'UNIT', 2200.00, 6, true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.products VALUES (4, 'PROD-SIL-002', 'Silla Giratoria Acolchada Confort Plus', 'Silla ergonómica operativa con tapizado en tela de alta durabilidad, espuma de inyección viscoelástica y pistón clase 4', 2, 'UNIT', 1450.00, 5, true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.products VALUES (5, 'PROD-ACC-001', 'Brazo Mecánico para Doble Monitor 17-32 Pulgadas', 'Soporte articulado de aluminio para dos pantallas con sistema de resorte de gas, pasacables integrado y fijación VESA', 3, 'UNIT', 780.00, 8, true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.products VALUES (6, 'PROD-SET-001', 'Set de Organización y Guiado de Cables para Escritorio', 'Juego integral que incluye canaleta bajo cubierta, 10 clips magnéticos, columna vertebrada articulada y bridas de velcro', 6, 'SET', 290.00, 10, true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.products VALUES (7, 'PROD-SET-002', 'Set Ergonómico Reposamuñecas y Tapete XXL', 'Conjunto ergonómico que contiene tapete de escritorio de cuero sintético resistente al agua y descansamuñecas de espuma viscoelástica', 3, 'SET', 340.00, 8, true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.products VALUES (8, 'PROD-ILU-001', 'Barra de Luz LED para Monitor ScreenBar Pro', 'Lámpara de iluminación asimétrica para monitor que previene el reflejo en pantalla, incluye control dial inalámbrico', 4, 'UNIT', 480.00, 7, true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.products VALUES (9, 'PROD-ALM-001', 'Cajonera Metálica Rodante con Cerradura Central', 'Mueble archivero metálico de tres gavetas sobre ruedas reforzadas con freno y sistema de bloqueo general con llave', 5, 'UNIT', 1250.00, 4, true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.products VALUES (10, 'PROD-SET-003', 'Set de Limpieza y Mantenimiento de Pantallas y Periféricos', 'Kit profesional que incluye cepillo retráctil de cerdas suaves, soplador de aire, solución limpiadora antiestática y paño de microfibra', 3, 'SET', 175.00, 12, true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.products VALUES (11, 'PROD-ESC-003', 'Escritorio Esquinero en L CornerMaster 180x140', 'Mesa de trabajo ejecutiva en forma de L con estructura metálica reforzada, pasacables integrado y superficie melamínica anti-rayaduras', 1, 'UNIT', 2850.00, 4, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (12, 'PROD-ESC-004', 'Mesa de Conferencia Modular Boardroom Pro 240x120', 'Mesa de reuniones ejecutiva de gran formato con centro de conectividad integrado HDMI, tomas de corriente y puertos USB-C', 1, 'UNIT', 4500.00, 3, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (13, 'PROD-ESC-005', 'Escritorio Plegable HomeOffice SpaceSaver 100x50', 'Escritorio plegable de fácil almacenamiento ideal para espacios reducidos con base de acero esmaltado y acabado en roble claro', 1, 'UNIT', 890.00, 6, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (14, 'PROD-SIL-003', 'Silla Ergonómica Gamer Pro Lumbar Supreme', 'Silla de alto confort con mecanismo reclinable 180 grados, cojines magnéticos cervical y lumbar, tapizado transpirable de microfibra', 2, 'UNIT', 2650.00, 5, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (15, 'PROD-SIL-004', 'Silla de Conferencia Tapizada Minimalist', 'Silla para salas de reunión con diseño contemporáneo, base trineo cromada y tapicería ignífuga de alta resistencia a la fricción', 2, 'UNIT', 950.00, 8, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (16, 'PROD-SIL-005', 'Taburete Activo Ergonómico con Balanceo Sit-Stand', 'Asiento dinámico para alternar entre sentado y de pie, estimula la postura activa y fortalece el core abdominal', 2, 'UNIT', 1120.00, 5, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (17, 'PROD-ACC-002', 'Soporte Elevador de Laptop de Aluminio Ventilado', 'Base articulada y plegable para portátil de 11 a 17 pulgadas con ranuras de ventilación térmica y topes antideslizantes de silicona', 3, 'UNIT', 320.00, 10, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (18, 'PROD-ACC-003', 'Reposapiés Ergonómico Ajustable con Rodillos de Masaje', 'Plataforma con ajuste de inclinación de 3 niveles y superficie geométrica texturizada para mejorar la circulación sanguínea', 3, 'UNIT', 260.00, 8, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (19, 'PROD-ACC-004', 'Soporte para CPU Bajo Escritorio de Giro 360°', 'Montura metálica suspendida de ancho y alto regulables con rotación de 360 grados para acceso inmediato a cables traseros', 3, 'UNIT', 390.00, 6, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (20, 'PROD-ACC-005', 'Cojín Ortopédico de Espuma Viscoelástica para Asiento', 'Cojín ergonómico con corte ergonómico en U para alivio de presión en el cóccix, espuma con memoria y funda lavable', 3, 'UNIT', 210.00, 12, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (21, 'PROD-ILU-002', 'Lámpara de Escritorio LED con Brazo Articulado y Carga Qi', 'Lámpara de sobremesa con panel táctil de 5 tonos de temperatura, regulador continuo de brillo y base con cargador inalámbrico rápido', 4, 'UNIT', 520.00, 6, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (22, 'PROD-ILU-003', 'Tira de Luz LED RGB Ambiental con Control Inteligente WiFi', 'Tira lumínica adhesiva de 2 metros para parte posterior de escritorio con sincronización musical y compatibilidad con app móvil', 4, 'UNIT', 280.00, 8, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (23, 'PROD-ILU-004', 'Foco de Pie Arquitectónico de Luz Indirecta 30W', 'Luminaria de pie en aluminio anodizado para iluminación homogénea y suave de techos, disipador térmico pasivo', 4, 'UNIT', 750.00, 4, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (24, 'PROD-ALM-002', 'Estantería Modular de Acero y Melamina 5 Niveles', 'Librero organizador industrial de alta capacidad con travesaños de refuerzo en X y repisas resistentes a la humedad', 5, 'UNIT', 1650.00, 4, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (25, 'PROD-ALM-003', 'Armario Metálico Bajo de Puertas Corredizas con Llave', 'Gabinete organizador de dos puertas deslizables suaves sobre rodamientos de teflón y dos juegos de llaves maestras', 5, 'UNIT', 1980.00, 3, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (26, 'PROD-ALM-004', 'Organizador de Documentos Vertical de Malla Metálica', 'Módulo clasificador de 4 divisiones para carpetas y expedientes en acero electroestático anticorrosión', 5, 'UNIT', 145.00, 15, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (27, 'PROD-CAB-001', 'Canaleta Pasacables Espinal Articulada para StandDesk', 'Canal guía flexible tipo espina de pez que acompaña verticalmente el movimiento de escritorios elevables eléctricos', 6, 'UNIT', 220.00, 10, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (28, 'PROD-CAB-002', 'Bandeja Pasacables de Malla Metálica Bajo Mesa', 'Bandeja suspendida bajo mesa para alojar fuentes de poder, adaptadores y multicontactos evitando saturación de cables en el suelo', 6, 'UNIT', 180.00, 10, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (29, 'PROD-SET-004', 'Set de Regletas con Protección de Sobretensión y USB-C PD', 'Pack de dos regletas supresoras de picos de 900 Joules con 6 salidas AC, interruptor térmico y puertos USB de carga inteligente', 6, 'SET', 360.00, 8, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.products VALUES (30, 'PROD-SET-005', 'Set de Paneles Acústicos Fonoabsorbentes para Escritorio', 'Conjunto de dos paneles divisorios acústicos en fieltro PET reciclado de alta densidad con abrazaderas de fijación a mesa', 3, 'SET', 490.00, 6, true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');


--
-- Data for Name: inventory; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.inventory VALUES (1, 1, 7, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (2, 2, 9, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (3, 3, 11, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (4, 4, 7, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (5, 5, 14, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (6, 6, 24, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (7, 7, 19, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (8, 8, 17, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (9, 9, 10, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (10, 10, 35, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (21, 11, 5, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (23, 13, 12, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (24, 14, 10, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (25, 15, 8, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (26, 16, 7, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (27, 17, 26, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (28, 18, 18, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (29, 19, 14, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (30, 20, 19, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (31, 21, 13, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (32, 22, 17, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (33, 23, 9, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (34, 24, 7, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (35, 25, 6, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (36, 26, 23, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (37, 27, 20, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (39, 29, 17, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (40, 30, 12, '2026-09-17 13:28:12.559058-06');
INSERT INTO public.inventory VALUES (22, 12, 13, '2026-09-17 18:11:09.936154-06');
INSERT INTO public.inventory VALUES (38, 28, 26, '2026-09-17 19:50:25.686331-06');


--
-- Data for Name: purchase_statuses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.purchase_statuses VALUES (1, 'SOLICITADA', 'Orden de compra solicitada y pendiente de autorización por gerencia', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.purchase_statuses VALUES (2, 'APROBADA', 'Orden de compra autorizada y transmitida formalmente al proveedor', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.purchase_statuses VALUES (3, 'EN_TRANSITO', 'Mercadería despachada por el proveedor en tránsito a bodega', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.purchase_statuses VALUES (4, 'RECIBIDA', 'Mercadería recibida satisfactoriamente en bodega con ingreso a inventario', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.purchase_statuses VALUES (5, 'CANCELADA', 'Orden de compra anulada por el departamento de compras o proveedor', true, '2026-09-17 13:28:12.381631-06');


--
-- Data for Name: suppliers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.suppliers VALUES (1, 'Mobiliario y Ergonomía de Guatemala S.A.', 'NIT-9845123-1', 'Carlos Alberto Morales Soto', 'contacto@mobiliariogt.com', 'Calzada Roosevelt 14-25 Zona 11, Ciudad de Guatemala', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.suppliers VALUES (2, 'Distribuidora Tecnológica del Altiplano S.A.', 'NIT-6523190-4', 'Andrea Beatriz Fuentes López', 'ventas@distecnoaltiplano.gt', 'Avenida Las Américas 8-40 Zona 13, Ciudad de Guatemala', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.suppliers VALUES (3, 'Importaciones y Diseños Corporativos de Centroamérica', 'NIT-1123489-K', 'Roberto Gómez Estrada', 'rgomez@disenoscorp.com.gt', '7ma Avenida 12-23 Zona 9, Ciudad de Guatemala', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.suppliers VALUES (4, 'Soluciones Metálicas y Ensambles Industriales S.A.', 'NIT-4432109-8', 'Lucía Méndez Castillo', 'servicioalcliente@metalicasindustriales.gt', 'Kilómetro 18.5 Carretera a El Salvador, Fraijanes', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.suppliers VALUES (5, 'Accesorios y Ergonomía Profesional del Sur S.A.', 'NIT-7788990-2', 'Fernando José Castillo Paz', 'info@ergonomiaprosur.gt', 'Boulevard Los Próceres 24-69 Zona 10, Ciudad de Guatemala', true, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.suppliers VALUES (6, 'Luminarias y Sistemas Electrónicos de Vanguardia S.A.', 'NIT-3344556-7', 'Mariana Sofía Estrada Morales', 'ventas@lumivanguardia.com.gt', '15 Avenida 3-40 Zona 10, Edificio Géminis 10, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.suppliers VALUES (7, 'Maderas y Estructuras Modulares del Norte S.A.', 'NIT-8877665-1', 'Gustavo Adolfo Palacios Reyes', 'gpalacios@maderasdelnorte.gt', 'Km 14.5 Carretera al Atlántico, Zona 17, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.suppliers VALUES (8, 'Suministros Globales de Confort y Oficina S.A.', 'NIT-5566778-9', 'Patricia Elena Velásquez Cruz', 'info@suministrosconfort.com.gt', 'Calzada San Juan 32-10 Zona 7, Centro Empresarial, Ciudad de Guatemala', true, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');


--
-- Data for Name: purchases; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.purchases VALUES (1, 2, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-08-23 13:28:12.381631-06', 31250.00, 3750.00, 35000.00, 'Lote de sillas ejecutivas de alta gama y operativas ergonómicas', '2026-08-23 13:28:12.381631-06', '2026-08-23 13:28:12.381631-06');
INSERT INTO public.purchases VALUES (2, 1, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-08-18 13:28:12.381631-06', 36000.00, 4320.00, 40320.00, 'Adquisición inicial de escritorios eléctricos y compactos para surtido de tienda', '2026-08-18 13:28:12.381631-06', '2026-08-18 13:28:12.381631-06');
INSERT INTO public.purchases VALUES (3, 4, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-09-07 13:28:12.381631-06', 9840.00, 1180.80, 11020.80, 'Recepción de cajoneras rodantes de tres gavetas en acero reforzado', '2026-09-07 13:28:12.381631-06', '2026-09-07 13:28:12.381631-06');
INSERT INTO public.purchases VALUES (4, 3, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-09-02 13:28:12.381631-06', 5600.00, 672.00, 6272.00, 'Adquisición de barras de luz LED para monitor con dial inalámbrico', '2026-09-02 13:28:12.381631-06', '2026-09-02 13:28:12.381631-06');
INSERT INTO public.purchases VALUES (5, 5, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-08-28 13:28:12.381631-06', 22950.00, 2754.00, 25704.00, 'Reabastecimiento de brazos dobles para monitores y sets ergonómicos', '2026-08-28 13:28:12.381631-06', '2026-08-28 13:28:12.381631-06');
INSERT INTO public.purchases VALUES (7, 4, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-09-05 13:28:12.559058-06', 23910.00, 2869.20, 26779.20, 'Recepción de estanterías metálicas de 5 niveles, armarios y organizadores verticales', '2026-09-05 13:28:12.559058-06', '2026-09-05 13:28:12.559058-06');
INSERT INTO public.purchases VALUES (8, 5, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-08-31 13:28:12.559058-06', 10250.00, 1230.00, 11480.00, 'Lote de elevadores de laptop en aluminio, reposapiés ajustables y soportes para CPU', '2026-08-31 13:28:12.559058-06', '2026-08-31 13:28:12.559058-06');
INSERT INTO public.purchases VALUES (9, 5, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-09-11 13:28:12.559058-06', 6250.00, 750.00, 7000.00, 'Reabastecimiento de paneles acústicos fonoabsorbentes y soportes para laptop', '2026-09-11 13:28:12.559058-06', '2026-09-11 13:28:12.559058-06');
INSERT INTO public.purchases VALUES (10, 3, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-09-08 13:28:12.559058-06', 10175.00, 1221.00, 11396.00, 'Adquisición de canaletas espinales, bandejas pasacables y regletas con protección', '2026-09-08 13:28:12.559058-06', '2026-09-08 13:28:12.559058-06');
INSERT INTO public.purchases VALUES (11, 6, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-09-03 13:28:12.559058-06', 12800.00, 1536.00, 14336.00, 'Suministro de lámparas articuladas Qi, tiras LED WiFi y focos arquitectónicos', '2026-09-03 13:28:12.559058-06', '2026-09-03 13:28:12.559058-06');
INSERT INTO public.purchases VALUES (12, 8, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-08-29 13:28:12.559058-06', 12920.00, 1550.40, 14470.40, 'Adquisición de sillas de conferencia minimalistas y accesorios de confort postural', '2026-08-29 13:28:12.559058-06', '2026-08-29 13:28:12.559058-06');
INSERT INTO public.purchases VALUES (13, 7, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-08-20 13:28:12.559058-06', 30700.00, 3684.00, 34384.00, 'Adquisición de escritorios esquineros en L y mesas para salas de conferencias', '2026-08-20 13:28:12.559058-06', '2026-08-20 13:28:12.559058-06');
INSERT INTO public.purchases VALUES (14, 7, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-08-24 13:28:12.559058-06', 8700.00, 1044.00, 9744.00, 'Lote de escritorios plegables compactos para trabajo remoto', '2026-08-24 13:28:12.559058-06', '2026-08-24 13:28:12.559058-06');
INSERT INTO public.purchases VALUES (15, 2, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 4, '2026-08-26 13:28:12.559058-06', 28400.00, 3408.00, 31808.00, 'Surtido de sillas gamer ergonómicas y taburetes activos sit-stand', '2026-08-26 13:28:12.559058-06', '2026-08-26 13:28:12.559058-06');
INSERT INTO public.purchases VALUES (6, 1, '82f586e1-0c57-47c1-8832-5eab20d9d15f', 2, '2026-09-14 13:28:12.559058-06', 19250.00, 2310.00, 21560.00, 'Segunda compra de reabastecimiento de escritorios eléctricos y compactos', '2026-09-14 13:28:12.559058-06', '2026-09-17 13:35:41.836085-06');


--
-- Data for Name: purchase_items; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.purchase_items VALUES (1, 2, 1, 10, 2350.00, 23500.00, '2026-08-18 13:28:12.381631-06');
INSERT INTO public.purchase_items VALUES (2, 2, 2, 10, 1250.00, 12500.00, '2026-08-18 13:28:12.381631-06');
INSERT INTO public.purchase_items VALUES (3, 1, 3, 15, 1450.00, 21750.00, '2026-08-23 13:28:12.381631-06');
INSERT INTO public.purchase_items VALUES (4, 1, 4, 10, 950.00, 9500.00, '2026-08-23 13:28:12.381631-06');
INSERT INTO public.purchase_items VALUES (5, 5, 5, 20, 480.00, 9600.00, '2026-08-28 13:28:12.381631-06');
INSERT INTO public.purchase_items VALUES (6, 5, 6, 30, 160.00, 4800.00, '2026-08-28 13:28:12.381631-06');
INSERT INTO public.purchase_items VALUES (7, 5, 7, 25, 190.00, 4750.00, '2026-08-28 13:28:12.381631-06');
INSERT INTO public.purchase_items VALUES (8, 5, 10, 40, 95.00, 3800.00, '2026-08-28 13:28:12.381631-06');
INSERT INTO public.purchase_items VALUES (9, 4, 8, 20, 280.00, 5600.00, '2026-09-02 13:28:12.381631-06');
INSERT INTO public.purchase_items VALUES (10, 3, 9, 12, 820.00, 9840.00, '2026-09-07 13:28:12.381631-06');
INSERT INTO public.purchase_items VALUES (11, 13, 11, 8, 1900.00, 15200.00, '2026-08-20 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (12, 13, 12, 5, 3100.00, 15500.00, '2026-08-20 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (13, 14, 13, 15, 580.00, 8700.00, '2026-08-24 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (14, 15, 14, 12, 1750.00, 21000.00, '2026-08-26 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (15, 15, 16, 10, 740.00, 7400.00, '2026-08-26 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (16, 12, 15, 16, 620.00, 9920.00, '2026-08-29 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (17, 12, 20, 25, 120.00, 3000.00, '2026-08-29 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (18, 8, 17, 20, 190.00, 3800.00, '2026-08-31 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (19, 8, 18, 20, 150.00, 3000.00, '2026-08-31 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (20, 8, 19, 15, 230.00, 3450.00, '2026-08-31 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (21, 11, 21, 15, 320.00, 4800.00, '2026-09-03 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (22, 11, 22, 20, 160.00, 3200.00, '2026-09-03 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (23, 11, 23, 10, 480.00, 4800.00, '2026-09-03 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (24, 7, 24, 10, 1080.00, 10800.00, '2026-09-05 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (25, 7, 25, 8, 1320.00, 10560.00, '2026-09-05 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (26, 7, 26, 30, 85.00, 2550.00, '2026-09-05 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (27, 10, 27, 25, 130.00, 3250.00, '2026-09-08 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (28, 10, 28, 25, 105.00, 2625.00, '2026-09-08 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (29, 10, 29, 20, 215.00, 4300.00, '2026-09-08 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (30, 9, 30, 15, 290.00, 4350.00, '2026-09-11 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (31, 9, 17, 10, 190.00, 1900.00, '2026-09-11 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (32, 6, 1, 5, 2350.00, 11750.00, '2026-09-14 13:28:12.559058-06');
INSERT INTO public.purchase_items VALUES (33, 6, 2, 6, 1250.00, 7500.00, '2026-09-14 13:28:12.559058-06');


--
-- Data for Name: inventory_lots; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.inventory_lots VALUES (1, 1, 1, 10, 8, 2350.00, '2026-08-18 13:28:12.381631-06', '2026-08-18 13:28:12.381631-06');
INSERT INTO public.inventory_lots VALUES (2, 2, 2, 10, 9, 1250.00, '2026-08-18 13:28:12.381631-06', '2026-08-18 13:28:12.381631-06');
INSERT INTO public.inventory_lots VALUES (3, 3, 3, 15, 12, 1450.00, '2026-08-23 13:28:12.381631-06', '2026-08-23 13:28:12.381631-06');
INSERT INTO public.inventory_lots VALUES (4, 4, 4, 10, 8, 950.00, '2026-08-23 13:28:12.381631-06', '2026-08-23 13:28:12.381631-06');
INSERT INTO public.inventory_lots VALUES (5, 5, 5, 20, 16, 480.00, '2026-08-28 13:28:12.381631-06', '2026-08-28 13:28:12.381631-06');
INSERT INTO public.inventory_lots VALUES (6, 6, 6, 30, 25, 160.00, '2026-08-28 13:28:12.381631-06', '2026-08-28 13:28:12.381631-06');
INSERT INTO public.inventory_lots VALUES (7, 7, 7, 25, 20, 190.00, '2026-08-28 13:28:12.381631-06', '2026-08-28 13:28:12.381631-06');
INSERT INTO public.inventory_lots VALUES (8, 8, 9, 20, 17, 280.00, '2026-09-02 13:28:12.381631-06', '2026-09-02 13:28:12.381631-06');
INSERT INTO public.inventory_lots VALUES (9, 9, 10, 12, 10, 820.00, '2026-09-07 13:28:12.381631-06', '2026-09-07 13:28:12.381631-06');
INSERT INTO public.inventory_lots VALUES (10, 10, 8, 40, 35, 95.00, '2026-08-28 13:28:12.381631-06', '2026-08-28 13:28:12.381631-06');
INSERT INTO public.inventory_lots VALUES (11, 1, 32, 5, 4, 2350.00, '2026-09-14 13:28:12.559058-06', '2026-09-14 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (12, 2, 33, 6, 6, 1250.00, '2026-09-14 13:28:12.559058-06', '2026-09-14 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (13, 11, 11, 8, 5, 1900.00, '2026-08-20 13:28:12.559058-06', '2026-08-20 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (14, 12, 12, 5, 3, 3100.00, '2026-08-20 13:28:12.559058-06', '2026-08-20 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (15, 13, 13, 15, 12, 580.00, '2026-08-24 13:28:12.559058-06', '2026-08-24 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (16, 14, 14, 12, 10, 1750.00, '2026-08-26 13:28:12.559058-06', '2026-08-26 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (17, 15, 16, 16, 8, 620.00, '2026-08-29 13:28:12.559058-06', '2026-08-29 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (18, 16, 15, 10, 7, 740.00, '2026-08-26 13:28:12.559058-06', '2026-08-26 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (19, 17, 31, 10, 10, 190.00, '2026-09-11 13:28:12.559058-06', '2026-09-11 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (20, 17, 18, 20, 16, 190.00, '2026-08-31 13:28:12.559058-06', '2026-08-31 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (21, 18, 19, 20, 18, 150.00, '2026-08-31 13:28:12.559058-06', '2026-08-31 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (22, 19, 20, 15, 14, 230.00, '2026-08-31 13:28:12.559058-06', '2026-08-31 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (23, 20, 17, 25, 19, 120.00, '2026-08-29 13:28:12.559058-06', '2026-08-29 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (24, 21, 21, 15, 13, 320.00, '2026-09-03 13:28:12.559058-06', '2026-09-03 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (25, 22, 22, 20, 17, 160.00, '2026-09-03 13:28:12.559058-06', '2026-09-03 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (26, 23, 23, 10, 9, 480.00, '2026-09-03 13:28:12.559058-06', '2026-09-03 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (27, 24, 24, 10, 7, 1080.00, '2026-09-05 13:28:12.559058-06', '2026-09-05 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (28, 25, 25, 8, 6, 1320.00, '2026-09-05 13:28:12.559058-06', '2026-09-05 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (29, 26, 26, 30, 23, 85.00, '2026-09-05 13:28:12.559058-06', '2026-09-05 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (30, 27, 27, 25, 20, 130.00, '2026-09-08 13:28:12.559058-06', '2026-09-08 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (32, 29, 29, 20, 17, 215.00, '2026-09-08 13:28:12.559058-06', '2026-09-08 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (33, 30, 30, 15, 12, 290.00, '2026-09-11 13:28:12.559058-06', '2026-09-11 13:28:12.559058-06');
INSERT INTO public.inventory_lots VALUES (31, 28, 28, 25, 20, 105.00, '2026-09-08 13:28:12.559058-06', '2026-09-08 13:28:12.559058-06');


--
-- Data for Name: inventory_movements; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.inventory_movements VALUES (1, 1, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 10, 10, 'PURCHASE', 2, 'PEPS', 'Ingreso inicial por recepción de compra #1', '2026-08-18 13:28:12.381631-06');
INSERT INTO public.inventory_movements VALUES (2, 3, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 15, 15, 'PURCHASE', 1, 'PEPS', 'Ingreso inicial por recepción de compra #2', '2026-08-23 13:28:12.381631-06');
INSERT INTO public.inventory_movements VALUES (3, 5, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 20, 20, 'PURCHASE', 5, 'PEPS', 'Ingreso inicial por recepción de compra #3', '2026-08-28 13:28:12.381631-06');
INSERT INTO public.inventory_movements VALUES (4, 6, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 30, 30, 'PURCHASE', 5, 'PEPS', 'Ingreso inicial por recepción de compra #3', '2026-08-28 13:28:12.381631-06');
INSERT INTO public.inventory_movements VALUES (5, 8, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 20, 20, 'PURCHASE', 4, 'PEPS', 'Ingreso inicial por recepción de compra #4', '2026-09-02 13:28:12.381631-06');
INSERT INTO public.inventory_movements VALUES (6, 9, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 12, 12, 'PURCHASE', 3, 'PEPS', 'Ingreso inicial por recepción de compra #5', '2026-09-07 13:28:12.381631-06');
INSERT INTO public.inventory_movements VALUES (7, 1, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 5, 5, 'PURCHASE', 6, 'PEPS', 'Ingreso por recepción de compra #15 - PROD-ESC-001', '2026-09-14 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (8, 2, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 6, 6, 'PURCHASE', 6, 'PEPS', 'Ingreso por recepción de compra #15 - PROD-ESC-002', '2026-09-14 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (9, 25, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 8, 8, 'PURCHASE', 7, 'PEPS', 'Ingreso por recepción de compra #12 - PROD-ALM-003', '2026-09-05 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (10, 24, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 10, 10, 'PURCHASE', 7, 'PEPS', 'Ingreso por recepción de compra #12 - PROD-ALM-002', '2026-09-05 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (11, 26, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 30, 30, 'PURCHASE', 7, 'PEPS', 'Ingreso por recepción de compra #12 - PROD-ALM-004', '2026-09-05 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (12, 17, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 20, 20, 'PURCHASE', 8, 'PEPS', 'Ingreso por recepción de compra #10 - PROD-ACC-002', '2026-08-31 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (13, 18, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 20, 20, 'PURCHASE', 8, 'PEPS', 'Ingreso por recepción de compra #10 - PROD-ACC-003', '2026-08-31 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (14, 19, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 15, 15, 'PURCHASE', 8, 'PEPS', 'Ingreso por recepción de compra #10 - PROD-ACC-004', '2026-08-31 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (15, 17, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 10, 10, 'PURCHASE', 9, 'PEPS', 'Ingreso por recepción de compra #14 - PROD-ACC-002', '2026-09-11 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (16, 30, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 15, 15, 'PURCHASE', 9, 'PEPS', 'Ingreso por recepción de compra #14 - PROD-SET-005', '2026-09-11 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (17, 29, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 20, 20, 'PURCHASE', 10, 'PEPS', 'Ingreso por recepción de compra #13 - PROD-SET-004', '2026-09-08 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (18, 28, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 25, 25, 'PURCHASE', 10, 'PEPS', 'Ingreso por recepción de compra #13 - PROD-CAB-002', '2026-09-08 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (19, 27, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 25, 25, 'PURCHASE', 10, 'PEPS', 'Ingreso por recepción de compra #13 - PROD-CAB-001', '2026-09-08 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (20, 22, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 20, 20, 'PURCHASE', 11, 'PEPS', 'Ingreso por recepción de compra #11 - PROD-ILU-003', '2026-09-03 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (21, 23, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 10, 10, 'PURCHASE', 11, 'PEPS', 'Ingreso por recepción de compra #11 - PROD-ILU-004', '2026-09-03 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (22, 21, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 15, 15, 'PURCHASE', 11, 'PEPS', 'Ingreso por recepción de compra #11 - PROD-ILU-002', '2026-09-03 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (23, 15, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 16, 16, 'PURCHASE', 12, 'PEPS', 'Ingreso por recepción de compra #9 - PROD-SIL-004', '2026-08-29 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (24, 20, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 25, 25, 'PURCHASE', 12, 'PEPS', 'Ingreso por recepción de compra #9 - PROD-ACC-005', '2026-08-29 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (25, 11, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 8, 8, 'PURCHASE', 13, 'PEPS', 'Ingreso por recepción de compra #6 - PROD-ESC-003', '2026-08-20 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (26, 12, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 5, 5, 'PURCHASE', 13, 'PEPS', 'Ingreso por recepción de compra #6 - PROD-ESC-004', '2026-08-20 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (27, 13, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 15, 15, 'PURCHASE', 14, 'PEPS', 'Ingreso por recepción de compra #7 - PROD-ESC-005', '2026-08-24 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (28, 14, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 12, 12, 'PURCHASE', 15, 'PEPS', 'Ingreso por recepción de compra #8 - PROD-SIL-003', '2026-08-26 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (29, 16, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'ENTRADA_COMPRA', 10, 10, 'PURCHASE', 15, 'PEPS', 'Ingreso por recepción de compra #8 - PROD-SIL-005', '2026-08-26 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (30, 21, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 13, 'SALE', 6, 'PEPS', 'Salida por venta FACT-001-00021 - PROD-ILU-002', '2026-09-14 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (31, 29, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 17, 'SALE', 6, 'PEPS', 'Salida por venta FACT-001-00021 - PROD-SET-004', '2026-09-14 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (32, 17, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 26, 'SALE', 7, 'PEPS', 'Salida por venta FACT-001-00022 - PROD-ACC-002', '2026-09-14 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (33, 20, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 19, 'SALE', 7, 'PEPS', 'Salida por venta FACT-001-00022 - PROD-ACC-005', '2026-09-14 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (34, 30, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 12, 'SALE', 8, 'PEPS', 'Salida por venta FACT-001-00023 - PROD-SET-005', '2026-09-15 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (35, 1, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 7, 'SALE', 8, 'PEPS', 'Salida por venta FACT-001-00023 - PROD-ESC-001', '2026-09-15 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (36, 5, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 14, 'SALE', 8, 'PEPS', 'Salida por venta FACT-001-00023 - PROD-ACC-001', '2026-09-15 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (37, 16, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 7, 'SALE', 9, 'PEPS', 'Salida por venta FACT-001-00024 - PROD-SIL-005', '2026-09-16 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (38, 22, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 17, 'SALE', 9, 'PEPS', 'Salida por venta FACT-001-00024 - PROD-ILU-003', '2026-09-16 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (39, 25, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 6, 'SALE', 10, 'PEPS', 'Salida por venta FACT-001-00025 - PROD-ALM-003', '2026-09-16 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (40, 27, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 20, 'SALE', 10, 'PEPS', 'Salida por venta FACT-001-00025 - PROD-CAB-001', '2026-09-16 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (41, 11, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 5, 'SALE', 10, 'PEPS', 'Salida por venta FACT-001-00025 - PROD-ESC-003', '2026-09-16 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (42, 3, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 11, 'SALE', 11, 'PEPS', 'Salida por venta FACT-001-00006 - PROD-SIL-001', '2026-08-22 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (43, 11, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 5, 'SALE', 11, 'PEPS', 'Salida por venta FACT-001-00006 - PROD-ESC-003', '2026-08-22 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (44, 13, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 12, 'SALE', 12, 'PEPS', 'Salida por venta FACT-001-00007 - PROD-ESC-005', '2026-08-25 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (45, 12, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 3, 'SALE', 12, 'PEPS', 'Salida por venta FACT-001-00007 - PROD-ESC-004', '2026-08-25 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (46, 14, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 10, 'SALE', 13, 'PEPS', 'Salida por venta FACT-001-00008 - PROD-SIL-003', '2026-08-27 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (47, 7, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 19, 'SALE', 13, 'PEPS', 'Salida por venta FACT-001-00008 - PROD-SET-002', '2026-08-27 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (48, 15, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -4, 8, 'SALE', 14, 'PEPS', 'Salida por venta FACT-001-00009 - PROD-SIL-004', '2026-08-30 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (49, 20, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -4, 19, 'SALE', 14, 'PEPS', 'Salida por venta FACT-001-00009 - PROD-ACC-005', '2026-08-30 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (50, 17, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 26, 'SALE', 15, 'PEPS', 'Salida por venta FACT-001-00010 - PROD-ACC-002', '2026-09-01 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (51, 18, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 18, 'SALE', 15, 'PEPS', 'Salida por venta FACT-001-00010 - PROD-ACC-003', '2026-09-01 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (52, 11, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 5, 'SALE', 16, 'PEPS', 'Salida por venta FACT-001-00011 - PROD-ESC-003', '2026-09-02 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (53, 16, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 7, 'SALE', 16, 'PEPS', 'Salida por venta FACT-001-00011 - PROD-SIL-005', '2026-09-02 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (54, 19, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 14, 'SALE', 16, 'PEPS', 'Salida por venta FACT-001-00011 - PROD-ACC-004', '2026-09-02 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (55, 22, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 17, 'SALE', 17, 'PEPS', 'Salida por venta FACT-001-00012 - PROD-ILU-003', '2026-09-04 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (56, 21, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 13, 'SALE', 17, 'PEPS', 'Salida por venta FACT-001-00012 - PROD-ILU-002', '2026-09-04 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (57, 25, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 6, 'SALE', 18, 'PEPS', 'Salida por venta FACT-001-00013 - PROD-ALM-003', '2026-09-06 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (58, 26, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -3, 23, 'SALE', 18, 'PEPS', 'Salida por venta FACT-001-00013 - PROD-ALM-004', '2026-09-06 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (59, 24, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 7, 'SALE', 18, 'PEPS', 'Salida por venta FACT-001-00013 - PROD-ALM-002', '2026-09-06 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (60, 4, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 7, 'SALE', 19, 'PEPS', 'Salida por venta FACT-001-00014 - PROD-SIL-002', '2026-09-07 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (61, 13, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 12, 'SALE', 19, 'PEPS', 'Salida por venta FACT-001-00014 - PROD-ESC-005', '2026-09-07 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (62, 27, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -3, 20, 'SALE', 20, 'PEPS', 'Salida por venta FACT-001-00015 - PROD-CAB-001', '2026-09-09 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (63, 29, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 17, 'SALE', 20, 'PEPS', 'Salida por venta FACT-001-00015 - PROD-SET-004', '2026-09-09 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (64, 28, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -3, 22, 'SALE', 20, 'PEPS', 'Salida por venta FACT-001-00015 - PROD-CAB-002', '2026-09-09 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (65, 23, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 9, 'SALE', 21, 'PEPS', 'Salida por venta FACT-001-00016 - PROD-ILU-004', '2026-09-10 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (66, 6, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 24, 'SALE', 21, 'PEPS', 'Salida por venta FACT-001-00016 - PROD-SET-001', '2026-09-10 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (67, 26, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -4, 23, 'SALE', 22, 'PEPS', 'Salida por venta FACT-001-00017 - PROD-ALM-004', '2026-09-11 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (68, 24, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 7, 'SALE', 22, 'PEPS', 'Salida por venta FACT-001-00017 - PROD-ALM-002', '2026-09-11 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (69, 14, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 10, 'SALE', 23, 'PEPS', 'Salida por venta FACT-001-00018 - PROD-SIL-003', '2026-09-12 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (70, 17, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 26, 'SALE', 23, 'PEPS', 'Salida por venta FACT-001-00018 - PROD-ACC-002', '2026-09-12 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (71, 15, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -4, 8, 'SALE', 24, 'PEPS', 'Salida por venta FACT-001-00019 - PROD-SIL-004', '2026-09-12 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (72, 12, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 3, 'SALE', 24, 'PEPS', 'Salida por venta FACT-001-00019 - PROD-ESC-004', '2026-09-12 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (73, 30, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 12, 'SALE', 25, 'PEPS', 'Salida por venta FACT-001-00020 - PROD-SET-005', '2026-09-13 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (74, 18, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -1, 18, 'SALE', 25, 'PEPS', 'Salida por venta FACT-001-00020 - PROD-ACC-003', '2026-09-13 13:28:12.559058-06');
INSERT INTO public.inventory_movements VALUES (75, 28, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'AJUSTE_ENTRADA', 6, 28, 'MANUAL_ADJUSTMENT', NULL, 'PEPS', 'Conteo trimestral', '2026-09-17 18:10:22.094808-06');
INSERT INTO public.inventory_movements VALUES (76, 12, '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', 'AJUSTE_ENTRADA', 10, 13, 'MANUAL_ADJUSTMENT', NULL, 'PEPS', 'Re ajuste de materilaes', '2026-09-17 18:11:09.938818-06');
INSERT INTO public.inventory_movements VALUES (77, 28, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 'SALIDA_VENTA', -2, 26, 'SALE', 26, 'PEPS', 'Salida por venta FACT-001-00026', '2026-09-17 19:50:25.687491-06');


--
-- Data for Name: inventory_movement_lots; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.inventory_movement_lots VALUES (1, 1, 1, 10, 2350.00);
INSERT INTO public.inventory_movement_lots VALUES (2, 2, 3, 15, 1450.00);
INSERT INTO public.inventory_movement_lots VALUES (3, 3, 5, 20, 480.00);
INSERT INTO public.inventory_movement_lots VALUES (4, 4, 6, 30, 160.00);
INSERT INTO public.inventory_movement_lots VALUES (5, 5, 8, 20, 280.00);
INSERT INTO public.inventory_movement_lots VALUES (6, 6, 9, 12, 820.00);
INSERT INTO public.inventory_movement_lots VALUES (7, 7, 11, 5, 2350.00);
INSERT INTO public.inventory_movement_lots VALUES (8, 8, 12, 6, 1250.00);
INSERT INTO public.inventory_movement_lots VALUES (9, 25, 13, 8, 1900.00);
INSERT INTO public.inventory_movement_lots VALUES (10, 26, 14, 5, 3100.00);
INSERT INTO public.inventory_movement_lots VALUES (11, 27, 15, 15, 580.00);
INSERT INTO public.inventory_movement_lots VALUES (12, 28, 16, 12, 1750.00);
INSERT INTO public.inventory_movement_lots VALUES (13, 23, 17, 16, 620.00);
INSERT INTO public.inventory_movement_lots VALUES (14, 29, 18, 10, 740.00);
INSERT INTO public.inventory_movement_lots VALUES (15, 15, 19, 10, 190.00);
INSERT INTO public.inventory_movement_lots VALUES (16, 12, 20, 20, 190.00);
INSERT INTO public.inventory_movement_lots VALUES (17, 13, 21, 20, 150.00);
INSERT INTO public.inventory_movement_lots VALUES (18, 14, 22, 15, 230.00);
INSERT INTO public.inventory_movement_lots VALUES (19, 24, 23, 25, 120.00);
INSERT INTO public.inventory_movement_lots VALUES (20, 22, 24, 15, 320.00);
INSERT INTO public.inventory_movement_lots VALUES (21, 20, 25, 20, 160.00);
INSERT INTO public.inventory_movement_lots VALUES (22, 21, 26, 10, 480.00);
INSERT INTO public.inventory_movement_lots VALUES (23, 10, 27, 10, 1080.00);
INSERT INTO public.inventory_movement_lots VALUES (24, 9, 28, 8, 1320.00);
INSERT INTO public.inventory_movement_lots VALUES (25, 11, 29, 30, 85.00);
INSERT INTO public.inventory_movement_lots VALUES (26, 19, 30, 25, 130.00);
INSERT INTO public.inventory_movement_lots VALUES (27, 18, 31, 25, 105.00);
INSERT INTO public.inventory_movement_lots VALUES (28, 17, 32, 20, 215.00);
INSERT INTO public.inventory_movement_lots VALUES (29, 16, 33, 15, 290.00);
INSERT INTO public.inventory_movement_lots VALUES (30, 77, 31, 2, 105.00);


--
-- Data for Name: payment_methods; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.payment_methods VALUES (1, 'EFECTIVO', 'Pago en efectivo en moneda de curso legal (Quetzales)', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.payment_methods VALUES (2, 'TARJETA_CREDITO', 'Pago mediante tarjeta de crédito internacional o local Visa y MasterCard', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.payment_methods VALUES (3, 'TARJETA_DEBITO', 'Pago directo mediante débito bancario autorizado', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.payment_methods VALUES (4, 'TRANSEFERENCIA_BANCARIA', 'Transferencia bancaria directa inmediata vía ACH o banca en línea', true, '2026-09-17 13:28:12.381631-06');


--
-- Data for Name: phone_numbers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.phone_numbers VALUES (1, '+502 5544-3326');
INSERT INTO public.phone_numbers VALUES (2, '+502 6677-1124');
INSERT INTO public.phone_numbers VALUES (3, '+502 2334-5501');
INSERT INTO public.phone_numbers VALUES (4, '+502 2399-4455');
INSERT INTO public.phone_numbers VALUES (5, '+502 2210-9983');
INSERT INTO public.phone_numbers VALUES (6, '+502 2456-7892');
INSERT INTO public.phone_numbers VALUES (7, '+502 2388-7711');
INSERT INTO public.phone_numbers VALUES (8, '+502 2255-8833');
INSERT INTO public.phone_numbers VALUES (9, '+502 2433-9900');


--
-- Data for Name: phone_suppliers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.phone_suppliers VALUES (1, 1, 3);
INSERT INTO public.phone_suppliers VALUES (2, 2, 6);
INSERT INTO public.phone_suppliers VALUES (3, 3, 5);
INSERT INTO public.phone_suppliers VALUES (4, 4, 2);
INSERT INTO public.phone_suppliers VALUES (5, 5, 4);
INSERT INTO public.phone_suppliers VALUES (6, 1, 1);
INSERT INTO public.phone_suppliers VALUES (7, 6, 7);
INSERT INTO public.phone_suppliers VALUES (8, 7, 8);
INSERT INTO public.phone_suppliers VALUES (9, 8, 9);


--
-- Data for Name: resources; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: sale_statuses; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sale_statuses VALUES (1, 'PENDIENTE', 'Venta registrada pendiente de validación de pago o preparación', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.sale_statuses VALUES (2, 'PAGADA', 'Pago recibido y validado, pendiente de despacho o entrega al cliente', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.sale_statuses VALUES (3, 'ENTREGADA', 'Mercadería despachada y entregada al cliente con acuse de recibido', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.sale_statuses VALUES (4, 'COMPLETADA', 'Venta finalizada exitosamente con cobro y entrega conformes', true, '2026-09-17 13:28:12.381631-06');
INSERT INTO public.sale_statuses VALUES (5, 'CANCELADA', 'Venta anulada sin impacto financiero ni despacho de inventario', true, '2026-09-17 13:28:12.381631-06');


--
-- Data for Name: sales; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sales VALUES (1, 1, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00001', '2026-09-05 13:28:12.381631-06', 7778.00, 933.36, 8711.36, '/invoices/2026/FACT-001-00001.pdf', '2026-09-05 13:28:12.381631-06', '2026-09-05 13:28:12.381631-06');
INSERT INTO public.sales VALUES (2, 2, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00002', '2026-09-08 13:28:12.381631-06', 2540.00, 304.80, 2844.80, '/invoices/2026/FACT-001-00002.pdf', '2026-09-08 13:28:12.381631-06', '2026-09-08 13:28:12.381631-06');
INSERT INTO public.sales VALUES (3, 3, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00003', '2026-09-10 13:28:12.381631-06', 9335.00, 1120.20, 10455.20, '/invoices/2026/FACT-001-00003.pdf', '2026-09-10 13:28:12.381631-06', '2026-09-10 13:28:12.381631-06');
INSERT INTO public.sales VALUES (4, 4, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00004', '2026-09-13 13:28:12.381631-06', 2330.00, 279.60, 2609.60, '/invoices/2026/FACT-001-00004.pdf', '2026-09-13 13:28:12.381631-06', '2026-09-13 13:28:12.381631-06');
INSERT INTO public.sales VALUES (5, 5, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00005', '2026-09-15 13:28:12.381631-06', 7450.00, 894.00, 8344.00, '/invoices/2026/FACT-001-00005.pdf', '2026-09-15 13:28:12.381631-06', '2026-09-15 13:28:12.381631-06');
INSERT INTO public.sales VALUES (6, 1, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00021', '2026-09-14 13:28:12.559058-06', 880.00, 105.60, 985.60, '/invoices/2026/FACT-001-00021.pdf', '2026-09-14 13:28:12.559058-06', '2026-09-14 13:28:12.559058-06');
INSERT INTO public.sales VALUES (7, 2, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00022', '2026-09-14 13:28:12.559058-06', 740.00, 88.80, 828.80, '/invoices/2026/FACT-001-00022.pdf', '2026-09-14 13:28:12.559058-06', '2026-09-14 13:28:12.559058-06');
INSERT INTO public.sales VALUES (8, 3, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00023', '2026-09-15 13:28:12.559058-06', 6039.00, 724.68, 6763.68, '/invoices/2026/FACT-001-00023.pdf', '2026-09-15 13:28:12.559058-06', '2026-09-15 13:28:12.559058-06');
INSERT INTO public.sales VALUES (9, 4, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00024', '2026-09-16 13:28:12.559058-06', 1400.00, 168.00, 1568.00, '/invoices/2026/FACT-001-00024.pdf', '2026-09-16 13:28:12.559058-06', '2026-09-16 13:28:12.559058-06');
INSERT INTO public.sales VALUES (10, 5, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00025', '2026-09-16 13:28:12.559058-06', 5270.00, 632.40, 5902.40, '/invoices/2026/FACT-001-00025.pdf', '2026-09-16 13:28:12.559058-06', '2026-09-16 13:28:12.559058-06');
INSERT INTO public.sales VALUES (11, 6, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00006', '2026-08-22 13:28:12.559058-06', 5050.00, 606.00, 5656.00, '/invoices/2026/FACT-001-00006.pdf', '2026-08-22 13:28:12.559058-06', '2026-08-22 13:28:12.559058-06');
INSERT INTO public.sales VALUES (12, 7, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00007', '2026-08-25 13:28:12.559058-06', 6280.00, 753.60, 7033.60, '/invoices/2026/FACT-001-00007.pdf', '2026-08-25 13:28:12.559058-06', '2026-08-25 13:28:12.559058-06');
INSERT INTO public.sales VALUES (13, 8, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00008', '2026-08-27 13:28:12.559058-06', 2990.00, 358.80, 3348.80, '/invoices/2026/FACT-001-00008.pdf', '2026-08-27 13:28:12.559058-06', '2026-08-27 13:28:12.559058-06');
INSERT INTO public.sales VALUES (14, 9, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00009', '2026-08-30 13:28:12.559058-06', 4640.00, 556.80, 5196.80, '/invoices/2026/FACT-001-00009.pdf', '2026-08-30 13:28:12.559058-06', '2026-08-30 13:28:12.559058-06');
INSERT INTO public.sales VALUES (15, 10, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00010', '2026-09-01 13:28:12.559058-06', 900.00, 108.00, 1008.00, '/invoices/2026/FACT-001-00010.pdf', '2026-09-01 13:28:12.559058-06', '2026-09-01 13:28:12.559058-06');
INSERT INTO public.sales VALUES (16, 11, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00011', '2026-09-02 13:28:12.559058-06', 5480.00, 657.60, 6137.60, '/invoices/2026/FACT-001-00011.pdf', '2026-09-02 13:28:12.559058-06', '2026-09-02 13:28:12.559058-06');
INSERT INTO public.sales VALUES (17, 12, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00012', '2026-09-04 13:28:12.559058-06', 1080.00, 129.60, 1209.60, '/invoices/2026/FACT-001-00012.pdf', '2026-09-04 13:28:12.559058-06', '2026-09-04 13:28:12.559058-06');
INSERT INTO public.sales VALUES (18, 13, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00013', '2026-09-06 13:28:12.559058-06', 4065.00, 487.80, 4552.80, '/invoices/2026/FACT-001-00013.pdf', '2026-09-06 13:28:12.559058-06', '2026-09-06 13:28:12.559058-06');
INSERT INTO public.sales VALUES (19, 14, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00014', '2026-09-07 13:28:12.559058-06', 2340.00, 280.80, 2620.80, '/invoices/2026/FACT-001-00014.pdf', '2026-09-07 13:28:12.559058-06', '2026-09-07 13:28:12.559058-06');
INSERT INTO public.sales VALUES (20, 15, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00015', '2026-09-09 13:28:12.559058-06', 1920.00, 230.40, 2150.40, '/invoices/2026/FACT-001-00015.pdf', '2026-09-09 13:28:12.559058-06', '2026-09-09 13:28:12.559058-06');
INSERT INTO public.sales VALUES (21, 16, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00016', '2026-09-10 13:28:12.559058-06', 1040.00, 124.80, 1164.80, '/invoices/2026/FACT-001-00016.pdf', '2026-09-10 13:28:12.559058-06', '2026-09-10 13:28:12.559058-06');
INSERT INTO public.sales VALUES (22, 17, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00017', '2026-09-11 13:28:12.559058-06', 3880.00, 465.60, 4345.60, '/invoices/2026/FACT-001-00017.pdf', '2026-09-11 13:28:12.559058-06', '2026-09-11 13:28:12.559058-06');
INSERT INTO public.sales VALUES (23, 18, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00018', '2026-09-12 13:28:12.559058-06', 2970.00, 356.40, 3326.40, '/invoices/2026/FACT-001-00018.pdf', '2026-09-12 13:28:12.559058-06', '2026-09-12 13:28:12.559058-06');
INSERT INTO public.sales VALUES (24, 19, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00019', '2026-09-12 13:28:12.559058-06', 8300.00, 996.00, 9296.00, '/invoices/2026/FACT-001-00019.pdf', '2026-09-12 13:28:12.559058-06', '2026-09-12 13:28:12.559058-06');
INSERT INTO public.sales VALUES (25, 20, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 4, 'FACT-001-00020', '2026-09-13 13:28:12.559058-06', 750.00, 90.00, 840.00, '/invoices/2026/FACT-001-00020.pdf', '2026-09-13 13:28:12.559058-06', '2026-09-13 13:28:12.559058-06');
INSERT INTO public.sales VALUES (26, 15, '00fe08f8-ef7c-465a-a33e-2bc50667c283', 2, 'FACT-001-00026', '2026-09-18 06:00:00-06', 360.00, 43.20, 403.20, '/invoices/2026/FACT-001-00026.pdf', '2026-09-17 19:49:56.97499-06', '2026-09-17 19:50:25.721314-06');


--
-- Data for Name: sale_items; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sale_items VALUES (1, 1, 1, 2, 3499.00, 6998.00, '2026-09-05 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (2, 1, 5, 1, 780.00, 780.00, '2026-09-05 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (3, 2, 3, 1, 2200.00, 2200.00, '2026-09-08 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (4, 2, 7, 1, 340.00, 340.00, '2026-09-08 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (5, 3, 3, 2, 2200.00, 4400.00, '2026-09-10 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (6, 3, 4, 2, 1450.00, 2900.00, '2026-09-10 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (7, 3, 6, 4, 290.00, 1160.00, '2026-09-10 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (8, 3, 10, 5, 175.00, 875.00, '2026-09-10 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (9, 4, 2, 1, 1850.00, 1850.00, '2026-09-13 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (10, 4, 8, 1, 480.00, 480.00, '2026-09-13 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (11, 5, 5, 3, 780.00, 2340.00, '2026-09-15 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (12, 5, 8, 2, 480.00, 960.00, '2026-09-15 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (13, 5, 9, 2, 1250.00, 2500.00, '2026-09-15 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (14, 5, 7, 4, 340.00, 1360.00, '2026-09-15 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (15, 5, 6, 1, 290.00, 290.00, '2026-09-15 13:28:12.381631-06');
INSERT INTO public.sale_items VALUES (16, 11, 11, 1, 2850.00, 2850.00, '2026-08-22 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (17, 11, 3, 1, 2200.00, 2200.00, '2026-08-22 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (18, 12, 12, 1, 4500.00, 4500.00, '2026-08-25 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (19, 12, 13, 2, 890.00, 1780.00, '2026-08-25 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (20, 13, 14, 1, 2650.00, 2650.00, '2026-08-27 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (21, 13, 7, 1, 340.00, 340.00, '2026-08-27 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (22, 14, 15, 4, 950.00, 3800.00, '2026-08-30 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (23, 14, 20, 4, 210.00, 840.00, '2026-08-30 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (24, 15, 17, 2, 320.00, 640.00, '2026-09-01 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (25, 15, 18, 1, 260.00, 260.00, '2026-09-01 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (26, 16, 11, 1, 2850.00, 2850.00, '2026-09-02 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (27, 16, 16, 2, 1120.00, 2240.00, '2026-09-02 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (28, 16, 19, 1, 390.00, 390.00, '2026-09-02 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (29, 17, 21, 1, 520.00, 520.00, '2026-09-04 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (30, 17, 22, 2, 280.00, 560.00, '2026-09-04 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (31, 18, 24, 1, 1650.00, 1650.00, '2026-09-06 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (32, 18, 25, 1, 1980.00, 1980.00, '2026-09-06 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (33, 18, 26, 3, 145.00, 435.00, '2026-09-06 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (34, 19, 13, 1, 890.00, 890.00, '2026-09-07 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (35, 19, 4, 1, 1450.00, 1450.00, '2026-09-07 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (36, 20, 27, 3, 220.00, 660.00, '2026-09-09 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (37, 20, 28, 3, 180.00, 540.00, '2026-09-09 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (38, 20, 29, 2, 360.00, 720.00, '2026-09-09 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (39, 21, 23, 1, 750.00, 750.00, '2026-09-10 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (40, 21, 6, 1, 290.00, 290.00, '2026-09-10 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (41, 22, 24, 2, 1650.00, 3300.00, '2026-09-11 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (42, 22, 26, 4, 145.00, 580.00, '2026-09-11 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (43, 23, 14, 1, 2650.00, 2650.00, '2026-09-12 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (44, 23, 17, 1, 320.00, 320.00, '2026-09-12 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (45, 24, 12, 1, 4500.00, 4500.00, '2026-09-12 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (46, 24, 15, 4, 950.00, 3800.00, '2026-09-12 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (47, 25, 30, 1, 490.00, 490.00, '2026-09-13 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (48, 25, 18, 1, 260.00, 260.00, '2026-09-13 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (49, 6, 21, 1, 520.00, 520.00, '2026-09-14 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (50, 6, 29, 1, 360.00, 360.00, '2026-09-14 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (51, 7, 20, 2, 210.00, 420.00, '2026-09-14 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (52, 7, 17, 1, 320.00, 320.00, '2026-09-14 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (53, 8, 1, 1, 3499.00, 3499.00, '2026-09-15 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (54, 8, 5, 2, 780.00, 1560.00, '2026-09-15 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (55, 8, 30, 2, 490.00, 980.00, '2026-09-15 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (56, 9, 16, 1, 1120.00, 1120.00, '2026-09-16 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (57, 9, 22, 1, 280.00, 280.00, '2026-09-16 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (58, 10, 11, 1, 2850.00, 2850.00, '2026-09-16 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (59, 10, 25, 1, 1980.00, 1980.00, '2026-09-16 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (60, 10, 27, 2, 220.00, 440.00, '2026-09-16 13:28:12.559058-06');
INSERT INTO public.sale_items VALUES (61, 26, 28, 2, 180.00, 360.00, '2026-09-17 19:49:56.987776-06');


--
-- Data for Name: sale_payments; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sale_payments VALUES (2, 26, 2, 403.20, '2026-09-18 06:00:00-06', NULL, 'Ninguna', '2026-09-17 19:49:56.997768-06');


--
-- Data for Name: sessions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.sessions VALUES ('96d40010-a437-4b79-847b-c1fe6eeaed84', '82f586e1-0c57-47c1-8832-5eab20d9d15f', 'e4a27f83ade5395749a2830334c50e7fdcdd9f0dd9ada84812d0e08e98f30790', '2026-10-17 13:28:26.991884-06', '2026-09-17 13:28:26.991884-06', '2026-09-17 17:29:48.991252-06');
INSERT INTO public.sessions VALUES ('f141a56b-51bd-4051-9c6a-c8735a553d3f', '82f586e1-0c57-47c1-8832-5eab20d9d15f', 'eb2b7b6e1aa900f99bde14e9d58099255c8ba5c94c6b3afb185a9fa47913d802', '2026-10-17 17:29:55.557213-06', '2026-09-17 17:29:55.557213-06', '2026-09-17 17:55:49.743391-06');
INSERT INTO public.sessions VALUES ('ff783795-65e0-42b1-93f6-01a6d4e23d3b', '996729c3-44ed-4a1f-a6dd-5c8a72f4dcba', '7a0d39f9379e63b8da736b7c4de13f55a24e2eeaf9115a18998a6ec654c2ca9a', '2026-10-17 17:55:56.519334-06', '2026-09-17 17:55:56.519334-06', '2026-09-17 18:35:12.808975-06');
INSERT INTO public.sessions VALUES ('4839c543-370b-4122-85dc-24f78e845750', '00fe08f8-ef7c-465a-a33e-2bc50667c283', '4af461d75e4e748b6ff1a588e3bdb9613d0aa16f69e8bc32ae35ebeb86258034', '2026-10-17 18:35:19.099947-06', '2026-09-17 18:35:19.099947-06', '2026-09-17 19:38:17.665351-06');
INSERT INTO public.sessions VALUES ('c8a7f201-3245-4770-a1a5-b7561e555c4f', '00fe08f8-ef7c-465a-a33e-2bc50667c283', '645ef65a518f57f43a90d76f946daadcc2d2571e782072fce3905cfb2d814522', '2026-10-17 19:38:21.696189-06', '2026-09-17 19:38:21.696189-06', NULL);


--
-- Data for Name: supplier_products; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.supplier_products VALUES (1, 1, 1, 'SUP1-DESK-01', 2350.00, 2350.00, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.supplier_products VALUES (2, 1, 2, 'SUP1-DESK-02', 1250.00, 1250.00, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.supplier_products VALUES (3, 2, 3, 'SUP2-CHAIR-01', 1450.00, 1450.00, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.supplier_products VALUES (4, 2, 4, 'SUP2-CHAIR-02', 950.00, 950.00, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.supplier_products VALUES (5, 5, 5, 'SUP5-ACC-01', 480.00, 480.00, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.supplier_products VALUES (6, 5, 6, 'SUP5-SET-01', 160.00, 160.00, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.supplier_products VALUES (7, 5, 7, 'SUP5-SET-02', 190.00, 190.00, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.supplier_products VALUES (8, 5, 10, 'SUP5-SET-03', 95.00, 95.00, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.supplier_products VALUES (9, 3, 8, 'SUP3-ILU-01', 280.00, 280.00, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.supplier_products VALUES (10, 4, 9, 'SUP4-ALM-01', 820.00, 820.00, '2026-09-17 13:28:12.381631-06', '2026-09-17 13:28:12.381631-06');
INSERT INTO public.supplier_products VALUES (11, 7, 11, 'PROD-ESC-003', 1900.00, 1900.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (12, 7, 12, 'PROD-ESC-004', 3100.00, 3100.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (13, 7, 13, 'PROD-ESC-005', 580.00, 580.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (14, 2, 14, 'PROD-SIL-003', 1750.00, 1750.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (15, 8, 15, 'PROD-SIL-004', 620.00, 620.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (16, 2, 16, 'PROD-SIL-005', 740.00, 740.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (17, 5, 17, 'PROD-ACC-002', 190.00, 190.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (18, 5, 18, 'PROD-ACC-003', 150.00, 150.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (19, 5, 19, 'PROD-ACC-004', 230.00, 230.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (20, 8, 20, 'PROD-ACC-005', 120.00, 120.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (21, 6, 21, 'PROD-ILU-002', 320.00, 320.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (22, 6, 22, 'PROD-ILU-003', 160.00, 160.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (23, 6, 23, 'PROD-ILU-004', 480.00, 480.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (24, 4, 24, 'PROD-ALM-002', 1080.00, 1080.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (25, 4, 25, 'PROD-ALM-003', 1320.00, 1320.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (26, 4, 26, 'PROD-ALM-004', 85.00, 85.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (27, 3, 27, 'PROD-CAB-001', 130.00, 130.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (28, 3, 28, 'PROD-CAB-002', 105.00, 105.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (29, 3, 29, 'PROD-SET-004', 215.00, 215.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (30, 5, 30, 'PROD-SET-005', 290.00, 290.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (31, 1, 13, 'MUEB-ESC-005', 595.00, 595.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (32, 7, 1, 'MAD-ELEC-001', 2390.00, 2390.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');
INSERT INTO public.supplier_products VALUES (33, 8, 3, 'CONF-SIL-001', 1490.00, 1490.00, '2026-09-17 13:28:12.559058-06', '2026-09-17 13:28:12.559058-06');


--
-- Name: audit_logs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.audit_logs_id_seq', 1, false);


--
-- Name: categories_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.categories_id_seq', 6, true);


--
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 21, true);


--
-- Name: inventory_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inventory_id_seq', 40, true);


--
-- Name: inventory_lots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inventory_lots_id_seq', 33, true);


--
-- Name: inventory_movement_lots_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inventory_movement_lots_id_seq', 30, true);


--
-- Name: inventory_movements_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.inventory_movements_id_seq', 77, true);


--
-- Name: payment_methods_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.payment_methods_id_seq', 4, true);


--
-- Name: phone_numbers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.phone_numbers_id_seq', 9, true);


--
-- Name: phone_suppliers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.phone_suppliers_id_seq', 9, true);


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 30, true);


--
-- Name: purchase_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchase_items_id_seq', 33, true);


--
-- Name: purchase_statuses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchase_statuses_id_seq', 5, true);


--
-- Name: purchases_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchases_id_seq', 15, true);


--
-- Name: roles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.roles_id_seq', 4, true);


--
-- Name: sale_items_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sale_items_id_seq', 61, true);


--
-- Name: sale_payments_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sale_payments_id_seq', 2, true);


--
-- Name: sale_statuses_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sale_statuses_id_seq', 5, true);


--
-- Name: sales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sales_id_seq', 26, true);


--
-- Name: seq_employee_number; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.seq_employee_number', 104, false);


--
-- Name: supplier_products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.supplier_products_id_seq', 33, true);


--
-- Name: suppliers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.suppliers_id_seq', 8, true);


--
-- PostgreSQL database dump complete
--

\unrestrict d0f8aHvdqobZaPh16cxu4OgV8qxBySHMw4GSZXUtV4EGXLXEPEsdPwZQYHiZfpD

