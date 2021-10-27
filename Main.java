import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Main {
    public static void get(StringBuilder sb) {
    while(true){
        String result;
        if (sb.lastIndexOf("<Value>") != -1) {
            String text3 = sb.substring(sb.lastIndexOf("<Value>") + 7, sb.lastIndexOf("</Value>"));

            System.out.print("Стоимость: " + text3 + ". ");
            sb.setLength(sb.length()-(sb.length()-sb.lastIndexOf("<Value>")));
        }

        if (sb.lastIndexOf("<Name>") != -1) {
            String text2 = sb.substring(sb.lastIndexOf("<Name>") + 6, sb.lastIndexOf("</Name>"));

            System.out.print(text2 + ", ");
            sb.setLength(sb.length()-(sb.length()-sb.lastIndexOf("<Name>")));
        }

        if (sb.lastIndexOf("<Nominal>") != -1) {
            String text1 = sb.substring(sb.lastIndexOf("<Nominal>") + 9, sb.lastIndexOf("</Nominal>"));

            System.out.print(text1 + ", ");
            sb.setLength(sb.length()-(sb.length()-sb.lastIndexOf("<Nominal>")));
        }
        
            if (sb.lastIndexOf("<CharCode>") != -1) {
                String text = sb.substring(sb.lastIndexOf("<CharCode>") + 10, sb.lastIndexOf("</CharCode>"));
                System.out.print(text + ". " + "\n");
             sb.setLength(sb.length()-(sb.length()-sb.lastIndexOf("<CharCode>")));
            }


            
}

    }
    public static void main(String[] args) throws IOException {
        String server = "www.cbr.ru";
        StringBuilder sb= new StringBuilder();

        InetAddress iAddress = InetAddress.getByName(server);
        Socket ClientSocket = new Socket(iAddress, 80);

        BufferedReader br = null;
        PrintWriter pw = null;
        br = new BufferedReader ( new InputStreamReader(ClientSocket.getInputStream()));
        pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter( ClientSocket.getOutputStream())), true);
        pw.println("GET /scripts/XML_daily.asp " + "HTTP/1.1\r\nHost: " +server +"\r\n\r\n");

        String response;
        while((response = br.readLine())!=null) {
            sb.append(response);
            sb.append("\n");
        }
        get(sb);
        ClientSocket.close();
        br.close();
        pw.close();
    }
}
