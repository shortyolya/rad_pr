package com.baltinfo.radius.fias.utils;

/**
 * Constans used in FIAS GAR
 *
 * @author Andrei Shymko
 * @since 13.10.2020
 */
public class FiasConstants {

    // for update FIAS job
    public static final String[] XML_EXTENSIONS = {"xml", "XML", "Xml"};
    public static final String ZIP_FILE_NAME = "fias_gar.zip";
    public static final String UNZIP_DIRECTORY_NAME = "fias_gar";
    public static final Integer START_LOAD_SIGN = 0;
    public static final Integer SUCCESSFUL_LOAD_SIGN = 1;
    public static final Integer ERROR_LOAD_SIGN = 2;
    public static final String FIAS_WARNING_MESSAGE = "Требуется первоначальная загрузка всей БД ГАР ФИАС";
    public static final String FIAS_LOADING_IN_PROGRESS = "Идет загрузка или обновление БД ГАР ФИАС";

    // for Hibernate constants
    public static final int BATCH_SIZE = 1000;
    public static final String FIELD_ID = "id";


    // start elements fias xml
    public static final String PARAM = "PARAM";
    public static final String STEAD = "STEAD";
    public static final String ROOM_TYPE = "ROOMTYPE";
    public static final String ROOM = "ROOM";
    public static final String OBJECT = "OBJECT";
    public static final String OPERATION_TYPE = "OPERATIONTYPE";
    public static final String HOUSE_TYPE = "HOUSETYPE";
    public static final String NDOC_TYPE = "NDOCTYPE";
    public static final String APARTMENT_TYPE = "APARTMENTTYPE";
    public static final String PARAM_TYPE = "PARAMTYPE";
    public static final String ADDRESS_OBJECT_TYPE = "ADDRESSOBJECTTYPE";
    public static final String APARTMENT = "APARTMENT";
    public static final String CAR_PLACE = "CARPLACE";
    public static final String ITEM = "ITEM";
    public static final String HOUSE = "HOUSE";
    public static final String NDOC_KIND = "NDOCKIND";
    public static final String NORM_DOC = "NORMDOC";
    public static final String OBJECT_LEVEL = "OBJECTLEVEL";

    // attributes fias xml
    public static final String ID = "ID";
    public static final String OBJECT_ID = "OBJECTID";
    public static final String TYPE_ID = "TYPEID";
    public static final String CHANGE_ID = "CHANGEID";
    public static final String UPDATE_DATE = "UPDATEDATE";
    public static final String VALUE = "VALUE";
    public static final String OBJECT_GUID = "OBJECTGUID";
    public static final String NUMBER = "NUMBER";
    public static final String OPER_TYPE_ID = "OPERTYPEID";
    public static final String PREV_ID = "PREVID";
    public static final String NEXT_ID = "NEXTID";
    public static final String START_DATE = "STARTDATE";
    public static final String END_DATE = "ENDDATE";
    public static final String IS_ACTUAL = "ISACTUAL";
    public static final String IS_ACTIVE = "ISACTIVE";
    public static final String NAME = "NAME";
    public static final String SHORT_NAME = "SHORTNAME";
    public static final String DESC = "DESC";
    public static final String TYPE_NAME = "TYPENAME";
    public static final String LEVEL = "LEVEL";
    public static final String CODE = "CODE";
    public static final String APART_TYPE = "APARTTYPE";
    public static final String PARENT_OBJ_ID = "PARENTOBJID";
    public static final String REGION_CODE = "REGIONCODE";
    public static final String AREA_CODE = "AREACODE";
    public static final String CITY_CODE = "CITYCODE";
    public static final String PLACE_CODE = "PLACECODE";
    public static final String PLAN_CODE = "PLANCODE";
    public static final String STREET_CODE = "STREETCODE";
    public static final String HOUSE_NUM = "HOUSENUM";
    public static final String ADD_NUM_1 = "ADDNUM1";
    public static final String ADD_NUM_2 = "ADDNUM2";
    public static final String ADD_TYPE_1 = "ADDTYPE1";
    public static final String ADD_TYPE_2 = "ADDTYPE2";
    public static final String OKTMO = "OKTMO";
    public static final String DATE = "DATE";
    public static final String TYPE = "TYPE";
    public static final String KIND = "KIND";
    public static final String ORG_NAME = "ORGNAME";
    public static final String REG_NUM = "REGNUM";
    public static final String REG_DATE = "REGDATE";
    public static final String ACC_DATE = "ACCDATE";
    public static final String COMMENT = "COMMENT";
    public static final String PARENT_ID = "PARENTID";
    public static final String CHILD_ID = "CHILDID";
    public static final String ADR_OBJECT_ID = "ADROBJECTID";
    public static final String NDOC_ID = "NDOCID";
    public static final String CHANGE_DATE = "CHANGEDATE";

    // names of the files xml
    public static final String ADDR_OBJ_DIVISION = "ADDR_OBJ_DIVISION";
    public static final String ADDR_OBJ_TYPES = "ADDR_OBJ_TYPES";
    public static final String ADM_HIERARCHY = "ADM_HIERARCHY";
    public static final String APARTMENT_TYPES = "APARTMENT_TYPES";
    public static final String APARTMENTS = "APARTMENTS";
    public static final String CAR_PLACES = "CARPLACES";
    public static final String CHANGE_HISTORY = "CHANGE_HISTORY";
    public static final String HOUSE_TYPES = "_HOUSE_TYPES";
    public static final String HOUSES = "HOUSES";
    public static final String MUN_HIERARCHY = "MUN_HIERARCHY";
    public static final String NORMATIVE_DOCS = "NORMATIVE_DOCS";
    public static final String NORMATIVE_DOCS_KINDS = "NORMATIVE_DOCS_KINDS";
    public static final String NORMATIVE_DOCS_TYPES = "NORMATIVE_DOCS_TYPES";
    public static final String OBJECT_LEVELS = "OBJECT_LEVELS";
    public static final String OPERATION_TYPES = "OPERATION_TYPES";
    public static final String PARAMS = "PARAMS";
    public static final String PARAM_TYPES = "PARAM_TYPES";
    public static final String ADDR_OBJ = "ADDR_OBJ_2";
    public static final String ROOM_TYPES = "ROOM_TYPES";
    public static final String ROOMS = "ROOMS";
    public static final String STEADS = "STEADS";
}