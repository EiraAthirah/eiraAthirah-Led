import java.net.*;
import java.io.*;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


public class serverJ
{
 public static void main(String[] args) throws IOException,InterruptedException
   {
    ServerSocket serverSocket = null;

    try {
         serverSocket = new ServerSocket(10007);
        }
    catch (IOException e)
        {
         System.err.println("Could not listen on port: 10007.");
         System.exit(1);
        }

    Socket clientSocket = null;
System.out.println ("Waiting for connection.....");

    try {
         clientSocket = serverSocket.accept();
        }
    catch (IOException e)
        {
         System.err.println("Accept failed.");
         System.exit(1);
        }

    System.out.println ("Connection successful");

    
/**
 * This example code demonstrates how to perform simple state
 * control of a GPIO pin on the Raspberry Pi.
 *
 * @author Robert Savage
 */


    

        System.out.println("<--Pi4J--> GPIO Control Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #01 as an output pin and turn on
        final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06, "MyLED", PinState.HIGH);

        // set shutdown state for this pin
        pin.setShutdownOptions(true, PinState.LOW);

        System.out.println("--> GPIO state should be: ON");

        Thread.sleep(5000);

        // turn off gpio pin #01
       // pin.low();
        //System.out.println("--> GPIO state should be: OFF");

        Thread.sleep(5000);

        // toggle the current state of gpio pin #01 (should turn on)
       // pin.toggle();
       // System.out.println("--> GPIO state should be: ON");

       // Thread.sleep(5000);

        // toggle the current state of gpio pin #01  (should turn off)
        //pin.toggle();
       // System.out.println("--> GPIO state should be: OFF");

       // Thread.sleep(5000);

        // turn on gpio pin #01 for 1 second and then off
       // System.out.println("--> GPIO state should be: ON for only 1 second");
     //   pin.pulse(1000, true); // set second argument to 'true' use a blocking call

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        //gpio.shutdown();

   //     System.out.println("Exiting ControlGpioExample");
   
    System.out.println ("Waiting for input.....");

    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                                      true);
    BufferedReader in = new BufferedReader(
            new InputStreamReader( clientSocket.getInputStream()));

			String inputLine;

    while ((inputLine = in.readLine()) != null)
        {


         if( inputLine != null) {
         System.out.println ("Message From Client: " + inputLine);
         out.println(inputLine); }


         if (inputLine.equals("Exit."))
             break;
        }

    out.close();
    in.close();
    clientSocket.close();
	pin.low();
	gpio.shutdown();
    serverSocket.close();
   }
   }

