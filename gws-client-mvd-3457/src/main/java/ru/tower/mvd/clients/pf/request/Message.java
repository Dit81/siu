
/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 * Copyright (c) 2013, MPL CodeInside http://codeinside.ru
 */

package ru.tower.mvd.clients.pf.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import ru.socit.pfr.service.data.Properties;
import ru.socit.pfr.service.data.Type;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Message")
public class Message {

    @XmlElement(name = "Header", required = true)
    protected BaseHeaderType header;
    @XmlElement(name = "Type", namespace = "http://data.service.pfr.socit.ru", required = true)
    protected Type type;
    @XmlElement(name = "Properties", namespace = "http://data.service.pfr.socit.ru", required = true)
    protected Properties properties;


    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link BaseHeaderType }
     *     
     */
    public BaseHeaderType getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link BaseHeaderType }
     *     
     */
    public void setHeader(BaseHeaderType value) {
        this.header = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setType(Type value) {
        this.type = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link Properties }
     *     
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Properties }
     *     
     */
    public void setProperties(Properties value) {
        this.properties = value;
    }



}
