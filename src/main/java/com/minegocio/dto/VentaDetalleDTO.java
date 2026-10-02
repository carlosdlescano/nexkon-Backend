/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.minegocio.dto;

/**
 *
 * @author miNegocio
 */
public class VentaDetalleDTO {
    private int idVenta;
    private double monto;
    private String hora;
    private String articulo;
    private int cantidad;

    public VentaDetalleDTO() {
    }

    public VentaDetalleDTO(int idVenta, double monto, String hora, String articulo, int cantidad) {
        this.idVenta = idVenta;
        this.monto = monto;
        this.hora = hora;
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    public int getIdVenta() {return idVenta;    }
    public void setIdVenta(int idVenta) {this.idVenta = idVenta;    }

    public double getMonto() {return monto;    }
    public void setMonto(double monto) {this.monto = monto;    }
    
    public String getHora() {return hora;    }
    public void setHora(String hora) {this.hora = hora;    }

    public String getArticulo() {return articulo;    }
    public void setArticulo(String articulo) {this.articulo = articulo;  }

    public int getCantidad() {return cantidad;   }
    public void setCantidad(int cantidad) {this.cantidad = cantidad;    }
    
    
}
