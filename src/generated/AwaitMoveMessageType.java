//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.06.05 at 02:26:35 PM CEST 
//


package generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AwaitMoveMessageType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AwaitMoveMessageType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="board" type="{}boardType"/>
 *         &lt;element name="treasuresToGo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="treasure" type="{}treasureType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AwaitMoveMessageType", propOrder = {
    "board",
    "treasuresToGo",
    "treasure"
})
public class AwaitMoveMessageType {

    @XmlElement(required = true)
    protected BoardType board;
    protected int treasuresToGo;
    @XmlElement(required = true)
    protected TreasureType treasure;

    /**
     * Gets the value of the board property.
     * 
     * @return
     *     possible object is
     *     {@link BoardType }
     *     
     */
    public BoardType getBoard() {
        return board;
    }

    /**
     * Sets the value of the board property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoardType }
     *     
     */
    public void setBoard(BoardType value) {
        this.board = value;
    }

    /**
     * Gets the value of the treasuresToGo property.
     * 
     */
    public int getTreasuresToGo() {
        return treasuresToGo;
    }

    /**
     * Sets the value of the treasuresToGo property.
     * 
     */
    public void setTreasuresToGo(int value) {
        this.treasuresToGo = value;
    }

    /**
     * Gets the value of the treasure property.
     * 
     * @return
     *     possible object is
     *     {@link TreasureType }
     *     
     */
    public TreasureType getTreasure() {
        return treasure;
    }

    /**
     * Sets the value of the treasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link TreasureType }
     *     
     */
    public void setTreasure(TreasureType value) {
        this.treasure = value;
    }

}
