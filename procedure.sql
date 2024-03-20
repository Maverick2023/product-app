-- Autor: Enrique Paiva
-- Nota: Las tablas se crean con JPA cuando se inicia el proyecto desde el ide
PROCEDURE PRC_REG_PRODUCTO
(
    OUT_CURSOR OUT SYS_REFCURSOR,
    OUT_CODIGO OUT NUMBER,
    OUT_MENSAJE OUT NVARCHAR2,
    P_ID_PRODUCTO NUMBER,
    P_FECHA_PRODUCTO DATE,
    P_NOMBRE_PRODUCTO NVARCHAR2
)
AS
  /**************************************************/
    GCSQLCODE   NUMBER;
    GCSQLERRM   VARCHAR2(100);
BEGIN
    
    INSERT INTO PRODUCTO(ID_PRODUCTO, FE_REGISTRO, NO_PRODUCTO) VALUES(P_ID_PRODUCTO, P_FECHA_PRODUCTO,P_NOMBRE_PRODUCTO);
    
    OPEN OUT_CURSOR FOR
        SELECT ID_PRODUCTO, FE_REGISTRO, NO_PRODUCTO 
        FROM PRODUCTO 
        WHERE TO_CHAR(SYSDATE,'DD/MM/RRRR') = TO_CHAR(FE_REGISTRO,'DD/MM/RRRR');
    
    OUT_CODIGO:= 1;
    OUT_MENSAJE:= 'SE REGISTRO EXITOSAMENTE';
    RETURN;

    EXCEPTION
        WHEN OTHERS THEN
            GCSQLCODE :=SQLCODE;
            GCSQLERRM :=SQLERRM;
            
        OPEN OUT_CURSOR FOR
            SELECT '' AS ID_PRODUCTO, '' AS FE_REGISTRO, '' AS NO_PRODUCTO FROM DUAL;
            OUT_CODIGO:= 0;
            OUT_MENSAJE:= 'OCURRIO UN PROBLEMA - CODIGO: '  || GCSQLCODE || ' - MENSAJE : ' ||GCSQLERRM ;
    
    RETURN;
END;