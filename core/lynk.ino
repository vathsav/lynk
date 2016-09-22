/* Function prototypes -------------------------------------------------------*/
int lynkDigitalRead(String pin);
int lynkDigitalWrite(String command);
int lynkAnalogRead(String pin);
int lynkAnalogWrite(String command);
int lynkBlow(String code);

SYSTEM_MODE(SEMI_AUTOMATIC);

void setup() {
	// Restore states of all digital pins
	int pinNumber = 0;
	while (pinNumber <= 7) {
		pinMode(pinNumber, OUTPUT);
		digitalWrite(pinNumber, EEPROM.read(pinNumber + 10));
		pinNumber++;
	}

	// Connect to the cloud
    Particle.connect();

	//Register all the Lynk functions
	Particle.function("digitalRead", lynkDigitalRead);
	Particle.function("digitalWrite", lynkDigitalWrite);
	Particle.function("analogRead", lynkAnalogRead);
	Particle.function("analogWrite", lynkAnalogWrite);
	Particle.function("skadoosh", lynkBlow);
}

void loop() {}

/*******************************************************************************
 * Function Name  : lynkDigitalRead
 * Description    : Reads the digital value of a given pin
 * Input          : Pin
 * Output         : None.
 * Return         : Value of the pin (0 or 1) in INT type
                    Returns a negative number on failure
 *******************************************************************************/
int lynkDigitalRead(String pin) {
	//convert ascii to integer
	int pinNumber = pin.charAt(1) - '0';
	//Sanity check to see if the pin numbers are within limits
	if (pinNumber < 0 || pinNumber > 7) return -1;

	if(pin.startsWith("D")) {
		pinMode(pinNumber, INPUT_PULLDOWN);
		return digitalRead(pinNumber);
	}
	else if (pin.startsWith("A")) {
		pinMode(pinNumber + 10, INPUT_PULLDOWN);
		return digitalRead(pinNumber + 10);
	}
	return -2;
}

/*******************************************************************************
 * Function Name  : lynkDigitalWrite
 * Description    : Sets the specified pin HIGH or LOW
 * Input          : Pin and value
 * Output         : None.
 * Return         : 1 on success and a negative number on failure
 *******************************************************************************/
int lynkDigitalWrite(String command) {
	bool value = 0;
	//convert ascii to integer
	int pinNumber = command.charAt(1) - '0';
	//Sanity check to see if the pin numbers are within limits
	if (pinNumber < 0 || pinNumber > 7) return -1;

	if(command.substring(3) == "1") value = 1;
	else if(command.substring(3) == "0") value = 0;
	else return -2;

	if(command.startsWith("D")) {
		pinMode(pinNumber, OUTPUT);
		digitalWrite(pinNumber, value);

		// Using the pin number as address!
		EEPROM.write(pinNumber + 10, value);
        return 1;
	}
	else if(command.startsWith("A")) {
		pinMode(pinNumber + 10, OUTPUT);
		digitalWrite(pinNumber + 10, value);

		// Using the pin number as address!
		//EEPROM.write(pinNumber, value);
		return 1;
	}
	else return -3;
}

/*******************************************************************************
 * Function Name  : lynkAnalogRead
 * Description    : Reads the analog value of a pin
 * Input          : Pin
 * Output         : None.
 * Return         : Returns the analog value in INT type (0 to 4095)
                    Returns a negative number on failure
 *******************************************************************************/
int lynkAnalogRead(String pin) {
	//convert ascii to integer
	int pinNumber = pin.charAt(1) - '0';
	//Sanity check to see if the pin numbers are within limits
	if (pinNumber < 0 || pinNumber > 7) return -1;

	if(pin.startsWith("D")) {
		return -3;
	}   
	else if (pin.startsWith("A")) {
		return analogRead(pinNumber + 10);
	}
	return -2;
}

/*******************************************************************************
 * Function Name  : lynkAnalogWrite
 * Description    : Writes an analog value (PWM) to the specified pin
 * Input          : Pin and Value (0 to 255)
 * Output         : None.
 * Return         : 1 on success and a negative number on failure
 *******************************************************************************/
int lynkAnalogWrite(String command) {
	//convert ascii to integer
	int pinNumber = command.charAt(1) - '0';
	//Sanity check to see if the pin numbers are within limits
	if (pinNumber < 0 || pinNumber > 7) return -1;

	String value = command.substring(3);

	if(command.startsWith("D")) {
		pinMode(pinNumber, OUTPUT);
		analogWrite(pinNumber, value.toInt());
		return 1;
	}
	else if(command.startsWith("A")) {
		pinMode(pinNumber + 10, OUTPUT);
		analogWrite(pinNumber + 10, value.toInt());
		return 1;
	}
	else return -2;
}

/*******************************************************************************
 * Function Name  : supermassiveEmp
 * Description    : Sends a low to all pins on the board
 * Input          : 007
 * Output         : None.
 * Return         : 1 on success and -1 on failure
 *******************************************************************************/
 int lynkBlow(String command) {
	if (command.startsWith("pandasareawesome")) {
		// Send a HIGH (Relay is inverted for now :P) to all digital pins
		// TODO: Flip this.
		pinMode(0, OUTPUT);
		digitalWrite(0, HIGH);
		pinMode(1, OUTPUT);
		digitalWrite(1, HIGH);
		pinMode(2, OUTPUT);
		digitalWrite(2, HIGH);
		pinMode(3, OUTPUT);
		digitalWrite(3, HIGH);
		pinMode(4, OUTPUT);
		digitalWrite(4, HIGH);
		pinMode(5, OUTPUT);
		digitalWrite(5, HIGH);
		pinMode(6, OUTPUT);
		digitalWrite(6, HIGH);
		pinMode(7, OUTPUT);
		digitalWrite(7, HIGH);

		// Send a LOW to all analog pins
		pinMode(10, OUTPUT);
		analogWrite(10, 0);
		pinMode(11, OUTPUT);
		analogWrite(11, 0);
		pinMode(12, OUTPUT);
		digitalWrite(12, LOW);
		pinMode(13, OUTPUT);
		analogWrite(13, 0);
		pinMode(14, OUTPUT);
		analogWrite(14, 0);
		pinMode(15, OUTPUT);
		analogWrite(15, 0);
		pinMode(16, OUTPUT);
		analogWrite(16, 0);
		pinMode(17, OUTPUT);
		analogWrite(17, 0);
	} else {
		return -1;
	}
 }