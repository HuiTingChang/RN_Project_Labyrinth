//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.04 at 02:43:47 PM CEST 
//


package generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for boardType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="boardType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="row" maxOccurs="7" minOccurs="7">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="col" type="{}cardType" maxOccurs="7" minOccurs="7"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="shiftCard" type="{}cardType"/>
 *         &lt;element name="forbidden" type="{}positionType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "boardType", propOrder = {
    "row",
    "shiftCard",
    "forbidden"
})
public class BoardType {

    @XmlElement(required = true)
    protected List<BoardType.Row> row;
    @XmlElement(required = true)
    protected CardType shiftCard;
    @XmlElement(required = true)
    protected PositionType forbidden;

    /**
     * Gets the value of the row property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the row property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRow().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BoardType.Row }
     * 
     * 
     */
    public List<BoardType.Row> getRow() {
        if (row == null) {
            row = new ArrayList<BoardType.Row>();
        }
        return this.row;
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

    /**
     * Gets the value of the forbidden property.
     * 
     * @return
     *     possible object is
     *     {@link PositionType }
     *     
     */
    public PositionType getForbidden() {
        return forbidden;
    }

    /**
     * Sets the value of the forbidden property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionType }
     *     
     */
    public void setForbidden(PositionType value) {
        this.forbidden = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="col" type="{}cardType" maxOccurs="7" minOccurs="7"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "col"
    })
    public static class Row {

        @XmlElement(required = true)
        protected List<CardType> col;

        /**
         * Gets the value of the col property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the col property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCol().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CardType }
         * 
         * 
         */
        public List<CardType> getCol() {
            if (col == null) {
                col = new ArrayList<CardType>();
            }
            return this.col;
        }

    }

}
