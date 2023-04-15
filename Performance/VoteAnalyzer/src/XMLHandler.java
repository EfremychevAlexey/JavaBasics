import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class XMLHandler extends DefaultHandler {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static StringBuilder insertQuery = new StringBuilder();
    private int count = 0;
    private boolean isVoters = false;
    private boolean isVoter = false;

    public XMLHandler() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("voters")) {
            isVoters = true;
        }
        if (isVoters && qName.equals("voter")) {
            isVoter = true;
            String str = (insertQuery.length() == 0 ? "" : ",") +
                    "('" + attributes.getValue("name") + "', '" + attributes.getValue("birthDay")
                    .replace('.', '-') + "', 1)";
            count++;
            insertQuery.append(str);
        }
        if (count == 100_000) {
            try {
                DBConnection.executeMultiInsert(insertQuery);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            insertQuery = new StringBuilder();
            count = 0;
        }
        if (qName.equals("visit") && isVoter == false) {
            throw new RuntimeException();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {


        if (qName.equals("voters")) {
            isVoters = false;
            try {
                DBConnection.executeMultiInsert(insertQuery);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public void printDuplicatedVoters() {
//        for (Voter voter : voterCounts.keySet()) {
//            int count = voterCounts.get(voter);
//            if (count > 1) {
//                System.out.println(voter.toString() + " - " + count);
//            }
//        }
//
//    }

}
