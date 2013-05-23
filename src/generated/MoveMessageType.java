//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.23 at 03:09:12 PM CEST 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MoveMessageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MoveMessageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="shiftPosition" type="{}positionType"/>
 *         &lt;element name="newPinPos" type="{}positionType"/>
 *         &lt;element name="shiftCard" type="{}cardType"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MoveMessageType", propOrder = {

})
public class MoveMessageType {

    @XmlElement(required = true)
    protected PositionType shiftPosition;
    @XmlElement(required = true)
    protected PositionType newPinPos;
    @XmlElement(required = true)
    protected CardType shiftCard;

    /**
     * Gets the value of the shiftPosition property.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getShiftPosition() {
        return shiftPosition;
    }

    /**
     * Sets the value of the shiftPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setShiftPosition(PositionType value) {
        this.shiftPosition = value;
    }

    /**
     * Gets the value of the newPinPos property.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getNewPinPos() {
        return newPinPos;
    }

    /**
     * Sets the value of the newPinPos property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setNewPinPos(PositionType value) {
        this.newPinPos = value;
    }

    /**
     * Gets the value of the shiftCard property.
     * 
     * @return
     *     possible object is
     *     {@link CardType }
     *     
     */
    public CardType getShiftCard() {
        return shiftCard;
    }

    /**
     * Sets the value of the shiftCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link CardType }
     *     
     */
    public void setShiftCard(CardType value) {
        this.shiftCard = value;
    }

}
