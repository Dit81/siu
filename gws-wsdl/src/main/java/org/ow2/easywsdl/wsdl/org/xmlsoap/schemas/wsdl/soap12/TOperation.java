//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-792 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.16 at 03:46:51 PM CET 
//


package org.ow2.easywsdl.wsdl.org.xmlsoap.schemas.wsdl.soap12;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.ow2.easywsdl.u.builder.EqualsBuilder;
import org.ow2.easywsdl.u.builder.HashCodeBuilder;
import org.ow2.easywsdl.u.builder.ToStringBuilder;
import org.jvnet.jaxb2_commons.lang.CopyTo;
import org.jvnet.jaxb2_commons.lang.Copyable;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.HashCode;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.builder.CopyBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBCopyBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBEqualsBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBHashCodeBuilder;
import org.jvnet.jaxb2_commons.lang.builder.JAXBToStringBuilder;


/**
 * <p>Java class for tOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tOperation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.xmlsoap.org/wsdl/soap12/}tExtensibilityElementOpenAttrs">
 *       &lt;attribute name="soapAction" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="soapActionRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="style" type="{http://schemas.xmlsoap.org/wsdl/soap12/}tStyleChoice" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tOperation")
public class TOperation
    extends TExtensibilityElementOpenAttrs
    implements CopyTo, Copyable, Equals, HashCode, ToString
{

    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String soapAction;
    @XmlAttribute
    protected Boolean soapActionRequired;
    @XmlAttribute
    protected TStyleChoice style;

    /**
     * Gets the value of the soapAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSoapAction() {
        return soapAction;
    }

    /**
     * Sets the value of the soapAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSoapAction(String value) {
        this.soapAction = value;
    }

    /**
     * Gets the value of the soapActionRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSoapActionRequired() {
        return soapActionRequired;
    }

    /**
     * Sets the value of the soapActionRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSoapActionRequired(Boolean value) {
        this.soapActionRequired = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link TStyleChoice }
     *     
     */
    public TStyleChoice getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link TStyleChoice }
     *     
     */
    public void setStyle(TStyleChoice value) {
        this.style = value;
    }

    public void toString(ToStringBuilder toStringBuilder) {
        super.toString(toStringBuilder);
        {
            String theSoapAction;
            theSoapAction = this.getSoapAction();
            toStringBuilder.append("soapAction", theSoapAction);
        }
        {
            Boolean theSoapActionRequired;
            theSoapActionRequired = this.isSoapActionRequired();
            toStringBuilder.append("soapActionRequired", theSoapActionRequired);
        }
        {
            TStyleChoice theStyle;
            theStyle = this.getStyle();
            toStringBuilder.append("style", theStyle);
        }
    }

    public String toString() {
        final ToStringBuilder toStringBuilder = new JAXBToStringBuilder(this);
        toString(toStringBuilder);
        return toStringBuilder.toString();
    }

    public void equals(Object object, EqualsBuilder equalsBuilder) {
        if (!(object instanceof TOperation)) {
            equalsBuilder.appendSuper(false);
            return ;
        }
        if (this == object) {
            return ;
        }
        super.equals(object, equalsBuilder);
        final TOperation that = ((TOperation) object);
        equalsBuilder.append(this.getSoapAction(), that.getSoapAction());
        equalsBuilder.append(this.isSoapActionRequired(), that.isSoapActionRequired());
        equalsBuilder.append(this.getStyle(), that.getStyle());
    }

    public boolean equals(Object object) {
        if (!(object instanceof TOperation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EqualsBuilder equalsBuilder = new JAXBEqualsBuilder();
        equals(object, equalsBuilder);
        return equalsBuilder.isEquals();
    }

    public void hashCode(HashCodeBuilder hashCodeBuilder) {
        super.hashCode(hashCodeBuilder);
        hashCodeBuilder.append(this.getSoapAction());
        hashCodeBuilder.append(this.isSoapActionRequired());
        hashCodeBuilder.append(this.getStyle());
    }

    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new JAXBHashCodeBuilder();
        hashCode(hashCodeBuilder);
        return hashCodeBuilder.toHashCode();
    }

    public Object copyTo(Object target, CopyBuilder copyBuilder) {
        final TOperation copy = ((target == null)?((TOperation) createCopy()):((TOperation) target));
        super.copyTo(copy, copyBuilder);
        {
            String sourceSoapAction;
            sourceSoapAction = this.getSoapAction();
            String copySoapAction = ((String) copyBuilder.copy(sourceSoapAction));
            copy.setSoapAction(copySoapAction);
        }
        {
            Boolean sourceSoapActionRequired;
            sourceSoapActionRequired = this.isSoapActionRequired();
            Boolean copySoapActionRequired = ((Boolean) copyBuilder.copy(sourceSoapActionRequired));
            copy.setSoapActionRequired(copySoapActionRequired);
        }
        {
            TStyleChoice sourceStyle;
            sourceStyle = this.getStyle();
            TStyleChoice copyStyle = ((TStyleChoice) copyBuilder.copy(sourceStyle));
            copy.setStyle(copyStyle);
        }
        return copy;
    }

    public Object copyTo(Object target) {
        final CopyBuilder copyBuilder = new JAXBCopyBuilder();
        return copyTo(target, copyBuilder);
    }

    public Object createCopy() {
        return new TOperation();
    }

}
