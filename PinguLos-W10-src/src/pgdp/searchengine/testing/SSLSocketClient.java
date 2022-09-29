package pgdp.searchengine.testing;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;

/*
 * This example demostrates how to use a SSLSocket as client to
 * send a HTTP request and get response from an HTTPS server.
 * It assumes that the client is not behind a firewall
 */

public class SSLSocketClient {

    public static void main(String[] args) throws Exception {
        try {
        	String host = "www.verisign.com";
        	String path = "/sg.1.html";
        	int port = 443;
        	
            SSLSocketFactory factory =
                    (SSLSocketFactory)SSLSocketFactory.getDefault();
            SSLSocket socket =
                    (SSLSocket)factory.createSocket(host, port);


            socket.startHandshake();

            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    socket.getOutputStream())));


            StringBuilder output = new StringBuilder();
            output.append("GET ");
            output.append(path);
            output.append(" HTTP/1.1\r\n");
            output.append("Host: ");
            output.append(host);
            output.append("\r\n");
            output.append("\r\n");
            out.print(output.toString());
            out.flush();
            
            /*-
            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();
             */
            
            /*-
            String[] array = {
                    "GET /sg.1.html HTTP/1.1",
                    "Host: man1.pgdp.sse.in.tum.de",
                    "Connection: keep-alive",
                    "Cache-Control: max-age=0",
                    "sec-ch-ua: \" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"97\", \"Chromium\";v=\"97\"",
                    "sec-ch-ua-mobile: ?0",
                    "sec-ch-ua-platform: \"Windows\"",
                    "Upgrade-Insecure-Requests: 1",
                    "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36",
                    "Sec-Fetch-Site: none",
                    "Sec-Fetch-Mode: navigate",
                    "Sec-Fetch-User: ?1",
                    "Sec-Fetch-Dest: document",
                    "Accept-Encoding: gzip, deflate, br",
                    "Accept-Language: en,en-US;q=0.9,tr;q=0.8",
                    "If-None-Match: \"a6a-5d3a4bdc9c188-gzip\"",
                    "If-Modified-Since: Tue, 21 Dec 2021 09:36:35 GMT",
                    "Accept: text/html",

            };
             */

            /*-
            for (String param :  array) {
                out.print(param + "\r\n");
            }
            out.flush();
             */

            /*
             * Make sure there were no surprises
             */
            if (out.checkError())
                System.out.println(
                        "SSLSocketClient:  java.io.PrintWriter error");

            /* read response */
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

            in.close();
            out.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}