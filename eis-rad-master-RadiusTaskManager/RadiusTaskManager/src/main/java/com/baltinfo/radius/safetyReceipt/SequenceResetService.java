package com.baltinfo.radius.safetyReceipt;

import com.baltinfo.radius.db.controller.SafetyReceiptController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SequenceResetService {

    private static final Logger logger = LoggerFactory.getLogger(SequenceResetService.class);
    private final SafetyReceiptController safetyReceiptController;

    public SequenceResetService(SafetyReceiptController safetyReceiptController) {
        this.safetyReceiptController = safetyReceiptController;
    }

    public void runProcedure() {
        safetyReceiptController.resetSequence();
    }

}
