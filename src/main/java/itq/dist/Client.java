package itq.dist;

public class Client
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
            Thread t_client = new ThreadClient(1, 0,4,0);
            
            t_client.start();
            t_client.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        /*
         * //client type 2 ...with sigup!, with all steps 
         * Thread t_client2 = new
         * ThreadClient(2,0); 
         * t_client2.start();
         * 
         * 
         * int step = 0; Thread tClientStep = new ThreadClient(1,step);
         * tClientStep.start();
         * 
         * Thread tClientStep2 = new ThreadClient(2,step); tClientStep2.start();
         */
    }
}
