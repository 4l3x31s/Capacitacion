/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CapRuat.dao;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexeis
 */
@Entity
@Table(name = "compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByIdCompra", query = "SELECT c FROM Compra c WHERE c.idCompra = :idCompra"),
    @NamedQuery(name = "Compra.findByFechaVenta", query = "SELECT c FROM Compra c WHERE c.fechaVenta = :fechaVenta"),
    @NamedQuery(name = "Compra.findByUnidVendida", query = "SELECT c FROM Compra c WHERE c.unidVendida = :unidVendida"),
    @NamedQuery(name = "Compra.findByCodCliente", query = "SELECT c FROM Compra c WHERE c.codCliente = :codCliente"),
    @NamedQuery(name = "Compra.findByCodArticulo", query = "SELECT c FROM Compra c WHERE c.codArticulo = :codArticulo")})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = true)
    @Column(name = "IdCompra")
    private Integer idCompra;
    @Column(name = "FechaVenta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVenta;
    @Column(name = "UnidVendida")
    private Integer unidVendida;
    @Column(name = "CodCliente")
    private Integer codCliente;
    @Column(name = "CodArticulo")
    private Integer codArticulo;

    public Compra() {
    }

    public Compra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getUnidVendida() {
        return unidVendida;
    }

    public void setUnidVendida(Integer unidVendida) {
        this.unidVendida = unidVendida;
    }

    public Integer getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Integer getCodArticulo() {
        return codArticulo;
    }

    public void setCodArticulo(Integer codArticulo) {
        this.codArticulo = codArticulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        if ((this.idCompra == null && other.idCompra != null) || (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CapRuat.dao.Compra[ idCompra=" + idCompra + " ]";
    }
    
}
