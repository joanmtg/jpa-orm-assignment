/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebajpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author invitado
 */
@Entity
@Table(name = "tipo_documento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t")
    , @NamedQuery(name = "TipoDocumento.findByTpIdDocumento", query = "SELECT t FROM TipoDocumento t WHERE t.tpIdDocumento = :tpIdDocumento")
    , @NamedQuery(name = "TipoDocumento.findByTpCodigo", query = "SELECT t FROM TipoDocumento t WHERE t.tpCodigo = :tpCodigo")
    , @NamedQuery(name = "TipoDocumento.findByTpNombre", query = "SELECT t FROM TipoDocumento t WHERE t.tpNombre = :tpNombre")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tp_id_documento")
    private Integer tpIdDocumento;
    @Column(name = "tp_codigo")
    private String tpCodigo;
    @Column(name = "tp_nombre")
    private String tpNombre;
    @OneToMany(mappedBy = "tpDocumento")
    private Collection<Personal> personalCollection;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer tpIdDocumento) {
        this.tpIdDocumento = tpIdDocumento;
    }

    public Integer getTpIdDocumento() {
        return tpIdDocumento;
    }

    public void setTpIdDocumento(Integer tpIdDocumento) {
        this.tpIdDocumento = tpIdDocumento;
    }

    public String getTpCodigo() {
        return tpCodigo;
    }

    public void setTpCodigo(String tpCodigo) {
        this.tpCodigo = tpCodigo;
    }

    public String getTpNombre() {
        return tpNombre;
    }

    public void setTpNombre(String tpNombre) {
        this.tpNombre = tpNombre;
    }

    @XmlTransient
    public Collection<Personal> getPersonalCollection() {
        return personalCollection;
    }

    public void setPersonalCollection(Collection<Personal> personalCollection) {
        this.personalCollection = personalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tpIdDocumento != null ? tpIdDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.tpIdDocumento == null && other.tpIdDocumento != null) || (this.tpIdDocumento != null && !this.tpIdDocumento.equals(other.tpIdDocumento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pruebajpa.TipoDocumento[ tpIdDocumento=" + tpIdDocumento + " ]";
    }
    
}
