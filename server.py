#!/usr/bin/python           # This is server.py file

import socket               # Import socket module

s = socket.socket()         # Create a socket object
host = socket.gethostname() # Get local machine name
port = 12345                # Reserve a port for your service.
s.bind((host, port))        # Bind to the port

s.listen(5)                 # Now wait for client connection.
while True:
   c, addr = s.accept()     # Establish connection with client.
   print 'Got connection from', addr
   c.send('Thank you for connecting')
   import RPi.GPIO as GPIO
   import time

   GPIO.setwarnings(False)
   GPIO.setmode(GPIO.BCM)
   GPIO.setup(18, GPIO.OUT)

   state = True

   # endless loop, on/off for 1 second
   while True:
    GPIO.output(18,True)
    time.sleep(1)
    GPIO.output(18,False)
    time.sleep(1)
   c.close()                # Close the connection
