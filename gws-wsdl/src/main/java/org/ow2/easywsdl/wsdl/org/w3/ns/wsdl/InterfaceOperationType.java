//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-792 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.02.16 at 03:46:51 PM CET 
//


package org.ow2.easywsdl.wsdl.org.w3.ns.wsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
import org.w3c.dom.Element;


/**
 * <p>Java class for InterfaceOperationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InterfaceOperationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.w3.org/ns/wsdl}ExtensibleDocumentedType">
 *       &lt;choice maxOccurs="unbounded" minOccurs="0">
 *         &lt;element name="input" type="{http://www.w3.org/ns/wsdl}MessageRefType"/>
 *         &lt;element name="output" type="{http://www.w3.org/ns/wsdl}MessageRefType"/>
 *         &lt;element name="infault" type="{http://www.w3.org/ns/wsdl}MessageRefFaultType"/>
 *         &lt;element name="outfault" type="{http://www.w3.org/ns/wsdl}MessageRefFaultType"/>
 *         &lt;any processContents='lax' namespace='##other'/>
 *       &lt;/choice>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="pattern" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="safe" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="style" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;anyAttribute processContents='lax' namespace='##other'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterfaceOperationType", propOrder = {
    "inputOrOutputOrInfault"
})
public class InterfaceOperationType
    extends ExtensibleDocumentedType
    implements CopyTo, Copyable, Equals, HashCode, ToString
{

    @XmlElementRefs({
        @XmlElementRef(name = "input", namespace = "http://www.w3.org/ns/wsdl", type = JAXBElement.class),
        @XmlElementRef(name = "infault", namespace = "http://www.w3.org/ns/wsdl", type = JAXBElement.class),
        @XmlElementRef(name = "outfault", namespace = "http://www.w3.org/ns/wsdl", type = JAXBElement.class),
        @XmlElementRef(name = "output", namespace = "http://www.w3.org/ns/wsdl", type = JAXBElement.class)
    })
    @XmlAnyElement(lax = true)
    protected List<Object> inputOrOutputOrInfault;
    @XmlAttribute(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String name;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String pattern;
    @XmlAttribute
    protected Boolean safe;
    @XmlAttribute
    @XmlSchemaType(name = "anyURI")
    protected String style;

    /**
     * Gets the value of the inputOrOutputOrInfault property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inputOrOutputOrInfault property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInputOrOutputOrInfault().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link MessageRefType }{@code >}
     * {@link JAXBElement }{@code <}{@link MessageRefFaultType }{@code >}
     * {@link Element }
     * {@link JAXBElement }{@code <}{@link MessageRefFaultType }{@code >}
     * {@link Object }
     * {@link JAXBElement }{@code <}{@link MessageRefType }{@code >}
     * 
     * 
     */
    public List<Object> getInputOrOutputOrInfault() {
        if (inputOrOutputOrInfault == null) {
            inputOrOutputOrInfault = new ArrayList<Object>();
        }
        return this.inputOrOutputOrInfault;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the pattern property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Sets the value of the pattern property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPattern(String value) {
        this.pattern = value;
    }

    /**
     * Gets the value of the safe property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSafe() {
        return safe;
    }

    /**
     * Sets the value of the safe property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSafe(Boolean value) {
        this.safe = value;
    }

    /**
     * Gets the value of the style property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStyle() {
        return style;
    }

    /**
     * Sets the value of the style property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStyle(String value) {
        this.style = value;
    }

    public void toString(ToStringBuilder toStringBuilder) {
        super.toString(toStringBuilder);
        {
            List<Object> theInputOrOutputOrInfault;
            theInputOrOutputOrInfault = this.getInputOrOutputOrInfault();
            toStringBuilder.append("inputOrOutputOrInfault", theInputOrOutputOrInfault);
        }
        {
            String theName;
            theName = this.getName();
            toStringBuilder.append("name", theName);
        }
        {
            String thePattern;
            thePattern = this.getPattern();
            toStringBuilder.append("pattern", thePattern);
        }
        {
            Boolean theSafe;
            theSafe = this.isSafe();
            toStringBuilder.append("safe", theSafe);
        }
        {
            String theStyle;
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
        if (!(object instanceof InterfaceOperationType)) {
            equalsBuilder.appendSuper(false);
            return ;
        }
        if (this == object) {
            return ;
        }
        super.equals(object, equalsBuilder);
        final InterfaceOperationType that = ((InterfaceOperationType) object);
        equalsBuilder.append(this.getInputOrOutputOrInfault(), that.getInputOrOutputOrInfault());
        equalsBuilder.append(this.getName(), that.getName());
        equalsBuilder.append(this.getPattern(), that.getPattern());
        equalsBuilder.append(this.isSafe(), that.isSafe());
        equalsBuilder.append(this.getStyle(), that.getStyle());
    }

    public boolean equals(Object object) {
        if (!(object instanceof InterfaceOperationType)) {
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
        hashCodeBuilder.append(this.getInputOrOutputOrInfault());
        hashCodeBuilder.append(this.getName());
        hashCodeBuilder.append(this.getPattern());
        hashCodeBuilder.append(this.isSafe());
        hashCodeBuilder.append(this.getStyle());
    }

    public int hashCode() {
        final HashCodeBuilder hashCodeBuilder = new JAXBHashCodeBuilder();
        hashCode(hashCodeBuilder);
        return hashCodeBuilder.toHashCode();
    }

    public Object copyTo(Object target, CopyBuilder copyBuilder) {
        final InterfaceOperationType copy = ((target == null)?((InterfaceOperationType) createCopy()):((InterfaceOperationType) target));
        super.copyTo(copy, copyBuilder);
        {
            List<Object> sourceInputOrOutputOrInfault;
            sourceInputOrOutputOrInfault = this.getInputOrOutputOrInfault();
            List<Object> copyInputOrOutputOrInfault = ((List<Object> ) copyBuilder.copy(sourceInputOrOutputOrInfault));
            copy.inputOrOutputOrInfault = null;
            List<Object> uniqueInputOrOutputOrInfaultl = copy.getInputOrOutputOrInfault();
            uniqueInputOrOutputOrInfaultl.addAll(copyInputOrOutputOrInfault);
        }
        {
            String sourceName;
            sourceName = this.getName();
            String copyName = ((String) copyBuilder.copy(sourceName));
            copy.setName(copyName);
        }
        {
            String sourcePattern;
            sourcePattern = this.getPattern();
            String copyPattern = ((String) copyBuilder.copy(sourcePattern));
            copy.setPattern(copyPattern);
        }
        {
            Boolean sourceSafe;
            sourceSafe = this.isSafe();
            Boolean copySafe = ((Boolean) copyBuilder.copy(sourceSafe));
            copy.setSafe(copySafe);
        }
        {
            String sourceStyle;
            sourceStyle = this.getStyle();
            String copyStyle = ((String) copyBuilder.copy(sourceStyle));
            copy.setStyle(copyStyle);
        }
        return copy;
    }

    public Object copyTo(Object target) {
        final CopyBuilder copyBuilder = new JAXBCopyBuilder();
        return copyTo(target, copyBuilder);
    }

    public Object createCopy() {
        return new InterfaceOperationType();
    }

}
