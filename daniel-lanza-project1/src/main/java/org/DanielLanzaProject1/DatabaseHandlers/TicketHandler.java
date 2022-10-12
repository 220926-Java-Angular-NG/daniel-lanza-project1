package org.DanielLanzaProject1.DatabaseHandlers;

import org.DanielLanzaProject1.DataTypes.Ticket;
import org.DanielLanzaProject1.DatabaseSQL.TicketDatabase;

import java.util.List;

public class TicketHandler {

    private TicketDatabase ticketDb;

    public TicketHandler(){
        ticketDb = new TicketDatabase();
    }

    public TicketHandler(TicketDatabase ticketDb){
        this.ticketDb = ticketDb;
    }


    public int createTicket(Ticket ticket){
        return ticketDb.create(ticket);
    }

    public List<Ticket> getAllTickets(){
        return ticketDb.getAll();
    }

    public List<Ticket> getAllPendingTickets(){
        return ticketDb.getAllPending();
    }

    public String getPrintedTickets(){
        List<Ticket> tickets = this.getAllTickets();
        String header = "      ID       "
                      + "     CASH      "
                      + "  DESCRIPTION  "
                      + "    STATUS     "
                      + "SUBMISSION TIME" + "\n";

        for(int i=0;i<tickets.size();i++){
            header = header + tickets.get(i).toString();
        }

        return header;
    }

    public String getPrintedTickets(int id){
        List<Ticket> tickets = ticketDb.getAllById(id);
        String header = "ID       "
                + "  CASH      "
                + "  DESCRIPTION  "
                + "    STATUS     "
                + "SUBMISSION TIME" + "\n";

        for(int i=0;i<tickets.size();i++){
            header = header + tickets.get(i).toString();
        }

        return header;
    }

    public Ticket getByID(int id){
        return ticketDb.getId(id);
    }

    public Ticket updateTicket(Ticket ticket){
        return ticketDb.update(ticket);
    }

    public String deleteTicket(Ticket ticket){
        if(ticketDb.delete(ticket)){
            return "Ticket has been successfully deleted";
        }else{
            return "Ticket could not be deleted";
        }
    }

}
