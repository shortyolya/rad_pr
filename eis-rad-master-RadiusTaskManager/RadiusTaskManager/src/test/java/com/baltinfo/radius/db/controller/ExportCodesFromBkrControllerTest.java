package com.baltinfo.radius.db.controller;

import com.baltinfo.radius.db.model.bankruptcy.VAuctionBkr;
import org.junit.Ignore;
import org.junit.Test;

public class ExportCodesFromBkrControllerTest {

    @Test
    @Ignore
    public void test() {
        ExportCodesFromBkrController exportCodesFromBkrController = new ExportCodesFromBkrController();
        VAuctionBkr vAuctionBkr = exportCodesFromBkrController.getAuctionFromBkr(960000012501L);
    }
}
