package com.prueba.prueba.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

//import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger;
import org.springframework.beans.factory.annotation.Autowired;
///import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.prueba.models.VentaModel;
import com.prueba.prueba.servicios.VentaService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/ventas/")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    private ResponseEntity<List<VentaModel>> getAllVentas() {
        return ResponseEntity.ok(ventaService.findAll());
    }
    
    @PostMapping
    private VentaModel SaveVentas(@RequestBody VentaModel venta){
        return ventaService.save(venta);
    }

 @GetMapping("/filtrarPorFecha")
 public List<VentaModel> filtrarVentasPorFecha(@RequestParam("fechaInicio") String fechaInicioStr,
                                           @RequestParam("fechaFin") String fechaFinStr) {
     try {
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         Date fechaInicio = new Date(sdf.parse(fechaInicioStr).getTime());
         Date fechaFin = new Date(sdf.parse(fechaFinStr).getTime());
         
         return ventaService.buscarVentasPorFechas(fechaInicio, fechaFin);
     } catch (ParseException e) {
         // Manejar la excepción de análisis de fecha si es necesario
         e.printStackTrace();
         return null; // O devolver una respuesta de error
     }
 }

}
