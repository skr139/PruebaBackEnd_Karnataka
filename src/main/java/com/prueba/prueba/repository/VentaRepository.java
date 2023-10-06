package com.prueba.prueba.repository;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.prueba.prueba.models.VentaModel;

/**
 * personaRepository
 */
public interface VentaRepository extends JpaRepository<VentaModel,Long>{

     @Query(value = "SELECT * FROM ventas WHERE fecha BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
    List<VentaModel> buscarVentasPorFechas(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
    
    //@Query(value = "SELECT * FROM ventas WHERE fecha BETWEEN :fechaInicio AND :fechaFin", nativeQuery = true)
    //List<VentaModel> buscarVentasPorRangoDeFechas(@Param("fechaInicio") String fechaInicioStr, @Param("fechaFin") String fechaFinStr);
}