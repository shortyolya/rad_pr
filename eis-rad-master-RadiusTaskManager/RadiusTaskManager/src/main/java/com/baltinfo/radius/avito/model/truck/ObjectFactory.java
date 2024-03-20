package com.baltinfo.radius.avito.model.truck;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.baltinfo.radius.avito.model package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 *
 * @author Andrei Shymko
 * @since 10.11.2020
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MAKES_QNAME = new QName("", "Makes");
    private final static QName _MAKE_QNAME = new QName("", "Make");
    private final static QName _Model_QNAME = new QName("", "Model");
    private final static QName _BodyType_QNAME = new QName("", "BodyType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.baltinfo.radius.avito.model
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MakesType }
     */
    public MakesType createMakesType() {
        return new MakesType();
    }

    /**
     * Create an instance of {@link MakeType }
     */
    public MakeType createMakeType() {
        return new MakeType();
    }

    /**
     * Create an instance of {@link ModelType }
     */
    public ModelType createModelType() {
        return new ModelType();
    }

    /**
     * Create an instance of {@link BodyTypeType }
     */
    public BodyTypeType createBodyTypeType() {
        return new BodyTypeType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakesType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "Makes")
    public JAXBElement<MakesType> createMaks(MakesType value) {
        return new JAXBElement<MakesType>(_MAKES_QNAME, MakesType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "Make", scope = MakesType.class)
    public JAXBElement<MakeType> createMake(MakeType value) {
        return new JAXBElement<MakeType>(_MAKE_QNAME, MakeType.class, MakesType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BodyTypeType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "BodyType", scope = ModelType.class)
    public JAXBElement<BodyTypeType> createModelTypeBodyType(BodyTypeType value) {
        return new JAXBElement<BodyTypeType>(_BodyType_QNAME, BodyTypeType.class, ModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelType }{@code >}}
     */
    @XmlElementDecl(namespace = "", name = "Model", scope = MakeType.class)
    public JAXBElement<ModelType> createMakeTypeModel(ModelType value) {
        return new JAXBElement<ModelType>(_Model_QNAME, ModelType.class, MakeType.class, value);
    }
}