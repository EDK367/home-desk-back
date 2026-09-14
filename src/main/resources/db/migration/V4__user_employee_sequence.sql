-- ======================================================================
-- CREACION DE SECUENCIA PARA EL NUMERO DE EMPLEADO
-- ======================================================================
DO
$$
    DECLARE
        next_val INTEGER;
    BEGIN
        SELECT COALESCE(MAX(employee_number) + 1, 100) INTO next_val FROM users;
        EXECUTE format(
                'CREATE SEQUENCE IF NOT EXISTS seq_employee_number START WITH %s INCREMENT BY 1 MINVALUE 100',
                next_val
                );
    END
$$;
