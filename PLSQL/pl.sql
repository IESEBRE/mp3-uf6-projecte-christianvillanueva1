-- Poseu el codi dels procediments/funcions emmagatzemats, triggers, ..., usats al projecte

CREATE OR REPLACE PROCEDURE creartaula IS
BEGIN
    BEGIN
        EXECUTE IMMEDIATE 'CREATE TABLE Productes (
            nom VARCHAR2(255) PRIMARY KEY,
            preu NUMBER NOT NULL,
            calories NUMBER NOT NULL,
            categoria VARCHAR2(255) NOT NULL,
            disponible NUMBER(1) NOT NULL
        )';
        DBMS_OUTPUT.PUT_LINE('Tabla Productes creada exitosamente.');
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error');
    END;
END;
/

