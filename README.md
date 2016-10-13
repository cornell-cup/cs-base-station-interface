# cs-base-station-interface
Interface to represent any base station

# Requirements
* GSON by Google - Necessary for serializing and deserializing JSON. Add this to your classpath. It can be found on Maven.
* Ice by ZeroC - Necessary for backwards compatibility communication with modbots. You can install ice natively in order to compile slice files to your native language; however, only the Java jar is needed to run the station. This will be added as a maven dependency in the future. See https://zeroc.com/
* Spark for Java - Hosts the HTTP server. Optional if the HTTP server is not being used. http://sparkjava.com/
