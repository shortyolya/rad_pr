package com.baltinfo.radius.lotonline.export;

import com.baltinfo.radius.radapi.security.Token;
import com.baltinfo.radius.radapi.security.TokenService;
import com.baltinfo.radius.utils.Result;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class LotOnlineProtocolServiceTest {

//    @Test
    public void test() {
//        {"id": 16, "memberId": 1195, "bkrId": 1020862, "lotId": 2451001}
//        {\"id\":\"732\",\"bkrId\":\"1019844\",\"lotId\":\"1710001\"}"
        Token token = new Token("732", "1019844", "1710001");
        TokenService tokenService = new TokenService("supersecretdemotocken", 5200);

        Result<String, String> resultToken = tokenService.generateToken(token);

        LotOnlineProtocolService lotOnlineProtocolService = new LotOnlineProtocolService("http://office2.lot-online.ru/", 60000);
        Result<byte[], String> result = lotOnlineProtocolService.getProtocol(54001003L, "Протокол рассмотрения заявок (ParticipationRequestsProcessed001).pdf", resultToken.getResult());
        if (result.isSuccess()) {
            File dir = new File("D:\\temp\\" + File.separator + new SimpleDateFormat("yyyyMMdd").format(new Date()));
            if (!dir.exists()) {
                dir.mkdir();
            }
            try {
                File file = File.createTempFile("tmp-", ".doc", dir);

                BufferedOutputStream output = new BufferedOutputStream(Files.newOutputStream(file.toPath()));
                output.write(result.getResult());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}