package httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;

public class SSLDemo {
    public static void main(String[] args) throws Exception {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial((chain, authType) -> true)
                .build();


        SSLConnectionSocketFactory sslConnectionSocketFactory2 = new SSLConnectionSocketFactory(
                sslcontext,
                null,
                null,
                (s, sslSession) -> true);


        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory2).build();
        try {

            HttpPost httpPost = new HttpPost("https://10.163.170.147:8181/CAI3G1.2/services/CAI3G1.2");
//            HttpPost httpPost = new HttpPost("http://10.163.170.147:8080/CAI3G1.2/services/CAI3G1.2");
            setHttpConfiguration(httpPost);

            System.out.println(httpPost.getConfig().getSocketTimeout());
            String xml = getXml();
            StringEntity data = new StringEntity(xml, StandardCharsets.UTF_8);
            httpPost.addHeader("Content-Type", "text/xml;charset=UTF-8");
            httpPost.setEntity(data);


            CloseableHttpResponse response = httpclient.execute(httpPost);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println(EntityUtils.toString(entity, "UTF-8"));
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }

    static void setHttpConfiguration(HttpPost httpPost){
        String connectTimeout = "5000";
        String connectionRequestTimeout = "1000";
        String socketTimeout = "4000";

        RequestConfig.Builder builder = RequestConfig.custom();
        builder.setConnectTimeout(Integer.parseInt(connectTimeout));
        builder.setConnectionRequestTimeout(Integer.parseInt(connectionRequestTimeout));
        builder.setSocketTimeout(Integer.parseInt(socketTimeout));

        httpPost.setConfig(builder.build());
    }

    static String getXml(){
        return "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:add=\"http://www.w3.org/2005/08/addressing\" xmlns:oas=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:cai3=\"http://schemas.ericsson.com/cai3g1.2/\" xmlns:gsm=\"http://schemas.ericsson.com/ma/CA/GSMSUB3/\">\n" +
                "   <soapenv:Header>\n" +
                "      <add:MessageID/>\n" +
                "      <oas:Security>\n" +
                "         <oas:UsernameToken>\n" +
                "            <oas:Username>mtn-tester</oas:Username>\n" +
                "            <oas:Password>Ericsson1234$</oas:Password>\n" +
                "         </oas:UsernameToken>\n" +
                "      </oas:Security>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                " <cai3:Set>\n" +
                "         <cai3:MOType>MTNGSMSUB3@http://schemas.ericsson.com/ma/CA/GSMSUB3/</cai3:MOType>\t>> Fixed\n" +
                "         <cai3:MOId>\n" +
                "            <gsm:serviceName>ActVasServices</gsm:serviceName>\t\t\t\t\t\t\t\t\n" +
                "\t\t\t<gsm:msisdn>2349062058887</gsm:msisdn>\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
                "\t\t\t<gsm:transactionID>202712134_27012019083922</gsm:transactionID>\t\t\t\t\t\n" +
                "         </cai3:MOId>\n" +
                "         <cai3:MOAttributes>\n" +
                "            <gsm:SetMTNGSMSUB serviceName=\"ActVasServices\">\t\t\t\t\t\t\t\t\t\n" +
                "               <gsm:actVasService>\n" +
                "                  <gsm:msisdn>2349062058887</gsm:msisdn>\n" +
                "                  <gsm:serviceName>ActVasServices</gsm:serviceName>\n" +
                "\t\t\t\t  <gsm:serviceClass>186</gsm:serviceClass>\n" +
                "\t\t\t\t  <gsm:prodIdentifier>BizPlus</gsm:prodIdentifier>\n" +
                "                  <gsm:pricePlan>PRE</gsm:pricePlan>\t\t\t\t\t\t\t\t\n" +
                "                  <gsm:loginAccount>sdpUser</gsm:loginAccount>\t\t\t\t\n" +
                "                  <gsm:transactionID>202712134_27012019083922</gsm:transactionID>\n" +
                "               </gsm:actVasService>\n" +
                "            </gsm:SetMTNGSMSUB>\n" +
                "         </cai3:MOAttributes>\n" +
                "      </cai3:Set>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
