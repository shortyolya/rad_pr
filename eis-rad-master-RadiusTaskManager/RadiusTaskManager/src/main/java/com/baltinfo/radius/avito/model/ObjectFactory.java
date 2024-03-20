//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.10 at 04:51:35 PM MSK 
//


package com.baltinfo.radius.avito.model;

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
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Catalog_QNAME = new QName("", "Catalog");
    private final static QName _ModelTypeGeneration_QNAME = new QName("", "Generation");
    private final static QName _ModificationTypeModification_QNAME = new QName("", "Modification");
    private final static QName _ModificationTypeYearTo_QNAME = new QName("", "YearTo");
    private final static QName _ModificationTypeEngineSize_QNAME = new QName("", "EngineSize");
    private final static QName _ModificationTypeTransmission_QNAME = new QName("", "Transmission");
    private final static QName _ModificationTypeMake_QNAME = new QName("", "Make");
    private final static QName _ModificationTypeYearFrom_QNAME = new QName("", "YearFrom");
    private final static QName _ModificationTypeBodyType_QNAME = new QName("", "BodyType");
    private final static QName _ModificationTypeFuelType_QNAME = new QName("", "FuelType");
    private final static QName _ModificationTypeDriveType_QNAME = new QName("", "DriveType");
    private final static QName _ModificationTypeDoors_QNAME = new QName("", "Doors");
    private final static QName _ModificationTypeModel_QNAME = new QName("", "Model");
    private final static QName _ModificationTypeComplectations_QNAME = new QName("", "Complectations");
    private final static QName _ModificationTypePower_QNAME = new QName("", "Power");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.baltinfo.radius.avito.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CatalogType }
     * 
     */
    public CatalogType createCatalogType() {
        return new CatalogType();
    }

    /**
     * Create an instance of {@link EngineSizeType }
     * 
     */
    public EngineSizeType createEngineSizeType() {
        return new EngineSizeType();
    }

    /**
     * Create an instance of {@link DriveTypeType }
     * 
     */
    public DriveTypeType createDriveTypeType() {
        return new DriveTypeType();
    }

    /**
     * Create an instance of {@link YearToType }
     * 
     */
    public YearToType createYearToType() {
        return new YearToType();
    }

    /**
     * Create an instance of {@link MakeType }
     * 
     */
    public MakeType createMakeType() {
        return new MakeType();
    }

    /**
     * Create an instance of {@link PowerType }
     * 
     */
    public PowerType createPowerType() {
        return new PowerType();
    }

    /**
     * Create an instance of {@link ComplectationsType }
     * 
     */
    public ComplectationsType createComplectationsType() {
        return new ComplectationsType();
    }

    /**
     * Create an instance of {@link GenerationType }
     * 
     */
    public GenerationType createGenerationType() {
        return new GenerationType();
    }

    /**
     * Create an instance of {@link ComplectationType }
     * 
     */
    public ComplectationType createComplectationType() {
        return new ComplectationType();
    }

    /**
     * Create an instance of {@link YearFromType }
     * 
     */
    public YearFromType createYearFromType() {
        return new YearFromType();
    }

    /**
     * Create an instance of {@link ModificationType }
     * 
     */
    public ModificationType createModificationType() {
        return new ModificationType();
    }

    /**
     * Create an instance of {@link FuelTypeType }
     * 
     */
    public FuelTypeType createFuelTypeType() {
        return new FuelTypeType();
    }

    /**
     * Create an instance of {@link TransmissionType }
     * 
     */
    public TransmissionType createTransmissionType() {
        return new TransmissionType();
    }

    /**
     * Create an instance of {@link DoorsType }
     * 
     */
    public DoorsType createDoorsType() {
        return new DoorsType();
    }

    /**
     * Create an instance of {@link ModelType }
     * 
     */
    public ModelType createModelType() {
        return new ModelType();
    }

    /**
     * Create an instance of {@link BodyTypeType }
     * 
     */
    public BodyTypeType createBodyTypeType() {
        return new BodyTypeType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CatalogType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Catalog")
    public JAXBElement<CatalogType> createCatalog(CatalogType value) {
        return new JAXBElement<CatalogType>(_Catalog_QNAME, CatalogType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Generation", scope = ModelType.class)
    public JAXBElement<GenerationType> createModelTypeGeneration(GenerationType value) {
        return new JAXBElement<GenerationType>(_ModelTypeGeneration_QNAME, GenerationType.class, ModelType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModificationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Modification", scope = ModificationType.class)
    public JAXBElement<ModificationType> createModificationTypeModification(ModificationType value) {
        return new JAXBElement<ModificationType>(_ModificationTypeModification_QNAME, ModificationType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearToType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "YearTo", scope = ModificationType.class)
    public JAXBElement<YearToType> createModificationTypeYearTo(YearToType value) {
        return new JAXBElement<YearToType>(_ModificationTypeYearTo_QNAME, YearToType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EngineSizeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "EngineSize", scope = ModificationType.class)
    public JAXBElement<EngineSizeType> createModificationTypeEngineSize(EngineSizeType value) {
        return new JAXBElement<EngineSizeType>(_ModificationTypeEngineSize_QNAME, EngineSizeType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransmissionType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Transmission", scope = ModificationType.class)
    public JAXBElement<TransmissionType> createModificationTypeTransmission(TransmissionType value) {
        return new JAXBElement<TransmissionType>(_ModificationTypeTransmission_QNAME, TransmissionType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MakeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Make", scope = ModificationType.class)
    public JAXBElement<MakeType> createModificationTypeMake(MakeType value) {
        return new JAXBElement<MakeType>(_ModificationTypeMake_QNAME, MakeType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link YearFromType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "YearFrom", scope = ModificationType.class)
    public JAXBElement<YearFromType> createModificationTypeYearFrom(YearFromType value) {
        return new JAXBElement<YearFromType>(_ModificationTypeYearFrom_QNAME, YearFromType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BodyTypeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "BodyType", scope = ModificationType.class)
    public JAXBElement<BodyTypeType> createModificationTypeBodyType(BodyTypeType value) {
        return new JAXBElement<BodyTypeType>(_ModificationTypeBodyType_QNAME, BodyTypeType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FuelTypeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "FuelType", scope = ModificationType.class)
    public JAXBElement<FuelTypeType> createModificationTypeFuelType(FuelTypeType value) {
        return new JAXBElement<FuelTypeType>(_ModificationTypeFuelType_QNAME, FuelTypeType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DriveTypeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DriveType", scope = ModificationType.class)
    public JAXBElement<DriveTypeType> createModificationTypeDriveType(DriveTypeType value) {
        return new JAXBElement<DriveTypeType>(_ModificationTypeDriveType_QNAME, DriveTypeType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Generation", scope = ModificationType.class)
    public JAXBElement<GenerationType> createModificationTypeGeneration(GenerationType value) {
        return new JAXBElement<GenerationType>(_ModelTypeGeneration_QNAME, GenerationType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DoorsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Doors", scope = ModificationType.class)
    public JAXBElement<DoorsType> createModificationTypeDoors(DoorsType value) {
        return new JAXBElement<DoorsType>(_ModificationTypeDoors_QNAME, DoorsType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Model", scope = ModificationType.class)
    public JAXBElement<ModelType> createModificationTypeModel(ModelType value) {
        return new JAXBElement<ModelType>(_ModificationTypeModel_QNAME, ModelType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ComplectationsType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Complectations", scope = ModificationType.class)
    public JAXBElement<ComplectationsType> createModificationTypeComplectations(ComplectationsType value) {
        return new JAXBElement<ComplectationsType>(_ModificationTypeComplectations_QNAME, ComplectationsType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PowerType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Power", scope = ModificationType.class)
    public JAXBElement<PowerType> createModificationTypePower(PowerType value) {
        return new JAXBElement<PowerType>(_ModificationTypePower_QNAME, PowerType.class, ModificationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModificationType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Modification", scope = GenerationType.class)
    public JAXBElement<ModificationType> createGenerationTypeModification(ModificationType value) {
        return new JAXBElement<ModificationType>(_ModificationTypeModification_QNAME, ModificationType.class, GenerationType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModelType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Model", scope = MakeType.class)
    public JAXBElement<ModelType> createMakeTypeModel(ModelType value) {
        return new JAXBElement<ModelType>(_ModificationTypeModel_QNAME, ModelType.class, MakeType.class, value);
    }

}
