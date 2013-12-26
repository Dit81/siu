//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-792 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.16 at 03:46:51 PM CET 
//


package org.ow2.easywsdl.wsdl.org.w3.ns.wsdl;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.namespace.QName;
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
import org.ow2.easywsdl.wsdl.org.w3.ns.wsdl.soap.Module;


/**
 * 
 *       This type is extended by component types to allow 
 *       attributes from other namespaces to be added.
 *       
 * 
 * <p>Java class for ExtensibleDocumentedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExtensibleDocumentedType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/ns/wsdl}DocumentedType">
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExtensibleDocumentedType")
@XmlSeeAlso({
    DescriptionType.class,
    ServiceType.class,
    BindingType.class,
    ImportType.class,
    InterfaceType.class,
    TypesType.class,
    EndpointType.class,
    IncludeType.class,
    BindingFaultType.class,
    BindingOperationType.class,
    InterfaceFaultType.class,
    BindingOperationMessageType.class,
    InterfaceOperationType.class,
    BindingOperationFaultType.class,
    MessageRefType.class,
    MessageRefFaultType.class,
    org.ow2.easywsdl.wsdl.org.w3.ns.wsdl.soap.Header.class,
    Module.class,
    org.ow2.easywsdl.wsdl.org.w3.ns.wsdl.http.Header.class
})
public abstract class ExtensibleDocumentedType
    extends DocumentedType
    implements CopyTo, Copyable, Equals, HashCode, ToString
{

    @XmlAnyAttribute
    private Map<QName, String> otherAttributes = new HashMap<QName, String>();

    /**
     * Gets a map that contains attributes that aren't bound to any typed property on this class.
     * 
     * <p>
     * the map is keyed by the name of the attribute and 
     * the value is the string value of the attribute.
     * 
     * the map returned by this method is live, and you can add new attribute
     * by updating the map directly. Because of this design, there's no setter.
     * 
     * 
     * @return
     *     always non-null
     */
    public Map<QName, String> getOtherAttributes() {
        return otherAttributes;
    }

    public void toString(ToStringBuilder toStringBuilder) {
        super.toString(toStringBuilder);
    }

    public String toString() {
        final ToStringBuilder toStringBuilder = new JAXBToStringBuilder(this);
        toString(toStringBuilder);
        return toStringBuilder.toString();
    }

    public void equals(Object object, EqualsBuilder equalsBuilder) {
        if (!(object instanceof ExtensibleDocumentedType)) {
            equalsBuilder.appendSuper(false);
            return ;
        }
        if (this == object) {
            return ;
        }
        super.equals(object, equalsBuilder);
        final ExtensibleDocumentedType that = ((ExtensibleDocumentedType) object);
    }

    public boolean equals(Object object) {
        if (!(object instanceof ExtensibleDocumentedType)) {
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
    }

    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new JAXBHashCodeBuilder();
        hashCode(hashCodeBuilder);
        return hashCodeBuilder.toHashCode();
    }

    public Object copyTo(Object target, CopyBuilder copyBuilder) {
        if (null == target) {
            throw new IllegalArgumentException("Target argument must not be null for abstract copyable classes.");
        }
        final ExtensibleDocumentedType copy = ((ExtensibleDocumentedType) target);
        super.copyTo(copy, copyBuilder);
        return copy;
    }

    public Object copyTo(Object target) {
        final CopyBuilder copyBuilder = new JAXBCopyBuilder();
        return copyTo(target, copyBuilder);
    }

}
