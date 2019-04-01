package itq.dist;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
//import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadClient extends Thread
{
    private static final Logger LOG = LogManager.getLogger(ThreadClient.class);
    static final String HOST = "localhost";// "<ipServer>";
    static final int PORT = 2000; // <portShared>

    private static DataOutputStream msgOut;
    private int client;
    private int step;
    // variables to test
    private Socket cltSocket;

    public ThreadClient(int client, int step)
    {
        this.client = client;
        this.step = step;
    }

    /**
     * Open the communication to client
     * 
     * @return outStream send menssage
     * @throws UnknownHostException
     * @throws IOException
     */
    public DataOutputStream newMessage() throws UnknownHostException, IOException
    {
        cltSocket = new Socket(HOST, PORT);
        OutputStream outStream = cltSocket.getOutputStream();
        return new DataOutputStream(outStream);
    }

    /**
     * Listen to client response
     * 
     * @return a string with information for the server
     * @throws IOException
     *             if something bad happens ...
     */
    public String reply() throws IOException
    {

        // cltSocket = new Socket(HOST,PORT);
        InputStream inStream = cltSocket.getInputStream();
        DataInputStream dataIn = new DataInputStream(inStream);
        return dataIn.readUTF().toString();
    }

    public void step(int step)
    {
        switch (step)
        {
        case 0:
            cStartSession();
            sStartSession();
            break;
        case 2:
            getEventList();
            postEventList();
            break;
        case 4:
            getEventInfo();
            postAvailableDates();
            break;
        case 6:
            getAvailableSeats();
            postAvailableSeats();
            break;
        case 8:
            requestReserveTickets();
            confirmReserveTickets();
            break;
        case 10:
            sigup();
            sigupStatus();
            break;
        case 12:
            loginCheck();
            loginStatus();
            break;
        case 14:
            postPaymentInfo();
            purchaseCompleted();
            break;
        }
    }

    @Override
    public void run()
    {
        if (step != 0)
        {
            step(step);
        }
        else
        {
            cStartSession();
            sStartSession();
            getEventList();
            postEventList();
            getEventInfo();
            postAvailableDates();
            getAvailableSeats();
            postAvailableSeats();
            requestReserveTickets();
            confirmReserveTickets();
            if (client == 2)
            {
                sigup();
            }
            sigupStatus();
            loginCheck();
            loginStatus();
            postPaymentInfo();
            purchaseCompleted();
        }
    }

    private boolean cStartSession()
    {
        try
        {

            LOG.info("Init conversation ");
            msgOut = newMessage();
            msgOut.writeUTF("0,null");
            // cltSocket.close();
            LOG.debug("enviado :  0,null");
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(0) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(0) IOException ");
        }
        return false;
    }

    private boolean getEventList()
    {
        try
        {
            msgOut = newMessage();
            // �2,1020,Concierto0,Auditorio Nacional,19-03-2019,17,0,null�
            msgOut.writeUTF("2,1020,Concierto0,Auditorio Nacional,19-03-2019,17,0,null");
            // cltSocket.close();
            LOG.debug("enviado :  2,1020,Concierto0,Auditorio Nacional,19-03-2019,17,0,null");
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(2) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(2) IOException ");
        }
        return false;
    }

    private boolean getEventInfo()
    {
        try
        {

            msgOut = newMessage();
            // �4,1020,1�
            msgOut.writeUTF("4,1020,1");
            // cltSocket.close();
            LOG.debug("4,1020,1");
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(4) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(4) IOException ");
        }
        return false;
    }

    private boolean getAvailableSeats()
    {
        try
        {

            msgOut = newMessage();
            // �6,1020,1,21�
            msgOut.writeUTF("6,1020,1,21");
            LOG.debug("6,1020,1,21");
            // cltSocket.close();
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(6) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(6) IOException ");
        }
        return false;
    }

    private boolean requestReserveTickets()
    {
        try
        {
            msgOut = newMessage();
            // �8,1020,1,4,23,24,25,26�
            msgOut.writeUTF("8,1020,1,4,23,24,25,26");
            // cltSocket.close();
            LOG.debug("8,1020,1,4,23,24,25,26");
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(8) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(8) IOException ");
        }
        return false;
    }

    private boolean sigup()
    {
        try
        {
            msgOut = newMessage();
            // �10,1020,juanitoPerez,contrapass,juanito@gmail.com,Queretaro�
            msgOut.writeUTF("10,1020,juanitoPerez,contrapass,juanito@gmail.com,Queretaro");
            // cltSocket.close();
            LOG.debug("10,1020,juanitoPerez,contrapass,juanito@gmail.com,Queretaro");
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(10) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(10) IOException ");
        }
        return false;
    }

    private boolean loginCheck()
    {
        try
        {
            msgOut = newMessage();
            // �12,1020,juanitoPerez,contrapassword�
            msgOut.writeUTF("12,1020,juanitoPerez,contrapassword");
            // cltSocket.close();
            LOG.debug("12,1020,juanitoPerez,contrapassword");
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(12) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(12) IOException ");
        }
        return false;
    }

    private boolean postPaymentInfo()
    {
        try
        {
            msgOut = newMessage();
            // !!!!!!!!!!!!!!!!!!!!!!!!!!!!! se mando la interfaz con #12 en lugar de 14
            // ahora que houston!?
            // �12,2020,1234-1234-1234-1234,04/22,333, VISA|MASTERCARD�
            msgOut.writeUTF("14,2020,1234-1234-1234-1234,04/22,333,VISA");
            // cltSocket.close();

            LOG.debug("14,2020,1234-1234-1234-1234,04/22,333,VISA");
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(14) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(14) IOException ");
        }
        return false;
    }

    /**
     * Server steps , only show the response ... nothing to do
     */
    private boolean sStartSession()
    {
        try
        {
            String input = reply();
            LOG.debug("Response serv: " + input);
            // to do something...
            cltSocket.close();
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(1) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(1) IOException" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private boolean postEventList()
    {
        try
        {
            String input = reply();
            LOG.debug("Response serv: " + input);
            // to do something...
            cltSocket.close();
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(3) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(3) IOException" + e.getMessage());
        }
        return false;
    }

    private boolean postAvailableDates()
    {
        try
        {
            String input = reply();
            LOG.debug("Response serv: " + input);
            // to do something...
            cltSocket.close();
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(5) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(5) IOException" + e.getMessage());
        }
        return false;
    }

    private boolean postAvailableSeats()
    {
        try
        {
            String input = reply();
            LOG.debug("Response serv: " + input);
            // to do something...
            cltSocket.close();
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(7) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(7) IOException" + e.getMessage());
        }
        return false;
    }

    private boolean confirmReserveTickets()
    {
        try
        {
            String input = reply();
            LOG.debug("Response serv: " + input);
            // to do something...
            cltSocket.close();
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(11) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(11) IOException" + e.getMessage());
        }
        return false;
    }

    private boolean sigupStatus()
    {
        try
        {
            String input = reply();
            LOG.debug("Response serv: " + input);
            // to do something...
            cltSocket.close();
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(5) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(5) IOException" + e.getMessage());
        }
        return false;
    }

    private boolean loginStatus()
    {
        try
        {
            String input = reply();
            LOG.debug("Response serv: " + input);
            // to do something...
            cltSocket.close();
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(13) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(13) IOException" + e.getMessage());
        }
        return false;
    }

    private boolean purchaseCompleted()
    {
        try
        {
            String input = reply();
            LOG.debug("Response serv: " + input);
            // to do something...
            cltSocket.close();
            return true;
        }
        catch (UnknownHostException e)
        {
            LOG.error("(15) without conection to host " + HOST + " on port " + PORT);
            e.printStackTrace();

        }
        catch (IOException e)
        {
            LOG.error("(15) IOException" + e.getMessage());
        }
        return false;
    }
}
