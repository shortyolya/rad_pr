package com.baltinfo.radius.bankruptcy.export;

import com.baltinfo.radius.bankruptcy.BkrPublicationBodyParams;
import com.baltinfo.radius.bankruptcy.BkrTypePublicationBody;
import com.baltinfo.radius.bankruptcy.IBkrPublicationBodyParams;
import com.baltinfo.radius.db.controller.BkrExchangeUnloadController;
import com.baltinfo.radius.utils.HibernateUtil;
import org.junit.Ignore;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

public class HtmlAssetUploadTest {

    private final String testString = "<fieldset>\n" +
            "\t<legend>Земельный участок для сельскохозяйственного производства</legend>\n" +
            "\t<br><br>\n" +
            "\t<div class=\"form-item\">\n" +
            "\t\t<label>Субъект Федерации</label>\n" +
            "\t\tМосковская область\n" +
            "\t</div>\n" +
            "\t<div class=\"form-item\">\n" +
            "\t\t<label>Наименование ФО</label>\n" +
            "\t\t«Русский земельный банк» АКБ (ОАО)\n" +
            "\t</div>\n" +
            "\t<div class=\"form-item\">\n" +
            "\t\t<label>Является залогом</label>\n" +
            "\t\tНет\n" +
            "\t</div>\n" +
            "\t<div class=\"form-item\">\n" +
            "\t\t<label>Прочее</label>\n" +
            "\t\tПроцедура межевания не проведена\n" +
            "\t</div>\n" +
            "\t<div class=\"form-item\">\n" +
            "\t\t<label>Тип</label>\n" +
            "\t\tЗемельные участки\n" +
            "\t</div>\n" +
            "\t<div class=\"form-item\">\n" +
            "\t\t<label>Площадь</label>\n" +
            "\t\t32001048\n" +
            "\t</div>\n" +
            "\t<div class=\"form-item\">\n" +
            "\t\t<label>Разрешенное использование</label>\n" +
            "\t\tДля сельскохозяйственного производства\n" +
            "\t</div>\n" +
            "\t<div class=\"form-item\">\n" +
            "\t\t<label>Расстояние до областного центра</label>\n" +
            "\t\t25\n" +
            "\t</div>\n" +
            "\t<div class=\"form-item\">\n" +
            "\t\t<label>Кадастровый номер</label>\n" +
            "\t\t50:03:0000000:166\n" +
            "\t</div>\n" +
            "\t<div class=\"form-item\">\n" +
            "\t\t<label>Дата оценки</label>\n" +
            "\t\t24.12.2014\n" +
            "\t</div>\n" +
            "</fieldset>";

//    @Ignore
//    @Test
//    public void test() throws SQLException, UnsupportedEncodingException {
//        BkrExchangeUnloadController bkrExchangeUnloadController = new BkrExchangeUnloadController();
//        Connection conn = null;
//        conn = HibernateUtil.getInstance().getConnectionProviderBKR().getConnection();
//        byte[] fileBody = testString.getBytes("UTF-8");
//        IBkrPublicationBodyParams bodyParams = new BkrPublicationBodyParams("html_asset.html", fileBody, BkrTypePublicationBody.HTML.getUnid());
//        //bkrExchangeUnloadController.exportPublicationBody(conn, 2527, bodyParams);
//        conn.commit();
//        conn.close();
//    }
}
