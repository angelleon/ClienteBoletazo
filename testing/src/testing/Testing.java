
public class Testing
{

    public static void main(String[] args)
    {
        try
        {
            // 1) client type 1 (without sigup) but... with all steps
            // 2) part of conversation
            // 3) with the third parameter is the number of tickets requested,
            //    they are selected randomly
            // 4) idsession = 0 -> anonymus session, otherwise it will be your session id
            Thread t_client = new ThreadClient(1, 6,4,1);
            
            t_client.start();
            t_client.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }       
    
    }

}
