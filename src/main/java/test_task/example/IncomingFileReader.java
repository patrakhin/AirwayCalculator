package test_task.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IncomingFileReader {
    private static final Logger logger = LogManager.getLogger(IncomingFileReader.class);

    private IncomingFileReader(){
        throw new IllegalStateException("IncomingFileReader class");
    }

    public static List<EntityAirTicket> readFlightTickets(String filePath) throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(new File(filePath));
            JsonNode ticketsNode = jsonNode.get("tickets");

            List<EntityAirTicket> tickets = new ArrayList<>();

            for (JsonNode ticketNode : ticketsNode) {
                EntityAirTicket ticket = objectMapper.treeToValue(ticketNode, EntityAirTicket.class);
                tickets.add(ticket);
            }
            return tickets;
        } catch (IOException e) {
            logger.error("Ошибка при чтении файла tickets.json: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}

