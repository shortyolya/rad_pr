package com.baltinfo.radius.dep.exchange;

import com.baltinfo.radius.db.constants.ExchangeProcStatus;
import com.baltinfo.radius.db.constants.ExchangeProcs;
import com.baltinfo.radius.db.controller.BkrDepController;
import com.baltinfo.radius.db.controller.ExchangeProcController;
import com.baltinfo.radius.db.controller.LoDepController;
import com.baltinfo.radius.db.model.ExchangeProcRun;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.Optional;

public class DepExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(DepExchangeService.class);
    private final ExchangeProcController exchangeProcController;
    private final BkrDepController bkrDepController;
    private final LoDepController loDepController;

    public DepExchangeService(ExchangeProcController exchangeProcController, BkrDepController bkrDepController, LoDepController loDepController) {
        this.exchangeProcController = exchangeProcController;
        this.bkrDepController = bkrDepController;
        this.loDepController = loDepController;
    }

    public void getBkrAccountInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_BKR_ACCOUNT_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = bkrDepController.getAccountInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error getBkrAccountInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    public void getBkrReturnInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_BKR_GET_RETURN_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = bkrDepController.getReturnInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error getBkrReturnInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    public void sendBkrPaymentInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_BKR_PAYMENT_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = bkrDepController.sendPaymentInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error sendBkrPaymentInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    public void sendBkrReturnInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_BKR_SEND_RETURN_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = bkrDepController.sendReturnInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error sendBkrReturnInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    public void getBkrReturnToOrgInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_BKR_GET_RETURN_TO_ORG_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = bkrDepController.getReturnToOrgInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error getBkrReturnToOrgInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    public void getBkrTransferToOperatorInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_BKR_GET_TRANSFER_TO_OPERATOR_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = bkrDepController.getTransferToOperatorInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error getBkrReturnToOrgInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    public void getLoAccountInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_LO_ACCOUNT_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = loDepController.getAccountInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error getLoAccountInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    public void getLoReturnInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_LO_GET_RETURN_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = loDepController.getReturnInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error getLoReturnInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    public void sendLoPaymentInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_LO_PAYMENT_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = loDepController.sendPaymentInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error sendLoPaymentInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    public void getLoReturnToOrgInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_LO_GET_RETURN_TO_ORG_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = loDepController.getReturnToOrgInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error getLoReturnToOrgInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    public void getLoRealizationInfo() {
        Optional<ExchangeProcRun> exchangeProcRunOpt = exchangeProcController
                .getFirstRunningExchangeProcRun(ExchangeProcs.DEP_LO_GET_REALIZATION_INFO);
        if (!exchangeProcRunOpt.isPresent()) {
            return;
        }
        ExchangeProcRun epr = exchangeProcRunOpt.get();
        try {
            updateEprRunning(epr);
            Result<String, String> result = loDepController.getRealizationInfo(epr);
            if (result.isSuccess()) {
                updateEprSuccess(epr, result.getResult());
            } else {
                updateEprError(epr, result.getError());
            }
        } catch(Exception ex) {
            logger.error("Error getLoRealizationInfo", ex);
            updateEprError(epr, getStackTrace(ex));
        }
    }

    private void updateEprRunning(ExchangeProcRun epr) {
        epr.setEprLoadStatus(ExchangeProcStatus.RUNNING.getCode());
        epr.setEprRunDate(new Date());
        exchangeProcController.updateExchangeProcRun(epr);
    }

    private void updateEprError(ExchangeProcRun epr, String error) {
        epr.setEprLoadStatus(ExchangeProcStatus.ERROR_RUNNING.getCode());
        String fullError = error;
        if (epr.getEprErrorText() != null) {
            fullError = String.join(";\n", epr.getEprErrorText(), error);
        }
        epr.setEprErrorText(fullError);
        exchangeProcController.updateExchangeProcRun(epr);
    }

    private void updateEprSuccess(ExchangeProcRun epr, String error) {
        epr.setEprLoadStatus(ExchangeProcStatus.FINISHED.getCode());
        String fullError = error;
        if (epr.getEprErrorText() != null) {
            fullError = String.join(";\n", epr.getEprErrorText(), error);
        }
        epr.setEprErrorText(fullError);
        exchangeProcController.updateExchangeProcRun(epr);
    }

    private String getStackTrace(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        return sw.toString();
    }
}
